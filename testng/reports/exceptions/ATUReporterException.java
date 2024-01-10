/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package atu.testng.reports.exceptions;

public class ATUReporterException extends Exception {
	private String message;

	public ATUReporterException() {
	}

	public ATUReporterException(String paramString) {
		this.message = paramString;
	}

	public String toString() {
		return "[ATU Custom Reporter Exception] " + this.message;
	}
}