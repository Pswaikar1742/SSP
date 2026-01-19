FROM tomcat:10-jdk21

# Copy the servlet project to Tomcat webapps
COPY ServletProject /usr/local/tomcat/webapps/ServletProject

# Expose port 8080
EXPOSE 8080

# The CMD is inherited from tomcat image (runs catalina.sh run)
