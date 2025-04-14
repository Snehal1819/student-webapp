package com.studentapp;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/update")
public class UpdateStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String email = request.getParameter("email");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql:3306/studentdb", "root", "password");

            PreparedStatement ps = con.prepareStatement("UPDATE students SET name=?, age=?, email=? WHERE id=?");
            ps.setString(1, name);
            ps.setInt(2, Integer.parseInt(age));
            ps.setString(3, email);
            ps.setInt(4, Integer.parseInt(id));
            ps.executeUpdate();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("students");
    }
}
