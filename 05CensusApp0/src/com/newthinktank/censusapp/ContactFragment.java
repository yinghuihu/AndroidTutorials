package com.newthinktank.censusapp;

import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class ContactFragment extends Fragment {
	
	// NEW Store the ID NUMBER for the current Contact
	// This is the key of the key / value pair that will
	// store the Contacts Id Number
	
	public static final String CONTACT_ID =
			"com.newthinktank.censusapp.contact_id";
	
	// END OF NEW

	private Contact contact;
	private EditText contactNameEditText;

	private EditText contactStreetEditText;
	private EditText contactCityEditText;
	private EditText contactPhoneEditText;
	
	private CheckBox contactedCheckBox;
	
	// NEW
	
	public static ContactFragment newContactFragment(UUID contactId){
		
		// A Bundle is used to pass data between Activitys
		
		Bundle passedData = new Bundle();
		
		// Put the Contacts ID in the Bundle
		
		passedData.putSerializable(CONTACT_ID, contactId);
		
		ContactFragment contactFragment = new ContactFragment();
		
		contactFragment.setArguments(passedData);
		
		return contactFragment;
		
	}
	
	// END OF NEW
	

	// Generate this with Right Click - Source - Override/Implement methods
	// This method is called when the Fragment is called for.
	// We initialize everything here.

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// NEW Replace contact = new Contact();
		// Get the value from CONTACT_ID that was passed in
		
		UUID contactId = (UUID) getArguments().getSerializable(CONTACT_ID);
		
		// Get the Contact with the matching ID
		
		contact = AllContacts.get(getActivity()).getContact(contactId);
		
		// END OF NEW
		
	}

	// Used to inflate the Fragment, or show it on the screen

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Pass in the layout to inflate, the views parent and whether
		// to add the inflated view to the parent.
		// We mark this false because the Activity will add the view.

		View theView = inflater.inflate(R.layout.fragment_contact, container, false);

		// Get a reference to the EditText

		contactNameEditText = (EditText) theView.findViewById(R.id.contactNameEditText);

		// If text in the EditText box is edited it will change the
		// name.
		
		contactStreetEditText = (EditText) theView.findViewById(R.id.contactStreetEditText);
		contactCityEditText = (EditText) theView.findViewById(R.id.contactCityEditText);
		contactPhoneEditText = (EditText) theView.findViewById(R.id.contactPhoneEditText);
		
		// All the EditText components will use just one TextWatcher
		// which auto updates Contact.java

		TextWatcher editTextWatcher = new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {

				if (contactNameEditText.hasFocus() == true){

					contact.setName(arg0.toString());

				} else if (contactStreetEditText.hasFocus() == true){
					
					contact.setStreetAddress(arg0.toString());
					
				} else if (contactCityEditText.hasFocus() == true){
					
					contact.setCity(arg0.toString());
					
				} else if (contactPhoneEditText.hasFocus() == true){
					
					contact.setPhoneNumber(arg0.toString());
					
				}

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start,
					int count, int after) {
				// TODO Auto-generated method stub

			}
		};

		
		contactStreetEditText.addTextChangedListener(editTextWatcher);
		contactCityEditText.addTextChangedListener(editTextWatcher);
		contactPhoneEditText.addTextChangedListener(editTextWatcher);

		contactNameEditText.addTextChangedListener(editTextWatcher);

		// Create CheckBox Listener
		
		contactedCheckBox = (CheckBox) theView.findViewById(R.id.contactedCheckBox);
		
		contactedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				
				// NEW Change the value of checked to the opposite
				// of what it was if clicked
				
				contact.setContacted(arg1);
				
				// END OF NEW
				
			}
			
		});
		
		// NEW
		// Get the values for the current Contact and put them in
		// the right Components
		
		contactNameEditText.setText(contact.getName());
		contactStreetEditText.setText(contact.getStreetAddress());;
		contactCityEditText.setText(contact.getCity());
		contactPhoneEditText.setText(contact.getPhoneNumber());
		contactedCheckBox.setChecked(contact.getContacted());
		
		// END OF NEW


		// Pass in the layout to inflate, the views parent and whether
		// to add the inflated view to the parent.
		// We mark this false because the Activity will add the view.

		return theView;

	}

}