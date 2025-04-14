# student-webapp
A simple Java-based CRUD web application using Servlets, JSP, MySQL, Docker, and Tomcat.

⚙️ Technologies Used
- Java Servlets
- HTML/CSS
- MySQL
- Apache Tomcat
- Docker
- Maven

🚀 Features
1. Add, View, Edit, Delete Students
2. Frontend styled with basic CSS
3. Uses Docker containers for MySQL and Tomcat
4. Persists data in a MySQL database

🐳 Docker Setup
1. Build the WAR File
mvn clean package
> This will generate `target/StudentApp.war`.

2. Build Docker Image
docker build -t student-webapp .

3. Run MySQL Container
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=studentdb -p 3307:3306 -d mysql:8

4. Run WebApp Container (after WAR file is ready)
docker run --name student_webapp -p 8080:8080 --link mysql-container:mysql -d student-webapp

🧪 Database Table Setup
Login to MySQL container:
docker exec -it mysql-container mysql -u root -p
Then run:
USE studentdb;

CREATE TABLE students (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  age INT,
  email VARCHAR(100)
);

🌐 Access the WebApp
Open browser:
http://localhost:8080/StudentApp

⚒️ Git Commands (initial setup)
git init
git remote add origin https://github.com/yourusername/student-webapp.git
git add .
git commit -m "Initial commit"
git push -u origin main

If error occurs on push, use:
git pull origin main --rebase
git push origin main
