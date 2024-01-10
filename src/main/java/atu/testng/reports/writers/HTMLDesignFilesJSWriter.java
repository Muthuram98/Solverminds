package atu.testng.reports.writers;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import atu.testng.reports.enums.Colors;
import  atu.testng.reports.enums.ReportLabels;
import  atu.testng.reports.utils.Directory;

public class HTMLDesignFilesJSWriter {
	public static int TICK_INTERVAL = 1;

	private static String reduceData(String status, int paramInt) {
		int i = 0;
		for (int j = 0; j < status.length(); j++) {
			if (status.charAt(j) == ',') {
				i++;
				if (i == paramInt) {
					status = status.substring(j + 1,
							status.length());
				}
			}
		}
		return status;
	}

	public static void pieChartJS(int passedSize, int failedSize, int skippedSize, int paramInt4) {
		try {
			PrintWriter localPrintWriter = new PrintWriter(Directory.RESULTSDir
					+ Directory.SEP + Directory.RUNName + paramInt4
					+ Directory.SEP + "pieChart.js");
			localPrintWriter
			.println("$(document).ready(function() {\n    var data = [['"
					+ ReportLabels.PASS.getLabel()
					+ "', "
					+ passedSize
					+ "], ['"
					+ ReportLabels.FAIL.getLabel()
					+ "', "
					+ failedSize
					+ "], ['"
					+ ReportLabels.SKIP.getLabel()
					+ "', "
					+ skippedSize
					+ "]];\n"
					+ "    jQuery.jqplot('chart', [data],\n"
					+ "            {seriesColors: [\""
					+ Colors.PASS.getColor()
					+ "\", \""
					+ Colors.FAIL.getColor()
					+ "\", \""
					+ Colors.SKIP.getColor()
					+ "\"],\n"
					+ "                seriesDefaults: {\n"
					+ "                    // Make this a pie chart.\n"
					+ "                    renderer: jQuery.jqplot.PieRenderer,\n"
					+ "                    rendererOptions: {\n"
					+ "                        padding: 15,\n"
					+ "                        sliceMargin: 1,\n"
					+ "                        // Put data labels on the pie slices.\n"
					+ "                        // By default, labels show the percentage of the slice.\n"
					+ "                        showDataLabels: true\n"
					+ "                    }\n"
					+ "                },\n"
					+ "                grid: {borderColor: '#cccccc', background: '#ffffff',\n"
					+ "                    borderWidth: 0, // pixel width of border around grid.\n"
					+ "                    shadow: false // draw a shadow for grid.\n"
					+ "                },\n"
					+ "                legend: {show: true, location: 'e'}\n"
					+ "            }\n" + "    );\n" + "});");
			localPrintWriter.close();
		} catch (FileNotFoundException localFileNotFoundException) {
			localFileNotFoundException.printStackTrace();
		}
	}

	public static void barChartJS(String passed, String failed, String skipped, int runCount) {
		passed = passed.substring(0, passed.lastIndexOf(';'))
				.replace(';', ',').trim();
		failed = failed.substring(0, failed.lastIndexOf(';'))
				.replace(';', ',').trim();
		skipped = skipped.substring(0, skipped.lastIndexOf(';'))
				.replace(';', ',').trim();
		int i = 0;
		if (runCount > 10) {
			i = runCount - 10;
			passed = reduceData(passed, i);
			failed = reduceData(failed, i);
			skipped = reduceData(skipped, i);
		}
		try {
			PrintWriter localPrintWriter = new PrintWriter(Directory.RESULTSDir
					+ Directory.SEP + "barChart.js");
			localPrintWriter
			.println("            $(document).ready(function(){\n                var s1 = ["
					+ passed
					+ "];\n"
					+ "                var s2 = ["
					+ failed
					+ "];\n"
					+ "                var s3 = ["
					+ skipped + "];\n");
			localPrintWriter.print("var ticks = [");
			for (int j = i + 1; j <= runCount; j++) {
				localPrintWriter.print(j);
				if (j != runCount) {
					localPrintWriter.print(",");
				}
			}
			localPrintWriter.print("];");
			localPrintWriter
			.println("    $.jqplot('bar', [s1, s2, s3], {\n        animate: true,axesDefaults:{min:0,tickInterval: "
					+ TICK_INTERVAL
					+ ""
					+ "},"
					+ "        seriesColors: [\""
					+ Colors.PASS.getColor()
					+ "\", \""
					+ Colors.FAIL.getColor()
					+ "\", \""
					+ Colors.SKIP.getColor()
					+ "\"],\n"
					+ "        stackSeries: false,\n"
					+ "        seriesDefaults: {\n"
					+ "            renderer: $.jqplot.BarRenderer,\n"
					+ "            pointLabels: {show: true}\n"
					+ "            , rendererOptions: {barWidth: 12, barMargin: 25, fillToZero: true}\n"
					+ "        }\n"
					+ "        ,\n"
					+ "        grid: {borderColor: '#ffffff', background: '#ffffff',\n"
					+ "            borderWidth: 0.5, // pixel width of border around grid.\n"
					+ "            shadow: false // draw a shadow for grid.\n"
					+ "        }\n"
					+ "        ,\n"
					+ "        legend: {\n"
					+ "            show: true,\n"
					+ "            location: 'e',\n"
					+ "            placement: 'outside',\n"
					+ "            labels: ['"
					+ ReportLabels.PASS.getLabel()
					+ "', '"
					+ ReportLabels.FAIL.getLabel()
					+ "', '"
					+ ReportLabels.SKIP.getLabel()
					+ "']\n"
					+ "        },\n"
					+ "        axes: {\n"
					+ "            xaxis: {\n"
					+ "                renderer: $.jqplot.CategoryAxisRenderer,\n"
					+ "                ticks: ticks,\n"
					+ "                label: \""
					+ ReportLabels.X_AXIS.getLabel()
					+ "\"\n"
					+ "            }\n"
					+ "            ,\n"
					+ "            yaxis: {\n"
					+ "                label: \""
					+ ReportLabels.Y_AXIS.getLabel()
					+ "\",\n"
					+ "                tickOptions: {\n"
					//+ "                    formatString: \"%dtc\"\n"
					+ "                    formatString: \"%d\"\n"
					+ "                }\n"
					+ "            }\n"
					+ "        }\n" + "    });\n" + "});");
			localPrintWriter.close();
		} catch (FileNotFoundException localFileNotFoundException) {
			localFileNotFoundException.printStackTrace();
		}
	}

