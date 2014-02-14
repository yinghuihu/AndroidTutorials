package com.chccc.bible.activity;

import com.chccc.bible.R;
import com.chccc.bible.R.id;
import com.chccc.bible.R.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

// NEW : This class will use the FragmentManager to ad 
// Fragments to multiple Activity views

public abstract class FragmentActivityBuilder extends FragmentActivity{
	
	// This method must be implemented so that the right
	// type of Fragment can be returned.
	// CensusApp gets ContactFragment()
	// ContactListActivity gets FragmentContactList()
	
	protected abstract Fragment createFragment();

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

		setContentView(R.layout.activity_main);

		FragmentManager fragManager = getSupportFragmentManager();

		// Check if the FragmentManager knows about the Fragment 
		// id we refer to

		Fragment theFragment = fragManager.findFragmentById(R.id.fragmentContainer);

		// Check if the Fragment was found

		if(theFragment == null){

			// If the Fragment wasn't found then we must create it

			// NEW We can generate many types of Fragments by having 
			// CreateFragment define the type. So
			// theFragment = new ContactFragment();
			// is replaced by 
			
			theFragment = createFragment();
			

			// Creates and commits the Fragment transaction
			// Fragment transactions add, attach, detach, replace
			// and remove Fragments.

			// add() gets the location to place the Fragment into and
			// the Fragment itself.

			fragManager.beginTransaction()
				.add(R.id.fragmentContainer, theFragment)
				.commit();

		}

	}

}