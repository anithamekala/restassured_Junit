package com.rest.assured.petstore.scripts;

import static com.rest.assured.petstore.scripts.TestConstants.KEY_CATEGORY;
import static com.rest.assured.petstore.scripts.TestConstants.KEY_ID;
import static com.rest.assured.petstore.scripts.TestConstants.KEY_NAME;
import static com.rest.assured.petstore.scripts.TestConstants.KEY_PHOTOURLS;
import static com.rest.assured.petstore.scripts.TestConstants.KEY_STATUS;
import static com.rest.assured.petstore.scripts.TestConstants.KEY_TAGS;
import static com.rest.assured.petstore.scripts.TestConstants.VALUE_STRING;

import java.util.ArrayList;

import org.json.JSONObject;

public class TestUtil {

	public static JSONObject getJsonObject(long id, String name, String status) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(KEY_ID, id);
		jsonObject.put(KEY_NAME, name);
		
		JSONObject category = new JSONObject();
		category.put(KEY_ID, 0);
		category.put(KEY_NAME, VALUE_STRING);
		jsonObject.put(KEY_CATEGORY, category);
		
		ArrayList<String> photoUrls = new ArrayList<>();
		photoUrls.add(VALUE_STRING);
		jsonObject.put(KEY_PHOTOURLS, photoUrls);
		
		JSONObject tag = new JSONObject();
		tag.put(KEY_ID, 0);
		tag.put(KEY_NAME, VALUE_STRING);
		ArrayList<JSONObject> tags = new ArrayList<>();
		tags.add(tag);
		jsonObject.put(KEY_TAGS, tags);
		
		jsonObject.put(KEY_STATUS, status);
		
		return jsonObject;
	}
}
