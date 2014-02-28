import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class LoadData {
	/* if isPersistable is false then write the queries to the QUERY_FILE
	 * otherwise save the data in the sqlite.
	 */
	private static boolean isPersistable = true;
	
	private static final String DB_NAME = "Bible1.db";
	private static final String QUERY_FILE = "Query-sqlite.sql";
	
	
	
	private static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) {
	
	}
	
	

}
