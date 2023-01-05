package com.december26.entity;

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.december26.entity.enums.EAddressType;
import com.december26.entity.enums.EGender;

/*
username--> unique olsun null geçilmesin
pasword 32 karakter olsun
gender string
tablomuzun ismi de tbluser

crud işlemleri için bir interface açalım bir de userRepositorysi oluşturalım
save, update, delete, findbyid, findAll Generic metodu yazalım

*/



@Entity
@Table(name = "tbluser")
public class User {

	
	//generated value strategies neler var farkları neler
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	
	@Column (nullable = false, unique = true)
	private String username;

	
	@Column(length = 32)
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	private UserDetail userDetail;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Post> posts;
	
	public User() {
		super();
	}

	
	

	public User(long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;

	}




	public User( String username, String password) {
		super();
		this.username = username;
		this.password = password;

	}







	public User(String username, String password, UserDetail userDetail) {
		super();
		this.username = username;
		this.password = password;
		this.userDetail = userDetail;
	}




	public UserDetail getUserDetail() {
		return userDetail;
	}




	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}




	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}







	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password +  "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
