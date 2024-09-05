package utility;

import org.testng.annotations.DataProvider;

import base.Base;

public class DataProviders extends Base{
//	//ExcelLibrary excelLibrary;
//	String path = Base.prop.getProperty("testdataPath");
	String sheetname = "Testdata";
//
////	public DataProviders() {
////
////		excelLibrary = new ExcelLibrary(path);
////	}

	@DataProvider(name = "alldata")
	public Object[][] getAllData() {

		int totRows = excelLibrary.getTotalNoOfRows(sheetname);
		int totCols = excelLibrary.getTotalNoOfColumns(sheetname);
		System.out.println("total rows: " + totRows);
		System.out.println("total columns: " + totCols);
		Object[][] userdata = new Object[totRows - 1][totCols];
		for (int i = 2; i <= totRows; i++) {
			for (int j = 1; j <= totCols; j++) {
				userdata[i - 2][j - 1] = excelLibrary.getCellData(sheetname, i, j);
			}
		}
		return userdata;

	}

	@DataProvider(name = "username")
	public String[] getName() {
		int rowno = excelLibrary.getTotalNoOfRows(sheetname);
		String[] names = new String[rowno-1];
		for (int i = 2; i <= rowno; i++) {
			names[i - 2] = excelLibrary.getCellData(sheetname, i, "Name");
		}
		return names;

	}

}
