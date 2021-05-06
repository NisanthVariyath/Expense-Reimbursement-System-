package com.ers.controller;
import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  



public class Logout extends HttpServlet {
	
	
        public static String   closeSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
            //response.setContentType("text/html");  
           PrintWriter out=response.getWriter();  
              
           // request.getRequestDispatcher("link.html").include(request, response);  
              
            HttpSession session=request.getSession(); 
           
            session.removeAttribute("username");
            session.invalidate();  
              
            out.print("You are successfully logged out!");  
              
            out.close(); 
            
            return "/html/Logout.html";
    }  
}  