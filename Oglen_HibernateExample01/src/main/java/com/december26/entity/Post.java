package com.december26.entity;



import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "content")
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP) //Timestamp yazarsak saliyeye kadar veriyor
	private Date createdDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	public Post() {
		super();
	}
	public Post(String content, Date createdDate, User user) {
		super();
		this.content = content;
		this.createdDate = createdDate;
		this.user = user;
	}
	
	public Post(String content, Date createdDate) {
		super();
		this.content = content;
		this.createdDate = createdDate;
	}
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", content=" + content + "]";
	}
	
	
	
	
	
	
	
}
