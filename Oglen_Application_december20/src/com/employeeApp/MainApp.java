package com.employeeApp;

import java.sql.SQLException;
import java.util.Scanner;

import com.employeeApp.entity.Employee;
import com.employeeApp.repository.EmployeeRepository;
import com.employeeApp.repository.IEmployeeRepository;
import com.employeeApp.util.DBConnection;

public class MainApp {

	public static void main(String[] args) {
		// veritabanı tablosu oluşturalım
		//name String salary int age int
		//Employee classını yazalım veritabanı tablosuna göre

		Scanner scanner = new Scanner(System.in);
		IEmployeeRepository empRep = new EmployeeRepository();
		
		do {
			System.out.println("1. Create Employee\n" + "2. List All Employees\n" + "3. Get Employee By Id \n"
                + "4. Update Employee \n" + "5. Delete Employee\n" + "6. Exit\n");
			
			
			System.out.println("Choose a number");
			int value = scanner.nextInt();
			
			switch (value) {
			case 1:
				System.out.println("Name: ");
				String name = scanner.next();
				System.out.println("Salary: ");
				int salary = scanner.nextInt();
				System.out.println("Age: ");
				int age = scanner.nextInt();
				
				empRep.createEmployee(new Employee(name, salary, age));
				break;
			case 2:
				empRep.getAllEmployees();
				break;
			case 3:
				System.out.println("ID: ");
				int employeeId = scanner.nextInt();
				empRep.getEmployeeById(employeeId);
				break;
			case 4:
				System.out.println("ID: ");
				employeeId = scanner.nextInt();
				System.out.println("Name: ");
				name = scanner.next();
				empRep.updateEmployeeById(employeeId, name);
				break;
			case 5:
				
				break;
			case 6:
				try {
					DBConnection.connect().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Exited");
				break;

			default:
				System.out.println("Please choose correct number");
				break;
			}
			
			
			
		} while (true);
		
		
		
		
	}

}
