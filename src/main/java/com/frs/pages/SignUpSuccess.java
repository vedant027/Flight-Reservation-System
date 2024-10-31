package com.frs.pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup_success")
public class SignUpSuccess extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try (PrintWriter pw = response.getWriter()) {
            pw.print("<!DOCTYPE html>");
            pw.print("<html lang='en'>");
            pw.print("<head>");
            pw.print("<meta charset='UTF-8' />");
            pw.print("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            pw.print("<title>Sign Up Success</title>");
            pw.print("<style>");
            pw.print("body {");
            pw.print("    background-color: #f0f4f8; /* Light gray-blue background */");
            pw.print("    font-family: 'Roboto', sans-serif; text-align:center;");
            pw.print("    margin: 0;");
            pw.print("    padding: 20px; /* Add padding to body */");
            pw.print("}"); 
            pw.print("h5 {");
            pw.print("    color: #28a745; /* Green for success message */");
            pw.print("    font-size: 2rem;");
            pw.print("    margin: 20px 0; /* Margin around the heading */");
            pw.print("}");
            pw.print(".button {");
            pw.print("    display: inline-block;");
            pw.print("    padding: 10px 20px;");
            pw.print("    background-color: #007bff; /* Blue for button */");
            pw.print("    color: white;");
            pw.print("    text-decoration: none;");
            pw.print("    border-radius: 5px;");
            pw.print("    font-size: 1rem;");
            pw.print("    transition: background-color 0.3s;");
            pw.print("}");
            pw.print(".button:hover {");
            pw.print("    background-color: #0056b3; /* Darker blue on hover */");
            pw.print("}");
            pw.print("</style>");
            pw.print("</head>");
            pw.print("<body>");
            pw.print("<h5>Successful SignUp!!!</h5>");
            pw.print("<a href='signin.html' class='button'>Login Now</a>");
            pw.print("</body>");
            pw.print("</html>");
        }
    }
}
