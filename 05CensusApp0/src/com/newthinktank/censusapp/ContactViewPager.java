package com.newthinktank.censusapp;

// ALL 100% NEW

import java.util.ArrayList;
import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

// ViewPager is a layout manager that allows you to flip left 
// and right through pages in your app.

public class ContactViewPager extends FragmentActivity{
	
	private ViewPager theViewPager;
	
	private ArrayList<Contact> contactList;

	@Override
	protected void onCreate(Bundle arg0) {

		super.onCreate(arg0);
		
		theViewPager = new ViewPager(this);
		
		// When using FragmentPagerAdapter the ViewPager must
		// have an id (/res/values/ids.xml)
		
		theViewPager.setId(R.id.viewPager);
		
		// Set the current View for the ViewPager
		
		setContentView(theViewPager);
		
		// Checks if an instance of allContacts exists. If it does
		// the one instance is returned. Otherwise the instance is
		// created. (Remember there is only 1 contact list)
		
		contactList = AllContacts.get(this).getContactList();
		
		// The FragmentManager ads Fragments to Activity views
		
		FragmentManager fragManager = getSupportFragmentManager();
		
		// Just like the AdapterView requires an Adapter like we
		// used in FragmentContactList, a ViewPager requires a
		// PagerAdapter.
		
		// The PagerAdapter provides the Adapter for populating 
		// pages inside of the ViewPager. 
		
		// The FragmentStatePagerAdapter implements the PagerAdapter.
		// It uses a Fragment to manage each page. It also saves
		// and restores each pages state.
		
		theViewPager.setAdapter(new FragmentStatePagerAdapter(fragManager) {
			
			@Override
			public Fragment getItem(int position) {
				
				// Gets the specific Contact from the right position
				// in the ArrayList
				
				Contact theContact = contactList.get(position);
				
				// Return a ContactFragment by retrieving the id 
				// number from the current Contact
				
				return ContactFragment.newContactFragment(theContact.getIdNumber());
				
			}

			// Returns the number of items in the ArrayList
			
			@Override
			public int getCount() {
				return contactList.size();
			}
			
		});
		
		// If you run the app now the pages can be flipped through
		// but if you click on one from the list it will always 
		// display the first. Let's change that by setting the ViewPagers
		// current item to the Contact clicked on
		
		UUID contactId = (UUID) getIntent()
				.getSerializableExtra(ContactFragment.CONTACT_ID);
		
		// Cycle through the Contacts in the ArrayList to find a match
		// Set the current position of the match in setCurrentItem
		
		for(int i = 0; i < contactList.size(); i++){
			
			if(contactList.get(i).getIdNumber().equals(contactId)){
				
				theViewPager.setCurrentItem(i);
				break;
				
			}
			
		}
		
		theViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				
				setTitle("Citizen #" + position);
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
}