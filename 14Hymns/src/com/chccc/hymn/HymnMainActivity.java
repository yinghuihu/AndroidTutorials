package com.chccc.hymn;

import java.io.InputStream;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.chccc.hymn.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class HymnMainActivity extends Activity {

	public final static int MENU_HYMN_NUMBER = 1; 
	
	public final static int MENU_HYMN_NUMBER_MULTIPLE = 2; 
	
	TextView textViewHymn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hymn_activity_main);
		
		textViewHymn = (TextView) findViewById(R.id.TextViewHymn);
		
		textViewHymn.setText(readHymn("001"));
		
		textViewHymn.setTextSize(20);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		menu.add(0, this.MENU_HYMN_NUMBER, 0, this.getString(R.string.menu_text_choose_hymn));
		
		menu.add(0, this.MENU_HYMN_NUMBER_MULTIPLE, 0, this.getString(R.string.menu_text_choose_hymn_multiple));
		
		return true;
	}
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == MENU_HYMN_NUMBER) {

			if (resultCode == RESULT_OK) {
				String result = data.getStringExtra("result");
				textViewHymn.setText(readHymn(result));
			}
			if (resultCode == RESULT_CANCELED) {
				// Write your code if there's no result
			}
		} else if (requestCode == MENU_HYMN_NUMBER_MULTIPLE) {
			if (resultCode == RESULT_OK) {
				String result = data.getStringExtra("result");
				
				StringTokenizer st = new StringTokenizer(result, " ");
				
				String text = "";
				while (st.hasMoreTokens()) {
					text = text + "###" +readHymn(st.nextToken().trim());
					text = text + "\n\n";
				}
				textViewHymn.setText(text);
			}
			if (resultCode == RESULT_CANCELED) {
				// Write your code if there's no result
			}
		}
	}

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
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		super.onMenuItemSelected(featureId, item);
		switch (item.getItemId()){
		case MENU_HYMN_NUMBER:
			Intent intent = new Intent(this, HymnNumberActivity.class);
			this.startActivityForResult(intent, MENU_HYMN_NUMBER);
			break;
		case MENU_HYMN_NUMBER_MULTIPLE:
			Intent intent2 = new Intent(this, HymnNumberMultipleActivity.class);
			this.startActivityForResult(intent2, MENU_HYMN_NUMBER_MULTIPLE);
		}
		
		return true;
	}

	private String readHymn(String hymnNumber) {
		InputStream is = null;
		String inputStreamString = null;

		try {
			int number = Integer.parseInt(hymnNumber);

			if (number > 536) {
				return this.getString(R.string.text_hymn_number_max_number);
			}

		} catch (Exception e) {
			return this.getString(R.string.text_hymn_number_non_number);
		}

		try {
			while (hymnNumber.length() < 3) {
				hymnNumber = "0" + hymnNumber;
			}
			is = this.getAssets().open("h" + hymnNumber + ".txt");


			inputStreamString = new Scanner(is, "UTF-8").useDelimiter("\\A")
					.next();

			is.close();

		} catch (Exception em) {
			em.printStackTrace();
		}

		return inputStreamString;
	}
}
