package com.december29.entity;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Teacher {
	@Id
	@GeneratedValue
	private long id;
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Embedded
	private Address address;
	
	@Enumerated(EnumType.STRING)
	private EGender gender;
	
	
	@OneToMany(mappedBy = "teacher")
	private List<Course> courses;
}
