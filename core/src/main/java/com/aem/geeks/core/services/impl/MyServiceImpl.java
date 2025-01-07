package com.aem.geeks.core.services.impl;

import com.aem.geeks.core.services.MyService;
import org.osgi.service.component.annotations.Component;

@Component(service = MyService.class)
public class MyServiceImpl implements MyService {

    @Override
    public String getServiceMessage() {
        return "Hello from MyService!";
    }
}
