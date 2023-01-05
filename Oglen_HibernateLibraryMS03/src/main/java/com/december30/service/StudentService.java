package com.december30.service;

import java.util.List;
import java.util.Optional;

import com.december30.entity.Student;
import com.december30.repository.StudentDao;

public class StudentService implements IService<Student> {

private StudentDao studentDao;
	
	public StudentService() {
		this.studentDao = new StudentDao();
	}
	
	@Override
	public void create(Student entity) {
		studentDao.create(entity);
	}

	@Override
	public void delete(long id) {
		studentDao.delete(id);
	}

	@Override
	public void update(long id, Student entity) {
		studentDao.update(id, entity);
	}

	@Override
	public List<Student> listAll() {
		return studentDao.listAll();
	}

	@Override
	public Student find(long id) {
		Student student = studentDao.find(id);
		return student;
	}

	public Optional<Student> findByUserName(String student) {
		return studentDao.findByUserName(student);
		
	}
	
}
