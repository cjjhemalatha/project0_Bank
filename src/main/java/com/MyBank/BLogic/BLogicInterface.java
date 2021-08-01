package com.MyBank.BLogic;

import java.util.List;

import model.PojoClass;

public interface BLogicInterface {
	public int sendToDB(PojoClass userdetails);
	public List<PojoClass> getFromDB();
}
