package com.upsolve.upsolve.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "links")
public class Link {
	@Id
	@Column(name = "link")
	private String link;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "resource")
	private String resource;
	
	@Column(name = "about")
	private String about;
	
	@Column(name = "addedby")
	private String addedby;
	
	

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
	public String getAddedby() {
		return addedby;
	}
	public void setAddedby(String addedby) {
		this.addedby = addedby;
	}
	public Link(String link, String name, String resource, String about,String addedby) {
		super();
		this.link = link;
		this.name = name;
		this.resource = resource;
		this.about = about;
		this.addedby = addedby;
	}
	public Link() {}

	
}
