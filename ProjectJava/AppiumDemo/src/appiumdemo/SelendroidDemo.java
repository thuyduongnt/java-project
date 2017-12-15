package appiumdemo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import io.appium.java_client.android.AndroidDriver;

public class SelendroidDemo {
	AndroidDriver driver;
	
	@BeforeTest
	public void initTest() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "Samsung Galaxy S6");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "5.1");
		cap.setCapability("appPackage", "com.lazada.android");
		cap.setCapability("appActivity", "pt.rocket.view.BaseActivity");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), cap);
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test
	public void Test() {
		driver.findElementById("com.lazada.android:id/basket_item");
	}
	
	@AfterTest
	public void afterTest() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}
}
