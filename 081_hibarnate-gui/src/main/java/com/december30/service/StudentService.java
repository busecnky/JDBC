package com.december30.service;

import java.util.List;

import com.december30.entity.Student;
import com.december30.repository.StudentRepository;

public class StudentService {

	
	private StudentRepository studentRepository;
	
	
	public StudentService() {
		studentRepository = new StudentRepository();
	}

	public void save(Student student) {
	
		try {
			studentRepository.save(student);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		}
		
		
		
	}

	public List<Student> getAll() {
		List<Student> list = null;
		try {
			list = studentRepository.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Student> getByName(String firstName) {
		List<Student> list = null; 
		try {
			list = studentRepository.getByName(firstName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;	
	}

	public void delete(String id) {
		try {
			studentRepository.delete(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void update(String id,  String firstName, String lastName, String email) {
		Student student = new Student(Integer.parseInt(id), firstName, lastName, email);
		try {
			studentRepository.update(student);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Update yapılamadı!");
		}
		
	}
}
