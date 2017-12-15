package lazadaWebdriver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import lazadaWebdriver.ExcelUtils;

public class LoginFunction {
	WebDriver driver;
	
	
	@BeforeTest
	public void initTest() {
		System.setProperty("webdriver.chrome.driver", "E:\\AutomationTest\\Tools\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
  @BeforeMethod
  public void beforeMethod() {
	  String baseURL = "https://www.lazada.vn/customer/account/login";
	  driver.get(baseURL);
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }


  @DataProvider(name="testdata")
  public Object[][] ExcelData(Method name) throws Exception {
	  String excelFilePath = "E:\\AutomationTest\\ProjectJava\\TestData.xlsx";
		String sheetName = "";
		int startRow = 0;
		int startCol = 0;
		int totalRows = 0;
		int totalCols = 0;
		
		if(name.getName().equals("LoginForm")) {
			sheetName = "Login";
			startRow = 4;
			startCol = 0;
			totalRows = 8;
			totalCols = 4;
		}
		
		return ExcelUtils.getTableArray(excelFilePath, sheetName, startRow, startCol, totalRows, totalCols);
  }
  

  @Test(dataProvider="testdata")
  public void LoginForm(String email, String msgEmail, String password, String msgPassword) {
		
		WebElement txtEmail = driver.findElement(By.id("LoginForm_email"));
		WebElement txtPassword = driver.findElement(By.id("LoginForm_password"));
		WebElement btnLogin = driver.findElement(By.cssSelector("button.ui-buttonCta"));
		
		txtEmail.clear();
		txtEmail.sendKeys(email);
		
		txtPassword.clear();
		txtPassword.sendKeys(password);
		
		btnLogin.click();
		 
		if(msgEmail != "") {
			WebElement messEmail = driver.findElement(By.xpath("//input[@id=\"LoginForm_email\"]/following::div[@class=\"s-error msg\"][]"));
			Assert.assertEquals(messEmail.getText(), msgEmail);
		}
		else if(msgPassword != "") {
			WebElement messPassword = driver.findElement(By.xpath("//input[@id=\"LoginForm_password\"]/following::div[@class=\"s-error msg\"]"));
			Assert.assertEquals(messPassword.getText(), msgPassword);
		}
		else if(msgEmail == "" && msgPassword == "") {
			String expectedTitle = "Lazada - My account";
			String actualTitle = driver.getTitle();
			
			if(actualTitle.compareTo(expectedTitle)!=0) {
				File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				try {
					FileUtils.copyFile(srcFile, new File("E:\\AutomationTest\\DoAnTotNghiep\\Image-bug-lazada\\Login\\bug_login(" + email + ") va (" + password + ").jpg"));
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
			Assert.assertEquals(actualTitle, expectedTitle);
			
			WebElement account = driver.findElement(By.cssSelector("p.dropdown__toggle_type_text"));
			account.click();
			
			WebElement ddlLogout = driver.findElement(By.linkText("Đăng xuất"));
			ddlLogout.click();
		}
	}

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
