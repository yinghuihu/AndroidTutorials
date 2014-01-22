package com.chccc.hymn;

import com.chccc.bible.R;
import com.chccc.bible.fragment.FragmentBookIndex;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HymnMainActivity extends Activity {

	LinearLayout hymnContainer ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hymn_activity_main);
		
		hymnContainer = (LinearLayout) findViewById(R.id.hymnContainer );
		
		Intent intent = getIntent();
		
		String result = intent.getStringExtra(FragmentBookIndex.HYMN_HEADER_NUMBER);
		TextView textViewHymn = new TextView(this);
		textViewHymn.setText(result);
		hymnContainer.addView(textViewHymn);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		return true;
	}
	
}
