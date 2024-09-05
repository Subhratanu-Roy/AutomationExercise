package testcases;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Base;
import pages.CartPage;
import pages.HomePage;
import utility.DataProviders;
import utility.PageFunctions;

public class TC_Cart extends Base {

	HomePage homepage = null;
	CartPage cartpage = null;
	String sheetname = "Cart Testdata";
	SoftAssert s = null;
	String colname = "Pass/Fail/Skip";
	boolean testfail = true;
	String tcname = null;

	@BeforeClass
	public void setUp() {
		loadWebBrowser();
	}

	@Test(dataProvider = "cartpage", dataProviderClass = DataProviders.class)
	void cartPageTest(String name) {
		SoftAssert s = new SoftAssert();
		tcname = name;
		homepage = new HomePage();
		PageFunctions.takeSnapWIthText(getDriver(), tcname, "Home Ppage");
		cartpage = homepage.clickOnCart();
		PageFunctions.takeSnapWIthText(getDriver(), tcname, "Cart Page");
		String actual_msg = cartpage.getEmptyCartMsg();
		String expected_msg = "Cart is empty!";
		s.assertTrue(actual_msg.equals(expected_msg));
		s.assertAll();
		testfail = false;

	}

	@AfterClass
	public void teardown() throws InvalidFormatException, IOException {
		getDriver().close();
		PageFunctions.createWordDoc(tcname);
		if (testfail) {
			excelLibrary.setCellData(sheetname, 2, colname, "Fail");
		} else {
			excelLibrary.setCellData(sheetname, 2, colname, "Pass");
		}

	}

}
