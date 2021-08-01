package com.MyBank.BankApp;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.MyBank.BLogic.BLogicInterface;
import com.MyBank.BLogic.GenerateId;
import com.MyBank.BLogic.ValidateNewUserDetails;

import model.PojoClass;

public class UserMain implements UserTypeInterface {
	private static Logger log=Logger.getLogger(UserMain.class);
	int op;
	Scanner sc=new Scanner(System.in);
	@Override
	public void getOption() {
		// TODO Auto-generated method stub
		System.out.println("Welcome!!! Hope you will enjoy our service");
		System.out.println("You can create a new account with a starting balance of Rs.5000/-");
		System.out.println("Options provided:");
		System.out.println("1.Willing to create take me to signup");
		System.out.println("2.Willing but would signup later");
		System.out.println("3.Not willing");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Enter an option:");
		op=sc.nextInt();
		sc.nextLine();
		while(true) {
			if(!(op==1) && !(op==2) && !(op==3)) {
				System.out.println("You have entered invalid option. Enter 1, 2 or 3 ");
				op=sc.nextInt();
				
			}
			else {
				
				break;
			}
		}

	}

	@Override
	public void displayInformation() {
		
		// TODO Auto-generated method stub
		PojoClass user=new PojoClass();
		if(op==1) {			
			System.out.println("Thank you for choosing our bank. Please enter following details:");
			log.info("getting details from user");
			System.out.println("First Name:");
			user.setFirstName(sc.next());
			
			System.out.println("Last Name:");
			user.setLastName(sc.next());
			
			System.out.println("Password(*Your password should contain atleast one UPPER CASE one LOWER CASE one NUMBER one SPECIAL CHARACTER and should be of LENGTH(8 or more))");
			
			user.setPassword(sc.next());
			//send to validate password 
			ValidateNewUserDetails vd=new ValidateNewUserDetails();
			int v=vd.validatePassword(user.getPassword());
			while(true) {
				if(v==1) {
					break;
				}
				else {
					System.out.println("Your password is weak. Please re-enter it. Check if it matches the following conditions( atleast one UPPER CASE one LOWER CASE one NUMBER one SPECIAL CHARACTER (@#$%^&+=) and should be of LENGTH(8 or more))");
					user.setPassword(sc.next());
					v=vd.validatePassword(user.getPassword());
				}
			}
			
			System.out.println("Enter contact no.");
			user.setPhoneNo(sc.next());
			//send to validate phone no.
			int vphn=vd.validatePhoneNumber(user.getPhoneNo());
			while(true) {
				if(vphn==1) {
					break;
				}
				else {
					System.out.println("Phone number entered by you is invalid. Check if it's 10 digits. ");
					user.setPhoneNo(sc.next());
					vphn=vd.validatePhoneNumber(user.getPhoneNo());
				}
			}
			System.out.println("Age");
			user.setAge(sc.nextInt());
			
			System.out.println("Address");
			sc.nextLine();
			user.setAddress(sc.nextLine());
			
			System.out.println("Adhar Number");
			user.setAdharNumber(sc.next());
			int vadhar=vd.validateAdhar(user.getAdharNumber());
			while(true) {
				if(vadhar==1) {
					break;
				}
				else {
					System.out.println("Adhar number entered by you is invalid. Check if it's 10 digits. ");
					user.setAdharNumber(sc.next());
					vadhar=vd.validateAdhar(user.getAdharNumber());
				}
			}
			
			
			System.out.println("Enter the initial amount with which you want to open account");
			System.out.println("Note: The initial amount should be minimum of Rs.5000 ");
			user.setInitialAmount(sc.nextInt());
			user.setTransactionDetails("initial "+user.getInitialAmount());
			//send to validate initialamount
			int vamt=vd.validateInitialBalance(user.getInitialAmount());
			while(true) {
				if(vamt==1) {
					break;
				}
				else {
					System.out.println("ALERT!!! The amount is below Rs.5000");
					System.out.println("Please enter amount above Rs.5000");
					user.setInitialAmount(sc.nextInt());
					user.setTransactionDetails("initial "+user.getInitialAmount());
					vamt=vd.validateInitialBalance(user.getInitialAmount());
				}
			}
			BLogicInterface userDetails=new ValidateNewUserDetails();
			GenerateId gid=new GenerateId();
			String id=gid.genratingIds(user);
			int status=userDetails.sendToDB(user);
			if(status!=0) {
				
				System.out.println("Your account has been created. Please remember this Unique ID: "+id);
				System.out.println("Thank you once again!!! Have a nice day");
			}
			else {
				System.out.println("Sorry Try again later.");
			}
		}
		else if(op==2) {
			System.out.println("Thank you. See you back soon");
		}
		else if(op==3) {
			System.out.println("Thank you for visiting MyBank.");
		}
		else {
			System.out.println("You have entered invalid choice. Please enter 1,2 or 3");
		}
	
	}

}
