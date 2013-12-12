package com.newthinktank.censusapp;

import java.util.UUID;

import android.util.Log;

public class Contact {
	
	private String name;
	private String phoneNumber;
	private String streetAddress;
	private String city;
	
	// NEW Add a Unique ID number for each person
	
	private UUID idNumber;
	private boolean contacted = false;
	
	public Contact(){
		
		// Generates a type 4 pseudo randomly generated UUID
		// Wiki : 1 billion UUIDs every second for the next 
		// 100 years, the probability of creating just one 
		// duplicate would be about 50%
		
		idNumber = UUID.randomUUID();
		
	}
	
	// END OF NEW

	public String getName() {
		return name;
	}

	public void setName(String name) {
		
		Log.e("CENSUS", "NAME CHANGED TO " + name);
		
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		
		Log.e("CENSUS", "PHONE CHANGED TO " + phoneNumber);
		
		this.phoneNumber = phoneNumber;
	}


	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		
		Log.e("CENSUS", "STREET CHANGED TO " + streetAddress);
		
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		
		Log.e("CENSUS", "CITY CHANGED TO " + city);
		
		this.city = city;
	}
	
	// NEW
	
	public boolean getContacted() {
		return contacted;
	}

	public void setContacted(boolean contacted) {
		
		Log.e("CENSUS", "CONTACTED CHANGED TO " + contacted);
		
		this.contacted = contacted;
	}

	public UUID getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(UUID idNumber) {
		this.idNumber = idNumber;
	}
	
	// END OF NEW

	
}