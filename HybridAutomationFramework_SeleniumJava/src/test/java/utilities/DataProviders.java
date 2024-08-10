package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name = "testdata")

	public Object[][] getData() throws Exception {
String path=".//testData//TestData.xlsx";
ExcelUtility excelUtility=new ExcelUtility(path); 
		int rows = excelUtility.getRowCount("Sheet1");
		
		int column = excelUtility.getCellCount("Sheet1", 1);
		
		String loginData[][] = new String[rows][column];

		for (int i = 1; i <= rows; i++) {
			for (int j = 0; j < column; j++) {
				loginData[i - 1][j] = excelUtility.getCellData("Sheet1", i, j);
			}
		}
		return loginData;
	}
}
