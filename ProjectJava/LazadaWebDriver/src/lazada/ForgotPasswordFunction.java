package lazada;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class ForgotPasswordFunction {
	WebDriver driver;
	
	@BeforeTest
	public void initTest() {
		System.setProperty("webdriver.chrome.driver", "E:\\AutomationTest\\Tools\\chromedriver(1).exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void beforeMethod() {
		String baseURL = "https://www.lazada.vn/customer/password/forgot/";
		driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public void LoginGmailMethod(String email, String password) {
		driver.get("https://www.google.com/gmail/about/#");
		
		WebElement signin = driver.findElement(By.cssSelector("a.gmail-nav__nav-link__sign-in"));
		signin.click();
		
		WebElement txtEmailGM = driver.findElement(By.id("identifierId"));
		txtEmailGM.clear();
		txtEmailGM.sendKeys(email);
		
		WebElement btnNext1 = driver.findElement(By.cssSelector("span.snByac"));
		btnNext1.click();
		
		WebDriverWait waiter1 = new WebDriverWait(driver, 10);
		waiter1.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		
		WebElement txtPassword = driver.findElement(By.name("password"));
		txtPassword.clear();
		txtPassword.sendKeys(password);
		
		WebElement btnNext2 = driver.findElement(By.cssSelector("span.snByac"));
		btnNext2.click();
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	public void CaptureMethod(String source) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(source));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	
	@Test(priority=1, dataProvider="dataForgotPassword", dataProviderClass=TestData.class)
	public void CheckGoBackShopping(String linkText, String expectedTitle) {
		WebElement link = driver.findElement(By.linkText(linkText));
		link.click();
		
		Assert.assertEquals(driver.getTitle(), expectedTitle);
	}
	
	@Test(priority=2, dataProvider="dataForgotPassword", dataProviderClass=TestData.class)
	public void CheckEmailFormat(String testemail, String msg) {
		WebElement txtEmail = driver.findElement(By.id("ForgotPasswordForm_email"));
		txtEmail.clear();
		txtEmail.sendKeys(testemail);
		
		WebElement btnSend = driver.findElement(By.className("forgot-pass-form__button"));
		btnSend.click();
		
		WebElement message = driver.findElement(By.xpath("//input[@id=\"ForgotPasswordForm_email\"]/following::div[@class=\"forgot-pass-form__error hidden\"]"));
		
		if(message.getText().compareTo(msg)!=0) {
			CaptureMethod("E:\\AutomationTest\\DoAnTotNghiep\\Image-bug-lazada\\ForgotPassword\\forgot_expected_message("  + msg + ") email(" + testemail + ").jpg");
		}
		
		Assert.assertEquals(message.getText(), msg);
	}
	
	
	@Test(priority=3, dataProvider="dataForgotPassword", dataProviderClass=TestData.class)
	public void FillEmail(String email, String msg) {
		WebElement txtEmail = driver.findElement(By.id("ForgotPasswordForm_email"));
		txtEmail.clear();
		txtEmail.sendKeys(email);
		
		WebElement btnSend = driver.findElement(By.className("forgot-pass-form__button"));
		btnSend.click();
		
		if(msg!="") {
			String expectedTitle = "Lazada - Forgot password?";
			
			if(driver.getTitle()==expectedTitle) {
				WebElement message = driver.findElement(By.xpath("//input[@id=\"ForgotPasswordForm_email\"]/following::div[@class=\"forgot-pass-form__error hidden\"]"));
				String actualMessage = message.getText();
				System.out.println("Actual message: " + actualMessage);
				
				if(actualMessage.compareTo(msg)!=0) {
					CaptureMethod("E:\\AutomationTest\\DoAnTotNghiep\\Image-bug-lazada\\ForgotPassword\\send_forgot_expected_message("  + msg + ") email(" + email + ").jpg");
				}
				Assert.assertEquals(actualMessage, msg);
			}
			else {
				CaptureMethod("E:\\AutomationTest\\DoAnTotNghiep\\Image-bug-lazada\\ForgotPassword\\send_forgot_expected_message("  + msg + ") email(" + email + ").jpg");
				
				Assert.assertEquals(driver.getTitle(), expectedTitle);
			}
		}
		else {
			String actualTitle = driver.getTitle();
			String expectedTitle = "Lazada";
			if(actualTitle.compareTo(expectedTitle)!=0) {
				CaptureMethod("E:\\AutomationTest\\DoAnTotNghiep\\Image-bug-lazada\\ForgotPassword\\send_forgot_cannot_send_email_to(" + email + ").jpg");
			}
			Assert.assertEquals(actualTitle, expectedTitle);
		}
	}
	
	
	@Test(priority=4, dataProvider="dataForgotPassword", dataProviderClass=TestData.class)
	public void CheckReceivedEmail(String email, String password) {
		String baseHandle = driver.getWindowHandle();
		
		//Send - forgot password link to gmail
		driver.switchTo().window(baseHandle);
		WebElement txtEmail = driver.findElement(By.id("ForgotPasswordForm_email"));
		txtEmail.clear();
		txtEmail.sendKeys(email);
		
		WebElement btnSend = driver.findElement(By.className("forgot-pass-form__button"));
		btnSend.click();
				
		//Login to Gmail
		String gmailHandle = "";
		
		((JavascriptExecutor)driver).executeScript("window.open('about:blank','_blank');");
		
		for(String el : driver.getWindowHandles()) {
			gmailHandle = el;
		}
		driver.switchTo().window(gmailHandle);
		
		LoginGmailMethod(email, password);
		
		
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
