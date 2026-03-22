package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;



public class TC_002_LoginTest extends BaseClass {

	@Test(groups={"Sanity","Master"})
	public void verify_Login() {
		logger.info("***** Started TC_002_LoginTest *****");
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
			if (myAccount) {
				logger.info("Login Test Paseed Successfully...");
				Assert.assertTrue(true);
			} else {
				logger.error("Error Occurred....");
				logger.debug("Test Case Failed....");
			}
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
		logger.info("***** Finished TC_002_LoginTest *****");
	}

}
