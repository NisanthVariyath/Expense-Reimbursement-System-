package com.ers.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ers.controller.LoginController;
import com.ers.controller.EmployeeExpenseController;
import com.ers.controller.ExpenseController;
import com.ers.controller.FinancialManagerController;

import com.ers.controller.RegisterController;

public class RequestDispatcher {
	


	public static String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		
		System.out.println("Inside Req Dispacther");
		switch (req.getRequestURI()) {
		case "/Project1-ERS/login.change":
			System.out.println("in Login.change dispatcher");
			return LoginController.login(req);

		case "/Project1-ERS/AdminHome.change":
			System.out.println("in AdminHome change dispatcher");
			return FinancialManagerController.loadExpenses(req, res);

		case "/Project1-ERS/EmployeeHome.change":
			System.out.println("in EmployeeHome change dispatcher");
			return EmployeeExpenseController.loadExpensesById(req, res);

		case "/Project1-ERS/NewExpense.change":
			System.out.println("in new Expense change dispacher ");
			return ExpenseController.submitExpense(req);

		case "/Project1-ERS/ApproveExpense.change":
			System.out.println("in new Approve Expense change dispacher ");
			return FinancialManagerController.approveExpense(req, res);

		case "/Project1-ERS/DenyExpense.change":
			System.out.println("in new Deny Expense change dispacher ");
			return FinancialManagerController.denyExpense(req, res);

		case "/Project1-ERS/ShowExpenseBYStatus.change":
			System.out.println("in new ShowExpenseBYStatus change dispacher ");
			return FinancialManagerController.showByStatus(req, res);

		case "/Project1-ERS/RegisterERS.change":
			System.out.println("in new RegisterERS  dispacher ");
			return RegisterController.RegisterEmp(req);

		case "/Project1-ERS/Logout.change":
			System.out.println("in new Logout change dispacher ");
			HttpSession session = req.getSession();
			session.removeAttribute("username");
		//	isLogout = false;
			session.invalidate();

			return "/html/Logout.html";
		// return Logout.closeSession(req, res);

		default:
			System.out.println("in default");
			return "html/unsuccessfullogin.html";
		}
		
	}
}
