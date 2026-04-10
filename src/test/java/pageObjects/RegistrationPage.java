package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstName;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastName;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtTelephone;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtConfirmPassword;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkPrivacyPolicy;

	By clkPrivacyPolicy = By.xpath("//input[@name='agree']");

	@FindBy(xpath = "//button[text()='Continue']")
	WebElement btnContinue;

	By continueBtn = By.xpath("//button[text()='Continue']");

	@FindBy(xpath = "//div/h1[text()='Your Account Has Been Created!']")
	WebElement msgConfirmation;

	By errorMessages = By.xpath("//input/following-sibling::div");

	By errorPrivacyPolicyMessage = By.xpath("//div[@id='alert']/div");

	@FindBy(xpath="//ul/li/a[text()='Logout']")
	WebElement btnLogout;

	/*@FindBy(xpath="//ul/li/a")
	List<WebElement> links;
	*/
	
	By links = By.xpath("//ul/li/a");
	
	public void setFirstName(String fName) {
		txtFirstName.sendKeys(fName);
	}

	public void setLastName(String lName) {
		txtLastName.sendKeys(lName);
	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void setTelephone(String phone) {
		txtTelephone.sendKeys(phone);
	}

	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}

	public void setConfirmPassword(String pwd) {
		txtConfirmPassword.sendKeys(pwd);
	}

	public void clickPrivacyPolicy() {
		/*scrollToElement(chkPrivacyPolicy);
		try {
			WebElement privacyPolicyBtn = waitForElementToBeClickable(clkPrivacyPolicy);
			System.out.println("We capture WebElement of privacy Policy....");
			privacyPolicyBtn.click();
			System.out.println("privacy Policy working fine");
		} catch (Exception e) {
			System.out.println("Privacy Policy not working...");
		}
		*/
		clickElement(chkPrivacyPolicy);
	}

	public void clickBtnContinue() {
		/*scrollToElement(btnContinue);
		WebElement btn = waitForElementToBeClickable(continueBtn);
		System.out.println("We capture Continue WebElement....");
		btn.click();
		System.out.println("Continue btn working fine");
		*/
		clickElement(btnContinue);
	}

	public String getConfirmMessage() {
		try {
			return (msgConfirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}

	public List<String> getAllErrorMessages() {
		List<WebElement> errors = waitForAllElementsVisibility(errorMessages);
		List<String> errorList = new ArrayList<>();
		for (WebElement error : errors) {
			errorList.add(error.getText());
		}
		return errorList;
	}

	public String getErrorMessPrivacyPolicy() {
		return waitForElementVisible(errorPrivacyPolicyMessage).getText();
	}

	public void clickLogout() throws InterruptedException {
		/*WebElement logoutBtn = waitForElementToBeClickable(lnkLogout);
		logoutBtn.click();
		*/
		clickElement(btnLogout);
	}

	public int countNumberOfLinks() {
		List<WebElement> elementLinks = waitForMultipleElementsVisible(links);
		if (elementLinks.isEmpty()) {
	        throw new RuntimeException("No links found on the page");
	    }
		return elementLinks.size();
	}
	
	public String getPasswordFieldType() {
		return txtPassword.getAttribute("type");
	}
	
	public String getEmailFieldType() {
		return txtEmail.getAttribute("type");
	}
}
