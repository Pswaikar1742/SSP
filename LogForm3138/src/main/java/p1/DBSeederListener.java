package p1;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebListener
public class DBSeederListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement()) {

            // create table if missing
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "username TEXT UNIQUE NOT NULL, " +
                    "password TEXT NOT NULL, " +
                    "address TEXT, dob TEXT, contact TEXT, city TEXT, gender TEXT)");

            // check if there are rows
            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM users")) {
                int count = 0;
                if (rs.next()) count = rs.getInt(1);
                if (count == 0) {
                    // load seeding SQL from resource and execute
                    InputStream in = this.getClass().getClassLoader().getResourceAsStream("db/init.sql");
                    if (in != null) {
                        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                            StringBuilder sql = new StringBuilder();
                            String line;
                            while ((line = reader.readLine()) != null) {
                                sql.append(line).append('\n');
                            }
                            // the file contains multiple statements; execute each separated by ;
                            String[] parts = sql.toString().split(";\n");
                            for (String p : parts) {
                                String s = p.trim();
                                if (!s.isEmpty()) stmt.executeUpdate(s);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // nothing
    }
}
