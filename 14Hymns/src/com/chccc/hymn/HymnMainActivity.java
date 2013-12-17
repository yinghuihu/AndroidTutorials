package com.chccc.hymn;

import java.io.InputStream;
import java.util.Scanner;

import com.example.scrolltextbasic.R;

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
	
	TextView textViewHymn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hymn_activity_main);
		
		textViewHymn = (TextView) findViewById(R.id.TextViewHymn);
		
//		Log.d("Ying", "I am here");

		textViewHymn.setText(readHymn("002"));
		
//		Log.d("Ying", "I am here2");
		
		textViewHymn.setTextSize(18);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		super.onCreateOptionsMenu(menu);
		
		menu.add(0, this.MENU_HYMN_NUMBER, 0, this.getString(R.string.menu_text_choose_hymn));
		
		return true;
	}
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 1) {

			if (resultCode == RESULT_OK) {
//				Log.d("Ying", "I am backhere");
				String result = data.getStringExtra("result");
				textViewHymn.setText(readHymn(result));
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
			this.startActivityForResult(intent, 1);
			break;
		case 2:
			
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

			// Log.d("Ying", "I am here inside readhymn");

			inputStreamString = new Scanner(is, "UTF-8").useDelimiter("\\A")
					.next();

			is.close();

		} catch (Exception em) {
			// Log.d("Ying", em.getMessage());
			em.printStackTrace();
		}

		return inputStreamString;
	}
}
