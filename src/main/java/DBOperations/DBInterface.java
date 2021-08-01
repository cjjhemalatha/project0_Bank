package DBOperations;

import java.util.List;

import model.PojoClass;

public interface DBInterface {
	public int addToDB(PojoClass userDB);
	public List<PojoClass> getFromDB(String a);
}
