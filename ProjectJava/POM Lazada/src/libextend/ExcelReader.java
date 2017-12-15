package libextend;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;

public class ExcelReader {
	public String path;
	FileInputStream fis;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	
	public ExcelReader(String path) {
		this.path = path;
		
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getCellData(String sheetName, String colName, int rowNum) {
		
		try {
			int col_Num = 0;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			
			for(int i = 0; i < row.getLastCellNum(); i++) {
				if(row.getCell(i).getStringCellValue().equals(colName)) {
					col_Num = i;
				}
			}
			row = sheet.getRow(rowNum-1);
			
			cell = row.getCell(col_Num);
			
			if(cell.getCellTypeEnum() == CellType.STRING) {
				return cell.getStringCellValue();
			}
			else if(cell.getCellTypeEnum() == CellType.NUMERIC) {
				return String.valueOf(cell.getNumericCellValue());
			}
			else if(cell.getCellTypeEnum() == CellType.BOOLEAN) {
				return String.valueOf(cell.getBooleanCellValue());
			}
			else if(cell.getCellTypeEnum() == CellType.BLANK) {
				return "";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		String path = System.getProperty("user.dir") + "src\\testdata\\TestData.xlsx";
		ExcelReader obj = new ExcelReader(path);
		System.out.println(obj.getCellData("2. Đăng nhập", "Password", 14));
	}
	
}
