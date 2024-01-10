/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package atu.testng.reports.excel;

import atu.testng.reports.enums.ReportLabels;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.ITestResult;

public class ExcelStyler {
	public static final short RESULT_PASS_COLOR = IndexedColors.GREEN
			.getIndex();
	public static final short RESULT_FAIL_COLOR = IndexedColors.RED.getIndex();
	public static final short RESULT_SKIP_COLOR = IndexedColors.ROYAL_BLUE
			.getIndex();
	public static final short FONT_COLOR = IndexedColors.WHITE.getIndex();
	public static final short HEADER_BG_COLOR = IndexedColors.GREY_50_PERCENT
			.getIndex();
	public static final short FOOTER_BG_COLOR = IndexedColors.DARK_TEAL
			.getIndex();
	public static final short HEADER_HEIGHT = 420;
	public static final short SHEET_COLOR = IndexedColors.DARK_TEAL.getIndex();

	public static void setSheetTabColor(Sheet paramSheet,
			ITestResult paramITestResult) {
		if (paramITestResult.getStatus() == 2)
			((XSSFSheet) paramSheet).setTabColor(IndexedColors.RED.index);
		else if (paramITestResult.getStatus() == 1)
			((XSSFSheet) paramSheet).setTabColor(IndexedColors.GREEN.index);
		else
			((XSSFSheet) paramSheet).setTabColor(IndexedColors.BLUE.index);
	}

	public static void setSheetTabColor(Sheet paramSheet) {
		((XSSFSheet) paramSheet).setTabColor(SHEET_COLOR);
	}

	public static void setBorderLine(Workbook paramWorkbook, Row paramRow) {
		for (int i = 0; i < 8; ++i) {
			Cell localCell = paramRow.getCell(i);
			CellStyle localCellStyle = paramWorkbook.createCellStyle();
			if ((i == 6) || (i == 7))
				localCellStyle = localCell.getCellStyle();
			localCellStyle.setBorderBottom(1);
			localCellStyle.setBottomBorderColor(HEADER_BG_COLOR);
			localCell.setCellStyle(localCellStyle);
		}
	}

	public static CellStyle setHeaderCellStyle(Workbook paramWorkbook,
			Cell paramCell) {
		Font localFont = paramWorkbook.createFont();
		localFont.setColor(FONT_COLOR);
		CellStyle localCellStyle = paramWorkbook.createCellStyle();
		localCellStyle.setFillForegroundColor(HEADER_BG_COLOR);
		localCellStyle.setFillPattern(1);
		localCellStyle.setBorderRight(1);
		localCellStyle.setRightBorderColor(IndexedColors.WHITE.getIndex());
		localCellStyle.setFont(localFont);
		localCellStyle.setAlignment(2);
		localCellStyle.setVerticalAlignment(1);
		paramCell.setCellStyle(localCellStyle);
		return localCellStyle;
	}

	static CellStyle setColor(Workbook paramWorkbook, short paramShort1,
			short paramShort2) {
		Font localFont = paramWorkbook.createFont();
		localFont.setColor(paramShort2);
		CellStyle localCellStyle = paramWorkbook.createCellStyle();
		localCellStyle.setFillForegroundColor(paramShort1);
		localCellStyle.setFillPattern(1);
		localCellStyle.setBorderRight(1);
		localCellStyle.setRightBorderColor(IndexedColors.WHITE.getIndex());
		localCellStyle.setFont(localFont);
		localCellStyle.setAlignment(2);
		return localCellStyle;
	}

	static void setResultCellStyle(Workbook paramWorkbook, Cell paramCell,
			int paramInt) {
		if (paramInt == 2) {
			paramCell.setCellStyle(setColor(paramWorkbook, RESULT_FAIL_COLOR,
					FONT_COLOR));
		} else if (paramInt == 1) {
			paramCell.setCellStyle(setColor(paramWorkbook, RESULT_PASS_COLOR,
					FONT_COLOR));
		} else {
			if (paramInt != 3)
				return;
			paramCell.setCellStyle(setColor(paramWorkbook, RESULT_SKIP_COLOR,
					FONT_COLOR));
		}
	}

	static void setResultCellStyle(Workbook paramWorkbook, Cell paramCell,
			ITestResult paramITestResult) {
		if (paramITestResult.getStatus() == 2) {
			paramCell.setCellValue(ReportLabels.FAIL.getLabel());
			paramCell.setCellStyle(setColor(paramWorkbook, RESULT_FAIL_COLOR,
					FONT_COLOR));
		} else if (paramITestResult.getStatus() == 1) {
			paramCell.setCellValue(ReportLabels.PASS.getLabel());
			paramCell.setCellStyle(setColor(paramWorkbook, RESULT_PASS_COLOR,
					FONT_COLOR));
		} else {
			if (paramITestResult.getStatus() != 3)
				return;
			paramCell.setCellValue(ReportLabels.SKIP.getLabel());
			paramCell.setCellStyle(setColor(paramWorkbook, RESULT_SKIP_COLOR,
					FONT_COLOR));
		}
	}
}