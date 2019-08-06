package com.cucumber.TestRunner;

import java.io.IOException;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.cucumber.listener.Reporter;
import com.cucumber.utility.Log;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = { "src/test/resources/features/BookTicket.feature" }, glue = {
		"com/cucumber/stepdefination" }, plugin = { "json:target/cucumber.json", "html:target/cucumber-pretty",
				"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-extent-reports/Extent_Report.html",
				"pretty" }, monochrome = true, dryRun = false, strict = true)

public class BookTicketRunner extends AbstractTestNGCucumberTests {

	String testcasename;
	

	@BeforeClass
	public void beforeclass() {

		testcasename = this.getClass().getSimpleName();
		DOMConfigurator.configure("log4j.xml");

		System.out.println("***************** " + testcasename + " Test satrting *****************");

		// DOMConfigurator.configure("log4j.xml");
		// "html:target/cucumber-reports/cucumber-pretty",
		// "rerun:target/cucumber-reports/rerun.txt"

		Log.startTestCase(testcasename);
	}

	@AfterClass(alwaysRun = true)
	public void testDownClass() throws IOException {

		//Reporter.loadXMLConfig(new File(FileReaderManager.getInstance().getConfigReader().getReportConfigPath()));

		// Reporter.loadXMLConfig(new File(ConfigFileReader.getReportConfigPath()));

		Reporter.addScreenCaptureFromPath("screenshotPath");
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("os", "Window");
		Reporter.setTestRunnerOutput("Sample test runner output message");

		Log.endTestCase(testcasename);
		System.out.println("***************** " + testcasename + " Test end *****************");

	}

}
