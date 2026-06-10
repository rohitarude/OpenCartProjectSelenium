package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
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
import org.testng.annotations.Test;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass
	@Parameters({ "os", "browser" })
	public void setup(String os, String browser) throws IOException {

		try {

			p = new Properties();
			FileReader fis = new FileReader(System.getProperty("user.dir") + "/src/test/resources/config.properties");

			p.load(fis);

			logger = LogManager.getLogger(this.getClass());

			if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {

				DesiredCapabilities capabilities = new DesiredCapabilities();

				if (os.equalsIgnoreCase("windows")) {

					capabilities.setPlatform(Platform.WIN11);
				} else if (os.equalsIgnoreCase("mac")) {
					capabilities.setPlatform(Platform.MAC);
				} else if (os.equalsIgnoreCase("Linux")) {
					capabilities.setPlatform(Platform.LINUX);
				} else {
					System.out.println("no maching OS avaialble");
				}

				switch (browser.toLowerCase()) {
				case "chrome":
					logger.info("Creating ChromeDriver");
					capabilities.setBrowserName("chrome");
					break;
				case "firefox":
					logger.info("Creating FirefoxDriver");
					capabilities.setBrowserName("firefox");
					break;

				default:
					logger.error("Invalid browser: " + browser);
					System.out.println("no browser found");
					return;
				}

				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

			}

			if (p.getProperty("execution_env").equalsIgnoreCase("local")) {

				logger.info("*** launching a browser ***");
				logger.info("Browser parameter: " + browser);

				switch (browser.toLowerCase()) {
				case "chrome":
					logger.info("Creating ChromeDriver");
					driver = new ChromeDriver();
					break;
				case "edge":
					logger.info("Creating EdgeDriver");
					driver = new EdgeDriver();
					break;
				case "firefox":
					logger.info("Creating FirefoxDriver");
					driver = new FirefoxDriver();
					break;
				default:
					logger.error("Invalid browser: " + browser);
					System.out.println(" invalid browser set");
					return;
				}

			}

			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(p.getProperty("appURL"));
			driver.manage().window().maximize();

		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	@AfterClass
	public void tearDown() {

		driver.quit();
	}

	@Test
	public String randomString() {

		String randomStringsGen = RandomStringUtils.secure().nextAlphabetic(5);
		return randomStringsGen;
	}

	@Test
	public String randomNumber() {

		String randomNumberGen = RandomStringUtils.secure().nextNumeric(10);
		return randomNumberGen;
	}

	@Test
	public String randomAlphabet() {

		String randomStriGen = RandomStringUtils.secure().nextAlphabetic(5);
		String randomNumGen = RandomStringUtils.secure().nextNumeric(3);

		return (randomStriGen + "@" + randomNumGen);
	}

	public String captureScreen1(String tname) {
		try {
			String timestamp = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());

			TakesScreenshot ts = (TakesScreenshot) driver;
			File sourceFile = ts.getScreenshotAs(OutputType.FILE);

			String targetPath = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timestamp + ".png";

			File targetFile = new File(targetPath);

			FileUtils.copyFile(sourceFile, targetFile);

			return targetPath;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
