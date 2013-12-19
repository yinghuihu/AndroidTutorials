package com.example.imageviewnasa;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	ImageView imageView = null;
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
		
		
		imageView = (ImageView)findViewById(R.id.imageDisplay);
		
		Thread thread = new Thread(new Runnable(){
		    @Override
		    public void run() {
		        try {
		        	HttpURLConnection connection = (HttpURLConnection)new URL("http://www.windsim.com/images/sky/sky_107.bmp").openConnection();
					
					connection.setDoInput(true);
					
					connection.connect();
					
					InputStream input = connection.getInputStream();
					
					Bitmap bitmap = BitmapFactory.decodeStream(input);
					imageView.setImageBitmap(bitmap);
					input.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		});

		thread.start(); 
		
//		if (android.os.Build.VERSION.SDK_INT > 9) {
//	        StrictMode.ThreadPolicy policy = 
//	             new StrictMode.ThreadPolicy.Builder().permitAll().build();
//	        StrictMode.setThreadPolicy(policy);
//		}
		//new MyAsyncTask().execute("http://www.windsim.com/images/sky/sky_107.bmp");
//		imageView.setImageBitmap(handler.getImage());
		
		TextView descriptionView = (TextView)findViewById(R.id.imageDescription);
		descriptionView.setText(handler.getDescription());
				
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private class MyAsyncTask extends AsyncTask<String, String, String>{

		// String... arg0 is the same as String[] args
		protected String doInBackground(String... args) {
			try {
				HttpURLConnection connection = (HttpURLConnection)new URL(args[0]).openConnection();
				
				connection.setDoInput(true);
				
				connection.connect();
				
				InputStream input = connection.getInputStream();
				
				Bitmap bitmap = BitmapFactory.decodeStream(input);
				imageView.setImageBitmap(bitmap);
				input.close();
				
			} catch (IOException ioe) { return null; }
			
			return null;
		}
	}
}
