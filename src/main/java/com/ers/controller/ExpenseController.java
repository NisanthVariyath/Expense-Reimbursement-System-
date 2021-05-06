package com.ers.controller;

import java.sql.Blob;

import javax.servlet.http.HttpServletRequest;

import com.ers.dao.ExpenseDAO;
import com.ers.model.Employee;
import com.ers.model.Expense;

public class ExpenseController {
	
	public static String submitExpense(HttpServletRequest request) {
		Employee emp = (Employee)request.getSession().getAttribute("currentVill");
		int emp_id = emp.getErs_user_id();
		
		String firstname = emp.getUser_firstname();
		String lastname = emp.getUser_lastname();
		System.out.println("TYPE: ."+request.getParameter("type")+".");
		System.out.println("Amount: ."+request.getParameter("Amount")+".");
		System.out.println("Blob."+request.getParameter("file")+".");
		if((request.getParameter("Amount")=="") || (request.getParameter("type")==null))
		{
			return "/html/EmployeeHome.html";
		}
		else {
		int type = Integer.parseInt(request.getParameter("type"));
		double amount = Double.parseDouble(request.getParameter("Amount"));
		String desc = request.getParameter("Description");
		ExpenseDAO expImpl = new ExpenseDAO();
		int reimb_id = expImpl.generateReimbId();
		Blob b =null;  /// blob is NULL
		
		
		
		Expense exp = new Expense(reimb_id,amount,null,null,desc,b,emp_id,0,1,type);
		expImpl.insertExpense(exp, false);
		return "/html/EmployeeHome.html";
		}
	}

}
