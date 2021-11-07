package resources;

import java.util.ArrayList;

import pojo.Location;
import pojo.LocationDetails;

public class TestData {
	public LocationDetails addPlacePayload(String name,String language,String address)
	{
		ArrayList<String>list=new ArrayList<>();
		list.add("shop");
		list.add("shoe park");
		LocationDetails l=new LocationDetails();
		Location l1=new Location();
		l1.setLat(-38.383494);
		l1.setLng(33.427362);
		l.setAccuracy(50);
		l.setAddress(address);
		l.setLanguage(language);
		l.setWebsite("http://google.com");
		l.setLocation(l1);
		l.setPhone_number("(+91) 983 893 3937");
		l.setName(name);
		l.setTypes(list);
		return l;
		
	}
	
	public String deletePlacePayload(String place_id)
	{
		return "{\n  \"place_id\":\""+place_id+"\"\n}\n";
	}
	
	
	   
	 
}
