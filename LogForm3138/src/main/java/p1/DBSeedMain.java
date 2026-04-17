package p1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBSeedMain {
    public static void main(String[] args) {
        String dbPath = null;
        if (args != null && args.length > 0) dbPath = args[0];
        if (dbPath == null || dbPath.isEmpty()) {
            dbPath = System.getProperty("db.path");
        }
        if (dbPath == null || dbPath.isEmpty()) {
            System.err.println("DB path not provided. Using ./app.db");
            dbPath = "app.db";
        }
        String url = "jdbc:sqlite:" + dbPath;
        try (Connection con = DriverManager.getConnection(url);
             Statement stmt = con.createStatement()) {

            InputStream in = DBSeedMain.class.getClassLoader().getResourceAsStream("db/init.sql");
            if (in == null) {
                System.out.println("No init.sql found in resources/db/");
                return;
            }
            StringBuilder sb = new StringBuilder();
            try (BufferedReader r = new BufferedReader(new InputStreamReader(in))) {
                String line;
                while ((line = r.readLine()) != null) {
                    sb.append(line).append('\n');
                }
            }
            String[] parts = sb.toString().split(";\n");
            for (String p : parts) {
                String s = p.trim();
                if (s.isEmpty()) continue;
                stmt.executeUpdate(s);
            }
            System.out.println("DB seeding complete: " + dbPath);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
