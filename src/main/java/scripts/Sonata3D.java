package scripts;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import atu.testng.reports.logging.LogAs;
import commonMethods.Keywords;
import commonMethods.TestNgXml;
import commonMethods.Utils;

public class Sonata3D extends Keywords{

	String URL = TestNgXml.getdatafromExecution().get("Sonata3D");
	String Username = Utils.getDataFromTestData("Solverminds", "Username");
	String Password = Utils.getDataFromTestData("Solverminds", "Password");
	
	boolean flag=true;
	
 public void Baplie_viewer(WebDriver driver) {
	  

		String[] stow_data=new String[40];
		String value;
		for(int i=1;i<39;i++) {
			value=Utils.getDataFromTestData("ContainerValue", "Container_"+i);
			//System.out.println(value);
			stow_data[i-1]=value;
		}
		
	    Dimension newDimension = new Dimension(1366, 720);
		driver.manage().window().setSize(newDimension);
		Dimension newSetDimension = driver.manage().window().getSize();
		int newHeight = newSetDimension.getHeight();
		int newWidth = newSetDimension.getWidth();
		System.out.println("Current height: " + newHeight);
		System.out.println("Current width: " + newWidth);
		driver.manage().window().maximize();
		    
	    navigateUrl(driver, URL);
		waitForElement(driver, username);
		
		sendKeys(driver, username, Username);
		
		waitForElement(driver, password);
		
		sendKeys(driver, password, Password);
		waitForElement(driver, login);
		click(driver,login);
		
		waitForElement(driver, selectvessel);
		click(driver,selectvessel);
		
		waitForElement(driver, clickvessel2);
		click(driver,clickvessel2);	

	    waitForElement(driver,OutsideCanvas);
	    waitForElement(driver,threeD_view);
	    click(driver,threeD_view);
	    
	    ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
        System.out.println(tab.size());
        driver.switchTo().window(tab.get(tab.size() - 1));
   
        waitForElement(driver,port);
        
        if(flag==true)
	    {
       // takescreenshot(driver, "/Expected_screenshot/Userflow_02/01");
		takescreenshot(driver, "/Actual_screenshot/Userflow_02/01");
 		try {
 			imageComparision(driver,"/Userflow_02/01", "/Userflow_02/01");
 		} catch (IOException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 			//Assert.fail();
 		}
	    }
        waitForElement(driver,switchTo_2D_3D);
        click(driver,switchTo_2D_3D);
        
        waitForElement(driver,baySelect);
        click(driver,baySelect);
        
        waitForElement(driver,firstBay);
        click(driver,firstBay);
        
        
        List<WebElement> Containers_WW=getWebElements(driver,container_All);
        int k=0;
        boolean stow=false;
        for(WebElement ele1:Containers_WW) {
             
        	JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');",ele1);

               ele1.click();
               System.out.println("*****Start*****");
               
               List<WebElement> containerList=getWebElements(driver,containerDetails);
        
            for(WebElement ele:containerList) {
               	String Stow_table=ele.getText();
        	
            	if(Stow_table.contains("Build Version")) {
             		break;
             	}
            	 String Expected_stowNo=stow_data[k];
            	
            	if(stow) {
            		if(Stow_table.equals(Expected_stowNo)) {
            			System.out.println("Excepted Stow No " + k + " = " + Expected_stowNo);
            			System.out.println("Actual Stow No " + k + " = " + Stow_table);
       			     	System.out.println("Both are matches.");
        		        add(driver,"Both are matches.", "Actual_"+k+" : "+Stow_table, true, "");

            		}else {
            			System.out.println("Excepted Stow No " + k + " = " + Expected_stowNo);
            			System.out.println("Actual Stow No " + k + " = " + Stow_table);
       			     	System.out.println("Both are dismatches.!!");
       			     	
       			        add1(driver,"Both are dismatches.!!", LogAs.FAILED,true,"Actual_"+k+" : "+Stow_table);
            		}
            		stow=false;
            	}
            	
            	if(Stow_table.contains("Stowage No")) {
            		stow=true;
            	}
            	

        }
            k++;
            System.out.println("******End******");
            System.out.println();
        
        }
        
        waitForElement(driver,switchTo_2D_3D);
        click(driver,switchTo_2D_3D);
        
      //  waitForElement1(driver,view_360);
        
        if(flag==true)
	    {
       // takescreenshot(driver, "/Expected_screenshot/Userflow_02/02");
		takescreenshot(driver, "/Actual_screenshot/Userflow_02/02");
 		try {
 			imageComparision(driver,"/Userflow_02/02", "/Userflow_02/02");
 		} catch (IOException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 			//Assert.fail();
 		}
	    }
        
        click(driver,bayForward);
        
        if(flag==true)
	    {
       // takescreenshot(driver, "/Expected_screenshot/Userflow_02/03");
		takescreenshot(driver, "/Actual_screenshot/Userflow_02/03");
 		try {
 			imageComparision(driver,"/Userflow_02/03", "/Userflow_02/03");
 		} catch (IOException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 			//Assert.fail();
 		}
	    }
        
        
 }
}
