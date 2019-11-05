package com.cucumber.stepdefination;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.cucumber.listener.Reporter;
import com.cucumber.utility.TestContext;
import com.google.common.io.Files;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	TestContext managerReder;

	public Hooks(TestContext reader) {

		managerReder = reader;
	}

	@Before(order = 0)
	public void beforeScenarioStart() {

		System.out.println("-----------------Start of Scenario-----------------");
	}

	@Before(order = 1)
	public void beforeScenario() {

		System.out.println("Start the browser and Clear the cookies");
	}

	@After(order = 0)
	public void afterScenarioFinish() {

		System.out.println("-----------------End of Scenario-----------------");
	}

	/*@After(order = 1)
	public void afterScenario() {

		System.out.println("Log out the user and close the browser");
	}*/

	@After(order = 1)
	public void afterScenario(Scenario scenario) throws InterruptedException {

		if (scenario.isFailed()) {

			String screenshotName = scenario.getName().replaceAll(" ", "_");

			System.out.println(screenshotName);

			try {

				File sourcePath = ((TakesScreenshot) managerReder.getWebDriverManager().getDriver())
						.getScreenshotAs(OutputType.FILE);

				File destinationPath = new File(
						System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png");

				System.out.println(destinationPath);

				Files.copy(sourcePath, destinationPath);

				Reporter.addScreenCaptureFromPath(destinationPath.toString());
				Thread.sleep(2000);

				System.out.println("Log out the user and close the browser");

			} catch (IOException e) {
			}
		}
	}

	@After(order = 0)
	public void AfterSteps() {

		managerReder.getWebDriverManager().quitDriver();
	}

}
