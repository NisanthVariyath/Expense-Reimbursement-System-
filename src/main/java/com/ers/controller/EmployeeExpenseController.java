package com.ers.controller;

import java.io.IOException;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ers.dao.EmployeeDAO;
import com.ers.dao.ExpenseDAO;
import com.ers.model.Employee;
import com.ers.model.Expense;
import com.ers.service.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeExpenseController {
	
	
	final static Logger loggy = Logger.getLogger(EmployeeExpenseController.class);
	//static LoginService villServ = new LoginService(new EmployeeDAO());

	public static String loadExpensesById(HttpServletRequest request, HttpServletResponse response) {
		ExpenseDAO expImpl = new ExpenseDAO();
		TreeSet<Expense> lExp = new TreeSet<Expense>();
		Employee emp = (Employee)request.getSession().getAttribute("currentVill");
		
/////////////////////////////////////////////////////
//	Employee vill = villServ.getVillainVerify(request.getParameter("username"), request.getParameter("password"));
		
	/*	if(emp==null) {
			return "wrongcreds.change";
		}else {*/
		//////////////////////////////////////////////////////////////////////
		
		int emp_id = emp.getErs_user_id();
		//System.out.println("BLOB."+emp.);
		lExp = expImpl.selectExpensesByEmployeeId(emp_id);
		try {
			response.getWriter().write(new ObjectMapper().writeValueAsString(lExp));
		} catch (JsonProcessingException e) {
			loggy.error(e);
		} catch (IOException e) {
			loggy.error(e);
		}
		return "/Project1-ERS/html/EmployeeHome.html";
	}
	}



