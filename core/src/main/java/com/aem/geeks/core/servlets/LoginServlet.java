package com.aem.geeks.core.servlets;

import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.HttpConstants;
import org.osgi.service.component.annotations.Component;
import org.apache.sling.api.servlets.ServletResolverConstants;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.json.JSONObject;

@Component(
    service = {Servlet.class},
    property = {
        ServletResolverConstants.SLING_SERVLET_PATHS + "=/bin/login",
        ServletResolverConstants.SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_POST
    }
)
public class LoginServlet extends SlingAllMethodsServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Dummy authentication logic for demonstration
        if ("admin".equals(username) && "admin".equals(password)) {
            // Redirect to success page
            response.sendRedirect(request.getContextPath() + "/content/success.html");
        } else {
            // Redirect to error page with message
            String errorMessage = "Invalid credentials, please try again.";
            response.sendRedirect(request.getContextPath() + "/content/error.html?message=" + java.net.URLEncoder.encode(errorMessage, "UTF-8"));
        }
    }
}
