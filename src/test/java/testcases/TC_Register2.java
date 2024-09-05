package testcases;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Base;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import utility.DataProviders;
import utility.PageFunctions;

public class TC_Register2 extends Base {

	HomePage obj_HomePage = null;
	LoginPage obj_LoginPage = null;
	RegisterPage obj_RegisterPage = null;
	SoftAssert s = null;
	boolean testfail = true;
	String sheetname = "Testdata";
	String colname = "Pass/Fail/Skip";
	static int dataset = 1;
	String testcasename = null;

	@BeforeClass
	void setUp() {
		loadWebBrowser();
	}



	@Test(dataProvider = "alldata", dataProviderClass = DataProviders.class)
	public void new_user_registration_Test(String tcname, String name, String email, String title, String password, String day,
			String month, String year, String newsletter, String specialOffer, String firstname, String lastname,
			String company, String address, String country, String state, String city, String zipcode, String mobno, String passfailskip) {
		testcasename = tcname;
		s = new SoftAssert();
		HomePage obj_HomePage = new HomePage();
		PageFunctions.takeSnapWIthText(getDriver(), tcname, "Home page");
		obj_LoginPage = obj_HomePage.clickOnLogin();
		PageFunctions.takeSnapWIthText(getDriver(), tcname, "Login Page");
		obj_RegisterPage = obj_LoginPage.signup(name, email);
		PageFunctions.takeSnapWIthText(getDriver(), tcname, "New User Signup");
		if (obj_RegisterPage.ifTitlePresent() == false) {
			System.out.println("Unable to signup");
			Assert.assertTrue(false);
		}
		System.out.println("User signed up successfully");
		if (title.contains("Mr"))
			obj_RegisterPage.enterMaleTitle();
		if (title.contains("Mrs"))
			obj_RegisterPage.enterFemaleTitle();
		PageFunctions.takeSnapWIthText(getDriver(), tcname, "Entering user details");
		obj_RegisterPage.enterPassword(password);
		System.out.println("Entered password: " + password);
		obj_RegisterPage.enterday(day);
		obj_RegisterPage.enterMonth(month);
		obj_RegisterPage.enterYears(year);
		switch (newsletter) {
		case ("Yes"):
			obj_RegisterPage.checkNewsletter();
		default:

		}
		switch (specialOffer) {
		case ("Yes"):
			obj_RegisterPage.checkSpecialOffers();
		default:

		}
		PageFunctions.takeSnapWIthText(getDriver(), tcname, "Entering user information");
		// address information
		obj_RegisterPage.enterFirstname(firstname);
		obj_RegisterPage.enterLastname(lastname);
		obj_RegisterPage.enterAddress(address);
		obj_RegisterPage.enterCompany(company);
		obj_RegisterPage.enterCountry(country);
		obj_RegisterPage.enterCity(city);
		obj_RegisterPage.enterState(state);
		obj_RegisterPage.enterZipcode(zipcode);
		obj_RegisterPage.enterMobno(mobno);
		PageFunctions.takeSnapWIthText(getDriver(), tcname, "Entering user information");
		obj_LoginPage = obj_RegisterPage.clickOnCreateAccount();
		PageFunctions.takeSnapWIthText(getDriver(), tcname, "Creating new account");
		String actualSuccessMsgOnAccountCreation = obj_LoginPage.getSuccessMsg();

		if (actualSuccessMsgOnAccountCreation != null) {
			System.out.println("Account created successfully");
			System.out.println("Success message: " + actualSuccessMsgOnAccountCreation);
		}
		String expectedSuccessMsgOnAccountCreation = "ACCOUNT CREATED!";
		s.assertEquals(expectedSuccessMsgOnAccountCreation, actualSuccessMsgOnAccountCreation);
		obj_LoginPage.clickOnContinue();
		System.out.println("Click on continue after account creation");
		System.out.println("Deleting account");
		PageFunctions.takeSnapWIthText(getDriver(), tcname, "Deleting newly created account");
		obj_LoginPage.clickOnDeleteAccount();
		PageFunctions.takeSnapWIthText(getDriver(), tcname, "After deleting");
		System.out.println("Account deletetion message: " + obj_LoginPage.getDeletionMsg());
		obj_LoginPage.clickOnContinue();
		System.out.println("Click on final continue");
		PageFunctions.takeSnapWIthText(getDriver(), tcname, "Back to login page");

		s.assertAll();
		testfail = false;

	}

	@AfterMethod
	void updateTestResult() throws InvalidFormatException, IOException {
		PageFunctions.createWordDoc(testcasename);
		if (testfail) {
			System.out.println("Marking case as fail");
			excelLibrary.setCellData(sheetname, dataset + 1, colname, "Fail");
		} else {
			System.out.println("Marking case as pass");
			excelLibrary.setCellData(sheetname, dataset + 1, colname, "Pass");
			testfail = true;
		}
		dataset++;
		
	}

	@AfterClass
	void closeBrowser() {

		getDriver().close();

	}

}
