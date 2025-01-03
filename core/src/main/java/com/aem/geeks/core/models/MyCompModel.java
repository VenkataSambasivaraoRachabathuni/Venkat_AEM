package com.aem.geeks.core.models;



import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;

import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class MyCompModel {
        private String title;

        @Inject
        private String options;

        @Inject
        @Optional
        private List<Resource> additionalInfo;

        public String getTitle() {
            return title;
        }

        public String getOptions() {
            return options;
        }

        public List<String> getAdditionalInfo() {
            if (additionalInfo != null) {
                return additionalInfo.stream()
                        .map(resource -> resource.getValueMap().get("infoItem", String.class))
                        .collect(Collectors.toList());
            }
            return new ArrayList<>();
        }
    }
