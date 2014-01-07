package com.chccc.bible;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

    // for our logs
    public static final String TAG = "DatabaseHandler.java";

    // database version
    private static final int DATABASE_VERSION = 5;

    // database name
    protected static final String DATABASE_NAME = "Bible";

    // table details
    public String tableName = "book";
    public String fieldId = "id";
    public String fieldName = "name";
    public String fieldSearchString = "searchString";
    public String fieldNumber = "number";

    // constructor
    public DatabaseHandler(Context context) {
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
        sql += fieldNumber + " TEXT ";
        sql += " ) ";

        db.execSQL(sql);

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
    public boolean create(Book book) {

        boolean createSuccessful = false;

        if(!checkIfExists(book.getName())){
                    
            SQLiteDatabase db = this.getWritableDatabase();
            
            ContentValues values = new ContentValues();
            values.put(fieldName, book.getName());
            values.put(fieldSearchString, book.getSearchString());
            values.put(fieldNumber, book.getNumber());
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
    public Book[] read(String searchTerm) {

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
        
        Book[] books = new Book[recCount];
        int x = 0;
        
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                String name = cursor.getString(cursor.getColumnIndex(fieldName));
                String searchString = cursor.getString(cursor.getColumnIndex(fieldSearchString));
                String number = cursor.getString(cursor.getColumnIndex(fieldNumber));
                
                Book book = new Book(name, searchString, number);

                books[x] = book;
                
                x++;
                
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return books;
        
    }

}