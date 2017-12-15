
package dataprovider;

import java.io.*;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ExcelDataProvider {
	WebDriver driver;
	
	@BeforeTest
	public void setupTest() {
		System.setProperty("webdriver.chrome.driver", "E:\\AutomationTest\\Tools\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://www.lazada.vn/customer/account/create/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@DataProvider(name="testdata")
	public Object[][] ValidateEmailExcel(Method name) throws Exception {
		String excelFilePath = "E:\\AutomationTest\\storeExcelFile\\Validation.xlsx";
		String sheetName = "";
		int startRow = 0;
		int totalRows = 0;
		int startCol = 0;
		int totalCols = 0;
		
		if(name.getName().equals("ValidateEmail")) { 
			sheetName = "ValidateEmail";
			startRow = 1;
			totalRows = 7;
			startCol = 0;
			totalCols = 2;
		}
		/*
		//else if(name.getName().equals("ValidatePassword")) {
		if(name.getName().equals("ValidatePassword")) {
			//excelFilePath = "E:\\AutomationTest\\storeExcelFile\\Validation.xlsx";
			sheetName = "ValidatePassword";
		}
		*/
		return ExcelUtils.getTableArray(excelFilePath, sheetName, startRow, totalRows, startCol, totalCols);
	}
	
	@Test(dataProvider="testdata")
	public void ValidateEmail(String testData, String msg) {
		WebElement elEmail = driver.findElement(By.id("RegistrationForm_email"));
		elEmail.clear();
		elEmail.sendKeys(testData);
		
		WebElement submitButton = driver.findElement(By.id("send"));
		submitButton.click();
		
		if(msg != "") {
		WebElement errorMessEmail = driver.findElement(By.xpath("//input[@id=\"RegistrationForm_email\"]/../div[@class=\"s-error\"]"));
			Assert.assertEquals(errorMessEmail.getText(), msg, "Lỗi khi nhập email: " + testData);
		}
	}
	
	@Test(dataProvider="testdata")
	public void ValidatePassword(String testData, String msg) {
		WebElement elPassword = driver.findElement(By.id("RegistrationForm_password"));
		elPassword.clear();
		elPassword.sendKeys(testData);
		
		WebElement elRePassword = driver.findElement(By.id("RegistrationForm_password2"));
		elRePassword.clear();
		elRePassword.sendKeys(testData);
		
		WebElement submitButton = driver.findElement(By.id("send"));
		submitButton.click();
		
		if(msg != "") {
			
			WebElement msgPass = driver.findElement(By.xpath("//input[@id=\"RegistrationForm_password\"]/following::div[@class=\"s-error\"]"));
			Assert.assertEquals(msgPass.getText(), msg, "Lỗi khi nhập: " + testData);
			
		/*
			List<WebElement> msgPass = driver.findElements(By.xpath("//input[@id=\"RegistrationForm_password\"]/following::div[@class=\"s-error\"]"));
			
			//Capture
			if(msgPass.size()!=0) {
				File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				try {
					FileUtils.copyFile(srcFile, new File("C:\\anh_bug\\bug1.jpg"));
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
			Assert.assertTrue(msgPass.size()==0, "Lỗi khi nhập: " + testData);*/
		}
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
