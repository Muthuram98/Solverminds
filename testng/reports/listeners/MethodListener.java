/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package atu.testng.reports.listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class MethodListener implements IInvokedMethodListener {
	public void afterInvocation(IInvokedMethod paramIInvokedMethod,
			ITestResult paramITestResult) {
	}

	public void beforeInvocation(IInvokedMethod paramIInvokedMethod,
			ITestResult paramITestResult) {
		if ((paramIInvokedMethod.isConfigurationMethod())
				&& (!(paramIInvokedMethod.isTestMethod())))
			return;
		ATUReportsListener.createReportDir(paramITestResult);
		ATUReportsListener.setPlatfromBrowserDetails(paramITestResult);
	}
}