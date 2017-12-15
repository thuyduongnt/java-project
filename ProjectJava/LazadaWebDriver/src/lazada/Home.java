package lazada;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.*;
import org.testng.annotations.*;

public class Home {
	WebDriver driver;
	
	@BeforeTest
	public void initTest() {
		System.setProperty("webdriver.chrome.driver", "E:\\AutomationTest\\Tools\\chromedriver(1).exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void beforeMethod() {
		String baseURL = "http://lazada.vn/";
		driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	/*
	@Test(priority=1)
	public void CheckGetTheApp() {
		WebElement gettheapp = driver.findElement(By.cssSelector("span.l-top-bar__item-caption"));
		gettheapp.click();
		
		String expectedTitle = "Mua sắm với khuyến mãi sốc khi sử dụng App Mobile tại Lazada";
		String actualTitle = driver.getTitle(); 
		
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	@Test(priority=2, dataProvider="dataHomepage", dataProviderClass=TestData.class)
	public void TestLinkTopNavigate(String linktest, String expectedTitle) {
		List<WebElement> listTopNavLink = driver.findElements(By.cssSelector("a.l-top-bar__item-caption"));
		
		for(WebElement nav : listTopNavLink) {
			if(nav.getText().equalsIgnoreCase(linktest)==true) {
				nav.click();
				System.out.println("--Title: " + driver.getTitle());
				if(driver.getTitle().equals(expectedTitle)==false) {
					File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
					try {
						FileUtils.copyFile(srcFile, new File("E:\\AutomationTest\\DoAnTotNghiep\\Image-bug-lazada\\Homepage\\home_link_error(" + linktest + ").jpg"));
					}
					catch(IOException e){
						e.printStackTrace();
					}
				}
				Assert.assertEquals(driver.getTitle(), expectedTitle, "Lỗi khi click link: " + linktest);
				break;
			}
		}
	}
	
	
	@Test(priority=3, dataProvider="dataHomepage", dataProviderClass=TestData.class)
	public void CheckDisplayPopup(String text) {
		List<WebElement> listDropdown = driver.findElements(By.cssSelector("span.l-dropdown__caption"));
		for(WebElement lst : listDropdown) {
			if(lst.getText().equalsIgnoreCase(text)) {
				lst.click();
				
				Boolean isDisplay = driver.findElement(By.cssSelector("div.l-dropdown_active")).isDisplayed();
				
				Assert.assertTrue(isDisplay==true, "Không hiển thị dropdownlist Chăm sóc khách hàng");
				
				break;
			}
		}
	}
	
	
	@Test(priority=4, dataProvider="dataHomepage", dataProviderClass=TestData.class)
	public void CheckItemCustomerCare(String itemname, String expectedTitle) {
		WebElement customerCare = driver.findElement(By.xpath("//div[@class=\'l-top-bar__list\']/div[@class=\'l-top-bar__item\'][3]"));
		customerCare.click();
		
		List<WebElement> listItem = driver.findElements(By.cssSelector("a.dropdown__item-anchor"));
		for(WebElement item : listItem) {
			if(item.getText().equals(itemname)) {
				item.click();
				
				if(driver.getTitle().equals(expectedTitle)==false) {
					File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
					try {
						FileUtils.copyFile(srcFile, new File("E:\\AutomationTest\\DoAnTotNghiep\\Image-bug-lazada\\Homepage\\home_customercare_link_error(" + itemname + ").jpg"));
					}
					catch(IOException e){
						e.printStackTrace();
					}
				}
				Assert.assertEquals(driver.getTitle(), expectedTitle);
				
				break;
			}
		}
	}
	
	
	@Test(priority=5, dataProvider="dataHomepage", dataProviderClass=TestData.class)
	public void CheckTrachMyOrder(String email, String ordernumber, String msg) {
		WebElement customerCare = driver.findElement(By.cssSelector("div.js-header-order-tracking"));
		customerCare.click();
		
		WebElement txtEmail = driver.findElement(By.id("email"));
		txtEmail.clear();
		txtEmail.sendKeys(email);
		
		WebElement txtOrderNumber = driver.findElement(By.id("orderNr"));
		txtOrderNumber.clear();
		txtOrderNumber.sendKeys(ordernumber);
		
		WebElement btnFind = driver.findElement(By.id("btn-get-order-tracking-data"));
		btnFind.click();
		
		if(msg!="") {
			WebElement txtErrorMessage = driver.findElement(By.className("l-order-tracking-form__status"));
			if(txtErrorMessage.getText().equals(msg)==false) {
				File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				try {
					FileUtils.copyFile(srcFile, new File("E:\\AutomationTest\\DoAnTotNghiep\\Image-bug-lazada\\Homepage\\home_trackmyorder_error_email(" + email + ") ordernumber(" + ordernumber + ").jpg"));
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
			Assert.assertEquals(txtErrorMessage.getText(), msg);
		}
	}
	
	
	@Test(priority=6)
	public void CheckLinkLogo() {
		WebElement gettheapp = driver.findElement(By.cssSelector("span.l-top-bar__item-caption"));
		gettheapp.click();
		
		WebElement logo = driver.findElement(By.cssSelector("a.logo__anchor"));
		logo.click();
		
		String expectedTitle = "LAZADA Vietnam™ - Mua Hàng Trực Tuyến Giá Tốt";
		Assert.assertEquals(driver.getTitle(), expectedTitle, "Logo không link đến trang chủ");
	}
	*/
	
	@Test(priority=7)
	public void CheckBasket() {
		WebElement basket = driver.findElement(By.cssSelector("div.l-header__basket"));
		basket.click();
		
		if(driver.getTitle().equals("Giỏ hàng")==false) {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(srcFile, new File("E:\\AutomationTest\\DoAnTotNghiep\\Image-bug-lazada\\Homepage\\home_shoppingcart_cannot_show.jpg"));
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		Assert.assertEquals(driver.getTitle(), "Giỏ hàng");
	}
	
	@Test(priority=8)
	public void CheckLinkCachMangMuaSam() {
		WebElement link = driver.findElement(By.cssSelector("div.l-header__badges"));
		link.click();
		
		String title = "Chương trình khuyến mãi Cách Mạng Mua Sắm Online tại Lazada.vn";
		
		if(driver.getTitle().equals(title)==false) {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(srcFile, new File("E:\\AutomationTest\\DoAnTotNghiep\\Image-bug-lazada\\Homepage\\home_header_badges_cannot_show.jpg"));
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	
	@AfterTest
	public void finishTest() {
		driver.quit();
	}
}
