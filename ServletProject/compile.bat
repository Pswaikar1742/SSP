@echo off
REM Servlet Compilation Script for Windows
REM This script compiles all servlet files and prepares them for deployment

echo ========================================
echo    Servlet Compilation Script
echo ========================================
echo.

REM Check if CATALINA_HOME is set
if "%CATALINA_HOME%"=="" (
    echo Warning: CATALINA_HOME not set
    echo Please set CATALINA_HOME to your Tomcat installation directory
    echo Example: set CATALINA_HOME=C:\apache-tomcat-9.0
    echo.
    set /p CATALINA_HOME="Enter Tomcat installation path: "
)

REM Servlet API JAR location
set SERVLET_API=%CATALINA_HOME%\lib\servlet-api.jar

REM Check if servlet-api.jar exists
if not exist "%SERVLET_API%" (
    echo Error: servlet-api.jar not found at %SERVLET_API%
    echo Please verify your Tomcat installation
    pause
    exit /b 1
)

echo Using Servlet API: %SERVLET_API%
echo.

REM Create classes directory if it doesn't exist
if not exist "WEB-INF\classes" mkdir "WEB-INF\classes"

REM Compile all Java files
echo Compiling servlet files...
echo.

javac -cp "%SERVLET_API%" -d WEB-INF\classes src\*.java

REM Check compilation status
if %ERRORLEVEL% EQU 0 (
    echo.
    echo [SUCCESS] Compilation successful!
    echo.
    echo Compiled classes are in: WEB-INF\classes\
    dir /B WEB-INF\classes\
    echo.
    echo ========================================
    echo    Deployment Options
    echo ========================================
    echo.
    echo Option 1: Manual Deployment
    echo   Copy this ServletProject folder to: %CATALINA_HOME%\webapps\
    echo.
    echo Option 2: Create WAR file
    echo   Run: jar -cvf ServletProject.war *
    echo   Then copy ServletProject.war to: %CATALINA_HOME%\webapps\
    echo.
    echo After deployment, access:
    echo   http://localhost:8080/ServletProject/
    echo.
) else (
    echo.
    echo [FAILED] Compilation failed!
    echo Please check the error messages above
    pause
    exit /b 1
)

pause
