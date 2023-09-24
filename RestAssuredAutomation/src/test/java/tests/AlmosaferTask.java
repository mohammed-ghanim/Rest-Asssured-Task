package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.joda.time.LocalDate;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Arrays;
import java.util.HashMap;
import io.restassured.http.ContentType;

public class AlmosaferTask {
	
	@Test
	public void searchFlight ( ) {
		
		String origionDepartureDate = LocalDate.now().toString();
		String destinationDepartureDate = LocalDate.now().plusDays(15).toString();
		
		baseURI = "https://global.almosafer.com";
		given().get
		("/api/v3/flights/flight/search?query=AMM-DXB/" + origionDepartureDate + "/" + destinationDepartureDate + "/Economy/1Adult")
		.then().assertThat().body(matchesJsonSchemaInClasspath("searchFlightSchema.json"))
		.statusCode(200)
		.body("request.leg[0].originId", equalTo("AMM"))
		.body("request.leg[1].originId", equalTo("DXB"))
		.body("request.leg[0].destinationId", equalTo("DXB"))
		.body("request.leg[1].destinationId", equalTo("AMM"))
		.body("request.leg[0].departure", equalTo(origionDepartureDate))
		.body("request.leg[1].departure", equalTo(destinationDepartureDate))
		.body("request.cabin", equalTo("Economy"))
		.body("request.pax.adult", equalTo(1))
		.log().all();

	}
	
	@Test
	public void engimaHotelSearch() {
		
		baseURI = "https://global.almosafer.com";
		
		JSONObject request = new JSONObject();
		
		String checkIn = LocalDate.now().plusDays(2).toString();
		String checkOut = LocalDate.now().plusDays(4).toString();
		
		
		request.put("checkIn",checkIn);
		request.put("checkOut",checkOut);
		Map<String, Object> roomsInfo = new LinkedHashMap<>();
		request.put("roomsInfo", Arrays.asList(new LinkedHashMap<String, Object>() {
		    {
		        put("adultsCount", 2); }}));
		request.put("searchInfo", null);
		request.put("crossSellDetail", null);
		request.put("placeId", "ChIJobufry48AxURgljBoEj_jPM");
		
		given()
		.header("Content-Type", "application/json")
		.header("Token", "4R!eVj7$&7Q8Duhv1#pB")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(request.toJSONString())
		.when()
		.post("/api/enigma/search/async")
		.then()
		.statusCode(200)
		.log().all();
	}
		
	}
