package com.chccc.bible;

public class Book {
	private String name;
	private String searchString;
	private String number;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public Book(String name, String searchString, String number) {
		this.name= name;
		this.searchString = searchString;
		this.number = number;
	}
}
