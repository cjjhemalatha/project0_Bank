package com.MyBank.BankApp;

public class FactoryClass {
	public static UserTypeInterface getBankInstance(int userType) {
		UserTypeInterface u=null;
		if(userType==1) {
			u=new ExistingUserMain();
		}
		if(userType==2) {
			u=new UserMain();
		}
		if(userType==3) {
			u=new EmployeeMain();
		}
		return u;
	}
}
