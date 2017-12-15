package appiumdemo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;
import org.testng.annotations.*;

import io.appium.java_client.android.AndroidDriver;

public class LoginLazada {
	AndroidDriver driver;
	
	@BeforeTest
	public void initTest() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "My Phone");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "5.0");
		cap.setCapability("appPackage", "com.lazada.android");
		cap.setCapability("appActivity", "com.lazada.login.LoginActivity");//com.lazada.login.LoginActivity
		//pt.rocket.view.BaseActivity
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test
	public void TestClick() {
		//driver.findElementById("com.lazada.android:id/sign_in").click();
		WebElement userNameWapper = driver.findElement(By.id("com.lazada.android:id/etLoginWrapper"));
		WebElement userNameEl = userNameWapper.findElement(By.className("android.widget.EditText"));
		userNameEl.sendKeys("sstdnguyen@gmail.com");
		
		WebElement passNameWapper = driver.findElement(By.id("com.lazada.android:id/etPasswordWrapper"));
		WebElement passNameEl = passNameWapper.findElement(By.className("android.widget.EditText"));
		passNameEl.sendKeys("Shinobi#12");
		
		WebElement btnLogin = driver.findElement(By.id("com.lazada.android:id/sign_in"));
		btnLogin.click();
	}
	
	@AfterTest
	public void afterTest() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}
}
