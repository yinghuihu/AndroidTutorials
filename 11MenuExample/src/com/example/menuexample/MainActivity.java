package com.example.menuexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	    }
	     
	    // Initiating Menu XML file (menu.xml)
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu)
	    {
	        MenuInflater menuInflater = getMenuInflater();
	        menuInflater.inflate(R.menu.main, menu);
	        return true;
	    }
	     
	    /**
	     * Event Handling for Individual menu item selected
	     * Identify single menu item by it's id
	     * */
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item)
	    {
	         
	        switch (item.getItemId())
	        {
	        case R.id.menu_bookmark:
	            // Single menu item is selected do something
	            // Ex: launching new activity/screen or show alert message
	            Toast.makeText(MainActivity.this, "Bookmark is Selected", Toast.LENGTH_SHORT).show();
	            return true;
	 
	        case R.id.menu_save:
	            Toast.makeText(MainActivity.this, "Save is Selected", Toast.LENGTH_SHORT).show();
	            return true;
	 
	        case R.id.menu_search:
	            Toast.makeText(MainActivity.this, "Search is Selected", Toast.LENGTH_SHORT).show();
	            return true;
	 
	        case R.id.menu_share:
	            Toast.makeText(MainActivity.this, "Share is Selected", Toast.LENGTH_SHORT).show();
	            return true;
	 
	        case R.id.menu_delete:
	            Toast.makeText(MainActivity.this, "Delete is Selected", Toast.LENGTH_SHORT).show();
	            return true;
	 
	        case R.id.menu_preferences:
	            Toast.makeText(MainActivity.this, "Preferences is Selected", Toast.LENGTH_SHORT).show();
	            return true;
	 
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }    
	 
	}