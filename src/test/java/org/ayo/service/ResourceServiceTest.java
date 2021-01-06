package org.ayo.service;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class ResourceServiceTest {

	@Test
	void testIfGetWorks() {
		given()
			.when()
			.get("/resources")
			.then()
			.statusCode(200);
	}

}
