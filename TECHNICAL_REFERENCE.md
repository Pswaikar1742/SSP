# Technical Reference - Servlet Implementation Details

## Code Overview

### 1. LifeCycleServlet - Detailed Explanation

```java
public class LifeCycleServlet extends HttpServlet {
    private int initCount = 0;
    private int serviceCount = 0;
    
    @Override
    public void init() throws ServletException {
        // CALLED ONCE when servlet is first loaded
        initCount++;
        System.out.println("Servlet initialized. Count: " + initCount);
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // CALLED for EVERY request
        serviceCount++;
        // Generate HTML response...
    }
    
    @Override
    public void destroy() {
        // CALLED ONCE when servlet is unloaded
        System.out.println("Servlet destroyed. Final count: " + serviceCount);
    }
}
```

**Key Points:**
- `init()`: One-time initialization (database connections, resources)
- `service()`: Handles each HTTP request
- `destroy()`: Cleanup when servlet unloads
- Counters demonstrate lifecycle behavior

---

### 2. RequestHeaderServlet - Header Processing

```java
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Get all header names
        Enumeration<String> headerNames = request.getHeaderNames();
        
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            // Display header...
        }
    }
}
```

**Key Techniques:**
- `request.getHeaderNames()` - Returns Enumeration of all header names
- `request.getHeader(name)` - Gets value for specific header
- `request.getRemoteAddr()` - Gets client IP
- `request.getMethod()` - Gets HTTP method
- `request.getProtocol()` - Gets protocol version

**Common Headers:**
| Header | Value | Example |
|--------|-------|---------|
| User-Agent | Browser info | Mozilla/5.0... |
| Accept | File types | text/html, image/* |
| Accept-Language | Preferred language | en-US, es |
| Cookie | Session data | sessionid=abc123 |
| Referer | Previous page | http://example.com |

---

### 3. StudentFormServlet - Form Processing

```java
public class StudentFormServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Single value parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        
        // Multiple value parameters (from checkboxes)
        String[] hobbies = request.getParameterValues("hobbies");
        
        // Combine and display...
    }
}
```

**Key Techniques:**
- `request.getParameter(name)` - Gets single form field value
- `request.getParameterValues(name)` - Gets multiple values (checkboxes)
- `request.getContentType()` - Gets form encoding
- Process and validate data
- Generate dynamic HTML response

**Form Input Types Handled:**
```html
<!-- Single values -->
<input type="text" name="name">        → request.getParameter("name")
<input type="email" name="email">      → request.getParameter("email")
<select name="course">...</select>     → request.getParameter("course")
<input type="radio" name="gender">     → request.getParameter("gender")

<!-- Multiple values -->
<input type="checkbox" name="hobbies"> → request.getParameterValues("hobbies")
```

---

## web.xml Configuration

```xml
<!-- Servlet Declaration -->
<servlet>
    <servlet-name>LifeCycleServlet</servlet-name>
    <servlet-class>LifeCycleServlet</servlet-class>
    <load-on-startup>1</load-on-startup>  <!-- Load on server startup -->
</servlet>

<!-- URL Mapping -->
<servlet-mapping>
    <servlet-name>LifeCycleServlet</servlet-name>
    <url-pattern>/LifeCycleServlet</url-pattern>
</servlet-mapping>

<!-- Session Configuration -->
<session-config>
    <session-timeout>30</session-timeout>  <!-- 30 minutes -->
</session-config>
```

**Key Configuration:**
- `<servlet>` - Declares servlet class
- `<servlet-mapping>` - Maps URL to servlet
- `<load-on-startup>` - Loads servlet on server startup (triggers init())
- `<session-timeout>` - Session expiry time in minutes

---

## HTML/CSS Key Features

### Form Styling
```css
/* Input fields */
input[type="text"]:focus,
select:focus,
textarea:focus {
    outline: none;
    border-color: #3498db;  /* Blue border on focus */
}

