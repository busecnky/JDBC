package com.december29.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="course_material")
public class CourseMaterial {

	
	@Id
	@GeneratedValue
	private long id;
	private String url;
	
	@OneToOne
	@JoinColumn(name = "course_id", referencedColumnName = "id")
	private Course course; 
	
}
