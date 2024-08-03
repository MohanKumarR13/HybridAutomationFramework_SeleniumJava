package testBases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public  WebDriver driver;

	@BeforeClass
	public  void setUp() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");
		driver.manage().window().maximize();
	}
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	public String randomString() {
		String generatedString=RandomStringUtils.randomAlphanumeric(5);
		return generatedString;
	}

	public String randomNumber() {
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}

	public String randomAlphaNumeric() {
		String generatedString=RandomStringUtils.randomAlphanumeric(5);
		String generatedNumber=RandomStringUtils.randomNumeric(3);
		return generatedString+"@"+generatedNumber;
	}
}
