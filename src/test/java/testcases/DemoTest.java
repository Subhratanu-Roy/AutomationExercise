package testcases;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.Base;
import pages.HomePage;
import utility.PageFunctions;

public class DemoTest extends Base{
HomePage obj_HomePage = null;
	@BeforeTest
	void setup() {
		loadWebBrowser();
	}
	
	@Test
	void saveScreenshots() throws InvalidFormatException, IOException {
		obj_HomePage = new HomePage();
		PageFunctions.takeSnap(getDriver(), "homepage_1");
		PageFunctions.takeSnap(getDriver(), "homepage_1");
		PageFunctions.takeSnap(getDriver(), "homepage_1");
		PageFunctions.createWordDoc("homepage_1");
		
		
	}
	@AfterTest
	void closeBrowser() {
		getDriver().close();
	}
	

}
