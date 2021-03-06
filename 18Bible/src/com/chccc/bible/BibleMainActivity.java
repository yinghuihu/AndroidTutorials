package com.chccc.bible;

import java.util.ArrayList;

import com.chccc.bible.db.BookHandler;
import com.chccc.bible.dto.ChapterDTO;
import com.chccc.bible.dto.VerseDTO;
import com.chccc.bible.plan.ReadBiblePlan2014;
import com.chccc.bible.plan.ReadPlanInterface;
import com.chccc.bible.util.BibleMainActivityPreferences;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class BibleMainActivity extends Activity {
	
	private GestureDetectorCompat mDetector; 
	public static BibleMainActivityPreferences preferences;
	
	TextView txtChapterHeader = null;
	
	MenuItem hhbMenu = null;
	MenuItem mixMenu = null;
	
	public final static String EXTRA_MESSAGE = "com.chccc.bible.BibleMainActivity.MESSAGE";

	LinearLayout bibleContainer ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.bible_activity_main);
		
//		mDetector = new GestureDetectorCompat(this, new MyGestureListener());
		txtChapterHeader = (TextView) findViewById(R.id.txtChapterHeader);
		bibleContainer = (LinearLayout) findViewById(R.id.bibleContainer );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		hhbMenu = menu.getItem(2);
		mixMenu = menu.getItem(3);
		
		readBible();
		return true;
	}
	
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		super.onMenuItemSelected(featureId, item);
		
		switch (item.getItemId()){
		
		case R.id.menu_chapter_chooser:
			Intent intent = new Intent(this, BibleChapterChooserActivity.class);
			this.startActivity(intent);
			this.finish();
			break;
		case R.id.menu_previous_chapter:
			
			preferences.moveToPreviousChapter();

			if (preferences.getPreferenceMessage() != null) {
				Toast.makeText(getApplicationContext(), preferences.getPreferenceMessage(), Toast.LENGTH_SHORT).show();
				preferences.resetPreferenceMessage();
			}
			
			readBible();
			
			break;
		case R.id.menu_next_chapter:
			
			preferences.moveToNextChapter();

			if (preferences.getPreferenceMessage() != null) {
				Toast.makeText(getApplicationContext(), preferences.getPreferenceMessage(), Toast.LENGTH_SHORT).show();
				preferences.resetPreferenceMessage();
			}
			
			readBible();
			
			break;
		case R.id.menu_version_hhb:
			preferences.setBibleVersion("hhb");
			preferences.commit();
			readBible();
			break;
		case R.id.menu_version_mix:
			preferences.setBibleVersion("mix");
			preferences.commit();
			readBible();
			break;
		case R.id.menu_version_niv:
			preferences.setBibleVersion("niv");
			preferences.commit();
			readBible();
			break;
		case R.id.menu_old_testament1: 
			Intent intentOld = new Intent(this, BibleBookChooserActivity.class);
			intentOld.putExtra(EXTRA_MESSAGE, BookHandler.OLD_TESTAMENT);
			this.startActivity(intentOld);
			this.finish();
			break;
			
		case R.id.menu_new_testament1: 
			Intent intentNew = new Intent(this, BibleBookChooserActivity.class);
			intentNew.putExtra(EXTRA_MESSAGE, BookHandler.NEW_TESTAMENT);
			this.startActivity(intentNew);
			this.finish();
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
		try {
			
	
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
		
		
		txtChapterHeader.setTextSize(preferences.getFontSizeHeader());
		txtChapterHeader.setBackgroundColor(Color.parseColor(this.getString(R.color.bible_header_gray)));
		
		if (version.equalsIgnoreCase("hhb")) {
			txtChapterHeader.setText(chapterHhb.getBookChineseName() + " - 第" + chapterNumber + "章");	
			hhbMenu.setVisible(false);
			mixMenu.setVisible(true);
			
		} else if (version.equalsIgnoreCase("mix")) {
			txtChapterHeader.setText(chapterHhb.getBookChineseName() + " - 第" + chapterNumber + "章");	
			hhbMenu.setVisible(true);
			mixMenu.setVisible(false);
		
		} else if (version.equalsIgnoreCase("niv")) {
			txtChapterHeader.setText(chapterHhb.getBookEnglishName() + " - Chapter " + chapterNumber);
			hhbMenu.setVisible(true);
			mixMenu.setVisible(true);
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
		
		textChapterContent.setFreezesText(false);
		textChapterContent.setTextSize(preferences.getFontSizeText());
		textChapterContent.setText(chapterContent);
		Typeface face = Typeface.createFromAsset(getAssets(), preferences.getFontFamily());
		textChapterContent.setTypeface(face);
		bibleContainer.addView(textChapterContent);
		
		ScrollView scrv = (ScrollView)findViewById(R.id.scrollView1);
		scrv.scrollTo(0, 0);
		
		} catch(Exception e) {
			Toast.makeText(getApplicationContext(), R.string.alert_generic_error_msg, Toast.LENGTH_LONG).show();
			preferences.reset();
		}
	}

//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		 this.mDetector.onTouchEvent(event);
//	        return super.onTouchEvent(event);
//	}
//	
//	class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
//        private static final String DEBUG_TAG = "Gestures"; 
//        
//        @Override
//        public boolean onDown(MotionEvent event) { 
//            Log.d(DEBUG_TAG,"onDown: " + event.toString()); 
//            return true;
//        }
//
//        @Override
//        public boolean onFling(MotionEvent event1, MotionEvent event2, 
//                float velocityX, float velocityY) {
//            Log.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());
//            return true;
//        }
//        
//        
//    }

}
