package com.ers.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.controller.EmployeeExpenseController;
import com.ers.controller.FinancialManagerController;
import com.ers.controller.RegisterController;
import com.ers.controller.LoginController;
import com.ers.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class JSONDispatcherServlet {
	
	
public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		switch(req.getRequestURI()) {
		
		 case "/Project1-ERS/getsessionvill.json":
			 LoginController.getSessionVill(req, res);
			 System.out.println("inside json");
			 break;
			 
		 case "/Project1-ERS/loadExpense.json":
			 FinancialManagerController.loadExpenses(req, res);
			 break;
			 
		 case "/Project1-ERS/LoadExpensesById.json":
			 EmployeeExpenseController.loadExpensesById(req, res);
			 break;
			 
		 case "/Project1-ERS/ShowByStatus.json":
			 FinancialManagerController.showByStatus(req, res);
			 break; 
			 
		 case "/Project1-ERS/RegisterERS.json":
			 RegisterController.RegisterEmp(req);
			 break;
			 
		default:
			res.getWriter().write(new ObjectMapper().writeValueAsString(new Employee()));
		
		}
	}
}
