package testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import BaseTestpack.BaseTest;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class LoginTest extends BaseTest{
	MyAccountPage myAcc;
	SoftAssert sAssert;
	//This is a data driven testing method which means this method will get set of test case data from Dataprovider() <- excel or anything to perform testing.
	@Test(dataProvider="logindp" , dataProviderClass = DataProviders.class, groups= {"Master","Regression","DDT"})
	public void verifyLogin(String eMail, String password, String dataType) {
		logging.info("Test Started!");
		driver.get(data.getProperty("home_url"));
		logging.info("Page Load completed!");
		HomePage hp= new HomePage(driver);
		hp.myAccountClick().myAccountLoginClick();
		
		LoginPage lp= new LoginPage(driver);
		lp.seteMail(eMail).setPassword(password).clickLogin();
	
		myAcc= new MyAccountPage(driver);
		
		boolean loginStatus = myAcc.verifyText();
		sAssert= new SoftAssert();
		
		if(dataType.equalsIgnoreCase("valid")&& loginStatus==true) {
			myAcc.clickLogout();
			return;
		}
		else if(dataType.equalsIgnoreCase("valid") && loginStatus==false){
			sAssert.fail();
		}
		else if(dataType.equalsIgnoreCase("invalid") && loginStatus==false) {
			return;
		}
		else if(dataType.equalsIgnoreCase("invalid") && loginStatus==true){
			myAcc.clickLogout();
			sAssert.fail();
		}
		else {
			System.out.println("Check the code!");
		}
		logging.info("Test Completed!");
		sAssert.assertAll();
	}
}
