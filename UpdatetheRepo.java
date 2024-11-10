package com.sep.basic.Day1;

import io.restassured.RestAssured; 
import io.restassured.response.Response; 
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test; 
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class UpdatetheRepo {
	@BeforeClass 
	public void setup() { RestAssured.baseURI = "https://api.github.com"; 
	// Base URI for GitHub API
	RestAssured.authentication = RestAssured.oauth2("your_github_token"); 
	// Replace with your GitHub personal access token } 
	@Test 
	public void testUpdateRepositoryName() {
		String requestBody = "{\n" + " \"name\": \"new-repo-name\",\n" + " \"description\": \"my repository created using apis after update\",\n" + " \"public\": false\n" + "}"; 
		Response response = given() 
		.header("Content-Type", "application/json") .body(requestBody) 
		.pathParam("owner", "your_username")
		
		.pathParam("repo", "existing-repo") // Replace with the existing repository name 
		.when() 
		.patch("/repos/{owner}/{repo}")
		.then() 
		.statusCode(200) // Validate status code is 200 
		.body("name", equalTo("new-repo-name")) // Validate new repository name is as in the request
		.extract().response(); 
		System.out.println("Repository Updated: " + response.jsonPath().getString("name")); } 
	}

	//private void testUpdateRepositoryName() {
		// TODO Auto-generated method stub
		
	}
	

