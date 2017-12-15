package lazada;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class DemoTest {
WebDriver driver;
	
	@BeforeTest
	public void initTest() {
		System.setProperty("webdriver.chrome.driver", "E:\\AutomationTest\\Tools\\chromedriver(1).exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void beforeMethod() {
		String baseURL = "https://www.lazada.vn/customer/account/create";
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
		
		WebDriverWait waitload = new WebDriverWait(driver, 10);
		waitload.until(ExpectedConditions.titleContains("Hộp thư đến - Gmail"));
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
	
	@Test(priority=1)
	public void FillEmail() {
		String baseHandle = driver.getWindowHandle();
		System.out.println("--base handle: " + baseHandle);
		//Login Gmail
		String gmailHandle = "";
		
		((JavascriptExecutor)driver).executeScript("window.open('about:blank','_blank');");
		
		for(String el : driver.getWindowHandles()) {
			gmailHandle = el;
		}
		driver.switchTo().window(gmailHandle);
		
		driver.get("https://www.google.com/gmail/about/#");
		
		String email = "test2.duongntt@gmail.com";
		String password = "Test#123456";
		
		LoginGmailMethod(email,password);
		
		if(driver.getTitle().contains("Hộp thư đến - Gmail")==false) {
			CaptureMethod("E:\\AutomationTest\\DoAnTotNghiep\\Image-bug-lazada\\ForgotPassword\\" + email + ".jpg");
		}
		else {
			//WebElement newEmail = driver.findElement(By.xpath(xpathExpression))
		}
	}
	
	@Test(priority=2)
	public void checkSelectBox() {
		
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
