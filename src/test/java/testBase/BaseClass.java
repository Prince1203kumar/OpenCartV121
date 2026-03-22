package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups = { "Sanity", "Regression", "Master" })
	@Parameters({ "os", "browser" })
	public void setUp(String os, String br) throws IOException {

		FileReader file = new FileReader(".//src//test//resources//config.properties");
		p = new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass());

		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			// os
			switch (br.toLowerCase()) {

			case "chrome":
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--remote-allow-origins=*");
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
				break;

			case "firefox":
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);
				break;

			case "edge":
				EdgeOptions edgeOptions = new EdgeOptions();
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), edgeOptions);
				break;

			default:
				throw new IllegalArgumentException("Invalid browser");
			}
		} else if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid Browser name");
				return;
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}

	@AfterClass(groups = { "Sanity", "Regression", "Master" })
	public void tearDown() {
		driver.quit();
	}

	public String randomAlpha() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomNumeric() {
		String generatedNumber = RandomStringUtils.randomNumeric(5);
		return generatedNumber;
	}

	public String randomAlphaNumeric() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		return (generatedString + "@" + generatedNumber);
	}

	public String captureScreen(String tname) {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "\\screenshot\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}

}
