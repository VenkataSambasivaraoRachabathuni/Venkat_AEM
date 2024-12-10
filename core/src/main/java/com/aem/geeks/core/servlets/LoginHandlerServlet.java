package com.aem.geeks.core.servlets;

import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.Override;

@Component(
        service = Servlet.class,
        property = {
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.paths=/bin/login-handler"
        }
)
public class LoginHandlerServlet extends LoginHandlerServlets {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch parameters from the form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String dateOfBirth = request.getParameter("dateOfBirth");

        // Sample logic to validate inputs
        if ("admin".equals(username) && "admin123".equals(password) && "1990-01-01".equals(dateOfBirth)) {
            response.getWriter().write("Login successful! Welcome, " + username + ".");
        } else {
            response.getWriter().write("Invalid login details. Please try again.");
        }
    }
}
