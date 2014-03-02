package com.example.googleform;

import java.net.URLEncoder;

import com.example.util.AndroidSystemUtil;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	final String myTag = "DocsUpload";
	
	Button buttonSave;
	EditText textTitle;
	EditText textContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		Thread t = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				postData();
//				
//			}
//		});
		
		textTitle = (EditText)findViewById(R.id.textTitle);
		textContent = (EditText)findViewById(R.id.textContent);
		
		buttonSave = (Button) findViewById(R.id.buttonSave);
		buttonSave .setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						postData();
						
					}
				});
				
				t.start();
			}
		});
		
		
		Log.i(myTag, "OnCreate()");
		
	}

	

	public void postData() {
		if (AndroidSystemUtil.isNetworkAvailable(this) ) {
			String fullUrl = "https://docs.google.com/forms/d/1f0-LOxdw3GEvEsf47IKX0288xE5sRTs44jzUbUU3mUE/formResponse";
			HttpRequest mReq = new HttpRequest();
			String col1 = this.textTitle.getText().toString();
			String col2 = this.textContent.getText().toString();;
			
			String data = "entry_1193850099=" + URLEncoder.encode(col1) + "&" + 
						  "entry_512191320=" + URLEncoder.encode(col2);
			String response = mReq.sendPost(fullUrl, data);
			Log.i(myTag, response);	
		}
	} 
}
