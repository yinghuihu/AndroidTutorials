package com.chccc.bible;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	AutoCompleteTextView textView=null;
	private ArrayAdapter<String> adapter;
	 
//	// Store contacts values in these arraylist
//	public static ArrayList<String> phoneValueArr = new ArrayList<String>();
//	public static ArrayList<String> nameValueArr = new ArrayList<String>();
	 
	EditText toNumber=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 textView = (AutoCompleteTextView) findViewById(R.id.toNumber);
		 
		 adapter = new ArrayAdapter<String> (this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());
		 textView.setThreshold(1);

		 //	Set adapter to AutoCompleteTextView
		 textView.setAdapter(adapter);
		 
		 readData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	private void readData() {
		adapter.add("马太福音 - mtfy");
		adapter.add("马可福音 - mkfy");
		adapter.add("路加福音 - ljfy");
		adapter.add("约翰福音 - yhfy");
		 
		// Add ArrayList names to adapter
		//phoneValueArr.add(phoneNumber.toString());
		//nameValueArr.add(name.toString());
   }
}
