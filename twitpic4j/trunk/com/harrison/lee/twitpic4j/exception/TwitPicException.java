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


package com.harrison.lee.twitpic4j.exception;

/**
 * <p>
 * Base class for exceptions thrown by the TwitPic library.
 * </p>
 * 
 * @author Harrison Lee
 * @version 0.9.0
 * 
 */
public class TwitPicException extends Exception {
	
	public static final int ERROR_INVALID_USER_OR_PASS 	= 1001;
	public static final int ERROR_IMAGE_NOT_FOUND		= 1002;
	public static final int ERROR_INVALID_IMAGE_TYPE	= 1003;
	public static final int ERROR_IMAGE_TOO_LARGE		= 1004;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
