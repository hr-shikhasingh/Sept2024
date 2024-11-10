package com.sep.basic.Day1;

package com.sep.basic.Day1;


import java.io.File;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class CreateDataTest {
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
		.body("{\"username\":\"divyashree@ta.com\",\"password\":\"divya@123\"}")
		//.body(cur_dir+"/src/test/resources/requestData/login.json")
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
	public void CreateUser() {
		
		Header tokenHeader=new Header("token", freshToken);
		Response res= RestAssured
				.given()
				.header(tokenHeader)
				.contentType(ContentType.JSON)
				.body("{\"accountno\":\"TA-divya01\",\"departmentno\":\"1\",\"salary\":\"1000\",\"pincode\":\"111111\"}")
				.when()
				.post("addData");
		
		res.then()
		.statusCode(201)
		.time(lessThan(10000L))
		.contentType(ContentType.JSON);
		
		res.prettyPrint();
		res.then().body("status", equalTo("success"));
		
	}

}

