package com.chccc.bible;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.TextView;


public class ChapterXmlParser {
	
	public static ArrayList<String> getChapterContent(Context applicationContext) {
		try {
			InputStream is = null;
			is = applicationContext.getAssets().open("data/hhb/hhb66.xml");

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(is));
			doc.getDocumentElement().normalize();

			NodeList bookNodeList = doc.getElementsByTagName("Book");
			
			if (bookNodeList !=null) {
				Element bookElement = (Element) bookNodeList.item(0);
				
				String name = bookElement.getAttribute("name");
				String englishName = bookElement.getAttribute("englishName");
				String chaptersCount = bookElement.getAttribute("chapters");
				
				NodeList chapterNodeList = bookElement.getElementsByTagName("Chapter");
			}

			for (int i = 0; i < bookNodeList.getLength(); i++) {

				Node node = bookNodeList.item(i);
	
				Element fstElmnt = (Element) node;
				NodeList nameList = fstElmnt.getElementsByTagName("name");
				Element nameElement = (Element) nameList.item(0);
				nameList = nameElement.getChildNodes();
				
				name[i].setText("Name = " + ((Node) nameList.item(0)).getNodeValue());
	
				NodeList websiteList = fstElmnt.getElementsByTagName("website");
				Element websiteElement = (Element) websiteList.item(0);
				websiteList = websiteElement.getChildNodes();
				website[i].setText("Website = "
				+ ((Node) websiteList.item(0)).getNodeValue());
	
				category[i].setText("Website Category = "
				+ websiteElement.getAttribute("category"));
	

			}
		} catch (Exception e) {
			System.out.println("XML Pasing Excpetion = " + e);
		}
	}

	/*
	public static ArrayList<String> getHymnHeaders(Context applicationContext) {
		ArrayList lines = new ArrayList();
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser xpp = factory.newPullParser();

			InputStream is = null;
			is = applicationContext.getAssets().open("data/hhb/hhb66.xml");
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

		return lines;
	}

	*/
}
