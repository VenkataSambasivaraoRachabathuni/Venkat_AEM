package com.aem.geeks.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class)
public class Header {
	
	@Inject
	private String home;
	
	@Inject
	private String contact;
	
	@Inject
	private String about;
	
	public String getHome() {
		return home;
	}

	public String getContact() {
		return contact;
	}

	public String getAbout() {
		return about;
	}

	public String getSearch() {
		return search;
	}

	@Inject
	private String search;

	
}