	public static void lineChartJS(String passedTest, String failedTest, String skippedTest, int runCount) {
		passedTest = passedTest.substring(0, passedTest.lastIndexOf(';'))
				.replace(';', ',').trim();
		failedTest = failedTest.substring(0, failedTest.lastIndexOf(';'))
				.replace(';', ',').trim();
		skippedTest = skippedTest.substring(0, skippedTest.lastIndexOf(';'))
				.replace(';', ',').trim();

		int i = -1;
		if (runCount > 10) {
			i = runCount - 10;
		}

		try {
			PrintWriter localPrintWriter = new PrintWriter(Directory.RESULTSDir
					+ Directory.SEP + "lineChart.js");
			localPrintWriter
			.println("            $(document).ready(function(){\n                var line1 = ["
					+ passedTest
					+ "];\n"
					+ "                var line2 = ["
					+ failedTest
					+ "];\n"
					+ "                var line3 = ["
					+ skippedTest + "];\n");
			localPrintWriter.print("var ticks = [");
			/* int i = 1;
	    if (runCount == 1) {
		i = 0;
	    }*/
			for (int j = i+1; j <= runCount; j++) {
				localPrintWriter.print(j);
				if (j != runCount) {
					localPrintWriter.print(",");
				}
			}

			localPrintWriter.print("];");
			localPrintWriter
			.print("$.jqplot('line', [line1, line2, line3], {\n        animate: true,\naxesDefaults:{min:0,tickInterval: "
					+ TICK_INTERVAL
					+ ""
					+ "},"
					+ "        seriesDefaults: {\n"
					+ "            rendererOptions: {\n"
					+ "                smooth: true\n"
					+ "            }\n"
					+ "        },\n"
					+ "        series: [{lineWidth: 1.5, label: '"
					+ ReportLabels.PASS.getLabel()
					+ "'},\n"
					+ "            {lineWidth: 1.5, label: '"
					+ ReportLabels.FAIL.getLabel()
					+ "'},\n"
					+ "            {lineWidth: 1.5, label: '"
					+ ReportLabels.SKIP.getLabel()
					+ "'}],\n"
					+ "        axes: {\n"
					+ "            xaxis: {\n"
					+ "                label: \""
					+ ReportLabels.X_AXIS.getLabel()
					+ "\",\n"
					+ "                ticks: ticks,\n"
					+ "                tickOptions: {\n");
			if (runCount <= 10) {
				localPrintWriter
				.print("                    formatString: \"%'d <br> Run\"\n");
			} else {
				localPrintWriter
				.print("                    formatString: \"%'d \"\n");
			}
			localPrintWriter
			.print("                },\n                pad: 1.2,\n                rendererOptions: {\n                    tickInset: 0.3,\n                    minorTicks: 1\n                }\n            },\n            yaxis: {\n                label: \""
					+ ReportLabels.Y_AXIS.getLabel()
					+ "\"\n"
					+ "                ,tickOptions: {\n"
					//+ "                    formatString: \"%'d Tc\"\n"
					+ "                    formatString: \"%'d tc \"\n"
					+ "                },\n"
					+ "            }\n"
					+ "        },\n"
					+ "        highlighter: {\n"
					+ "            show: true,\n"
					+ "            sizeAdjust: 10,\n"
					+ "            tooltipLocation: 'n',\n"
					+ "            tooltipAxes: 'y',\n"
					+ "            tooltipFormatString: '%d :&nbsp;<b><i><span style=\"color:black;\">"
					+ ReportLabels.LINE_CHART_TOOLTIP.getLabel()
					+ "</span></i></b>',\n"
					+ "            useAxesFormatters: false\n"
					+ "        },\n"
					+ "        cursor: {\n"
					+ "            show: true\n"
					+ "        },\n"
					+ "        grid: {background: '#ffffff', drawGridLines: true, gridLineColor: '#cccccc', borderColor: '#cccccc',\n"
					+ "            borderWidth: 0.5, shadow: false},\n"
					+ "        legend: {show: true, placement: 'outside', location: 'e'},\n"
					+ "        seriesColors: [\""
					+ Colors.PASS.getColor()
					+ "\", \""
					+ Colors.FAIL.getColor()
					+ "\", \""
					+ Colors.SKIP.getColor()
					+ "\"]\n"
					+ "    });\n"
					+ "});\n" + "");
			localPrintWriter.close();
		} catch (FileNotFoundException localFileNotFoundException) {
			localFileNotFoundException.printStackTrace();
		}
	}
}
