package com.ers.dao;

import java.io.File;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.ers.dao.interfaces.ExpenseDAOInterface;
import com.ers.enums.ReimbursementStatus;
import com.ers.model.Employee;
import com.ers.model.Expense;
import com.ers.model.PHash;



public class ExpenseDAO implements ExpenseDAOInterface {
	private static Logger log = Logger.getLogger(EmployeeDAO.class);

	@Override
	public void insertExpense(Expense exp, boolean isBlob) {
	
		
		 try {
			 System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		  	    System.out.println(exp);
		  	    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		  	    if(isBlob==false) // without BLOB - reimb_receipt
		  	    {
		  	       Connection conn = JDBCConnector.getConn();
		  		   String sql = "{? = call add_reimbursement_request2(?,?,?,?,?,?)}";
		  		   CallableStatement cs = conn.prepareCall(sql);
		  		   
		  		   System.out.println(" inside insert --> ");
		  		   cs.registerOutParameter(1, Types.VARCHAR);
		  		   cs.setInt(2, exp.getReimb_id());
		  		   cs.setDouble(3, exp.getReimb_amount());
		  		   cs.setString(4, exp.getReimb_description());
		  		   cs.setInt(5,exp.getReimb_author());
		  		   cs.setInt(6,exp.getReimb_status_id());
		  		   cs.setInt(7,exp.getReimb_type_id());
		  		 System.out.println(" inside insert2 --> ");	
		  		   cs.execute();
		  		 System.out.println(" inside insert3 after execute --> ");
		  		   //cs.close();
		  		   log.trace("Record successfully updated in ERS_REIMBURSEMENT table in database using SQL-function add_reimbursement_request2()");
		  	    }else {
		  	         // with  BLOB - reimb_receipt
		  	    	Connection conn = JDBCConnector.getConn();
			  		   String sql = "{? = call add_reimbursement_request(?,?,?,?,?,?,?)}";
			  		   CallableStatement cs = conn.prepareCall(sql);
			  		   cs.registerOutParameter(1, Types.VARCHAR);
			  		   cs.setInt(2, exp.getReimb_id());
			  		   cs.setDouble(3, exp.getReimb_amount());
			  		   cs.setString(4, exp.getReimb_description());
			  		   cs.setBlob(5, exp.getReimb_recipt());
			  		   cs.setInt(6,exp.getReimb_author());
			  		   cs.setInt(7,exp.getReimb_status_id());
			  		   cs.setInt(8,exp.getReimb_type_id());
								
			  		   cs.execute();
			  		   //cs.close();
			  		   log.trace("Record successfully updated in ERS_REIMBURSEMENT table in database using SQL-function add_reimbursement_request2()");
		  	    	
		  	    }
		  	    
		     } catch (SQLException e){
				   log.warn("Error while inserting into ERS_REIMBURSEMENT table in database using SQL-function add_reimbursement_request2()", e);
			  }
		
	
	}

	
/////////////////////////////////////////////////////////////////////////////////////
	@Override
	public TreeSet<Expense> selectExpensesByEmployeeId(int reimb_author) {
	
		TreeSet<Expense> returnThis = new TreeSet<Expense>();
		Expense exp = null;
		 try {
			    Connection conn = JDBCConnector.getConn();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_AUTHOR=?");
				ps.setInt(1, reimb_author);
				ResultSet rs = ps.executeQuery();
				
			       int reimb_id;
				   Double reimb_amount;
				   Timestamp reimb_submitted;
				   Timestamp reimb_resolved;
				   String reimb_description;
				   Blob  reimb_receipt;
			       int reimb_author_temp;
			       int reimb_resolver;
			       int reimb_status_id;
			       int reimb_type_id;
			       
			       while (rs.next()) {
			    	   reimb_id = rs.getInt("REIMB_ID");
			    	   reimb_amount = rs.getDouble("REIMB_AMOUNT");
			    	   reimb_submitted = rs.getTimestamp("REIMB_SUBMITTED");
			    	   reimb_resolved =  rs.getTimestamp("REIMB_RESOLVED");
			    	   reimb_description = rs.getString("REIMB_DESCRIPTION");
			    	   reimb_receipt  = rs.getBlob("REIMB_RECIPT");  // Getting file as blob - blob or BYTEA
			    	   reimb_author_temp = rs.getInt("REIMB_AUTHOR");
			    	   reimb_resolver = rs.getInt("REIMB_RESOLVER");
			    	   reimb_status_id = rs.getInt("REIMB_STATUS_ID");
			    	   reimb_type_id = rs.getInt("REIMB_TYPE_ID");
				        log.trace( "Single record returned from ERS_REIMBURSEMENT table :" + reimb_id);
				        
					
				        exp = new Expense(reimb_id,reimb_amount,reimb_submitted,reimb_resolved,
				        		reimb_description,reimb_receipt,reimb_author_temp,reimb_resolver,reimb_status_id,reimb_type_id);
					
				        returnThis.add(exp);
					
					
			       }
			       
						
		
		
		 } catch (SQLException e){
			 	log.warn("Error while accessing ERS_REIMBURSEMENT table in database", e);
		  	  }
		      
		      log.trace(returnThis.size() + " records returned from ERS_REIMBURSEMENT table.");
			  return returnThis;
	}
	
/////////////////////////////////////////////////////////////////////////////////////

