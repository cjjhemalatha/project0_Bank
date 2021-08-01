package com.MyBank.BLogic;

import org.apache.log4j.Logger;

import model.PojoClass;

public class GenerateId {
	private static Logger log=Logger.getLogger(GenerateId.class);
	public String genratingIds(PojoClass userr){
	int id= (int)(Math.random()*9000)+1000;
	String randomId = String.valueOf(id);
	userr.setId(randomId);
	log.debug("User ID generated");
	return(userr.getId());
}
}
