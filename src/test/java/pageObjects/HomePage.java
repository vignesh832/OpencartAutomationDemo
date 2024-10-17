package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	//constructor uses super class to initialize the driver and initialize the elements.
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//a[@title=\"My Account\"]") WebElement btn_myAccount_loc;
	@FindBy(xpath="//a[text()=\"Register\"]") WebElement btn_myAccount_Reg_loc;
	@FindBy(xpath="//a[text()=\"Login\"]") WebElement btn_myAccount_login_loc;
	
	public HomePage myAccountClick() {
		btn_myAccount_loc.click();
		return this;
	}
	public HomePage myAccountRegClick() {
		btn_myAccount_Reg_loc.click();
		return this;
	}
	public HomePage myAccountLoginClick() {
		btn_myAccount_login_loc.click();
		return this;
	}
}
