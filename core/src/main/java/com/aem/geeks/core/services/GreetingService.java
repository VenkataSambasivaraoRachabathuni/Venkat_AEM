package com.aem.geeks.core.services;

public interface GreetingService {
    String getGreeting(String name, String language);
    boolean isLanguageSupported(String language);

    CharSequence supportedLanguages();
}
