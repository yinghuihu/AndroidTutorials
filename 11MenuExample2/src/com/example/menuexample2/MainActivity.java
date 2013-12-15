package com.example.menuexample2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		super.onCreateOptionsMenu(menu);
		menu.add("Another")
		.setIcon(R.drawable.another_activity)
		.setIntent(new Intent(this, AnotherActivity.class));
		menu.add("Yet Another")
		.setIcon(R.drawable.yet_another)
		.setIntent(new Intent(this, YetAnotherActivity.class));
		return true;
	}

}
