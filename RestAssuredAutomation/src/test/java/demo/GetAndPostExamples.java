package demo;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.Map;
import java.util.HashMap;

public class GetAndPostExamples {
	
	@Test
	public void testGet() {
	 baseURI = "https://reqres.in/api";
	 
	 given().get("/users?page=2")
	 .then().statusCode(200)
	 .body("data[4].first_name", equalTo("George"))
	 .body("data.first_name", hasItems("George","Rachel"));
	 
	}
	
	@Test
	public void testPost() {
		baseURI = "https://reqres.in/api";
//		Map<String, Object> map = new HashMap<String,Object>();
//		
//		map.put("name","Raghav");
//		map.put("Job", "Teacher");
//		System.out.println(map);
		
		JSONObject request = new JSONObject();
		request.put("name","Raghav");
		request.put("Job", "Teacher");
		System.out.println(request.toJSONString());
		
		given().
		header("Content-Type", "application/json")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(request.toJSONString())
		.when()
		.post("/users")
		.then()
		.statusCode(201).log().all();
	}

}
