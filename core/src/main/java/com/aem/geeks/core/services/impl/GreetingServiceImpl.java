package com.aem.geeks.core.services.impl;



import com.aem.geeks.core.services.GreetingService;
import org.osgi.service.component.annotations.Component;

@Component(service = GreetingService.class)
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String getGreeting(String name) {
        return "Hello, " + name + "! Welcome to OSGi in AEM.";
    }
}
