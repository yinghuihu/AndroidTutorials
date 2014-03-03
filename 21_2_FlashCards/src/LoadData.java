import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class LoadData {
	
	private static final String DB_NAME = "FlashCard.db";
	
	private static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) {
		SetDTO set = XmlParserUtil.getSetContent("");
		
		createSetTable();
		createQuestionTable();
		
		createSet(set);
//		System.out.println(set);
	}
	
	private static void createSetTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);

			stmt = c.createStatement();

			String tableName = "FlashCardSet";

			String fieldId = "id";
			String fieldName = "name";

			String sql = "";

			sql += "CREATE TABLE " + tableName;
			sql += " ( ";
			sql += fieldId + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
			sql += fieldName + " TEXT ";
			sql += " ); ";

			stmt.executeUpdate(sql);
			
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	
	
	private static boolean createSet(SetDTO setDTO) {

		boolean createSuccessful = false;

		String tableName = "FlashCardSet";

		String fieldId = "id";
		String fieldName = "name";

		Connection c = null;
		
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
			c.setAutoCommit(false);
//			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO %s (%s) "
					+ "VALUES ('%s');";

			sql = String.format(sql, tableName, 
					fieldName, 
					setDTO.getName());
			
			System.out.println(sql);
			stmt.executeUpdate(sql);
			
			stmt.close();
			c.commit();
			c.close();
			
			int setFK = getSetFK(setDTO.getName());
			
			for (int i=0; i< setDTO.getQuestions().size(); i++) {
				insertQuestion(setDTO.getQuestions().get(i), setFK);
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return createSuccessful;
	}
	
	public static int getSetFK(String setName_) {
		int setFK=0;
		String tableName = "FlashCardSet";
		String fieldName = "name";
		String fieldId = "id";

		// select query
		String sql = "";
		sql += "SELECT * FROM " + tableName;
		sql += " WHERE " + fieldName + " = '" + setName_ + "'";
		sql += " LIMIT 0,5";

		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				setFK = rs.getInt(fieldId);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return setFK;
	}
	
	private static boolean insertQuestion(QuestionDTO questionDTO, int setFK) {

		boolean createSuccessful = false;

		String tableName = "FlashCardSetQuestions";

		String fieldId = "id";
		String fieldTitle = "Title";
		String fieldAnswers = "Answers";
		String fieldSetID = "setFK";

		Connection c = null;
		
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
			c.setAutoCommit(false);
//			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO %s (%s, %s, %s) "
					+ "VALUES ('%s', '%s', %s);";

			sql = String.format(sql, tableName, 
					fieldTitle,  fieldAnswers, fieldSetID,
					questionDTO.getTitle().replaceAll("'", "''"), questionDTO.getAnswer().replaceAll("'", "''"), setFK);
			
			System.out.println(sql);
			stmt.executeUpdate(sql);
			
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return createSuccessful;
	}
	
	
	private static void createQuestionTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);

			stmt = c.createStatement();

			String tableName = "FlashCardSetQuestions";

			String fieldId = "id";
			String fieldTitle = "Title";
			String fieldAnswers = "Answers";
			String fieldSetID = "setFK";

			String sql = "";

			sql += "CREATE TABLE " + tableName;
			sql += " ( ";
			sql += fieldId + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
			sql += fieldTitle + " TEXT, ";
			sql += fieldAnswers + " TEXT, ";
			sql += fieldSetID + " INTEGER ";
			sql += " ); ";

			stmt.executeUpdate(sql);
			
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
}
