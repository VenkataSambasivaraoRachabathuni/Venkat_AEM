package com.aem.geeks.core.servlets;


import com.aem.geeks.core.services.FormDataService;
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
                "sling.servlet.resourceTypes=" + "aemgeeks/components/content/loginPage",
                "sling.servlet.extensions=" + "json"
        }
)
public class ResourceTypeServlet extends SlingAllMethodsServlet {

    @Reference
    private FormDataService formDataService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        // Fetch data using the service
        String username = formDataService.getUsername();
        String password = formDataService.getPassword();
        String dateOfBirth = formDataService.getDateOfBirth();
        String buttonText = formDataService.getButtonText();

        // Create JSON response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
                String.format(
                        "{\"username\":\"%s\",\"password\":\"%s\",\"dateOfBirth\":\"%s\",\"buttonText\":\"%s\"}",
                        username, password, dateOfBirth, buttonText
                )
        );
    }
}
