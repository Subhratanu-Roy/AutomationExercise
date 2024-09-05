package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.Base;
import pages.HomePage;
import pages.LoginPage;

public class TC_LoginFunctionality extends Base {
	HomePage obj_HomePage;
	LoginPage obj_LoginPage;

	@BeforeClass
	public void launchBrowser() {
		loadWebBrowser();
	}

	@Test
	public void validate_error_message_on_invalid_login() {
		log.info("starting of validate_error_message_on_invalid_login test");
		obj_HomePage = new HomePage();
		obj_LoginPage = obj_HomePage.clickOnLogin();
		log.info("clicked on login page");
		obj_LoginPage.login("uiytu@bhj.jdkd", "12345");
		log.info("giving invalid credentials");
		String actualMsg = obj_LoginPage.getErrorMsgOnInvalidCredentials();
		String expectedMsg = "Your email or password is incorrect!";
		log.info("end of validate_error_message_on_invalid_login test");
		Assert.assertEquals(actualMsg, expectedMsg);

	}

	@AfterClass
	public void closeBrowser() {
		getDriver().close();
	}

}