	@Override
	public TreeSet<Expense> selectAllExpenses() {
	
		TreeSet<Expense> returnThis = new TreeSet<Expense>();
		Expense exp = null;
		
		 try {
			    Connection conn = JDBCConnector.getConn();
			  //  PreparedStatement st = conn.prepareStatement("SELECT * FROM ERS_REIMBURSEMENT");
			    PreparedStatement st = conn.prepareStatement("select USER_FIRST_NAME,USER_LAST_NAME,reimb_id, reimb_amount,reimb_submitted,reimb_resolved,reimb_description,REIMB_RECIPT,reimb_author,reimb_resolver,reimb_status_id,reimb_type_id from ers_reimbursement,ers_users "+ 
		                                   "where ERS_REIMBURSEMENT.reimb_author = ERS_USERS.ers_user_id");
			    
			    ResultSet rs = st.executeQuery();
			    
			       int reimb_id;
			       String firstname;
			       String lastname;
				   Double reimb_amount;
				   Timestamp reimb_submitted;
				   Timestamp reimb_resolved;
				   String reimb_description;
				   Blob  reimb_receipt;
			       int reimb_author_temp;
			       int reimb_resolver;
			       int reimb_status_id;
			       int reimb_type_id;
			       
			       while (rs.next()) {
			    	   reimb_id = rs.getInt("REIMB_ID");
			    	   firstname =rs.getString("USER_FIRST_NAME");
			    	   lastname =rs.getString("USER_LAST_NAME");
			    	   reimb_amount = rs.getDouble("REIMB_AMOUNT");
			    	   reimb_submitted = rs.getTimestamp("REIMB_SUBMITTED");
			    	   reimb_resolved =  rs.getTimestamp("REIMB_RESOLVED");
			    	   reimb_description = rs.getString("REIMB_DESCRIPTION");
			    	   reimb_receipt  = rs.getBlob("REIMB_RECIPT");  // Getting file as blob - blob or BYTEA
			    	   reimb_author_temp = rs.getInt("REIMB_AUTHOR");
			    	   reimb_resolver = rs.getInt("REIMB_RESOLVER");
			    	   reimb_status_id = rs.getInt("REIMB_STATUS_ID");
			    	   reimb_type_id = rs.getInt("REIMB_TYPE_ID");
				        log.trace( "Single record returned from ERS_REIMBURSEMENT table :" + reimb_id);
				        
					
				        exp = new Expense(reimb_id,firstname,lastname,reimb_amount,reimb_submitted,reimb_resolved,
				        		reimb_description,reimb_receipt,reimb_author_temp,reimb_resolver,reimb_status_id,reimb_type_id);
					
				        returnThis.add(exp);
					
					
			       }
			    
			    
		
		 } catch (SQLException e){
			 	log.warn("Error while accessing ERS_REIMBURSEMENT table in database", e);
		  	  }
		      
		      log.trace(returnThis.size() + " records returned from ERS_REIMBURSEMENT table.");
			  
		      return returnThis;
			
	}
	
/////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void updateExpense(Expense exp) {

