package com.example.scrollviewbasic;

import java.io.InputStream;
import java.util.Scanner;

import com.example.scrolltextbasic.R;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView textViewHymn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textViewHymn = (TextView) findViewById(R.id.TextViewHymn);
		
		Log.d("Ying", "I am here");

		textViewHymn.setText(readHymn("002"));
		
		Log.d("Ying", "I am here2");
		
		textViewHymn.setTextSize(18);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private String readHymn(String hymnNumber) {
		InputStream is  = null;
		String inputStreamString = null;
		
		try {
			is = this.getAssets().open("h" + hymnNumber + ".txt");
			
			Log.d("Ying", "I am here inside readhymn");
			
			inputStreamString = new Scanner(is,"UTF-8").useDelimiter("\\A").next();
			
		    is.close();
		    
	     } catch (Exception em) {
	    	 Log.d("Ying", em.getMessage());
	    	 em.printStackTrace();
		 } 

	     return inputStreamString;
	    }
}
