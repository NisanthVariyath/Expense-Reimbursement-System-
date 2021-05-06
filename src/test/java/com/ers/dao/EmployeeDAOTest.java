package com.ers.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ers.model.Employee;
import com.ers.model.Expense;
import com.ers.model.PHash;

public class EmployeeDAOTest {
	
	
	@Mock
	private EmployeeDAO fakeDao;
	//private CreatureCardService ccServ /* = new CreatureCardService(fakeDao) */;
	private Employee emp;
	private Employee emp2;
	TreeSet ts = new TreeSet();
	TreeSet ts1 = new TreeSet();
	PHash ph = new PHash();
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this); //  will tell mackito to create an instance of ever field member marked by @Mock
		//ccServ = new CreatureCardService(fakeDao);
		//exp2 = new Expense();
		emp = new Employee(1,"donald",ph,"Donald","Duck","donald.duck@fakemail.com",2);
		
		emp2=new Employee(2,"donald2",ph,"Donald2","Duck2","donald2.duck@fakemail.com",2);
		
		ts.add(emp);
		ts1.add(emp);
		ts1.add(emp2);
	
		
		when( fakeDao.selectEmployeeByUsername("donald")).thenReturn(emp);

		
	
		when( fakeDao.selectAllEmployees()).thenReturn(ts1);
		
	//	doNothing().when(fakeDao).approveExpense(0);
		
	  // doNothing().when(fakeDao).denyExpense(0);
		
	//	doNothing().when(fakeDao).insertExpense(exp2, false); // how to mock void return type methods
		
	}	
	



	@Test
	public void getExpenseByUsesrnameSuccess() {
		assertEquals(emp, fakeDao.selectEmployeeByUsername("donald"),"Successfully returned EMP  when user name given");
	}
	
	
	
	@Test
	public void getSelectAllEmployeeSuccess() {
		assertEquals(ts1,fakeDao.selectAllEmployees(),"Successfully returned TreeSet<Employee> ");//.getCreatureCardByName("Jace").getName(), "Creature card service get name success");
	}
	
	/*
	 * @Test public void getExpenseByAuthorIdFailure() {
	 * assertThrows(NullPointerException.class,
	 * ()->fakeDao.selectExpensesByEmployeeId(0)); }
	 */
	  
	


	
	
	
	
	
	
	
	
	
	
	
	
	

}
