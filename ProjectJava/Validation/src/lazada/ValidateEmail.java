package lazada;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ValidateEmail {
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
	
	@Test
	public void validateEmail() {
		//Test data
		List<String> invalidEmailData = new ArrayList();
		invalidEmailData.add("khanh.tx");
		invalidEmailData.add("khanh.tx@");
		invalidEmailData.add("khanh.tx@gmail");
		invalidEmailData.add("khanh.tx@gmail.");
		invalidEmailData.add("khanh.tx@gamil.c");
		
		String validEmailData = "khanh.tx@live.com";
		
		//Select element
		WebElement errorMessEmail = null;
		//Running test
		for(String s: invalidEmailData) {
			WebElement elEmail = driver.findElement(By.id("RegistrationForm_email"));
			elEmail.clear();
			elEmail.sendKeys(s);
			WebElement submitButton = driver.findElement(By.cssSelector("button.sel-new-account-submit-button"));
			submitButton.click();
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			errorMessEmail = driver.findElement(By.xpath("//input[@id=\"RegistrationForm_email\"]/../div[@class=\"s-error\"]"));
			Assert.assertEquals(errorMessEmail.getText(), "Email không là một email hợp lệ", "Lỗi khi nhập email: " + s);
			////div/input[@id=\\\"RegistrationForm_email\\\"]/following::div
		}
		
		//For valid email
		WebElement elEmail = driver.findElement(By.id("RegistrationForm_email"));
		elEmail.clear();
		elEmail.sendKeys(validEmailData);
		WebElement submitButton = driver.findElement(By.cssSelector("button.sel-new-account-submit-button"));
		submitButton.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<WebElement> elEmailErrorNotice = driver.findElements(By.xpath("//input[@id=\"RegistrationForm_email\"]/../div[@class=\"s-error\"]"));
		
		//Capture
		if(elEmailErrorNotice.size()!=0) {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(srcFile, new File("C:\\anh_bug\\bug.jpg"));
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		Assert.assertTrue(elEmailErrorNotice.size()==0, "Lỗi khi nhập: " + validEmailData);
	}
	
	@AfterTest
	public void afterTest() {
		//driver.quit();
	}
}
