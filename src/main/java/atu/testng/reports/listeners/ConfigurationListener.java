package atu.testng.reports.listeners;

import atu.testng.reports.utils.Directory;
import atu.testng.reports.writers.TestCaseReportsPageWriter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.testng.IConfigurationListener2;
import org.testng.ITestResult;

public class ConfigurationListener implements IConfigurationListener2 {
	 List<ITestResult> passedConfigurations = new ArrayList<ITestResult>();
	 List<ITestResult> failedConfigurations = new ArrayList<ITestResult>();
	 List<ITestResult> skippedConfigurations = new ArrayList<ITestResult>();
	
	TestCaseReportsPageWriter testWriter=new TestCaseReportsPageWriter();

	public void onConfigurationFailure(ITestResult paramITestResult) {
		if (!(Directory.generateConfigReports))
			return;
		failedConfigurations.add(paramITestResult);
	}

	public void onConfigurationSkip(ITestResult paramITestResult) {
		if (!(Directory.generateConfigReports))
			return;
		ATUReportsListener.createReportDir(paramITestResult);
		skippedConfigurations.add(paramITestResult);
	}

	public void onConfigurationSuccess(ITestResult paramITestResult) {
		if (!(Directory.generateConfigReports))
			return;
		passedConfigurations.add(paramITestResult);
	}

	public  void startConfigurationMethodsReporting(int paramInt) {
		startReportingForPassedConfigurations(passedConfigurations, paramInt);
		startReportingForFailedConfigurations(failedConfigurations, paramInt);
		startReportingForSkippedConfigurations(skippedConfigurations, paramInt);
	}

	private  void startReportingForPassedConfigurations(List<ITestResult> paramList, int paramInt) {
		PrintWriter localPrintWriter = null;
		Iterator<ITestResult> localIterator = paramList.iterator();
		while (localIterator.hasNext()) {
			ITestResult localITestResult = (ITestResult) localIterator.next();
			String str = null;
			str = localITestResult.getAttribute("reportDir").toString();
			try {
				localPrintWriter = new PrintWriter(str + Directory.SEP + localITestResult.getName() + ".html");
				TestCaseReportsPageWriter.header(localPrintWriter, localITestResult);
				TestCaseReportsPageWriter.menuLink(localPrintWriter, localITestResult, 0);
				testWriter.content(localPrintWriter, localITestResult, paramInt);
				TestCaseReportsPageWriter.footer(localPrintWriter);
			} catch (FileNotFoundException localException2) {
				localException2.printStackTrace();
			} finally {
				try {
					localPrintWriter.close();
				} catch (Exception localException3) {
					localPrintWriter = null;
				}
			}
		}
	}

	private  void startReportingForFailedConfigurations(List<ITestResult> paramList, int paramInt) {
		PrintWriter localPrintWriter = null;
		Iterator<ITestResult> localIterator = paramList.iterator();
		while (localIterator.hasNext()) {
			ITestResult localITestResult = (ITestResult) localIterator.next();
			String str = localITestResult.getAttribute("reportDir").toString();
			try {
				localPrintWriter = new PrintWriter(str + Directory.SEP + localITestResult.getName() + ".html");
				TestCaseReportsPageWriter.header(localPrintWriter, localITestResult);
				TestCaseReportsPageWriter.menuLink(localPrintWriter, localITestResult, 0);
				testWriter.content(localPrintWriter, localITestResult, paramInt);
				TestCaseReportsPageWriter.footer(localPrintWriter);
			} catch (FileNotFoundException localException2) {
				localException2.printStackTrace();
			} finally {
				try {
					localPrintWriter.close();
				} catch (Exception localException3) {
					localPrintWriter = null;
				}
			}
		}
	}

	private  void startReportingForSkippedConfigurations(List<ITestResult> paramList, int paramInt) {
		PrintWriter localPrintWriter = null;
		Iterator<ITestResult> localIterator = paramList.iterator();
		while (localIterator.hasNext()) {
			ITestResult localITestResult = (ITestResult) localIterator.next();
			String str = localITestResult.getAttribute("reportDir").toString();
			try {
				localPrintWriter = new PrintWriter(str + Directory.SEP + localITestResult.getName() + ".html");
				TestCaseReportsPageWriter.header(localPrintWriter, localITestResult);
				TestCaseReportsPageWriter.menuLink(localPrintWriter, localITestResult, 0);
				testWriter.content(localPrintWriter, localITestResult, paramInt);
				TestCaseReportsPageWriter.footer(localPrintWriter);
			} catch (FileNotFoundException localException2) {
				localException2.printStackTrace();
			} finally {
				try {
					localPrintWriter.close();
				} catch (Exception localException3) {
					localPrintWriter = null;
				}
			}
		}
	}

	public void beforeConfiguration(ITestResult paramITestResult) {
		ATUReportsListener.createReportDir(paramITestResult);
	}
}