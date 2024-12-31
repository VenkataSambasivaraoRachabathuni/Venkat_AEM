package com.aem.geeks.core.services.impl;


import com.aem.geeks.core.config.GreetingServiceConfig;
import com.aem.geeks.core.services.GreetingService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = GreetingService.class)
@Designate(ocd = GreetingServiceConfig.class)
public class GreetingServiceImpl implements GreetingService {

    private String greetingMessage;
    private boolean enableGreeting;

    @Activate
    @Modified
    protected void activate(GreetingServiceConfig config) {
        this.greetingMessage = config.greetingMessage();
        this.enableGreeting = config.enableGreeting();
    }

    @Override
    public String getGreeting(String name) {
        if (!enableGreeting) {
            return "Greeting service is disabled.";
        }
        return greetingMessage + ", " + name + "!";
    }
}
