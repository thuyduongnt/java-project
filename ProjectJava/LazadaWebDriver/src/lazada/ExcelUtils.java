package lazada;

import java.io.*;

import org.apache.poi.xssf.usermodel.*;

public class ExcelUtils {
	private static XSSFWorkbook ExcelWBook;
	private static XSSFSheet ExcelWSheet;
	//private static XSSFRow Row;
	//private static XSSFCell Cell;
	
	public static Object[][] getTableArray(String FilePath, String SheetName, int startRow, int startCol, int totalRows, int totalCols) throws Exception {
		String[][] tabArray = null;
		
		try {
			FileInputStream ExcelFile = new FileInputStream(FilePath);
			
			//Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			
			//Khoi tao mang du lieu
			tabArray = new String[totalRows][totalCols];
			
			//Doc du lieu theo dong va cot
			for(int i = startRow; i <= totalRows; i++) {
				for(int j = startCol; j <= totalCols; j++) {
					if(ExcelWSheet.getRow(i).getCell(j)!=null) {
						tabArray[i-1][j] = ExcelWSheet.getRow(i).getCell(j).getStringCellValue();
					}
					else {
						tabArray[i-1][j] = "";
					}
				}
			}
		}
		catch(FileNotFoundException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		catch(IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		
		return tabArray;
	}
}
