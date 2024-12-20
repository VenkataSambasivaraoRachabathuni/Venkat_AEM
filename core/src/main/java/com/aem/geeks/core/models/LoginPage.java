package com.aem.geeks.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import javax.inject.Named;

@Model(adaptables = Resource.class)
public class LoginPage {

    @Inject
    private String username;

    @Inject
    private String password;

    @Inject
    private String dateOfBirth;

    @Inject
    private String buttonText;

    public String getButtonText() {
        return buttonText;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
