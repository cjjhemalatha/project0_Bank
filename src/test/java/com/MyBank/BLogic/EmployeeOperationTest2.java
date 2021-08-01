package com.MyBank.BLogic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.EmployeePojoModel;

public class EmployeeOperationTest2 {
	EmployeeOperation empTest;
	EmployeePojoModel empTestSample;
	@Before
	public void setUp() throws Exception {
		empTest= new EmployeeOperation();
		empTestSample= new EmployeePojoModel();
		empTestSample.setEmpID("1001");
		empTestSample.setEmpName("Jeya");
		empTestSample.setEmpPassword("Jeya1001");
	}

	@Test
	public void testEmployeeLoginValidation() {
		assertEquals(1,empTest.employeeLoginValidation("1001", empTestSample));
		
	}

}
