package lazada;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.*;
import org.testng.annotations.*;

import pom.LoginPage;
import libextend.*;

public class LoginFunction {
	WebDriver driver;
	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "E:\\AutomationTest\\Tools\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void beforeMethod() {
		String baseURL = "https://www.lazada.vn/customer/account/login";
		driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@DataProvider(name="dataLogin")
	public Object[][] validateEmailExcel(Method name) throws Exception {
		String filePath = "E:\\AutomationTest\\ProjectJava\\POM Lazada\\TestData.xlsx";
		
		String sheetName = "";
		int startRow = 0;
		int totalRows = 0;
		int startCol = 0;
		int totalCols = 0;
		
		if(name.getName().equals("loginForm")) {
			sheetName = "2. Đăng nhập";
			startRow = 9;
			totalRows = 8;
			startCol = 0;
			totalCols = 6;
		}
		
		else if(name.getName().equals("checkActiveLinks")) {
			sheetName = "2. Đăng nhập";
			startRow = 3;
			totalRows = 2;
			startCol = 0;
			totalCols = 3;
		}
		
		return ExcelUtils.getTableArray(filePath, sheetName, startRow, totalRows, startCol, totalCols);
		//return ExcelUtils.getTableArray(filePath, sheetName, startRow, totalRows, startCol, totalCols);
	}
	
	@Test(priority=1, dataProvider="dataLogin")
	public void checkActiveLinks(String id, String link, String ExpectedTitle) {
		LoginPage login = new LoginPage(driver);
		login.checkLink(link);
		Assert.assertEquals(driver.getTitle(), ExpectedTitle);
	}
	
	//@Test(priority=2, dataProvider="dataLogin", dataProviderClass=TestData.class)
	@Test(priority=2, dataProvider="dataLogin")
	public void loginForm(String id_testcase, String email, String msgEmail, String password, String msgPassword, String expectedTitle) {
		LoginPage login = new LoginPage(driver);
		ScreenshotUtils capture = new ScreenshotUtils(driver);
		
		login.inputEmail(email);
		login.inputPassword(password);
		login.submit();
		
		if(msgEmail != "") {
			Assert.assertEquals(login.getMessageEmail(), msgEmail);
		}
		else if(msgPassword != "") {
			Assert.assertEquals(login.getMessagePassword(), msgPassword);
		}
		else if(msgEmail == "" && msgPassword == "") {
			String actualTitle = driver.getTitle();
			
			if(actualTitle.compareTo(expectedTitle)!=0) {
				capture.CaptureMethod("../Image_Bug\\Login\\" + id_testcase + "_(" + email + ") va (" + password + ").jpg");
			}
			Assert.assertEquals(actualTitle, expectedTitle);
			
			login.checkLogout();
		}
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
