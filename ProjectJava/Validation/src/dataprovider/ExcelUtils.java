package dataprovider;

import java.io.*;

import org.apache.poi.xssf.usermodel.*;

public class ExcelUtils {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	
	public static Object[][] getTableArray(String FilePath, String SheetName, int startRow, int totalRows, int startCol, int totalCols) throws Exception {
		String[][] tabArray = null;
		
		try {
			//System.out.println(FilePath);
			FileInputStream ExcelFile = new FileInputStream(FilePath);
			//Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			/*
			int startRow = 1;
			int startCol = 0;
			int totalRows = ExcelWSheet.getLastRowNum();
			
			//Chỉ ra số lượng cột dữ liệu
			int totalCols = 2;
			*/
			//Khởi tạo mảng dữ liệu
			tabArray = new String[totalRows][totalCols];
			
			//Đọc dữ liệu theo dòng và cột
			for(int i = startRow; i <= totalRows + startRow - 1; i++) {
				for(int j = startCol; j < totalCols + startCol; j++) {
					if(ExcelWSheet.getRow(i).getCell(j)!=null) {
						tabArray[i-1][j] = ExcelWSheet.getRow(i).getCell(j).getStringCellValue();
					}
					else
						tabArray[i-1][j] = "";
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
