package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@name=\"email\"]") WebElement txt_eMail;
	@FindBy(xpath="//input[@name=\"password\"]") WebElement txt_password;
	@FindBy(xpath="//input[@value=\"Login\"]") WebElement btn_login;
	
	public LoginPage seteMail(String eMail) {
		txt_eMail.sendKeys(eMail);
		return this;
	}
	public LoginPage setPassword(String password) {
		txt_password.sendKeys(password);
		return this;
	}
	public LoginPage clickLogin() {
		btn_login.click();
		return this;
	}
	
}
