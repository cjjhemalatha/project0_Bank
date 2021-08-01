package com.MyBank.BLogic;

import org.junit.Test;
import junit.framework.TestCase;

public class ValidateNewUserDetailsTest extends TestCase {
	ValidateNewUserDetails test=new ValidateNewUserDetails();
	@Test
	public void testValidatePassword() {
		assertEquals(1,test.validatePassword("27Hello#"));
	}
	@Test
	public void testValidatePhoneNumber() {
		assertEquals(1,test.validatePhoneNumber("7654678976"));
	}
	@Test
	public void testValidateInitialBalance() {
		assertEquals(1,test.validateInitialBalance(5000));
	}
	@Test
	public void testValidateAdhar() {
		assertEquals(1,test.validateAdhar("8976545678"));
	}
	@Test
	public void testValidateInitialBalance2() {
		assertEquals(1,test.validateInitialBalance(5001));
	}

}
