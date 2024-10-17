package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	String filepath;
	FileInputStream input;
	FileOutputStream output;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	
	public ExcelUtils(String path){
		this.filepath= path;
	}
	public Object[][] getRowData(String sheetName, int row, int cell) throws IOException{
		Object[][] obj= new Object[row-1][cell];
		input= new FileInputStream(filepath);
		workbook= new XSSFWorkbook(input);
		sheet= workbook.getSheet(sheetName);
		//System.out.println(row +" "+ cell);
		for(int i=1;i<row;i++) {
			for(int j=0;j<cell;j++) {
				this.cell= sheet.getRow(i).getCell(j);
				if(this.cell!=null) {
					obj[i-1][j]= sheet.getRow(i).getCell(j).toString();
				}
				else {
					obj[i-1][j]="";
				}
				
			}
		}
		input.close();
		workbook.close();
		return obj;
	}
	
	public int getRowCount(String sheetName) throws IOException{
		input= new FileInputStream(filepath);
		workbook= new XSSFWorkbook(input);
		sheet= workbook.getSheet(sheetName);
		return sheet.getLastRowNum();
	}
	
	public int getCellCount(String sheetName) throws IOException{
		input= new FileInputStream(filepath);
		workbook= new XSSFWorkbook(input);
		sheet= workbook.getSheet(sheetName);
		return sheet.getRow(1).getLastCellNum();
	}
}
