package com.aem.geeks.core.models;


import com.adobe.cq.export.json.ExporterConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Collections;

@Model(
        adaptables = {Resource.class},  // Adapting from the resource
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, // Optional injection strategy
        resourceType = "aemgeeks/components/content/article"
)
@Exporter(
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, // Enable JSON export
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class AdvancedSlingModel {

    // Injecting a simple property
    @ValueMapValue
    @Default(values = "Default Title")
    private String title;

    // Injecting a required property
    @Inject
    private String description;

    // Optional property
    @Inject
    private String author;

    // Fetching property via the current resource
    @Self
    private Resource currentResource;

    // Injecting a multi-field (List of child resources)
    @ChildResource
    private List<Resource> articles;

    // Injecting using a specific source
    @Inject
    @Source("sling-object")
    private ResourceResolver resourceResolver;

    // Computed values
    private int articleCount;
    private List<Article> articleList;

    /**
     * PostConstruct - Executed after Sling Model is initialized.
     */
    @PostConstruct
    protected void init() {
        articleList = new ArrayList<>();
        if (articles != null) {
            for (Resource articleResource : articles) {
                String title = articleResource.getValueMap().get("title", StringUtils.EMPTY);
                String description = articleResource.getValueMap().get("description", StringUtils.EMPTY);
                String author = articleResource.getValueMap().get("author", StringUtils.EMPTY);
                articleList.add(new Article(title, description, author));
            }
        }
        articleCount = articleList.size();
    }

    // Nested class for handling multi-field
    public static class Article {
        private final String title;
        private final String description;
        private final String author;

        public Article(String title, String description, String author) {
            this.title = title;
            this.description = description;
            this.author = author;
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

    // Exposing the model as JSON properties
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Optional<String> getAuthor() {
        return Optional.ofNullable(author);
    }

    public int getCount() {
        return articleCount;
    }

    public List<Article> getList() {
        return Collections.unmodifiableList(articleList);
    }

    @JsonIgnore
    public Resource getResource() {
        return currentResource;
    }
}
