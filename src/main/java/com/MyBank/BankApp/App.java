package com.MyBank.BankApp;

import java.util.Scanner;

import org.apache.log4j.Logger;
public class App 
{
	private static Logger log=Logger.getLogger(App.class);
	int i;
    public static void main( String[] args )
    {
    	App x=new App();
    	Scanner sc= new Scanner(System.in);
        System.out.println( "$$$$$$$$ Welcome to MyBank $$$$$$$$" );
        System.out.println("-------------------------------------------------------");
        System.out.println("");
        log.info("Enter options how to use BankApp");
     
        System.out.println("You want to use app as which of the following:");
        System.out.println("1. Existing User");
        System.out.println("2. New User");
        System.out.println("3. Employee of the bank");
        while(true) {
	        System.out.println("Enter your option");
	        x.i=sc.nextInt();
	        if(x.i==1)
	        {
	        	
	        	ExistingUserMain eum= (ExistingUserMain) FactoryClass.getBankInstance(x.i);
	        	eum.getOption();
	        	eum.displayInformation();
	        	break;
	        }
	        else if(x.i==2) {
	        	UserMain um= (UserMain) FactoryClass.getBankInstance(x.i);
	        	um.getOption();
	        	um.displayInformation();
	        	break;
	        }
	        else if(x.i==3) {
	        	EmployeeMain em= (EmployeeMain) FactoryClass.getBankInstance(x.i);
	        	em.getOption();
	        	break;
	        }

	        else {
		        log.error("Wrong option entered");
	        	System.out.println("You have entered an invalid option. Please enter 1, 2 or 3");
	        }
        }
        sc.close();
        
        
        
    }
}

