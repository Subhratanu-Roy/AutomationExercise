package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.Base;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import utility.DataProviders;

public class TC_Register extends Base {
	RegisterPage obj_RegisterPage = null;
	LoginPage obj_LoginPage = null;

	@BeforeClass
	void launchApp() {
		loadWebBrowser();
	}

	@Test(dataProvider = "alldata", dataProviderClass = DataProviders.class)
	void new_user_register_test(String name, String email, String title, String password, String day, String month,
			String year, String newsletter, String specialOffer, String firstname, String lastname, String company,
			String address, String country, String state, String city, String zipcode, String mobno) {

		HomePage obj_HomePage = new HomePage();
		obj_LoginPage = obj_HomePage.clickOnLogin();
		obj_RegisterPage = obj_LoginPage.signup(name, email);
		if (obj_RegisterPage.ifTitlePresent() == false) {
			System.out.println("Unable to signup");
			Assert.assertTrue(false);
		}
		System.out.println("User signed up successfully");
		if (title.contains("Mr"))
			obj_RegisterPage.enterMaleTitle();
		if (title.contains("Mrs"))
			obj_RegisterPage.enterFemaleTitle();

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
		obj_LoginPage = obj_RegisterPage.clickOnCreateAccount();

		String actualSuccessMsgOnAccountCreation = obj_LoginPage.getSuccessMsg();

		if (actualSuccessMsgOnAccountCreation != null) {
			System.out.println("Account created successfully");
			System.out.println("Success message: " + actualSuccessMsgOnAccountCreation);
		}
		String expectedSuccessMsgOnAccountCreation = "ACCOUNT CREATED!";
		Assert.assertEquals(expectedSuccessMsgOnAccountCreation, actualSuccessMsgOnAccountCreation);
		System.out.println("Test passed, proceeding towards next test if presents");
		obj_LoginPage.clickOnContinue();
		System.out.println("Click on continue after account creation");
		System.out.println("Deleting account");
		obj_LoginPage.clickOnDeleteAccount();
		System.out.println("Account deletetion message: " + obj_LoginPage.getDeletionMsg());
		obj_LoginPage.clickOnContinue();
		System.out.println("Click on final continue");

	}

	@AfterClass
	void closeBrowser() {

		getDriver().close();
	}

	/*
	 * modification run the register test with one +ve and one -ve data change name
	 * of the screenshots so that individual ss can be taken for each failure
	 * browser is not closing for homepage test
	 */

}
