package lazada;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

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
	/*
	@DataProvider(name="testdata")
	public Object[][] LoginExcel(Method name) throws Exception{
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
	*/
	
	@Test(priority=1, dataProvider="dataLogin", dataProviderClass=TestData.class)
	public void CheckLink(String linkTest, String expectedTitle) {
		WebElement link = driver.findElement(By.linkText(linkTest));
		link.click();
		
		String actualTitle = driver.getTitle();
		
		if(actualTitle.compareTo(expectedTitle)!=0) {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(srcFile, new File("E:\\AutomationTest\\DoAnTotNghiep\\Image-bug-lazada\\Login\\bug_errorLink(" + linkTest + ").jpg"));
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	//(priority=2, dataProvider="testdata")
	@Test(priority=2, dataProvider="dataLogin", dataProviderClass=TestData.class)
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
			WebElement messEmail = driver.findElement(By.xpath("//input[@id=\"LoginForm_email\"]/following::div[@class=\"s-error msg\"]"));
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
	
	
	@Test(priority=3, dataProvider="dataLogin", dataProviderClass=TestData.class)
	public void LoginWithFacebook(String email, String password) {
		String baseHandle = driver.getWindowHandle();
		String handle = "";
		
		WebElement btnLoginFacebook = driver.findElement(By.id("facebook-login-button"));
		btnLoginFacebook.click();
		
		Set<String> allHandles = driver.getWindowHandles();
		for(String elHandle : allHandles) {
			handle = elHandle;
		}
		driver.switchTo().window(handle);
		
		WebElement txtEmail = driver.findElement(By.id("email"));
		txtEmail.clear();
		txtEmail.sendKeys(email);
		
		WebElement txtPassword = driver.findElement(By.id("pass"));
		txtPassword.clear();
		txtPassword.sendKeys(password);
		
		WebElement btnLogin = driver.findElement(By.id("u_0_0"));
		btnLogin.click();
		
		WebDriverWait waiter1 = new WebDriverWait(driver, 10);
		waiter1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button._51sy")));
		
		WebElement btnAllow = driver.findElement(By.cssSelector("button._51sy"));
		btnAllow.click();
		
		driver.switchTo().window(baseHandle);
		
		WebDriverWait waiter = new WebDriverWait(driver, 10);
		waiter.until(ExpectedConditions.titleIs("Lazada - My account"));
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "Lazada - My account";
		
		if(actualTitle.compareTo(expectedTitle)!=0) {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(srcFile, new File("E:\\AutomationTest\\DoAnTotNghiep\\Image-bug-lazada\\Login\\cannot_login_with_facebook.jpg"));
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		else {
			Assert.assertEquals(actualTitle, expectedTitle);

			WebElement account = driver.findElement(By.cssSelector("p.dropdown__toggle_type_text"));
			account.click();
			
			WebElement ddlLogout = driver.findElement(By.linkText("Đăng xuất"));
			ddlLogout.click();
		}
		
	}
	
	
	@Test(priority=4, dataProvider="dataLogin", dataProviderClass=TestData.class)
	public void LoginWithGoogle(String email, String password) {
		String baseHandle = driver.getWindowHandle();
		String handle = "";
		
		WebElement btnLoginGoogle = driver.findElement(By.id("google-login-button"));
		btnLoginGoogle.click();
		
		Set<String> allHandles = driver.getWindowHandles();
		for(String elHandle : allHandles) {
			handle = elHandle;
		}
		driver.switchTo().window(handle);
		
		WebElement txtEmail = driver.findElement(By.id("identifierId"));
		txtEmail.clear();
		txtEmail.sendKeys(email);
		
		WebElement btnNext1 = driver.findElement(By.cssSelector("span.snByac"));
		btnNext1.click();
		
		WebDriverWait waiter1 = new WebDriverWait(driver, 10);
		waiter1.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		
		WebElement txtPassword = driver.findElement(By.name("password"));
		txtPassword.clear();
		txtPassword.sendKeys(password);
		
		WebElement btnNext2 = driver.findElement(By.cssSelector("span.snByac"));
		btnNext2.click();
		
		driver.switchTo().window(baseHandle);
		//System.out.println("==Base handle: " + baseHandle);
		//System.out.println("==Handle: " + driver.getWindowHandle());
		
		WebDriverWait waiter2 = new WebDriverWait(driver, 10);
		waiter2.until(ExpectedConditions.titleIs("Lazada - My account"));
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "Lazada - My account";
		
		if(actualTitle.compareTo(expectedTitle)!=0) {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(srcFile, new File("E:\\AutomationTest\\DoAnTotNghiep\\Image-bug-lazada\\Login\\cannot_login_with_google.jpg"));
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
