package com.upsolve.upsolve.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LinkChecker {
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String link;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String name;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String resource;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String about;
	private String addedby;

	public String getAddedBy() {
		return addedby;
	}
	public void setAddedBy(String addedby) {
		this.addedby = addedby;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	@Override
	public String toString() {
		return "LinkChecker [link=" + link + ", name=" + name + ", resource=" + resource + ", about=" + about + "]";
	}

	public LinkChecker(@NotNull(message = "is required") @Size(min = 1, message = "is required") String link,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String name,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String resource,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String about) {
		super();
		this.link = link;
		this.name = name;
		this.resource = resource;
		this.about = about;
	}

	public LinkChecker() {}

}
