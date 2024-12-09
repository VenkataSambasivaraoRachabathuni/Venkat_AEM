package com.aem.geeks.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Cinema {

    @ValueMapValue
    private String movieName;

    @ChildResource
    private Resource actorname;

    @ChildResource
    private Resource directorname;

    private Map<String, String> actorNamesMap = new HashMap<>();
    private Map<String, String> directorNamesMap = new HashMap<>();

    @PostConstruct
    protected void init() {
        if (actorname != null) {
            actorNamesMap.put("hero", actorname.getValueMap().get("hero", String.class));
            actorNamesMap.put("heroin", actorname.getValueMap().get("heroin", String.class));
            actorNamesMap.put("sideactor", actorname.getValueMap().get("sideactor", String.class));
        }

        if (directorname != null) {
            directorNamesMap.put("directorname", directorname.getValueMap().get("directorname", String.class));
        }
    }

    public String getMovieName() {
        return movieName;
    }

    public Map<String, String> getActorname() {
        return actorNamesMap;
    }

    public Map<String, String> getDirectorname() {
        return directorNamesMap;
    }
}
