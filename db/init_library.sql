-- init_library.sql
-- Creates a basic Library table `books` and inserts a couple of sample rows

PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS books (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    author TEXT NOT NULL,
    year INTEGER,
    isbn TEXT UNIQUE,
    available INTEGER DEFAULT 1
);

-- sample data
INSERT INTO books(title,author,year,isbn,available) VALUES('The Hobbit','J.R.R. Tolkien',1937,'978-0547928227',1);
INSERT INTO books(title,author,year,isbn,available) VALUES('1984','George Orwell',1949,'978-0451524935',1);
