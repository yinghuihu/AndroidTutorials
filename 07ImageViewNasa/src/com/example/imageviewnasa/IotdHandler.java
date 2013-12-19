package com.example.imageviewnasa;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class IotdHandler extends DefaultHandler {
	private String url = "http://www.nasa.gov/rss/image_of_the_day.rss";
	private boolean inUrl = false;
	private boolean inTitle = false;
	private boolean inDescription = false;
	private boolean inItem = false;
	private boolean inDate = false;
	
	private Bitmap image = null;
	private String title = null;
	private String description = null;
	private String date = null;
	
	public void processFeed() {
		title  = "Solar Dynamics Observatory Shows Sun&#039;s Rainbow of Wavelengths";
		date = "Wed, 18 Dec 2013 12:00:00 EDT";
		description = "This still image was taken from a new NASA movie of the sun based on data from NASA\'s Solar Dynamics Observatory, or SDO, showing the wide range of wavelengths – " +
				"invisible to the naked eye – that the telescope can view. SDO converts the wavelengths into an image humans can see, and the light is colorized into a rainbow of colors. " +
				"5800 Angstroms, for example, generally emanates from material of about 10,000 degrees F (5700 degrees C), which represents the surface of the sun. Extreme ultraviolet light of 94 Angstroms, which is typically colorized in green in SDO images, comes from atoms that are about 11 million degrees F (6,300,000 degrees C) and is a good wavelength for looking at solar flares, which can reach such high temperatures. By examining pictures of the sun in a variety of wavelengths – as is done not only by SDO, but also by NASA&#039;s Interface Region Imaging Spectrograph, NASA&#039;s Solar Terrestrial Relations Observatory and the European Space Agency/NASA Solar and Heliospheric Observatory -- scientists can track how particles and heat move through the sun&#039;s atmosphere." +
				"&gt; Read more" +
				"&gt; Video: Jewel Box Sun" +
				"Image Credit: NASA Goddard Space Flight Center";
		
//		image  = getBitmap ("http://www.windsim.com/images/sky/sky_107.bmp");
//		try {
//			SAXParserFactory factory =
//			SAXParserFactory.newInstance();
//			SAXParser parser = factory.newSAXParser();
//			XMLReader reader = parser.getXMLReader();
//			reader.setContentHandler(this);
//			InputStream inputStream = new URL(url).openStream();
//			reader.parse(new InputSource(inputStream));
//		} catch (Exception e) {
//			
//		}
	}
	
	
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (localName.equals("url")) { 
			inUrl = true; 
		}
		else { inUrl = false; }
		
		if (localName.startsWith("item")) { inItem = true; }
		else if (inItem) {
			
		if (localName.equals("title")) { inTitle = true; }
		else { inTitle = false; }
		
		if (localName.equals("description")) { inDescription = true; }
		else { inDescription = false; }
		
		if (localName.equals("pubDate")) { inDate = true; }
		else { inDate = false; }
		
		}
	}
	
	private Bitmap getBitmap(String url) {
		try {
			HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
			
			connection.setDoInput(true);
			
			connection.connect();
			
			InputStream input = connection.getInputStream();
			
			Bitmap bitmap = BitmapFactory.decodeStream(input);
			
			input.close();
			return bitmap;
		} catch (IOException ioe) { return null; }
	}

//	public void characters(char ch[], int start, int length) {
//		String chars = new String(ch).substring(start, start + length);
//		if (inUrl && url == null) { image = getBitmap(chars); }
//		if (inTitle && title == null) { title = chars; }
//		if (inDescription) { description.append(chars); }
//		if (inDate && date == null) { date = chars; }
//	}
	
	public Bitmap getImage() { return image; }
	public String getTitle() { return title; }
	public String getDescription() { return description; }
	public String getDate() { return date; }
}