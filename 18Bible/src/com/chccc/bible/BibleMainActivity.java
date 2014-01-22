package com.chccc.bible;

import java.util.ArrayList;

import com.chccc.bible.dto.ChapterDTO;
import com.chccc.bible.dto.VerseDTO;
import com.chccc.bible.util.BibleMainActivityPreferences;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
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
	
	public final static int MENU_BIBLE_INDEX = 12;
	
	private static int fontSize = 18; 
	
	TextView txtChapterHeader = null;
	
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
		txtChapterHeader = (TextView) findViewById(R.id.txtChapterHeader);
		bibleContainer = (LinearLayout) findViewById(R.id.bibleContainer );
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		MenuItem chapterChooserMenu = menu.add(0, this.MENU_BIBLE_CHAPTER, 0, this.getString(R.string.menu_text_choose_bible));
		chapterChooserMenu.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

		menu.add(0, this.MENU_BIBLE_PREVIOUS_CHAPTER, 0, this.getString(R.string.menu_text_previous_chapter));
		menu.add(0, this.MENU_BIBLE_NEXT_CHAPTER, 0, this.getString(R.string.menu_text_next_chapter));
		
		
		
		hhbMenu = menu.add(0, this.MENU_BIBLE_VERSION_HHB, 0, this.getString(R.string.menu_text_bible_version_hhb));
		hhbMenu.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
				
		nivMenu = menu.add(0, this.MENU_BIBLE_VERSION_NIV, 0, this.getString(R.string.menu_text_bible_version_niv));
		nivMenu.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
		mixMenu = menu.add(0, this.MENU_BIBLE_VERSION_MIX, 0, this.getString(R.string.menu_text_bible_version_mix));
		mixMenu.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
		menu.add(0, this.MENU_BIBLE_INDEX, 0, this.getString(R.string.menu_index));
		
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
			preferences.setBibleVersion("mix");
			preferences.commit();
			readBible();
			break;
		case MENU_BIBLE_VERSION_NIV:
			preferences.setBibleVersion("niv");
			preferences.commit();
			readBible();
			break;
		case MENU_BIBLE_INDEX: 
			Intent intentIndex = new Intent(this, BookIndexActivity.class);
			this.startActivity(intentIndex);
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
		
		ChapterDTO chapterHhb = ChapterXmlParser.getChapterContent(getApplicationContext(), "hhb", bookNumber, chapterNumber);
		ChapterDTO chapterNiv = ChapterXmlParser.getChapterContent(getApplicationContext(), "niv", bookNumber, chapterNumber);

		//validate the chapter choosen
		int bookTotalChapterCount = Integer.parseInt(preferences.getBookTotalChapter());
		if (Integer.parseInt(chapterNumber) > bookTotalChapterCount) {
			 preferences.setChapterNumber("1");
			 preferences.commit();
			 
			 chapterNumber = "1";
			 chapterHhb = ChapterXmlParser.getChapterContent(getApplicationContext(), "hhb", bookNumber, "1");
			 chapterNiv = ChapterXmlParser.getChapterContent(getApplicationContext(), "niv", bookNumber, "1");
			 
			 Toast.makeText(getApplicationContext(), R.string.alert_chapter_number_exceed_max, Toast.LENGTH_LONG).show();
		}
		
		
		txtChapterHeader.setTextSize(30);
		txtChapterHeader.setBackgroundColor(Color.parseColor(this.getString(R.string.color_hymn_header)));
		
		if (version.equalsIgnoreCase("hhb") || version.equalsIgnoreCase("mix")) {
			txtChapterHeader.setText(chapterHhb.getBookChineseName() + " - 第" + chapterNumber + "章");	
			nivMenu.setVisible(true);
			hhbMenu.setVisible(false);
		} else if (version.equalsIgnoreCase("niv")) {
			txtChapterHeader.setText(chapterHhb.getBookEnglishName() + " - Chapter " + chapterNumber);
			hhbMenu.setVisible(true);
			nivMenu.setVisible(false);
		}
		
//		bibleContainer.addView(textViewBookHeader);
		
		String chapterContent = "";
		
		ArrayList<VerseDTO> versesHhb = chapterHhb.getVerses();
		ArrayList<VerseDTO> versesNiv = chapterNiv.getVerses();
		
//		for (String verse: verses) {
//			chapterContent = chapterContent + verse;
//		}
		
		for (int i=0; i< versesHhb.size(); i++) {
			if (version.equalsIgnoreCase("hhb")) {
				VerseDTO versehhb = versesHhb.get(i);
				chapterContent = chapterContent + versehhb.getVerseIndex() + "\n" + versehhb.getVerseContent() + "\n\n";
				
			} else  if (version.equalsIgnoreCase("niv")) {
				VerseDTO verseniv = versesNiv.get(i);
				chapterContent = chapterContent + verseniv.getVerseIndex() + "\n" + verseniv.getVerseContent() + "\n\n";
			} else  if (version.equalsIgnoreCase("mix")) {
				VerseDTO versehhb = versesHhb.get(i);
				VerseDTO verseniv = versesNiv.get(i);
				chapterContent = chapterContent + versehhb.getVerseIndex() + "\n" + versehhb.getVerseContent() + "\n";
				chapterContent = chapterContent + verseniv.getVerseContent() + "\n\n";
				
			}
		}
		
		TextView textChapterContent = new TextView(this);
		textChapterContent.setTextSize(fontSize +4);
		textChapterContent.setText(chapterContent);
		Typeface face = Typeface.createFromAsset(getAssets(), "fonts/STKAITI.TTF");
		textChapterContent.setTypeface(face);
		bibleContainer.addView(textChapterContent);
	}
}
