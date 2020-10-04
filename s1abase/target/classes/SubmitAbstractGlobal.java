package resources;


import java.io.IOException;
import java.util.Set;
import java.util.Iterator;

//import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import objectrepository.LoginPageObjects;
import objectrepository.SubmisionPageObject;
import objectrepository.WelcomePageObjects;
import resources.base;

@SuppressWarnings("unused")
public class SubmitAbstractGlobal extends base {
	public WebDriver driver;
	
	
	public SubmitAbstractGlobal(WebDriver driver)
	{	
		this.driver = driver;
	
	}
	public static Logger log = LogManager.getLogger(SubmitAbstractGlobal.class.getName());


	
	public String AbstractSubmission(String submissionName,String title,String presentation, String subPresentation,String category,String subCategory) throws IOException {
		
		SubmisionPageObject SP = new SubmisionPageObject(driver);
		
		Select_Submission_Type(submissionName);
		
		FillAbstractTitlePage(title);
		SP.GetSaveAndContinueBtn().click();
		
		FillAbstractPropertiesPage(presentation, subPresentation, category, subCategory);
		SP.GetSaveAndContinueBtn().click();
		
		FillAuthors();
		SP.GetSaveAndContinueBtn().click();
		
		FillDisclosures();
		driver.findElement(By.id("save_continue_btn")).click();
		
		return ClickSubmit();
	}
	
	public void Select_Submission_Type(String submissionName) throws IOException {
		
		//driver = driverGlobal;
		
		WelcomePageObjects WP = new WelcomePageObjects(driver);
		WP.GetSubmissionCenter().click();
		SubmisionPageObject SP = new SubmisionPageObject(driver);
		SP.GetCreateNewSubmissionLnk().click();
		
		WebElement submissionTable = SP.GetSubmisionTypeTable();
		int rowCount = submissionTable.findElements(By.xpath("//tr")).size();
		//System.out.println(rowCount);
		for(int i =1;i<=rowCount;i++)
		{
			String roleName = submissionTable.findElement(By.xpath("//tr["+i+"]/td[2]/strong")).getText().trim();
			log.info("This is a role name for submission:"+roleName);
			if (roleName.contentEquals(submissionName))
			{
				submissionTable.findElement(By.xpath("//tr["+i+"]/td[1]/label/input")).click();
				break;						
			}
		}
		
		//sleep(3000);
		WebDriverWait w1 = new WebDriverWait(driver, 5);
		w1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='confirm-submit-btn btn btn-primary pull-right']")));
		SP.GetContinueBtn().click();
		
		//sleep(3000);
		w1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-primary pull-right ok-button']")));
		SP.GetContinueWithThisTypeBtn().click();
	}


	public void FillAbstractTitlePage(String title) throws IOException {
		
		//driver = driverGlobal;
		SubmisionPageObject SP = new SubmisionPageObject(driver);
		
		sleep(3000);
		//WebDriverWait w1 = new WebDriverWait(driver, 5);
		//w1.until(ExpectedConditions.visibilityOfElementLocated(By.id("TITLE")));
		
		driver.switchTo().frame(0);
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("/html"))).click().sendKeys(title).build().perform(); 
		driver.switchTo().defaultContent();
		

		ScroolDown(driver,200);
        driver.switchTo().frame(1);
        a.moveToElement(driver.findElement(By.xpath("/html"))).click().sendKeys("Abs Body").build().perform();
        driver.switchTo().defaultContent();
        
        //SP.GetSaveAndContinueBtn().click();
	}

	
	public void FillAbstractPropertiesPage(String presentation, String subPresentation,String category,String subCategory) throws IOException {
		//driver = driverGlobal;
		SubmisionPageObject SP = new SubmisionPageObject(driver);
		
		Select selPresentation= new Select(SP.GetPresentationTypeSel());
		selPresentation.selectByVisibleText(presentation);
		
		if(SP.GetSubPresentationTypeSel().isDisplayed())
		{
			Select selSubPresentation= new Select(SP.GetSubPresentationTypeSel());
			selSubPresentation.selectByVisibleText(subPresentation);
		}
		
		Select selCategory= new Select(SP.GetCategoryTypeSel());
		selCategory.selectByVisibleText(category);
		
		if(SP.GetSubCategoryTypeSel().isDisplayed())
		{
			Select selSubCategory= new Select(SP.GetSubCategoryTypeSel());
			selSubCategory.selectByVisibleText(subCategory);
		}
		
		Select selKeyword= new Select(SP.GetKeywordSel());
		selKeyword.selectByVisibleText("Bone");
		
		//SP.GetSaveAndContinueBtn().click();
	}

	public void FillAuthors() throws IOException{
		
		SubmisionPageObject SP = new SubmisionPageObject(driver);
		
		Select selAuthor= new Select(SP.GetAuthorInstitutionSel());
	    selAuthor.selectByVisibleText("QTP Department, QTP Institution");
	    
	    // Click Disclosure link
	    SP.GetAuthorDisclosureLnk().click();
	    Set<String> ids= driver.getWindowHandles();
		Iterator<String> it=ids.iterator();
		String parentId2 = it.next();
		String firstChildId = it.next();
		driver.switchTo().window(firstChildId);
		//
		SP.GetDisclosureAnswerRB(0).click();
		SP.GetSaveDisclosureBtn().click();
		driver.switchTo().window(parentId2);
		
		//SP.GetSaveAndContinueBtn().click();
	}

	public void FillDisclosures() throws IOException{
		
		SubmisionPageObject SP = new SubmisionPageObject(driver);
		
		for(int i=1;i<SP.GetAllRadioBtn().size();i=i+2)
		{
		    sleep(200);
		    SP.GetAllRadioBtn().get(i).click();
  		} 
		
		ScroolDown(driver, 1000);
		SP.GetDisclosureCb().click();
		
		WebDriverWait w = new WebDriverWait(driver, 5);
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".span4 .input-block-level[type='text']")));
		SP.GetTextBox().get(0).sendKeys("xxx");
		SP.GetTextBox().get(1).sendKeys("xxx");
		
		//SP.GetSaveAndContinueBtn().click(); ???
		//driver.findElement(By.id("save_continue_btn")).click(); //Report this inconsistency as technical debt
		
	}
	
	public String ClickSubmit() throws IOException{
		
		SubmisionPageObject SP = new SubmisionPageObject(driver);
		
		WebDriverWait w1 = new WebDriverWait(driver, 5);
		w1.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit_btn")));
		
		String CID = ReturnCID(SP.GetCIDelement());
		SP.GetSubmitBtn().click();
		
		return CID;
	}
	
	public String ReturnCID(WebElement cidElem) {
		
		String CID = cidElem.getText().trim();
		int stringSize=CID.length();
		CID = CID.substring(3, stringSize);
	 
		return CID;
	}

}
