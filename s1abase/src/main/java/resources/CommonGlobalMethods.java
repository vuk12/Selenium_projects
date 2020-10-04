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

import objectrepository.DeveloperPageObject;
import objectrepository.LoginPageObjects;
import objectrepository.SubmisionPageObject;
import objectrepository.WelcomePageObjects;
import resources.base;

@SuppressWarnings("unused")
public class CommonGlobalMethods extends base {
	public WebDriver driver;
	
	
	public CommonGlobalMethods(WebDriver driver)
	{	
		this.driver = driver;
	
	}
	public static Logger log = LogManager.getLogger(CommonGlobalMethods.class.getName());


	
	public void LogInGlobal(String user, String password) throws IOException {
		LoginPageObjects LP = new LoginPageObjects(driver);
		WelcomePageObjects WP = new WelcomePageObjects(driver);
		
		WP.GetLogOutLnk().click();
		if(WP.GetLeaveAsDraftPresence().size()>0)
		{
			WP.GetLeaveAsDraftBtn().click();
		}	
		LP.user().sendKeys(user);
		log.info("user "+user+" was entered");
		LP.pass().sendKeys(password);
		log.info("password "+password+" was entered");
		LP.login_button().click();
		log.info("login button was clicked");
		
	}
	
	public void TrigerTimer(String timerName) throws IOException{
		
		WelcomePageObjects WP = new WelcomePageObjects(driver);
		DeveloperPageObject DP = new DeveloperPageObject(driver);
		
		LogInGlobal("qtp@config.net", "password4qa2");
		WP.GetDeveloperCenter().click();
		sleep(1000);
		DP.GetTimerTaskLnk().click();
		
		WebElement timerTable = DP.GetTimerTable();
		int rowCount = timerTable.findElements(By.xpath("//tr")).size();

		for(int i =2;i<=rowCount;i++)
		{

			String timer = timerTable.findElement(By.xpath("//tr["+i+"]/td[1]/p")).getText().trim();
			log.info("This is a role name for submission:"+timer);
			if (timer.contains(timerName))
			{
				ScroolDown(driver, 500);
				sleep(200);
				WebElement cellToClick = timerTable.findElement(By.xpath("//tr["+i+"]/td[1]/p"));
				cellToClick.findElement(By.cssSelector("a[href*='javascript']")).click();
				break;	
				
			}
		}
		driver.switchTo().alert().accept();  
		sleep(60000);//Wait some time for trigger to finish execution
	}
	
}
