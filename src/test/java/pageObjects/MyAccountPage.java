package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h1[text()='My Account']")
	WebElement headerMyAccount;
	@FindBy(xpath="//aside/div/a[text()='Logout']")
	WebElement lnkLogout;
	
	public boolean getHeaderMyAccount() {
		try {
			return (headerMyAccount.isDisplayed());
		}catch(Exception e) {
			return (headerMyAccount.isDisplayed());
		}
		
	}
	
	public void clickLogout() {
		//lnkLogout.click();
		clickElement(lnkLogout);
	}
}
