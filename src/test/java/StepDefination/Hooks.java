package StepDefination;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	 @Before("@DeletePlace")
	 public void beforeScenario() throws IOException
	 {
		 StepDefination sd=new StepDefination();
		 if(StepDefination.place_id==null)
		 {
			 sd.add_place_payload_with("Rahul", "English", "Asia");
		 
			 sd.user_calls_with_http_request("AddPlaceAPI", "POST");
			 sd.verify_place_id_created_maps_to_using("Rahul", "GetPlaceAPI");
		 }
	 }
}
