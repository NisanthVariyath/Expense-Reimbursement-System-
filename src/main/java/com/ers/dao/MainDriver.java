package com.ers.dao;

import java.util.LinkedList;
import java.util.TreeSet;

import com.ers.model.Employee;
import com.ers.model.Expense;
import com.ers.model.PHash;

public class MainDriver {

	public static void main(String[] args) {
		
		EmployeeDAO empDao =new EmployeeDAO();
	   ExpenseDAO expDAO = new ExpenseDAO();
		TreeSet<Employee> ts = new TreeSet<Employee>();
		TreeSet<Expense> ts1 = new TreeSet<Expense>();
		ts = empDao.selectAllEmployees();
		
		ts1 = expDAO.selectAllExpenses();
		
		int max = expDAO.generateReimbId();
		
    System.out.println("-------------------------------------------------------------");
	System.out.println("max : " + max);
		
	System.out.println("-------------------------------------------------------------");
		/*
		 for(Employee e: ts) {
		     System.out.println(e);
		     
		     boolean b = e.checkPassword("password");
		     System.out.println("Boolean Password check :"+ b);
		 }
		 */
		for(Expense e: ts1)
		System.out.println(e);
		
	//////////////////////////VIEW BY EMP NAME/////////////////////////////////
		
	System.out.println("-------------------------------------------------------------");
	
	/*
	 * Employee emp =new Employee(); emp= empDao.selectEmployeeByUsername("nash");
	 * System.out.println(emp);
	 */
	 
	 ////////////////////////////////////////////INSERT//////////////////////////////////
		/*
		 * PHash ph= new PHash(); ph.setPassword("password");; Employee emp =new
		 * Employee(120,"jack",ph,"Jack","Will","jack.will@abcdmail.com",2);
		 * empDao.insertEmployee(emp); System.out.println("successful");
		 */
		 
		 

	}

}
