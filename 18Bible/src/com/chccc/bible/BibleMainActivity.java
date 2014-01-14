package com.chccc.bible;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BibleMainActivity extends Activity {

//	public final static int MENU_HYMN_NUMBER = 1; 
//	
//	public final static int MENU_HYMN_NUMBER_MULTIPLE = 2;
	
	private static int fontSize = 18; 
	

	public static void setFontSize(int fontSize) {
		BibleMainActivity.fontSize = fontSize;
	}

	//	TextView textViewHymn;
	LinearLayout bibleContainer ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bible_activity_main);
		
		bibleContainer = (LinearLayout) findViewById(R.id.bibleContainer );
		readBible("hhb", "40", "27", bibleContainer);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		
//		menu.add(0, this.MENU_HYMN_NUMBER, 0, this.getString(R.string.menu_text_choose_hymn));
//		
//		menu.add(0, this.MENU_HYMN_NUMBER_MULTIPLE, 0, this.getString(R.string.menu_text_choose_hymn_multiple));
		
		return true;
	}
	
	
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//		if (requestCode == MENU_HYMN_NUMBER) {
//
//			if (resultCode == RESULT_OK) {
//				String result = data.getStringExtra("result");
//				hymnContainer.removeAllViews();
//				readHymn (result, hymnContainer);
//				
//			}
//			if (resultCode == RESULT_CANCELED) {
//				// Write your code if there's no result
//			}
//		} else if (requestCode == MENU_HYMN_NUMBER_MULTIPLE) {
//			if (resultCode == RESULT_OK) {
//				String result = data.getStringExtra("result");
//				hymnContainer.removeAllViews();
//				
//				StringTokenizer st = new StringTokenizer(result, " ");
//				
//				String text = "";
//				while (st.hasMoreTokens()) {
//					readHymn (st.nextToken(), hymnContainer);
//				}
//			}
//			if (resultCode == RESULT_CANCELED) {
//				// Write your code if there's no result
//			}
//		}
//	}

	@Override
	public void onBackPressed() {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setTitle(this.getString(R.string.alert_dialog_title));
	    //builder.setMessage("Are You Sure?");

	    builder.setPositiveButton(this.getString(R.string.alert_dialog_ok_button_text), new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	                    dialog.dismiss();
	                    finish();
	            }
	        });

	    builder.setNegativeButton(this.getString(R.string.alert_dialog_notok_button_text), new DialogInterface.OnClickListener() {
	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	            dialog.dismiss();
	        }
	    });
	    AlertDialog alert = builder.create();
	    alert.show();
	}
	
//	@Override
//	public boolean onMenuItemSelected(int featureId, MenuItem item) {
//		super.onMenuItemSelected(featureId, item);
//		switch (item.getItemId()){
//		case MENU_HYMN_NUMBER:
//			Intent intent = new Intent(this, BibleNumberActivity.class);
//			this.startActivityForResult(intent, MENU_HYMN_NUMBER);
//			break;
//		case MENU_HYMN_NUMBER_MULTIPLE:
//			Intent intent2 = new Intent(this, BibleNumberMultipleActivity.class);
//			this.startActivityForResult(intent2, MENU_HYMN_NUMBER_MULTIPLE);
//		}
//		
//		return true;
//	}

	private void readBible(String version, String bookNumber, String chapterNumber, View v) {
		
		ChapterDO chapter = ChapterXmlParser.getChapterContent(getApplicationContext(), version, bookNumber, chapterNumber);
		
		TextView textViewBookHeader = new TextView(this);
		textViewBookHeader.setTextSize(30);
		textViewBookHeader.setBackgroundColor(Color.parseColor(this.getString(R.string.color_hymn_header)));
//		textViewBookHeader.setTypeface(null, Typeface.BOLD);
		textViewBookHeader.setText(chapter.getBookChineseName() + " " + chapterNumber);
		bibleContainer.addView(textViewBookHeader);
		
		String chapterContent = "";
		
		ArrayList<String> verses = chapter.getVerses();
		
		for (String verse: verses) {
			chapterContent = chapterContent + verse;
		}
		
		
		TextView textChapterContent = new TextView(this);
		textChapterContent.setTextSize(fontSize +4);
		textChapterContent.setText(chapterContent);
		Typeface face = Typeface.createFromAsset(getAssets(), "fonts/STKAITI.TTF");
		textChapterContent.setTypeface(face);
		bibleContainer.addView(textChapterContent);
	}
}
