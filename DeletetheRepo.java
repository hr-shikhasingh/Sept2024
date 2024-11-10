package com.sep.basic.Day1;
import io.restassured.RestAssured; 
import org.testng.annotations.BeforeClass; 
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given; 
import static org.hamcrest.Matchers.nullValue; 
public class DeletetheRepo { 
	@BeforeClass public void setup() { 
	RestAssured.baseURI = "https://api.github.com"; 
	// Base URI for GitHub API 
	RestAssured.authentication = RestAssured.oauth2("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");
	// Replace with your GitHub personal access token } 
	}
	@Test
	public void testDeleteRepository() {
		given() 
		.pathParam("hr-shikhasingh", "your_username") 
		// Replace with the repository owner's username
		.pathParam("repo", "repo-to-delete") // Replace with the name of the repository to delete 
		.when() 
		.delete("/repos/{owner}/{repo}") 
		.then()
		.statusCode(204);
		// Validate status code is 204 .body(equalTo(nullValue())); 
		// Validate body is null
		System.out.println("Repository deleted successfully."); } }	
	
	
	
