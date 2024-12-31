package com.aem.geeks.core.servlets;


import com.aem.geeks.core.services.GreetingService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/greeting",
        "sling.servlet.methods=GET"
})
public class GreetingServlet extends HttpServlet {

    @Reference
    private GreetingService greetingService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name == null || name.isEmpty()) {
            name = "World";
        }
        String greeting = greetingService.getGreeting(name);
        response.getWriter().write(greeting);
    }
}
