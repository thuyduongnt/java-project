package javaBasic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

public class AddToCart {
	WebDriver driver;
	
	@BeforeTest
	public void initTest() {
		System.setProperty("webdriver.chrome.driver", "E:\\AutomationTest\\Tools\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver.get("http://iwebshop3.testmaster.vn/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test
	public void addToCart() {
		WebElement txtSearch = driver.findElement(By.id("txtSearchInput"));
		txtSearch.clear();
		txtSearch.sendKeys("laptop");
		
		WebElement btnSearch = driver.findElement(By.cssSelector("button.btn-search"));
		btnSearch.click();
		
		//wait for result displayed
		
		WebDriverWait waiter = new WebDriverWait(driver, 10);
		waiter.until(ExpectedConditions.presenceOfElementLocated(By.className("product-list-container")));
		
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
