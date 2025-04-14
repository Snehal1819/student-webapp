package com.studentapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/edit")
public class EditStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql:3306/studentdb", "root", "password");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students WHERE id=" + id);
            
            if (rs.next()) {
                out.println("<html><head><title>Edit Student</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; background-color: #f2f2f2; padding: 20px; }");
                out.println("h2 { color: #333; }");
                out.println("form { background: #fff; padding: 20px; border-radius: 10px; max-width: 400px; margin: auto; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
                out.println("input[type=text], input[type=number], input[type=email] { width: 100%; padding: 10px; margin: 8px 0; border: 1px solid #ccc; border-radius: 5px; }");
                out.println("input[type=submit] { background-color: #4CAF50; color: white; padding: 10px 15px; border: none; border-radius: 5px; cursor: pointer; }");
                out.println("input[type=submit]:hover { background-color: #45a049; }");
                out.println("</style></head><body>");

                out.println("<h2>Edit Student</h2>");
                out.println("<form method='post' action='update'>");
                out.println("<input type='hidden' name='id' value='" + rs.getInt("id") + "'/>");
                out.println("Name: <input type='text' name='name' value='" + rs.getString("name") + "'/><br/>");
                out.println("Age: <input type='number' name='age' value='" + rs.getInt("age") + "'/><br/>");
                out.println("Email: <input type='email' name='email' value='" + rs.getString("email") + "'/><br/>");
                out.println("<input type='submit' value='Update'/>");
                out.println("</form></body></html>");
            } else {
                out.println("<p>Student not found!</p>");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error loading student data.</p>");
        }
    }
}
