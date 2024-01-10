package atu.testng.reports.writers;

import atu.testng.reports.enums.Colors;
import atu.testng.reports.enums.ExceptionDetails;
import atu.testng.reports.enums.ReportLabels;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.AuthorDetails;
import atu.testng.reports.utils.Directory;
import atu.testng.reports.utils.Platform;
import atu.testng.reports.utils.Steps;
import atu.testng.reports.utils.Utils1;

import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

//import org.omg.CORBA.LocalObject;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;

import commonMethods.Testcases;
import commonMethods.Utils;
import atu.testng.reports.writers.*;

public class TestCaseReportsPageWriter extends ReportsPage {
	
	Testcases test1=new Testcases();
	Platform plat=new Platform();
	public static void header(PrintWriter paramPrintWriter, ITestResult paramITestResult) {
		paramPrintWriter.println(
				"<!DOCTYPE html>\n\n<html>\n    <head>\n        <title>Pie Charts</title>\n\n        <link rel=\"stylesheet\" type=\"text/css\" href=\"../"
						+ getTestCaseHTMLPath(paramITestResult) + "HTML_Design_Files/CSS/design.css\" />\n"
						+ "        <link rel=\"stylesheet\" type=\"text/css\" href=\"../"
						+ getTestCaseHTMLPath(paramITestResult) + "HTML_Design_Files/CSS/jquery.jqplot.css\" />\n"
						+ "\n" + "        <script type=\"text/javascript\" src=\"../"
						+ getTestCaseHTMLPath(paramITestResult) + "HTML_Design_Files/JS/jquery.min.js\"></script>\n"
						+ "        <script type=\"text/javascript\" src=\"../" + getTestCaseHTMLPath(paramITestResult)
						+ "HTML_Design_Files/JS/jquery.jqplot.min.js\"></script>\n" + "        <!--[if lt IE 9]>\n"
						+ "        <script language=\"javascript\" type=\"text/javascript\" src=\"../"
						+ getTestCaseHTMLPath(paramITestResult) + "HTML_Design_Files/JS/excanvas.js\"></script>\n"
						+ "        <![endif]-->\n" + "\n"
						+ "        <script language=\"javascript\" type=\"text/javascript\" src=\"../"
						+ getTestCaseHTMLPath(paramITestResult)
						+ "HTML_Design_Files/JS/jqplot.pieRenderer.min.js\"></script>\n"
						+ "        <script language=\"javascript\" type=\"text/javascript\">"
						+ "$(document).ready(function() {" + " $(\".exception\").hide();" + " $(\"#showmenu\").show();"
						+ " $('#showmenu').click(function() {" + " $('.exception').toggle(\"slide\");" + " });" + " });"
						+ "        </script>" + "    </head>\n" + "    <body>\n" + "        <table id=\"mainTable\">\n"
						+ "            <tr id=\"header\" >\n" + "                <td id=\"logo\">" + "<img src=\"../"
						+ getTestCaseHTMLPath(paramITestResult) + "HTML_Design_Files/IMG/TrackDfect.jpg"
						+"\" alt=\"Logo\" height=\"80\" width=\"140\" /> " + "<br/>"
						+ "</td>\n" + "                <td id=\"headertext\">\n"
						+ "           " + ReportLabels.HEADER_TEXT.getLabel() + "         \n"
						+ "<div style=\"padding-right:20px;float:right\"><img src=\"../"
						+ getTestCaseHTMLPath(paramITestResult) + "HTML_Design_Files/IMG/"
						+ ReportLabels.PROJ_LOGO.getLabel() + "\" height=\"70\" width=\"140\" /> </i></div>"
						+ "                </td>\n" + "            </tr>");
	}

	public static String getExecutionTime(ITestResult paramITestResult) {
		long l = paramITestResult.getEndMillis() - paramITestResult.getStartMillis();
		if (l > 1000L) {
			l /= 1000L;
			return l + " Sec";
		}
		return l + " Milli Sec";
	}

