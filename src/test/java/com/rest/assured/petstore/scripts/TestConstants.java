package com.rest.assured.petstore.scripts;

public class TestConstants {

	public static final String URI = "https://petstore.swagger.io/v2/pet";
	
	public static final int STATUS_CODE_OK = 200;
	public static final String STATUS_LINE_OK = "HTTP/1.1 200 OK";
	public static final int STATUS_CODE_NOT_FOUND = 404;
	public static final String STATUS_LINE_NOT_FOUND = "HTTP/1.1 404 Not Found";
	
	public static final int GET_ERROR_CODE = 1;
	public static final String GET_ERROR_MESSAGE = "Pet not found";
	
	public static final String POST_REQUEST_JSON_FILE = "postRequest.json";
	
	public static final String KEY_ID = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_CATEGORY = "category";
	public static final String KEY_PHOTOURLS = "photoUrls";
	public static final String KEY_TAGS = "tags";
	public static final String KEY_STATUS = "status";
	public static final String KEY_CODE = "code";
	public static final String KEY_MESSAGE = "message";
	
	public static final String VALUE_STATUS_AVAILABLE = "available";
	public static final String VALUE_STATUS_PENDING = "pending";
	public static final String VALUE_STATUS_SOLD = "sold";
	public static final String VALUE_STRING = "string";
	public static final String VALUE_NAME = "Doggie";
	public static final String VALUE_UPDATED_NAME = "Toby";
	
	public static Long id = 9223372036854027037L;
}
