# Quick Start Guide - Running the Servlet Project

## Option 1: Install Apache Tomcat (Recommended)

### Step 1: Download Tomcat
```bash
# Download Tomcat 10 (compatible with Jakarta EE)
cd /tmp
wget https://archive.apache.org/dist/tomcat/tomcat-10/v10.1.24/bin/apache-tomcat-10.1.24.tar.gz

# Extract
tar -xzf apache-tomcat-10.1.24.tar.gz
sudo mv apache-tomcat-10.1.24 /usr/local/tomcat
```

### Step 2: Set permissions
```bash
sudo chmod +x /usr/local/tomcat/bin/*.sh
```

### Step 3: Deploy your application
```bash
# Copy the project to webapps
cp -r /home/psw/Projects/SSP/ServletProject /usr/local/tomcat/webapps/
```

### Step 4: Start Tomcat
```bash
/usr/local/tomcat/bin/startup.sh
```

### Step 5: Access the application
Open your browser and visit:
```
http://localhost:8080/ServletProject/
```

### Stop Tomcat
```bash
/usr/local/tomcat/bin/shutdown.sh
```

---

## Option 2: Using Docker (Fastest)

### Step 1: Create Dockerfile
```bash
cat > /home/psw/Projects/SSP/Dockerfile << 'DOCKERFILE'
FROM tomcat:10-jdk21
COPY ServletProject /usr/local/tomcat/webapps/ServletProject
EXPOSE 8080
DOCKERFILE
```

### Step 2: Build Docker image
```bash
cd /home/psw/Projects/SSP
docker build -t servlet-app .
```

### Step 3: Run Docker container
```bash
docker run -p 8080:8080 servlet-app
```

### Step 4: Access the application
```
http://localhost:8080/ServletProject/
```

---

## Option 3: Manual Java Server Setup (Development Only)

Use an embedded Tomcat or Jetty in Java code (not recommended for this project).

---

## Troubleshooting

### Port 8080 already in use
```bash
# Find process using port 8080
lsof -i :8080
# Kill the process
kill -9 <PID>
```

### Permission denied when running startup.sh
```bash
chmod +x /usr/local/tomcat/bin/*.sh
```

### Application not found (404 error)
- Make sure ServletProject folder is in webapps/
- Make sure Tomcat is running
- Check http://localhost:8080/manager/html (Tomcat manager)

---

## Testing the Application

Once deployed, visit:

1. **Home Page**
   http://localhost:8080/ServletProject/

2. **Servlet Lifecycle Demo**
   http://localhost:8080/ServletProject/LifeCycleServlet
   - Refresh the page to see service count increase

3. **Request Headers**
   http://localhost:8080/ServletProject/RequestHeaderServlet
   - View all HTTP headers sent by your browser

4. **Student Form**
   http://localhost:8080/ServletProject/student-form.html
   - Fill and submit the form
   - See confirmation page with submitted data

---

## View Server Console

To see servlet output and logging:

```bash
# Tail Tomcat logs
tail -f /usr/local/tomcat/logs/catalina.out
```

Check for init() and service() calls logged from LifeCycleServlet!