/* Gradient backgrounds */
background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);

/* Responsive grid */
display: grid;
grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
```

### Validation Indicators
```html
<label>Full Name <span class="required">*</span></label>
<input type="text" required placeholder="...">
```

---

## HTTP Request/Response Cycle

### 1. Life Cycle Servlet Request/Response

**Request:**
```
GET /ServletProject/LifeCycleServlet HTTP/1.1
Host: localhost:8080
User-Agent: Mozilla/5.0...
Connection: keep-alive
```

**Processing:**
1. Container calls `service()` method
2. Generates HTML with current counts
3. Logs to console

**Response:**
```
HTTP/1.1 200 OK
Content-Type: text/html
Content-Length: 1234

<!DOCTYPE html>
<html>
  <body>
    Service Count: 5
    ...
  </body>
</html>
```

---

### 2. Request Header Servlet Request/Response

**Request:**
```
GET /ServletProject/RequestHeaderServlet HTTP/1.1
Host: localhost:8080
User-Agent: Mozilla/5.0...
Accept: text/html,application/xhtml+xml...
Accept-Language: en-US,en;q=0.9
Accept-Encoding: gzip, deflate
Cookie: JSESSIONID=abc123...
```

**Processing:**
1. Gets all header names using `getHeaderNames()`
2. Iterates through each header
3. Retrieves header values using `getHeader()`
4. Formats into HTML table

**Response:**
```
HTTP/1.1 200 OK
Content-Type: text/html

<!DOCTYPE html>
<html>
  <table>
    <tr>
      <td>User-Agent</td>
      <td>Mozilla/5.0...</td>
    </tr>
    ...
  </table>
</html>
```

---

### 3. Student Form Request/Response

**Request:**
```
POST /ServletProject/StudentFormServlet HTTP/1.1
Host: localhost:8080
Content-Type: application/x-www-form-urlencoded
Content-Length: 256

rollno=12345&name=John%20Doe&email=john@example.com&
course=Computer%20Science&gender=Male&hobbies=Reading&hobbies=Coding&...
```

**Processing:**
1. Extract parameters: `getParameter("name")`
2. Extract multiple values: `getParameterValues("hobbies")`
3. Validate all required fields
4. Process and store data
5. Generate confirmation response

**Response:**
```
HTTP/1.1 200 OK
Content-Type: text/html

<!DOCTYPE html>
<html>
  <body>
    <h1>Student Registration Successful</h1>
    <table>
      <tr><td>Roll Number</td><td>12345</td></tr>
      <tr><td>Name</td><td>John Doe</td></tr>
      ...
    </table>
  </body>
</html>
```

---

## Servlet Container Lifecycle

```
                    SERVER STARTUP
                         ↓
    ┌───────────────────────────────────────┐
    │  1. Load servlet classes              │
    │  2. Call init() for load-on-startup   │
    │  3. Servlet ready to handle requests  │
    └───────────────────────────────────────┘
                         ↓
    ┌───────────────────────────────────────┐
    │  For each client request:             │
    │  1. Create HttpServletRequest obj     │
    │  2. Create HttpServletResponse obj    │
    │  3. Call service() method             │
    │  4. service() calls doGet() or        │
    │     doPost() as appropriate           │
    │  5. Send response to client           │
    └───────────────────────────────────────┘
                         ↓
                SERVER SHUTDOWN
                         ↓
    ┌───────────────────────────────────────┐
    │  1. Stop accepting new requests       │
    │  2. Wait for current requests to end  │
    │  3. Call destroy() method             │
    │  4. Unload servlet classes            │
    └───────────────────────────────────────┘
