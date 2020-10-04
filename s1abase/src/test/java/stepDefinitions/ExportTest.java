package stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objectrepository.AdminPageObjects;
import objectrepository.ConfigPageObjects;
import objectrepository.LoginPageObjects;
import objectrepository.WelcomePageObjects;
import resources.base;
import resources.CommonGlobalMethods;
import resources.SubmitAbstractGlobal;

@SuppressWarnings("unused")
public class ExportTest extends base {
	
	public WebDriver driver;
	public String abstractID;
	public static Logger log = LogManager.getLogger(LoginTests.class.getName());

	public ExportTest(){
		 driver = getDriver();
	}

	
	/*@Before
	public void beforeScenario() throws IOException {
		driver = initializeDriver();
		driver.manage().window().maximize();
		log.info("driver initialized and window maximized");
	}*/


    @Then("^Abstracts titles are exported with title case rules$")
    public void abstracts_titles_are_exported_with_title_case_rules() throws Throwable {
    	
    	WelcomePageObjects WP = new WelcomePageObjects(driver);
    	AdminPageObjects AP = new AdminPageObjects(driver);
    	
    	WP.GetAdminCenter().click();
    	sleep(500);
    	AP.GetDataExportLnk().click();
    	
		WebElement submissionTable = AP.GetDataExportTable();
		//first link for download files in Data Export table
		driver.findElement(By.xpath("//tr[1]//td[6]//a[1]")).click();;
		sleep(2000);
		
		File folderContent = new File(GetDownloadPath());
		File[] listOfFiles = folderContent.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	Unzip((GetDownloadPath()+"//"+file.getName()), GetDownloadPath());
		    	break;
		    }
		}
		
		String content="";
		File UnZIPfolderContent = new File(GetDownloadPath());
		File[] listOfUnZipFiles = UnZIPfolderContent.listFiles();
		for (File file : listOfUnZipFiles) {
		    if (file.isFile()) {
		    	if(file.getName().contains(".xml"))
		    	{
		    		content = Files.readString(Paths.get((GetDownloadPath()+"//"+file.getName())));
		    		break;
			    }
		        
		    }
		}

		sleep(200);
		String s1= "This is WIerd Title: Self-Report Self-Report SELF-REPORT a an an the the and and but but or or nor May nor";

		Assert.assertTrue(content.contains(s1));
		Assert.assertTrue(content.contains("As as at at by by for for in in of of on on per per to to via via vs. vs. vs vs v. v. v v"));
		Assert.assertTrue(content.contains("Asteroid WireD Permanent TitlE en en if if \"nor\" 'nor' Inter"));
		Assert.assertTrue(content.contains("Words in \"Title Case Override List\" which are exceptions for classic Rules (e.g., and, as, but, for, if, nor, or, so, yet )Articles ( a, an, the )Short Prepositions (e.g., as, at, by, for, in, of, off, on, per, to, up, via"));
		Assert.assertTrue(content.contains("Small add to s1a-71 (for Brackets) and Also \"Other Types\" of Delimiters;Like the e.g. {This Bracket Also}"));
		
		for (File file : listOfUnZipFiles) {
		    if (file.isFile()) {
		    	if (!file.isDirectory()) 
		            file.delete();  
		    }
		}
    }

    @And("^User submit few Abstracts$")
    public void user_submit_few_abstracts() throws Throwable {
		WelcomePageObjects WP = new WelcomePageObjects(driver);
		ConfigPageObjects CP = new ConfigPageObjects(driver);
		AdminPageObjects AP = new AdminPageObjects(driver);
		//Set Title Case option to Yes
		WP.GetConfigCenter().click();	
		CP.GetTitleBodyLnk().click();
		CP.GetTitleCaseRbt().get(0).click();
		CP.GetSaveBtn().click();
		
		//Set Admin Title case Override WebEdit
		WP.GetAdminCenter().click();
		sleep(1000);
		AP.GetClientConfigLnk().click();
		
		sleep(1000);
		AP.GetGeneralConfigLnk().click();
		
		AP.GetTitleCaseOwerListTb().clear();
		AP.GetTitleCaseOwerListTb().sendKeys("words in \"Title Case Override List\" which are exceptions for classic rules\"");
		AP.GetSaveBtn().click();
		Assert.assertEquals(AP.GetTitleCaseOwerListTb().getText(), "words in \"Title Case Override List\" which are exceptions for classic rules\"", "Strings are not equqla");
    	
		//Submit abstracts with appropriate titles
		SubmitAbstractGlobal sa = new SubmitAbstractGlobal(driver);
		String abstractTitle1 = "this is wIerd title: Self-report self-Report SELF-REPORT a an AN the The and AND but BUT or OR NOR May nor";
		String abstractTitle2 = "as AS at AT by BY for For in IN of OF on ON per Per to TO via Via vs. VS. vs Vs v. V. V v";
		String abstractTitle3 = "asteroid wireD permanent titlE EN en if If \"NOR\" 'NOR' inter\r\n" + 
				"";
		String abstractTitle4 ="words in \"Title Case Override List\" which are exceptions for classic rules (e.g., and, as, but, for, if, nor, or, so, yet )articles ( a, an, the )short prepositions (e.g., as, at, by, for, in, of, off, on, per, to, up, via )";
		String abstractTitle5 ="small add to s1a-71 (for brackets) and also \"other types\" of delimiters;like the e.g. {this bracket also}";
		
		sa.AbstractSubmission("Free Submission", abstractTitle1, "Oral", "Oral Only", "GENETICS", "Diagnosis");
		sa.AbstractSubmission("Free Submission", abstractTitle2, "Oral", "Oral Only", "GENETICS", "Diagnosis");
		sa.AbstractSubmission("Free Submission", abstractTitle3, "Oral", "Oral Only", "GENETICS", "Diagnosis");
		sa.AbstractSubmission("Free Submission", abstractTitle4, "Oral", "Oral Only", "GENETICS", "Diagnosis");
		sa.AbstractSubmission("Free Submission", abstractTitle5, "Oral", "Oral Only", "GENETICS", "Diagnosis");
    }

    @And("^User performs Data Export$")
    public void user_performs_data_export() throws Throwable {
    	
    	WelcomePageObjects WP = new WelcomePageObjects(driver);
    	AdminPageObjects AP = new AdminPageObjects(driver);
    	CommonGlobalMethods CGM = new CommonGlobalMethods(driver);
    	
    	WP.GetAdminCenter().click();
    	sleep(1000);
    	AP.GetDataExportLnk().click();
    	
    	//start new Export
    	AP.GetStartNewExportBtn().click();
    	sleep(500);
    	AP.GetExportNameTb().sendKeys("CHECK_TITLE_CASE_ABSTRACTS");
    	AP.GetSaveModalBtn().click();
    	sleep(500);
    	AP.GetYesModalBtn().click();
       	
    	WebDriverWait w1 = new WebDriverWait(driver, 5);
		w1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='alertModal']//a[@class='btn btn-primary pull-center button-ok'][contains(text(),'Ok')]")));
    	AP.GetOKModalBtn().click();
    	CGM.TrigerTimer("XMLDataExportTimerTask");
    	
    	
    }

}