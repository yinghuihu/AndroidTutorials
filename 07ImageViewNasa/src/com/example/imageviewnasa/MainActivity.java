package com.example.imageviewnasa;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		IotdHandler handler = new IotdHandler();
		handler.processFeed();
		
		TextView titleView = (TextView)findViewById(R.id.imageTitle);
		titleView.setText(handler.getTitle());
		
		TextView dateView = (TextView)findViewById(R.id.imageDate);
		dateView.setText(handler.getDate());
		
		
		ImageView imageView = (ImageView)findViewById(R.id.imageDisplay);
		
		
		TextView descriptionView = (TextView)findViewById(R.id.imageDescription);
		descriptionView.setText(handler.getDescription());
				
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
