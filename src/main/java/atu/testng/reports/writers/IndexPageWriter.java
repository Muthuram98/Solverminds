package atu.testng.reports.writers;

import atu.testng.reports.enums.ReportLabels;
import java.io.PrintWriter;

public class IndexPageWriter extends ReportsPage {
	public static void header(PrintWriter paramPrintWriter) {
		paramPrintWriter.println(
				"<!DOCTYPE html>\n\n<html>\n    <head>\n        <title>Execution Summary</title>\n\n        <link rel=\"stylesheet\" type=\"text/css\" href=\"HTML_Design_Files/CSS/design.css\" />\n\n    </head>\n    <body>\n        <table id=\"mainTable\">\n            <tr id=\"header\" >\n                <td id=\"logo\"><img src=\"HTML_Design_Files/IMG/TrackDfect.jpg"
						+ "\" alt=\"Logo\" height=\"70\" width=\"140\" /> " + "<br/>"
						+ "</td>\n" + "                <td id=\"headertext\">\n"
						+ ReportLabels.HEADER_TEXT.getLabel() + "\n"
						+ "<div style=\"padding-right:20px;float:right\"><img src=\"HTML_Design_Files/IMG/"
						+ ReportLabels.PROJ_LOGO.getLabel()+ "\" height=\"70\" width=\"140\" /> </i></div>"
						+ "                </td>\n" + "            </tr>\n" + "\n"
						+ "            <tr id=\"container\">\n" + "                <td id=\"menu\">\n" + "\n"
						+ "                    <ul>\n"
						+ "                        <li class=\"menuStyle\"><a href=\"Results/ConsolidatedPage.html\" >Consolidated Reports</a></li>                        \n"
						+ "                    </ul>\n" + "                </td>");
	}

	public static void content(PrintWriter paramPrintWriter, String paramString) {
		paramPrintWriter.println("<td id=\"content\">\n");
		paramPrintWriter.print(paramString);
		paramPrintWriter.println("                </td>\n            </tr>");
	}
}