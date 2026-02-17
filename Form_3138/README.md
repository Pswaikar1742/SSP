# Student Login and Registration Application

## Overview
A complete web application for student login and registration using Java Servlets and MariaDB database.

**Form Reference:** See the provided form specification at /home/psw/Projects/SSP/SSP4.pdf for field layout and validation details; this will be used to adapt `register.html` and `login.html`.

## Features
- **Login Form**: Secure login with username and password
- **Registration Form**: Student registration with password confirmation
- **Session Management**: User session tracking
- **Database Storage**: All user data stored in MariaDB
- **Responsive Design**: Modern UI with gradient styling
- **Error Handling**: Comprehensive validation and error messages

## Project Structure
```
Form_3138/
├── login.html              # Login form page
├── register.html           # Registration form page
├── welcome.jsp             # Welcome page after login
├── web.xml                 # Deployment descriptor
├── DatabaseConnection.java # Database connection utility
├── LoginServlet.java       # Login servlet
├── RegisterServlet.java    # Registration servlet
├── LogoutServlet.java      # Logout servlet
├── setup.sh               # Setup and compilation script
├── init_database.sh       # Database initialization script
└── README.md              # This file
```

## Database Schema
```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## Installation and Setup

### 1. Database Setup
```bash
# Initialize database and create tables
bash init_database.sh
```

### 2. Build and Deploy
```bash
# Compile and deploy to Tomcat
bash setup.sh
```

### 3. Start Tomcat
```bash
/home/psw/Projects/SSP/apache-tomcat-10.1.50/bin/startup.sh
```

### 4. Access Application
Open your browser and navigate to:
```
http://localhost:8080/StudentForm/login.html
```

## Database Connection Details
- **Host**: localhost
- **Database**: student_db
- **Username**: root
- **Password**: (empty)
- **JDBC Driver**: org.mariadb.jdbc.Driver
- **Driver JAR**: mariadb-java-client-3.5.6.jar

## Test Credentials (after database initialization)
- **Username**: student1
- **Password**: password123

OR

- **Username**: student2
- **Password**: pass456

## Usage

### Registration
1. Click "Register Here" link on login page
2. Enter username (must be unique)
3. Enter password and confirm password
4. Fill in address field
5. Click "Register" button
6. Upon success, redirect to login page

### Login
1. Enter registered username
2. Enter password
3. Click "Submit" button
4. Upon successful login, user is redirected to welcome page
5. Session is created and user can view their profile

### Logout
1. Click "Logout" button on welcome page
2. Session is invalidated
3. Redirected to login page

## Files Description

### login.html
- User login interface
- Username and password input fields
- Link to registration page
- Error message display for failed login attempts

### register.html
- Student registration interface
- Form fields: Username, Password, Confirm Password, Address
- Validation for password matching
- Username uniqueness check
- Success and error messages

### LoginServlet.java
- Handles POST requests from login form
- Validates credentials against database
- Creates user session upon successful login
- Redirects to welcome page or back to login on error

### RegisterServlet.java
- Handles POST requests from registration form
- Validates all form fields
- Checks for duplicate usernames
- Inserts new user record into database
- Returns success or error messages

### LogoutServlet.java
- Invalidates user session
- Redirects user to login page

### DatabaseConnection.java
- Provides database connectivity
- Manages MariaDB JDBC connections
- Initializes database tables on first run
- Connection pooling and error handling

### web.xml
- Web application deployment descriptor
- Servlet mapping and configuration
- Session tracking settings
- Welcome file list

## Configuration

To change database connection details, edit `DatabaseConnection.java`:

```java
private static final String URL = "jdbc:mariadb://localhost:3306/student_db";
private static final String USER = "root";
private static final String PASSWORD = "";
```

To change the web application name, edit the deployed directory name in Tomcat.

## Security Notes
- Passwords are stored in plain text (for educational purposes)
- For production, implement password hashing (BCrypt, PBKDF2, etc.)
- Add HTTPS/SSL support
- Implement CSRF protection
- Add input validation and SQL injection prevention
- Use prepared statements (already implemented)

## Troubleshooting

### Database Connection Error
- Ensure MariaDB is running
- Check database credentials in DatabaseConnection.java
- Verify mariadb-java-client JAR is in WEB-INF/lib/

### Compilation Error
- Ensure Java is installed and in PATH
- Verify Tomcat lib directory exists
- Check MariaDB JDBC driver JAR path

### 404 Error
- Ensure application is deployed to correct directory
- Verify web.xml is in WEB-INF folder
- Check Tomcat is running

## Future Enhancements
- Password encryption and hashing
- Email verification during registration
- Forgot password functionality
- User profile edit functionality
- Admin panel for user management
- Role-based access control (RBAC)
- Two-factor authentication
- Password strength meter
- User activity logging

## Author
Created for Student Information Portal

## License
Educational Purpose Only
