package com.rest.assured.petstore.scripts;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.InputStream;

import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static com.rest.assured.petstore.scripts.TestConstants.*;

public class Post_File {
	@Test
	public void test_addNewPet() {
		InputStream ioStream = this.getClass().getClassLoader().getResourceAsStream(POST_REQUEST_JSON_FILE);

		Response res = given()
				.when().contentType(ContentType.JSON).accept(ContentType.JSON).body(ioStream).when()
				.post(URI)
				.then().statusCode(STATUS_CODE_OK).statusLine(STATUS_LINE_OK).body(KEY_STATUS, equalTo(VALUE_STATUS_AVAILABLE))
				.log().body().extract().response();
		
		
		assertEquals(STATUS_CODE_OK, res.getStatusCode());
		assertEquals(STATUS_LINE_OK, res.getStatusLine());

		String jsonString = res.asString();
		JsonPath jsonPath = new JsonPath(jsonString);
		assertEquals(VALUE_STATUS_AVAILABLE, jsonPath.get(KEY_STATUS));
		
		id = jsonPath.get(KEY_ID);
		assertNotEquals(0, id, "Id should not be Zero");
	}
}
