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

/**
 * <p>
 * Class that encapsulates XML messages returned by TwitPic API calls.  This
 * class can be instantiated, but generally a TwitPicResponse object should
 * be created from the object returned by TwitPic API call methods.  Something
 * like this:
 * </p> 
 * <p>
 * File f = new File("somepic.jpg");
 * TwitPic tp = new TwitPic("username", "password");
 * TwitPicResponse = tp.upload(f);
 * </p>
 * 
 * @author Harrison Lee
 * @version 0.9.0
 *
 */
public class TwitPicResponse {
	
	private String mStatus;
	private String mStatusId;
	private String mUserId;
	private String mMediaAid;
	private String mMediaUrl;
	private String mErrorCode;
	private String mErrorMessage;
	
	/**
	 * Constructor
	 */
	public TwitPicResponse(){}
	
	
	/**
	 * Prints instance variables (for debugging purposes).
	 */
	public void dumpVars(){
		System.out.println(
				"Status: " 		+ mStatus 		+ "\n" +
				"Status Id: " 	+ mStatusId 	+ "\n" +
				"User Id: " 	+ mUserId 		+ "\n" +
				"Media Aid: " 	+ mMediaAid 	+ "\n" +
				"Media Url: " 	+ mMediaUrl		+ "\n" +
				"Error Code: " 	+ mErrorCode 	+ "\n" +
				"Error Msg: " 	+ mErrorMessage
				);
	}
	
	/* ------------ Getters and Setters ------------ */
	
	public String getStatus() {
		return mStatus;
	}

	public void setStatus(String status) {
		mStatus = status;
	}

	public String getStatusId() {
		return mStatusId;
	}

	public void setStatusId(String statusId) {
		mStatusId = statusId;
	}

	public String getUserId() {
		return mUserId;
	}

	public void setUserId(String userId) {
		mUserId = userId;
	}

	public String getMediaAid() {
		return mMediaAid;
	}

	public void setMediaAid(String mediaAid) {
		mMediaAid = mediaAid;
	}

	public String getMediaUrl() {
		return mMediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		mMediaUrl = mediaUrl;
	}

	public String getErrorCode() {
		return mErrorCode;
	}

	public void setErrorCode(String errorCode) {
		mErrorCode = errorCode;
	}

	public String getErrorMessage() {
		return mErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		mErrorMessage = errorMessage;
	}
	
}