package com.chccc.bible.dto;

public class BookDTO {
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
	
	public BookDTO(String name, String searchString, String number) {
		this.name= name;
		this.searchString = searchString;
		this.number = number;
	}
}
