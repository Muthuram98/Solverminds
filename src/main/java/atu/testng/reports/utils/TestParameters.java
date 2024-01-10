package atu.testng.reports.utils;

import java.io.File;

public class TestParameters {

	private File testCaseFileName;
	private String testCaseName;
	private File orSheetFileName;
	private String moduleName;

	public String getTestCaseName() {
		return testCaseName;
	}

	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}

	private String browserName;

	public File getTestCaseFileName() {
		return testCaseFileName;
	}

	public void setTestCaseFileName(File testCaseFileName) {
		this.testCaseFileName = testCaseFileName;
	}

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public File getOrSheetFileName() {
		return orSheetFileName;
	}

	public void setOrSheetFileName(File orSheetFileName) {
		this.orSheetFileName = orSheetFileName;
	}

	@Override
	public String toString() {
		return testCaseName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
}

