-- SQLite initialization script for GreenScale Lite
PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS users (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  username TEXT NOT NULL UNIQUE,
  password TEXT NOT NULL,
  role TEXT NOT NULL DEFAULT 'user'
);

CREATE TABLE IF NOT EXISTS nodes (
  node_id INTEGER PRIMARY KEY AUTOINCREMENT,
  hostname TEXT NOT NULL UNIQUE,
  specs TEXT,
  status TEXT NOT NULL DEFAULT 'Available',
  allocated_to INTEGER,
  FOREIGN KEY(allocated_to) REFERENCES users(id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Insert admin user (bcrypt hash for 'adminpass')
INSERT INTO users (username, password, role) VALUES
  ('admin', '$2a$12$inR3X4w1X9DpPasANvNQ4.z9PnJpAGeO6QUDzT5rkQCec8X.4eSC2', 'admin');

-- Insert sample nodes
INSERT INTO nodes (hostname, specs, status, allocated_to) VALUES
  ('nobara-node-01', 'CPU: 8 cores; RAM: 32GB; Storage: 1TB NVMe', 'Available', NULL);
INSERT INTO nodes (hostname, specs, status, allocated_to) VALUES
  ('nobara-node-02', 'CPU: 16 cores; RAM: 64GB; Storage: 2TB NVMe', 'Available', NULL);
INSERT INTO nodes (hostname, specs, status, allocated_to) VALUES
  ('nobara-node-03', 'CPU: 24 cores; RAM: 128GB; Storage: 4TB NVMe', 'Available', NULL);
