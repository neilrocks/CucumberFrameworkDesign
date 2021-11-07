package StepDefination;

import static io.restassured.RestAssured.given;


import java.io.IOException;

import static org.junit.Assert.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import resources.*;

public class StepDefination extends Utilis {
	
	RequestSpecification res;
	ResponseSpecification respec;
	Response response;
	TestData data=new TestData();
	static String place_id;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		res=given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));
	}
	
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
		APIResources resourceAPI=APIResources.valueOf(resource);//from enum class APIResources.java

		respec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
			response=res.when().post(resourceAPI.getResource());   //accessing the resource value from enum
//				.then().spec(respec).extract().response();
		else if(method.equalsIgnoreCase("GET"))
			response=res.when().get(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("DELETE"))
			response=res.when().delete(resourceAPI.getResource());
	}
	


	@Then("The API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(response.getStatusCode(),200);
	}
	

	@And("{string} in response body is {string}")
	public void status_in_response_body_is_ok(String key,String value) {
	    // Write code here that turns the phrase above into concrete actions
		
		assertEquals(getJSONPath(response,key),value);
	    
	}
	@And("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expected_name, String method) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		place_id=getJSONPath(response,"place_id");
		res=given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(method,"GET");
		String actual_name=getJSONPath(response,"name");
		assertEquals(actual_name,expected_name);
		
	}
	
	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		res=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}



}
