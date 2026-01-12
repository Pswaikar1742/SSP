# Server Side Programming - Servlet Assignment

## 📚 What is a Servlet?

A **Servlet** is a Java programming language class that extends the capabilities of servers that host applications accessed by a request-response model. Servlets are:

- **Server-side Java components** that handle HTTP requests
- Run inside a **servlet container** (like Apache Tomcat)
- Generate **dynamic web content**
- **Platform-independent** and scalable
- Part of **Java EE (Enterprise Edition)** specification

### Servlet Life Cycle:
1. **init()** - Called once when servlet is first loaded (initialization)
2. **service()** - Called for each client request (request processing)
3. **destroy()** - Called once when servlet is unloaded (cleanup)

---

## ✅ Assignment Implementation

### Problem Statement:
Write a program to create a simple servlet for:
1. **Demonstration of Servlet Life Cycle**
2. **Printing request header information**
3. **Form processing (Student Information)**

### Status: ✅ **COMPLETE**

All three servlets have been successfully implemented in the `ServletProject/` directory.

---

## 📂 Project Structure

```
ServletProject/
├── src/
│   ├── LifeCycleServlet.java      # Servlet Life Cycle demonstration
│   ├── RequestHeaderServlet.java  # Request header display
│   └── StudentFormServlet.java    # Student form processing
│
├── WEB-INF/
│   ├── web.xml                     # Deployment descriptor
│   └── classes/                    # Compiled classes (after build)
│
├── index.html                      # Home/landing page
├── student-form.html              # Student registration form
│
├── compile.sh                      # Linux/Mac build script
├── compile.bat                     # Windows build script
│
└── Documentation:
    ├── README.md                   # Complete guide
    ├── QUICK_START.md             # Quick reference
    ├── DIAGRAMS.md                # Visual diagrams
    └── PROJECT_SUMMARY.txt        # Visual summary
```

---

## 🚀 Quick Start

### Prerequisites:
- JDK 8 or higher
- Apache Tomcat 9.x or higher

### Step 1: Set Tomcat Path
```bash
# Linux/Mac
export CATALINA_HOME=/path/to/tomcat

# Windows
set CATALINA_HOME=C:\path\to\tomcat
```

### Step 2: Compile
```bash
cd ServletProject

# Linux/Mac
./compile.sh

# Windows
compile.bat
```

### Step 3: Deploy
```bash
# Copy to Tomcat webapps
cp -r ServletProject $CATALINA_HOME/webapps/
```

### Step 4: Start Tomcat
```bash
# Linux/Mac
$CATALINA_HOME/bin/catalina.sh start

# Windows
%CATALINA_HOME%\bin\startup.bat
```

### Step 5: Access
Open browser: `http://localhost:8080/ServletProject/`

---

## 📋 Implemented Servlets

### 1. Life Cycle Servlet
- **URL:** `/ServletProject/LifeCycleServlet`
- **Purpose:** Demonstrates servlet life cycle methods
- **Features:** Shows init count, service count, and destroy logging

### 2. Request Header Servlet
- **URL:** `/ServletProject/RequestHeaderServlet`
- **Purpose:** Displays HTTP request headers
- **Features:** Shows all headers, common headers, and request properties

### 3. Student Form Servlet
- **Form URL:** `/ServletProject/student-form.html`
- **Servlet URL:** `/ServletProject/StudentFormServlet`
- **Purpose:** Processes student registration
- **Features:** Handles text, email, radio, checkbox, select inputs

---

## 📖 Documentation

- **[SERVLET_ASSIGNMENT_SUMMARY.md](SERVLET_ASSIGNMENT_SUMMARY.md)** - Complete assignment summary
- **[ServletProject/README.md](ServletProject/README.md)** - Detailed documentation
- **[ServletProject/QUICK_START.md](ServletProject/QUICK_START.md)** - Quick reference guide
- **[ServletProject/DIAGRAMS.md](ServletProject/DIAGRAMS.md)** - Visual flow diagrams
- **[ServletProject/PROJECT_SUMMARY.txt](ServletProject/PROJECT_SUMMARY.txt)** - Visual overview

---

## 🎯 Key Concepts Covered

1. **Servlet Life Cycle**
   - Initialization with `init()`
   - Request handling with `service()` / `doGet()` / `doPost()`
   - Cleanup with `destroy()`

2. **HTTP Request Processing**
   - Accessing request headers
   - Reading request parameters
   - Handling different HTTP methods

3. **Form Processing**
   - Processing form data
   - Handling different input types
   - Generating dynamic HTML responses

4. **Web Application Configuration**
   - `web.xml` deployment descriptor
   - Servlet mappings
   - URL patterns

---

## ✨ Features

- ✅ Three fully functional servlets
- ✅ Beautiful, responsive HTML/CSS design
- ✅ Comprehensive documentation
- ✅ Cross-platform build scripts
- ✅ Visual diagrams and explanations
- ✅ Server-side logging
- ✅ Form validation
- ✅ Error handling

---

## 🎓 Learning Outcomes

After completing this assignment, you will understand:
- What servlets are and how they work
- The servlet life cycle
- HTTP request/response handling
- Form data processing
- Web application deployment
- Servlet configuration

---

## 📞 Need Help?

1. Check [QUICK_START.md](ServletProject/QUICK_START.md) for troubleshooting
2. Review [DIAGRAMS.md](ServletProject/DIAGRAMS.md) for visual explanations
3. Read [README.md](ServletProject/README.md) for detailed instructions

---

**Status:** ✅ Complete and Ready for Deployment  
**Created:** January 2026
