package com.upsolve.upsolve.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comment")
public class Comment implements Comparable<Comment>  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotNull
	@Column(name = "content")
	private String content;
	
	@Column(name = "addedby")
	private String addedby;
	
	@Column(name = "blogid")
	private Integer blogid;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blogid", nullable = false,insertable = false, updatable = false)
    private Blog blog;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getBlogid() {
		return blogid;
	}

	public void setBlogid(Integer blogid) {
		this.blogid = blogid;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public Comment(Integer id, String content, String addedby, Integer blogid, Blog blog) {
		super();
		this.id = id;
		this.content = content;
		this.addedby = addedby;
		this.blogid = blogid;
		this.blog = blog;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Comment o) {
		return o.getId() - this.id;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", addedby=" + addedby + ", blogid=" + blogid + ", blog="
				+ blog + "]";
	}
	
	
}
