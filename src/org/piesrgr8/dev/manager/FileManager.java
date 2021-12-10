package org.piesrgr8.dev.manager;

import java.io.File;

public class FileManager {
	
	private File f;
	
	public void createDir() {
		JSONManager jsm = new JSONManager();
		f = new File(dir());
		if (f.exists()) {
			return;
		} else {
			f.mkdir();
			jsm.createFiles();
		}
	}
	
	public String dir() {
		String workingDirectory;
		String OS = (System.getProperty("os.name")).toUpperCase();
		
		if (OS.contains("WIN")) {
		    workingDirectory = System.getenv("AppData");
		} else {
		    workingDirectory = System.getProperty("user.home");
		    workingDirectory += "/Library/Application Support";
		}
		return workingDirectory += "/MedTrak/";
	}
}
