package com.ers.controller;

import java.io.IOException;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ers.dao.ExpenseDAO;
import com.ers.model.Employee;
import com.ers.model.Expense;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FinancialManagerController {
	
	
	final static Logger loggy = Logger.getLogger(FinancialManagerController.class);

	public static String loadExpenses(HttpServletRequest request, HttpServletResponse response) {
		ExpenseDAO expImpl = new ExpenseDAO();
		TreeSet<Expense> lExp = new TreeSet<Expense>();
		lExp = expImpl.selectAllExpenses();
		try {
			response.getWriter().write(new ObjectMapper().writeValueAsString(lExp));
		} catch (JsonProcessingException e) {
			loggy.error(e);
		} catch (IOException e) {
			loggy.error(e);
		}
		return "/html/AdminHome.html";
	}
	
	public static String approveExpense(HttpServletRequest request, HttpServletResponse response) {
		int ticket_id = Integer.parseInt(request.getParameter("value"));
		ExpenseDAO expImpl = new ExpenseDAO();
		expImpl.approveExpense(ticket_id);
		return "/html/AdminHome.html";
	}

	public static String denyExpense(HttpServletRequest request, HttpServletResponse response) {
		int ticket_id = Integer.parseInt(request.getParameter("value"));
		ExpenseDAO expImpl = new ExpenseDAO();
		expImpl.denyExpense(ticket_id);
		return "/html/AdminHome.html";
	}
	
public static String showByStatus(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("STATUS:"+ request.getParameter("reimb_type"));//getParameter("reimb_type"));
		int status = Integer.parseInt(request.getParameter("reimb_type"));
		System.out.println("."+status+".");
		ExpenseDAO expImpl = new ExpenseDAO();
		TreeSet<Expense> lExp = new TreeSet<Expense>();
		if(status==4) 
			lExp = expImpl.selectAllExpenses();
		else
		    lExp = expImpl.selectExpensesByReimbursementStatus(status); 
		try {
			response.getWriter().write(new ObjectMapper().writeValueAsString(lExp));
		} catch (JsonProcessingException e) {
			loggy.error(e);
		} catch (IOException e) {
			loggy.error(e);
		}
		return "/html/AdminHome.html";
	}
	


}
