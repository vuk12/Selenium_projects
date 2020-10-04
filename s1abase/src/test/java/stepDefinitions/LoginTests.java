package stepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objectrepository.LoginPageObjects;
import objectrepository.WelcomePageObjects;
import resources.base;
import resources.SubmitAbstractGlobal;

@SuppressWarnings("unused")
public class LoginTests extends base {
	public WebDriver driver;
	public String abstractID;
	public static Logger log = LogManager.getLogger(LoginTests.class.getName());

	@Before
	public void beforeScenario() throws IOException {
		driver = initializeDriver();
		driver.manage().window().maximize();
		log.info("driver initialized and window maximized");
	}

	@Given("^User is on \"([^\"]*)\" login page$")
	public void user_is_on_something_login_page(String sitename) throws IOException {
		String url = "https://" + sitename + "." + release + "-" + environment + (prop.getProperty("base_url"));
		driver.get(url);
		log.info("user was taken to Url "+url);
	}

	@When("^User logs in with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_logs_in_with_something_and_something(String user, String pass) throws Throwable {
		LoginPageObjects LP = new LoginPageObjects(driver);
		WelcomePageObjects WP = new WelcomePageObjects(driver);
		LP.user().sendKeys(user);
		log.info("user "+user+" was entered");
		LP.pass().sendKeys(pass);
		log.info("password "+pass+" was entered");
		LP.login_button().click();
		log.info("login button was clicked");
		//incomplete account
		
		if (WP.GetOkBtnPresence().size()>0)
		{
			WP.GetOkBtn().click();
			WP.GetNoBtn().click();
			WP.GetPrivacyLnk().click();
			WP.GetPrivacyCb().click();
			WP.GetSaveChangesBtn().click();
		}
	}

	@Then("^Homepage is displayed$")
	public void homepage_is_displayed() throws Throwable {
		LoginPageObjects LP = new LoginPageObjects(driver);
		//SubmitAbstractGlobal sa = new SubmitAbstractGlobal(driver);
		//String cid = sa.AbstractSubmission("Free Submission", "Abstract Title", "Oral", "Oral Only", "GENETICS", "Diagnosis");

		
		Assert.assertTrue(false);
		

		// Assert.assertEquals(LP.welcome_text().getText(), "Welcome to the ScholarOne
		// Abstracts Demonstration Site",
		// "Login Message Not displayed");
	}

}
