package p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private static final String DB_PATH = "/home/psw/Projects/SSP/LogForm3138/app.db";
    private static final String DB_URL = "jdbc:sqlite:" + DB_PATH;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection con = DriverManager.getConnection(DB_URL);
                 Statement stmt = con.createStatement()) {
                stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "username TEXT UNIQUE NOT NULL, " +
                    "password TEXT NOT NULL, " +
                    "address TEXT, " +
                    "dob TEXT, " +
                    "contact TEXT, " +
                    "city TEXT, " +
                    "gender TEXT" +
                    ")"
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
