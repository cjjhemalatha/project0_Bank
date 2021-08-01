package DBOperations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import model.EmployeePojoModel;
import model.PojoClass;

public class DAOdbConnect implements DBInterface {
	private static Logger log=Logger.getLogger(DAOdbConnect.class);
	@Override
	public int addToDB(PojoClass userDB) {
		// TODO Auto-generated method stub
		int result=0;
		try {
			log.info("--adding details to DB--");
			PreparedStatement pstmt=(DBConnection.getConnectionInstance())
					.prepareCall("insert into bankuser values(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, userDB.getId());
			pstmt.setString(2, userDB.getFirstName());
			pstmt.setString(3, userDB.getLastName());
			pstmt.setString(4, userDB.getPassword());
			pstmt.setString(5, userDB.getPhoneNo());
			pstmt.setInt(6, userDB.getAge());
			pstmt.setString(7, userDB.getAddress());
			pstmt.setString(8, userDB.getAdharNumber());
			pstmt.setInt(9, userDB.getInitialAmount());
			pstmt.setString(10, userDB.getTransactionDetails());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.fatal("--Exception in adding to db--");
			e.printStackTrace();
		}
		return result;
	}
	
	public int updateDB(PojoClass userDB) {
		int result=0;
		PreparedStatement pstmt=null;
		try {
			pstmt=(DBConnection.getConnectionInstance()).prepareStatement("update bankuser set balance=?, transactionHistory=? where id=?");
			pstmt.setInt(1, userDB.getInitialAmount());
			pstmt.setString(2, userDB.getTransactionDetails());
			pstmt.setString(3, userDB.getId());
			result=pstmt.executeUpdate();
			log.debug("Update successfull");
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}

	@Override
	public List<PojoClass> getFromDB(String userID) {
		// TODO Auto-generated method stub
		List<PojoClass> listForValidation=null;
		try {
		PreparedStatement pstmt=(DBConnection.getConnectionInstance()).prepareCall("select * from bankuser where id=?");
		pstmt.setString(1,userID );
		ResultSet rs = pstmt.executeQuery();
		listForValidation=new ArrayList<PojoClass>();
		while(rs.next()) {
			PojoClass userV=new PojoClass();
			userV.setId(rs.getString(1));
			userV.setFirstName(rs.getString(2));
			userV.setPassword(rs.getString(4));
			userV.setInitialAmount(rs.getInt(9));
			userV.setTransactionDetails(rs.getString(10));
			listForValidation.add(userV);
			log.debug("list for validating the required user send");
		}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(listForValidation.size()!=0) {
			log.warn("No matching id found in db");
			return listForValidation;
		}
		
		else {
			return null;
		}
		
	}
	
	public List<PojoClass> getFromDB() {
		// TODO Auto-generated method stub
		List<PojoClass> listForValidation=null;
		try {
		PreparedStatement pstmt=(DBConnection.getConnectionInstance()).prepareCall("select * from bankuser");
		ResultSet rs = pstmt.executeQuery();
		listForValidation=new ArrayList<PojoClass>();
		while(rs.next()) {
			PojoClass userV=new PojoClass();
			userV.setId(rs.getString(1));
			userV.setFirstName(rs.getString(2));
			userV.setAge(rs.getInt(6));
			userV.setPhoneNo(rs.getString(5));
			userV.setInitialAmount(rs.getInt(9));
			userV.setTransactionDetails(rs.getString(10));
			listForValidation.add(userV);
			log.info("--List for Employee to view--");
		}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(listForValidation.size()!=0)
		
			return listForValidation;
		else {
			return null;
		}
		
	}
	
	

	public List<EmployeePojoModel> getFromEmpDB(String id) {
		// TODO Auto-generated method stub
		List<EmployeePojoModel> listForValidation=null;
		try {
		PreparedStatement pstmt=(DBConnection.getConnectionInstance()).prepareCall("select * from Employee where empid=?");
		pstmt.setString(1,id );
		ResultSet rs = pstmt.executeQuery();
		listForValidation=new ArrayList<EmployeePojoModel>();
		while(rs.next()) {
			EmployeePojoModel empV=new EmployeePojoModel();
			empV.setEmpID(rs.getString(1));
			empV.setEmpName(rs.getString(2));
			empV.setEmpPassword(rs.getString(3));
			listForValidation.add(empV);
			log.debug("List for validating paticular employee");
		}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(listForValidation.size()!=0)
		
			return listForValidation;
		else {
			return null;
		}
		
	}
	

}
