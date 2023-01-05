package com.december30.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Entity
public class Student extends User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "user_type")
	@Enumerated(EnumType.STRING)
	private EUserType userType = EUserType.STUDENT;
	
	
	@ManyToMany(mappedBy = "studentList")
	private List<Book> books;
	
	
	public Student(String username, String password) {
		super(username,password);

	}
	public Student() {
		super();

	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", userType=" + userType + "]";
	}
	
}
