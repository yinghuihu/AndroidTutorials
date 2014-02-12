package com.chccc.bible;

import com.chccc.bible.db.BookHandler;
import com.chccc.bible.dto.BookDTO;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class BibleBookChapterChooserActivity extends Activity implements OnClickListener{
	
	TableLayout bookChapterChooserContainer;
	
	public BookHandler bookHandler;
	private BookDTO book;
	private int button_per_row =5;
	
	String initialString =null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.biblebookchapterchooser_activity_main);
		
		
		Intent intent = getIntent();

		initialString = intent.getStringExtra(BibleBookChooserActivity.EXTRA_MESSAGE);
		
		bookHandler = new BookHandler(BibleBookChapterChooserActivity.this);
		book = bookHandler.getBookByInitialString(initialString);
		
		int count = Integer.parseInt(book.getChapterCount());
		
		
		bookChapterChooserContainer = (TableLayout)findViewById(R.id.bookChapterChooserContainer);
		bookChapterChooserContainer.removeAllViews();
		
		TextView  tv= new TextView(this);
		tv.setText(book.getName());
		tv.setTextSize(30);
		
		
		
		TableRow.LayoutParams params = new TableRow.LayoutParams();
		params.span = button_per_row;
		
		TableRow trt = new TableRow(this);
		trt.addView(tv, 0, params);
		bookChapterChooserContainer.addView(trt);
		
		
		TableRow tr = new TableRow(this);
		for (int i = 1; i<= count; i++) {
			
			if (i % button_per_row ==1) {
				tr = new TableRow(this);
				bookChapterChooserContainer.addView(tr);
			}
			
			Button btn = new Button(this);
			btn.setText(i + "");
			
			btn.setOnClickListener(this);
			
			tr.addView(btn);
			
		}
		
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	@Override
    public void onClick(View v) {
		Button btn = (Button) v;
		
//		String initialString = btn.getText().toString();
		BookDTO book = bookHandler.getBookByInitialString(initialString);
		
//		BibleMainActivity.preferences.setBibleVersion("hhb");
        BibleMainActivity.preferences.setBookNumber(book.getNumber());
        BibleMainActivity.preferences.setChapterNumber(btn.getText().toString());
        BibleMainActivity.preferences.commit();
		Intent intent = new Intent(this, BibleMainActivity.class);
		startActivity(intent);
		finish();
		
//		Toast.makeText(getApplicationContext(), book.getName(), Toast.LENGTH_SHORT).show();
    }
}
