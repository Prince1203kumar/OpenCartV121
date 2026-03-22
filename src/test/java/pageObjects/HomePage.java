package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//a/span[text()='My Account']")
	WebElement lnkMyAccount;
	
	@FindBy(xpath="//ul/li/a[text()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath="//li/a[text()='Login']")
	WebElement lnkLogin;
	
	public void clickMyAccount() {
		lnkMyAccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		lnkLogin.click();
	}
}
