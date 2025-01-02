package com.aem.geeks.core.services.impl;


import com.aem.geeks.core.services.FormDataService;
import org.apache.sling.api.resource.Resource;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = FormDataService.class)
public class FormDataServiceImpl implements FormDataService {

    @Reference
    private Resource resource;

    @Override
    public String getUsername() {
        return resource.getValueMap().get("username", "Default Username");
    }

    @Override
    public String getPassword() {
        return resource.getValueMap().get("password", "Default Password");
    }

    @Override
    public String getDateOfBirth() {
        return resource.getValueMap().get("dateOfBirth", "Default Date of Birth");
    }

    @Override
    public String getButtonText() {
        return resource.getValueMap().get("buttonText", "Default Button Text");
    }
}
