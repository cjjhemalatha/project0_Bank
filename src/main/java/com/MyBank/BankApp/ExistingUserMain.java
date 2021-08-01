package com.MyBank.BankApp;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.MyBank.BLogic.EmployeeOperation;


import model.PojoClass;

public class ExistingUserMain implements UserTypeInterface {
	private static Logger log=Logger.getLogger(ExistingUserMain.class);
	@Override
	
	public void getOption() {
		
		Scanner sc=new Scanner(System.in);
		PojoClass login=new PojoClass();
		PojoClass rDetails=new PojoClass();
		// TODO Auto-generated method stub
		System.out.println("Welcome back again!!!");
		System.out.println("-------------------------------------------------------------");
		log.info("getting login credential");
		System.out.println("**************User Login**************");
		System.out.println("Please enter your user id:");
		login.setId(sc.next());
		System.out.println("Please enter your user name:");
		login.setFirstName(sc.next());
		System.out.println("Please enter your password:");
		login.setPassword(sc.next());
		EmployeeOperation user=new EmployeeOperation();
		PojoClass resu=user.userLoginValidation("user",login.getId(),login);
		while(true) {
			if(resu!=null) {
				log.debug("login successfull");
				System.out.println("Log-in Successfull!!!");
				System.out.println("---------------------------------------------------------");
				login.setInitialAmount(resu.getInitialAmount());
				login.setTransactionDetails(resu.getTransactionDetails());
				break;
			}
			else {
				log.error("login failed");
				System.out.println("Bad credentials. Please Re-enter Details");
				System.out.println("Please enter your user id:");
				login.setId(sc.next());
				System.out.println("Please enter your user name:");
				login.setFirstName(sc.next());
				System.out.println("Please enter your password:");
				login.setPassword(sc.next());
				resu=user.userLoginValidation("user",login.getId(),login);
				
			}
		}
		System.out.println("-------------------------------------------------------------");
		System.out.println("You can perform following operations:");
		System.out.println("1.View Balance");
		System.out.println("2.Withdrawal");
		System.out.println("3.Deposit");
		System.out.println("4.Transfer amount");
		log.info("----getting options for Existing user----");
		int op=sc.nextInt();
		
		EmployeeOperation emp1= new EmployeeOperation();
		int result;
		while(true) {
			if(op==1) {
				log.info("--balance viewed--");
				result=emp1.updateUser(0, 1, login );
				System.out.println("Your Balance is: "+login.getInitialAmount());
				break;
			}
			else if(op==2) {
				log.info("--withdrawal of amount--");
				System.out.println("Enter the amount you want to withdraw:");
				int wa=sc.nextInt();
				result=emp1.updateUser(wa, 2, login);
				if(result>0)
					System.out.println("Now your balance is: "+login.getInitialAmount());
				else if(result==-1) {
					log.warn("--Less Balance withdraw fail--");
					System.out.println("Sorry your balance would go below the minimum required blance by performing this operation. Therefore we are not able to process your request. ");
				}
					else {
						log.error("--error in DB connection--");
					System.out.println("Sorry, Error occured. Please try later.");
					}
				break;
			}
			else if(op==3) {
				log.info("--amount deposited--");
				System.out.println("Enter the amount you want to deposit:");
				int dep=sc.nextInt();
				result=emp1.updateUser(dep, 3, login);
				System.out.println("Now your balance is: "+login.getInitialAmount());
				break;
			}
			else if(op==4) {
				System.out.println("Select from following options:");
				System.out.println("1.Different Bank user");
				System.out.println("2.Same bank user");
				int opTransfer=sc.nextInt();
				if(opTransfer==1) {
					log.info("--Transfer to another bank user--");
					System.out.println("Enter the following details to transfer:");
					System.out.println("Enter the amount you want to transfer:");
					int transfer=sc.nextInt();
					System.out.println("Name of the recipient:");
					@SuppressWarnings("unused")
					String name= sc.nextLine();
					System.out.println("Enter accountno. of recipient:");
					@SuppressWarnings("unused")
					String accountNo= sc.next();
					System.out.println("Enter ifscCode");
					@SuppressWarnings("unused")
					String ifscCode=sc.next();
					result=emp1.updateUser(transfer, 4, login);
					if(result>0)
						System.out.println("Now your balance is: "+login.getInitialAmount());
					else if(result==-1) {
						log.error("--insufficient balance to transfer the amount--");
						System.out.println("Sorry your balance would go below the minimum required blance by performing this operation. Therefore we are not able to process your request. ");
					}
						else 
						System.out.println("Sorry, Error occured. Please try later.");
					break;
					
				}
				else if(opTransfer==2) {
					log.info("--Transfer too same bank user--");
					System.out.println("Enter the existing bank user ID:");
					rDetails.setId(sc.next());
					
					System.out.println("Enter the name of the person:");
					rDetails.setFirstName(sc.next());
					PojoClass r=user.userLoginValidation("ruser",rDetails.getId(),rDetails);
					if(r!=null) {
						System.out.println("Valid Recepient");
						System.out.println("---------------------------------------------------------");
						rDetails.setInitialAmount(r.getInitialAmount());
						rDetails.setTransactionDetails(r.getTransactionDetails());
					System.out.println("Enter the amount you want to transfer:");
					int transfer=sc.nextInt();
					result=emp1.updateUser(transfer, 4, login,rDetails);
					if(result>0)
						System.out.println("Now your balance is: "+login.getInitialAmount());
					else if(result==-1) {
						log.error("--Insufficient balance to transfer--");
						System.out.println("Sorry your balance would go below the minimum required blance by performing this operation. Therefore we are not able to process your request. ");
					}
					break;
					
					
				}
			}
			else {
				System.out.println("Invalid Option. Please enter 1 ,2 ,3 or 4");
				op=sc.nextInt();
			}
			
			}
		}
		
		sc.close();
		//TODO Accept amount from other
	}

	@Override
	public void displayInformation() {
		// TODO Auto-generated method stub
		System.out.println("Thank you, Have a wonderfull day!!!");
		
	}

}

