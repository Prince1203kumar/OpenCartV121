package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a/span[text()='My Account']")
	WebElement lnkMyAccount;
	
	By myAccount = By.xpath("//a/span[text()='My Account']");

	@FindBy(xpath = "//ul/li/a[text()='Register']")
	WebElement lnkRegister;

	@FindBy(xpath = "//li/a[text()='Login']")
	WebElement lnkLogin;

	public void clickMyAccount() {
	    WebElement element = waitForElementToBeClickable(lnkMyAccount);
	    clickElement(element);
	}

	public void clickRegister() {
		WebElement element = waitForElementToBeClickable(lnkRegister);
	    clickElement(element);
	}

	public void clickLogin() {
		lnkLogin.click();
	}
}
