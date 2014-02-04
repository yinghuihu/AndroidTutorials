package com.chccc.bible;

import java.util.ArrayList;

import com.chccc.bible.db.BookHandler;
import com.chccc.bible.dto.BookDTO;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class BibleBookChooserActivity extends Activity implements OnClickListener{
	
	TableLayout bookChooserContainer;
	
	public BookHandler bookHandler;
	private ArrayList<BookDTO> booksOld;
	
	private ArrayList<BookDTO> booksNew;
	
	private int button_per_row =5;
	
	private static int fontSize = 22; 
	
	public final static String EXTRA_MESSAGE = "com.chccc.bible.BookChapterChooser.MESSAGE";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.biblebookchooser_activity_main);
		
		
		Typeface face = Typeface.createFromAsset(getAssets(), "fonts/STKAITI.TTF");
		
		bookHandler = new BookHandler(BibleBookChooserActivity.this);
		booksOld = bookHandler.getBooks(BookHandler.OLD_TESTAMENT);	
		
		bookChooserContainer = (TableLayout)findViewById(R.id.bookChooserContainer);
		bookChooserContainer.removeAllViews();
		
		//add old testament
		TextView  tvold= new TextView(this);
		tvold.setText("旧约");
		
		tvold.setTextSize(30);
//		tvold.setBackgroundColor(Color.parseColor(this.getString(R.string.color_hymn_header)));
		
		TableRow trold = new TableRow(this);
		trold.addView(tvold);
		bookChooserContainer.addView(trold);
		
		int bookCount = 0;
		
		TableRow tr = new TableRow(this);
		for (BookDTO book: booksOld) {
			
			if (bookCount % button_per_row ==0) {
				tr = new TableRow(this);
				bookChooserContainer.addView(tr);
			}
			
			Button btn = new Button(this);
			
			
			btn.setText(book.getInitialString());
			
			btn.setTypeface(face);
			btn.setTextSize(fontSize);
			btn.setOnClickListener(this);
			
			tr.addView(btn);
			
			bookCount++;
		}
		
		
		//add new testament
		TextView  tvnew= new TextView(this);
		tvnew.setText("新约");
		tvnew.setTextSize(30);
//		tvnew.setBackgroundColor(Color.parseColor(this.getString(R.string.color_hymn_header)));
		TableRow trnew = new TableRow(this);
		trnew.addView(tvnew);
		bookChooserContainer.addView(trnew);
		
		
		booksNew = bookHandler.getBooks(BookHandler.NEW_TESTAMENT);
		
		bookCount = 0;
		
		tr = new TableRow(this);
		for (BookDTO book: booksNew) {
			
			if (bookCount % button_per_row ==0) {
				tr = new TableRow(this);
				bookChooserContainer.addView(tr);
			}
			
			Button btn = new Button(this);
			btn.setText(book.getInitialString());
			
			btn.setTypeface(face);
			btn.setTextSize(fontSize);
			
			btn.setOnClickListener(this);
			
			tr.addView(btn);
			
			bookCount++;
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
		
		String initialString = btn.getText().toString();
//		BookDTO book = bookHandler.getBookByInitialString(initialString);
		
		Intent intent = new Intent(this, BibleBookChapterChooserActivity.class);


		// Add the text to the intent
		intent.putExtra(EXTRA_MESSAGE, initialString);

		// startActivity causes the Activity to start
		startActivity(intent);
		
		finish();
		
    }
}
