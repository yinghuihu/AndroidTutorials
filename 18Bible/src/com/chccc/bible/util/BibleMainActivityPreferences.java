package com.chccc.bible.util;

import com.chccc.bible.BibleMainActivity;

import android.content.SharedPreferences;

public class BibleMainActivityPreferences {
	
	public static final String PREFS_NAME = "com.chccc.bible";
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	
	private String bibleVersion = "";
	private String bookNumber = "";
	private String chapterNumber = "";
	
	private String theme = "";
	
	private BibleMainActivity bibleMainActivity;
	
	public BibleMainActivityPreferences(BibleMainActivity bibleMainActivity) {
		this.bibleMainActivity = bibleMainActivity;
		
		settings = bibleMainActivity.getSharedPreferences(PREFS_NAME, 0);
		editor = settings.edit();
		
		if (!settings.getBoolean("preference_setted", false)) {
			editor.putString("BibleVersion", "hhb");
			editor.putString("bookNumber", "40");
			editor.putString("chapterNumber", "1");
			editor.putBoolean("preference_setted", true);
		}
		
	    
	}

	public String getBibleVersion() {
		return settings.getString("BibleVersion", "hhb");
	}

	public void setBibleVersion(String bibleVersion) {
		editor.putString("BibleVersion", bibleVersion);
	}

	public String getBookNumber() {
		return settings.getString("bookNumber", "40");
	}

	public void setBookNumber(String bookNumber) {
		editor.putString("bookNumber", bookNumber);
	}

	public String getChapterNumber() {
		return settings.getString("chapterNumber", "1");
	}

	public void setChapterNumber(String chapterNumber) {
		editor.putString("chapterNumber", chapterNumber);
	}

	public String getTheme() {
		return settings.getString("theme", "");
	}

	public void setTheme(String theme) {
		editor.putString("theme", theme);
	}
	
	public void commit() {
		editor.commit();
	}
	
	
}
