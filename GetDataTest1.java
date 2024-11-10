package com.sep.basic.Day1;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class GetDataTest1 {
	String freshToken=null;
	
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI="https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net/";
	}
	

	@Test()
	public void getToken() {
		Response res= RestAssured
		.given()
		.contentType(ContentType.JSON)
		.body("{\"username\":\"sept2024.shikha@tekarch.com\",\"password\":\"Admin123\"}")
		.when()
		.post("login");
		
		res
		.then()
		.statusCode(201)
		.time(lessThan(4000L))
		.contentType(ContentType.JSON);
		
		freshToken= res.jsonPath().get("[0].token");
		System.out.println(freshToken);
		
	}
	@Test(dependsOnMethods = "getToken")
	public void GetAllUSers() {
		Header ob=new Header("token", freshToken);
		Response res= RestAssured
		.given()
		.header(ob)
		.when()
		.get("getdata");
		
		res
		.then()
		.statusCode(200)
		//.time(lessThan(20000L))
		.contentType(ContentType.JSON);
		
		//res.prettyPrint();
		
		System.out.println("total records="+res.jsonPath().get("size()"));
		System.out.println("first record account no="+res.jsonPath().get("[0].accountno"));
		System.out.println("first record user id="+res.body().jsonPath().get("[0].userid"));
		System.out.println("first record id="+res.body().jsonPath().get("[0].id"));
		
	
		
	}
	
	
	

}