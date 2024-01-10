/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package atu.testng.reports.utils;

import atu.testng.reports.enums.RecordingFor;
import atu.testng.reports.enums.ReportLabels;
import atu.testng.reports.exceptions.ATUReporterException;
import atu.testng.reports.writers.HTMLDesignFilesWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

public class Directory {
	public static final String ATU_VERSION = "v5.1.1";
	public static final String CURRENTDir = System.getProperty("user.dir");
	public static final String SEP = System.getProperty("file.separator");
	public static String REPORTSDIRName = "ATU Reports";
	public static String REPORTSDir = CURRENTDir + SEP + REPORTSDIRName;
	public static String RESULTSDir = REPORTSDir + SEP + "Results";
	public static String HTMLDESIGNDIRName = "HTML_Design_Files";
	public static String HTMLDESIGNDir = REPORTSDir + SEP + HTMLDESIGNDIRName;
	public static String CSSDIRName = "CSS";
	public static String CSSDir = HTMLDESIGNDir + SEP + CSSDIRName;
	public static String IMGDIRName = "IMG";
	public static String IMGDir = HTMLDESIGNDir + SEP + IMGDIRName;
	public static String JSDIRName = "JS";
	public static String JSDir = HTMLDESIGNDir + SEP + JSDIRName;
	public static String RUNName = "Run_";
	public static String RUNDir = RESULTSDir + SEP + RUNName;
	public static String SETTINGSFile = RESULTSDir + SEP
			+ "Settings.properties";
	public static final char JS_SETTINGS_DELIM = 59;
	public static final String REPO_DELIM = "##@##@##";
	public static final char JS_FILE_DELIM = 44;
	public static final String MENU_LINK_NAME = "Run ";
	public static final String SCREENSHOT_TYPE = "PNG";
	public static final String SCREENSHOT_EXTENSION = ".PNG";
	private static String logo = null;
	public static String SCREENSHOT_DIRName = "img";
	public static boolean generateConfigReports = true;
	public static boolean generateExcelReports = false;
	public static boolean takeScreenshot = false;
	public static boolean continueExecutionAfterStepFailed = false;
	public static boolean recordExecutionAvailable = false;
	public static boolean recordSuiteExecution = false;
	public static boolean recordTestMethodExecution = false;
	public static final String MAIN_RECORD_FILE_NAME = "ATU_CompleteSuiteRecording";

