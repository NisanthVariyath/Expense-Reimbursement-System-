package com.ers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.ers.dao.interfaces.EmployeeDAOInterface;
import com.ers.model.Employee;
import com.ers.model.PHash;

public class EmployeeDAO implements EmployeeDAOInterface {
	
	private static Logger log = Logger.getLogger(EmployeeDAO.class);
	
	@Override
	public Employee selectEmployeeByUsername(String user_name) {
		Employee emp = null;
		try {
			
			
			Connection conn = JDBCConnector.getConn();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ERS_USERS WHERE ERS_USERNAME=?");
			ps.setString(1, user_name);
			ResultSet rs = ps.executeQuery();
			
		       int ers_user_id;
			   String ers_username;
			   String tHash_password;
			   String user_firstname;
			   String user_lastname;
			   String  user_email;
		       int user_role_id;
			
			
			
			
			
			if (rs.next()) {
				
			        ers_user_id = rs.getInt("ERS_USER_ID");
				    ers_username = rs.getString("ERS_USERNAME");
				    tHash_password = rs.getString("ERS_PASSWORD");
				    user_firstname = rs.getString("USER_FIRST_NAME");
				    user_lastname = rs.getString("USER_LAST_NAME");
				    user_email  = rs.getString("USER_EMAIL");
			        user_role_id = rs.getInt("USER_ROLE_ID");
			        
			        PHash nPHash_password =new PHash(tHash_password);
			      
			        
			        log.trace( "Single record returned from ERS_USERS table.");
			        
				
			        emp = new Employee(ers_user_id,ers_username,nPHash_password,user_firstname,user_lastname,user_email,user_role_id);
				
				
				
				
			}else {
		    	log.info("Attempt to retreive record from ERS_USERS table failed");
		    }
			
		}catch (SQLException e){
		 	log.warn("Error while accessing  ERS_USERS table in database", e);
	  	  }
	      
		return emp;
	}

	/////////////////////////////////////////////////////////////////////////////////
	@Override
	public void insertEmployee(Employee emp) {
		 try {
			 System.out.println("inside Insert");
		  	    Connection conn = JDBCConnector.getConn();
		  	    PreparedStatement ps = conn.prepareStatement("INSERT INTO ERS_USERS VALUES (?, ?, ?, ?, ? ,?, ?)");
		  	    System.out.println(emp);
		  	    ps.setInt(1, emp.getErs_user_id()); // to be generated
		  	    ps.setString(2, emp.getErs_username());
		  	    ps.setString(3, emp.getErs_password().getHash());
		  	    ps.setString(4, emp.getUser_firstname());
		  	    ps.setString(5, emp.getUser_lastname());
		  	    ps.setString(6, emp.getUser_email());
		  	    ps.setInt(7, emp.getUser_role_id());  // to be fetched from ERS_USER_ROLES
		  	    ps.execute();
		  	    
		  	  log.trace("Record successfully updated in ERS_USERS table in database");
		  	    
		     } catch (SQLException e){
				log.warn("Error while inserting into ERS_USERS table in database", e);
			  }
		
	}
//////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public TreeSet<Employee> selectAllEmployees() {
		
		TreeSet<Employee> returnThis = new TreeSet<Employee>();

		  
		  
		  try {
			    Connection conn = JDBCConnector.getConn();
			    PreparedStatement st = conn.prepareStatement("SELECT * FROM ERS_USERS");
			    ResultSet rs = st.executeQuery();
			   
			    Employee neoEmployee = new Employee();
		           int ers_user_id;
				   String ers_username;
				   String tHash_password;
				   String user_firstname;
				   String user_lastname;
				   String  user_email;
			       int user_role_id;
		    	 
		    	
     
		    	while (rs.next()) {
		    		   
		    		
		    		    ers_user_id = rs.getInt("ERS_USER_ID");
					    ers_username = rs.getString("ERS_USERNAME");
					    tHash_password = rs.getString("ERS_PASSWORD");
					    user_firstname = rs.getString("USER_FIRST_NAME");
					    user_lastname = rs.getString("USER_LAST_NAME");
					    user_email  = rs.getString("USER_EMAIL");
				        user_role_id = rs.getInt("USER_ROLE_ID");
				        
				        PHash nPHash_password = new PHash(tHash_password);
				        
				        System.out.println("Password tHash_password = "+tHash_password );
				        System.out.println("Password nPHash_password = "+nPHash_password );
			    	
			    	neoEmployee = new Employee(ers_user_id, ers_username, nPHash_password,user_firstname,
			    			                   user_lastname,user_email,user_role_id);
			    	
			    	
			          returnThis.add( neoEmployee);
			      
			    	
			    	
			    }
			  } catch (SQLException e){
			 	log.warn("Error while accessing ERS_USERS table in database", e);
		  	  }
		      
		      log.trace(returnThis.size() + " records returned from ERS_USERS table.");
			  return returnThis;
	}

///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@Override
	public void updateEmployee(Employee emp) {
		
		  try {
		  	    Connection conn = JDBCConnector.getConn();
		  	    PreparedStatement ps = conn.prepareStatement("UPDATE ERS_USERS SET ERS_USERNAME=?, ERS_PASSWORD=?, USER_FIRST_NAME=?, USER_LAST_NAME=?, USER_EMAIL=?, USER_ROLE_ID =?  WHERE ERS_USER_ID=?");
		  	   
		  	    
		  	    ps.setString(1, emp.getErs_username());
		  	    ps.setString(2, emp.getErs_password().getHash());
		  	    ps.setString(3, emp.getUser_firstname());
		  	    ps.setString(4, emp.getUser_lastname());
		  	    ps.setString(5, emp.getUser_email());
		  	    ps.setInt(6, emp.getUser_role_id());  // to be fetched from ERS_USER_ROLES
		  	    ps.setInt(7, emp.getErs_user_id()); // to be fetched from user input
		  	    ps.execute();
		  	    log.trace("Record successfully updated in ERS_USERS table in database");
			  } catch (SQLException e){
				 	log.warn("Error while updating ERS_USERS table in database", e);
			  } 
		
			
		
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////	

	@Override
	public void deleteEmployee(Employee emp) {


		 try {
		  	    Connection conn = JDBCConnector.getConn();
		  	    PreparedStatement ps = conn.prepareStatement("DELETE FROM ERS_USERS WHERE ERS_USER_ID=?");
		  	    ps.setInt(1, emp.getErs_user_id()); // to be fetched from user input
		  	    ps.execute();
		  	    log.trace("Record successfully deleted from ERS_USERS table in database");
			  } catch (SQLException e){
				 	log.warn("Error while removing from ERS_USERS table in database", e);
			  }      
		
		
		
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public int generateErsUserId() {
		int returnThis =0;
		try {
		    Connection conn = JDBCConnector.getConn();
		    PreparedStatement st = conn.prepareStatement("SELECT MAX(ERS_USER_ID) FROM ERS_USERS");
		    ResultSet rs = st.executeQuery();
		    
		    while(rs.next())
		    {
		    	returnThis = rs.getInt(1);
		    }
		   
		    returnThis +=10;
		
		 } catch (SQLException e){
			 	log.warn("Error while accessing ERS_USERS table in database", e);
		  	  }
		      
		      log.trace(" max ERS_USER_ID returned from ERS_USERS table.");
			  
		      return returnThis;
		
		
		
	}
	
	
	
	

}