	private static String getExceptionDetails(ITestResult paramITestResult) {
		try {
			paramITestResult.getThrowable().toString();
		} catch (Throwable localThrowable) {
			return "";
		}
		String str1 = paramITestResult.getThrowable().toString();
		String str2 = str1;
		if (str1.contains(":"))
			str2 = str1.substring(0, str1.indexOf(":")).trim();
		else
			str1 = "";
		try {
			str2 = getExceptionClassName(str2, str1);
			if (str2.equals("Assertion Error")) {
				if (str1.contains(">")) {
					str2 = str2 + str1.substring(str1.indexOf(":"), str1.lastIndexOf(">") + 1).replace(">", "\"");
					str2 = str2.replace("<", "\"");
				}
				if (paramITestResult.getThrowable().getMessage().trim().length() > 0)
					str2 = paramITestResult.getThrowable().getMessage().trim();
			} else if (str1.contains("{")) {
				str2 = str2 + str1.substring(str1.indexOf("{"), str1.lastIndexOf("}"));
				str2 = str2.replace("{\"method\":", " With ").replace(",\"selector\":", " = ");
			} else if ((str2.equals("Unable to connect Browser")) && (str1.contains("."))) {
				str2 = str2 + ": Browser is Closed";
			} else if (str2.equals("WebDriver Exception")) {
				str2 = paramITestResult.getThrowable().getMessage();
			}
		} catch (ClassNotFoundException localClassNotFoundException) {
		} catch (Exception localException) {
		}
		str2 = str2.replace(">", "\"");
		str2 = str2.replace("<", "\"");
		return str2;
	}

	private static String getExceptionClassName(String paramString1, String paramString2)
			throws ClassNotFoundException {
		String str = "";
		try {
			str = ExceptionDetails.valueOf(paramString1.trim().replace(".", "_")).getExceptionInfo();
		} catch (Exception localException) {
			str = paramString1;
		}
		return str;
	}

	public static String getReqCoverageInfo(ITestResult paramITestResult) {
		try {
			if (paramITestResult.getAttribute("reqCoverage") == null)
				return ReportLabels.TC_INFO_LABEL.getLabel();
			return paramITestResult.getAttribute("reqCoverage").toString();
		} catch (Exception localException) {
		}
		return ReportLabels.TC_INFO_LABEL.getLabel();
	}

