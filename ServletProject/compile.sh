#!/bin/bash

# Servlet Compilation and Deployment Script
# This script compiles all servlet files and prepares them for deployment

echo "========================================"
echo "   Servlet Compilation Script"
echo "========================================"
echo ""

# Color codes for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Check if CATALINA_HOME is set
if [ -z "$CATALINA_HOME" ]; then
    echo -e "${YELLOW}Warning: CATALINA_HOME not set${NC}"
    echo "Please set CATALINA_HOME to your Tomcat installation directory"
    echo "Example: export CATALINA_HOME=/usr/local/tomcat"
    echo ""
    echo "Or provide the path manually:"
    read -p "Enter Tomcat installation path: " TOMCAT_PATH
    CATALINA_HOME=$TOMCAT_PATH
fi

# Servlet API JAR location
SERVLET_API="$CATALINA_HOME/lib/servlet-api.jar"

# Check if servlet-api.jar exists
if [ ! -f "$SERVLET_API" ]; then
    echo -e "${RED}Error: servlet-api.jar not found at $SERVLET_API${NC}"
    echo "Please verify your Tomcat installation"
    exit 1
fi

echo -e "${GREEN}Using Servlet API: $SERVLET_API${NC}"
echo ""

# Create classes directory if it doesn't exist
mkdir -p WEB-INF/classes

# Compile all Java files
echo "Compiling servlet files..."
echo ""

javac -cp "$SERVLET_API" -d WEB-INF/classes src/*.java

# Check compilation status
if [ $? -eq 0 ]; then
    echo ""
    echo -e "${GREEN}✓ Compilation successful!${NC}"
    echo ""
    echo "Compiled classes are in: WEB-INF/classes/"
    ls -lh WEB-INF/classes/
    echo ""
    echo "========================================"
    echo "   Deployment Options"
    echo "========================================"
    echo ""
    echo "Option 1: Manual Deployment"
    echo "  Copy this ServletProject folder to: $CATALINA_HOME/webapps/"
    echo ""
    echo "Option 2: Create WAR file"
    echo "  Run: jar -cvf ServletProject.war *"
    echo "  Then copy ServletProject.war to: $CATALINA_HOME/webapps/"
    echo ""
    echo "After deployment, access:"
    echo "  http://localhost:8080/ServletProject/"
    echo ""
else
    echo ""
    echo -e "${RED}✗ Compilation failed!${NC}"
    echo "Please check the error messages above"
    exit 1
fi
