package com.rest.assured.petstore.scripts;

import static com.rest.assured.petstore.scripts.TestConstants.GET_ERROR_CODE;
import static com.rest.assured.petstore.scripts.TestConstants.GET_ERROR_MESSAGE;
import static com.rest.assured.petstore.scripts.TestConstants.KEY_CATEGORY;
import static com.rest.assured.petstore.scripts.TestConstants.KEY_CODE;
import static com.rest.assured.petstore.scripts.TestConstants.KEY_ID;
import static com.rest.assured.petstore.scripts.TestConstants.KEY_MESSAGE;
import static com.rest.assured.petstore.scripts.TestConstants.KEY_NAME;
import static com.rest.assured.petstore.scripts.TestConstants.KEY_PHOTOURLS;
import static com.rest.assured.petstore.scripts.TestConstants.KEY_STATUS;
import static com.rest.assured.petstore.scripts.TestConstants.KEY_TAGS;
import static com.rest.assured.petstore.scripts.TestConstants.STATUS_CODE_NOT_FOUND;
import static com.rest.assured.petstore.scripts.TestConstants.STATUS_CODE_OK;
import static com.rest.assured.petstore.scripts.TestConstants.STATUS_LINE_NOT_FOUND;
import static com.rest.assured.petstore.scripts.TestConstants.STATUS_LINE_OK;
import static com.rest.assured.petstore.scripts.TestConstants.URI;
import static com.rest.assured.petstore.scripts.TestConstants.VALUE_NAME;
import static com.rest.assured.petstore.scripts.TestConstants.VALUE_STATUS_AVAILABLE;
import static com.rest.assured.petstore.scripts.TestConstants.VALUE_STATUS_PENDING;
import static com.rest.assured.petstore.scripts.TestConstants.VALUE_STATUS_SOLD;
import static com.rest.assured.petstore.scripts.TestConstants.VALUE_STRING;
import static com.rest.assured.petstore.scripts.TestConstants.VALUE_UPDATED_NAME;
import static com.rest.assured.petstore.scripts.TestConstants.id;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EndToEnd {

	@Test
	@Order(1)
	public void test_status_available() {

		Response res = given().queryParam(KEY_STATUS, VALUE_STATUS_AVAILABLE).when().accept(ContentType.JSON).get(URI + "/findByStatus");
		
		assertEquals(STATUS_CODE_OK, res.getStatusCode());
		assertEquals(STATUS_LINE_OK, res.getStatusLine());
		
		String jsonString = res.asString();
		JsonPath jsonPath = new JsonPath(jsonString);
		List<String> statuses = jsonPath.getList(KEY_STATUS);
		for (String status: statuses) {
			assertEquals(VALUE_STATUS_AVAILABLE, status);
		}
	}

	@Test
	@Order(2)
	public void test_status_pending() {

		Response res = given().queryParam(KEY_STATUS, VALUE_STATUS_PENDING).when().accept(ContentType.JSON).get(URI + "/findByStatus");
		
		assertEquals(STATUS_CODE_OK, res.getStatusCode());
		assertEquals(STATUS_LINE_OK, res.getStatusLine());
		
		String jsonString = res.asString();
		JsonPath jsonPath = new JsonPath(jsonString);
		List<String> statuses = jsonPath.getList(KEY_STATUS);
		for (String status: statuses) {
			assertEquals(VALUE_STATUS_PENDING, status);
		}
	}

	@Test
	@Order(3)
	public void test_status_sold() {

		Response res = given().queryParam(KEY_STATUS, VALUE_STATUS_SOLD).when().accept(ContentType.JSON).get(URI + "/findByStatus");
		
		assertEquals(STATUS_CODE_OK, res.getStatusCode());
		assertEquals(STATUS_LINE_OK, res.getStatusLine());
		
		String jsonString = res.asString();
		JsonPath jsonPath = new JsonPath(jsonString);
		List<String> statuses = jsonPath.getList(KEY_STATUS);
		for (String status: statuses) {
			assertEquals(VALUE_STATUS_SOLD, status);
		}
	}
	
	@Test
	@Order(4)
	public void test_add() {

		JSONObject pet = new JSONObject();
		pet.put(KEY_ID, 0);
		pet.put(KEY_NAME, VALUE_NAME);
		
		JSONObject category = new JSONObject();
		category.put(KEY_ID, 0);
		category.put(KEY_NAME, VALUE_STRING);
		pet.put(KEY_CATEGORY, category);
		
		ArrayList<String> photoUrls = new ArrayList<>();
		photoUrls.add(VALUE_STRING);
		pet.put(KEY_PHOTOURLS, photoUrls);
		
		JSONObject tag = new JSONObject();
		tag.put(KEY_ID, 0);
		tag.put(KEY_NAME, VALUE_STRING);
		ArrayList<JSONObject> tags = new ArrayList<>();
		tags.add(tag);
		pet.put(KEY_TAGS, tags);
		
		pet.put(KEY_STATUS, VALUE_STATUS_AVAILABLE);
		
		Response res = given()
				.when().contentType(ContentType.JSON).accept(ContentType.JSON).body(pet.toString()).when()
				.post(URI)
				.then().statusCode(STATUS_CODE_OK).statusLine(STATUS_LINE_OK).body(KEY_STATUS, equalTo(VALUE_STATUS_AVAILABLE))
				.log().body().extract().response();
		
		
		assertEquals(STATUS_CODE_OK, res.getStatusCode());
		assertEquals(STATUS_LINE_OK, res.getStatusLine());

		String jsonString = res.asString();
		JsonPath jsonPath = new JsonPath(jsonString);
		assertEquals(VALUE_NAME, jsonPath.get(KEY_NAME));
		assertEquals(VALUE_STATUS_AVAILABLE, jsonPath.get(KEY_STATUS));
		
		id = jsonPath.get(KEY_ID);
		assertNotEquals(0, id, "Id should not be Zero");
	}
	
	@Test
	@Order(5)
	public void test_getById_after_add() throws InterruptedException
	{
		Thread.sleep(2000);
		
		Response res = given()
				.when().contentType(ContentType.JSON).accept(ContentType.JSON)
				.get(URI + "/" + id)
				.then().statusCode(STATUS_CODE_OK).statusLine(STATUS_LINE_OK)
				.log().body().extract().response();
		
		String jsonString = res.asString();
		JsonPath jsonPath = new JsonPath(jsonString);
		//id =  jsonPath.get("id");
		
		assertEquals(id, jsonPath.get(KEY_ID));
		assertEquals(VALUE_NAME, jsonPath.get(KEY_NAME));
	}
	
	@Test
	@Order(6)
	public void test_Update() throws InterruptedException
	{
		Thread.sleep(2000);
		
		JSONObject pet = new JSONObject();
		pet.put(KEY_ID, id);
		pet.put(KEY_NAME, VALUE_UPDATED_NAME);
		
		JSONObject category = new JSONObject();
		category.put(KEY_ID, 0);
		category.put(KEY_NAME, VALUE_STRING);
		pet.put(KEY_CATEGORY, category);
		
		ArrayList<String> photoUrls = new ArrayList<>();
		photoUrls.add(VALUE_STRING);
		pet.put(KEY_PHOTOURLS, photoUrls);
		
		JSONObject tag = new JSONObject();
		tag.put(KEY_ID, 0);
		tag.put(KEY_NAME, VALUE_STRING);
		ArrayList<JSONObject> tags = new ArrayList<>();
		tags.add(tag);
		pet.put(KEY_TAGS, tags);
		
		pet.put(KEY_STATUS, VALUE_STATUS_AVAILABLE);

		Response res = given()
				.when().contentType(ContentType.JSON).accept(ContentType.JSON).body(pet.toString()).when()
				.put(URI)
				.then().statusCode(STATUS_CODE_OK).statusLine(STATUS_LINE_OK).body(KEY_STATUS, equalTo(VALUE_STATUS_AVAILABLE))
				.log().body().extract().response();
		
		
		assertEquals(STATUS_CODE_OK, res.getStatusCode());
		assertEquals(STATUS_LINE_OK, res.getStatusLine());

		String jsonString = res.asString();
		JsonPath jsonPath = new JsonPath(jsonString);
		assertEquals(VALUE_STATUS_AVAILABLE, jsonPath.get(KEY_STATUS));
		assertEquals(id, jsonPath.get(KEY_ID));
		assertEquals(VALUE_UPDATED_NAME, jsonPath.get(KEY_NAME));
	}
	
	@Test
	@Order(7)
	public void test_getById_after_update() throws InterruptedException
	{
		Thread.sleep(2000);
		
		Response res = given()
				.when().contentType(ContentType.JSON).accept(ContentType.JSON)
				.get(URI + "/" + id)
				.then().statusCode(STATUS_CODE_OK).statusLine(STATUS_LINE_OK)
				.log().body().extract().response();
		
		String jsonString = res.asString();
		JsonPath jsonPath = new JsonPath(jsonString);
		assertEquals(id, jsonPath.get(KEY_ID));
		assertEquals(VALUE_UPDATED_NAME, jsonPath.get(KEY_NAME));
	}
	
	@Test
	@Order(8)
	public void test_Delete() {
		Response res = given().when().contentType(ContentType.JSON).accept(ContentType.JSON)
		.delete(URI + "/" + id).then().statusCode(STATUS_CODE_OK)
		.statusLine(STATUS_LINE_OK).log().body().extract().response();
		
		String jsonString = res.asString();
		JsonPath jsonPath = new JsonPath(jsonString);
		assertEquals(STATUS_CODE_OK, (int)jsonPath.get(KEY_CODE));
		assertEquals("" + id, jsonPath.get(KEY_MESSAGE));

	}
	
	@Test
	@Order(9)
	public void test_getById_after_delete() throws InterruptedException
	{
		Thread.sleep(2000);
		
		Response res = given()
				.when().contentType(ContentType.JSON).accept(ContentType.JSON)
				.get(URI + "/" + id)
				.then().statusCode(STATUS_CODE_NOT_FOUND).statusLine(STATUS_LINE_NOT_FOUND)
				.log().body().extract().response();
		
		String jsonString = res.asString();
		JsonPath jsonPath = new JsonPath(jsonString);
		assertEquals(GET_ERROR_CODE, (int)jsonPath.get(KEY_CODE));
		assertEquals(GET_ERROR_MESSAGE, jsonPath.get(KEY_MESSAGE));
	}
}
