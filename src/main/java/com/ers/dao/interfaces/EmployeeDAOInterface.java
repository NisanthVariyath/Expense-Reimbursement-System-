package com.ers.dao.interfaces;

import java.util.TreeSet;

import com.ers.model.Employee;

public interface EmployeeDAOInterface {
	    // CREATE
		public void insertEmployee(Employee emp);

		// READ
		public Employee  selectEmployeeByUsername(String username);

		public TreeSet<Employee> selectAllEmployees();

		// UPDATE
		public void updateEmployee(Employee emp);

		// DELETE
		public void deleteEmployee(Employee emp);
		
		public int generateErsUserId();

}
