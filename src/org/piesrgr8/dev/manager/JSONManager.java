package org.piesrgr8.dev.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class JSONManager {
	
	private FileManager fm = new FileManager();
	private String pillPath = fm.dir() + "pills.csv";
	private String trackPath = fm.dir() + "tracking.csv";

	public void createPillData(String name, String grams, String time) {
		List<List<String>> data = Arrays.asList(
				Arrays.asList(name, grams, time));
		

		try (FileWriter csvWriter = new FileWriter(pillPath , true)) {
			for (List<String> rowData : data) {
			    csvWriter.append(String.join(",", rowData));
			    csvWriter.append("\n");
			}
			csvWriter.flush();
			csvWriter.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void createTrackData(String name) {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        String strDate = dateFormat.format(date);
        String strTime = timeFormat.format(date);
        
		List<List<String>> data = Arrays.asList(
				Arrays.asList(strDate, strTime, name));
		

		try (FileWriter csvWriter = new FileWriter(trackPath , true)) {
			for (List<String> rowData : data) {
			    csvWriter.append(String.join(",", rowData));
			    csvWriter.append("\n");
			}
			csvWriter.flush();
			csvWriter.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public ArrayList<String[]> readAllPillData() {
		ArrayList<String[]> list = new ArrayList<String[]>();
		try (BufferedReader csvReader = new BufferedReader(new FileReader(pillPath))) {
			String row;
			while ((row = csvReader.readLine()) != null) {
			    String[] read = row.split(",");
			    System.out.println(read[0] + " " + read[1] + " " + read[2]);
			    list.add(read);
			}
			csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public String[] listPillData() {
		ArrayList<String> list = new ArrayList<String>();
		try (BufferedReader csvReader = new BufferedReader(new FileReader(pillPath))) {
			String row;
			
			while ((row = csvReader.readLine()) != null) {
			    String[] read = row.split(",");
			    
			    //Why tf is this not working?!?!?
			    if (read[0] == "Name") 
			    	continue;
			   
			    list.add(read[0]);
			}
			
			csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list.toArray(new String[list.size()]);
	}
	
	public ArrayList<String[]> listTrackData() {
		ArrayList<String[]> list = new ArrayList<String[]>();
		try (BufferedReader csvReader = new BufferedReader(new FileReader(trackPath))) {
			String row;
			while ((row = csvReader.readLine()) != null) {
			    String[] read = row.split(",");
			    list.add(read);
			}
			
			csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void createFiles() {
		File pillFile = new File(pillPath);
		File trackFile = new File(trackPath);
		if (!pillFile.exists()) {
			try {
				pillFile.createNewFile();
				trackFile.createNewFile();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
