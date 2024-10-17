package utilities;

import java.io.IOException;
import java.util.Arrays;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	ExcelUtils excelUtils;
	
	@DataProvider(name="logindp",indices= {0,1,2})
	public Object[][] loginTestData() {
		excelUtils = new ExcelUtils("./testData/Login_TestCaseData.xlsx");
		Object[][] data = null;
		try {
			int rowCount=excelUtils.getRowCount("Sheet1");
			int cellCount=excelUtils.getCellCount("Sheet1");
			data= excelUtils.getRowData("Sheet1", rowCount, cellCount);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
