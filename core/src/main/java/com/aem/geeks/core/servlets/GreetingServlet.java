package com.aem.geeks.core.servlets;



import com.aem.geeks.core.services.GreetingService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(
        service = Servlet.class,
        property = {
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.paths=/bin/example/greet"
        }
)
public class GreetingServlet extends SlingAllMethodsServlet {

    @Reference
    private GreetingService greetingService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        // Get the "name" parameter from the request
        String name = request.getParameter("name");
        if (name == null || name.isEmpty()) {
            name = "Guest";
        }

        // Get the greeting message from the service
        String greeting = greetingService.getGreeting(name);

        // Set response type and send the greeting
        response.setContentType("text/plain");
        response.getWriter().write(greeting);
    }
}