	public void content(PrintWriter paramPrintWriter, ITestResult paramITestResult, int paramInt)
  {
		 Object currentClass = paramITestResult.getInstance();
				 
				  String browserName= ((Testcases) currentClass).browser;        
    paramPrintWriter.println("<td id=\"content\">   \n                    <div class=\"info\">\n       TestCase Name: <b>" + paramITestResult.getName() + "  :  Iteration " + paramITestResult.getAttribute("iteration").toString() + "</b><br/>" + "                        Time Taken for Executing: <b>" + getExecutionTime(paramITestResult) + "</b> <br/>\n" + "                        Current Run Number: <b>Run " + paramInt + "</b></br>\n"   + "                    </div>");
   // paramPrintWriter.println("<div class=\"info\"><br/><b>Requirement Coverage/ TestCase Description</b><br/><br/>" + getReqCoverageInfo(paramITestResult) + "</div>");
    paramPrintWriter.println("<div class=\"chartStyle summary\" style=\"background-color:#dbae0f;width: 30%; height: 200px;\">          \n                        <b>Execution Platform Details</b><br/><br/>\n                        <table>\n                            <tr>\n                                <td>O.S</td>\n                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n                                <td>" + Platform.OS + ", " + Platform.OS_ARCH + "Bit, v" + Platform.OS_VERSION + "</td>\n" + "                            </tr>\n" + "                            <tr>\n" + "                                <td>Java</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + Platform.JAVA_VERSION + "</td>\n" + "                            </tr>\n" + "\n" + "                            <tr>\n" + "                                <td>Hostname</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + Platform.getHostName() + "</td>\n" + "                            </tr>\n" + "\n" + "                            <tr>\n" + "                                <td>Appium</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + Platform.DRIVER_VERSION + "</td>\n" + "                            </tr>\n" + "                        </table>  \n" + "                    </div>\n" + "                   ");
    paramPrintWriter.println(" <div class=\"chartStyle summary\" style=\"background-color:#03b78a; margin-left: 40px; height: 200px;width: 30%; \">\n" + "                        <b>Summary</b><br/><br/>\n" + "                        <table>\n" + "                            <tr>\n" + "                                <td>Status</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + getResult(paramITestResult) + "</td>\n" + "                            </tr>\n" + "                            <tr>\n" + "                                <td>Execution Date</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + Utils1.getCurrentTime() + "</td>\n" + "                            </tr>\n" + "                            \n" + "\n" + "                            <tr>\n" + "                                <td>Browser</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" +browserName+ "</td>\n" + "                            </tr>\n" + "                        </table> \n" + "                    </div>");
    AuthorDetails localAuthorDetails = null;
    try
    {
      if (paramITestResult.getAttribute("authorInfo") == null)
        localAuthorDetails = new AuthorDetails();
      else
        localAuthorDetails = (AuthorDetails)paramITestResult.getAttribute("authorInfo");
    }
    catch (Exception localException1)
    {
    }
    //paramPrintWriter.println(" <div class=\"chartStyle summary\" style=\"background-color:#041fa3;margin-left: 20px; height: 200px;width: 30%; \">\n                       <b>Author Info</b><br/><br/>\n                        <table>\n                            <tr>\n                                <td>Author Name</td>\n                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n                                <td>" + localAuthorDetails.getAuthorName() + "</td>                                \n" + "                            </tr>\n" + "                            <tr>\n" + "                                <td>Creation Date</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + localAuthorDetails.getCreationDate() + "</td>\n" + "                            </tr>\n" + "                            <tr>\n" + "                                <td>Version</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + localAuthorDetails.getVersion() + "</td>\n" + "                            </tr>\n" + "                            <tr>\n" + "                                <td>System User</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + Platform.USER + "</td>\n" + "                            </tr>\n" + "                        </table> \n" + "                    </div>\n" + "                    ");
    Object localObject1;
    int i;
    Object localObject2 = null;
    if (paramITestResult.getStatus() != 3)
    {
      localObject1 = Reporter.getOutput(paramITestResult);
      paramPrintWriter.println("   <div>\n                        <table class=\"chartStyle\" id=\"tableStyle\" style=\"height:50px; float: left\">\n                            <tr>\n                                <th>S.No</th>\n                                <th>Step Description</th>\n                                <th>Input Value</th>\n                    <th>Actual Value</th>\n                   <th>Time</th>\n                                <th>Line No</th>\n                                <th>Status</th>\n                                <th>Screen shot</th>\n                            </tr>\n                           \n");
      i = 1;
      if (Reporter.getOutput(paramITestResult).size() <= 0)
      {
        paramPrintWriter.print("<tr>");
        paramPrintWriter.print("<td colspan=\"8\"><b>No Steps Available</b></td>");
        paramPrintWriter.print("</tr>");
      }
      i = 1;
      Iterator<?> localIterator = ((List<?>)localObject1).iterator();
      while (localIterator.hasNext())
      {
        localObject2 = (String)localIterator.next();
        Steps localSteps = null;
        localSteps = (Steps)paramITestResult.getAttribute((String)localObject2);
        if (localSteps == null)
        {
          paramPrintWriter.print("<tr>");
          paramPrintWriter.println("<td>" + i + "</td>");
          paramPrintWriter.print("<td style=\"text-align:left\" colspan=\"8\">" + ((String)localObject2) + "</td></tr>");
          ++i;
        }
        paramPrintWriter.print("<tr>");
        paramPrintWriter.println("<td>" + i + "</td>");
        paramPrintWriter.println("<td>" + localSteps.getDescription() + "</td>");
        paramPrintWriter.println("<td>" + localSteps.getInputValue() + "</td>");
       // paramPrintWriter.println("<td>" + localSteps.getExpectedValue() + "</td>");
        paramPrintWriter.println("<td>" + localSteps.getActualValue() + "</td>");
        paramPrintWriter.println("<td>" + localSteps.getTime() + "</td>");
        paramPrintWriter.println("<td>" + localSteps.getLineNum() + "</td>");
        paramPrintWriter.println("<td>" + getLogDescription(localSteps.getLogAs(), paramITestResult) + "</td>");
        try
        {
          Integer.parseInt(localSteps.getScreenShot().trim());
          paramPrintWriter.println("<td><a href=\"img/" + i + ".PNG" + "\"><img alt=\"No Screenshot\" src=\"img/" + i + ".PNG" + "\"/></a></td>");
        }
        catch (Exception localException3)
        {
          paramPrintWriter.println("<td></td>");
        }
        paramPrintWriter.print("</tr>");
        ++i;
      }
      paramPrintWriter.print("\n                        </table>  \n");
    }
    if ((paramITestResult.getParameters().length > 0) || (paramITestResult.getStatus() == 3) || (paramITestResult.getStatus() == 2))
    {
      paramPrintWriter.println(" <div class=\"chartStyle summary\" style=\"color: black;width: 98%; height: 100%; padding-bottom: 30px;\">          \n");
      if (paramITestResult.getParameters().length > 0)
      {
        paramPrintWriter.print("<b>Parameters: </b><br/>");
        for (Object LocalObject : paramITestResult.getParameters())
          paramPrintWriter.print("Param: " + localObject2.toString() + "<br/>");
      }
      if (paramITestResult.getStatus() == 3)
      {
        paramPrintWriter.print("<br/><br/>");
        paramPrintWriter.println("                      <b>Reason for Skipping</b><br/><br/>\n");
        localObject1 = paramITestResult.getMethod().getGroupsDependedUpon();
        String[] arrayOfString = paramITestResult.getMethod().getMethodsDependedUpon();
        String str1;
        String str2;
       /* if (localObject1.length > 0)
        {
          str1 = "";
          for (str2 : localObject1)
            str1 = str1 + str2 + "<br/>";
          paramPrintWriter.print("<b>Depends on Groups: </b><br/>" + str1);
        }
        if (arrayOfString.length > 0)
        {
          str1 = "";
          for (str2 : arrayOfString)
            str1 = str1 + str2 + "<br/>";
          paramPrintWriter.print("<b>Depends on Methods: </b><br/>" + str1 + "<br/><br/>");*/
        //}
        paramPrintWriter.print("                    </div>");
      }
      if (paramITestResult.getStatus() == 2)
      {
        paramPrintWriter.println("                        <br/><br/><b>Reason for Failure:&nbsp;&nbsp;</b>" + getExceptionDetails(paramITestResult) + "<br/><br/>\n" + "<b id=\"showmenu\">Click Me to Show/Hide the Full Stack Trace</b>" + "<div class=\"exception\">");
        try
        {
          paramITestResult.getThrowable().printStackTrace(paramPrintWriter);
        }
        catch (Exception localException2)
        {
        }
        paramPrintWriter.print("</div>");
      }
      paramPrintWriter.print("                    </div>");
    }
    paramPrintWriter.print("                    </div>\n\n                </td>\n            </tr>");
  }

	public static String getLogDescription(LogAs logas, ITestResult itestresult)  {
		switch(logas)
		{
		case PASSED:
			return (new StringBuilder()).append("<img style=\"height:20px;width:20px;border:none\"  alt=\"PASS\" src=\"../").append(getTestCaseHTMLPath(itestresult)).append("/HTML_Design_Files/IMG/logpass.png\" />").toString();

		case FAILED:
			return (new StringBuilder()).append("<img style=\"height:18px;width:18px;border:none\"  alt=\"FAIL\" src=\"../").append(getTestCaseHTMLPath(itestresult)).append("/HTML_Design_Files/IMG/logfail.png\" />").toString();

		case INFO:
			return (new StringBuilder()).append("<img style=\"height:20px;width:20px;border:none\" alt=\"INFO\" src=\"../").append(getTestCaseHTMLPath(itestresult)).append("/HTML_Design_Files/IMG/loginfo.png\" />").toString();

		case WARNING:
			return (new StringBuilder()).append("<img style=\"height:20px;width:20px;border:none\"  alt=\"WARNING\" src=\"../").append(getTestCaseHTMLPath(itestresult)).append("/HTML_Design_Files/IMG/logwarning.png\" />").toString();
		}
		return "img";
	}

/*	public static String getSkippedDescription(ITestResult paramITestResult)
  {
    String[] arrayOfString1 = paramITestResult.getMethod().getGroupsDependedUpon();
    String[] arrayOfString2 = paramITestResult.getMethod().getMethodsDependedUpon();
    String str1 = "";
    for (String str2 : arrayOfString1)
      str1 = str1 + str2;
    String ??? = "";
    for (String str3 : arrayOfString2)
      ??? = ((String)???) + str3;
    return ((String)"");
	return str1;
  }*/

	private static String getBrowserName(ITestResult paramITestResult) {
		try {
			return paramITestResult.getAttribute(Platform.BROWSER_NAME_PROP).toString();
		} catch (Exception localException) {
		}
		return "";
	}

	private static String getBrowserVersion(ITestResult paramITestResult) {
		try {
			return paramITestResult.getAttribute(Platform.BROWSER_VERSION_PROP).toString();
		} catch (Exception localException) {
		}
		return "";
	}

	private static String getColorBasedOnResult(ITestResult paramITestResult) {
		if (paramITestResult.getStatus() == 1)
			return Colors.PASS.getColor();
		if (paramITestResult.getStatus() == 2)
			return Colors.FAIL.getColor();
		if (paramITestResult.getStatus() == 3)
			return Colors.SKIP.getColor();
		return Colors.PASS.getColor();
	}

	private static String getResult(ITestResult paramITestResult) {
		if (paramITestResult.getStatus() == 1)
			try {
				if (paramITestResult.getAttribute("passedButFailed").equals("passedButFailed"))
					return "Failed";
				return "Passed";
			} catch (Exception localException) {
				return "Passed";
			}
		if (paramITestResult.getStatus() == 2)
			return "Failed";
		if (paramITestResult.getStatus() == 3)
			return "Skipped";
		return "Unknown";
	}

	public static String getTestCaseHTMLPath(ITestResult paramITestResult) {
		String str = paramITestResult.getAttribute("reportDir").toString();
		str = str.substring(str.indexOf(Directory.RESULTSDir) + Directory.RESULTSDir.length() + 1);
		String[] arrayOfString = str.replace(Directory.SEP, "##@##@##").split("##@##@##");
		str = "";
		for (int i = 0; i < arrayOfString.length; ++i)
			str = str + "../";
		return str;
	}

	public static void menuLink(PrintWriter paramPrintWriter, ITestResult paramITestResult, int paramInt) {
		getTestCaseHTMLPath(paramITestResult);
		paramPrintWriter.println(
				"\n            <tr id=\"container\">\n                <td id=\"menu\">\n                    <ul> \n");
		paramPrintWriter.println(" <li class=\"menuStyle\"><a href=\"../" + getTestCaseHTMLPath(paramITestResult)
				+ "index.html\" >Index</a></li>" + "<li style=\"padding-top: 4px;\"><a href=\""
				+ getTestCaseHTMLPath(paramITestResult) + "ConsolidatedPage.html\" >Consolidated Page</a></li>\n");
		if (paramInt == 1)
			paramPrintWriter.println("\n <li class=\"menuStyle\"><a href=\"" + Directory.RUNName + paramInt
					+ Directory.SEP + "CurrentRun.html\" >" + "Run " + paramInt + " </a></li>\n");
		else
			for (int i = 1; i <= paramInt; ++i) {
				if (i == paramInt) {
					paramPrintWriter.println(
							"\n <li style=\"padding-top: 4px;padding-bottom: 4px;\"><a href=\"" + Directory.RUNName + i
									+ Directory.SEP + "CurrentRun.html\" >" + "Run " + i + " </a></li>\n");
					break;
				}
				paramPrintWriter.println("\n <li class=\"menuStyle\"><a href=\"" + Directory.RUNName + i + Directory.SEP
						+ "CurrentRun.html\" >" + "Run " + i + " </a></li>\n");
			}
		paramPrintWriter.println("\n                    </ul>\n                </td>\n\n");
	}
}