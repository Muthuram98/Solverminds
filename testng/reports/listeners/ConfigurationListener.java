/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
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
	static List<ITestResult> passedConfigurations = new ArrayList();
	static List<ITestResult> failedConfigurations = new ArrayList();
	static List<ITestResult> skippedConfigurations = new ArrayList();

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

	public static void startConfigurationMethodsReporting(int paramInt) {
		startReportingForPassedConfigurations(passedConfigurations, paramInt);
		startReportingForFailedConfigurations(failedConfigurations, paramInt);
		startReportingForSkippedConfigurations(skippedConfigurations, paramInt);
	}

	private static void startReportingForPassedConfigurations(
			List<ITestResult> paramList, int paramInt) {
		PrintWriter localPrintWriter = null;
		Iterator localIterator = paramList.iterator();
		while (localIterator.hasNext()) {
			ITestResult localITestResult = (ITestResult) localIterator.next();
			String str = null;
			str = localITestResult.getAttribute("reportDir").toString();
			try {
				localPrintWriter = new PrintWriter(str + Directory.SEP
						+ localITestResult.getName() + ".html");
				TestCaseReportsPageWriter.header(localPrintWriter,
						localITestResult);
				TestCaseReportsPageWriter.menuLink(localPrintWriter,
						localITestResult, 0);
				TestCaseReportsPageWriter.content(localPrintWriter,
						localITestResult, paramInt);
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

	private static void startReportingForFailedConfigurations(
			List<ITestResult> paramList, int paramInt) {
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
						localITestResult, paramInt);
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

	private static void startReportingForSkippedConfigurations(
			List<ITestResult> paramList, int paramInt) {
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
						localITestResult, paramInt);
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

	public void beforeConfiguration(ITestResult paramITestResult) {
		ATUReportsListener.createReportDir(paramITestResult);
	}
}