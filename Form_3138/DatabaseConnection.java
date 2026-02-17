import java.sql.*;

public class DatabaseConnection {
    private static final String DRIVER = "org.mariadb.jdbc.Driver";
    private static final String URL = "jdbc:mariadb://localhost:3306/student_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("MariaDB Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }

    public static void initializeDatabase() {
        Connection conn = getConnection();
        try {
            if (conn != null) {
                // Create users table if it doesn't exist
                String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "username VARCHAR(50) UNIQUE NOT NULL, " +
                        "password VARCHAR(255) NOT NULL, " +
                        "address TEXT, " +
                        "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                        ")";
                
                Statement stmt = conn.createStatement();
                stmt.execute(createTableSQL);
                stmt.close();
                System.out.println("Database initialized successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
        } finally {
            closeConnection(conn);
        }
    }
}
