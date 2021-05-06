package com.ers.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import com.ers.model.Expense;

public class ExpenseDAOTest {
	
	@Mock
	private ExpenseDAO fakeDao;
	//private CreatureCardService ccServ /* = new CreatureCardService(fakeDao) */;
	private Expense exp;
	private Expense exp2;
	TreeSet ts = new TreeSet();
	TreeSet ts1 = new TreeSet();
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this); //  will tell mackito to create an instance of ever field member marked by @Mock

		exp = new Expense(0,100.50,null,null,"@Cab",null,10,55,1,1);
		
		exp2= new Expense(1,100.50,null,null,"@Cab",null,20,55,1,1);
		
		ts.add(exp);
		ts1.add(exp);
		ts1.add(exp2);
	
		
		when( fakeDao.selectExpensesByEmployeeId(10)).thenReturn(ts);
		
		when( fakeDao.selectExpensesByReimbursementStatus(1)).thenReturn(ts);
	
		when( fakeDao.selectAllExpenses()).thenReturn(ts1);
		
		doNothing().when(fakeDao).approveExpense(0);
		
	   doNothing().when(fakeDao).denyExpense(0);
		
		doNothing().when(fakeDao).insertExpense(exp2, false); 
		
	}	
	



	@Test
	public void getExpenseByAuthorIdSuccess() {
		assertEquals(ts, fakeDao.selectExpensesByEmployeeId(10),"Successfully returned TreeSet<Expense>  when author id given");
	}
	
	@Test
	public void getExpenseByReimbStatusIdSuccess() {
		assertEquals(ts, fakeDao.selectExpensesByReimbursementStatus(1),"Successfully returned TreeSet<Expense>  when status id given");
	}
	
	@Test
	public void getSelectAllExpenseSuccess() {
		assertEquals(ts1,fakeDao.selectAllExpenses(),"Successfully returned TreeSet<Expense> ");
	}
	
	  @Test 
	  public void getExpenseByAuthorIdFailure() {
	  assertThrows(NullPointerException.class, ()->fakeDao.selectExpensesByEmployeeId(0)); }
	  
	  
	
	
//  @Test public void insertCardSuccess() {
//	  assertEquals("Expense added",fakeDao.insertExpense(exp2,false),"Expense Inserted");
//	  }
	  
	 


	 
	



	 
	
	
	
	

}
