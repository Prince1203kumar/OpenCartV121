package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterationPage;
import testBase.BaseClass;


public class TC_001_AccountRegisterationTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void verify_Account_Registeration() {
		logger.info("***** Starting TC_001_AccountRegisterationTest *****");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked My Account Link");
			hp.clickRegister();
			logger.info("Clicked Register Link");

			RegisterationPage regPage = new RegisterationPage(driver);
			logger.info("Providing Customer details...");
			regPage.setFirstName(randomAlpha());
			regPage.setLastName(randomAlpha());
			regPage.setEmail(randomAlpha() + "@gmail.com");
			regPage.setTelephone(randomNumeric());
			String password = randomAlphaNumeric();
			regPage.setPassword(password);
			regPage.setConfirmPassword(password);
			regPage.clickPrivacyPolicy();
			regPage.clickBtnContinue();
			String getConfirmMessage = regPage.getConfirmMessage();
			if(getConfirmMessage.equals("Your Account Has Been Created!")) {
				logger.info("Test Case Passed...");
				Assert.assertTrue(true);
			}else {
				logger.error("Test Case Failed...");
				logger.debug("Failed");
				
			}
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
		logger.info("***** Finished TC_001_AccountRegisterationTest *****");
	}

}
