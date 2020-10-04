package objectrepository;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class DeveloperPageObject {
	public static Logger log = LogManager.getLogger(DeveloperPageObject.class.getName());
	WebDriver driver;


	public DeveloperPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!! DEVELOPER MENU !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	
	@FindBy(xpath = "//td[@class='tablelines']//td[@class='tablelines']//tbody")
	WebElement timerTable;
	
	
	public WebElement GetTimerTable() {
		log.info("timerTable element returned");
		return timerTable;
	}
	
	public WebElement GetTimerTaskLnk() {
		
		log.info("timerTaskLnk element returned");
		return driver.findElements(By.cssSelector(".menu-item-link td[valign='middle'] div")).get(2);
	}


}
