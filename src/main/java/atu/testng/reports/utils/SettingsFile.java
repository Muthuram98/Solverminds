package atu.testng.reports.utils;

import atu.testng.reports.exceptions.ATUReporterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class SettingsFile {
	private static Properties properties;

	public static void initSettingsFile() throws ATUReporterException {
		create(Directory.SETTINGSFile);
		set("run", "0");
		set("passedList", "");
		set("failedList", "");
		set("skippedList", "");
	}

	public static void create(String paramString) throws ATUReporterException {
		File localFile = new File(paramString);
		try {
			if (!(localFile.exists())) {
				localFile.createNewFile();
			} else {
				localFile.delete();
				localFile.createNewFile();
			}
		} catch (IOException localIOException) {
			throw new ATUReporterException("Unable To Create Required Files for Custom Reports");
		}
	}

	public static void open() throws ATUReporterException {
		properties = new Properties();
		try {
			properties.load(new FileReader(Directory.SETTINGSFile));
		} catch (FileNotFoundException localFileNotFoundException) {
			throw new ATUReporterException("Settings File Not Available");
		} catch (IOException localIOException) {
			throw new ATUReporterException("Unable To Create Required Files for Custom Reports");
		}
	}

	public static void close() throws ATUReporterException {
		try {
			properties.store(new FileWriter(Directory.SETTINGSFile), "");
		} catch (FileNotFoundException localFileNotFoundException) {
		} catch (IOException localIOException) {
		} finally {
			properties = null;
		}
	}

	public static void correctErrors() throws NumberFormatException, ATUReporterException {
		int i = Integer.parseInt(get("run"));
		int j = get("passedList").split(";").length;
		int k = get("failedList").split(";").length;
		int l = get("skippedList").split(";").length;
		if (isFirstParamBig(i, j, k, l)) {
			int i1 = i - j;
			String str1 = get("passedList");
			for (int i2 = 0; i2 < i1; ++i2)
				str1 = str1 + 0 + ';';
			set("passedList", str1);
			i1 = i - k;
			String str2 = get("failedList");
			for (int i3 = 0; i3 < i1; ++i3)
				str2 = str2 + 0 + ';';
			set("failedList", str2);
			i1 = i - l;
			String str3 = get("skippedList");
			for (int i4 = 0; i4 < i1; ++i4)
				str3 = str3 + 0 + ';';
			set("skippedList", str3);
			return;
		}
		if (isFirstParamBig(j, i, k, l))
			return;
		if (isFirstParamBig(k, j, i, l))
			return;
		if (!(isFirstParamBig(l, j, k, i)))
			return;
		return;
	}

	private static boolean isFirstParamBig(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
		return ((paramInt1 > paramInt2) && (paramInt1 > paramInt3) && (paramInt1 > paramInt4));
	}

	public static String get(String paramString) throws ATUReporterException {
		open();
		String str = properties.getProperty(paramString);
		close();
		return str;
	}

	public static void set(String paramString1, String paramString2) throws ATUReporterException {
		open();
		properties.setProperty(paramString1, paramString2);
		close();
	}
}