package com.aem.geeks.core.servlets;

import java.io.IOException;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class,
    property = {
        "sling.servlet.paths=/bin/news",
        "sling.servlet.methods=GET"
    }
)
public class SambaServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        JsonObjectBuilder object = Json.createObjectBuilder();
        object.add("name", "Samba");
        object.add("site", "www.google.com");
        
        res.getWriter().write(object.build().toString());
    }
}
