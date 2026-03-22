package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


public class TC_003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="loginData", dataProviderClass=DataProviders.class)
	public void verify_LoginDDT(String email, String pwd, String exp) {
		
		logger.info("***** Started TC_003_LoginDDT *****");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account Link...");
			hp.clickLogin();
			logger.info("Clicked on Login Link...");
			
			LoginPage lp = new LoginPage(driver);
			logger.info("Entering email and password from Excel data files");
			lp.enterEmail(email);
			lp.enterPassword(pwd);
			logger.info("Email and Password have taken successfully from Excel Data Files....");
			lp.clickLogin();
			logger.info("Clicked on Login Button");
			
			MyAccountPage accPage = new MyAccountPage(driver);
			boolean target = accPage.getHeaderMyAccount();
			if(exp.equalsIgnoreCase("Valid")) {
				if(target) {
					logger.info("Successfully Logged in...");
					accPage.clickLogout();
					Assert.assertTrue(true);
					logger.info("Test Case Passed...");
				}else {
					logger.info("Test Case Failed...");
					Assert.assertTrue(false);
				}
			}else if(exp.equalsIgnoreCase("Invalid")) {
				if(target) {
					accPage.clickLogout();
					logger.info("In case of Invalid expected result Test Case Failed...");
					Assert.assertTrue(false);
				}else {
					logger.info("In case of Invalid expected result Test Case Passed...");
					Assert.assertTrue(true);
				}
			}
			
		}catch(Exception e) {
			Assert.assertTrue(false);
		}
		logger.info("***** Finished TC_003_LoginDDT *****");
	}
}
