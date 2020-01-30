package com.upsolve.upsolve.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddPostChecker {

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String title;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String description;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String content;
	
	private String addedby;
	
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddedby() {
		return addedby;
	}

	public void setAddedby(String addedby) {
		this.addedby = addedby;
	}

	public AddPostChecker(@NotNull(message = "is required") @Size(min = 1, message = "is required") String title,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String description,
			@NotNull(message = "is required") @Size(min = 1, message = "is required") String content, String addedby) {
		super();
		this.title = title;
		this.description = description;
		this.content = content;
		this.addedby = addedby;
	}

	public AddPostChecker() {
		super();
		this.id = -1;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AddPostChecker [title=" + title + ", description=" + description + ", content=" + content + ", addedby="
				+ addedby + "]";
	}
	
	
	
	
}
