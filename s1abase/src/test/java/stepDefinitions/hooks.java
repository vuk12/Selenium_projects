package stepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.qameta.allure.Attachment;
import resources.base;

public class hooks extends base {
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(hooks.class.getName());
	@Before
	public void doSomethingBefore() throws IOException {
		log.info("executed the doSomethingBefore method in hooks");
	}

	@After
	public void AfterScenario(Scenario scenario) throws IOException {
		log.info("executing the AfterScenario method in hooks");
		driver = getDriver();
		if (scenario.isFailed()) {
			embedScreenshot(scenario);
			driver.quit();
		}
		driver.quit();
	}

	@BeforeMethod
	public void BeforeStep(Scenario scenario) {
		log.info("executed the BeforeStep method in hooks");
	}

	@AfterMethod
	public void AfterStep(Scenario scenario) {
		log.info("executed the AfterStep method in hooks");
	}

	@Attachment(value = "Page screenshot", type = "image/png")
	public void embedScreenshot(Scenario scenario) {
		log.info("executing the embedScreenshot method in hooks");
		try {
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
