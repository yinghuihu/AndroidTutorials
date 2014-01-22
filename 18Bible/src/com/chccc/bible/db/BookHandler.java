package com.chccc.bible.db;

import java.util.ArrayList;

import com.chccc.bible.dto.BookDTO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BookHandler extends SQLiteOpenHelper {

    // for our logs
    public static final String TAG = "BookHandler.java";

    // database version
    private static final int DATABASE_VERSION = 1;

    // database name
    protected static final String DATABASE_NAME = "Bible";

    // table details
    private  final String tableName = "book";
    private  final String fieldId = "id";
    private  final String fieldName = "name";
    private  final String fieldSearchString = "searchString";
    private  final String fieldNumber = "number";
    
    private  final String fieldTestament = "testament";
    private  final String fieldChapterCount = "chapterCount";
    
    private  final String OLD_TESTAMENT = "OLD";
    private  final String NEW_TESTAMENT = "NEW";
    

    // constructor
    public BookHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // creating table
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "";

        sql += "CREATE TABLE " + tableName;
        sql += " ( ";
        sql += fieldId + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        sql += fieldName + " TEXT, ";
        sql += fieldSearchString + " TEXT, ";
        sql += fieldNumber + " TEXT, ";
        sql += fieldTestament + " TEXT, ";
        sql += fieldChapterCount + " TEXT ";
        sql += " ) ";

        db.execSQL(sql);
        
//        insertBookData();

    }

    public void insertBookData(){
        // CREATE
    	create( new BookDTO("创世纪", "csj", "01", "OLD", "50"));
    	create( new BookDTO("出埃及记", "cajj", "02", "OLD", "40"));
    	create( new BookDTO("利未记", "lwj", "03", "OLD", "27"));
    	create( new BookDTO("民数记", "msj", "04", "OLD", "36"));
    	create( new BookDTO("申命记", "smj", "05", "OLD", "34"));
    	create( new BookDTO("约书亚记", "ysyj", "06", "OLD", "24"));
    	create( new BookDTO("士师记", "ssj", "07", "OLD", "21"));
    	create( new BookDTO("路得记", "ldj", "08", "OLD", "4"));
    	create( new BookDTO("撒母耳记上", "smejs", "09", "OLD", "31"));
    	create( new BookDTO("撒母耳记下", "smejx", "10", "OLD", "24"));
    	create( new BookDTO("列王记上", "lwjs", "11", "OLD", "22"));
    	create( new BookDTO("列王记下", "lwjx", "12", "OLD", "25"));
    	create( new BookDTO("历代志上", "ldzs", "13", "OLD", "29"));
    	create( new BookDTO("历代志下", "ldzx", "14", "OLD", "36"));
    	create( new BookDTO("以斯拉记", "yslj", "15", "OLD", "10"));
    	create( new BookDTO("尼希米记", "nxmj", "16", "OLD", "13"));
    	create( new BookDTO("以斯帖记", "ystj", "17", "OLD", "10"));
    	create( new BookDTO("约伯记", "ybj", "18", "OLD", "42"));
    	create( new BookDTO("诗篇", "sp", "19", "OLD", "150"));
    	create( new BookDTO("箴言", "zy", "20", "OLD", "31"));
    	create( new BookDTO("传道书", "cds", "21", "OLD", "12"));
    	create( new BookDTO("雅歌", "yg", "22", "OLD", "8"));
    	create( new BookDTO("以赛亚书", "ysys", "23", "OLD", "66"));
    	create( new BookDTO("耶利米书", "ylms", "24", "OLD", "52"));
    	create( new BookDTO("耶利米哀歌", "ylmag", "25", "OLD", "5"));
    	create( new BookDTO("以西结书", "yxjs", "26", "OLD", "48"));
    	create( new BookDTO("但以理书", "dyls", "27", "OLD", "12"));
    	create( new BookDTO("何西阿书", "hxas", "28", "OLD", "14"));
    	create( new BookDTO("约珥书", "yes", "29", "OLD", "3"));
    	create( new BookDTO("阿摩司书", "amss", "30", "OLD", "9"));
    	create( new BookDTO("俄巴底亚书", "ebdys", "31", "OLD", "1"));
    	create( new BookDTO("约拿书", "yns", "32", "OLD", "4"));
    	create( new BookDTO("弥迦书", "mjs", "33", "OLD", "7"));
    	create( new BookDTO("那鸿书", "nhs", "34", "OLD", "3"));
    	create( new BookDTO("哈巴谷书", "hbgs", "35", "OLD", "3"));
    	create( new BookDTO("西番雅书", "xfys", "36", "OLD", "3"));
    	create( new BookDTO("哈该书", "hgs", "37", "OLD", "2"));
    	create( new BookDTO("撒迦利亚书", "sjlys", "38", "OLD", "14"));
    	create( new BookDTO("玛拉基书", "mljs", "39", "OLD", "4"));
    	
    	create( new BookDTO("马太福音", "mtfy", "40", "NEW", "28"));
    	create( new BookDTO("马可福音", "mkfy", "41", "NEW", "16")); 
    	create( new BookDTO("路加福音", "ljfy", "42", "NEW", "24"));
    	create( new BookDTO("约翰福音", "yhfy", "43", "NEW", "21"));
    	create( new BookDTO("使徒行传", "stxz", "44", "NEW", "28"));
    	create( new BookDTO("罗马书", "lms", "45", "NEW", "16"));
    	create( new BookDTO("哥林多前书", "gldqs", "46", "NEW", "16"));
    	create( new BookDTO("哥林多后书", "gldhs", "47", "NEW", "13"));
    	create( new BookDTO("加拉太书", "jlts", "48", "NEW", "6"));
    	create( new BookDTO("以弗所书", "yfss", "49", "NEW", "6"));
    	create( new BookDTO("腓立比书", "flbs", "50", "NEW", "4"));
    	create( new BookDTO("歌罗西书", "glxs", "51", "NEW", "4"));
    	create( new BookDTO("帖撒罗尼加前书", "tslnjqs", "52", "NEW", "5"));
    	create( new BookDTO("帖撒罗尼加后书", "tslnjhs", "53", "NEW", "3"));
    	create( new BookDTO("提摩太前书", "tmtqs", "54", "NEW", "6"));
    	create( new BookDTO("提摩太后书", "tmths", "55", "NEW", "4"));
    	create( new BookDTO("提多书", "tds", "56", "NEW", "3"));
    	create( new BookDTO("腓立门书", "flbs", "57", "NEW", "1"));
    	create( new BookDTO("希伯来书", "xbls", "58", "NEW", "13"));
    	create( new BookDTO("雅各书", "ygs", "59", "NEW", "5"));
    	create( new BookDTO("彼得前书", "bdqs", "60", "NEW", "5"));
    	create( new BookDTO("彼得后书", "bdhs", "61", "NEW", "3"));
    	create( new BookDTO("约翰一书", "yhys", "62", "NEW", "5"));
    	create( new BookDTO("约翰二书", "yhes", "63", "NEW", "1"));
    	create( new BookDTO("约翰三书", "yhss", "64", "NEW", "1"));
    	create( new BookDTO("犹大书", "yds", "65", "NEW", "1"));
    	create( new BookDTO("启示录", "qsl", "66", "NEW", "22"));
    }
    
    /*
     * When upgrading the database, it will drop the current table and recreate.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + tableName;
        db.execSQL(sql);

        onCreate(db);
    }

    /*
     * create new record
     * @param myObj contains details to be added as single row.
     */
    public boolean create(BookDTO book) {

        boolean createSuccessful = false;

        if(!checkIfExists(book.getName())){
                    
            SQLiteDatabase db = this.getWritableDatabase();
            
            ContentValues values = new ContentValues();
            values.put(fieldName, book.getName());
            values.put(fieldSearchString, book.getSearchString());
            values.put(fieldNumber, book.getNumber());
            values.put(fieldTestament, book.getTestament());
            values.put(fieldChapterCount, book.getChapterCount());
            
            createSuccessful = db.insert(tableName, null, values) > 0;
            
            db.close();
            
            if(createSuccessful){ 
                Log.e(TAG, book.getName() + " created.");
            }
        }
        
        return createSuccessful;
    }
    
    // check if a record exists so it won't insert the next time you run this code
    public boolean checkIfExists(String bookName){
        
        boolean recordExists = false;
                
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + fieldName + " FROM " + tableName + " WHERE " + fieldName + " = '" + bookName + "'", null);
        
        if(cursor!=null) {
            
            if(cursor.getCount()>0) {
                recordExists = true;
            }
        }

        cursor.close();
        db.close();
        
        return recordExists;
    }

    /*
     * Read records related to the search term
     */
    public BookDTO[] read(String searchTerm) {

        // select query
        String sql = "";
        sql += "SELECT * FROM " + tableName;
        sql += " WHERE " + fieldSearchString + " LIKE '%" + searchTerm + "%'";
        sql += " ORDER BY " + fieldId + " DESC";
        sql += " LIMIT 0,5";

        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery(sql, null);

        int recCount = cursor.getCount();
        
        BookDTO[] books = new BookDTO[recCount];
        int x = 0;
        
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                String name = cursor.getString(cursor.getColumnIndex(fieldName));
                String searchString = cursor.getString(cursor.getColumnIndex(fieldSearchString));
                String number = cursor.getString(cursor.getColumnIndex(fieldNumber));
                String testament= cursor.getString(cursor.getColumnIndex(fieldTestament));
                String chapterCount = cursor.getString(cursor.getColumnIndex(fieldChapterCount));
                
                BookDTO book = new BookDTO(name, searchString, number, testament, chapterCount);

                books[x] = book;
                
                x++;
                
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return books;
        
    }
    
    public BookDTO getBook(String bookName) {

        // select query
        String sql = "";
        sql += "SELECT * FROM " + tableName;
        sql += " WHERE " + fieldName + " LIKE '%" + bookName + "%'";
        sql += " ORDER BY " + fieldId + " DESC";
        sql += " LIMIT 0,5";

        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery(sql, null);

        int recCount = cursor.getCount();
        
        BookDTO book = null;
        
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex(fieldName));
            String searchString = cursor.getString(cursor.getColumnIndex(fieldSearchString));
            String number = cursor.getString(cursor.getColumnIndex(fieldNumber));
            String testament= cursor.getString(cursor.getColumnIndex(fieldTestament));
            String chapterCount = cursor.getString(cursor.getColumnIndex(fieldChapterCount));
            
            book = new BookDTO(name, searchString, number, testament, chapterCount);

                
        }

        cursor.close();
        db.close();

        return book;
        
    }

    public ArrayList<BookDTO> getBooks(String testament) {
    	
    	ArrayList <BookDTO> books = new ArrayList<BookDTO> ();
    	
        // select query
        String sql = "";
        sql += "SELECT * FROM " + tableName;
        sql += " WHERE " + fieldTestament + " = '" + testament.toUpperCase() + "'";
        sql += " ORDER BY " + fieldId + " DESC";
//        sql += " LIMIT 0,5";

        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery(sql, null);

        int recCount = cursor.getCount();
        
        BookDTO book = null;
        
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex(fieldName));
            String searchString = cursor.getString(cursor.getColumnIndex(fieldSearchString));
            String number = cursor.getString(cursor.getColumnIndex(fieldNumber));
//            String testament= cursor.getString(cursor.getColumnIndex(fieldTestament));
            String chapterCount = cursor.getString(cursor.getColumnIndex(fieldChapterCount));
            
            book = new BookDTO(name, searchString, number, testament.toUpperCase(), chapterCount);
            
            books.add(book);
            
                
        }

        cursor.close();
        db.close();

        return books;
        
    }
}