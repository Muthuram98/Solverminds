package scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonMethods.TestNgXml;
import commonMethods.Utils;
import commonMethods.Keywords;


public class Solvecrane extends Keywords {
	
	String URL = TestNgXml.getdatafromExecution().get("Solvecrane");
	String Username = Utils.getDataFromTestData("Solverminds", "Username");
	String Password = Utils.getDataFromTestData("Solverminds", "Password");
	String voyage_from = Utils.getDataFromTestData("Solverminds", "Voyage_from");
	String voyage_to = Utils.getDataFromTestData("Solverminds", "Voyage_to");


	public void sonata (WebDriver driver) {
	
		navigateUrl(driver, URL);
		WebDriverWait wait = new WebDriverWait (driver, 10);
		waitForElement(driver, username);
		
		sendKeys(driver, username, Username);
		
		waitForElement(driver, password);
		
		sendKeys(driver, password, Password);
		waitForElement(driver, login);
		click(driver,login);
		
		waitForElement(driver, selectvessel);
		click(driver,selectvessel);
		
	    WebElement selectedvessel= driver.findElement(By.xpath("//*[@id='demo-simple-select-outlined-option-0']"));
		
		String selectvessel=selectedvessel.getText();
		String select_vessel = selectvessel.substring(6,14);
		
		System.out.println("selected vessel name is :" + select_vessel);
		
		waitForElement(driver, clickvessel);
		click(driver,clickvessel);	
				
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='sel_vessel_name']")));
		WebElement vessel= driver.findElement(By.xpath("//*[@class='sel_vessel_name']"));
		String vesselname=vessel.getText();
		System.out.println("Vessel name is :" + vesselname);
		
		if(vesselname.equals(selectvessel)) {
			System.out.println("vessel showing correctly");
		}else {
			System.out.println("vessel selected incorrectly");
		}
		
		waitForElement(driver, schedule);
		click(driver,schedule);
		
		waitForElement(driver, schedule);
				
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='image_size '])[1]")));	
			
		driver.findElement(By.xpath("(//*[@class='image_size '])[1]")).click();
		
		wait.until(ExpectedConditions.alertIsPresent());
		Alert1(driver);
		
		waitForElement(driver, searchservice);
		click(driver,searchservice);
			
		waitForElement(driver, toronto);
		   	    
		doubleClick(driver,toronto);
		
		waitForElement(driver, voyagefrom);
	    sendKeys(driver,voyagefrom,voyage_from);
		
		waitForElement(driver, voyageto);
	    sendKeys(driver,voyageto,voyage_to);
	  
	    waitForElement(driver, boundfrom);
		click(driver,boundfrom);
		
	    waitForElement(driver, selectboundS);
		click(driver,selectboundS);
		
	    waitForElement(driver, boundto);
		click(driver,boundto);
		
	    waitForElement(driver, selectboundW);
		click(driver,selectboundW);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Show']")));
		driver.findElement(By.xpath("//span[text()='Show']")).click();
		
//		wait(driver,"3");
//		Alert1(driver);
//		
//		wait(driver,"3");
//		 waitForElement(driver, portsearch);
//		click(driver,portsearch);
//		
//		 waitForElement(driver, selectport);
//		doubleClick(driver,selectport);
//		
//		waitForElement(driver, add);
//		click(driver,add);
//		
//		wait(driver,"3");
//		waitForElement(driver, save);
//		click(driver,save);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@col-id='voyageno'])[2]")));
		WebElement voyagename = driver.findElement(By.xpath("(//*[@col-id='voyageno'])[2]"));
		String voyage = voyagename.getText();
		System.out.println("Selected voyage name is : " + voyage);	
		
		if(voyage.equals("ASIA")) {
			
			System.out.println("Voage updated correctly");
		}else {
			System.out.println("Voyage got changed");
		}
		
//**************************************** Solve Crane *****************************************************//		
		
		waitForElement(driver, optimiser);
		click(driver,optimiser);
		
		waitForElement(driver, crane);
		click(driver,crane);
		
		waitForElement(driver, semiauto);
		click(driver,semiauto);
		
		waitForElement(driver, solvecrane);
		click(driver,solvecrane);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Crane Report']")));
		
		WebElement crane= driver.findElement(By.xpath("//*[text()='Crane Report']"));
		
		String name=crane.getText();
		
		System.out.println(name);
		
		if(name.equals("Crane Report")) {
			
			System.out.println("Solve crane is getting started ");
		}else {
			System.out.println("Solve crane is not started");
		}

	}
}
