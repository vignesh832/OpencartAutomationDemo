package testCases;

import java.io.IOException;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import BaseTestpack.BaseTest;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;

public class AccRegTest extends BaseTest{
	
	
  @Test(groups={"Master","Sanity","Regression"})
  public void newAccRegTest() throws IOException {
	  logging.info("Test Started!");
	  driver.get(data.getProperty("home_url"));
	  logging.info("page load completed!");
	  HomePage homePage= new HomePage(driver);
	  homePage.myAccountClick();
	  homePage.myAccountRegClick();
	  RegistrationPage regPage= new RegistrationPage(driver);
	  String password = randomAlphaNumeric(10);
	  String msg = regPage.setFirstName(randomAlphabetic(6))
	  .setLastName(randomAlphabetic(7))
	  .seteMail(randomAlphabetic(10)+"@gmail.com")
	  .setTelephone(randomNumeric(10))
	  .setPassword(password)
	  .setPasswordConfirm(password)
	  .clickSubscribeNo()
	  .clickAgreePrivacyPolicy()
	  .clickRegSubmit().getRegMessage(); 
	  logging.info("proceeding to validation!");
	  AssertJUnit.assertEquals(msg, "Your Account Has Been Created!");
  }
  
}
