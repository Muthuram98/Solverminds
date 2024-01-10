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