package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;


public class base {
	public static WebDriver driver;
	public Properties prop; 
	public String environment;
	public String release;
	public static String downloadPath;
	
	@SuppressWarnings("unused")
	public WebDriver initializeDriver() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);

		// mvn test -Dbrowser=chrome
		// String browserName = System.getProperty("browser");
		String browserName = prop.getProperty("browser");
		downloadPath=System.getProperty("user.dir")+"\\tempDownloadedFiles";
		
		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if (browserName.contains("headless")) {
				options.addArguments("--headless");
			}
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\resources\\chromedriver.exe");
			
			//Set Download path for Charome
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadPath);
			options.setExperimentalOption("prefs", chromePrefs);

			driver=new ChromeDriver(options);
		}

		else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\resources\\geckodriver.exe");
			//driver = new FirefoxDriver();
			
			  //Create FireFox Profile object
			FirefoxOptions options = new FirefoxOptions();
			options.setAcceptInsecureCerts(true);
			
			options.addPreference("browser.download.folderList", 2);
			options.addPreference("browser.helperApps.alwaysAsk.force", false);
			options.addPreference("browser.download.dir", downloadPath); 
			options.addPreference("browser.download.defaultFolder",downloadPath); 
			options.addPreference("browser.download.manager.showWhenStarting", false);
			options.addPreference("browser.helperApps.neverAsk.saveToDisk","multipart/x-zip,application/zip,application/x-zip-compressed,application/x-compressed,application/msword,application/csv,text/csv,image/png ,image/jpeg, application/pdf, text/html,text/plain,  application/excel, application/vnd.ms-excel, application/x-excel, application/x-msexcel, application/octet-stream");
			 
			driver = new FirefoxDriver(options);
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	protected void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ScroolDown(WebDriver driver, int pixelVerticalScrool){
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,"+pixelVerticalScrool+")");
	}
	


	public WebDriver getDriver() {
		return driver;
	}
	
	public base() {
		environment = System.getProperty("env");
		release = System.getProperty("release");
		//environment = "beta1";
		//release = "s1a4173";
	}
	
	public String GetDownloadPath() {
		return downloadPath;
	}
	
	public static void Unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while(ze != null){
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

	

}
