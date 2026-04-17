package com.greenscale.db;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverPropertyInfo;
import java.sql.Driver;
import java.util.List;
import java.util.ArrayList;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.NoSuchFileException;

/**
 * Utility to obtain a JDBC Connection. Supports MySQL (default) and SQLite.
 * If a SQLite URL is used and the database file does not exist, it will
 * initialize the database from `greenscale_sqlite.sql` in the project root.
 */
public final class DatabaseConnection {

    private static final String DEFAULT_SQLITE_URL = "jdbc:sqlite:greenscale.db";

    private static final String JDBC_URL;
    private static final String JDBC_USER;
    private static final String JDBC_PASSWORD;

    private static final Object INIT_LOCK = new Object();
    private static volatile boolean sqliteInitialized = false;

    static {
        JDBC_URL = System.getenv("DB_URL") != null ? System.getenv("DB_URL") : DEFAULT_SQLITE_URL;
        JDBC_USER = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "";
        JDBC_PASSWORD = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "";

        try {
            // Try loading the SQLite driver from the parent classpath. If not present, we'll attempt dynamic loading when obtaining a connection.
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC not found on parent classpath; will attempt runtime discovery: " + e.getMessage());
        } catch (Throwable t) {
            System.err.println("Warning while attempting to load SQLite JDBC driver: " + t.getMessage());
        }
    }

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
        if (JDBC_URL.startsWith("jdbc:sqlite:")) {
            // attempt to load the sqlite driver using the webapp/context classloader
            try {
                Class.forName("org.sqlite.JDBC", true, Thread.currentThread().getContextClassLoader());
            } catch (ClassNotFoundException e) {
                System.err.println("SQLite driver not found via context classloader: " + e.getMessage());
            }
            try {
                Connection conn = DriverManager.getConnection(JDBC_URL);
                initSqliteIfNeeded(conn);
                return conn;
            } catch (SQLException initialEx) {
                // If no suitable driver, try to load the provided jar directly and register the driver
                if (initialEx.getMessage() != null && initialEx.getMessage().toLowerCase().contains("no suitable driver")) {
                    try {
                        java.nio.file.Path jar1 = java.nio.file.Paths.get("src/main/webapp/WEB-INF/lib/sqlite-jdbc-3.47.0.0.jar");
                        java.nio.file.Path jar2 = java.nio.file.Paths.get("sqlite-jdbc-3.47.0.0.jar");
                        java.nio.file.Path jar = java.nio.file.Files.exists(jar1) ? jar1 : jar2;
                        if (java.nio.file.Files.exists(jar)) {
                            URL url = jar.toUri().toURL();
                            try (URLClassLoader ucl = new URLClassLoader(new URL[]{url}, Thread.currentThread().getContextClassLoader())) {
                                Class<?> drvClass = Class.forName("org.sqlite.JDBC", true, ucl);
                                Driver drv = (Driver) drvClass.getDeclaredConstructor().newInstance();
                                DriverManager.registerDriver(new DriverShim(drv));
                                Connection conn = DriverManager.getConnection(JDBC_URL);
                                initSqliteIfNeeded(conn);
                                return conn;
                            }
                        } else {
                            throw initialEx;
                        }
                    } catch (Exception ex) {
                        throw new SQLException("Failed to dynamically load/register sqlite driver", ex);
                    }
                }
                throw initialEx;
            }
        }

        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    private static void initSqliteIfNeeded(Connection conn) throws SQLException {
        if (!JDBC_URL.startsWith("jdbc:sqlite:")) return;
        if (sqliteInitialized) return;

        synchronized (INIT_LOCK) {
            if (sqliteInitialized) return;

            Path script = Paths.get("greenscale_sqlite.sql");
            if (Files.exists(script)) {
                try {
                    try (Statement st = conn.createStatement()) {
                        // If the users table already exists, assume DB is initialized.
                        try (java.sql.ResultSet rs = st.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='users';")) {
                            if (rs.next()) {
                                sqliteInitialized = true;
                                return;
                            }
                        } catch (SQLException ignore) {
                            // if that query fails, fall back to running the script
                        }

                        String sql = Files.readString(script, StandardCharsets.UTF_8);
                        st.execute("PRAGMA foreign_keys = ON;");
                        List<String> statements = splitSqlStatements(sql);
                        for (String stmt : statements) {
                            if (stmt == null) continue;
                            stmt = stmt.trim();
                            if (stmt.isEmpty()) continue;
                            st.execute(stmt);
                        }
                    }
                } catch (IOException e) {
                    throw new SQLException("Failed to read SQLite init script", e);
                }
            }

            sqliteInitialized = true;
        }
    }

    private static List<String> splitSqlStatements(String sql) {
        List<String> stmts = new ArrayList<>();
        if (sql == null || sql.isEmpty()) return stmts;
        StringBuilder cur = new StringBuilder();
        boolean inSingleQuote = false;
        for (int i = 0; i < sql.length(); i++) {
            char c = sql.charAt(i);
            char next = (i + 1 < sql.length()) ? sql.charAt(i + 1) : '\0';

            // handle single-quote string delimiters and escaped quotes ''
            if (c == '\'') {
                cur.append(c);
                if (inSingleQuote && next == '\'') {
                    // escaped single-quote inside string literal
                    cur.append(next);
                    i++; // skip escaped quote
                } else {
                    inSingleQuote = !inSingleQuote;
                }
                continue;
            }

            // skip line comments starting with -- when not in a string
            if (!inSingleQuote && c == '-' && next == '-') {
                // consume until end of line
                int j = i + 2;
                while (j < sql.length() && sql.charAt(j) != '\n') j++;
                i = j;
                continue;
            }

            // skip block comments /* ... */ when not in a string
            if (!inSingleQuote && c == '/' && next == '*') {
                int j = i + 2;
                while (j + 1 < sql.length() && !(sql.charAt(j) == '*' && sql.charAt(j + 1) == '/')) j++;
                i = Math.min(j + 1, sql.length() - 1);
                continue;
            }

            // split on semicolon when not inside a string
            if (!inSingleQuote && c == ';') {
                String s = cur.toString().trim();
                if (!s.isEmpty()) stmts.add(s);
                cur.setLength(0);
                continue;
            }

            cur.append(c);
        }

        String last = cur.toString().trim();
        if (!last.isEmpty()) stmts.add(last);
        return stmts;
    }

    /**
     * Simple wrapper to register a driver loaded from a custom classloader without
     * leaking that classloader into DriverManager's internals.
     */
    private static class DriverShim implements Driver {
        private final Driver delegate;

        DriverShim(Driver d) { this.delegate = d; }

        public boolean acceptsURL(String u) throws SQLException { return delegate.acceptsURL(u); }
        public Connection connect(String u, java.util.Properties p) throws SQLException { return delegate.connect(u, p); }
        public int getMajorVersion() { return delegate.getMajorVersion(); }
        public int getMinorVersion() { return delegate.getMinorVersion(); }
        public DriverPropertyInfo[] getPropertyInfo(String u, java.util.Properties p) throws SQLException { return delegate.getPropertyInfo(u, p); }
        public boolean jdbcCompliant() { return delegate.jdbcCompliant(); }
        public java.util.logging.Logger getParentLogger() { try { return delegate.getParentLogger(); } catch (Exception e) { return java.util.logging.Logger.getAnonymousLogger(); } }
    }
}
