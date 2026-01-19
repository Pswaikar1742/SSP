# Servlet Programming Assignment

## Problem Statement
Write a program to create a simple servlet for:
1. **Demonstration of Servlet Life Cycle**
2. **Printing request header information**
3. **Form processing (Student Information)**

---

## What is a Servlet?

A **Servlet** is a Java programming language class that is used to extend the capabilities of servers that host applications accessed by means of a request-response model. Although servlets can respond to any type of request, they are commonly used to extend the applications hosted by web servers.

### Key Features:
- **Server-side component** written in Java
- Handles **HTTP requests** and generates **dynamic responses**
- Runs on a **servlet container** (Tomcat, Jetty, GlassFish)
- **Platform-independent** and scalable
- Part of **Java EE** specification

### Servlet Life Cycle:
1. **init()** - Called once when servlet is first loaded (initialization)
2. **service()** - Called for each client request (request processing)
3. **destroy()** - Called once when servlet is unloaded (cleanup)

---

## Project Structure

```
ServletProject/
├── src/
│   ├── LifeCycleServlet.java      # Demonstrates servlet life cycle
│   ├── RequestHeaderServlet.java  # Displays HTTP request headers
│   └── StudentFormServlet.java    # Processes student form
├── WEB-INF/
│   ├── web.xml                     # Deployment descriptor
│   └── classes/                    # Compiled classes (after compilation)
├── index.html                      # Home page
└── student-form.html              # Student registration form
```

---

## Implementation Details

### 1. Life Cycle Servlet (`LifeCycleServlet.java`)
**Purpose:** Demonstrates the three phases of servlet life cycle

**Features:**
- Shows `init()` method called once during initialization
- Tracks `service()` method calls for each request
- Implements `destroy()` method for cleanup
- Displays counters to show method invocation

**URL:** `http://localhost:8080/ServletProject/LifeCycleServlet`

---

### 2. Request Header Servlet (`RequestHeaderServlet.java`)
**Purpose:** Displays HTTP request header information

**Features:**
- Lists all HTTP request headers
- Shows common headers (User-Agent, Accept, etc.)
- Displays request method, URI, protocol
- Shows client IP address and host information
- Additional request properties (content type, encoding, etc.)

**URL:** `http://localhost:8080/ServletProject/RequestHeaderServlet`

---

### 3. Student Form Servlet (`StudentFormServlet.java`)
**Purpose:** Processes student registration form

**Features:**
- Handles POST requests from HTML form
- Processes various input types (text, email, radio, checkbox, select)
- Displays confirmation with submitted data
- Beautiful, responsive design
- Server-side logging of student information

**Form Fields:**
- Roll Number, Name, Email, Phone
- Course (dropdown selection)
- Gender (radio buttons)
- Hobbies (checkboxes)
- Address, City, State

**URL:** `http://localhost:8080/ServletProject/student-form.html`

---

## Setup and Deployment

### Prerequisites
- **JDK 8 or higher**
- **Apache Tomcat 9.x or higher**
- **Servlet API JAR** (included in Tomcat)

### Step 1: Compile the Servlets

```bash
# Navigate to the project directory
cd ServletProject

# Compile with servlet-api.jar in classpath
javac -cp "/path/to/tomcat/lib/servlet-api.jar" -d WEB-INF/classes src/*.java
```

**For Windows:**
```cmd
javac -cp "C:\apache-tomcat-9.0\lib\servlet-api.jar" -d WEB-INF\classes src\*.java
```

**For Linux/Mac:**
```bash
javac -cp "/usr/local/tomcat/lib/servlet-api.jar" -d WEB-INF/classes src/*.java
```

### Step 2: Deploy to Tomcat

**Option A: Manual Deployment**
1. Copy the entire `ServletProject` folder to Tomcat's `webapps` directory
2. Start Tomcat:
   - Windows: `catalina.bat start`
   - Linux/Mac: `./catalina.sh start`

**Option B: Create WAR file**
```bash
# From the ServletProject directory
jar -cvf ServletProject.war *

# Copy to Tomcat webapps
cp ServletProject.war /path/to/tomcat/webapps/
```

### Step 3: Access the Application

Open your browser and navigate to:
- **Home Page:** `http://localhost:8080/ServletProject/`
- **Life Cycle:** `http://localhost:8080/ServletProject/LifeCycleServlet`
- **Request Headers:** `http://localhost:8080/ServletProject/RequestHeaderServlet`
- **Student Form:** `http://localhost:8080/ServletProject/student-form.html`

---

## Alternative: Using Servlet Annotations (Servlet 3.0+)

Instead of `web.xml`, you can use annotations:

```java
@WebServlet("/LifeCycleServlet")
public class LifeCycleServlet extends HttpServlet {
    // servlet code
}
```

If using annotations, you can remove servlet mappings from `web.xml`.

---

## Testing the Servlets

### 1. Test Life Cycle Servlet
1. Access `http://localhost:8080/ServletProject/LifeCycleServlet`
2. Check console output for "INIT METHOD CALLED"
3. Refresh the page multiple times
4. Observe service count increasing
5. Stop Tomcat and check for "DESTROY METHOD CALLED"

### 2. Test Request Header Servlet
1. Access `http://localhost:8080/ServletProject/RequestHeaderServlet`
2. View all HTTP headers sent by your browser
3. Try accessing from different browsers to see different headers

### 3. Test Student Form
1. Access `http://localhost:8080/ServletProject/student-form.html`
2. Fill in all required fields
3. Submit the form
4. View the confirmation page with submitted data
5. Check Tomcat console for server-side logging

---

## Troubleshooting

### Common Issues:

**1. ClassNotFoundException**
- Ensure servlets are compiled in `WEB-INF/classes` directory
- Check package names match directory structure

**2. HTTP 404 - Not Found**
- Verify servlet mappings in `web.xml`
- Check URL patterns are correct
- Ensure Tomcat is running

**3. HTTP 500 - Internal Server Error**
- Check Tomcat logs: `logs/catalina.out`
- Verify servlet-api.jar is in Tomcat's lib folder
- Check for compilation errors

**4. Form submission not working**
- Ensure form action URL is correct
- Verify method is POST
- Check servlet mapping matches form action

---

## Learning Outcomes

After completing this assignment, you will understand:

1. **Servlet Life Cycle:**
   - Initialization with `init()`
   - Request handling with `service()` / `doGet()` / `doPost()`
   - Cleanup with `destroy()`

2. **HTTP Request Processing:**
   - Accessing request headers
   - Reading request parameters
   - Handling different HTTP methods

3. **Form Processing:**
   - Processing form data
   - Handling different input types
   - Generating dynamic HTML responses

4. **Web Application Deployment:**
   - Configuring `web.xml`
   - Deploying to servlet container
   - URL mapping and routing

---

## Additional Resources

- [Java Servlet Tutorial](https://docs.oracle.com/javaee/7/tutorial/servlets.htm)
- [Apache Tomcat Documentation](https://tomcat.apache.org/tomcat-9.0-doc/)
- [Servlet API JavaDoc](https://docs.oracle.com/javaee/7/api/javax/servlet/package-summary.html)

---

## Author
Created for Servlet Programming Assignment

## License
Educational use only
