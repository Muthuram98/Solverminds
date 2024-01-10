package scripts;

import static org.testng.Assert.fail;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import atu.testng.reports.logging.LogAs;

import java.util.Arrays;
import java.util.List;

import commonMethods.Keywords;
import commonMethods.TestNgXml;
import commonMethods.Utils;

public class shipping extends Keywords {

	String Stow_no_1 = Utils.getDataFromTestData("Solverminds", "Stow_no_1");
	String Stow_no_2 = Utils.getDataFromTestData("Solverminds", "Stow_no_2");
	String Stow_no_3 = Utils.getDataFromTestData("Solverminds", "Stow_no_3");
	String Stow_no_4 = Utils.getDataFromTestData("Solverminds", "Stow_no_4");
	String Stow_no_5 = Utils.getDataFromTestData("Solverminds", "Stow_no_5");
	String Stow_no_6 = Utils.getDataFromTestData("Solverminds", "Stow_no_6");
	String Stow_no_7 = Utils.getDataFromTestData("Solverminds", "Stow_no_7");
	String Stow_no_8 = Utils.getDataFromTestData("Solverminds", "Stow_no_8");
	
	
	String URL = TestNgXml.getdatafromExecution().get("shipping");
	String Username = Utils.getDataFromTestData("AxisLogin", "Username");
	String Password = Utils.getDataFromTestData("AxisLogin", "Password");

	public void sonatacanvas(WebDriver driver) {

		navigateUrl(driver, URL);

		waitForElement(driver, username);
		sendKeys(driver, username, "trackdfectuser1");

		waitForElement(driver, password);
		sendKeys(driver, password, "trackDfect@2h7s4");

		waitForElement(driver, login);
		click(driver, login);

		waitForElement(driver, selectvessel);
		click(driver, selectvessel);

		waitForElement(driver, clickvessel);
		click(driver, clickvessel);

		waitForElement(driver, dropdown);
		click(driver, dropdown);

		waitForElement(driver, add);
		click(driver, add);

		waitForElement(driver, stow_table);
		doubleClick(driver, stow_table);

		wait(driver, "2");

		System.out.println("mousehover started");
		WebElement element = driver.findElement(By.id("4"));

		int xCoordinate = element.getLocation().getX();
		int yCoordinate = element.getLocation().getY();

		Actions actions = new Actions(driver);

		List<Integer> xValues = Arrays.asList(0, 30, 60, 90, 170, 200, 230, 260);

		List<String> values = Arrays.asList(Stow_no_1, Stow_no_2, Stow_no_3, Stow_no_4, Stow_no_5, Stow_no_6, Stow_no_7,
				Stow_no_8);

		int i = 1;

		for (int j = 0; j < values.size(); j++) {
			int x = xValues.get(j);

			actions.moveToElement(element, x, yCoordinate).pause(Duration.ofSeconds(2)).perform();

			WebElement stow = driver.findElement(By.xpath("//*[@class='tooltiptext']"));
			String stow_no = stow.getText();
			String Stowno = stow_no.substring(9, 15);
			System.out.println("Actual Stow No " + i + " = " + Stowno);
			add(driver, "Actual Stow No " + i , Stowno, true, "");

			
			String expectedValue = values.get(j);

			if (Stowno.equals(expectedValue)) {
				System.out.println("Excepted Stow No " + i + " = " + expectedValue);
				System.out.println("Both are matches.");
				
				add(driver, "Excepted Stow No " + i , expectedValue, true, "");
				add(driver, "verified" , "Both are matches.", true, "");
				
			} else {
				System.out.println("Excepted Stow No " + i + " = " + expectedValue);
				System.out.println("Both are dismatches.");
				add1(driver, "Excepted Stow No " + i + " = " + expectedValue + "\n - Both are dismatches. ", LogAs.FAILED, true, expectedValue);
				add1(driver,"Both are dismatches.",LogAs.FAILED, true,expectedValue);
				
				Assert.fail();
				
			}
			i++;

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
        actions.moveToElement(element, xCoordinate, yCoordinate).pause(Duration.ofSeconds(2)).click().perform();
		
		WebElement stow = driver.findElement(By.xpath("//*[@class='tooltiptext']"));
		String stow_no = stow.getText();
		String Stowno = stow_no.substring(9, 15);
		System.out.println("Selected slot No = " + Stowno);
		add(driver, "Selected slot No ", Stowno, true, "");
		
		WebElement slot = driver.findElement(By.xpath("//*[@id='selectedSlots']"));
		String seleted_slot = slot.getText();
		System.out.println("Open slot No = " + seleted_slot);
		add(driver, "Open slot No ", seleted_slot, true, "");
		
		if (seleted_slot.equals(Stowno)){
			
			System.out.println("Both selected and Open slot No are equal.");
			add(driver, "Verified" , "Both selected and Open slot No are equal.", true, "");
			
		}else {
			System.out.println("Both selected and Open slot No are equal.");
			add1(driver,"Both selected and Open slot No are equal.",LogAs.FAILED, true,"");
			Assert.fail();
		}
		
		
		waitForElement(driver, Add_cargo_close);
		click(driver, Add_cargo_close);

	}

}
