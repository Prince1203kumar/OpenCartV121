package testCases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC_001_AccountRegisterationTest extends BaseClass {

	// Positive Test Cases for all mandatory fields
	@Test(priority=1, groups={"Regression","Master"})
	public void verify_Account_Registeration() {
		logger.info("***** Starting TC_001_AccountRegisterationTest *****");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked My Account Link");
			hp.clickRegister();
			logger.info("Clicked Register Link");

			RegistrationPage regPage = new RegistrationPage(driver);
			logger.info("Providing Customer details...");
			regPage.setFirstName(randomAlpha());
			logger.info("Entered Customer First Name");
			regPage.setLastName(randomAlpha());
			logger.info("Entered Customer Last Name");
			regPage.setEmail(randomAlpha() + "@gmail.com");
			logger.info("Entered Customer Email");
			// regPage.setTelephone(randomNumeric());
			String password = randomAlphaNumeric();
			regPage.setPassword(password);
			logger.info("Entered Customer Password");
			// regPage.setConfirmPassword(password);
			regPage.clickPrivacyPolicy();
			logger.info("Clicked on Privacy Policy");
			regPage.clickBtnContinue();
			logger.info("Clicked on Continue Button");
			String getConfirmMessage = regPage.getConfirmMessage();
			Assert.assertEquals(getConfirmMessage, "Your Account Has Been Created!");
			logger.info(
					"'My Account Page' success Message - " + "'Your Account Has Been Created!'" + "also validated...");
			hp.clickMyAccount();
			regPage.clickLogout();
			logger.info("Clicked on Logout Btn...");
			logger.info("Test Case Passed...");
		} catch (Exception e) {
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
		logger.info("***** Finished TC_001_AccountRegisterationTest *****");
	}

	// Negative Test Cases for all Mandatory fields
	@Test(priority=2)
	public void verify_mandatoryFieldErrors() {
		logger.info("***** Starting verify_mandatoryFieldErrors Validation Test.....");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked My Account Menu Dropdown");
			hp.clickRegister();
			logger.info("Clicked Register Link");

			RegistrationPage regPage = new RegistrationPage(driver);
			logger.info("Navigated to registration Page...");
			// don't enter any Data
			regPage.clickBtnContinue();
			logger.info("Clicked Continue Button");
			List<String> actualErrors = regPage.getAllErrorMessages();
			logger.info("Verifying all error messages....");
			List<String> expectedErrors = Arrays.asList("First Name must be between 1 and 32 characters!",
					"Last Name must be between 1 and 32 characters!", "E-Mail Address does not appear to be valid!",
					"Password must be between 6 and 40 characters!");
			Assert.assertEquals(actualErrors, expectedErrors);
			logger.info("Validation passed for First name, Last Name, E-Mail, Password");
			String privacyPolicyerror = regPage.getErrorMessPrivacyPolicy();
			Assert.assertEquals(privacyPolicyerror, "Warning: You must agree to the Privacy Policy!",
					"Assertion Mismatch");
			logger.info("Validation passed for Privacy Policy");
		} catch (Exception e) {
			logger.info("Test Case failed....");
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
		logger.info("***** Finished Mandatory Field Validation Test *****");
	}

	//@Test(priority=3)
	public void verify_numberOfLinksPresent() {
		logger.info("***** Starting verify_numberOfLinksPresent *****");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account Dropdown");
			hp.clickRegister();
			logger.info("Clicked on Register");

			RegistrationPage regPage = new RegistrationPage(driver);
			logger.info("Navigated to Registration Page....");
			int actualLinks = regPage.countNumberOfLinks();
			Assert.assertTrue(actualLinks > 50 && actualLinks <= 500, "Unexpected link count: " + actualLinks);
			logger.info("Test case Passed");

		} catch (Exception e) {
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
		logger.info("***** Finished verify_numberOfLinksPresent *****");
	}
	
	@Test(priority=4)
	public void verify_passwordFieldAppearsAsPasswordType() {
		logger.info("***** Starting verify_passwordFieldAppearsAsPasswordType *****");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account Dropdown");
			hp.clickRegister();
			logger.info("Clicked on Register");
			
			RegistrationPage regPage = new RegistrationPage(driver);
			logger.info("Navigated to Registration Page....");
			String actualType = regPage.getPasswordFieldType();
			System.out.println("Password Type is: "+actualType);
			Assert.assertEquals(actualType, "password");
			logger.info("Test Case passed");
		}catch(Exception e) {
			Assert.fail("Failed due to reason"+e.getMessage());
		}
		logger.info("***** Finished verify_passwordFieldAppearsAsPasswordType *****");
	}
	
	@Test(priority=5)
	public void verify_emailFieldAppearsAsEmailType() {
		logger.info("***** Starting verify_emailFieldAppearsAsEmailType *****");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account Dropdown");
			hp.clickRegister();
			logger.info("Clicked on Register");
			
			RegistrationPage regPage = new RegistrationPage(driver);
			logger.info("Navigated to Registration Page....");
			String email = randomAlpha();
			regPage.setEmail(email+"@gmail.com");
			String actualEmailType = regPage.getEmailFieldType();
			Assert.assertEquals(actualEmailType, "email");
			logger.info("Test Case passed");
		}catch(Exception e) {
			Assert.fail("Failed due to reason"+e.getMessage());
		}
		logger.info("***** Finished verify_emailFieldAppearsAsEmailType *****");
	}

}
