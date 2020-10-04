package objectrepository;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class SubmisionPageObject {
	public static Logger log = LogManager.getLogger(SubmisionPageObject.class.getName());
	WebDriver driver;


	public SubmisionPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!CREATE NEW SUBMISSION PAGE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@FindBy(xpath = "//a[contains(@class,'left-side-menu-item')]")
	WebElement createNewSubmissionLnk;
	
	@FindBy(xpath = "//table[@id='subtype']//tbody")
	WebElement submisionTypeTable;
	
	
	@FindBy(xpath = "//a[@class='confirm-submit-btn btn btn-primary pull-right']")
	WebElement continueBtn;

	@FindBy(xpath = "//button[@class='btn btn-primary pull-right ok-button']")
	WebElement continueWithThisTypeBtn;

	@FindBy(id = "saveAndContinueBtn")
	WebElement saveAndContinueBtn;
	
	   //////METHODS////
	public WebElement GetCreateNewSubmissionLnk() {
		log.info("submission linnk web element returned");
		return createNewSubmissionLnk;
	}
	
	public WebElement GetSubmisionTypeTable() {
		log.info("submisionTypeTable web element returned");
		return submisionTypeTable;
	}
	
	public WebElement GetContinueBtn() {
		log.info("continue button web element returned");
		return continueBtn;
	}
	
	public WebElement GetContinueWithThisTypeBtn() {
		log.info("welcome_text web element returned");
		return continueWithThisTypeBtn;
	}
	
	public WebElement GetSaveAndContinueBtn() {
		log.info("welcome_text web element returned");
		return saveAndContinueBtn;
	}
	
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!STEP 2: TITLE & BODY PAGE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!STEP 3: PROPERTIES PAGE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@FindBy(id = "type_combobox")
	WebElement presentationTypeSel;
	
	@FindBy(id = "sub_type_combobox")
	WebElement subPresentationTypeSel;
	
	@FindBy(id = "category_combobox1")
	WebElement categoryTypeSel;
	
	@FindBy(id = "sub_category_combobox1")
	WebElement subCategoryTypeSel;
	
	@FindBy(id = "keyword_combobox1")
	WebElement keyword;
	
	
	//METHODS
	public WebElement GetPresentationTypeSel() {
		log.info("presentation type web element returned");
		return presentationTypeSel;
	}
	
	public WebElement GetSubPresentationTypeSel() {
		log.info("Subpresentation type web element returned");
		return subPresentationTypeSel;
	}
	public WebElement GetCategoryTypeSel() {
		log.info("Category type web element returned");
		return categoryTypeSel;
	}
	
	public WebElement GetSubCategoryTypeSel() {
		log.info("Subcategory type web element returned");
		return subCategoryTypeSel;
	}
	
	public WebElement GetKeywordSel() {
		log.info("Keyword web element returned");
		return keyword;
	}
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!STEP 4: AUTHORS PAGE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@FindBy(css = "select[name*='AUTHOR_INSTITUTION']")
	WebElement authorInstitutionSel;
	
	@FindBy(xpath  = "//strong[contains(text(),'Click to review and acknowledge Disclosure')]")
	WebElement authorDisclosureLnk;
	
	//Disclosure pop up
	@FindBy(xpath  = "//a[@id='saveAndContinueBtn']")
	WebElement saveDisclosureBtn; 
	
	
	//METHODS
	public WebElement GetAuthorInstitutionSel() {
		log.info("Author Institution web element returned");
		return authorInstitutionSel;
	}
	public WebElement GetAuthorDisclosureLnk() {
		log.info("Author Disclosure web element returned");
		return authorDisclosureLnk;
	}
	   //Disclosure Pop up
	public WebElement GetDisclosureAnswerRB(int index) {
		log.info("Disclosure Answer web element returned");
		return driver.findElements(By.cssSelector(".accordion .disclaimer-item")).get(index);
	}
	
	public WebElement GetSaveDisclosureBtn() {
		log.info("Save and continue button in disclosure popUp web element returned");
		return saveDisclosureBtn;
	}
	
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!STEP 5: DISCLOSURE PAGE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@FindBy(css = "[type='checkbox']")
	WebElement disclosureCb;
	
	public WebElement GetDisclosureCb() {
		log.info("Check box for disclosure web element returned");
		return disclosureCb;
	}
	
	public List <WebElement> GetAllRadioBtn() {
		
		log.info("All radio buttons for disclosure page list of web element returned");
		return driver.findElements(By.cssSelector("[type='radio']"));
	}
	
	public List <WebElement> GetTextBox() {
		
		log.info("All text box for disclosure page list of web element returned");
		return driver.findElements(By.cssSelector(".span4 .input-block-level[type='text']"));
	}
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!STEP 6: REVIEW & SUBMIT PAGE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@FindBy(xpath = "//p[@class='pull-right']")
	WebElement CID;
	
	@FindBy(id = "submit_btn")
	WebElement submitBtn;
	
	public WebElement GetCIDelement() {
		log.info("CID web element returned");
		return CID;
	}
	
	public WebElement GetSubmitBtn() {
		log.info("Submit web element returned");
		return submitBtn;
	}
	
}
