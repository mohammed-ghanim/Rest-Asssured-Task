package demo;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import org.testng.annotations.Test;

public class JSONSchemaValidator {
	
	@Test
	public void testGet() {
	 baseURI = "https://reqres.in/api";
	 
	 given().get("/users?page=2")
	 .then().assertThat().body(matchesJsonSchemaInClasspath("schema.json")).statusCode(200);
	 
	}

}