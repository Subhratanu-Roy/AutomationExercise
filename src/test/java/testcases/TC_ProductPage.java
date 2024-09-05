package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.Base;
import pages.HomePage;
import pages.ProductPage;

public class TC_ProductPage extends Base {

	HomePage obj_HomePage = null;
	ProductPage obj_productPage = null;

	@BeforeClass
	void setUp() {
		loadWebBrowser();
	}

	@Test
	public void validate_if_image_displayed() {
		obj_HomePage = new HomePage();
		obj_productPage = obj_HomePage.clickOnProducts();
		boolean res = obj_productPage.isImageDIsplayed();
		Assert.assertTrue(res);

	}
	
	@AfterClass
	void closeBrowser() {
		getDriver().close();
	}

}
