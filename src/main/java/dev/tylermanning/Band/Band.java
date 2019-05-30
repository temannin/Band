package dev.tylermanning.Band;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.json.JSONObject;

public class Band {
	
	private HashMap<String, MultiValuedMap<String, JSONObject>> store = new HashMap<String, MultiValuedMap<String, JSONObject>>();
	
	public void addToBand(JSONObject json, String[] properties) {
		for (String property : properties) {
			if (store.containsKey(property)) {
				store.get(property).put(json.getString(property), json);
			}
			else {
				MultiValuedMap<String, JSONObject> temp = new ArrayListValuedHashMap<String, JSONObject>();
				temp.put(json.getString(property), json);
				store.put(property, temp);
				
			}
		}
	}

	public Band search(String property, String value) {
		System.out.println("Searching " + property + "for " +  value);
		double startTime = System.nanoTime();
		Collection<JSONObject> temp = store.get(property).get(value);		
		double endTime = System.nanoTime();

		double duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds.
		
		NumberFormat formatter = new DecimalFormat("#.#######"); 
		String f = formatter.format(duration); 
		System.out.println("operation took " + f + " ms");
		return Band.convertTo(temp);
	}

	private static Band convertTo(Collection<JSONObject> temp) {
		Band tempBand = new Band();
		for (JSONObject jsonObject : temp) {
			Iterator it = jsonObject.keys();
		    while (it.hasNext()) {
		        tempBand.addToBand(jsonObject, new String[] {it.next().toString()});
		    }
		}
		return tempBand;
	}

	@Override
	public String toString() {
		
		int totalSize = 0;
		String repr = "";
		
		Iterator it = this.store.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        MultiValuedMap<String, JSONObject> temp = (MultiValuedMap<String, JSONObject>) pair.getValue();
	        totalSize += temp.size();
	        repr += temp;
	    }
	    return repr;
//	    return String.valueOf(totalSize);
	}
}
