FROM tomcat:9.0
COPY target/StudentApp.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
