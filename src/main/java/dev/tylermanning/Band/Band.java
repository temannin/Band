package dev.tylermanning.Band;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class Band {
	
	public Band() { }
	
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
		Collection<JSONObject> temp = store.get(property).get(value);
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
		return "NOT IMPLEMENTED";
	}

	
	public JSONArray getResults() {		
		Entry<String, MultiValuedMap<String, JSONObject>> it = this.store.entrySet().iterator().next();
		Collection<Collection<JSONObject>> map = it.getValue().asMap().values();
		Iterator mapIter = map.iterator();
		if (mapIter.hasNext()) {
			return new JSONArray(mapIter.next().toString());
		}
		else {
			return new JSONArray();
		}
		
	}
}
