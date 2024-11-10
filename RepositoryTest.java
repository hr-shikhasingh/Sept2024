package com.sep.basic.Day1;
	import io.restassured.RestAssured; 
	import io.restassured.specification.RequestSpecification; 
	import org.testng.annotations.BeforeClass; 
	import org.testng.annotations.Test; 
	import static io.restassured.RestAssured.given; 
	import static org.hamcrest.Matchers.equalTo; 
	public class RepositoryTest 
	{ 
		@BeforeClass public void setup() 
		{ 
			RestAssured.baseURI = "https://api.github.com"; // Example base URI for GitHub API 
			} 
		@Test public void testGetRepository() { // Send GET request and verify the response 
			given() 
			.pathParam("owner", "octocat") 
			.pathParam("repo", "Hello-World") 
			.when() 
			.get("/repos/{owner}/{repo}") 
			.then() .statusCode(200) 
			.body("name", equalTo("Hello-World")) 
			.body("owner.login", equalTo("octocat")) 
			.body("private", equalTo(false)); }
	}