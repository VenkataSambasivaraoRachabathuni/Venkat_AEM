package com.aem.geeks.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(
        service = Servlet.class,
        property = {
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.resourceTypes=aemgeeks/components/content/loginPage",
                "sling.servlet.selectors=submit"
        }
)
public class LoginHandlerServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        // Fetch parameters from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String buttonText = request.getParameter("buttonText"); // New field

        // Get ResourceResolver from the request
        ResourceResolver resourceResolver = request.getResourceResolver();

        // Example: Retrieve a resource from the repository
        Resource userProfileResource = resourceResolver.getResource("/content/userProfiles/" + username);

        // Set content type
        response.setContentType("application/json");

        String jsonResponse;
        if (userProfileResource != null) {
            // Fetch properties from the resource
            String storedPassword = userProfileResource.getValueMap().get("password", String.class);
            String storedDOB = userProfileResource.getValueMap().get("dateOfBirth", String.class);

            // Validate login details
            if (storedPassword != null && storedPassword.equals(password) &&
                    storedDOB != null && storedDOB.equals(dateOfBirth)) {
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
        } else {
            jsonResponse = String.format(
                    "{ \"message\": \"User profile not found.\", \"buttonText\": \"%s\" }",
                    buttonText != null ? buttonText : "Try Again"
            );
        }

        // Write response
        response.getWriter().write(jsonResponse);
    }
}
