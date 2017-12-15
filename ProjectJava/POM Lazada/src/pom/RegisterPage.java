package pom;

import org.openqa.selenium.*;

public class RegisterPage {
	WebDriver driver;
	
	By txtName = By.id("RegistrationForm_first_name");
	By txtPassword1 = By.id("RegistrationForm_password");
	By txtPassword2 = By.id("RegistrationForm_password2");
	
	
	public RegisterPage(WebDriver mainDriver) {
		driver = mainDriver;
	}
	
	
}
