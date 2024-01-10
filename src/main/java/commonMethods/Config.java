package commonMethods;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import atu.testng.reports.ATUReports;
import io.github.bonigarcia.wdm.WebDriverManager;
public class Config  extends Keywords {
	public  WebDriver driver;
	
	ATUReports atuRep=new ATUReports();
	
	public  WebDriver getDriver() throws MalformedURLException {
		return this.driver;
		
	}
public  void setDriver(WebDriver paramDriver) throws MalformedURLException {
		this.driver=paramDriver;
	} 
	
public WebDriver getWebDriver(String browserName) throws MalformedURLException {
	if (browserName.equals("Chrome")) {
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Comdex/driver/chromedriver.exe");
	
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		options.addArguments("disable-notifications");
		options.addArguments("--disable-extensions"); // to disable extension
	    options.addArguments("--disable-notifications"); // to disable notification
	    options.addArguments("--disable-application-cache"); // to disable cache
	    options.addArguments("--safebrowsing-disable-download-protection");
	    options.addArguments("ignore-certificate-errors");
	    options.addArguments("--disable-popup-blocking");
	    options.addArguments("--disable-gpu");
		LoggingPreferences logPrefs = new LoggingPreferences();
	    logPrefs.enable(LogType.BROWSER, Level.ALL);
	    // options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
	    options.setCapability("goog:loggingPrefs", logPrefs);
		//options.addArguments("--log-level=1");
	    options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
		WebDriver driver = new ChromeDriver(options);
		System.out.println("Chrome Browser launched...");	
		setDriver(driver);
		driver.manage().window().maximize();
 
		/*
		 * Map<String, Object> prefs = new HashMap<String, Object>();
		 * prefs.put("profile.default_content_setting_values.notifications", 1);
		 * prefs.put("download.default_directory", System.getProperty("user.dir") +
		 * "\\DownloadedFiles");
		 */
		
	} else if (browserName.equalsIgnoreCase("Firefox")) {
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\driver\\geckodriver.exe");
		
		 FirefoxOptions option = new FirefoxOptions();
	        option.addPreference("dom.webnotifications.enabled", false);
	        //option.addPreference("app.update.enabled", false);
	        //option.addPreference("geo.enabled", false);
	        WebDriver driver = new FirefoxDriver(option);
	        System.out.println("Firefox Browser launched...");	
		//driver = new FirefoxDriver();
		setDriver(driver);
		driver.manage().window().maximize();

	} else if (browserName.equalsIgnoreCase("IE")) {
		System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\driver\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		setDriver(driver);
		driver.manage().window().maximize();
		
	}
	return driver;
}	


}
