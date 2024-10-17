package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//div/a[text()=\"Logout\"]") WebElement btn_logout;
	@FindBy(xpath="//h2[text()=\"My Account\"]") WebElement txt_myAcc;
	
	public MyAccountPage clickLogout() {
		btn_logout.click();
		return this;
	}
	
	public boolean verifyText() {
		try {
			return txt_myAcc.isDisplayed();
		}
		catch(Exception e){
			return false;
		}
	}
}
