package com.employeeApp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.employeeApp.entity.Employee;
import com.employeeApp.util.DBConnection;

public class EmployeeRepository implements IEmployeeRepository {

	private Connection connection = DBConnection.connect();

	@Override
	public void createEmployee(Employee employee) {

		String sql = " insert into employee (name,salary,age ) values (?,?,?)";

		PreparedStatement prp;

		try {
			prp = connection.prepareStatement(sql);
			prp.setString(1, employee.getName());
			prp.setInt(2, employee.getSalary());
			prp.setInt(3, employee.getAge());
			prp.executeUpdate();
			System.out.println(employee.getName() + " veri tabanına eklendi.");
			prp.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void displayEmployee(ResultSet resultSet) {
		try {

			while (resultSet.next()) {
				System.out.println(resultSet.getInt("ID") + "-" + "\t" + resultSet.getString("Name") + "\t\t"
						+ resultSet.getInt("Salary") + "\t" + resultSet.getInt("Age"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void getAllEmployees() {
		String sql = "Select * from employee";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			displayEmployee(resultSet);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void getEmployeeById(int id) {
		String sql = "Select * from employee where id= " + id;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			displayEmployee(resultSet);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateEmployeeById(int id, String name) {
		String sql = "update employee set = ? where id = " + id;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			int effectedRow = preparedStatement.executeUpdate();
			
			if(effectedRow > 0) {
				System.out.println("Update Success");
			}else {
				System.out.println("Update Fail");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteEmployeeById(int id) {
		String sql = "delete from employee where id = " + id;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			int effectedRow = preparedStatement.executeUpdate();
			
			if(effectedRow > 0) {
				System.out.println("Update Success");
			}else {
				System.out.println("Update Fail");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

}
