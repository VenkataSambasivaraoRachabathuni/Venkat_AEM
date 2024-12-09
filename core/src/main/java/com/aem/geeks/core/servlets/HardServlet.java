package com.aem.geeks.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class,
           property = {
               "sling.servlet.methods=GET",
               "sling.servlet.paths=/bin/hard"
           })
public class HardServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) 
            throws ServletException, IOException {
        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource resource = resourceResolver.getResource("/content/aemgeeks/us/en/jcr:content/root/container/container/hardcomponent");

        if (resource != null) {
            Resource nestedResource = resource.getChild("nested");
            if (nestedResource != null) {
                ValueMap properties = nestedResource.getValueMap();
                String title = properties.get("title", String.class);
                String description = properties.get("description", String.class);
                String author = properties.get("author", String.class);

                response.setContentType("application/json");
                response.getWriter().write("{");
                response.getWriter().write("\"title\": \"" + title + "\",");
                response.getWriter().write("\"description\": \"" + description + "\",");
                response.getWriter().write("\"author\": \"" + author + "\"");
                response.getWriter().write("}");
            } else {
                response.setStatus(SlingHttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.setStatus(SlingHttpServletResponse.SC_NOT_FOUND);
        }
    }
}
