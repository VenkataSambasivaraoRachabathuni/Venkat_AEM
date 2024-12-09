package com.aem.geeks.core.models;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = Resource.class)
public class ParentModel {

    @Self
    private Resource resource;

    @ChildResource(name = "nested")
    private NestedModel nestedModel;

    public String getNestedTitle() {
        return nestedModel.getTitle();
    }

    public String getNestedDescription() {
        return nestedModel.getDescription();
    }

    public String getNestedAuthor() {
        return nestedModel.getAuthor();
    }

    public List<String> getNestedItems() {
        return nestedModel.getItems();
    }
}
