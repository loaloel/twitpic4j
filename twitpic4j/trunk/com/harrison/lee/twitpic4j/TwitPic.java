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

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.harrison.lee.twitpic4j.exception.ImageNotFoundException;
import com.harrison.lee.twitpic4j.exception.ImageTooLargeException;
import com.harrison.lee.twitpic4j.exception.InvalidImageTypeException;
import com.harrison.lee.twitpic4j.exception.InvalidUsernameOrPasswordException;
import com.harrison.lee.twitpic4j.exception.TwitPicException;

/**
 * <p>
 * Main class of the twitpic4j library.  This class defines methods
 * pertinent to uploading image data to TwitPic.com.
 * </p>
 * @author Harrison Lee
 * @version	0.9.0
 * 
 */
public class TwitPic {
	
	public static final String PARAM_USERNAME 		= "username";
	public static final String PARAM_PASSWORD 		= "password";
	public static final String PARAM_MEDIA			= "media";
	public static final String PARAM_MESSAGE		= "message";
	public static final String UPLOAD_URL			= "http://twitpic.com/api/upload";
	public static final String UPLOAD_AND_POST_URL	= "http://twitpic.com/api/uploadAndPost";
	
	private String mUsername;
	private String mPassword;
	private ClientHttpRequest mRequest;
	private TwitPicResponse mResponse;
	
	/**
	 * Constructor
	 */
	public TwitPic(){}
	
	/**
	 * Constructor.  Sets username and password.
	 * 
	 * @param username
	 * @param password
	 */
	public TwitPic(String username, String password){
		mUsername = username;
		mPassword = password;
	}
	
	/**
	 * Passes instance variables 'mUsername' and 'mPassword' into POST request.
	 * 
	 * @throws IOException
	 */
	private void setUserParameters() throws IOException {
		mRequest.setParameter(PARAM_USERNAME, mUsername);
		mRequest.setParameter(PARAM_PASSWORD, mPassword);
	}
	
	/**
	 * Uploads a file to TwitPic.  Twitter will *NOT* be updated when uploading
	 * with this function.
	 * 
	 * @param picture  The picture file to be uploaded
	 * @return 	Returns a TwitPicResponse object which encapsulates XML response message from TwitPic.
	 * @throws IOException
	 * @throws TwitPicException
	 */
	public TwitPicResponse upload(File picture) throws IOException, TwitPicException {
		
		// Create new HTTP client with 'upload' URL
		mRequest = new ClientHttpRequest(new URL(UPLOAD_URL));
		
		// Set userame and password for POST request
		setUserParameters();
		
		// Set file parameter
		mRequest.setParameter(PARAM_MEDIA, picture);
		
		// Get response XML
		mResponse = new ResponseXMLParser(mRequest.post()).parseResponse();
		
		// Handle possible errors returned by TwitPic
		if(mResponse.getErrorCode() != null)
			handleErrorMessage(Integer.parseInt(mResponse.getErrorCode()));
		
		// Make post request and return response
		return mResponse;
	}
	
	/**
	 * Uploads an image contained in a byte array to TwitPic.  Twitter 
	 * will *NOT* be updated when uploading with this function.
	 * 
	 * @param picture  The picture file to be uploaded
	 * @return 	Returns a TwitPicResponse object which encapsulates XML response message from TwitPic.
	 * @throws IOException
	 * @throws TwitPicException
	 */
	public TwitPicResponse upload(byte[] picture) throws IOException, TwitPicException {
		
		// Create new HTTP client with 'upload' URL
		mRequest = new ClientHttpRequest(new URL(UPLOAD_URL));
		
		// Set userame and password for POST request
		setUserParameters();
		
		// Set file parameter
		mRequest.setParameter(PARAM_MEDIA, picture);
		
		// Get response XML
		mResponse = new ResponseXMLParser(mRequest.post()).parseResponse();
		
		// Handle possible errors returned by TwitPic
		if(mResponse.getErrorCode() != null)
			handleErrorMessage(Integer.parseInt(mResponse.getErrorCode()));
		
		// Make post request and return response
		return mResponse;
	}
	
