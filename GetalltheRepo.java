package com.sep.basic.Day1;


	import io.restassured.RestAssured; 
	
	import io.restassured.response.Response;
	import org.testng.annotations.BeforeClass; 
	import org.testng.annotations.Test; 
	import static io.restassured.RestAssured.given;
	import static org.hamcrest.Matchers.*;
	public class GetalltheRepo {
	 @BeforeClass
		public void setup() 
	 			{ 
		 		RestAssured.baseURI = "https://api.github.com"; // Base URI for GitHub API 
		 		RestAssured.authentication = RestAssured.oauth2("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"); // Replace with your GitHub personal access token 
		 		} 
		@Test
		public void testGetAllRepositories() { 
			Response response = given()
					.pathParam("hr-shikhasingh","your_username") 
					.when() 
					.get("/users/hr-shikhasingh/repos") 
					.then() 
					.statusCode(200) 
					.body("size()", greaterThan(0)) // Ensure there is at least one repository 
					.extract().response(); 
					System.out.println("Repositories: " + response.asString()); 
		}
	}
