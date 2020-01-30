package com.upsolve.upsolve.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "problem")
public class Problem {
	
	@Id
	@Column(name = "link")
	private String link;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "addedby")
	private String addedby;

	public Problem(String link, String name, String type, String addedby) {
		super();
		this.link = link;
		this.name = name;
		this.type = type;
		this.addedby = addedby;
	}
	public Problem() {}
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
	public String getAddedby() {
		return addedby;
	}
	public void setAddedby(String addedby) {
		this.addedby = addedby;
	}
	@Override
	public String toString() {
		return "Problem [link=" + link + ", name=" + name + ", type=" + type + ", addedby=" + addedby + "]";
	}


	
	
	
	
	
}
