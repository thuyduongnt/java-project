package appiumdemo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import io.appium.java_client.android.AndroidDriver;


public class RegisterLazada {
	AndroidDriver driver;
	
	@BeforeTest
	public void initTest() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "My phone");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "5.0");
		cap.setCapability("appPackage", "com.lazada.android");
		cap.setCapability("appActivity", "com.lazada.login.LoginActivity");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	//@Test
	@Test(dataProvider="data", dataProviderClass = TestDataAppium.class)  
	public void testFullName(String testData, String msg) {
		WebElement registerTabWapper = driver.findElement(By.id("com.lazada.android:id/tabs"));
		WebElement registerTabEl = registerTabWapper.findElement(By.xpath("//*[@class=\"android.support.v7.app.ActionBar$Tab\"][2]"));
		registerTabEl.click();
		
		WebElement fullNameWapper = driver.findElement(By.id("com.lazada.android:id/full_name"));
		WebElement fullNameEl = fullNameWapper.findElement(By.className("android.widget.EditText"));
		fullNameEl.clear();
		fullNameEl.sendKeys(testData);
		
		WebElement btnRegister = driver.findElement(By.id("com.lazada.android:id/sign_up"));
		btnRegister.click();
		
		if(msg != "") {
			WebElement msgFullName = driver.findElementById("com.lazada.android:id/full_name");
			WebElement msgfullname = msgFullName.findElement(By.className("android.widget.TextView"));
			
			Assert.assertEquals(msgfullname.getText(), msg, "Lỗi khi nhập tên: " + msg);
		}
	}
	
	@Test(dataProvider="data", dataProviderClass = TestDataAppium.class)
	public void validateEmail(String testData, String msg) {
		WebElement EmailParent = driver.findElementById("com.lazada.android:id/email");
		WebElement email = EmailParent.findElement(By.className("android.widget.EditText"));
		email.clear();
		email.sendKeys(testData);
		
		WebElement btnRegister = driver.findElement(By.id("com.lazada.android:id/sign_up"));
		btnRegister.click();
		
		if(msg != "") {
			WebElement msgEmailParent = driver.findElementById("com.lazada.android:id/email");
			WebElement msgEmail = msgEmailParent.findElement(By.className("android.widget.TextView"));
			
			Assert.assertEquals(msgEmail.getText(), msg);
		}
	}
	
	@Test(dataProvider = "data", dataProviderClass = TestDataAppium.class)
	public void validatePassword(String testData, String msg) {
		WebElement PassParent = driver.findElementById("com.lazada.android:id/lazada_password");
		WebElement passEl = PassParent.findElement(By.className("android.widget.EditText"));
		passEl.clear();
		passEl.sendKeys(testData);

		WebElement btnRegister = driver.findElement(By.id("com.lazada.android:id/sign_up"));
		btnRegister.click();
		
		if(msg != "") {
			WebElement msgPassParent = driver.findElementById("com.lazada.android:id/lazada_password");
			WebElement msgPass = msgPassParent.findElement(By.className("android.widget.TextView"));
			
			Assert.assertEquals(msgPass.getText(), msg);
		}
	}
	
	@AfterTest
	public void afterTest() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}
}
