package com.aem.geeks.core.servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(
    service = { Servlet.class },
    property = {
        "sling.servlet.paths=/bin/aemgeeks/components/header",
        "sling.servlet.methods=GET"
    }
)
public class MyComponentServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Get the ResourceResolver
        ResourceResolver resourceResolver = request.getResourceResolver();

        // Define the path to the content node you want to retrieve
        String contentPath = "/content/aemgeeks/us/en/jcr:content/root/container/container/header_1480840502";

        // Retrieve the resource
        Resource resource = resourceResolver.getResource(contentPath);

        // Check if the resource exists
        if (resource != null) {
            // Get the properties of the resource
            String title = resource.getValueMap().get("jcr:title", String.class);
            String description = resource.getValueMap().get("jcr:description", String.class);

            // Build the JSON response
            response.getWriter().write("{");
            response.getWriter().write("\"title\":\"" + title + "\",");
            response.getWriter().write("\"description\":\"" + description + "\"");
            response.getWriter().write("}");
        } else {
            // Resource not found
            response.getWriter().write("{\"error\":\"Component not found\"}");
        }
    }
}
