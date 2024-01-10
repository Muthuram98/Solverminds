/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package atu.testng.reports.listeners;

import atu.testng.reports.ATUReports;
import atu.testng.reports.excel.ExcelReports;
import atu.testng.reports.exceptions.ATUReporterStepFailedException;
import atu.testng.reports.utils.Directory;
import atu.testng.reports.utils.Platform;
import atu.testng.reports.utils.SettingsFile;
import atu.testng.reports.writers.ConsolidatedReportsPageWriter;
import atu.testng.reports.writers.CurrentRunPageWriter;
import atu.testng.reports.writers.HTMLDesignFilesJSWriter;
import atu.testng.reports.writers.IndexPageWriter;
import atu.testng.reports.writers.TestCaseReportsPageWriter;
import atu.testrecorder.ATUTestRecorder;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.testng.IClass;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class ATUReportsListener implements ITestListener, ISuiteListener {
	int runCount = 0;
	ISuite iSuite;
	List<ITestResult> passedTests = new ArrayList();
	List<ITestResult> failedTests = new ArrayList();
	List<ITestResult> skippedTests = new ArrayList();
	private ATUTestRecorder recorder;

	public void onFinish(ITestContext paramITestContext) {
	}

	public void onStart(ITestContext paramITestContext) {
	}

	public void onTestFailedButWithinSuccessPercentage(
			ITestResult paramITestResult) {
	}

	public void onTestFailure(ITestResult paramITestResult) {
		this.failedTests.add(paramITestResult);
	}

	public void onTestSkipped(ITestResult paramITestResult) {
		createReportDir(paramITestResult);
		this.skippedTests.add(paramITestResult);
	}

	public void onTestStart(ITestResult paramITestResult) {
	}

	public void onTestSuccess(ITestResult paramITestResult) {
		try {
			if (paramITestResult.getAttribute("passedButFailed").equals(
					"passedButFailed")) {
				paramITestResult.setStatus(2);
				paramITestResult
						.setThrowable(new ATUReporterStepFailedException());
				this.failedTests.add(paramITestResult);
				return;
			}
		} catch (NullPointerException localNullPointerException) {
		}
		this.passedTests.add(paramITestResult);
	}

	public static void setPlatfromBrowserDetails(ITestResult paramITestResult) {
		Platform.prepareDetails(ATUReports.getWebDriver());
		paramITestResult.setAttribute(Platform.BROWSER_NAME_PROP,
				Platform.BROWSER_NAME);
		paramITestResult.setAttribute(Platform.BROWSER_VERSION_PROP,
				Platform.BROWSER_VERSION);
	}

	public static void createReportDir(ITestResult paramITestResult) {
		String str = getReportDir(paramITestResult);
		Directory.mkDirs(str);
		Directory.mkDirs(str + Directory.SEP + Directory.SCREENSHOT_DIRName);
	}

	public static String getRelativePathFromSuiteLevel(
			ITestResult paramITestResult) {
		String str1 = paramITestResult.getTestContext().getSuite().getName();
		String str2 = paramITestResult.getTestContext().getCurrentXmlTest()
				.getName();
		String str3 = paramITestResult.getTestClass().getName()
				.replace(".", Directory.SEP);
		String str4 = paramITestResult.getMethod().getMethodName();
		str4 = str4
				+ "_Iteration"
				+ (paramITestResult.getMethod().getCurrentInvocationCount() + 1);
		return str1 + Directory.SEP + str2 + Directory.SEP + str3
				+ Directory.SEP + str4;
	}

	public static String getReportDir(ITestResult paramITestResult) {
		String str1 = getRelativePathFromSuiteLevel(paramITestResult);
		paramITestResult.setAttribute("relativeReportDir", str1);
		String str2 = Directory.RUNDir + Directory.SEP + str1;
		paramITestResult.setAttribute("iteration", Integer
				.valueOf(paramITestResult.getMethod()
						.getCurrentInvocationCount() + 1));
		paramITestResult.setAttribute("reportDir", str2);
		return str2;
	}

	public void onFinish(ISuite paramISuite) {
		try {
			this.iSuite = paramISuite;
			String str1 = SettingsFile.get("passedList")
					+ this.passedTests.size() + ';';
			String str2 = SettingsFile.get("failedList")
					+ this.failedTests.size() + ';';
			String str3 = SettingsFile.get("skippedList")
					+ this.skippedTests.size() + ';';
			SettingsFile.set("passedList", str1);
			SettingsFile.set("failedList", str2);
			SettingsFile.set("skippedList", str3);
			HTMLDesignFilesJSWriter
					.lineChartJS(str1, str2, str3, this.runCount);
			HTMLDesignFilesJSWriter.barChartJS(str1, str2, str3, this.runCount);
			HTMLDesignFilesJSWriter.pieChartJS(this.passedTests.size(),
					this.failedTests.size(), this.skippedTests.size(),
					this.runCount);
			generateIndexPage();
			paramISuite.setAttribute("endExecution",
					Long.valueOf(System.currentTimeMillis()));
			long l = ((Long) paramISuite.getAttribute("startExecution"))
					.longValue();
			generateConsolidatedPage();
			generateCurrentRunPage(l, System.currentTimeMillis());
			startReportingForPassed(this.passedTests);
			startReportingForFailed(this.failedTests);
			startReportingForSkipped(this.skippedTests);
			if (Directory.generateExcelReports)
				ExcelReports.generateExcelReport(Directory.RUNDir
						+ Directory.SEP + "(" + Directory.REPORTSDIRName + ") "
						+ Directory.RUNName + this.runCount + ".xlsx",
						this.passedTests, this.failedTests, this.skippedTests);
			if (Directory.generateConfigReports)
				ConfigurationListener
						.startConfigurationMethodsReporting(this.runCount);
			if (Directory.recordSuiteExecution)
				try {
					this.recorder.stop();
				} catch (Throwable localThrowable) {
				}
		} catch (Exception localException) {
			throw new IllegalStateException(localException);
		}
	}

	public void onStart(ISuite paramISuite) {
		try {
			paramISuite.setAttribute("startExecution",
					Long.valueOf(System.currentTimeMillis()));
			Directory.verifyRequiredFiles();
			SettingsFile.correctErrors();
			this.runCount = (Integer.parseInt(SettingsFile.get("run").trim()) + 1);
			SettingsFile.set("run", "" + this.runCount);
			Directory.RUNDir += this.runCount;
			Directory.mkDirs(Directory.RUNDir);
			if (Directory.recordSuiteExecution)
				try {
					this.recorder = new ATUTestRecorder(Directory.RUNDir,
							"ATU_CompleteSuiteRecording",
							Boolean.valueOf(false));
					this.recorder.start();
				} catch (Throwable localThrowable) {
				}
			Directory.mkDirs(Directory.RUNDir + Directory.SEP
					+ paramISuite.getName());
			Iterator localIterator = paramISuite.getXmlSuite().getTests()
					.iterator();
			while (localIterator.hasNext()) {
				XmlTest localXmlTest = (XmlTest) localIterator.next();
				Directory.mkDirs(Directory.RUNDir + Directory.SEP
						+ paramISuite.getName() + Directory.SEP
						+ localXmlTest.getName());
			}
		} catch (Exception localException) {
			throw new IllegalStateException(localException);
		}
	}

	public void generateIndexPage() {
		PrintWriter localPrintWriter = null;
		try {
			localPrintWriter = new PrintWriter(Directory.REPORTSDir
					+ Directory.SEP + "index.html");
			IndexPageWriter.header(localPrintWriter);
			IndexPageWriter.content(localPrintWriter,
					ATUReports.indexPageDescription);
			IndexPageWriter.footer(localPrintWriter);
		} catch (FileNotFoundException localException2) {
			localFileNotFoundException.printStackTrace();
		} finally {
			try {
				localPrintWriter.close();
			} catch (Exception localException3) {
				localPrintWriter = null;
			}
		}
	}

	public void generateCurrentRunPage(long paramLong1, long paramLong2) {
		PrintWriter localPrintWriter = null;
		try {
			localPrintWriter = new PrintWriter(Directory.RUNDir + Directory.SEP
					+ "CurrentRun.html");
			CurrentRunPageWriter.header(localPrintWriter);
			CurrentRunPageWriter.menuLink(localPrintWriter, 0);
			CurrentRunPageWriter.content(localPrintWriter, this.iSuite,
					this.passedTests, this.failedTests, this.skippedTests,
					ConfigurationListener.passedConfigurations,
					ConfigurationListener.failedConfigurations,
					ConfigurationListener.skippedConfigurations, this.runCount,
					paramLong1, paramLong2);
			CurrentRunPageWriter.footer(localPrintWriter);
		} catch (FileNotFoundException localException2) {
			localFileNotFoundException.printStackTrace();
		} finally {
			try {
				localPrintWriter.close();
			} catch (Exception localException3) {
				localPrintWriter = null;
			}
		}
	}

	public void generateConsolidatedPage() {
		PrintWriter localPrintWriter = null;
		try {
			localPrintWriter = new PrintWriter(Directory.RESULTSDir
					+ Directory.SEP + "ConsolidatedPage.html");
			ConsolidatedReportsPageWriter.header(localPrintWriter);
			ConsolidatedReportsPageWriter.menuLink(localPrintWriter,
					this.runCount);
			ConsolidatedReportsPageWriter.content(localPrintWriter);
			ConsolidatedReportsPageWriter.footer(localPrintWriter);
		} catch (FileNotFoundException localException2) {
			localFileNotFoundException.printStackTrace();
		} finally {
			try {
				localPrintWriter.close();
			} catch (Exception localException3) {
				localPrintWriter = null;
			}
		}
	}

	public void startReportingForPassed(List<ITestResult> paramList) {
		PrintWriter localPrintWriter = null;
		Iterator localIterator = paramList.iterator();
		while (localIterator.hasNext()) {
			ITestResult localITestResult = (ITestResult) localIterator.next();
			String str = localITestResult.getAttribute("reportDir").toString();
			try {
				localPrintWriter = new PrintWriter(str + Directory.SEP
						+ localITestResult.getName() + ".html");
				TestCaseReportsPageWriter.header(localPrintWriter,
						localITestResult);
				TestCaseReportsPageWriter.menuLink(localPrintWriter,
						localITestResult, 0);
				TestCaseReportsPageWriter.content(localPrintWriter,
						localITestResult, this.runCount);
				TestCaseReportsPageWriter.footer(localPrintWriter);
			} catch (FileNotFoundException localException2) {
				localFileNotFoundException.printStackTrace();
			} finally {
				try {
					localPrintWriter.close();
				} catch (Exception localException3) {
					localPrintWriter = null;
				}
			}
		}
	}

	public void startReportingForFailed(List<ITestResult> paramList) {
		PrintWriter localPrintWriter = null;
		Iterator localIterator = paramList.iterator();
		while (localIterator.hasNext()) {
			ITestResult localITestResult = (ITestResult) localIterator.next();
			String str = localITestResult.getAttribute("reportDir").toString();
			try {
				localPrintWriter = new PrintWriter(str + Directory.SEP
						+ localITestResult.getName() + ".html");
				TestCaseReportsPageWriter.header(localPrintWriter,
						localITestResult);
				TestCaseReportsPageWriter.menuLink(localPrintWriter,
						localITestResult, 0);
				TestCaseReportsPageWriter.content(localPrintWriter,
						localITestResult, this.runCount);
				TestCaseReportsPageWriter.footer(localPrintWriter);
			} catch (FileNotFoundException localException2) {
			} finally {
				try {
					localPrintWriter.close();
				} catch (Exception localException3) {
					localPrintWriter = null;
				}
			}
		}
	}

	public void startReportingForSkipped(List<ITestResult> paramList) {
		PrintWriter localPrintWriter = null;
		Iterator localIterator = paramList.iterator();
		while (localIterator.hasNext()) {
			ITestResult localITestResult = (ITestResult) localIterator.next();
			String str = localITestResult.getAttribute("reportDir").toString();
			try {
				localPrintWriter = new PrintWriter(str + Directory.SEP
						+ localITestResult.getName() + ".html");
				TestCaseReportsPageWriter.header(localPrintWriter,
						localITestResult);
				TestCaseReportsPageWriter.menuLink(localPrintWriter,
						localITestResult, 0);
				TestCaseReportsPageWriter.content(localPrintWriter,
						localITestResult, this.runCount);
				TestCaseReportsPageWriter.footer(localPrintWriter);
			} catch (FileNotFoundException localException2) {
				localFileNotFoundException.printStackTrace();
			} finally {
				try {
					localPrintWriter.close();
				} catch (Exception localException3) {
					localPrintWriter = null;
				}
			}
		}
	}
}