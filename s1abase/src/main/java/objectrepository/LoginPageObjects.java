package objectrepository;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class LoginPageObjects {
	public static Logger log = LogManager.getLogger(LoginPageObjects.class.getName());
	WebDriver driver;


	public LoginPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@name='LOGIN_USER_ID']")
	WebElement user;
	

	@FindBy(xpath = "//input[@name='PASSWORD']")
	WebElement pass;

	@FindBy(xpath = "//a[@href='javascript:setNextPage(\"WELCOME\")']")
	WebElement login_button;

	@FindBy(xpath = "//font[contains(text(),'Welcome to the ScholarOne Abstracts Demonstration')]")
	WebElement welcome_text;

	public WebElement user() {
		log.info("user web element returned");
		return user;
	}

	public WebElement pass() {
		log.info("pass web element returned");
		return pass;
	}

	public WebElement login_button() {
		log.info("login_button web element returned");
		return login_button;
	}

	public WebElement welcome_text() {
		log.info("welcome_text web element returned");
		return welcome_text;
	}

}
