/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package atu.testng.reports.enums;

public enum ReportLabels {
	HEADER_TEXT, PASS, FAIL, SKIP, X_AXIS, Y_AXIS, LINE_CHART_TOOLTIP, ATU_LOGO, ATU_CAPTION, PROJ_LOGO, PROJ_CAPTION, PIE_CHART_LABEL, TC_INFO_LABEL;

	private String label;

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String paramString) {
		this.label = paramString;
	}
}