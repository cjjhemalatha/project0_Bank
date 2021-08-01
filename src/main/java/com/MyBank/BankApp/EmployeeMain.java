package com.MyBank.BankApp;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.MyBank.BLogic.BLogicInterface;
import com.MyBank.BLogic.EmployeeOperation;

import model.EmployeePojoModel;
import model.PojoClass;


public class EmployeeMain implements UserTypeInterface {
	private static Logger log=Logger.getLogger(EmployeeMain.class);
	@Override
	public void getOption() {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		EmployeePojoModel login=new EmployeePojoModel();
		// TODO Auto-generated method stub
		System.out.println("Welcome back again!!!");
		System.out.println("-------------------------------------------------------------");
		log.info("Employee login");
		System.out.println("**************Employee Login**************");
		System.out.println("Please enter your Employee id:");
		login.setEmpID(sc.next());
		System.out.println("Please enter your employee name:");
		login.setEmpName(sc.next());
		System.out.println("Please enter your password:");
		login.setEmpPassword(sc.next());
		EmployeeOperation user=new EmployeeOperation();
		int resu=user.employeeLoginValidation(login.getEmpID(),login);
		while(true) {
			if(resu!=0) {
				System.out.println("Log-in Successfull!!!");
				System.out.println("---------------------------------------------------------");
				break;
			}
			else {
				log.error("login failed due to invalid credential");
				System.out.println("Bad credentials. Please Re-enter Details");
				System.out.println("Please enter your Employee id:");
				login.setEmpID(sc.next());
				System.out.println("Please enter your Employee name:");
				login.setEmpName(sc.next());
				System.out.println("Please enter your password:");
				login.setEmpPassword(sc.next());
				resu=user.employeeLoginValidation(login.getEmpID(),login);
			}
		}
		System.out.println("Option");
		System.out.println("1. View all user Details");
		System.out.println("2. View all user transaction Details: ");
		System.out.println("Your option:");
		int o=sc.nextInt();
		if(o==1) {
			displayInformation();
		}
		else if(o==2) {
			displayTransaction();
		}
		sc.close();

	}

	@Override
	public void displayInformation() {
		// TODO Auto-generated method stub
		log.info("Displaying all user information");
		BLogicInterface userDetails=new EmployeeOperation();
		List<PojoClass> lu= userDetails.getFromDB();
		for(PojoClass u:lu) {
			System.out.println("User ID: "+u.getId());
			System.out.println("User Name: "+u.getFirstName());
			System.out.println("User Age: "+u.getAge());
			System.out.println("User Phone no: "+u.getPhoneNo());
			System.out.println("User Balance: "+u.getInitialAmount());
			System.out.println("*******************************************");
		}
		

	}
	public void displayTransaction() {
		log.info("Displaying all transaction details");
		BLogicInterface userDetails=new EmployeeOperation();
		List<PojoClass> lu= userDetails.getFromDB();
		for(PojoClass u:lu) {
			System.out.println("User ID: "+u.getId());
			System.out.println("User Name: "+u.getFirstName());
			System.out.println("User Transaction: "+u.getTransactionDetails());
			System.out.println("*******************************************");
		}
	}
	

}
