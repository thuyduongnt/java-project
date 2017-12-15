package pom;

import org.openqa.selenium.*;
import org.testng.Assert;

public class LoginPage {
	WebDriver driver;
	
	//Dinh nghia cac WebElement
	By txtEmail = By.id("LoginForm_email");
	By txtPassword = By.id("LoginForm_password");
	By btnLogin = By.cssSelector("button.ui-buttonCta");
	
	By msgEmail = By.xpath("//input[@id=\"LoginForm_email\"]/following::div[@class=\"s-error msg\"][]");
	By msgPassword = By.xpath("//input[@id=\"LoginForm_password\"]/following::div[@class=\"s-error msg\"]");
	
	By toggle_logout = By.cssSelector("p.dropdown__toggle_type_text");
	By logout = By.linkText("Đăng xuất");
	
	//Dinh nghia cac phuong thuc
	public LoginPage(WebDriver mainDriver) {
		driver = mainDriver;
	}
	
	//Phuong thuc on page
	public void inputEmail(String email) {
		WebElement emailField = driver.findElement(txtEmail);
		emailField.clear();
		emailField.sendKeys(email);
	}
	
	public void inputPassword(String password) {
		WebElement passwordField = driver.findElement(txtPassword);
		passwordField.clear();
		passwordField.sendKeys(password);
	}
	
	public void submit() {
		driver.findElement(btnLogin).click();
	}
	
	public String getMessageEmail() {
		WebElement messEmail = driver.findElement(msgEmail);
		return messEmail.getText();
	}
	
	public String getMessagePassword() {
		WebElement messPassword = driver.findElement(msgPassword);
		return messPassword.getText();
	}
	
	public void checkLogout() {
		WebElement account = driver.findElement(toggle_logout);
		account.click();
		
		WebElement ddlLogout = driver.findElement(logout);
		ddlLogout.click();
	}
	
	public void checkLink(String linkTest) {
		driver.findElement(By.linkText(linkTest)).click();
	}
}
