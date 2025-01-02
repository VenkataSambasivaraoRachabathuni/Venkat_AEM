package com.aem.geeks.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Greeting Service Configuration", description = "Configuration for the Greeting Service")
public @interface GreetingServiceConfig {

    @AttributeDefinition(name = "Greeting Message", description = "Message to be used in the greeting")
    String greetingMessage() default "Hello";

    @AttributeDefinition(name = "Enable Greeting", description = "Enable or disable the greeting service")
    boolean enableGreeting() default true;

}
