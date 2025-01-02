package com.aem.geeks.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "Greeting Service Configuration",
        description = "Configuration for Greeting Service"
)
public @interface GreetingServiceConfig {

    @AttributeDefinition(
            name = "Greeting Message",
            description = "The greeting message to display."
    )
    String greetingMessage() default "Hello";

    @AttributeDefinition(
            name = "Enable Greeting",
            description = "Flag to enable or disable the greeting functionality."
    )
    boolean enableGreeting() default true;

    @AttributeDefinition(
            name = "Supported Languages",
            description = "Comma-separated list of supported languages for greetings."
    )
    String[] supportedLanguages() default { "English", "Spanish", "French" };
}
