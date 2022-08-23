package pages;

import java.io.IOException;

import org.openqa.selenium.By;

import resources.BasePage;

public class LoginPage extends BasePage {

	By inputUserName = By.id("userName");
	By inputPassword = By.id("password");
	By loginButton = By.id("login");
	By newUserButton = By.id("newUser");
	
	
	public LoginPage() throws IOException {
		initializeDriver();
	}
	
	public void clickOnInputUserName() {
		clickElement(inputUserName);
	}
	
	public void funcionSinUsar() {
		
	}
}
