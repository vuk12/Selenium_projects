package objectrepository;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class WelcomePageObjects {
	public static Logger log = LogManager.getLogger(WelcomePageObjects.class.getName());
	WebDriver driver;


	public WelcomePageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! CENTERS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@FindBy(css = ".nav-collapse.toplvlnav li a[href*='submission']")
	WebElement submisionCenter;
	
	@FindBy(xpath = "//i[@class='fa fa-check-square-o']")
	WebElement adminCenter;
	
	
	@FindBy(xpath = "//i[@class='fa fa-user']")
	WebElement supportCenter;
	
	@FindBy(xpath = "//i[@class='fa fa-cog']")
	WebElement developerCenter;
	
	
	public WebElement GetSubmissionCenter() {
		log.info("submission center returned");
		return submisionCenter;
	}
	
	public WebElement GetAdminCenter() {
		log.info("Admin center returned");
		return adminCenter;
	}
	
	public WebElement GetConfigCenter() {
		
		log.info("Config center returned");
		int size = driver.findElements(By.cssSelector(".nav-collapse.toplvlnav li a[href*='com/config?']")).size();
		return driver.findElements(By.cssSelector(".nav-collapse.toplvlnav li a[href*='com/config?']")).get(size-1);
	}
	
	public WebElement GetDeveloperCenter() {
		log.info("Developer center returned");
		return developerCenter;
	}
	
	public WebElement GetSupportCenter() {
		
		int size = driver.findElements(By.cssSelector(".nav-collapse.toplvlnav li a[href*='com/config?']")).size();
		log.info("Support center returned");
		//only if user have support config role
		return driver.findElements(By.cssSelector(".nav-collapse.toplvlnav li a[href*='com/config?']")).get(0);

	}
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! HEADER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@FindBy(xpath = "//a[@class='navigation-link logout-lnk']")
	WebElement logOutLnk;
	
	@FindBy(xpath = "//span[contains(text(),'Yes, Leave this as draft')]")
	WebElement leaveAsDraft;
	
	public WebElement GetLogOutLnk() {
		log.info("logOutLnk for incomplete returned");
		return logOutLnk;
	}
	public WebElement GetLeaveAsDraftBtn() {
		log.info("leaveAsDraft for incomplete returned");
		return leaveAsDraft;
	}
	
	public List<WebElement> GetLeaveAsDraftPresence() {
		log.info("leaveAsDraft for incomplete returned");
		return driver.findElements(By.xpath("//span[contains(text(),'Yes, Leave this as draft')]"));
	}
	
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! INCOMPLETE ACCOUNT !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@FindBy(xpath = "//div[@id='validateAccountModalWelcome']//a[@class='btn btn-primary pull-center ok-button'][contains(text(),'Ok')]")
	WebElement okBtn;
	
	public WebElement GetOkBtn() {
		log.info("Ok button for incomplete returned");
		return okBtn;
	}
	
	public List<WebElement> GetOkBtnPresence() {
		log.info("Ok button for incomplete returned");
		return driver.findElements(By.xpath("//div[@id='validateAccountModalWelcome']//a[@class='btn btn-primary pull-center ok-button'][contains(text(),'Ok')]"));
	}
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! MODIFY ACCOUNT - MENU AND COMMON ELEMENTS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@FindBy(linkText = "Privacy")
	WebElement privacyLnk;
	
	
	@FindBy(xpath = "//a[@class='btn btn-primary pull-right save-and-continue-btn']")
	WebElement saveAndNext;
	
	public WebElement GetPrivacyLnk() {
		log.info("Privacy link for account center returned");
		return privacyLnk;
	}
	
	public WebElement GetSaveAndNext() {
		log.info("Save and next button on modify account returned returned");
		return saveAndNext;
	}
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! MODIFY ACCOUNT - DISCOLSURES !!!!!!!!!!!!!!!!!!!!!!!!!!
	
	public WebElement GetNoBtn() {
		log.info("No radio button returned");
		return driver.findElements(By.cssSelector("[type='radio']")).get(0);
	}
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! MODIFY ACCOUNT - PRIVACY !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@FindBy(id = "IS_ACK_PRIVACY_POLICY")
	WebElement privacyCb;
	
	@FindBy(xpath = "//a[@class='btn btn-primary pull-right save-and-continue-btn']")
	WebElement saveChanges;
	
	
	
	public WebElement GetPrivacyCb() {
		log.info("Privacy checkbox web element returned");
		return privacyCb;
	}
	public WebElement GetSaveChangesBtn() {
		log.info("Save Changes button web element returned");
		return privacyCb;
	}

	

}
