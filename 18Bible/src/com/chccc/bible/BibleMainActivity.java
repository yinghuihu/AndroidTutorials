package com.chccc.bible;

import java.util.ArrayList;

import com.chccc.bible.dto.ChapterDTO;
import com.chccc.bible.util.BibleMainActivityPreferences;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BibleMainActivity extends Activity {
	
	public static BibleMainActivityPreferences preferences;
	
	public final static int MENU_BIBLE_CHAPTER = 1; 
	
	public final static int MENU_BIBLE_VERSION_MIX = 7;
	public final static int MENU_BIBLE_VERSION_HHB = 8;
	public final static int MENU_BIBLE_VERSION_NIV= 9;
	
	public final static int MENU_BIBLE_NEXT_CHAPTER = 10;
	public final static int MENU_BIBLE_PREVIOUS_CHAPTER = 11;
	
	private static int fontSize = 18; 
	
	
	MenuItem hhbMenu = null;
	MenuItem nivMenu = null;
	MenuItem mixMenu = null;

	public static void setFontSize(int fontSize) {
		BibleMainActivity.fontSize = fontSize;
	}

	LinearLayout bibleContainer ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bible_activity_main);
		
		bibleContainer = (LinearLayout) findViewById(R.id.bibleContainer );
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		menu.add(0, this.MENU_BIBLE_CHAPTER, 0, this.getString(R.string.menu_text_choose_bible));

		menu.add(0, this.MENU_BIBLE_PREVIOUS_CHAPTER, 0, this.getString(R.string.menu_text_previous_chapter));
		menu.add(0, this.MENU_BIBLE_NEXT_CHAPTER, 0, this.getString(R.string.menu_text_next_chapter));
		
		mixMenu = menu.add(0, this.MENU_BIBLE_VERSION_MIX, 0, this.getString(R.string.menu_text_bible_version_mix));
		mixMenu.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
		hhbMenu = menu.add(0, this.MENU_BIBLE_VERSION_HHB, 0, this.getString(R.string.menu_text_bible_version_hhb));
		hhbMenu.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
				
		nivMenu = menu.add(0, this.MENU_BIBLE_VERSION_NIV, 0, this.getString(R.string.menu_text_bible_version_niv));
		nivMenu.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
		readBible();
		return true;
	}
	
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		super.onMenuItemSelected(featureId, item);
		
		int currentChapterNumber = 0;
		
		switch (item.getItemId()){
		
		case MENU_BIBLE_CHAPTER:
			Intent intent = new Intent(this, BibleChapterChooserActivity.class);
			this.startActivity(intent);
			break;
		case MENU_BIBLE_PREVIOUS_CHAPTER:
			
			currentChapterNumber = Integer.parseInt(preferences.getChapterNumber());
			
			if (currentChapterNumber != 1) {
				currentChapterNumber--;
				
				preferences.setChapterNumber("" + currentChapterNumber);
				preferences.commit();
				readBible();
			} else {
				Toast.makeText(getApplicationContext(), R.string.alert_first_chapter_already, Toast.LENGTH_SHORT).show();
			}
			
			break;
		case MENU_BIBLE_NEXT_CHAPTER:
			
			currentChapterNumber = Integer.parseInt(preferences.getChapterNumber());
			
			int bookTotalChapterCount = Integer.parseInt(preferences.getBookTotalChapter());
			
			if (currentChapterNumber < bookTotalChapterCount) {
				currentChapterNumber ++;
				
				preferences.setChapterNumber("" + currentChapterNumber);
				preferences.commit();
				
				readBible();
			} else {
				Toast.makeText(getApplicationContext(), R.string.alert_last_chapter_already, Toast.LENGTH_SHORT).show();
			}
			
			break;
		case MENU_BIBLE_VERSION_HHB:
			preferences.setBibleVersion("hhb");
			preferences.commit();
			readBible();
			break;
		case MENU_BIBLE_VERSION_MIX:
			preferences.setBibleVersion("hhb");
			preferences.commit();
			readBible();
			break;
		case MENU_BIBLE_VERSION_NIV:
			preferences.setBibleVersion("niv");
			preferences.commit();
			readBible();
			break;
		}
		
		return true;
	}

	
	@Override
	public void onBackPressed() {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setTitle(this.getString(R.string.alert_dialog_title));

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
	
	
	private void readBible() {
		bibleContainer.removeAllViews();
		
		preferences = new BibleMainActivityPreferences(this);
		
		String version = preferences.getBibleVersion();
		String bookNumber = preferences.getBookNumber();
		String chapterNumber = preferences.getChapterNumber();
		
		ChapterDTO chapter = ChapterXmlParser.getChapterContent(getApplicationContext(), version, bookNumber, chapterNumber);

		//validate the chapter choosen
		int bookTotalChapterCount = Integer.parseInt(preferences.getBookTotalChapter());
		if (Integer.parseInt(chapterNumber) > bookTotalChapterCount) {
			 preferences.setChapterNumber("1");
			 preferences.commit();
			 
			 chapterNumber = "1";
			 chapter = ChapterXmlParser.getChapterContent(getApplicationContext(), version, bookNumber, "1");
			 
			 Toast.makeText(getApplicationContext(), R.string.alert_chapter_number_exceed_max, Toast.LENGTH_LONG).show();
		}
		
		
		TextView textViewBookHeader = new TextView(this);
		textViewBookHeader.setTextSize(30);
		textViewBookHeader.setBackgroundColor(Color.parseColor(this.getString(R.string.color_hymn_header)));
		
		if (version.equalsIgnoreCase("hhb")) {
			textViewBookHeader.setText(chapter.getBookChineseName() + " - 第" + chapterNumber + "章");	
			nivMenu.setVisible(true);
			hhbMenu.setVisible(false);
		} else if (version.equalsIgnoreCase("niv")) {
			textViewBookHeader.setText(chapter.getBookEnglishName() + " - Chapter" + chapterNumber);
			hhbMenu.setVisible(true);
			nivMenu.setVisible(false);
		}
		
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
