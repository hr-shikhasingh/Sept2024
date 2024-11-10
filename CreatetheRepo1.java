package com.sep.basic.Day1;


import io.restassured.RestAssured;
import io.restassured.response.Response; 
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test; 
import static io.restassured.RestAssured.given; 
import static org.hamcrest.Matchers.equalTo; 
public class CreatetheRepo1 {
	@BeforeClass
	public void setup() { RestAssured.baseURI = "https://api.github.com"; 
	// Base URI for GitHub API RestAssured.authentication = RestAssured.oauth2("your_github_token"); 
	// Replace with your GitHub personal access token }
	}
	@Test
	public void testCreateDuplicateRepository() { String requestBody = "{\n" + " \"name\": \"existing-repo\",\n" + " \"description\": \"This is a test repository\",\n" + " \"private\": false\n" + "}";
	// Create the repository for the first time Response createResponse = given() .header("Content-Type",
	}
Response createResponse = given() 
.header("Content-Type", "application/json") 
.body(requestBody) 
.when() 
.post("/user/repos") 
.then() .statusCode(201) .body("name", equalTo("existing-repo"))
.extract().response();

System.out.println("Repository Created: " + createResponse.jsonPath().getString("name"));
// Attempt to create the same repository again to check for duplicate error
given() 
header("Content-Type", "application/json") 
.body(requestBody) 
.when() 
.post("/user/repos")
.then()
.statusCode(422) 
.body("message", equalTo("Repository creation failed.")); }