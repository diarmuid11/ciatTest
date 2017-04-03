package org.ciat.test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Persistence {
	
	private static Persistence instance = new Persistence();
	
	private JSONObject data = new JSONObject();

	private Map<String,String> headQuarters = new TreeMap<String,String>();

	private TreeMap<String,String> types = new TreeMap<String,String>();
	
	private TreeMap<String,String> countries = new TreeMap<String,String>();

	@SuppressWarnings("unchecked")
	private Persistence() throws RuntimeException{
        //headQuarters.putAll((Map<String,String>)parser.parse(new FileReader("testData/HeadQuarter.json")));
		try {
	        JSONParser parser = new JSONParser();
	        File partner = new File("testData/Partner.json");
	        if (!partner.exists()) {
	        	//partner.createNewFile();
		        FileWriter fw = new FileWriter("testData/Partner.json");
	        	fw.write("{}");
	        	fw.close();
	        }
	        FileReader fr1 = new FileReader("testData/Partner.json");
	        data = (JSONObject)parser.parse(fr1);
	        fr1.close();
			buildHeadQuarters();
	        FileReader fr2 = new FileReader("testData/Type.json");
			types.putAll((Map<String,String>)parser.parse(new FileReader("testData/Type.json")));
	        fr2.close();
	        FileReader fr3 = new FileReader("testData/Country.json");
			countries.putAll((Map<String,String>)parser.parse(new FileReader("testData/Country.json")));
	        fr3.close();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	private void buildHeadQuarters() {
		for (Object key:data.keySet()) {
			JSONObject partner = (JSONObject)data.get(key);
			headQuarters.put((String)key, (String)partner.get("name"));
		}
	}
	
	public static Persistence getInstance() {
		return instance;
	}
	
	public Map<String,String> getHeadQuarters(){
		return this.headQuarters;
	}
	
	public Map<String,String> getTypes(){
		return this.types;
	}
	
	public Map<String,String> getCountries(){
		return this.countries;
	}
	
	@SuppressWarnings("unchecked")
	public List<JSONObject> getPartners(){
		List<JSONObject> result = new ArrayList<JSONObject>();
		for (String hqKey:getHeadQuarters().keySet()) {
			//Map<String,Object> row = new HashMap<String, Object>();
			JSONObject partner = (JSONObject)data.get(hqKey);
			partner.put("branch", "NO");
			partner.put("headQuarter", "");
			JSONObject branches = (JSONObject)partner.get("branches");
			for (Object oBranch:branches.values()) {
				JSONObject branch = (JSONObject)oBranch;
				partner.put("branch", "SI");
				partner.put("headQuarter", partner.get("acronym"));
				result.add(branch);
			}
			//row.putAll(partner);
			result.add(partner);
		}
		return result;
		
	}
	
	@SuppressWarnings("unchecked")
	public void addPartner(String acronym,String name,String type,String country,String city,String url) throws Exception {
		if (headQuarters.get(acronym)!=null) {
			throw new Exception("A partner with same acronym already exists");
		}
		if (headQuarters.containsValue(name)) {
			throw new Exception("A partner with same name already exists");
		}
		data.put(acronym, buildPartner(acronym,name,type,country,city,url));
		writeData();
		headQuarters.put(acronym, name);
	}
	
	@SuppressWarnings("unchecked")
	public void addBranch(String headQuarter,String acronym,String name,String type,String country,String city,String url) throws Exception {
		JSONObject partner = (JSONObject)data.get(headQuarter);
		JSONObject otherBranches = (JSONObject)partner.get("branches");
		if (partner.get("name").equals(name)||partner.get("acronym").equals(acronym)) {  //tiene el mismo nombre o acronimo que el hq
			if (partner.get("city").equals(city) || partner.get("country").equals(country)) {
				throw new Exception("This branch has the same location as headQuarter");
			}
			for (Object otherBranch:otherBranches.values()) {
				JSONObject jo = (JSONObject)otherBranch;
				if (jo.get("city").equals(city) || jo.get("country").equals(country)) {  //tiene el mismo nombre o acronimo de una branch del mismo HQ
					if (jo.get("city").equals(city) || jo.get("country").equals(country)) {
						throw new Exception("This branch has the same location as another fellow branch");
					}
				}
			}
			
		}
		otherBranches.put(acronym, buildPartner(acronym,name,type,country,city,url));
		data.put(acronym, partner);
		writeData();
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject buildPartner(String acronym,String name,String type,String country,String city,String url) {
		JSONObject partner = new JSONObject();
		partner.put("acronym", acronym);
		partner.put("name", name);
		partner.put("type", type);
		partner.put("country", country);
		partner.put("city", city);
		partner.put("url", url);
		partner.put("branches",new JSONObject());
		return partner;
		
	}

	
	public void writeData() throws IOException {
		File file = new File("testData/Partner.json");
		FileWriter fw = new FileWriter(file,false);
		data.writeJSONString(fw);
		fw.close();
	}
	
	
	

	
}
