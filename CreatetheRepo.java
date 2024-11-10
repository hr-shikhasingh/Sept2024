package com.sep.basic.Day1;
import io.restassured.RestAssured; 
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreatetheRepo {
 @BeforeClass
 public void setup() {
	  RestAssured.baseURI = "https://api.github.com"; // Base URI for GitHub API
	  RestAssured.authentication = RestAssured.oauth2("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"); // Replace with your GitHub personal access token
	    }

	@Test
	    public void testCreateRepository() {
	        String requestBody = "{\n" +
	                "  \"Hello-World\": \"test-repo\",\n" +
	                "  \"description\": \"This is your first repo\",\n" +
	                "  \"private\": false\n" +
	                "}";

	        Response response = given()
	            .header("Content-Type", "application/json")
	            .body(requestBody)
	            .when()
	            .post("/user/repos")
	            .then()
	            .statusCode(201)
	            .body("name", equalTo("test-repo"))
	            .extract().response();

	        System.out.println("Repository Created: " + response.jsonPath().getString("name"));
	    }
	

}


