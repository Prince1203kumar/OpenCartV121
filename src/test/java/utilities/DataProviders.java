package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="loginData")
	public String[][] getData() throws IOException {
		String path=".\\testData\\OpenCart_LoginData.xlsx";
		ExcelUtility excel = new ExcelUtility(path);
		int totalRowsCount = excel.getRowCount("Sheet1");
		int totalColsCount = excel.getCellCount("Sheet1", 1);
		String[][] loginData = new String[totalRowsCount][totalColsCount];
		for(int r=1;r<totalRowsCount;r++) {
			for(int c=0;c<totalColsCount;c++) {
				loginData[r-1][c] =excel.getCellData("Sheet1", r, c); 
			}
		}
		return loginData;
	}

}
