package atu.testng.reports.utils;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import commonMethods.Utils;

public class Utils1 {
	public static String testExecutionFile=System.getProperty("user.dir") + Utils.getDataFromTestConfig("TestExeFile");
	public static String getCurrentTime() {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
		Date localDate = new Date();
		return localSimpleDateFormat.format(localDate);
	}
	
	public static String getTestcaseCount(String component)
	{
		String requiredCellVal = "";
		try {
			FileInputStream fis = new FileInputStream(testExecutionFile);
			@SuppressWarnings("resource")
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet ws = wb.getSheet("Sheet1");

			int rowNum = ws.getLastRowNum() + 1;

			java.util.Iterator<Row> rowIterator = ws.rowIterator();
            int numberOfCells = 0;
            if (rowIterator.hasNext())
            {
                Row headerRow = (Row) rowIterator.next();
                //get the number of cells in the header row
                numberOfCells = headerRow.getLastCellNum();
            }
            
			for (int index = 0; index < rowNum; index++) 
			{
				XSSFRow row = ws.getRow(index);
				
				for(int cellIndex=0; cellIndex<numberOfCells; cellIndex++)
				{
					XSSFCell cell = row.getCell(cellIndex);
					String cellVal = cellToString(cell);
					if (cellVal.equals(component)) 
					{
						//XSSFCell adjacentCell = row.getCell(cellIndex -3);
						XSSFCell adjacentCell = row.getCell(cellIndex -1);
						String adjacentCellVal = cellToString(adjacentCell);
						requiredCellVal = adjacentCellVal;
						break;
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return requiredCellVal;
	}

	public static String getPackageName(String component)
	{
		String requiredCellVal = "";
		try {
			FileInputStream fis = new FileInputStream(testExecutionFile);
			@SuppressWarnings("resource")
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet ws = wb.getSheet("Sheet1");

			int rowNum = ws.getLastRowNum() + 1;

			java.util.Iterator<Row> rowIterator = ws.rowIterator();
            int numberOfCells = 0;
            if (rowIterator.hasNext())
            {
                Row headerRow = (Row) rowIterator.next();
                //get the number of cells in the header row
                numberOfCells = headerRow.getLastCellNum();
            }
            
			for (int index = 0; index < rowNum; index++) 
			{
				XSSFRow row = ws.getRow(index);
				
				for(int cellIndex=0; cellIndex<numberOfCells; cellIndex++)
				{
					XSSFCell cell = row.getCell(cellIndex);
					String cellVal = cellToString(cell);
					if (cellVal.equals(component)) 
					{
						//XSSFCell adjacentCell = row.getCell(cellIndex -2);
						XSSFCell adjacentCell = row.getCell(cellIndex -3);
						String adjacentCellVal = cellToString(adjacentCell);
						requiredCellVal = adjacentCellVal;
						break;
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return requiredCellVal;
	}
	public static String getClassName(String component)
	{
		String requiredCellVal = "";
		try {
			FileInputStream fis = new FileInputStream(testExecutionFile);
			try (XSSFWorkbook wb = new XSSFWorkbook(fis)) {
				XSSFSheet ws = wb.getSheet("Sheet1");

				int rowNum = ws.getLastRowNum() + 1;

				java.util.Iterator<Row> rowIterator = ws.rowIterator();
				int numberOfCells = 0;
				if (rowIterator.hasNext())
				{
				    Row headerRow = (Row) rowIterator.next();
				    //get the number of cells in the header row
				    numberOfCells = headerRow.getLastCellNum();
				}
				
				for (int index = 0; index < rowNum; index++) 
				{
					XSSFRow row = ws.getRow(index);
					
					for(int cellIndex=0; cellIndex<numberOfCells; cellIndex++)
					{
						XSSFCell cell = row.getCell(cellIndex);
						String cellVal = cellToString(cell);
						if (cellVal.equals(component)) 
						{
							//XSSFCell adjacentCell = row.getCell(cellIndex -1);
							XSSFCell adjacentCell = row.getCell(cellIndex -2);
							String adjacentCellVal = cellToString(adjacentCell);
							requiredCellVal = adjacentCellVal;
							break;
						}
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return requiredCellVal;
	}

	
	@SuppressWarnings("deprecation")
	public static String cellToString(XSSFCell cell) 
	{
			int type;
			String result;
			type = cell.getCellType();

			switch (type) {

			case Cell.CELL_TYPE_NUMERIC: // numeric value in Excel
				result = "" + cell.getNumericCellValue();
				break;
			case Cell.CELL_TYPE_FORMULA: // precomputed value based on formula
				result = "" + cell.getNumericCellValue();
				break;
			case Cell.CELL_TYPE_STRING: // String Value in Excel
				result = "" + cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BLANK:
				result = "";
				break;
			case Cell.CELL_TYPE_BOOLEAN: // boolean value
				result = "" + cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_ERROR:
				result = "Error";
			default:
				throw new RuntimeException(
						"There is no support for this type of cell");
			}

			return result.toString();
	}
}