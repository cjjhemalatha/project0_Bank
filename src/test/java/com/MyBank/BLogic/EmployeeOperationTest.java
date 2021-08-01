package com.MyBank.BLogic;

import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

import model.PojoClass;

public class EmployeeOperationTest {
	EmployeeOperation empTest;
	PojoClass user1;
	@Before
	public void setUp() {
		empTest=new EmployeeOperation();
		user1=new PojoClass();
		user1.setId("2162");
		user1.setPassword("Meera99");
		user1.setFirstName("Meera");
		user1.setInitialAmount(6000);
	}
	@Test
	public void testUpdateUser() {
		assertEquals(-1,empTest.updateUser(1300,2,user1));
	}
	@Test
	public void testUserLoginValidation() {
		assertEquals(null,empTest.userLoginValidation("user","2162", user1));
	}
	

}
