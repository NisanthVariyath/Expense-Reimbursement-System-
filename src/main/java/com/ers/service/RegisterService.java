package com.ers.service;

import com.ers.dao.EmployeeDAO;
import com.ers.model.Employee;
import com.ers.model.PHash;

public class RegisterService {
	
	

	private EmployeeDAO svDao;
	
	//private ExpenseDAO  expDao;
	
	public RegisterService() {
		// TODO Auto-generated constructor stub
	}

	public RegisterService(EmployeeDAO svDao) {
		super();
		this.svDao = svDao;
	}
	
	public void register(String username, String email, String firstname, String lastname, int userRoleId ,String passwd) {
		PHash ph =new PHash();
		ph.setPassword(passwd);
		
		 Employee emp =new Employee(svDao.generateErsUserId(),username,ph,firstname, lastname,email,userRoleId);
		 
	
		 svDao.insertEmployee(emp);
	
		
	//	return null;
	}
	
	

}