	/**
	 * Uploads a file to TwitPic.  Twitter *WILL* be updated when uploading
	 * with this function.  The optional parameter 'message' will be posted
	 * with the image on TwitPic, and with the link on Twitter.  Enter a value
	 * of 'null' if no message is desired.
	 * 
	 * @param picture
	 * @param message
	 * @return
	 * @throws IOException
	 * @throws TwitPicException
	 */
	public TwitPicResponse uploadAndPost(File picture, String message) throws IOException, TwitPicException {
		
		// Create new HTTP client with 'upload' URL
		mRequest = new ClientHttpRequest(new URL(UPLOAD_AND_POST_URL));
		
		// Set userame and password for POST request
		setUserParameters();
		
		// Set message parameter
		if(message != null)
			mRequest.setParameter(PARAM_MESSAGE, message);
		
		// Set file parameter
		mRequest.setParameter(PARAM_MEDIA, picture);
		
		// Get response XML
		mResponse = new ResponseXMLParser(mRequest.post()).parseResponse();
		
		// Handle possible errors returned by TwitPic
		if(mResponse.getErrorCode() != null)
			handleErrorMessage(Integer.parseInt(mResponse.getErrorCode()));
		
		// Make post request and return response
		return mResponse;
	}
	
	
	/**
	 * Uploads an image contained in a byte array to TwitPic.  
	 * Twitter *WILL* be updated when uploading with this function.  
	 * The optional parameter 'message' will be posted with the image 
	 * on TwitPic, and with the link on Twitter.  Enter a value of 
	 * 'null' if no message is desired.
	 * 
	 * @param picture
	 * @param message
	 * @return
	 * @throws IOException
	 * @throws TwitPicException
	 */
	public TwitPicResponse uploadAndPost(byte[] picture, String message) throws IOException, TwitPicException {
		
		// Create new HTTP client with 'upload' URL
		mRequest = new ClientHttpRequest(new URL(UPLOAD_AND_POST_URL));
		
		// Set userame and password for POST request
		setUserParameters();
		
		// Set message parameter
		if(message != null)
			mRequest.setParameter(PARAM_MESSAGE, message);
		
		// Set file parameter
		mRequest.setParameter(PARAM_MEDIA, picture);
		
		// Get response XML
		mResponse = new ResponseXMLParser(mRequest.post()).parseResponse();
		
		// Handle possible errors returned by TwitPic
		if(mResponse.getErrorCode() != null)
			handleErrorMessage(Integer.parseInt(mResponse.getErrorCode()));
		
		// Make post request and return response
		return mResponse;
		
	}
	
	/**
	 * Handles the throwing of TwitPicExceptions based on error codes found
	 * in TwitPic responses.
	 * 
	 * @param code Error code
	 * @throws TwitPicException
	 */
	private void handleErrorMessage(int code) throws TwitPicException {
		switch(code){
		case TwitPicException.ERROR_IMAGE_NOT_FOUND:
			throw new ImageNotFoundException();
		case TwitPicException.ERROR_IMAGE_TOO_LARGE:
			throw new ImageTooLargeException();
		case TwitPicException.ERROR_INVALID_IMAGE_TYPE:
			throw new InvalidImageTypeException();
		case TwitPicException.ERROR_INVALID_USER_OR_PASS:
			throw new InvalidUsernameOrPasswordException();
		}
	}
	
	
	/* ------------ Getters and Setters ------------ */
	
	public String getUsername() {
		return mUsername;
	}

	public void setUsername(String username) {
		mUsername = username;
	}

	public String getPassword() {
		return mPassword;
	}

	public void setPassword(String password) {
		mPassword = password;
	}
	
	
	
}
