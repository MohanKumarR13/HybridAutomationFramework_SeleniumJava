package testBase;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	public static Logger logger;
	public static Properties properties;
	public static DesiredCapabilities capabilities;

	@BeforeClass(groups = { "sanity", "regression", "master", "datadriven" })
	@Parameters({ "platformName", "browserName" })
	public void setUp(String platformName, String browserName) throws Exception {
		FileReader fileReader = new FileReader(".//src//test//resources//config.properties");
		properties = new Properties();
		properties.load(fileReader);
		logger = LogManager.getLogger(this.getClass());
		if (properties.getProperty("EXECUTION_ENV").equalsIgnoreCase("remote")) {
			capabilities = new DesiredCapabilities();
			if (platformName.equalsIgnoreCase("windows 11")) {
				capabilities.setPlatform(Platform.WIN11);
			}

			else if (platformName.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.WIN11);
			} else {
				System.out.println("No Match Os Found");
				return;
			}
			switch (browserName.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "MicrosoftEdge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			default:
				System.out.println("Invalid Browser Name...");
				return;
			}
			driver = new RemoteWebDriver(new URL("http://192.168.87.106:4444/wd/hub"), capabilities);

		}
		if (properties.getProperty("EXECUTION_ENV").equalsIgnoreCase("local")) {

			switch (browserName.toLowerCase()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");
				driver.manage().window().maximize(); 
				break;
			case "MicrosoftEdge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				 driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");
				driver.manage().window().maximize();
				break;
		
			default:
				System.out.println("Invalid Browser Name..");
				return;
			}
		}
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphanumeric(5);
		return generatedString;
	}

	public String randomNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}

	public String randomAlphaNumeric() {
		String generatedString = RandomStringUtils.randomAlphanumeric(5);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		return generatedString + "@" + generatedNumber;
	}

	public static String captureScreen(String testsName) {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + testsName + "_" + timestamp
				+ ".png";
		File taget = new File(targetFilePath);
		sourceFile.renameTo(taget);
		return targetFilePath;

	}
}
