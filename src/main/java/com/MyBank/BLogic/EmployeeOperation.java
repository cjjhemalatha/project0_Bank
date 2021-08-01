package com.MyBank.BLogic;

import java.util.List;

import org.apache.log4j.Logger;

import DBOperations.DAOdbConnect;
import DBOperations.DBInterface;
import model.EmployeePojoModel;
import model.PojoClass;

public class EmployeeOperation implements BLogicInterface {
	
	private static Logger log=Logger.getLogger(EmployeeOperation.class);
	PojoClass d=new PojoClass();
	public PojoClass userLoginValidation(String type,String id, PojoClass details) {
		// TODO Auto-generated method stub
		DBInterface ulogin=new DAOdbConnect();
		List<PojoClass> listFromDB=ulogin.getFromDB(id);
		
		if(listFromDB==null) {
			return null;
		}
		if(type.equals("ruser")) {
			if(listFromDB.get(0).getId().equals(id) && listFromDB.get(0).getFirstName().equals(details.getFirstName())) {
				d.setTransactionDetails(listFromDB.get(0).getTransactionDetails());
				d.setInitialAmount(listFromDB.get(0).getInitialAmount());
				return d;
			}
		}
		else if(type.equals("user")) {
			if(listFromDB.get(0).getId().equals(id) && listFromDB.get(0).getFirstName().equals(details.getFirstName()) && listFromDB.get(0).getPassword().equals(details.getPassword())) {
				d.setTransactionDetails(listFromDB.get(0).getTransactionDetails());
				d.setInitialAmount(listFromDB.get(0).getInitialAmount());
				return d;
		    }
		
		}
		return null;
	}
	
	
	public int employeeLoginValidation(String id,EmployeePojoModel login) {
		DAOdbConnect elogin=new DAOdbConnect();
		List<EmployeePojoModel> listFromDB=elogin.getFromEmpDB(id);
		
		if(listFromDB==null) {
			return 0;
		}
		if(listFromDB.get(0).getEmpID().equals(id) && listFromDB.get(0).getEmpName().equals(login.getEmpName()) && listFromDB.get(0).getEmpPassword().equals(login.getEmpPassword())) {
			log.debug("Employee validated successfully");
			return 1;
		}
		return 0;
		
			
	}
	
	
	@Override
	public int sendToDB(PojoClass userdetails) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PojoClass> getFromDB() {
		// TODO Auto-generated method stub
		DAOdbConnect d=new DAOdbConnect();
		List<PojoClass> ul=d.getFromDB();
		
		return ul;
	}

	public int updateUser(int amount, int type, PojoClass log) {
		// TODO Auto-generated method stub
		int newBalance;
		DAOdbConnect updateBalance=new DAOdbConnect();
		if(type==1) {
			System.out.println(log.getInitialAmount());
				return(log.getInitialAmount());
			
		}
		String ut; 
		if(type==2) {
				newBalance = log.getInitialAmount()-amount;
				if(newBalance>=5000) {
					log.setInitialAmount(newBalance);
					ut=" "+log.getTransactionDetails()+" || withdrawal - "+amount;
					log.setTransactionDetails(ut);
					int status=updateBalance.updateDB(log);
					return(status);
					//TODO update balance
				}
				else {
					return (-1);
				}
			
			}
		if(type==3) {
				newBalance = log.getInitialAmount()+amount;
				log.setInitialAmount(newBalance);
				ut=" "+log.getTransactionDetails()+" || deposit - "+amount;
				log.setTransactionDetails(ut);
				int status=updateBalance.updateDB(log);
				 return(status);
				//todo update balance
			}
		if(type==4) {
			newBalance = log.getInitialAmount()-amount;
			if(newBalance>=5000) {
				log.setInitialAmount(newBalance);
				ut=" "+log.getTransactionDetails()+" || transfered - "+amount;
				log.setTransactionDetails(ut);
				System.out.println(newBalance);
				int status=updateBalance.updateDB(log);
				
				return(status);
				//TODO update balance
			}
			else {
				
				return -1;
			}
		
		}
		return 0;
			
	}
	String Sstr;
	String str;
	public int updateUser(int amount, int i, PojoClass log, PojoClass rDetails) {
		DAOdbConnect updateBalance=new DAOdbConnect();
		// TODO Auto-generated method stub
		int newBalance = log.getInitialAmount()-amount;
		
		if(newBalance>=5000) {
			int rNewBalance= rDetails.getInitialAmount()+amount;
			rDetails.setInitialAmount(rNewBalance);
			str=rDetails.getTransactionDetails()+" || "+amount+" credited by "+log.getFirstName()+" ";
			rDetails.setTransactionDetails(str);
			Sstr=log.getTransactionDetails()+" || "+amount+" debited to "+rDetails.getFirstName()+" ";
			log.setTransactionDetails(Sstr);
			log.setInitialAmount(newBalance);
			int status=updateBalance.updateDB(log);
			updateBalance.updateDB(rDetails);
			return(status);
			//TODO update balance
		}
		else {
			return (-1);
		}
	}
		
		
	}


