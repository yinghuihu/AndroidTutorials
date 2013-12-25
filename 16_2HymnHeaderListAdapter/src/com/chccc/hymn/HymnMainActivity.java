package com.chccc.hymn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.chccc.hymn.R;

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

public class HymnMainActivity extends Activity {

	LinearLayout hymnContainer ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hymn_activity_main);
		
		hymnContainer = (LinearLayout) findViewById(R.id.hymnContainer );
		
		Intent intent = getIntent();
		
		String result = intent.getStringExtra(FragmentHymnHeaderList.HYMN_HEADER_NUMBER);
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
