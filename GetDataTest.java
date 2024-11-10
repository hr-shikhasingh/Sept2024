package com.sep.basic.Day1;
//import org.checkerframework.checker.index.qual.LessThan;//
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetDataTest {

	@Test
	public void getToken() {
		RestAssured.baseURI="https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net/";
		Response res= RestAssured
		.given()
		.contentType(ContentType.JSON)
		.body("{\"username\":\"sept2024.shikha@tekarch\",\"password\":\"Admin123\"}")
		.when()
		.post("login");
		
		res
		.then()
		.statusCode(201)
		.time(lessThan(4000L))
		.contentType(ContentType.JSON);
		
		res.prettyPrint();
		
		String token= res.jsonPath().get("[0].token");
		System.out.println(token);
	}
}