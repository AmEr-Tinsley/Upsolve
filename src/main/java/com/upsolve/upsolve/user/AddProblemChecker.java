package com.upsolve.upsolve.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class AddProblemChecker {
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String link;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String name;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String type;
	
	
	private String addedby;
	
	public String getAddedby() {
		return addedby;
	}
	
	public void setAddedby(String added) {
		this.addedby = added;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "AddProblemChecker [link=" + link + ", name=" + name + ", type=" + type + "]";
	}

	public AddProblemChecker(@NotNull(message = "is required") @Size(min = 1, message = "is required") String link,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String name,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String type) {
		super();
		this.link = link;
		this.name = name;
		this.type = type;
	}
	
	public AddProblemChecker(){addedby = "nobody" ;}
	
	
	
}
