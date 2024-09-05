package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utility.ExcelLibrary;
import utility.Log;

public class Base {

	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
	public static Properties prop;
	public static Log log = new Log();
	public static ExcelLibrary excelLibrary;

	@BeforeSuite // initialize all the required excel files for the tests
	public void init() throws IOException {

		prop = new Properties();
		String propPath = System.getProperty("user.dir") + "\\resources\\Globaldata.properties";
		FileInputStream fis = new FileInputStream(new File(propPath));
		prop.load(fis);
		excelLibrary = new ExcelLibrary(System.getProperty("user.dir") + "\\resources\\Testdata.xlsx");

	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public void loadWebBrowser() {
		String browser = prop.getProperty("browser");
		System.out.println("Browser: " + browser);
		log.info("initialize " + browser + " browser");
		if (browser.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());
		}
		if (browser.equalsIgnoreCase("firefox")) {
			driver.set(new FirefoxDriver());
		}
		if (browser.equalsIgnoreCase("edge")) {
			driver.set(new EdgeDriver());
		}
		getDriver().get(prop.getProperty("url"));
		// log.info("Opening url");
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	@AfterSuite // close the current browser
	public void afterSuite() {
		getDriver().close();
	}
}
