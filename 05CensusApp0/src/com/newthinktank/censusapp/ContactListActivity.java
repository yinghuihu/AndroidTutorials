package com.newthinktank.censusapp;

//We will use the android.support.v4.app.Fragment
//support library so our app runs on older versions
//of Android

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

//Change Activity to FragmentActivity

// The FragmentManager ads Fragments to an Activity's view

//NEW Change to extend FragmentActivityBuilder so that
//that class can handle the work FragmentManager must do

public class ContactListActivity extends FragmentActivityBuilder {

	/* This work is now handled by FragmentActivityBuilder
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_census_app);

		FragmentManager fragManager = getSupportFragmentManager();

		// Check if the FragmentManager knows about the Fragment 
		// id we refer to

		Fragment theFragment = fragManager.findFragmentById(R.id.fragmentContainer);

		// Check if the Fragment was found

		if(theFragment == null){

			// If the Fragment wasn't found then we must create it
			// We change this from ContactFragment, which we used 
			// in CensusApp

			theFragment = new FragmentContactList();

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
	
	*/
	
	// NEW : Call for FragmentActivityBuilder to have the FragmentManager
	// add the right Fragment to the Activity

	@Override
	protected Fragment createFragment() {
		return new FragmentContactList();
	}


}