#!/bin/bash

TOMCAT_HOME="/tmp/apache-tomcat-10.1.24"
SERVLET_PROJECT="/home/psw/Projects/SSP/ServletProject"

echo "════════════════════════════════════════════════════════"
echo "   Servlet Application - Local Setup & Run"
echo "════════════════════════════════════════════════════════"
echo ""

# Check if Tomcat exists
if [ ! -d "$TOMCAT_HOME" ]; then
    echo "❌ Tomcat not found at $TOMCAT_HOME"
    echo ""
    echo "📥 Steps to install:"
    echo ""
    echo "1. Download Tomcat:"
    echo "   Visit: https://tomcat.apache.org/download-10.cgi"
    echo "   Download: apache-tomcat-10.1.24.tar.gz"
    echo ""
    echo "2. Extract:"
    echo "   cd /tmp"
    echo "   tar -xzf apache-tomcat-10.1.24.tar.gz"
    echo ""
    echo "3. Then run this script again!"
    echo ""
    exit 1
fi

# Check if project exists
if [ ! -d "$SERVLET_PROJECT" ]; then
    echo "❌ Servlet project not found at $SERVLET_PROJECT"
    exit 1
fi

echo "✅ Java version:"
java -version 2>&1 | head -1
echo ""

echo "✅ Tomcat found at: $TOMCAT_HOME"
echo "✅ Project found at: $SERVLET_PROJECT"
echo ""

# Make scripts executable
chmod +x $TOMCAT_HOME/bin/*.sh

# Copy project to webapps if not already there
if [ ! -d "$TOMCAT_HOME/webapps/ServletProject" ]; then
    echo "📋 Deploying project to Tomcat..."
    cp -r "$SERVLET_PROJECT" "$TOMCAT_HOME/webapps/"
    echo "✅ Project deployed"
else
    echo "✅ Project already deployed"
fi

echo ""
echo "🚀 Starting Tomcat Server..."
echo ""

# Start Tomcat
$TOMCAT_HOME/bin/startup.sh

echo ""
echo "⏳ Waiting for Tomcat to start (5 seconds)..."
sleep 5

echo ""
echo "════════════════════════════════════════════════════════"
echo "✅ TOMCAT IS RUNNING!"
echo "════════════════════════════════════════════════════════"
echo ""
echo "📱 Open your browser:"
echo "   http://localhost:8080/ServletProject/"
echo ""
echo "🔗 Direct links:"
echo "   • Home:            http://localhost:8080/ServletProject/"
echo "   • Life Cycle:      http://localhost:8080/ServletProject/LifeCycleServlet"
echo "   • Request Headers: http://localhost:8080/ServletProject/RequestHeaderServlet"
echo "   • Student Form:    http://localhost:8080/ServletProject/student-form.html"
echo ""
echo "📊 To view server logs (in new terminal):"
echo "   tail -f $TOMCAT_HOME/logs/catalina.out"
echo ""
echo "⏹️  To stop Tomcat:"
echo "   $TOMCAT_HOME/bin/shutdown.sh"
echo ""
echo "════════════════════════════════════════════════════════"
