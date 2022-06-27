package com.rest.assured.petstore.scripts;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static com.rest.assured.petstore.scripts.TestConstants.*;

public class GetByStatus {

	@Test
	public void test_available() {

		Response res = given().queryParam(KEY_STATUS, "available").when().accept(ContentType.JSON).get(URI);
		
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
	public void test_pending() {

		Response res = given().queryParam(KEY_STATUS, VALUE_STATUS_PENDING).when().accept(ContentType.JSON).get(URI);
		
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
	public void test_sold() {

		Response res = given().queryParam(KEY_STATUS, VALUE_STATUS_SOLD).when().accept(ContentType.JSON).get(URI);
		
		assertEquals(STATUS_CODE_OK, res.getStatusCode());
		assertEquals(STATUS_LINE_OK, res.getStatusLine());
		
		String jsonString = res.asString();
		JsonPath jsonPath = new JsonPath(jsonString);
		List<String> statuses = jsonPath.getList(KEY_STATUS);
		for (String status: statuses) {
			assertEquals(VALUE_STATUS_SOLD, status);
		}
	}
}
