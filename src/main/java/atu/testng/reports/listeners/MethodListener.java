package atu.testng.reports.listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class MethodListener implements IInvokedMethodListener {
	
	ATUReportsListener atuLis=new ATUReportsListener();
	
	public void afterInvocation(IInvokedMethod paramIInvokedMethod, ITestResult paramITestResult) {
	}

	public void beforeInvocation(IInvokedMethod paramIInvokedMethod, ITestResult paramITestResult) {
		if ((paramIInvokedMethod.isConfigurationMethod()) && (!(paramIInvokedMethod.isTestMethod())))
			return;
		ATUReportsListener.createReportDir(paramITestResult);
		atuLis.setPlatfromBrowserDetails(paramITestResult);
	}
}