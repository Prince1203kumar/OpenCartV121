package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	public WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickElement(WebElement element) {
	    try {
	        waitForElementToBeClickable(element);
	        scrollToElement(element);
	        element.click();
	    } catch (Exception e) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].click();", element);
	    }
	}
	
	// For By
	public WebElement waitForElementToBeClickable(By locator) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	// For WebElement
	public WebElement waitForElementToBeClickable(WebElement element) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	
	//For locator
	public WebElement waitForElementVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		try {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}catch(Exception e) {
			throw new RuntimeException("Element not visible: " + locator, e);
		}
	}
	
	
	public List<WebElement> waitForMultipleElementsVisible(By locator) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    try {
	        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	    } catch (Exception e) {
	        throw new RuntimeException("Elements not visible within timeout", e);
	    }
	}
	
	//Locate WebElement only for Presence
	public WebElement waitForElementPresence(By locator) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	//For Multiple WebElements visibility
	public List<WebElement> waitForAllElementsVisibility(By locator){
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		try {
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}catch(Exception e) {
			return new ArrayList<>();
		}
	}
	
	//Scroll to Element
	public void scrollToElement(WebElement element) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
	}
	

}
