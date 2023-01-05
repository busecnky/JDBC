package com.december30.controller;

import com.december30.entity.Student;
import com.december30.service.StudentService;
import com.december30.util.BAUtils;

public class StudentController {

	StudentService studentService;

	public StudentController() {
		this.studentService = new StudentService();
	}

	public void create() {
		String username = BAUtils.readString("Lütfen kullanca icin belirlediginiz kullanca adan giriniz:");
		String password = BAUtils.readString("Lütfen kullanic için belirlediginiz kullanca sifresini giriniz:");
		Student student = new Student(username, password);
		studentService.create(student);
	}

	public void delete() {
		long id = BAUtils.readInt("Lütfen silme istediginiz ögrencinin ID sini giriniz:");
		studentService.delete(id);
	}

}
