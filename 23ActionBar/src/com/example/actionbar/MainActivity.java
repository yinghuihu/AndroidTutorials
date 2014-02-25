package com.example.actionbar;

import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

public class MainActivity extends Activity {

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        getActionBar().setIcon(R.drawable.ic_action_android);
	    }
	 
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.main, menu);
	 
	        
	        // Associate searchable configuration with the SearchView
	        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
	                .getActionView();
	        searchView.setSearchableInfo(searchManager
	                .getSearchableInfo(getComponentName()));
	 
	        return super.onCreateOptionsMenu(menu);
	        
	    }
	    
	    /**
	     * On selecting action bar icons
	     * */
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Take appropriate action for each action item click
	        switch (item.getItemId()) {
	        case R.id.action_search:
	            // search action
	            return true;
	        case R.id.action_location_found:
	            // location found
	            LocationFound();
	            return true;
	        case R.id.action_refresh:
	            // refresh
	            return true;
	        case R.id.action_help:
	            // help action
	            return true;
	        case R.id.action_check_updates:
	            // check for updates action
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }
	 
	    /**
	     * Launching new activity
	     * */
	    private void LocationFound() {
	        Intent i = new Intent(MainActivity.this, LocationFound.class);
	        startActivity(i);
	    }

}
