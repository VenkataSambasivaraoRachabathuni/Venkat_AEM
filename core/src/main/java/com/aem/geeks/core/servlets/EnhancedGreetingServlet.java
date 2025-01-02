package com.aem.geeks.core.servlets;

import com.aem.geeks.core.services.GreetingService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(
        service = { Servlet.class },
        property = {
                "sling.servlet.paths=/bin/enhanced-greeting",
                "sling.servlet.methods=GET",
                Constants.SERVICE_DESCRIPTION + "=Enhanced Greeting Servlet",
                Constants.SERVICE_VENDOR + "=AEM Geeks"
        }
)
public class EnhancedGreetingServlet extends SlingAllMethodsServlet {

    private final GreetingService greetingService;

    public EnhancedGreetingServlet(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String language = request.getParameter("lang");

        if (name == null || name.isEmpty()) {
            name = "World";
        }

        if (!greetingService.isLanguageSupported(language)) {
            response.getWriter().write("Language not supported. Supported languages: " + String.join(", ", greetingService.supportedLanguages()));
            return;
        }

        String greeting = greetingService.getGreeting(name, language);
        response.setContentType("text/plain");
        response.getWriter().write(greeting);
    }
}
