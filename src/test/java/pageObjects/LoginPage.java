package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//button[text()='Login']")
	WebElement btnLogin;
	
	@FindBy(xpath="//*[@id='input-password']/following-sibling::a")
	WebElement lnkForgottenPassword;
	
	@FindBy(xpath="//div[@id='account-login']/div[text()=' text_success ']")
	WebElement msgSuccess;
	
	@FindBy(xpath="//*[text()='Continue']")
	WebElement btnContinue;
	
	By errorMsg = By.xpath("//div[@id='alert']/div[contains(text(),' Warning:')]");
	
	public void enterEmail(String email) {
		txtEmail.sendKeys(email);
	}
	public void enterPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	public void clickLogin() {
		//btnLogin.click();
		clickElement(btnLogin);
	}
	
	public void clickForgottenPassword() {
		//lnkForgottenPassword.click();
		clickElement(lnkForgottenPassword);
	}
	
	public String getSuccessMessage() {
		return msgSuccess.getText();
	}
	
	public void clickBtnContinue() {
		//btnContinue.click();
		clickElement(btnContinue);
	}
	
	public String getErrorMessage() {
		WebElement element = waitForElementVisible(errorMsg);
		return element.getText();
	}

}
