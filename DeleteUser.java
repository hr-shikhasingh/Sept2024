package com.sep.basic.Day1;
import java.io.File;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class DeleteUser {
	String freshToken;
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI="https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net/";
	}
	@Test
	public void logintoTekarchRequest() {
		
		 String cur_dir=System.getProperty("user.dir");
		Response res= RestAssured
		.given()
		.contentType(ContentType.JSON)
		.body(new File(cur_dir+"/src/test/resources/requestData/login.json"))
		.when()
		.post("login");
		
		res
		.then()
		.statusCode(201)
		.time(lessThan(5000L))
		.contentType(ContentType.JSON);
		
		res.prettyPrint();
		
		freshToken=res.jsonPath().get("[0].token");
		System.out.println("token="+freshToken);
		
	}
	@Test(dependsOnMethods = "logintoTekarchRequest")
	public void deleteUser() {
		
		Header tokenHeader=new Header("token", freshToken);
		Response res= RestAssured
				.given()
				.header(tokenHeader)
				.contentType(ContentType.JSON)
				
				.body("{\"userid\":\"gQZ4fqeDFg3Q5GXeyGrY\",\"id\":\"\"r7lR0nll067ZYBazO74Y}")
				.when()
				.delete("deleteData");
		
		res.then()
		.statusCode(200)
		.time(lessThan(10000L))
		.contentType(ContentType.JSON);
		
		res.prettyPrint();
		res.then().body("status", equalTo("success"));
		
	
		
		
	}

}