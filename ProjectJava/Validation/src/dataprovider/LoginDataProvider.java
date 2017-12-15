package dataprovider;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginDataProvider {
	WebDriver driver;
	
	@BeforeTest
	public void setupTest() {
		System.setProperty("webdriver.chrome.driver", "E:\\AutomationTest\\Tools\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://www.lazada.vn/customer/account/login/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test(dataProvider="data", dataProviderClass=TestData.class)
	public void testLogin(String testEmail, String msgEmail, String testPass, String msgPass) {
		WebElement txtEmail = driver.findElement(By.id("LoginForm_email"));
		txtEmail.clear();
		txtEmail.sendKeys(testEmail);
		
		WebElement txtPassword = driver.findElement(By.id("LoginForm_password"));
		txtPassword.clear();
		txtPassword.sendKeys(testPass);
		
		WebElement btnLogin = driver.findElement(By.cssSelector("button.ui-buttonCta"));
		btnLogin.click();
		
		WebElement msg1 = driver.findElement(By.xpath("//input[@id=\"LoginForm_email\"]/following::div[@class=\"s-error msg\"]"));
		if(msgEmail!="") {
			Assert.assertEquals(msg1.getText(), msgEmail, "Lỗi khi nhập: " + testEmail);
		}
		
		WebElement msg2 = driver.findElement(By.xpath("//input[@id=\"LoginForm_password\"]/following::div[@class=\"s-error msg\"]"));
		if(msgPass!="") {
			Assert.assertEquals(msg2.getText(), msgPass, "Lỗi khi nhập: " + testPass);
		}
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
