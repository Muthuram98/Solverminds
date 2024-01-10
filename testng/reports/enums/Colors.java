/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package atu.testng.reports.enums;

public enum Colors {
	PASS, FAIL, SKIP;

	private String color;

	public String getColor() {
		return this.color;
	}

	private void setColor(String paramString) {
		this.color = paramString;
	}
}