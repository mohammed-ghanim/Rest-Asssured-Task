package demo;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TestOne {
	

@Test
public void searchFlight ( ) {
	
	baseURI = "https://global.almosafer.com";
	given().get
	("/api/v3/flights/flight/search?query=AMM-DXB/2023-10-01/2023-10-10/Economy/1Adult").then().statusCode(200).
	body("request.leg[0].originId", equalTo("AMM")).body("request.leg[1].originId", equalTo("DXB"))
	.body("request.leg[0].destinationId", equalTo("DXB")).body("request.leg[1].destinationId", equalTo("AMM"));

}
}
