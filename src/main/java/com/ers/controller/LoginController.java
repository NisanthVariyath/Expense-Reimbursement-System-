package com.ers.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.dao.EmployeeDAO;
import com.ers.model.Employee;
import com.ers.service.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginController {
	
	static LoginService villServ = new LoginService(new EmployeeDAO());
	
	public static String login(HttpServletRequest req) {
		System.out.println("in Login controller login");
		if(!req.getMethod().equals("POST")) {
			return "html/Login.html";
		}
		
		Employee vill = villServ.getVillainVerify(req.getParameter("username"), req.getParameter("password"));
		
		if(vill== null) {
			return "wrongcreds.change";
		}else {
			req.getSession().setAttribute("currentVill", vill);
			
			
			
			if(vill.getUser_role_id()==1) {  // code added
				return "html/AdminHome.html";
			}
			else
				return "html/EmployeeHome.html";
			//return "html/home.html";
		}
	}
	
	public static void getSessionVill(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		Employee vill = (Employee)req.getSession().getAttribute("currentVill");
		res.getWriter().write(new ObjectMapper().writeValueAsString(vill));
	}

}
