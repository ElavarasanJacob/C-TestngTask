package com.reports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.base.BaseClassForAPI;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
public class Reporting extends BaseClassForAPI {
	
	public static void getReports(String jsonFile) throws FileNotFoundException, IOException {
		File file = new File(getPropertyFileValue("JVMReport"));
		Configuration configuration = new Configuration(file, "OMR Branch application API Automation");
		configuration.addClassifications("Browser", "Chrome");
		configuration.addClassifications("Version", "103");
		configuration.addClassifications("OS", "WIN10");
		List<String> jsonFiles =new ArrayList<String>();
		jsonFiles.add(jsonFile);
		ReportBuilder builder= new ReportBuilder(jsonFiles, configuration);
		builder.generateReports();
		
}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
