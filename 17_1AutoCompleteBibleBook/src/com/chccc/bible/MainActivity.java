package com.chccc.bible;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	
	AutoCompleteTextView textView = null;
	ArrayAdapter<Book> adapter;
	
	DatabaseHandler databaseH;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		  // instantiate database handler
        databaseH = new DatabaseHandler(MainActivity.this);
        
        // put sample data to database
        insertBookData();
        
        try {
	        //backup database
	        copyAppDbToDownloadFolder();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        Book[] books = new Book[0];
		textView = (CustomAutoCompleteView) findViewById(R.id.toNumber);
		 
		textView.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));
		
		textView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                
                RelativeLayout rl = (RelativeLayout) arg1;
                TextView tv = (TextView) rl.getChildAt(0);
                textView.setText(tv.getText().toString());
                
            }

        });
		 adapter = new AutocompleteCustomArrayAdapter(this, R.layout.list_view_row, books);
         
		 textView.setThreshold(1);

		 //	Set adapter to AutoCompleteTextView
		 textView.setAdapter(adapter);
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	public void insertBookData(){
        // CREATE
        databaseH.create( new Book("马太福音", "mtfy", "40"));
        databaseH.create( new Book("马可福音", "mkfy", "41")); 
        databaseH.create( new Book("路加福音", "ljfy", "42"));
        databaseH.create( new Book("约翰福音", "yhfy", "43"));
    }
	
	public void copyAppDbToDownloadFolder() throws IOException {
        File backupDB = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "YingBible_Backup"); // for example "my_data_backup.db"
        File currentDB = getApplicationContext().getDatabasePath("Bible"); //databaseName=your current application database name, for example "my_data.db"
        if (currentDB.exists()) {
            FileChannel src = new FileInputStream(currentDB).getChannel();
            FileChannel dst = new FileOutputStream(backupDB).getChannel();
            dst.transferFrom(src, 0, src.size());
            src.close();
            dst.close();
        }
    }
}
