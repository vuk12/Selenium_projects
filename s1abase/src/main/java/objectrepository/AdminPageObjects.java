package objectrepository;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class AdminPageObjects {
	public static Logger log = LogManager.getLogger(AdminPageObjects.class.getName());
	WebDriver driver;


	public AdminPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ADMIN MENU !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@FindBy(css = "a.left-side-menu-item[data-page='Data Export'][data-page='Data Export']")
	WebElement dataExportLnk;
	
	@FindBy(css = "[data-page='Client Configuration']")
	WebElement clientConfigLnk;
	
	@FindBy(id = "saveBtn")
	WebElement saveBtn;
	
	@FindBy(xpath = "//table[@class='table table-striped rt cf']//tbody")
	WebElement dataExportTable;
	
	
	public WebElement GetDataExportLnk() {
		log.info("Data Export Lnk web element returned");
		return dataExportLnk;
	}
	public WebElement GetClientConfigLnk() {
		log.info("clientConfigLnk Lnk web element returned");
		return clientConfigLnk;
	}
	public WebElement GetSaveBtn() {
		log.info("saveBtn Lnk web element returned");
		return saveBtn;
	}
	public WebElement GetDataExportTable() {
		log.info("dataExportTable Lnk web element returned");
		return dataExportTable;
	}
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ADMIN MENU CLIENT CONFIGURATION !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@FindBy(css = "[data-page='General Configuration']")
	WebElement generalConfigLnk;
	
	public WebElement GetGeneralConfigLnk() {
		log.info("generalConfigLnk web element returned");
		return generalConfigLnk;
	}
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ADMIN MENU CLIENT CONFIGURATION- GENERAL CONFIG !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@FindBy(xpath = "//textarea[@name='TITLE_CASE_OVERRIDE_LIST']")
	WebElement titleCaseOwerListTb;
	
	public WebElement GetTitleCaseOwerListTb() {
		log.info("titleCaseOwerListTb web element returned");
		return titleCaseOwerListTb;
	}
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ADMIN DATA EXPORT !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@FindBy(xpath = "//a[@class='btn pull-left start-new-export-btn']")
	WebElement startNewExportBtn;
	
	@FindBy(xpath = "//input[@name='EXPORT_LOCATION_NAME']")
	WebElement exportNameTb;
	
	@FindBy(xpath = "//a[@class='btn pull-right btn-primary save-btn']")
	WebElement saveModalBtn;
	
	@FindBy(xpath = "//div[@id='confirmationModal']//a[@class='btn btn-primary pull-right button-yes']")
	WebElement yesModalBtn;
	
	@FindBy(xpath = "//div[@id='alertModal']//a[@class='btn btn-primary pull-center button-ok'][contains(text(),'Ok')]")
	WebElement okModalBtn;
	
	
	public WebElement GetStartNewExportBtn() {
		log.info("Start New Export button web element returned");
		return startNewExportBtn;
	}
	
	public WebElement GetExportNameTb() {
		log.info("exportNameTbt button web element returned");
		return exportNameTb;
	}
	
	public WebElement GetSaveModalBtn() {
		log.info("saveModalBtn button web element returned");
		return saveModalBtn;
	}
 
	public WebElement GetYesModalBtn() {
		log.info("yesModalBtn button web element returned");
		return yesModalBtn;
	}
	
	public WebElement GetOKModalBtn() {
		log.info("okModalBtn button web element returned");
		return okModalBtn;
	}
	

}
