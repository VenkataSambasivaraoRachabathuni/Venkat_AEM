package com.aem.geeks.core.servlets;

import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(
        service = Servlet.class,
        property = {
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.paths=/bin/login-handler",
                "sling.servlet.selectors=login",
                "sling.servlet.extensions=json"
        }
)
public class LoginHandlerServlet extends SlingAllMethodsServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch parameters from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String buttonText = request.getParameter("buttonText"); // New field

        // Set content type
        response.setContentType("application/json");

        // Logic to validate inputs and build response
        String jsonResponse;
        if ("admin".equals(username) && "admin123".equals(password) && "1990-01-01".equals(dateOfBirth)) {
            jsonResponse = String.format(
                    "{ \"message\": \"Login successful! Welcome, %s.\", \"buttonText\": \"%s\" }",
                    username, buttonText != null ? buttonText : "Default Button Text"
            );
        } else {
            jsonResponse = String.format(
                    "{ \"message\": \"Invalid login details. Please try again.\", \"buttonText\": \"%s\" }",
                    buttonText != null ? buttonText : "Try Again"
            );
        }

        // Write response
        response.getWriter().write(jsonResponse);
    }
}
