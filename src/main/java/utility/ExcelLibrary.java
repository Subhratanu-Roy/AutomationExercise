package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelLibrary {

	FileInputStream fis = null;
	XSSFWorkbook wb = null;
	XSSFSheet sheet = null;
	XSSFRow row = null;
	XSSFCell cell = null;
	FileOutputStream fos = null;
	String path = null;

	public ExcelLibrary(String path) {
		try {
			this.path = path;
			fis = new FileInputStream(new File(path));
			try {
				wb = new XSSFWorkbook(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getCellData(String sheetname, int rowno, int colno) {

		int index = wb.getSheetIndex(sheetname);
		if (index == -1) {
			System.out.println("no sheet found");
			return "";
		}
		if (rowno <= 0) {
			System.out.println("invalid row number");
			return "";
		}
		sheet = wb.getSheet(sheetname);
		row = sheet.getRow(rowno - 1);
		if (row == null) {
			System.out.println("No row present at position " + rowno);
			return "";
		}
		cell = row.getCell(colno - 1);
		if (cell == null) {
			System.out.println("No column present at position " + colno);
			return "";
		}

		if (cell.getCellType() == CellType.STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == CellType.NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else if (cell.getCellType() == CellType.BLANK) {
			return "";
		} else
			return "";

	}

	public String getCellData(String sheetname, int rowno, String colname) {
		int index = wb.getSheetIndex(sheetname);
		if (index == -1) {
			System.out.println("no sheet found with name" + sheetname);
			return "";
		}
		if (rowno <= 0) {
			System.out.println("invalid rowno");
			return "";
		}
		sheet = wb.getSheet(sheetname);
		row = sheet.getRow(0);
		if (row == null) {
			System.out.println("no row present in the sheet");
			return "";
		}
		sheet = wb.getSheet(sheetname);
		int colno = -1;
		int totalNoOfCols = row.getLastCellNum();
		for (int i = 0; i < totalNoOfCols; i++) {
			if (row.getCell(i).getStringCellValue().trim().equals(colname.trim())) {
				colno = i;
				System.out.println("column found at " + String.valueOf(colno + 1));
				break;
			}
		}
		if (colno == -1) {
			System.out.println("no column found with name " + colname);
			return "";
		}
		row = sheet.getRow(rowno - 1);
		if (row == null) {
			System.out.println("No row present at position " + rowno);
			return "";

		}
		cell = row.getCell(colno);
		if (cell == null) {
			System.out.println("No column found at " + colno + 1);
			return "";
		}
		if (cell.getCellType() == CellType.STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == CellType.NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else if (cell.getCellType() == CellType.BLANK) {
			return "";
		} else
			return "";

	}

	public boolean setCellData(String sheetname, int rowno, String colname, String value) {
		int index = wb.getSheetIndex(sheetname);
		if (index == -1) {
			System.out.println("no sheet found with name" + sheetname);
			return false;
		}
		if (rowno <= 0) {
			System.out.println("invalid rowno");
			return false;
		}
		sheet = wb.getSheet(sheetname);
		row = sheet.getRow(0);
		if (row == null) {
			System.out.println("no row present in the sheet");
			return false;
		}
		sheet = wb.getSheet(sheetname);
		int colno = -1;
		int totalNoOfCols = row.getLastCellNum();
		for (int i = 0; i < totalNoOfCols; i++) {
			if (row.getCell(i).getStringCellValue().trim().equals(colname.trim())) {
				colno = i;
				System.out.println("column found at " + String.valueOf(colno + 1));
				break;
			}
		}
		if (colno == -1) {
			System.out.println("no column found with name " + colname);
			return false;
		}
		row = sheet.getRow(rowno - 1);
		if (row == null) {
			row = sheet.createRow(rowno - 1);
			System.out.println("First row is created");

		}
		cell = row.getCell(colno);
		if (cell == null) {
			cell = row.createCell(colno);
			System.out.println("New cell is created");
		}
		cell.setCellValue(value);
		System.out.println("value added in the cell successfully");

		try {
			fos = new FileOutputStream(new File(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wb.write(fos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	public int getTotalNoOfRows(String sheetname) {
		int idx = wb.getSheetIndex(sheetname);
		if (idx == -1) {
			System.out.println("Sheet does not exist");
			return -1;
		}
		sheet = wb.getSheet(sheetname);
		return sheet.getLastRowNum() + 1;
	}

	public int getTotalNoOfColumns(String sheetname) {
		int idx = wb.getSheetIndex(sheetname);
		if (idx == -1) {
			System.out.println("Sheet does not exist");

		}
		sheet = wb.getSheet(sheetname);
		row = sheet.getRow(0);
		if (row == null) {
			System.out.println("No row present");

		}
		return row.getLastCellNum();
	}

}
