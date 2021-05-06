package com.ers.service;

import java.util.TreeSet;

import com.ers.dao.EmployeeDAO;
import com.ers.dao.ExpenseDAO;
import com.ers.model.Employee;
import com.ers.model.Expense;

public class LoginService {
	
	

	private EmployeeDAO svDao = new EmployeeDAO(); ///////////////////////////////////////////////////
			
	//private ExpenseDAO  expDao;
	
	public LoginService() {
		// TODO Auto-generated constructor stub
	}

	public LoginService(EmployeeDAO svDao) {
		super();
		//this.svDao=new EmployeeDAO();
		
	this.svDao = svDao;
	}
	
	public Employee getVillainVerify(String username, String password) {
		
		 Employee vill = svDao.selectEmployeeByUsername(username);
		 if(vill != null) {
			 if(vill.checkPassword(password)) {
				 
			//	 TreeSet<Expense> exp =  expDao.selectAllExpenses();
				 
				 return vill;
			 }
		 }
		
		return null;
	}
	

}