		try {
	  	    Connection conn = JDBCConnector.getConn();
	  	    PreparedStatement ps = conn.prepareStatement("UPDATE ERS_REIMBURSEMENT SET REIMB_AMOUNT=?, REIMB_SUBMITTED=?, REIMB_RESOLVED=?, REIMB_DESCRIPTION=?,"
	  	    		+ "REIMB_RECIPT=?, REIMB_AUTHOR =?,REIMB_RESOLVER=?, REIMB_STATUS_ID=?,REIMB_TYPE_ID=? WHERE REIMB_ID=?");
	  	   
	  	    ps.setDouble(1, exp.getReimb_amount());
	  	    ps.setTimestamp(2, exp.getReimb_submitted());
	  	    ps.setTimestamp(3, exp.getReimb_resolved());
	  	    ps.setString(4, exp.getReimb_description());
	  	    ps.setBlob(5, exp.getReimb_recipt());
	  	    ps.setInt(6, exp.getReimb_author());
	  	    ps.setInt(7, exp.getReimb_resolver());
	  	    ps.setInt(8, exp.getReimb_status_id());
	  	    ps.setInt(9, exp.getReimb_type_id());
	  	    ps.setInt(10, exp.getReimb_id());
	  	    
	  	    ps.execute();
	  	    
	  	    
	  	    
	  	    log.trace("Record successfully updated in ERS_REIMBURSEMENT table in database");
		  } catch (SQLException e){
			 	log.warn("Error while updating ERS_REIMBURSEMENT table in database", e);
		  } 
		
		
		
	}
	
/////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void approveExpense(int reimb_id) {
		
		try {
	  	    Connection conn = JDBCConnector.getConn();
	  	    PreparedStatement ps = conn.prepareStatement("UPDATE ERS_REIMBURSEMENT SET REIMB_RESOLVED=?, REIMB_STATUS_ID=? WHERE REIMB_ID=?");
	  	   
	  	    ps.setTimestamp(1,getCurrentTimeStamp());
	  	    ps.setInt(2, ReimbursementStatus.APPROVED.getValue());
	  	    ps.setInt(3, reimb_id);
	  	    ps.execute();
	  	    
	  	  	
		    log.trace("Record successfully updated(approve EXpense) in ERS_REIMBURSEMENT table in database");
			  } catch (SQLException e){
				 	log.warn("Error while updating(approve EXpense) ERS_REIMBURSEMENT table in database", e);
			  } 
		
		
		
		
	}
	
/////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void denyExpense(int reimb_id) {
		
		
		try {
	  	    Connection conn = JDBCConnector.getConn();
	  	    PreparedStatement ps = conn.prepareStatement("UPDATE ERS_REIMBURSEMENT SET REIMB_RESOLVED=?, REIMB_STATUS_ID=? WHERE REIMB_ID=?");
	  	   
	  	    ps.setTimestamp(1,getCurrentTimeStamp());
	  	    ps.setInt(2, ReimbursementStatus.DENIED.getValue());
	  	    ps.setInt(3, reimb_id);
	  	    ps.execute();
	  	    
	  	  	
		    log.trace("Record successfully updated(approve EXpense) in ERS_REIMBURSEMENT table in database");
			  } catch (SQLException e){
				 	log.warn("Error while updating(approve EXpense) ERS_REIMBURSEMENT table in database", e);
			  } 
		
		
	}
