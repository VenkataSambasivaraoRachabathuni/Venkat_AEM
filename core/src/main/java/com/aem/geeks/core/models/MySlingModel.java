package com.aem.geeks.core.models;

import com.aem.geeks.core.services.MyService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.SlingHttpServletRequest;

import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = "aemgeeks/components/content/slingmodel",
        adapters = MySlingModel.class
)
@Exporter(name = "jackson", extensions = "json")
public class MySlingModel {

    /* Inject the 'title' field from the component's dialog */
    @ValueMapValue
    @Default(values = "Default Title")
    private String title;

    // Inject the 'description' field from the component's dialog
    @Inject
    @Optional
    private String description;

    // Inject the list of child resources under this component (if any)
    @ChildResource
    private List<Resource> items;

    // Inject Sling-specific objects like the ResourceResolver
    @SlingObject
    private ResourceResolver resourceResolver;

    // Inject the self resource
    @Self
    private Resource resource;

    // Inject the custom service (MyService)
    @Inject
    @Source("osgi-services")
    private MyService myService;

    // Inject the page path
    @Inject
    @Via("resource")
    private String pagepath;

    // A message that is generated after the Sling Model is initialized
    private String initializedMessage;

    public MySlingModel(String title, String description, List<Resource> items, ResourceResolver resourceResolver, Resource resource, MyService myService, String pagepath) {
        // Constructor
        this.title = title;
        this.description = description;
        this.items = items;
        this.resourceResolver = resourceResolver;
        this.resource = resource;
        this.myService = myService;
        this.pagepath = pagepath;
    }

    // PostConstruct to initialize any complex logic after fields are injected
    @PostConstruct
    protected void init() {
        // Initialize the message with the title (can also include service logic here)
        initializedMessage = "Component initialized with title: " + title;

        // Example of using the injected service
        if (myService != null) {
            initializedMessage += " and service message: " + myService.getServiceMessage();
        }

        // Additional initialization logic if needed
    }

    // Getters for the fields so they can be used in HTL
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Resource> getItems() {
        return items;
    }

    public String getPage() {
        return pagepath;
    }

    private String getInitialized() {
        return initializedMessage;
    }

}
