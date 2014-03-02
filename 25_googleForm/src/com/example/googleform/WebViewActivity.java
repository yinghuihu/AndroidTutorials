package com.example.googleform;

import com.example.googleform.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class WebViewActivity extends Activity {
	private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.webview1);
 
		webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("https://docs.google.com/forms/d/1f0-LOxdw3GEvEsf47IKX0288xE5sRTs44jzUbUU3mUE/viewform");
    }


   
    
}