	public static void init() throws ATUReporterException {
		if (getCustomProperties() == null)
			return;
		Properties localProperties = new Properties();
		try {
			localProperties.load(new FileReader(getCustomProperties()));
			String str1 = localProperties.getProperty("atu.reports.dir").trim();
			String str2 = localProperties.getProperty("atu.proj.header.text")
					.trim();
			logo = localProperties.getProperty("atu.proj.header.logo").trim();
			String str3 = localProperties.getProperty("atu.proj.description")
					.trim();
			String str4 = localProperties.getProperty(
					"atu.reports.takescreenshot").trim();
			String str5 = localProperties.getProperty(
					"atu.reports.configurationreports").trim();
			String str6 = localProperties.getProperty("atu.reports.excel")
					.trim();
			String str7 = localProperties.getProperty(
					"atu.reports.continueExecutionAfterStepFailed").trim();
			String str8 = localProperties.getProperty(
					"atu.reports.recordExecution").trim();
			try {
				if ((str2 != null) && (str2.length() > 0))
					ReportLabels.HEADER_TEXT.setLabel(str2);
				if ((str4 != null) && (str4.length() > 0))
					try {
						takeScreenshot = Boolean.parseBoolean(str4);
					} catch (Exception localException1) {
					}
				if ((str5 != null) && (str5.length() > 0))
					try {
						generateConfigReports = Boolean.parseBoolean(str5);
					} catch (Exception localException2) {
					}
				if ((str6 != null) && (str6.length() > 0))
					try {
						generateExcelReports = Boolean.parseBoolean(str6);
					} catch (Exception localException3) {
					}
				if ((str7 != null) && (str7.length() > 0))
					try {
						continueExecutionAfterStepFailed = Boolean
								.parseBoolean(str7);
					} catch (Exception localException4) {
					}
				if ((str8 != null) && (str8.length() > 0))
					try {
						RecordingFor localRecordingFor = RecordingFor
								.valueOf(str8.toUpperCase());
						if (localRecordingFor == RecordingFor.SUITE)
							recordSuiteExecution = true;
						else if (localRecordingFor == RecordingFor.TESTMETHOD)
							recordTestMethodExecution = true;
					} catch (Throwable localThrowable) {
					}
				if ((str3 != null) && (str3.length() > 0))
					atu.testng.reports.ATUReports.indexPageDescription = str3;
				if ((str1 != null) && (str1.length() > 0)) {
					REPORTSDir = str1;
					REPORTSDIRName = new File(REPORTSDir).getName();
					RESULTSDir = REPORTSDir + SEP + "Results";
					HTMLDESIGNDIRName = "HTML_Design_Files";
					HTMLDESIGNDir = REPORTSDir + SEP + HTMLDESIGNDIRName;
					CSSDIRName = "CSS";
					CSSDir = HTMLDESIGNDir + SEP + CSSDIRName;
					IMGDIRName = "IMG";
					IMGDir = HTMLDESIGNDir + SEP + IMGDIRName;
					JSDIRName = "JS";
					JSDir = HTMLDESIGNDir + SEP + JSDIRName;
					RUNName = "Run_";
					RUNDir = RESULTSDir + SEP + RUNName;
					SETTINGSFile = RESULTSDir + SEP + "Settings.properties";
				}
			} catch (Exception localException5) {
				throw new ATUReporterException(localException5.toString());
			}
		} catch (FileNotFoundException localFileNotFoundException) {
			throw new ATUReporterException(
					"The Path for the Custom Properties file could not be found");
		} catch (IOException localIOException) {
			throw new ATUReporterException(
					"Problem Occured while reading the ATU Reporter Config File");
		}
	}

	public static void mkDirs(String paramString) {
		File localFile = new File(paramString);
		if (localFile.exists())
			return;
		localFile.mkdirs();
	}

	public static boolean exists(String paramString) {
		File localFile = new File(paramString);
		return localFile.exists();
	}

	public static void verifyRequiredFiles() throws ATUReporterException {
		init();
		mkDirs(REPORTSDir);
		if (!(exists(RESULTSDir))) {
			mkDirs(RESULTSDir);
			SettingsFile.initSettingsFile();
		}
		if (!(exists(HTMLDESIGNDir))) {
			mkDirs(HTMLDESIGNDir);
			mkDirs(CSSDir);
			mkDirs(JSDir);
			mkDirs(IMGDir);
			HTMLDesignFilesWriter.writeCSS();
			HTMLDesignFilesWriter.writeIMG();
			HTMLDesignFilesWriter.writeJS();
		}
		if ((logo == null) || (logo.length() <= 0))
			return;
		String str = new File(logo).getName();
		if (!(new File(IMGDir + SEP + str).exists()))
			copyImage(logo);
		ReportLabels.PROJ_LOGO.setLabel(str);
	}

	private static void copyImage(String paramString)
			throws ATUReporterException {
		File localFile = new File(paramString);
		if (!(localFile.exists()))
			return;
		FileImageInputStream localFileImageInputStream = null;
		FileImageOutputStream localFileImageOutputStream = null;
		try {
			localFileImageInputStream = new FileImageInputStream(new File(
					paramString));
			localFileImageOutputStream = new FileImageOutputStream(new File(
					IMGDir + SEP + localFile.getName()));
			int i = 0;
			while ((i = localFileImageInputStream.read()) >= 0)
				localFileImageOutputStream.write(i);
			localFileImageOutputStream.close();
		} catch (Exception localException3) {
		} finally {
			try {
				localFileImageInputStream.close();
				localFileImageOutputStream.close();
				localFile = null;
			} catch (Exception localException4) {
				localFileImageInputStream = null;
				localFileImageOutputStream = null;
				localFile = null;
			}
		}
	}

	private static String getCustomProperties() {
		return System.getProperty("atu.reporter.config");
	}
}