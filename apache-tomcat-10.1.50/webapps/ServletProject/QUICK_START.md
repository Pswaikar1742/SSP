# Quick Start Guide - Servlet Assignment

## What You Need to Know About Servlets

### What is a Servlet?
A Servlet is a Java class that runs on a web server and handles HTTP requests/responses dynamically.

### Servlet Life Cycle (3 Phases):
```
1. init()    → Called ONCE when servlet loads (initialization)
2. service() → Called for EVERY request (request processing)
3. destroy() → Called ONCE when servlet unloads (cleanup)
```

---

## Quick Compilation & Deployment

### For Linux/Mac:
```bash
# 1. Set Tomcat path (replace with your path)
export CATALINA_HOME=/usr/local/tomcat

# 2. Compile servlets
./compile.sh

# 3. Deploy
cp -r ServletProject $CATALINA_HOME/webapps/

# 4. Start Tomcat
$CATALINA_HOME/bin/catalina.sh start

# 5. Access
# http://localhost:8080/ServletProject/
```

### For Windows:
```cmd
# 1. Set Tomcat path (replace with your path)
set CATALINA_HOME=C:\apache-tomcat-9.0

# 2. Compile servlets
compile.bat

# 3. Deploy
xcopy ServletProject %CATALINA_HOME%\webapps\ServletProject\ /E /I

# 4. Start Tomcat
%CATALINA_HOME%\bin\startup.bat

# 5. Access
# http://localhost:8080/ServletProject/
```

---

## Three Servlets Explained

### 1. LifeCycleServlet
**What it does:** Shows when init(), service(), and destroy() are called

**How to test:**
1. Visit: `http://localhost:8080/ServletProject/LifeCycleServlet`
2. Check console for "INIT METHOD CALLED" (first visit only)
3. Refresh page → service count increases
4. Stop Tomcat → check for "DESTROY METHOD CALLED"

**Key Points:**
- init() called only once when servlet first loads
- service() called for every request
- destroy() called when server shuts down

---

### 2. RequestHeaderServlet
**What it does:** Displays all HTTP headers sent by your browser

**How to test:**
1. Visit: `http://localhost:8080/ServletProject/RequestHeaderServlet`
2. See headers like User-Agent, Accept, Host, etc.
3. Try from different browsers → see different headers

**Key Points:**
- Access headers using `request.getHeader("HeaderName")`
- `request.getHeaderNames()` gets all header names
- Headers contain client/browser information

---

### 3. StudentFormServlet
**What it does:** Processes student registration form data

**How to test:**
1. Visit: `http://localhost:8080/ServletProject/student-form.html`
2. Fill in student details
3. Submit → see confirmation page
4. Check Tomcat console for logged data

**Key Points:**
- Form uses POST method
- `request.getParameter("fieldname")` gets form data
- Radio buttons → single value
- Checkboxes → `request.getParameterValues()` for multiple values

---

## URL Mappings

| Servlet | URL | Purpose |
|---------|-----|---------|
| Home | `/ServletProject/` | Landing page |
| LifeCycleServlet | `/ServletProject/LifeCycleServlet` | Life cycle demo |
| RequestHeaderServlet | `/ServletProject/RequestHeaderServlet` | Header info |
| Student Form | `/ServletProject/student-form.html` | Form page |
| StudentFormServlet | `/ServletProject/StudentFormServlet` | Form processor |

---

## Important Code Snippets

### Basic Servlet Structure:
```java
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class MyServlet extends HttpServlet {
    
    // Initialization
    public void init() throws ServletException {
        // Called once when servlet loads
    }
    
    // Handle GET requests
    protected void doGet(HttpServletRequest request, 
                        HttpServletResponse response) 
                        throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello World</h1>");
    }
    
    // Handle POST requests
    protected void doPost(HttpServletRequest request, 
                         HttpServletResponse response) 
                         throws ServletException, IOException {
        // Process form data
        String name = request.getParameter("name");
    }
    
    // Cleanup
    public void destroy() {
        // Called once when servlet unloads
    }
}
```

### Get Form Data:
```java
// Single value
String name = request.getParameter("name");

// Multiple values (checkboxes)
String[] hobbies = request.getParameterValues("hobbies");

// All parameter names
Enumeration<String> params = request.getParameterNames();
```

### Get Request Headers:
```java
// Single header
String userAgent = request.getHeader("User-Agent");

// All headers
Enumeration<String> headers = request.getHeaderNames();
while (headers.hasMoreElements()) {
    String headerName = headers.nextElement();
    String headerValue = request.getHeader(headerName);
}
```

---

## Troubleshooting

| Problem | Solution |
|---------|----------|
| 404 Error | Check URL mapping in web.xml |
| 500 Error | Check Tomcat logs: `logs/catalina.out` |
| Compilation fails | Ensure servlet-api.jar path is correct |
| Changes not reflected | Restart Tomcat or redeploy |
| Form not submitting | Check form action URL matches servlet mapping |

---

## Testing Checklist

- [ ] LifeCycleServlet loads and shows init count = 1
- [ ] Refreshing LifeCycleServlet increases service count
- [ ] RequestHeaderServlet shows all browser headers
- [ ] Student form displays properly
- [ ] Form submission shows confirmation page
- [ ] All submitted data displays correctly
- [ ] Console shows "Student Registration" log

---

## Key Files

```
ServletProject/
├── src/
│   ├── LifeCycleServlet.java       # Life cycle demo
│   ├── RequestHeaderServlet.java   # Header display
│   └── StudentFormServlet.java     # Form processing
├── WEB-INF/
│   └── web.xml                      # Servlet configuration
├── index.html                       # Home page
├── student-form.html               # Registration form
└── compile.sh / compile.bat        # Build scripts
```

---

## Important Notes

1. **Always compile to WEB-INF/classes/** - Tomcat looks for classes here
2. **Restart Tomcat after changes** - To reload servlets
3. **Check console output** - For init() and destroy() messages
4. **Use servlet-api.jar for compilation** - Not runtime (Tomcat provides it)
5. **URL patterns in web.xml must match** - Form action and servlet mapping

---

## Expected Output

### LifeCycleServlet Console Output:
```
=== INIT METHOD CALLED ===
Servlet is being initialized - Count: 1
This method is called only ONCE during servlet lifecycle

=== SERVICE METHOD CALLED ===
Processing request - Service Count: 1

=== SERVICE METHOD CALLED ===
Processing request - Service Count: 2
```

### StudentFormServlet Console Output:
```
=== Student Registration ===
Roll No: 101
Name: John Doe
Email: john@example.com
Course: Computer Science
========================
```

---

Good luck with your assignment! 🚀
