package objectrepository;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ConfigPageObjects {
	public static Logger log = LogManager.getLogger(ConfigPageObjects.class.getName());
	WebDriver driver;


	public ConfigPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! CONFIG MENU !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@FindBy(xpath = "//div[contains(text(),'Title & Body')]")
	WebElement titleBodyLnk;
	
	@FindBy(css = "//div[contains(text(),'Admin Dashboard')]")
	WebElement adminDashboardLnk;
	
	public WebElement GetTitleBodyLnk() {
		log.info("Title & Body Lnk web element returned");
		return titleBodyLnk;
	}
	public WebElement GetAdminDashboardLnk() {
		log.info("Admin Dashboard Lnk web element returned");
		return adminDashboardLnk;
	}
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! CONFIG COMMON OBJECTS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
		@FindBy(xpath = "//select[@name='XIK_SELECTED_ROLE_ID']")
		WebElement roleNameToConfigureSel;
		
		
		public WebElement GetRoleNameToConfigureSel() {
			log.info("Role Name to configure select web element returned");
			return roleNameToConfigureSel;
		}
		
		public WebElement GetSaveBtn() {
			log.info("Save btn web element returned");
			
			int size = driver.findElements(By.cssSelector("div.btn.btn-primary")).size();
			return driver.findElements(By.cssSelector("div.btn.btn-primary")).get(size-1);
		}
	 
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! TITLE& BODY PAGE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			

		public List<WebElement> GetTitleCaseRbt() {
			//0 is for Yes and 1 is for No
			log.info("Title Case buttons web element returned");
			return driver.findElements(By.cssSelector("[type='radio'][name='CONFIG_PARAM_VALUE0']"));
		}
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ADMIN DASHBOARD !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@FindBy(xpath = "//a[@class='btn pull-left start-new-export-btn']")
	WebElement startNewExportBtn;
	

	public WebElement GetStartNewExportBtn() {
		log.info("Start New Export button web element returned");
		return startNewExportBtn;
	}
 
	
	

}
