package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC_004_EndToEndTest extends BaseClass{
	
	@Test(priority=1)
	public void verify_registration_and_login_flow() {
		logger.info("***** Starting  verify_registration_and_login_flow *****");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked My Account Dropdown");
			hp.clickRegister();
			logger.info("Clicked Register");
			
			RegistrationPage regPage = new RegistrationPage(driver);
			logger.info("Providing New Cutomer Details......");
			String text = randomAlpha();
			regPage.setFirstName(text);
			logger.info("Entered First Name");
			regPage.setLastName(text);
			logger.info("Entered Last Name");
			regPage.setEmail(text+"@gmail.com");
			logger.info("Entered E-Mail");
			String ranPassword = randomNumeric();
			String password = ranPassword+ranPassword;
			regPage.setPassword(password);
			logger.info("Entered Password");
			regPage.clickPrivacyPolicy();
			logger.info("Clicked Privacy Policy");
			regPage.clickBtnContinue();
			logger.info("Clicked Continue Button");
			String actualmessage = regPage.getConfirmMessage();
			Assert.assertEquals(actualmessage, "Your Account Has Been Created!");
			logger.info("Message- "+"'Your Account Has Been Created!' Verified Successfully...");
			hp.clickMyAccount();
			regPage.clickLogout();
			logger.info("Successfully Clicked on Logout and Registration Test flow passed");
			
			logger.info("Now going to verify Login functionality....");
			hp.clickMyAccount();
			logger.info("Clicked My Account Dropdown");
			hp.clickLogin();
			logger.info("Clicked Login");
			
			LoginPage lp = new LoginPage(driver);
			logger.info("Providing Same Customer data while doing Registration....");
			lp.enterEmail(text+"@gmail.com");
			logger.info("Entered E-Mail");
			lp.enterPassword(password);
			logger.info("Entered Password");
			lp.clickLogin();
			logger.info("Clicked Login");
			
			MyAccountPage accPage = new MyAccountPage(driver);
			boolean actualHeader = accPage.getHeaderMyAccount();
			Assert.assertEquals(actualHeader, true);
			logger.info("Header Successfully Verified....");
			hp.clickMyAccount();
			regPage.clickLogout();
			logger.info("Successfully Clicked Logout and Test Passed");
		}catch(Exception e) {
			Assert.fail("Assertion failed due to reason: "+ e.getMessage());
		}
		logger.info("***** Finished EndToEnd verify_registration_and_login_flow *****");
	}

}
