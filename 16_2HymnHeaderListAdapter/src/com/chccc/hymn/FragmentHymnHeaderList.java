package com.chccc.hymn;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

// The ListFragment displays a list of items in a 
// ListView, by binding to our ArrayList using an
// ArrayAdapter in this situation.

public class FragmentHymnHeaderList extends ListFragment {
	
	public static final String HYMN_HEADER_NUMBER = "com.chccc.hymn.header.number";
	private ArrayList<HymnHeader> hhl;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Change the title for the current Activity
		
		getActivity().setTitle(R.string.fragment_hymnheader_list_title);
		
		// Get the ArrayList from AllContacts
		
		hhl = HymnHeaderXmlParser.getHymnHeaders(this.getActivity());
		
		HymnHeaderAdapter hymnHeaderAdapter = new HymnHeaderAdapter(hhl);
		
		// Provides the data for the ListView by setting the Adapter 
		
		setListAdapter(hymnHeaderAdapter);
		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		
		HymnHeader clickedHymnHeader = ((HymnHeaderAdapter) getListAdapter()).getItem(position);
		
		// NEW
		// We want our ContactViewPager to be called now if they click 
		// on a Contact
		
		Intent newIntent = new Intent(getActivity(), HymnMainActivity.class);
		
//		startActivityForResult(newIntent, 0);
		
//		Intent newIntent = new Intent(getActivity(), ContactViewPager.class);
		
		newIntent.putExtra(HYMN_HEADER_NUMBER, clickedHymnHeader.getNumber());
//		
		startActivity(newIntent);
		
		// END OF NEW
		
	}

//	@Override
//	public void onActivityResult(int requestCode, int resultCode, Intent data) {
//		
//		((ContactAdapter) getListAdapter()).notifyDataSetChanged();
//		
//	}




	private class HymnHeaderAdapter extends ArrayAdapter<HymnHeader> {

		public HymnHeaderAdapter(ArrayList<HymnHeader> hhl) {
	    	
	    		// An Adapter acts as a bridge between an AdapterView and the 
				// data for that view. The Adapter also makes a View for each 
				// item in the data set. (Each list item in our ListView)
			
				// The constructor gets a Context so it so it can use the 
				// resource being the simple_list_item and the ArrayList
				// android.R.layout.simple_list_item_1 is a predefined 
				// layout provided by Android that stands in as a default
	    	
	            super(getActivity(), android.R.layout.simple_list_item_1, hhl);
	    }
		
		// getView is called each time it needs to display a new list item
		// on the screen because of scrolling for example.
		// The Adapter is asked for the new list row and getView provides
		// it.
		// position represents the position in the Array from which we will 
		// be pulling data.
		// convertView is a pre-created list item that will be reconfigured 
		// in the code that follows.
		// ViewGroup is our ListView
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			// Check if this is a recycled list item and if not we inflate it
			
			if(convertView == null){
				convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_hymnheader, null);
			}
			
			// Find the right data to put in the list item
			HymnHeader theHymnHeaderItem = getItem(position);
			
			// Put the right data into the right components
			
			TextView hymnHeaderTitleTextView = (TextView)convertView.findViewById(R.id.hymnHeaderTitle);
			
			hymnHeaderTitleTextView.setText(theHymnHeaderItem.getTitle());
			
	        return convertView;
			
		}
		
	}

}