/////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void deleteExpense(Expense exp) {
		
				
		
		
		
		
		
		
		
	}
///////////////////////////////////////////////////////////////////////////////
	
	@Override
	public TreeSet<Expense> selectExpensesByReimbursementStatus(int reimb_status_id) {
		
		TreeSet<Expense> returnThis = new TreeSet<Expense>();
		Expense exp = null;
		 try {
			    Connection conn = JDBCConnector.getConn();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM ERS_REIMBURSEMENT,ERS_USERS WHERE REIMB_STATUS_ID=? and ERS_REIMBURSEMENT.reimb_author = ERS_USERS.ers_user_id");
				ps.setInt(1, reimb_status_id);
				ResultSet rs = ps.executeQuery();
				
			       int reimb_id;
			       String firstname;
			       String lastname;
				   Double reimb_amount;
				   Timestamp reimb_submitted;
				   Timestamp reimb_resolved;
				   String reimb_description;
				   Blob  reimb_receipt;
			       int reimb_author_temp;
			       int reimb_resolver;
			       int reimb_status_id_temp;
			       int reimb_type_id;
			       
			       while (rs.next()) {
			    	   reimb_id = rs.getInt("REIMB_ID");
			    	   firstname =rs.getString("USER_FIRST_NAME");
			    	   lastname =rs.getString("USER_LAST_NAME");
			    	   reimb_amount = rs.getDouble("REIMB_AMOUNT");
			    	   reimb_submitted = rs.getTimestamp("REIMB_SUBMITTED");
			    	   reimb_resolved =  rs.getTimestamp("REIMB_RESOLVED");
			    	   reimb_description = rs.getString("REIMB_DESCRIPTION");
			    	   reimb_receipt  = rs.getBlob("REIMB_RECIPT");  // Getting file as blob - blob or BYTEA
			    	   reimb_author_temp = rs.getInt("REIMB_AUTHOR");
			    	   reimb_resolver = rs.getInt("REIMB_RESOLVER");
			    	   reimb_status_id_temp = rs.getInt("REIMB_STATUS_ID");
			    	   reimb_type_id = rs.getInt("REIMB_TYPE_ID");
				        log.trace( "Single record returned from ERS_REIMBURSEMENT table :" + reimb_id);
				        
					
				        exp = new Expense(reimb_id,firstname,lastname,reimb_amount,reimb_submitted,reimb_resolved,
				        		reimb_description,reimb_receipt,reimb_author_temp,reimb_resolver,reimb_status_id_temp,reimb_type_id);
					
				        returnThis.add(exp);
					
					
			       }
			       
			
		 } catch (SQLException e){
			 	log.warn("Error while accessing ERS_REIMBURSEMENT table in database", e);
		  	  }
		      
		      log.trace(returnThis.size() + " records returned from ERS_REIMBURSEMENT table.");
		      
			  return returnThis;
		
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////

	public int generateReimbId() {
		int returnThis =0;
		try {
		    Connection conn = JDBCConnector.getConn();
		    PreparedStatement st = conn.prepareStatement("SELECT MAX(REIMB_ID) FROM ERS_REIMBURSEMENT");
		    ResultSet rs = st.executeQuery();
		    
		    while(rs.next())
		    {
		    	returnThis = rs.getInt(1);
		    }
		   
		    returnThis +=10;
		
		 } catch (SQLException e){
			 	log.warn("Error while accessing ERS_REIMBURSEMENT table in database", e);
		  	  }
		      
		      log.trace(" max reimb_id returned from ERS_REIMBURSEMENT table.");
			  
		      return returnThis;
		
		
		
	}
//////////////////////////////////////////////////////////////////////////////////////////////
	
	// TO GET CURRENT TIMESTASMP
	
	private static java.sql.Timestamp getCurrentTimeStamp() {

	    java.util.Date today = new java.util.Date();
	    return new java.sql.Timestamp(today.getTime());

	}


	
}
