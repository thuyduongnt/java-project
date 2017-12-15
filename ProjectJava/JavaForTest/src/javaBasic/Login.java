package javaBasic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class Login {
	WebDriver driver;
	
	@BeforeTest
	public void initTest() {
		System.setProperty("webdriver.chrome.driver", "E:\\AutomationTest\\Tools\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://www.lazada.vn/customer/account/login/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test(priority=1)
	public void LoginWithBlankValue() {
		WebElement txtEmail = driver.findElement(By.id("LoginForm_email"));
		txtEmail.clear();
		txtEmail.sendKeys("");
		
		WebElement txtPassword = driver.findElement(By.name("LoginForm[password]"));
		txtPassword.clear();
		txtPassword.sendKeys("");
		
		WebElement btnLogin = driver.findElement(By.cssSelector("button.ui-buttonCta"));
		btnLogin.click();
	}
	
	@Test(priority=2)
	public void LoginWithValidValue() {
		WebElement txtEmail = driver.findElement(By.id("LoginForm_email"));
		txtEmail.clear();
		txtEmail.sendKeys("khanh.tx@live.com");
		
		WebElement txtPassword = driver.findElement(By.name("LoginForm[password]"));
		txtPassword.clear();
		txtPassword.sendKeys("Abc123");
		
		WebElement btnLogin = driver.findElement(By.cssSelector("button.ui-buttonCta"));
		btnLogin.click();
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
