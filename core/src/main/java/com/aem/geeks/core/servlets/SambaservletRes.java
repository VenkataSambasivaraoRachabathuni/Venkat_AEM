package com.aem.geeks.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.framework.Constants;

@Component(
    service = { Servlet.class },
    property = {
        Constants.SERVICE_DESCRIPTION + "=Example Path Servlet",
        "sling.servlet.methods=" + "GET",
        "sling.servlet.resourceTypes=" + "content/aemgeeks/us/en/author",
        "sling.servlet.extensions=" + "html"
    }
)
public class SambaservletRes extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"message\":\"Hello from Resource Type Servlet!\"}");
    }
}
