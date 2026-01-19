# Run Servlet Application Locally - Step by Step

## Option A: Download Tomcat from Official Website (Recommended)

### Step 1: Download Tomcat
1. Visit: https://tomcat.apache.org/download-10.cgi
2. Download: **apache-tomcat-10.1.24.tar.gz** (Core)
3. Save to: `/tmp/` or your Downloads folder

### Step 2: Extract Tomcat
```bash
cd /tmp
tar -xzf apache-tomcat-10.1.24.tar.gz
```

### Step 3: Deploy Your Application
```bash
# Copy your servlet project to Tomcat
cp -r /home/psw/Projects/SSP/ServletProject /tmp/apache-tomcat-10.1.24/webapps/
```

### Step 4: Start Tomcat
```bash
# Make scripts executable
chmod +x /tmp/apache-tomcat-10.1.24/bin/*.sh

# Start Tomcat
/tmp/apache-tomcat-10.1.24/bin/startup.sh
```

You should see:
```
Using CATALINA_BASE:   /tmp/apache-tomcat-10.1.24
Using CATALINA_HOME:   /tmp/apache-tomcat-10.1.24
Using CATALINA_TMPDIR: /tmp/apache-tomcat-10.1.24/temp
Using JRE_HOME:        /usr
Using CLASSPATH:       /tmp/apache-tomcat-10.1.24/bin/bootstrap.jar:...
Tomcat started.
```

### Step 5: Access Your Application

**Open your browser and go to:**
```
http://localhost:8080/ServletProject/
```

You should see the home page with three cards!

---

## Testing Each Servlet

### 1. Servlet Life Cycle
Click "View Demo" or go to:
```
http://localhost:8080/ServletProject/LifeCycleServlet
```
- Shows: init count = 1, service count = 1
- **Refresh the page** → service count increases, init count stays 1
- This demonstrates the lifecycle!

### 2. Request Headers
Click "View Headers" or go to:
```
http://localhost:8080/ServletProject/RequestHeaderServlet
```
- Shows all HTTP headers your browser sent
- Shows client IP, browser info, etc.

### 3. Student Form
Click "Fill Form" or go to:
```
http://localhost:8080/ServletProject/student-form.html
```
- Fill out the form
- Click "Submit Registration"
- See confirmation page with your data

---

## Viewing Server Logs

Open a new terminal to see live server output:
```bash
tail -f /tmp/apache-tomcat-10.1.24/logs/catalina.out
```

Watch for servlet lifecycle messages:
```
=== INIT METHOD CALLED ===
=== SERVICE METHOD CALLED ===
```

---

## Stop Tomcat

When you're done, stop the server:
```bash
/tmp/apache-tomcat-10.1.24/bin/shutdown.sh
```

---

## Troubleshooting

### Port 8080 already in use
```bash
# Find what's using port 8080
lsof -i :8080

# Kill it (if needed)
kill -9 <PID>
```

### Application shows 404 error
1. Check if Tomcat is running: `http://localhost:8080`
2. Verify folder is in: `/tmp/apache-tomcat-10.1.24/webapps/ServletProject`
3. Check logs: `tail -f /tmp/apache-tomcat-10.1.24/logs/catalina.out`

### Permission denied error
```bash
chmod +x /tmp/apache-tomcat-10.1.24/bin/*.sh
```

### Java not found
```bash
# Check Java is installed
java -version

# If not installed, install it
sudo apt install default-jdk
```

---

## Quick Setup Script

Save this as `run.sh` and make it executable:

```bash
#!/bin/bash

TOMCAT_HOME="/tmp/apache-tomcat-10.1.24"
SERVLET_PROJECT="/home/psw/Projects/SSP/ServletProject"

echo "=== Servlet Application Setup ==="
echo ""

# Check if Tomcat exists
if [ ! -d "$TOMCAT_HOME" ]; then
    echo "❌ Tomcat not found at $TOMCAT_HOME"
    echo ""
    echo "Please download Tomcat first:"
    echo "1. Visit: https://tomcat.apache.org/download-10.cgi"
    echo "2. Download: apache-tomcat-10.1.24.tar.gz"
    echo "3. Extract to /tmp"
    echo ""
    exit 1
fi

# Check if project exists
if [ ! -d "$SERVLET_PROJECT" ]; then
    echo "❌ Servlet project not found at $SERVLET_PROJECT"
    exit 1
fi

echo "✅ Tomcat found at: $TOMCAT_HOME"
echo "✅ Project found at: $SERVLET_PROJECT"
echo ""

# Make scripts executable
chmod +x $TOMCAT_HOME/bin/*.sh

# Copy project to webapps if not already there
if [ ! -d "$TOMCAT_HOME/webapps/ServletProject" ]; then
    echo "📋 Copying project to Tomcat..."
    cp -r "$SERVLET_PROJECT" "$TOMCAT_HOME/webapps/"
else
    echo "✅ Project already in Tomcat"
fi

echo ""
echo "🚀 Starting Tomcat..."
echo ""

# Start Tomcat
$TOMCAT_HOME/bin/startup.sh

echo ""
echo "⏳ Waiting for Tomcat to start..."
sleep 3

echo ""
echo "✅ Tomcat is running!"
echo ""
echo "📱 Open your browser:"
echo "   http://localhost:8080/ServletProject/"
echo ""
echo "💡 To stop Tomcat, run:"
echo "   $TOMCAT_HOME/bin/shutdown.sh"
echo ""
echo "📊 To view logs, run in another terminal:"
echo "   tail -f $TOMCAT_HOME/logs/catalina.out"
```

Run it with:
```bash
chmod +x run.sh
./run.sh
```

---

## Alternative: Use Already-Installed Tomcat

If you already have Tomcat installed somewhere, adjust the paths:

```bash
# Find existing Tomcat
find / -name "catalina.sh" 2>/dev/null

# Then copy project to its webapps
cp -r /home/psw/Projects/SSP/ServletProject /path/to/your/tomcat/webapps/
```

---

**You're ready! Download Tomcat and follow the steps above!**