```

---

## Common Servlet Methods

### HttpServlet Methods

| Method | Called When | Override When |
|--------|-------------|----------------|
| `init()` | Servlet first loaded | Need one-time setup |
| `doGet()` | HTTP GET request | Need to handle GET requests |
| `doPost()` | HTTP POST request | Need to handle form submissions |
| `doPut()` | HTTP PUT request | Need to handle PUT requests |
| `doDelete()` | HTTP DELETE request | Need to handle DELETE requests |
| `service()` | Any HTTP request | Need to handle all requests |
| `destroy()` | Server shutdown/unload | Need cleanup operations |

### HttpServletRequest Methods

| Method | Returns | Purpose |
|--------|---------|---------|
| `getParameter(name)` | String | Get single form parameter |
| `getParameterValues(name)` | String[] | Get multiple parameter values |
| `getParameterNames()` | Enumeration | Get all parameter names |
| `getMethod()` | String | Get HTTP method (GET, POST, etc.) |
| `getHeader(name)` | String | Get HTTP header value |
| `getHeaderNames()` | Enumeration | Get all header names |
| `getRemoteAddr()` | String | Get client IP address |
| `getRemoteHost()` | String | Get client hostname |
| `getServerName()` | String | Get server hostname |
| `getServerPort()` | int | Get server port |
| `getContextPath()` | String | Get context path |
| `getRequestURI()` | String | Get request URI |
| `getProtocol()` | String | Get HTTP protocol |

### HttpServletResponse Methods

| Method | Purpose |
|--------|---------|
| `setContentType(type)` | Set response content type |
| `getWriter()` | Get PrintWriter for text output |
| `getOutputStream()` | Get OutputStream for binary output |
| `sendRedirect(url)` | Redirect to another URL |
| `setStatus(code)` | Set HTTP status code |
| `addHeader(name, value)` | Add response header |

---

## Deployment Checklist

- [x] All Java files compile without errors
- [x] web.xml configured correctly
- [x] All servlet mappings defined
- [x] HTML files properly formatted
- [x] CSS styling included
- [x] Form validation included
- [x] Error handling implemented
- [x] Console logging implemented
- [x] README/documentation provided
- [x] Directory structure correct

---

## Performance Considerations

1. **init() vs service()**
   - Heavy operations (DB connection pooling) in `init()`
   - Lightweight operations in `service()` (called frequently)

2. **Memory Management**
   - `init()` initializes once - good for pooling
   - `service()` processes each request - should be efficient
   - `destroy()` releases resources - prevents memory leaks

3. **Thread Safety**
   - `service()` is called by multiple threads concurrently
   - Use instance variables carefully
   - Better to use local variables in method

---

## Common Issues & Solutions

| Issue | Cause | Solution |
|-------|-------|----------|
| "404 Not Found" | Wrong URL or servlet not mapped | Check web.xml mappings |
| "500 Internal Error" | Servlet error | Check server logs |
| Form data not received | Wrong HTTP method or form action | Use POST method, check action URL |
| Headers not displaying | Client didn't send headers | Normal - some headers are optional |
| Init not called | Servlet not in load-on-startup | Access servlet first time triggers init |

---

## Testing with curl

```bash
# Test LifeCycleServlet
curl http://localhost:8080/ServletProject/LifeCycleServlet

# Test RequestHeaderServlet
curl -v http://localhost:8080/ServletProject/RequestHeaderServlet

# Test StudentFormServlet (POST)
curl -X POST http://localhost:8080/ServletProject/StudentFormServlet \
  -d "rollno=101&name=John&email=john@example.com&phone=1234567890&course=CS&gender=Male"
```

---

## Further Learning Topics

1. **Sessions & Cookies**
   - HttpSession for persistent data
   - Cookie for client-side storage

2. **Filters & Interceptors**
   - Request/response filtering
   - Cross-cutting concerns

3. **Error Handling**
   - Custom error pages
   - Exception handling

4. **Security**
   - User authentication
   - Authorization & permissions
   - CSRF protection

5. **Performance Optimization**
   - Caching strategies
   - Connection pooling
   - Response compression
