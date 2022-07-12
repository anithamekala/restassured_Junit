package com.rest.assured.petstore.scripts;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

import static com.rest.assured.petstore.scripts.TestConstants.*;

public class DeleteById {

	@Test
	public void test_DeletePet() {
		given().when().contentType(ContentType.JSON).accept(ContentType.JSON)
				.delete(URI + "/" + id).then().statusCode(STATUS_CODE_NOT_FOUND)
				.statusLine(STATUS_LINE_NOT_FOUND).log().body().extract().response();
	}
}
