package atu.testng.reports;

import atu.testng.reports.exceptions.ATUReporterStepFailedException;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.AuthorDetails;
import atu.testng.reports.utils.Directory;
import atu.testng.reports.utils.Platform;
import atu.testng.reports.utils.Steps;
import atu.testng.selenium.reports.CaptureScreen;

import com.google.common.io.Files;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ATUReports {
	//public  WebDriver atudriver;
	public  final int MAX_BAR_REPORTS = 10;
	public  final String MESSAGE = "ATU Reporter: Preparing Reports";
	public  String indexPageDescription = "Reports Description";
	public  String currentRunDescription = "Here you can give description about the current Run";
	private  String screenShotNumber;
	private static long lastExecutionTime;
	private  long currentExecutionTime;
	public  final String EMPTY = "";
	public  final String STEP_NUM = "STEP";
	public  final String PASSED_BUT_FAILED = "passedButFailed";
	
//	public  void setWebDriver(WebDriver driver) {
//		this.atudriver = driver;
//	}
//
//	public WebDriver getWebDriver() {
//		return this.atudriver;
//	}
	

	public  void setAuthorInfo(String paramString1, String paramString2, String paramString3) {
		AuthorDetails localAuthorDetails = new AuthorDetails();
		localAuthorDetails.setAuthorName(paramString1);
		localAuthorDetails.setCreationDate(paramString2);
		localAuthorDetails.setVersion(paramString3);
		Reporter.getCurrentTestResult().setAttribute("authorInfo", localAuthorDetails);
	}

	public  void setTestCaseReqCoverage(String paramString) {
		Reporter.getCurrentTestResult().setAttribute("reqCoverage", paramString);
	}

	private  void stepFailureHandler(ITestResult paramITestResult, Steps paramSteps, LogAs paramLogAs) {
		if (paramLogAs == LogAs.FAILED) {
			buildReportData(paramSteps);
			if (Directory.continueExecutionAfterStepFailed)
				paramITestResult.setAttribute("passedButFailed", "passedButFailed");
			else
				throw new ATUReporterStepFailedException();
			return;
		}
		buildReportData(paramSteps);
	}

	public  void add(WebDriver driver,String paramString, LogAs paramLogAs, CaptureScreen paramCaptureScreen) {
		if (paramCaptureScreen != null)
			if (paramCaptureScreen.isCaptureBrowserPage())
				takeBrowserPageScreenShot(driver);
			else if (paramCaptureScreen.isCaptureDesktop())
				takeDesktopScreenshot();
			else if (paramCaptureScreen.isCaptureWebElement())
				takeWebElementScreenShot(driver,paramCaptureScreen.getElement());
		Steps localSteps = new Steps();
		localSteps.setDescription(paramString);
		localSteps.setInputValue("");
		localSteps.setExpectedValue("");
		localSteps.setActualValue("");
		localSteps.setTime(getExecutionTime());
		localSteps.setLineNum(getLineNumDesc());
		localSteps.setScreenShot(screenShotNumber);
		localSteps.setLogAs(paramLogAs);
		stepFailureHandler(Reporter.getCurrentTestResult(), localSteps, paramLogAs);
	}

	public  void add(WebDriver driver,String paramString1, String paramString2, LogAs paramLogAs,
			CaptureScreen paramCaptureScreen) {
		if (paramCaptureScreen != null)
			if (paramCaptureScreen.isCaptureBrowserPage())
				takeBrowserPageScreenShot(driver);
			else if (paramCaptureScreen.isCaptureDesktop())
				takeDesktopScreenshot();
			else if (paramCaptureScreen.isCaptureWebElement())
				takeWebElementScreenShot(driver,paramCaptureScreen.getElement());
		Steps localSteps = new Steps();
		localSteps.setDescription(paramString1);
		localSteps.setInputValue(paramString2);
		localSteps.setExpectedValue("");
		localSteps.setActualValue("");
		localSteps.setTime(getExecutionTime());
		localSteps.setLineNum(getLineNumDesc());
		localSteps.setScreenShot(screenShotNumber);
		localSteps.setLogAs(paramLogAs);
		stepFailureHandler(Reporter.getCurrentTestResult(), localSteps, paramLogAs);
	}

	public  void add(WebDriver driver,String paramString1, String paramString2, String paramString3, LogAs paramLogAs,
			CaptureScreen paramCaptureScreen) {
		if (paramCaptureScreen != null)
			if (paramCaptureScreen.isCaptureBrowserPage())
				takeBrowserPageScreenShot(driver);
			else if (paramCaptureScreen.isCaptureDesktop())
				takeDesktopScreenshot();
			else if (paramCaptureScreen.isCaptureWebElement())
				takeWebElementScreenShot(driver,paramCaptureScreen.getElement());
		Steps localSteps = new Steps();
		localSteps.setDescription(paramString1);
		localSteps.setInputValue("");
		localSteps.setExpectedValue(paramString2);
		localSteps.setActualValue(paramString3);
		localSteps.setTime(getExecutionTime());
		localSteps.setLineNum(getLineNumDesc());
		localSteps.setScreenShot(screenShotNumber);
		localSteps.setLogAs(paramLogAs);
		stepFailureHandler(Reporter.getCurrentTestResult(), localSteps, paramLogAs);
	}

	public  void add(WebDriver driver,String paramString1, String paramString2, String paramString3, String paramString4,
			LogAs paramLogAs, CaptureScreen paramCaptureScreen) {
		if (paramCaptureScreen != null)
			if (paramCaptureScreen.isCaptureBrowserPage())
				takeBrowserPageScreenShot(driver);
			else if (paramCaptureScreen.isCaptureDesktop())
				takeDesktopScreenshot();
			else if (paramCaptureScreen.isCaptureWebElement())
				takeWebElementScreenShot(driver,paramCaptureScreen.getElement());
		Steps localSteps = new Steps();
		localSteps.setDescription(paramString1);
		localSteps.setInputValue(paramString2);
		localSteps.setExpectedValue(paramString3);
		localSteps.setActualValue(paramString4);
		localSteps.setTime(getExecutionTime());
		localSteps.setLineNum(getLineNumDesc());
		localSteps.setScreenShot(screenShotNumber);
		localSteps.setLogAs(paramLogAs);
		stepFailureHandler(Reporter.getCurrentTestResult(), localSteps, paramLogAs);
	}

	private  void buildReportData(Steps paramSteps) {
		screenShotNumber = null;
		int i = Reporter.getOutput().size() + 1;
		Reporter.getCurrentTestResult().setAttribute("STEP" + i, paramSteps);
		Reporter.log("STEP" + i);
	}

	private  String getExecutionTime() {
		currentExecutionTime = System.currentTimeMillis();
		long l = currentExecutionTime - lastExecutionTime;
		if (l > 1000L) {
			l /= 1000L;
			lastExecutionTime = currentExecutionTime;
			return l + " Sec";
		}
		lastExecutionTime = currentExecutionTime;
		return l + " Milli Sec";
	}

	private  String getLineNumDesc() {
		String str = "" + java.lang.Thread.currentThread().getStackTrace()[3].getLineNumber();
		return str;
	}

	private  void takeDesktopScreenshot() {
		if (!(Directory.takeScreenshot))
			return;
		ITestResult localITestResult = Reporter.getCurrentTestResult();
		String str = localITestResult.getAttribute("reportDir").toString() + Directory.SEP + Directory.IMGDIRName;
		screenShotNumber = (Reporter.getOutput(Reporter.getCurrentTestResult()).size() + 1) + "";
		File localFile = new File(str + Directory.SEP + screenShotNumber + ".PNG");
		try {
			Rectangle localRectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage localBufferedImage = new Robot().createScreenCapture(localRectangle);
			ImageIO.write(localBufferedImage, "PNG", localFile);
		} catch (Exception localException) {
			screenShotNumber = null;
		}
	}

	private  void takeBrowserPageScreenShot(WebDriver driver) {
		if (!(Directory.takeScreenshot))
			return;
		if (driver == null) {
			screenShotNumber = null;
			return;
		}
		ITestResult localITestResult = Reporter.getCurrentTestResult();
		String str = localITestResult.getAttribute("reportDir").toString() + Directory.SEP + Directory.IMGDIRName;
		screenShotNumber = (Reporter.getOutput(Reporter.getCurrentTestResult()).size() + 1) + "";
		File localFile = new File(str + Directory.SEP + screenShotNumber + ".PNG");
		try {
			WebDriver localWebDriver;
			if (driver.getClass().getName().equals("org.openqa.selenium.remote.RemoteWebDriver"))
				localWebDriver = (WebDriver) new Augmenter().augment(driver);
			else
				localWebDriver = driver;
			if (localWebDriver instanceof TakesScreenshot) {
				byte[] arrayOfByte = (byte[]) ((TakesScreenshot) localWebDriver).getScreenshotAs(OutputType.BYTES);
				Files.write(arrayOfByte, localFile);
			} else {
				screenShotNumber = null;
			}
		} catch (Exception localException) {
			screenShotNumber = null;
		}
	}

	private  void takeWebElementScreenShot(WebDriver driver,WebElement paramWebElement) {
		if (!(Directory.takeScreenshot))
			return;
		if (driver == null) {
			screenShotNumber = null;
			return;
		}
		ITestResult localITestResult = Reporter.getCurrentTestResult();
		String str = localITestResult.getAttribute("reportDir").toString() + Directory.SEP + Directory.IMGDIRName;
		screenShotNumber = (Reporter.getOutput(Reporter.getCurrentTestResult()).size() + 1) + "";
		File localFile1 = new File(str + Directory.SEP + screenShotNumber + ".PNG");
		try {
			WebDriver localWebDriver;
			if (driver.getClass().getName().equals("org.openqa.selenium.remote.RemoteWebDriver"))
				localWebDriver = (WebDriver) new Augmenter().augment(driver);
			else
				localWebDriver = driver;
			if (localWebDriver instanceof TakesScreenshot) {
				File localFile2 = (File) ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				BufferedImage localBufferedImage1 = ImageIO.read(localFile2);
				Point localPoint = paramWebElement.getLocation();
				int i = paramWebElement.getSize().getWidth();
				int j = paramWebElement.getSize().getHeight();
				BufferedImage localBufferedImage2 = localBufferedImage1.getSubimage(localPoint.getX(),
						localPoint.getY(), i, j);
				ImageIO.write(localBufferedImage2, "PNG", localFile1);
			} else {
				screenShotNumber = null;
			}
		} catch (Exception localException) {
			screenShotNumber = null;
		}
	}

	@Deprecated
	public void add(WebDriver driver,String paramString,LogAs paramLogAs, boolean paramBoolean,String object) {
		if (paramBoolean)
			takeScreenShot(driver,object);
		Steps localSteps = new Steps();
		localSteps.setDescription(paramString);
		localSteps.setInputValue("");
		localSteps.setExpectedValue("");
		localSteps.setActualValue("");
		localSteps.setTime(getExecutionTime());
		localSteps.setLineNum(getLineNumDesc());
		localSteps.setScreenShot(screenShotNumber);
		localSteps.setLogAs(paramLogAs);
		buildReportData(localSteps);
//		localSteps.setStatus("PASS");
	}
	
	public void add(String paramString,LogAs paramLogAs, boolean paramBoolean,String value,double time) {
		//if (paramBoolean)
			//takeScreenShot(driver,object);
		Steps localSteps = new Steps();
		localSteps.setDescription(paramString);
		localSteps.setInputValue("");
		localSteps.setExpectedValue("");
		localSteps.setActualValue(value);
		localSteps.setTime(""+time);
		localSteps.setLineNum("");
		localSteps.setScreenShot(screenShotNumber);
		localSteps.setLogAs(paramLogAs);
		buildReportData(localSteps);
	}
	@Deprecated
	public  void add1(WebDriver driver,String paramString,LogAs paramLogAs, boolean paramBoolean,String object) {
		if (paramBoolean)
			takeScreenShot1(driver,object);
		Steps localSteps = new Steps();
		localSteps.setDescription(paramString);
		localSteps.setInputValue("");
		localSteps.setExpectedValue("");
		localSteps.setActualValue("");
		localSteps.setTime(getExecutionTime());
		localSteps.setLineNum(getLineNumDesc());
		localSteps.setScreenShot(screenShotNumber);
		localSteps.setLogAs(paramLogAs);
		buildReportData(localSteps);
//		localSteps.setStatus("FAIL");
	}
	public  void add1(String paramString,LogAs paramLogAs, boolean paramBoolean,String object) {
		Steps localSteps = new Steps();
		localSteps.setDescription(paramString);
		localSteps.setInputValue("");
		localSteps.setExpectedValue("");
		localSteps.setActualValue(object);
		localSteps.setTime(getExecutionTime());
		localSteps.setLineNum(getLineNumDesc());
		localSteps.setScreenShot(screenShotNumber);
		localSteps.setLogAs(paramLogAs);
		buildReportData(localSteps);
	}

	@Deprecated
	public  void add(WebDriver driver,String paramString1, String paramString2, boolean paramBoolean,String object) {
		if (paramBoolean)
			takeScreenShot(driver,object);
		Steps localSteps = new Steps();
		localSteps.setDescription(paramString1);
		localSteps.setInputValue(paramString2);
		localSteps.setExpectedValue("");
		localSteps.setActualValue("");
		localSteps.setTime(getExecutionTime());
		localSteps.setLineNum(getLineNumDesc());
		localSteps.setScreenShot(screenShotNumber);
		localSteps.setLogAs(LogAs.PASSED);
//		localSteps.setStatus("PASS");
		
		buildReportData(localSteps);
	}
	
	@Deprecated
	public  void add5(WebDriver driver,String paramString1, String paramString2, String paramString3, boolean paramBoolean,String object) {
		if (paramBoolean)
			takeScreenShot(driver,object);
		Steps localSteps = new Steps();
		localSteps.setDescription(paramString1);
		localSteps.setInputValue(paramString2);
		localSteps.setExpectedValue("");
		localSteps.setActualValue(paramString3);
		localSteps.setTime(getExecutionTime());
		localSteps.setLineNum(getLineNumDesc());
		localSteps.setScreenShot(screenShotNumber);
		localSteps.setLogAs(LogAs.PASSED);
//		localSteps.setStatus("PASS");
		
		buildReportData(localSteps);
	}
	
	@Deprecated
	public  void add4(WebDriver driver,String paramString1, String paramString2, boolean paramBoolean,String object) {
		if (paramBoolean)
			takeScreenShot(driver,object);
		Steps localSteps = new Steps();
		localSteps.setDescription(paramString1);
		localSteps.setInputValue("");
		localSteps.setExpectedValue("");
		localSteps.setActualValue(paramString2);
		localSteps.setTime(getExecutionTime());
		localSteps.setLineNum(getLineNumDesc());
		localSteps.setScreenShot(screenShotNumber);
		localSteps.setLogAs(LogAs.PASSED);
//		localSteps.setStatus("PASS");
		
		buildReportData(localSteps);
	}
	
	@Deprecated
	public  void add2(WebDriver driver,String Status,String paramString1, String paramString2, boolean paramBoolean,LogAs paramLogAs) {
		if (paramBoolean)
			takeScreenShot(driver,"");
		Steps localSteps = new Steps();
		localSteps.setDescription(paramString1);
		localSteps.setInputValue(paramString2);
		localSteps.setExpectedValue("");
		localSteps.setActualValue("");
		localSteps.setTime(getExecutionTime());
		localSteps.setLineNum(getLineNumDesc());
		localSteps.setScreenShot(screenShotNumber);
		localSteps.setLogAs(paramLogAs);
//		localSteps.setStatus(Status);
		buildReportData(localSteps);
	}
	 
	@Deprecated
	public  void add3(WebDriver driver,String paramString1, String paramString2,String paramstring3, boolean paramBoolean,LogAs paramLogAs) {
		if (paramBoolean)
			takeScreenShot(driver,"");
		Steps localSteps = new Steps();
		localSteps.setDescription(paramString1);
		localSteps.setInputValue(paramString2);
		localSteps.setExpectedValue("");
		localSteps.setActualValue(paramstring3);
		localSteps.setTime(getExecutionTime());
		localSteps.setLineNum(getLineNumDesc());
		localSteps.setScreenShot(screenShotNumber);
		localSteps.setLogAs(paramLogAs);
		buildReportData(localSteps);
	}
	@Deprecated
	public  void add1(WebDriver driver,String paramString1, String paramString2, boolean paramBoolean,String object) {
		if (paramBoolean)
			takeScreenShot1(driver,object);
		Steps localSteps = new Steps();
		localSteps.setDescription(paramString1);
		localSteps.setInputValue(paramString2);
		localSteps.setExpectedValue("");
		localSteps.setActualValue("");
		localSteps.setTime(getExecutionTime());
		localSteps.setLineNum(getLineNumDesc());
		localSteps.setScreenShot(screenShotNumber);
		localSteps.setLogAs(LogAs.FAILED);
//		localSteps.setStatus("FAIL");
		buildReportData(localSteps);
	}
	
//	@Deprecated
//	public  void add(String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
//		if (paramBoolean)
//			takeScreenShot();
//		Steps localSteps = new Steps();
//		localSteps.setDescription(paramString1);
//		localSteps.setInputValue("");
//		localSteps.setExpectedValue(paramString2);
//		localSteps.setActualValue(paramString3);
//		localSteps.setTime(getExecutionTime());
//		localSteps.setLineNum(getLineNumDesc());
//		localSteps.setScreenShot(screenShotNumber);
//		localSteps.setLogAs(LogAs.PASSED);
//		buildReportData(localSteps);
//	}

//	@Deprecated
//	public  void add(String paramString1, String paramString2, String paramString3, String paramString4,
//			boolean paramBoolean) {
//		if (paramBoolean)
//			takeScreenShot();
//		Steps localSteps = new Steps();
//		localSteps.setDescription(paramString1);
//		localSteps.setInputValue(paramString2);
//		localSteps.setExpectedValue(paramString3);
//		localSteps.setActualValue(paramString4);
//		localSteps.setTime(getExecutionTime());
//		localSteps.setLineNum(getLineNumDesc());
//		localSteps.setScreenShot(screenShotNumber);
//		localSteps.setLogAs(LogAs.PASSED);
//		buildReportData(localSteps);
//	}

	private  void takeScreenShot(WebDriver driver, String object) {
		
		if (driver == null) {
			screenShotNumber = null;
			return;
		}
		if(Directory.TestPassScreenshot==false)
		{
		return;
		}
		screenShot(driver,object);
	}
private  void takeScreenShot1(WebDriver driver,String object) {
		if (driver == null) {
			screenShotNumber = null;
			return;
		}
//		if(Directory.takeScreenshot==false)
//		{
//		return;		
//		}
		
		screenShot(driver,object);
	}
	public void screenShot(WebDriver driver, String object)
	{
		ITestResult localITestResult = Reporter.getCurrentTestResult();
		String str = localITestResult.getAttribute("reportDir").toString() + Directory.SEP + Directory.IMGDIRName;
		//screenShotNumber = localITestResult.getName() + "_"+ object ;
		
		screenShotNumber = (Reporter.getOutput(Reporter.getCurrentTestResult()).size() + 1) + "";
		File localFile = new File(str + Directory.SEP + screenShotNumber +".PNG");
		try {
			WebDriver localWebDriver;
			if (driver.getClass().getName().equals("org.openqa.selenium.remote.RemoteWebDriver"))
				localWebDriver =  (WebDriver) new Augmenter().augment(driver);
			else
				localWebDriver = driver;
			if (localWebDriver instanceof TakesScreenshot) {
				byte[] arrayOfByte = (byte[]) ((TakesScreenshot) localWebDriver).getScreenshotAs(OutputType.BYTES);
				Files.write(arrayOfByte, localFile);
			} else {
				screenShotNumber = null;
			}
		} catch (Exception localException) {
			screenShotNumber = null;
		}
	}	
	 {
		try {
			lastExecutionTime = Reporter.getCurrentTestResult().getStartMillis();
		} catch (Exception localException) {
		}
	}
	 
	 

/*public void takeScreenShot(WebDriver driver) {
	
	ITestResult localITestResult = Reporter.getCurrentTestResult();
	String str = localITestResult.getAttribute("reportDir").toString() + Directory.SEP + Directory.IMGDIRName;
	screenShotNumber = (Reporter.getOutput(Reporter.getCurrentTestResult()).size() + 1) + "";
	File localFile = new File(str + Directory.SEP + screenShotNumber + ".PNG");
	try {
		WebDriver localWebDriver;
		if (driver.getClass().getName().equals("org.openqa.selenium.remote.RemoteWebDriver"))
			localWebDriver = new Augmenter().augment(driver);
		else
			localWebDriver = driver;
		if (localWebDriver instanceof TakesScreenshot) {
			byte[] arrayOfByte = (byte[]) ((TakesScreenshot) localWebDriver).getScreenshotAs(OutputType.BYTES);
			Files.write(arrayOfByte, localFile);
		} else {
			screenShotNumber = null;
		}
	} catch (Exception localException) {
		screenShotNumber = null;
	}
}

static {
	try {
		lastExecutionTime = Reporter.getCurrentTestResult().getStartMillis();
	} catch (Exception localException) {
	}
}*/
}