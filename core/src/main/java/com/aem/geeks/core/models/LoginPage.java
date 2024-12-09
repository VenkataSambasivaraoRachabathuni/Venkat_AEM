package com.aem.geeks.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import javax.inject.Named;

@Model(adaptables = Resource.class)
public class LoginPage {

    @Inject
    @Named("username")
    private String username;

    @Inject
    @Named("password")
    private String password;

    @Inject
    @Named("dateofbirth")
    private String dateOfBirth;

    @Inject
    @Named("buttonText")
    private String buttonText;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getButtonText() {
        return buttonText != null ? buttonText : "Click here to login";
    }


}
