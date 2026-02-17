#!/bin/bash

# Setup script for Student Login and Registration Application
# This script compiles the servlets and sets up the web application

PROJECT_DIR="/home/psw/Projects/SSP/Form_3138"
TOMCAT_DIR="/home/psw/Projects/SSP/apache-tomcat-10.1.50"
MARIADB_JAR="/home/psw/Projects/SSP/Form_3138/mariadb-java-client-3.5.6.jar"
APP_NAME="StudentForm"
WEBAPP_DIR="$TOMCAT_DIR/webapps/$APP_NAME"

echo "=========================================="
echo "Student Login and Registration Setup"
echo "=========================================="

# Create webapp directory structure
echo "Creating web application directory structure..."
mkdir -p "$WEBAPP_DIR/WEB-INF/classes"
mkdir -p "$WEBAPP_DIR/WEB-INF/lib"

# Copy MariaDB JAR to lib folder
echo "Copying MariaDB JDBC driver..."
cp "$MARIADB_JAR" "$WEBAPP_DIR/WEB-INF/lib/"

# Copy HTML files
echo "Copying HTML files..."
cp "$PROJECT_DIR/login.html" "$WEBAPP_DIR/"
cp "$PROJECT_DIR/register.html" "$WEBAPP_DIR/"

# Copy web.xml
echo "Copying web.xml..."
cp "$PROJECT_DIR/web.xml" "$WEBAPP_DIR/WEB-INF/"

# Compile Java files
echo "Compiling Java servlets..."
cd "$PROJECT_DIR"

# Find servlet API JAR
SERVLET_JAR=$(find "$TOMCAT_DIR/lib" -name "servlet-api*.jar" -o -name "jakarta*.jar" | head -1)
if [ -z "$SERVLET_JAR" ]; then
    SERVLET_JAR="$TOMCAT_DIR/lib/*"
fi

javac -d "$WEBAPP_DIR/WEB-INF/classes" \
    -cp "$MARIADB_JAR:$SERVLET_JAR:$TOMCAT_DIR/lib/*" \
    "$PROJECT_DIR/DatabaseConnection.java" \
    "$PROJECT_DIR/LoginServlet.java" \
    "$PROJECT_DIR/RegisterServlet.java" \
    "$PROJECT_DIR/LogoutServlet.java" \
    "$PROJECT_DIR/WelcomeServlet.java"

if [ $? -eq 0 ]; then
    echo ""
    echo "=========================================="
    echo "✓ Compilation successful!"
    echo "=========================================="
    echo ""
    echo "Next steps:"
    echo "1. Create database: mariadb -u root -e 'CREATE DATABASE IF NOT EXISTS student_db;'"
    echo "2. Start Tomcat: $TOMCAT_DIR/bin/startup.sh"
    echo "3. Access application: http://localhost:8080/$APP_NAME/login.html"
    echo ""
    echo "Database connection details:"
    echo "  Host: localhost"
    echo "  Database: student_db"
    echo "  User: root"
    echo "  Password: (empty)"
    echo ""
else
    echo "✗ Compilation failed!"
    exit 1
fi
