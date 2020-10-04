package cucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/java/features" }, glue = { "stepDefinitions" }, plugin = {
		"pretty" }, monochrome = false)
public class TestRunner extends AbstractTestNGCucumberTests {

}
