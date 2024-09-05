package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.Base;
import pages.HomePage;

public class TC_HomePage extends Base {

	HomePage obj_HomePage=null;

	@BeforeClass
	void setUp() {
		loadWebBrowser();
		
	}

	@Test
	void TC_validateHeading() {
		obj_HomePage = new HomePage();
		log.info("starting test validateheading");
		String heading = obj_HomePage.getHeading();
		System.out.println(heading);
	}

	@Test
	void TC_validateHeading2() {
		log.info("starting test validateheading2");
		String heading2 = obj_HomePage.getHeading2();
		System.out.println(heading2);
	}

	@Test
	void TC_validateIntroParagraph() {
		String para = obj_HomePage.getParagraph();
		Assert.assertTrue(para.contains("selenium"));
	}

	@AfterClass
	void closeBrowser() {
		getDriver().close();
	}

}
