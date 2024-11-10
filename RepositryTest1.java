package com.sep.basic.Day1;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test; 
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RepositryTest1 {
		@BeforeClass 
		public void setup() {
	RestAssured.baseURI = "https://api.github.com"; // Example base URI for GitHub API 
		}
	@Test 
	public void testGetNonExistingRepository() { // Send GET request and verify the response for a non-existing repository 
		given() .pathParam("owner", "octocat")
		.pathParam("repo", "non-existing-repo") .when() .get("/repos/{owner}/{repo}")
		.then() .statusCode(404) .body("message", equalTo("Not Found")); }
	
	}


