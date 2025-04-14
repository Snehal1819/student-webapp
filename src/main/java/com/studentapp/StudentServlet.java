package com.studentapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Student List</title>");
        out.println("<style>");
        out.println("body { font-family: Arial; margin: 40px; background: #f9f9f9; }");
        out.println("table { border-collapse: collapse; width: 80%; margin-bottom: 20px; }");
        out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println("form { margin-bottom: 30px; }");
        out.println("input[type='text'], input[type='number'], input[type='email'] { padding: 5px; margin-right: 10px; }");
        out.println("input[type='submit'] { padding: 6px 10px; background-color: #4CAF50; color: white; border: none; cursor: pointer; }");
        out.println("</style>");
        out.println("</head><body>");

        out.println("<h2>Register Student</h2>");
        out.println("<form method='post'>");
        out.println("Name: <input type='text' name='name' required>");
        out.println("Age: <input type='number' name='age' required>");
        out.println("Email: <input type='email' name='email' required>");
        out.println("<input type='submit' value='Add Student'>");
        out.println("</form>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql:3306/studentdb", "root", "password");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            out.println("<h2>Student List</h2>");
            out.println("<table><tr><th>ID</th><th>Name</th><th>Age</th><th>Email</th><th>Actions</th></tr>");
            while (rs.next()) {
                int id = rs.getInt("id");
                out.println("<tr><td>" + id + "</td><td>" + rs.getString("name") + "</td><td>" +
                        rs.getInt("age") + "</td><td>" + rs.getString("email") + "</td>");
                out.println("<td><a href='edit?id=" + id + "'>Edit</a> | <a href='delete?id=" + id + "'>Delete</a></td></tr>");
            }
            out.println("</table>");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error fetching students</p>");
        }

        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String email = request.getParameter("email");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql:3306/studentdb", "root", "password");
            String sql = "INSERT INTO students (name, age, email) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, Integer.parseInt(age));
            ps.setString(3, email);
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("students");
    }
}
