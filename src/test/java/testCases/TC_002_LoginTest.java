package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;



public class TC_002_LoginTest extends BaseClass {

	@Test(priority=1, groups={"Sanity","Master"})
	public void verify_Login() {
		logger.info("***** Started verify_Login *****");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			logger.info("Clicked on Login Link...");
			
			LoginPage lp = new LoginPage(driver);
			logger.info("Entering email and password from config files");
			lp.enterEmail(p.getProperty("email"));
			lp.enterPassword(p.getProperty("password"));
			logger.info("Email and Password fetched successfully from config files");
			lp.clickLogin();
			logger.info("Clicked on Login Button");
			
			MyAccountPage myAccPage = new MyAccountPage(driver);
			boolean myAccount = myAccPage.getHeaderMyAccount();
			Assert.assertTrue(myAccount);
			logger.info("My Account Header Validation passed");
			hp.clickMyAccount();
			
			RegistrationPage regPage = new RegistrationPage(driver);
			regPage.clickLogout();
			logger.info("Test Case Passed...");
		} catch (Exception e) {
			Assert.fail("Test Faile due to reason: "+ e.getMessage());
		}
		logger.info("***** Finished verify_Login *****");
	}
	
	@Test(priority=2)
	public void verify_forgottenPassword() {
		logger.info("***** Started verify_forgottenPassword *****");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			logger.info("Clicked on Login Link...");
			
			LoginPage login = new LoginPage(driver);
			login.clickForgottenPassword();
			logger.info("Clicked on Forgotten Password link");
			login.enterEmail(p.getProperty("email"));
			logger.info("Entered E-Mail from config files");
			login.clickBtnContinue();
			logger.info("Clicked on Continue Button");
			String successMsg = login.getSuccessMessage();
			Assert.assertEquals(successMsg, " text_success ".trim());
			logger.info("Test Case Passed");
		}catch(Exception e) {
			Assert.fail("Failed due to reason: "+e.getMessage());
		}
		logger.info("***** Finsished verify_forgottenPassword *****");
	}
	
	@Test(priority=3)
	public void verifyErrorMessageForInvalidLoginCredentials() {
		
		logger.info("***** Starting verifyErrorMessageForInvalidLoginCredentials *****");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			logger.info("Clicked on Login");
			
			LoginPage login = new LoginPage(driver);
			login.enterEmail(randomAlpha()+"@gmail.com");
			logger.info("Entered E-Mail");
			String text= randomAlpha();
			String password = text+randomAlpha();
			login.enterPassword(password);
			logger.info("Entered password");
			login.clickLogin();
			logger.info("Clicked on Login Button");
			String actualErrorMessage = login.getErrorMessage();
			Assert.assertEquals(actualErrorMessage, " Warning: No match for E-Mail Address and/or Password. ".trim());
			logger.info("Test Case Passed with error message Validation");
		}catch(Exception e) {
			Assert.fail("Failed due to reason: "+e.getMessage());
		}
		
	}

}
