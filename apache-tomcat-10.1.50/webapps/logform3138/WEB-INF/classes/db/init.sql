-- init.sql: create users table and seed initial rows
BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS users (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  username TEXT UNIQUE NOT NULL,
  password TEXT NOT NULL,
  address TEXT,
  dob TEXT,
  contact TEXT,
  city TEXT,
  gender TEXT
);

INSERT OR IGNORE INTO users(username,password,address,dob,contact,city,gender) VALUES('testuser','pass123','123 Test St','1990-01-01','1234567890','TestCity','Male');
INSERT OR IGNORE INTO users(username,password,address,dob,contact,city,gender) VALUES('alice','alicepwd','1 Alice Rd','1992-05-10','1112223333','Wonderland','Female');
COMMIT;
