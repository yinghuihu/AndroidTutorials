package com.chccc.bible;


import com.chccc.bible.db.BookHandler;
import com.chccc.bible.dto.BookDTO;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import com.chccc.bible.chapterChooser.CustomAutoCompleteView;
import com.chccc.bible.chapterChooser.CustomAutoCompleteTextChangedListener;
import com.chccc.bible.chapterChooser.AutocompleteCustomArrayAdapter;

public class BibleChapterChooserActivity extends Activity {
	Button buttonHymnNumberSave;
	EditText textHymnNumber;
	
	public AutoCompleteTextView textView = null;
	public ArrayAdapter<BookDTO> adapter;
	
	public BookHandler bookHandler;
	
	private BibleChapterChooserActivity sef;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bible_chapter_chooser2);
		
		sef = this;
		
		bookHandler = new BookHandler(BibleChapterChooserActivity.this);
		
		// put data to database
		bookHandler.insertBookData();
		
		BookDTO[] books = new BookDTO[0];
		textView = (CustomAutoCompleteView) findViewById(R.id.toNumber);
		 
		textView.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));
		
		textView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                
                RelativeLayout rl = (RelativeLayout) arg1;
                TextView tv = (TextView) rl.getChildAt(0);
                textView.setText(tv.getText().toString());
                
                BibleMainActivity.preferences.setBibleVersion("niv");
                BibleMainActivity.preferences.setBookNumber("40");
                BibleMainActivity.preferences.setChapterNumber("1");
                
               
                
            }

        });
		 adapter = new AutocompleteCustomArrayAdapter(this, R.layout.list_view_row, books);
         
		 textView.setThreshold(1);

		 //	Set adapter to AutoCompleteTextView
		 textView.setAdapter(adapter);
		 
		buttonHymnNumberSave = (Button) findViewById(R.id.buttonHymnNumberSave);
		textHymnNumber = (EditText)findViewById(R.id.textHymnNumber);
		
		buttonHymnNumberSave.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
//				Intent returnIntent = new Intent();
//				returnIntent.putExtra("result", textHymnNumber.getText().toString());
//				setResult(RESULT_OK, returnIntent);
				BibleMainActivity.preferences.setBibleVersion("niv");
                BibleMainActivity.preferences.setBookNumber("40");
                BibleMainActivity.preferences.setChapterNumber("1");
                BibleMainActivity.preferences.commit();
				Intent intent = new Intent(sef, BibleMainActivity.class);
	    		startActivity(intent);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	    
	
}
