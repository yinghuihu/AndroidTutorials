package com.newthinktank.censusapp;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

// The ListFragment displays a list of items in a 
// ListView, by binding to our ArrayList using an
// ArrayAdapter in this situation.

public class FragmentContactList extends ListFragment {
	
	// Stores the list of Contacts
	
	private ArrayList<Contact> contactList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Change the title for the current Activity
		
		getActivity().setTitle(R.string.fragment_contact_list_title);
		
		// Get the ArrayList from AllContacts
		
		contactList = AllContacts.get(getActivity()).getContactList();
		
		ContactAdapter contactAdapter = new ContactAdapter(contactList);
		
		// Provides the data for the ListView by setting the Adapter 
		
		setListAdapter(contactAdapter);
		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		
		Contact clickedContact = ((ContactAdapter) getListAdapter()).getItem(position);
		
		// NEW
		// We want our ContactViewPager to be called now if they click 
		// on a Contact
		
		// Intent newIntent = new Intent(getActivity(), CensusApp.class);
		
		// startActivityForResult(newIntent, 0);
		
		Intent newIntent = new Intent(getActivity(), ContactViewPager.class);
		
		newIntent.putExtra(ContactFragment.CONTACT_ID, clickedContact.getIdNumber());
		
		startActivity(newIntent);
		
		// END OF NEW
		
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		((ContactAdapter) getListAdapter()).notifyDataSetChanged();
		
	}




	private class ContactAdapter extends ArrayAdapter<Contact> {

		public ContactAdapter(ArrayList<Contact> contacts) {
	    	
	    		// An Adapter acts as a bridge between an AdapterView and the 
				// data for that view. The Adapter also makes a View for each 
				// item in the data set. (Each list item in our ListView)
			
				// The constructor gets a Context so it so it can use the 
				// resource being the simple_list_item and the ArrayList
				// android.R.layout.simple_list_item_1 is a predefined 
				// layout provided by Android that stands in as a default
	    	
	            super(getActivity(), android.R.layout.simple_list_item_1, contacts);
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
				
				convertView = getActivity().getLayoutInflater()
						.inflate(R.layout.list_item_contact, null);
				
			}
			
			// Find the right data to put in the list item
			
			Contact theContact = getItem(position);
			
			// Put the right data into the right components
			
			TextView contactNameTextView =
	                (TextView)convertView.findViewById(R.id.contact_name);
			
			contactNameTextView.setText(theContact.getName());
			
	        TextView streetTextView =
	                (TextView)convertView.findViewById(R.id.contact_street);
	        
	        streetTextView.setText(theContact.getStreetAddress());
	        
	        CheckBox contactedCheckBox =
	                (CheckBox)convertView.findViewById(R.id.contact_contacted_checkbox);
	        
	        contactedCheckBox.setChecked(theContact.getContacted());
			
			// Return the finished list item for display
			
	        return convertView;
			
		}
		
	}

}