package com.runner;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.base.BaseClassForAPI;
import com.reports.Reporting;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(snippets=SnippetType.CAMELCASE,monochrome=true,strict=true,dryRun=false,plugin= {"pretty",
"json:target/api.json" },features= {"src\\test\\resources\\Features"},glue = { "com.stepDefinition" })
public class TestRunnerClass extends BaseClassForAPI {

	@AfterClass
	public static void afterClass() throws IOException {
		String reports = getPropertyFileValue("getReport");
		Reporting.getReports(reports);
	}

	 
	
	
}
