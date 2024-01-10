package commonMethods;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import atu.testng.reports.exceptions.ATUReporterException;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

import scripts.Solvecrane;
import scripts.shipping;
import scripts.Sonata3D;
import scripts.Schedule_failure;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })

public class Testcases extends Config {
	{
		System.setProperty("atu.reporter.config", System.getProperty("user.dir") + "/atu.properties");
	}
	public String appURL;
	public String appURL1;
	public String appURL2;
	public String PROURL;
	public String PROURL12;
	public String mailinatorurl;
	public String proxy;
	public String usernameValue;
	public String passwordValue;
	public String project_Name;
	public String version_Name;
	public String environment;
	public String browser;
	public WebDriver driver;
	public String search;

	String Userflowurl = null;

	File f = new File(report_folder_create + "\\reports");
	Solvecrane fq= new Solvecrane();
	shipping sc= new shipping();
	Sonata3D threed= new Sonata3D();
	Schedule_failure fa= new Schedule_failure();

	
	@BeforeMethod
	public void getDataFromConfig() throws ATUReporterException, IOException, InterruptedException {

		appURL = Utils.getDataFromTestConfig("URL");
		appURL1 = Utils.getDataFromTestConfig("URL1");
		appURL2 = Utils.getDataFromTestConfig("URL2");
		PROURL = Utils.getDataFromTestConfig("PROURL");
		PROURL12 = Utils.getDataFromTestConfig("PRURL1236");
		browser = Utils.getDataFromTestConfig("AppBrowser");
		project_Name = Utils.getDataFromTestConfig("Project_Name");
		version_Name = Utils.getDataFromTestConfig("Version_Name");
		driver = getWebDriver(browser);

	}



	
	@Test
	public void Solvecrane() {

		fq.sonata(driver);
		
		driver.quit();

	}

	
	@Test
	public void shipping() {

		sc.sonatacanvas(driver);
		
		driver.quit();
	}
	
	@Test
	public void Sonata3D() {

		threed.Baplie_viewer(driver);
		
		driver.quit();

	}
	
	@Test
	public void Schedule_failure() {

		fa.sonata_fail(driver);
		
		driver.quit();

	}
	

	
	

}
