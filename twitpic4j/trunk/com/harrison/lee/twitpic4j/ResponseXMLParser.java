//    twitpic4j is a small Java library aimed at easy TwitPic image uploads.
//
//	  Copyright (C) 2009  Harrison Lee
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.


package com.harrison.lee.twitpic4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 * <p>
 * Class used to parse XML responses from TwitPic.
 * </p>
 * 
 * @author Harrison Lee
 * @version 0.9.0
 * 
 */
public class ResponseXMLParser extends DefaultHandler{
	
	private TwitPicResponse mResponse;
	private InputStream mInputStream = null;
	
	private boolean mIsStartElem 	= false;					// Used to parse characters only on start tag
	private List<String> mTags 		= new ArrayList<String>();	// Array list for storing tag tree
    private int mLevel 				= 0;						// Current mLevel in tree
    
	/**
	 * Constructor
	 * @param is InputStream containing XML file to be parsed
	 */
	public ResponseXMLParser(InputStream is){
		mInputStream = is;
	}
	
	/**
	 * Creates an XML parser and parses TwitPic XML response.  The XML
	 * feed data is then stored in a TwitPicResponse object and returned
	 * to the calling object.
	 * 
	 * @return Object representing response XML from TwitPic
	 * @throws IOException
	 */
	public TwitPicResponse parseResponse() throws IOException {
		
		mResponse = new TwitPicResponse();
		
		try {
        	
            InputSource inputSource = new InputSource(mInputStream);
            SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			xr.setContentHandler(this);
			xr.parse(inputSource);
        
        } catch (SAXException saxe) {
        	saxe.printStackTrace();
        } catch (ParserConfigurationException e){ 
        	e.printStackTrace();
		} finally {
            if (mInputStream != null) {
            	mInputStream.close();
            }
		}
		
		return mResponse;
	}

	@Override
	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		
		name = name.trim().toLowerCase();	// Format tag name
		localName = localName.trim().toLowerCase();
		
		String tagName = (name == null) ? localName : name;
		
		mIsStartElem = true;	// Let interested parties know current element is a start tag
    	mLevel++;				// Increase the mLevel of the tree
        mTags.add(tagName);		// Add tag to tree
		
        // Store status and error info (if error occurred)
        String status;
        if(tagName.equals("rsp")) {
        	status = attributes.getValue("status");
        	status = status == null ? attributes.getValue("stat") : status;
        	mResponse.setStatus(status);
        } else if(tagName.equals("err")){
        	mResponse.setErrorCode(attributes.getValue("code"));
        	mResponse.setErrorMessage(attributes.getValue("msg"));
        }
        
		super.startElement(uri, localName, name, attributes);
	}
	
	@Override
	public void endElement(String uri, String localName, String name) throws SAXException {
		
		mIsStartElem = false;	// Let interested parties know current element is an end tag
    	mLevel--;
        mTags.remove(mLevel);
		
		super.endElement(uri, localName, name);
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		String phrase = new String(ch, start, length);	// Store characters in string
        phrase = phrase.trim();							// Trim characters
		
		if(mIsStartElem){
			if(mLevel == 2){
				
				// Store status ID, user ID, media aid, and media URL
				String tag = mTags.get(1);
			
				if(tag.equals("statusid"))
					mResponse.setStatusId(phrase);
				else if(tag.equals("userid"))
					mResponse.setUserId(phrase);
				else if(tag.equals("mediaid"))
					mResponse.setMediaAid(phrase);
				else if(tag.equals("mediaurl"))
					mResponse.setMediaUrl(phrase);
				
			}
		}
		
		super.characters(ch, start, length);
	}

	
}
