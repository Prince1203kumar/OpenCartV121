package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterationPage extends BasePage{
	
	public RegisterationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPrivacyPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//div/h1[text()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
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
		chkPrivacyPolicy.click();
	}
	
	public void clickBtnContinue() {
		btnContinue.click();
	}
	
	public String getConfirmMessage() {
		try {
			return (msgConfirmation.getText());
		}catch(Exception e) {
			return (e.getMessage());
		}
	}
}
