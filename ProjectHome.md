# Summary #
This library is intended to ease image uploads to TwitPic by handling the construction of multipart/form-data POST requests.  The library offers methods for both the "upload" and "uploadAndPost" TwitPic API calls.  It's extremely easy to use and designed to be lightweight.

## Features ##
  * Easy implementation
  * Built-in exceptions for TwitPic error responses
  * Byte array uploads, specifically designed for **Android** camera callbacks.

## Example ##
### Code ###
```
// Create file
File picture = new File("images/somepic.jpg");

// Create TwitPic object and allocate TwitPicResponse object
TwitPic tpRequest = new TwitPic("username", "password");
TwitPicResponse tpResponse = null;

// Make request and handle exceptions				
try {
	tpResponse = tpRequest.uploadAndPost(picture, "Hello World!!!");
} catch (IOException e) {
	e.printStackTrace();
} catch (TwitPicException e) {
	e.printStackTrace();
}

// If we got a response back, print out response variables				
if(tpResponse != null)
	tpResponse.dumpVars();
```
### Output ###
```
Status: ok
Status Id: 2455416825
User Id: 51561338
Media Aid: 97lkj
Media Url: http://twitpic.com/97lkj
Error Code: null
Error Msg: null
```