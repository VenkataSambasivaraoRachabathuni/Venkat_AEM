package com.aem.geeks.core.models;

import com.aem.geeks.core.services.TitleService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import javax.annotation.PostConstruct;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;

@Model(adaptables = Resource.class)
public class AdvancedModel {
    
    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    private String title;

    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    private String description;

    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    private String author;

    @OSGiService
    private TitleService titleService;

    @PostConstruct
    protected void init() {
        if (title == null) {
            title = titleService.getDefaultTitle();
        }
        if (description == null) {
            description = titleService.getDefaultDescription();
        }
        if (author == null) {
            author = titleService.getDefaultAuthor();
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }
}
