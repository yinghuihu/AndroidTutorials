import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class LoadData {
	private static final String DB_NAME = "Bible.db";
	private static final String QUERY_FILE = "Query-sqlite.sql";
	
	private static boolean isPersistable = false;
	
	private static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) {
		createBookTable();
		createVerseTable();
		
		insertBookData();
		
		loadDataForVersion("hhb");
		loadDataForVersion("niv");
		
		FileUtil.writeFile(sb.toString(), QUERY_FILE);
		System.out.println("DONE");
	}
	
	private static void loadDataForVersion(String version) {
		try{
			for (int i=1; i<=66; i++) {
				String bookNumber = "";
				if (i<10) {
					bookNumber = "0" + i;
				} else {
					bookNumber = "" + i;
				}
				
				int chapterCount = ChapterXmlParser.getChapterCount(version, bookNumber);
				
				for (int j=1; j<=chapterCount; j++) {
					String chapterNumber = "" + j;
					ChapterDTO chapter = ChapterXmlParser.getChapterContent(version, bookNumber, chapterNumber);
					insertChapterVerse(chapter.getVerses(), version, chapterNumber, bookNumber);
				}
			}
		}
		catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
	}

	private static void createBookTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);

			stmt = c.createStatement();

			String tableName = "book";

			String fieldId = "id";
			String fieldName = "name";
			String fieldSearchString = "searchString";
			String fieldNumber = "number";

			String fieldTestament = "testament";
			String fieldChapterCount = "chapterCount";
			String fieldInitialString = "initialString";

			String sql = "";

			sql += "CREATE TABLE " + tableName;
			sql += " ( ";
			sql += fieldId + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
			sql += fieldName + " TEXT, ";
			sql += fieldSearchString + " TEXT, ";
			sql += fieldNumber + " TEXT, ";
			sql += fieldTestament + " TEXT, ";
			sql += fieldChapterCount + " TEXT, ";
			sql += fieldInitialString + " TEXT ";
			sql += " ); ";

			System.out.println(sql);
			if (isPersistable) {
				stmt.executeUpdate(sql);
			} else {
				sb.append(sql + "\n");
			}
			
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	private static void createVerseTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);

			stmt = c.createStatement();

			// table details
			String tableName = "verse";

			String fieldId = "id";
			String fieldVersion = "version";
			String fieldContent = "content";
			String fieldLineNumber = "lineNumber";
			String fieldChapterNumber = "chapterNumber";
			String fieldBookNumber = "bookNumber";

			String sql = "";

			sql += "CREATE TABLE " + tableName;
			sql += " ( ";
			sql += fieldId + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
			sql += fieldVersion + " TEXT, ";
			sql += fieldBookNumber + " TEXT, ";
			sql += fieldContent + " TEXT, ";
			sql += fieldLineNumber + " TEXT, ";
			sql += fieldChapterNumber + " TEXT ";
			sql += " ); ";

			System.out.println(sql);
			if (isPersistable) {
				stmt.executeUpdate(sql);
			} else {
				sb.append(sql + "\n");
			}
			
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	private static void insertBookData() {
		// CREATE
		create(new BookDTO("创世纪", "csj", "01", "OLD", "50", "创"));
		create(new BookDTO("出埃及记", "cajj", "02", "OLD", "40", "出"));
		create(new BookDTO("利未记", "lwj", "03", "OLD", "27", "利"));
		create(new BookDTO("民数记", "msj", "04", "OLD", "36", "民"));
		create(new BookDTO("申命记", "smj", "05", "OLD", "34", "申"));
		create(new BookDTO("约书亚记", "ysyj", "06", "OLD", "24", "书"));
		create(new BookDTO("士师记", "ssj", "07", "OLD", "21", "士"));
		create(new BookDTO("路得记", "ldj", "08", "OLD", "4", "得"));
		create(new BookDTO("撒母耳记上", "smejs", "09", "OLD", "31", "撒上"));
		create(new BookDTO("撒母耳记下", "smejx", "10", "OLD", "24", "撒下"));
		create(new BookDTO("列王记上", "lwjs", "11", "OLD", "22", "王上"));
		create(new BookDTO("列王记下", "lwjx", "12", "OLD", "25", "王下"));
		create(new BookDTO("历代志上", "ldzs", "13", "OLD", "29", "代上"));
		create(new BookDTO("历代志下", "ldzx", "14", "OLD", "36", "代下"));
		create(new BookDTO("以斯拉记", "yslj", "15", "OLD", "10", "拉"));
		create(new BookDTO("尼希米记", "nxmj", "16", "OLD", "13", "尼"));
		create(new BookDTO("以斯帖记", "ystj", "17", "OLD", "10", "斯"));
		create(new BookDTO("约伯记", "ybj", "18", "OLD", "42", "伯"));
		create(new BookDTO("诗篇", "sp", "19", "OLD", "150", "诗"));
		create(new BookDTO("箴言", "zy", "20", "OLD", "31", "箴"));
		create(new BookDTO("传道书", "cds", "21", "OLD", "12", "传"));
		create(new BookDTO("雅歌", "yg", "22", "OLD", "8", "雅"));
		create(new BookDTO("以赛亚书", "ysys", "23", "OLD", "66", "赛"));
		create(new BookDTO("耶利米书", "ylms", "24", "OLD", "52", "耶"));
		create(new BookDTO("耶利米哀歌", "ylmag", "25", "OLD", "5", "哀"));
		create(new BookDTO("以西结书", "yxjs", "26", "OLD", "48", "结"));
		create(new BookDTO("但以理书", "dyls", "27", "OLD", "12", "但"));
		create(new BookDTO("何西阿书", "hxas", "28", "OLD", "14", "何"));
		create(new BookDTO("约珥书", "yes", "29", "OLD", "3", "珥"));
		create(new BookDTO("阿摩司书", "amss", "30", "OLD", "9", "摩"));
		create(new BookDTO("俄巴底亚书", "ebdys", "31", "OLD", "1", "俄"));
		create(new BookDTO("约拿书", "yns", "32", "OLD", "4", "拿"));
		create(new BookDTO("弥迦书", "mjs", "33", "OLD", "7", "弥"));
		create(new BookDTO("那鸿书", "nhs", "34", "OLD", "3", "鸿"));
		create(new BookDTO("哈巴谷书", "hbgs", "35", "OLD", "3", "哈"));
		create(new BookDTO("西番雅书", "xfys", "36", "OLD", "3", "番"));
		create(new BookDTO("哈该书", "hgs", "37", "OLD", "2", "该"));
		create(new BookDTO("撒迦利亚书", "sjlys", "38", "OLD", "14", "亚"));
		create(new BookDTO("玛拉基书", "mljs", "39", "OLD", "4", "玛"));

		create(new BookDTO("马太福音", "mtfy", "40", "NEW", "28", "太"));
		create(new BookDTO("马可福音", "mkfy", "41", "NEW", "16", "可"));
		create(new BookDTO("路加福音", "ljfy", "42", "NEW", "24", "路"));
		create(new BookDTO("约翰福音", "yhfy", "43", "NEW", "21", "约"));
		create(new BookDTO("使徒行传", "stxz", "44", "NEW", "28", "徒"));
		create(new BookDTO("罗马书", "lms", "45", "NEW", "16", "罗"));
		create(new BookDTO("哥林多前书", "gldqs", "46", "NEW", "16", "林前"));
		create(new BookDTO("哥林多后书", "gldhs", "47", "NEW", "13", "林后"));
		create(new BookDTO("加拉太书", "jlts", "48", "NEW", "6", "加"));
		create(new BookDTO("以弗所书", "yfss", "49", "NEW", "6", "弗"));
		create(new BookDTO("腓立比书", "flbs", "50", "NEW", "4", "腓"));
		create(new BookDTO("歌罗西书", "glxs", "51", "NEW", "4", "西"));
		create(new BookDTO("帖撒罗尼加前书", "tslnjqs", "52", "NEW", "5", "帖前"));
		create(new BookDTO("帖撒罗尼加后书", "tslnjhs", "53", "NEW", "3", "帖后"));
		create(new BookDTO("提摩太前书", "tmtqs", "54", "NEW", "6", "提前"));
		create(new BookDTO("提摩太后书", "tmths", "55", "NEW", "4", "提后"));
		create(new BookDTO("提多书", "tds", "56", "NEW", "3", "多"));
		create(new BookDTO("腓立门书", "flbs", "57", "NEW", "1", "门"));
		create(new BookDTO("希伯来书", "xbls", "58", "NEW", "13", "来"));
		create(new BookDTO("雅各书", "ygs", "59", "NEW", "5", "雅"));
		create(new BookDTO("彼得前书", "bdqs", "60", "NEW", "5", "彼前"));
		create(new BookDTO("彼得后书", "bdhs", "61", "NEW", "3", "彼后"));
		create(new BookDTO("约翰一书", "yhys", "62", "NEW", "5", "约1"));
		create(new BookDTO("约翰二书", "yhes", "63", "NEW", "1", "约2"));
		create(new BookDTO("约翰三书", "yhss", "64", "NEW", "1", "约3"));
		create(new BookDTO("犹大书", "yds", "65", "NEW", "1", "犹"));
		create(new BookDTO("启示录", "qsl", "66", "NEW", "22", "启"));
	}

	
	private static boolean insertChapterVerse(ArrayList<VerseDTO> verses, String version, String chapterNumber, String bookNumber) {
		String fieldVersion = "version";
		String fieldContent = "content";
		String fieldLineNumber = "lineNumber";
		String fieldChapterNumber = "chapterNumber";
		String fieldBookNumber = "bookNumber";
		
		boolean createSuccessful = false;

		String tableName = "verse";

		for (VerseDTO verse: verses) {
			Connection c = null;
			Statement stmt = null;
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
				c.setAutoCommit(false);
//				System.out.println("Opened database successfully");

				stmt = c.createStatement();
				String sql = "INSERT INTO %s (%s, %s, %s, %s, %s) "
						+ "VALUES ('%s', '%s', '%s', '%s', '%s' );";

				sql = String.format(sql, tableName, fieldVersion, fieldBookNumber, fieldChapterNumber,  fieldLineNumber, fieldContent, 
						version, bookNumber, chapterNumber, verse.getVerseIndex(), verse.getVerseContent().replaceAll("'", "''"));

				System.out.println(sql);
				if (isPersistable) {
					stmt.executeUpdate(sql);
				} else {
					sb.append(sql + "\n");
				}
				
				stmt.close();
				
				c.commit();
				c.close();
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
		}
		

		return createSuccessful;
	}

	private static boolean create(BookDTO book) {

		boolean createSuccessful = false;

		String tableName = "book";

		String fieldName = "name";
		String fieldSearchString = "searchString";
		String fieldNumber = "number";

		String fieldTestament = "testament";
		String fieldChapterCount = "chapterCount";
		String fieldInitialString = "initialString";

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
			c.setAutoCommit(false);
//			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO %s (%s, %s, %s, %s, %s, %s) "
					+ "VALUES ('%s', '%s', '%s', '%s', '%s', '%s' );";

			sql = String.format(sql, tableName, fieldName, fieldSearchString,
					fieldNumber, fieldTestament, fieldChapterCount,
					fieldInitialString, book.getName(), book.getSearchString(),
					book.getNumber(), book.getTestament(),
					book.getChapterCount(), book.getInitialString());
			
			System.out.println(sql);
			if (isPersistable) {
				stmt.executeUpdate(sql);
			} else {
				sb.append(sql + "\n");
			}
			
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return createSuccessful;
	}

}
