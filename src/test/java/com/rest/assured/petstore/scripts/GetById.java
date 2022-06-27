package com.rest.assured.petstore.scripts;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static com.rest.assured.petstore.scripts.TestConstants.*;

public class GetById {
	@Test
	public void test_id()
	{
		
		Response res = given()
				.when().contentType(ContentType.JSON).accept(ContentType.JSON)
				.get(URI)
				.then().statusCode(STATUS_CODE_OK).statusLine(STATUS_LINE_OK)
				.log().body().extract().response();
		
		String jsonString = res.asString();
		JsonPath jsonPath = new JsonPath(jsonString);
		//assertEquals(id, jsonPath.get(KEY_ID));
		assertEquals(VALUE_NAME, jsonPath.get(KEY_NAME));
	}
}
