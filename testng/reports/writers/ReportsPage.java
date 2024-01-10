/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package atu.testng.reports.writers;

import java.io.PrintWriter;

public class ReportsPage {
	public static void footer(PrintWriter paramPrintWriter) {
		paramPrintWriter
				.println("            <tr id=\"footer\">\n                <td colspan=\"2\">\n                    Best Viewed in &nbsp;\n                    <a href=\"http://www.mozilla.org/en-US/firefox/new/\">Firefox</a> &nbsp;\n                    <a href=\"http://www.apple.com/in/safari/\">Safari</a>&nbsp;\n                    <a href=\"http://www.google.com/chrome/\">Chrome</a>&nbsp;\n                    <a href=\"http://windows.microsoft.com/en-IN/internet-explorer/download-ie\">IE 9 & Above</a>&nbsp;\n                    &nbsp;\nATU Reporter Version: v5.1.1                    &nbsp;\n                    Reports by: <a href=\"http://automationtestingutilities.blogspot.in/\">Automation Testing Utilities</a>\n                </td>\n            </tr>\n        </table>\n    </body>\n</html>");
	}
}