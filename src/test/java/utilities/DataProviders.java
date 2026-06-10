package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="loginData")
	public String[][] getData() throws IOException {
		
		
		String path =System.getProperty("user.dir")+"\\testData\\Opencart_LoginData.xlsx";
		
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalRow = xlutil.getRowCount("sheet1");
		int totalcolumn = xlutil.getCellCount("sheet1", 1);
		
		String loginData[][] = new String[totalRow][totalcolumn];
		
		for (int i = 1; i <= totalRow; i++) {
			
			
			for (int j = 0; j < totalcolumn; j++) {
				
				loginData[i-1][j] = xlutil.getCellData("sheet1", i, j);
				
			}
			
		}
		return loginData;

	}

}
