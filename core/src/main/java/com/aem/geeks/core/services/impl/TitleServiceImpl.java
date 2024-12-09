package com.aem.geeks.core.services.impl;

import com.aem.geeks.core.services.TitleService;
import org.osgi.service.component.annotations.Component;

@Component(service = TitleService.class)
public class TitleServiceImpl implements TitleService {
    @Override
    public String getDefaultTitle() {
        return "Default Title";
    }

    @Override
    public String getDefaultDescription() {
        return "Default Description";
    }

    @Override
    public String getDefaultAuthor() {
        return "Default Author";
    }
}
