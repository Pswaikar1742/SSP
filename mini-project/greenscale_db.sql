-- Drop and recreate database
DROP DATABASE IF EXISTS greenscale_db;
CREATE DATABASE IF NOT EXISTS greenscale_db
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_unicode_ci;
USE greenscale_db;

-- users table
CREATE TABLE IF NOT EXISTS users (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(50) NOT NULL DEFAULT 'user',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- nodes table
CREATE TABLE IF NOT EXISTS nodes (
  node_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  hostname VARCHAR(100) NOT NULL UNIQUE,
  specs TEXT,
  status VARCHAR(50) NOT NULL DEFAULT 'Available',
  allocated_to INT UNSIGNED DEFAULT NULL,
  PRIMARY KEY (node_id),
  CONSTRAINT fk_nodes_allocated_to FOREIGN KEY (allocated_to)
    REFERENCES users(id) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Sample data (admin password stored as bcrypt hash; plaintext for convenience: adminpass)
INSERT INTO users (username, password, role) VALUES
  ('admin', '$2a$12$inR3X4w1X9DpPasANvNQ4.z9PnJpAGeO6QUDzT5rkQCec8X.4eSC2', 'admin');

INSERT INTO nodes (hostname, specs, allocated_to) VALUES
  ('nobara-node-01', 'CPU: 8 cores; RAM: 32GB; Storage: 1TB NVMe', NULL),
  ('nobara-node-02', 'CPU: 16 cores; RAM: 64GB; Storage: 2TB NVMe', NULL),
  ('nobara-node-03', 'CPU: 24 cores; RAM: 128GB; Storage: 4TB NVMe', NULL);
