package com.chccc.hymn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.TextView;


public class HymnHeaderXmlParser {

	public static ArrayList<HymnHeader> getHymnHeaders(Context applicationContext) {
		ArrayList hymnHeaderlist = new ArrayList();
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser xpp = factory.newPullParser();

			InputStream is = null;
			is = applicationContext.getAssets().open("h000.xml");
			BufferedReader input = new BufferedReader(new InputStreamReader(is));
			String line = null; 
			
			String inputStreamString = "";
			while ((line = input.readLine()) != null ) {
				inputStreamString = inputStreamString + line.trim();
			}
			
			StringReader sr = new StringReader(inputStreamString);
			xpp.setInput(sr);
			
			int eventType = xpp.getEventType();
			String lastTag = "";
			
			HymnHeader header = null; 
			String tagName  = "";
			while (eventType != XmlPullParser.END_DOCUMENT) 
			{
				tagName = xpp.getName();
				
				if (eventType == XmlPullParser.START_TAG) {

					if (tagName.equals("Hymns")) {
						hymnHeaderlist.clear();
					} else if (tagName.equals("Hymn")) {
						header = new HymnHeader();
					} else if (tagName.equals("Number")) {
						lastTag = "Number";
					} else if (tagName.equals("Title")) {
						lastTag = "Title";
					} else if (tagName.equals("Caption")) {
						lastTag = "Caption";
					} else if (tagName.equals("SearchString")) {
						lastTag = "SearchString";
					}

				} else if (eventType == XmlPullParser.END_TAG) {
					lastTag  ="";
					if (tagName.equals("Hymn")) {
						hymnHeaderlist.add(header);
					}
				} else if (eventType == XmlPullParser.TEXT) {
					if (lastTag.equals("Number")) {
						header.setNumber(xpp.getText());
					} else if (lastTag.equals("Title")) {
						header.setTitle(xpp.getText());
					} else if (lastTag.equals("Caption")) {
						header.setSubTitle(xpp.getText());
					} else  if (lastTag.equals("SearchString")) {
						header.setSearchString(xpp.getText());
					}
				
				}

				eventType = xpp.next();
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}

		finally {
		}

		return hymnHeaderlist;
	}

}
