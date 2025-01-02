package com.aem.geeks.core.services.impl;

import com.aem.geeks.core.services.GreetingService;
import com.aem.geeks.core.config.GreetingServiceConfig;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = GreetingService.class)
@Designate(ocd = GreetingServiceConfig.class)
public class GreetingServiceImpl implements GreetingService {

    private String greetingMessage;
    private boolean enableGreeting;
    private String[] supportedLanguages;

    @Activate
    @Modified
    protected void activate(GreetingServiceConfig config) {
        this.greetingMessage = config.greetingMessage();
        this.enableGreeting = config.enableGreeting();
        this.supportedLanguages = config.supportedLanguages();
    }

    @Override
    public String getGreeting(String name, String language) {
        if (!enableGreeting) {
            return "Greeting service is disabled.";
        }

        if (language == null || language.equalsIgnoreCase("English")) {
            return greetingMessage + ", " + name + "!";
        } else if (language.equalsIgnoreCase("Spanish")) {
            return "Hola, " + name + "!";
        } else if (language.equalsIgnoreCase("French")) {
            return "Bonjour, " + name + "!";
        } else {
            return greetingMessage + ", " + name + "!";
        }
    }

    @Override
    public boolean isLanguageSupported(String language) {
        for (String lang : supportedLanguages) {
            if (lang.equalsIgnoreCase(language)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public CharSequence supportedLanguages() {
        return null;
    }
}
