package dev.tylermanning.Band;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.json.JSONObject;
import org.junit.Test;

public class BandTest {

	@Test(timeout = 500)
	public void testSearchSpeed() {
		long time = System.currentTimeMillis();
		
		Band band = new Band();		
		JSONObject actual = new JSONObject();
		actual.put("firstname", "tyler");
		actual.put("lastname", "manning");
		actual.put("address", "7833 Village Dr.");
		band.addToBand(actual, new String[] {"firstname","lastname", "address"});
		
		actual = new JSONObject();
		actual.put("firstname", "tyler");
		actual.put("lastname", "manning");
		actual.put("address", "2211 Hannaford Ave");
		band.addToBand(actual, new String[] {"firstname","lastname", "address"});
		
		ArrayList<String> names = new ArrayList<String>();
		
		for (int i = 0; i < 28828; i++) {
			
			JSONObject test = new JSONObject();
			String firstname = getRandomString();
			test.put("firstname", firstname);
			test.put("lastname", getRandomString());
			test.put("address", getRandomString());
			test.put("city", getRandomString());
			test.put("state", getRandomString());
			test.put("country", getRandomString());
			test.put("elevation", getRandomString());
			test.put("lat", getRandomString());
			test.put("lon", getRandomString());
			test.put("tz", getRandomString());
			band.addToBand(test, new String[] {"firstname","lastname", "address", "city","state","country","elevation","lat","lon", "tz"});
//			System.out.println((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / Math.pow(2, 20));
			if ( i % 1000 == 0 ) {
				names.add(firstname);
			}
		}
		
		System.out.println(band.search("firstname", "tyler"));
		for (String string : names) {	
			time = System.nanoTime();
			System.out.println(band.search("firstname", string));
			assertTrue("Method completed in half a second", System.nanoTime() - time < 3500000);
		}

		
	}

	private static String getRandomString() {
		int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    return buffer.toString();
	}

	private JSONObject generateJson(String pro, String value) {
		JSONObject test = new JSONObject();		
		test.put(pro, value);
		return test;
	}

}
