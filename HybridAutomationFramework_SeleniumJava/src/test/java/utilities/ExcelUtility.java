package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public static FileInputStream fileInputStream;
	public static FileOutputStream fileOutputStream;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle cellStyle;
	static String path;

	public ExcelUtility(String path) {
		this.path = path;
	}

	public static int getRowCount(String sheetName) throws IOException {
		fileInputStream = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInputStream);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		fileInputStream.close();
		return rowCount;
	}

	public static int getCellCount(String sheetName, int rowNum) throws IOException {
		fileInputStream = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInputStream);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fileInputStream.close();
		return cellCount;
	}

	public static String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
		fileInputStream = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInputStream);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		DataFormatter dataFormatter = new DataFormatter();

		String data;
		try {
			// data=cell.toString();
			data = dataFormatter.formatCellValue(cell);

		} catch (Exception e) {
			data = "";
		}
		workbook.close();
		fileInputStream.close();
		return data;
	}

	public static void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
		File xlFile = new File(path);
		if (!xlFile.exists()) {
			workbook = new XSSFWorkbook();
			fileOutputStream = new FileOutputStream(path);
			workbook.write(fileOutputStream);
		}
		fileInputStream = new FileInputStream(path);
		workbook = new XSSFWorkbook(fileInputStream);

		if (workbook.getSheetIndex(sheetName) == 1)
			workbook.createSheet(sheetName);
		sheet = workbook.getSheet(sheetName);

		if (sheet.getRow(rowNum) == null)
			sheet.createRow(rowNum);
		row = sheet.getRow(rowNum);

		cell = row.createCell(colNum);
		cell.setCellValue(data);
		fileOutputStream = new FileOutputStream(path);
		workbook.write(fileOutputStream);
		workbook.close();
		fileInputStream.close();
		fileOutputStream.close();
	}

	public static void fillGreenColour(String xlFile, String xlSheet, int rowNum, int colNum) throws IOException {
		fileInputStream = new FileInputStream(xlFile);
		workbook = new XSSFWorkbook(fileInputStream);
		sheet = workbook.getSheet(xlSheet);
		row = sheet.getRow(rowNum);
		cell = row.createCell(colNum);
		cellStyle = workbook.createCellStyle();
		cellStyle.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(cellStyle);
		fileOutputStream = new FileOutputStream(xlFile);
		workbook.write(fileOutputStream);
		workbook.close();
		fileInputStream.close();
		fileOutputStream.close();
	}

	public static void fillRedColour(String xlFile, String xlSheet, int rowNum, int colNum) throws IOException {
		fileInputStream = new FileInputStream(xlFile);
		workbook = new XSSFWorkbook(fileInputStream);
		sheet = workbook.getSheet(xlSheet);
		row = sheet.getRow(rowNum);
		cell = row.createCell(colNum);
		cellStyle = workbook.createCellStyle();
		cellStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(cellStyle);
		fileOutputStream = new FileOutputStream(xlFile);
		workbook.write(fileOutputStream);
		workbook.close();
		fileInputStream.close();
		fileOutputStream.close();
	}


}