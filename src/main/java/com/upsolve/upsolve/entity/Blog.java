package com.upsolve.upsolve.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "blog")
public class Blog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "addedby")
	private String addedby;
	
	
	 @OneToMany(cascade = CascadeType.ALL,
	            fetch = FetchType.LAZY,
	            mappedBy = "blog")
	    private Set<Comment> comments = new HashSet<>();
	
	 
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

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

	public Blog(Integer id, String title, String description, String content, String addedby) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
		this.addedby = addedby;
	}

	public Blog() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", description=" + description + ", content=" + content
				+ ", addedby=" + addedby + "]";
	}
	
	
}
