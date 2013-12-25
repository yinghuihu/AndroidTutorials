package com.chccc.hymn;

public class HymnHeader {
	
	private String number = "";
	private String title = "";
	private String subTitle = "";
	private String searchString = "";
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
	public String toString() {
		String s = "Number is " + this.number + "\n";
		s = s + "Title is " + this.title+ "\n";
		s = s + "SubTitle is " + this.subTitle+ "\n";
		s = s + "SearchString  is " + this.searchString+ "\n";
		return s;
	
	}
}
