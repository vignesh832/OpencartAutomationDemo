package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{
	
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@name=\"firstname\"]") WebElement txt_firstName;
	@FindBy(xpath="//input[@name=\"lastname\"]") WebElement txt_lastName;
	@FindBy(xpath="//input[@name=\"email\"]") WebElement txt_eMail;
	@FindBy(xpath="//input[@name=\"telephone\"]") WebElement txt_telephone;
	@FindBy(xpath="//input[@name=\"password\"]") WebElement txt_password;
	@FindBy(xpath="//input[@name=\"confirm\"]") WebElement txt_passwordConfirm;
	@FindBy(xpath="//input[@name=\"newsletter\" and @value=\"0\"]") WebElement rdb_subscribe_no;
	@FindBy(xpath="//input[@name=\"agree\"]") WebElement chb_agreePrivacyPolicy;
	@FindBy(xpath="//input[@value=\"Continue\"]") WebElement reg_submit;
	@FindBy(xpath="//div[@id=\"content\"]/h1") WebElement msg_regStatus;
	
	public RegistrationPage setFirstName(String fName) {
		txt_firstName.sendKeys(fName);
		return this;
	}
	public RegistrationPage setLastName(String lName) {
		txt_lastName.sendKeys(lName);
		return this;
	}
	public RegistrationPage seteMail(String eMail) {
		txt_eMail.sendKeys(eMail);
		return this;
	}
	public RegistrationPage setTelephone(String phone) {
		txt_telephone.sendKeys(phone);
		return this;
	}
	public RegistrationPage setPassword(String password) {
		txt_password.sendKeys(password);
		return this;
	}
	public RegistrationPage setPasswordConfirm(String password) {
		txt_passwordConfirm.sendKeys(password);
		return this;
	}
	public RegistrationPage clickSubscribeNo() {
		rdb_subscribe_no.click();
		return this;
	}
	public RegistrationPage clickAgreePrivacyPolicy() {
		chb_agreePrivacyPolicy.click();
		return this;
	}
	public RegistrationPage clickRegSubmit() {
		reg_submit.click();
		//or
		//reg_submit.submit();
		return this;
	}
	public String getRegMessage() {
		return msg_regStatus.getText();
	}
}
