#!/bin/bash

# Database initialization script
# Creates the student_db database and users table with sample data

echo "=========================================="
echo "Database Initialization Script"
echo "=========================================="
echo ""

# Database credentials
DB_HOST="localhost"
DB_USER="root"
DB_PASSWORD=""
DB_NAME="student_db"

echo "Creating database and table..."

# Create database and table
mariadb -h $DB_HOST -u $DB_USER <<EOF
CREATE DATABASE IF NOT EXISTS $DB_NAME;

USE $DB_NAME;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Optional: Insert sample test data
INSERT INTO users (username, password, address) VALUES 
    ('student1', 'password123', '123 Main Street, City, Country'),
    ('student2', 'pass456', '456 Oak Avenue, Town, Country');

SHOW TABLES;
SELECT COUNT(*) as user_count FROM users;

EOF

if [ $? -eq 0 ]; then
    echo ""
    echo "=========================================="
    echo "✓ Database initialized successfully!"
    echo "=========================================="
    echo ""
    echo "Database Details:"
    echo "  Name: $DB_NAME"
    echo "  Host: $DB_HOST"
    echo "  User: $DB_USER"
    echo ""
    echo "Test Credentials:"
    echo "  Username: student1"
    echo "  Password: password123"
    echo ""
else
    echo "✗ Database initialization failed!"
    exit 1
fi
