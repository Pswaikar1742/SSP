package app;

import java.sql.*;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    // Resolve DB path from a set of likely locations
    private static final String DB_URL;

    static {
        String resolved = findDbPath();
        DB_URL = "jdbc:sqlite:" + resolved;
        System.out.println("Using DB: " + resolved);
    }

    private static String findDbPath() {
        String[] candidates = new String[] {
                "db/library.db",       // workspace-relative
                "../db/library.db",    // when running from JDBC_Terminal
                "LogForm3138/app.db",  // webapp db
                "../LogForm3138/app.db",
                "JDBC_Terminal/../db/library.db"
        };
        for (String c : candidates) {
            Path p = Paths.get(c).normalize();
            if (Files.exists(p)) return p.toString();
        }
        // fallback: use workspace-relative default (will create a new DB file)
        return "db/library.db";
    }

    public static void main(String[] args) {
        ensureTable();
        Scanner sc = new Scanner(System.in);
        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1": createBook(sc); break;
                case "2": listBooks(); break;
                case "3": updateBook(sc); break;
                case "4": deleteBook(sc); break;
                case "5": System.out.println("Exiting."); sc.close(); return;
                default: System.out.println("Invalid choice. Try again.");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("=== Library Terminal Menu ===");
        System.out.println("1) Create book");
        System.out.println("2) List books");
        System.out.println("3) Update book details");
        System.out.println("4) Delete book");
        System.out.println("5) Exit");
        System.out.print("Choose [1-5]: ");
    }

    private static void ensureTable() {
        try (Connection con = DriverManager.getConnection(DB_URL);
             Statement st = con.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS books (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "title TEXT NOT NULL, " +
                    "author TEXT NOT NULL, " +
                    "year INTEGER, " +
                    "isbn TEXT UNIQUE, " +
                    "available INTEGER DEFAULT 1)");
        } catch (SQLException e) {
            System.err.println("Failed to create table: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void createBook(Scanner sc) {
        System.out.print("Title: ");
        String title = sc.nextLine().trim();
        System.out.print("Author: ");
        String author = sc.nextLine().trim();
        System.out.print("Year (optional): ");
        String yearStr = sc.nextLine().trim();
        System.out.print("ISBN (optional): ");
        String isbn = sc.nextLine().trim();

        Integer year = null;
        if (!yearStr.isEmpty()) {
            try { year = Integer.parseInt(yearStr); } catch (NumberFormatException ignored) {}
        }

        String sql = "INSERT INTO books(title,author,year,isbn,available) VALUES(?,?,?,?,1)";
        try (Connection con = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, author);
            if (year == null) ps.setNull(3, Types.INTEGER); else ps.setInt(3, year);
            if (isbn.isEmpty()) ps.setNull(4, Types.VARCHAR); else ps.setString(4, isbn);
            ps.executeUpdate();
            System.out.println("Book added.");
        } catch (SQLException e) {
            System.err.println("Error adding book: " + e.getMessage());
        }
    }

    private static void listBooks() {
        String sql = "SELECT id,title,author,year,isbn,available FROM books ORDER BY id";
        try (Connection con = DriverManager.getConnection(DB_URL);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("ID | Title | Author | Year | ISBN | Available");
            while (rs.next()) {
                System.out.printf("%d | %s | %s | %s | %s | %s%n",
                        rs.getInt("id"), rs.getString("title"), rs.getString("author"),
                        rs.getObject("year") == null ? "" : rs.getInt("year"),
                        rs.getString("isbn") == null ? "" : rs.getString("isbn"),
                        rs.getInt("available") == 1 ? "yes" : "no");
            }
        } catch (SQLException e) {
            System.err.println("Error listing books: " + e.getMessage());
        }
    }

    private static void updateBook(Scanner sc) {
        System.out.print("Book ID to update: ");
        String idStr = sc.nextLine().trim();
        int id;
        try { id = Integer.parseInt(idStr); } catch (NumberFormatException ex) { System.out.println("Invalid id"); return; }
        System.out.print("New title (leave blank to keep): ");
        String title = sc.nextLine().trim();
        System.out.print("New author (leave blank to keep): ");
        String author = sc.nextLine().trim();
        System.out.print("New year (leave blank to keep): ");
        String yearStr = sc.nextLine().trim();
        System.out.print("New ISBN (leave blank to keep): ");
        String isbn = sc.nextLine().trim();
        System.out.print("Available? (yes/no, leave blank to keep): ");
        String avail = sc.nextLine().trim().toLowerCase();

        StringBuilder sql = new StringBuilder("UPDATE books SET ");
        boolean first = true;
        if (!title.isEmpty()) { sql.append("title=?"); first=false; }
        if (!author.isEmpty()) { if (!first) sql.append(","); sql.append("author=?"); first=false; }
        if (!yearStr.isEmpty()) { if (!first) sql.append(","); sql.append("year=?"); first=false; }
        if (!isbn.isEmpty()) { if (!first) sql.append(","); sql.append("isbn=?"); first=false; }
        if (!avail.isEmpty()) { if (!first) sql.append(","); sql.append("available=?"); first=false; }
        if (first) { System.out.println("Nothing to update."); return; }
        sql.append(" WHERE id=?");

        try (Connection con = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = con.prepareStatement(sql.toString())) {
            int idx = 1;
            if (!title.isEmpty()) ps.setString(idx++, title);
            if (!author.isEmpty()) ps.setString(idx++, author);
            if (!yearStr.isEmpty()) {
                try { ps.setInt(idx++, Integer.parseInt(yearStr)); } catch (NumberFormatException ex) { ps.setNull(idx++, Types.INTEGER); }
            }
            if (!isbn.isEmpty()) ps.setString(idx++, isbn);
            if (!avail.isEmpty()) ps.setInt(idx++, avail.startsWith("y") ? 1 : 0);
            ps.setInt(idx, id);
            int updated = ps.executeUpdate();
            if (updated > 0) System.out.println("Book updated."); else System.out.println("Book not found.");
        } catch (SQLException e) {
            System.err.println("Error updating book: " + e.getMessage());
        }
    }

    private static void deleteBook(Scanner sc) {
        System.out.print("Book ID to delete: ");
        String idStr = sc.nextLine().trim();
        int id;
        try { id = Integer.parseInt(idStr); } catch (NumberFormatException ex) { System.out.println("Invalid id"); return; }
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection con = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int deleted = ps.executeUpdate();
            if (deleted > 0) System.out.println("Book deleted."); else System.out.println("Book not found.");
        } catch (SQLException e) {
            System.err.println("Error deleting book: " + e.getMessage());
        }
    }
}
