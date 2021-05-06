package com.ers.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import com.ers.model.Employee;
import com.ers.model.PHash;

public class LoginServiceTest {
	
	
private LoginService ls;
private Employee emp;
PHash ph =new PHash();

	@BeforeAll
		public static void setUpBeforeClass() {
		System.out.println("-----------Before Class-----------------");
	}
	
	@AfterAll
		public static void tearDownAfterClass() {
		System.out.println("-----------After Class---------------------");
	}
	
	@BeforeEach
		public void setUp() {
		System.out.println("-----------Before Method ----------------------");
		ls = new LoginService();
		ph.setPassword("password");
		emp = new Employee(100,"nash",ph,"Nisanth","Variyath","nisanth.mulayathvariyath@revature.net",1);
	}
	
	@AfterEach
		public void tearDown() {
		System.out.println("-------------------After Method ----------------");
	}
	
   @Test
    public void loginServiceTestSuccessful() {
	   System.out.println("in  test successful ");
	   assertEquals(emp,ls.getVillainVerify("nash", "password"),"successful");
   }
   
   @Test
   public void loginServiceTestFail() {
	  
	   assertEquals(null,ls.getVillainVerify("nash", "pass"),"Fail");
   }
   
   
 //  @Test(expected = IllegalArgumentException.class)
   
	/*
	 * @Test
	 * 
	 * @Order(3) public void divisionExceptionTest() {
	 * 
	 * Exception e = assertThrows(IllegalArgumentException.class, () ->
	 * calc.division(1,0)); // e.printStackTrace();
	 * System.out.println(e.getMessage()); System.out.println("Division Exception");
	 * 
	 * }
	 */
	

}
