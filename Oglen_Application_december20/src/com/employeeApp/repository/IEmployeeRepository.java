package com.employeeApp.repository;

import com.employeeApp.entity.Employee;

public interface IEmployeeRepository {

	
	public void createEmployee(Employee employee);
	
	public void getAllEmployees();
	
	public void getEmployeeById(int id);
	
	public void updateEmployeeById(int id, String name);
	
	public void deleteEmployeeById(int id);
}
