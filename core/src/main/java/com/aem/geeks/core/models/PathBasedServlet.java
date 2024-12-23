package com.aem.geeks.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.framework.Constants;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(
        service = Servlet.class,
        property = {
                Constants.SERVICE_DESCRIPTION + "=Example Path-based Servlet",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.paths=/bin/example/pathbased" // Registering the servlet to this path
        }
)
public class PathBasedServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        // Set response type
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Hardcoded JSON response
        String jsonResponse = "{ \"message\": \"Hello from Path-based Servlet!\", \"status\": \"success\" }";

        // Write the response
        response.getWriter().write(jsonResponse);
    }
}
