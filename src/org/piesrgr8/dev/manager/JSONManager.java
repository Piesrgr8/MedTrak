package org.piesrgr8.dev.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONManager {
	
	private FileManager fm = new FileManager();
	private String pillPath = fm.dir() + "Pills.json";

	@SuppressWarnings("unchecked")
	public void createPillData(String name, String grams, String time) {
		JSONObject details = new JSONObject();
		details.put("name", name);
		details.put("grams", grams);
		details.put("time", time);

		JSONObject obj = new JSONObject();
		obj.put("pill", details);

		try (FileWriter file = new FileWriter(pillPath)) {
			// We can write any JSONArray or JSONObject instance to the file
			file.write(obj.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void readAllPillData() {
		 JSONParser jsonParser = new JSONParser();
         
	        try (FileReader reader = new FileReader(pillPath)) {
	            //Read JSON file
	            Object obj = jsonParser.parse(reader);
	 
	            JSONArray employeeList = (JSONArray) obj;
	            System.out.println(employeeList);
	             
	            //Iterate over employee array
	            employeeList.forEach( emp -> parsePillObject( (JSONObject) emp ) );
	 
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	    }
	
	public void createFiles() {
		File pillFile = new File(pillPath);
		if (!pillFile.exists()) {
			try {
				pillFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
    private void parsePillObject(JSONObject pill) {
        //Get employee object within list
        JSONObject pillObject = (JSONObject) pill.get("pill");
         
        //Get employee first name
        String name = (String) pillObject.get("name");    
        System.out.println(name);
         
        //Get employee last name
        String grams = (String) pillObject.get("grams");  
        System.out.println(grams);
         
        //Get employee time name
        String time = (String) pillObject.get("time");    
        System.out.println(time);
    }
}
