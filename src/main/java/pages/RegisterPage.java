package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import base.Base;
import utility.PageFunctions;

public class RegisterPage extends Base {

	public RegisterPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	@FindBy(css = "#id_gender1")
	WebElement title_mr;

	@FindBy(css = "#id_gender2")
	WebElement title_mrs;

	@FindBy(xpath = "//input[@type = 'password']")
	WebElement password;

	@FindBy(id = "days")
	WebElement selectDay;

	@FindBy(id = "months")
	WebElement selectMonths;

	@FindBy(id = "years")
	WebElement selectYears;

	@FindBy(xpath = "//label[contains(text(),'newsletter')]/preceding-sibling::div/span/input")
	WebElement newsletterCheckbox;

	@FindBy(xpath = "//label[contains(text(),'special offers')]/preceding-sibling::div/span/input")
	WebElement specialoffersCheckbox;

	@FindBy(id = "first_name")
	WebElement firstname;

	@FindBy(id = "last_name")
	WebElement lastname;

	@FindBy(id = "company")
	WebElement company;

	@FindBy(id = "address1")
	WebElement address1;

	@FindBy(id = "country")
	WebElement country;

	@FindBy(id = "state")
	WebElement state;

	@FindBy(id = "city")
	WebElement city;

	@FindBy(id = "zipcode")
	WebElement zipcode;

	@FindBy(id = "mobile_number")
	WebElement mobno;

	@FindBy(css = "div.login-form>h2.title")
	WebElement title;
	
	@FindBy(css = "button[data-qa='create-account']")
	WebElement createAccountBtn;
	
	
	

	public void enterFemaleTitle() {
		PageFunctions.waitForElementToBeClickable(getDriver(), title_mrs, 10);
		PageFunctions.clickOnElement(getDriver(), title_mrs);
	}

	public void enterMaleTitle() {
		PageFunctions.waitForElementToBeClickable(getDriver(), title_mr, 10);
		PageFunctions.clickOnElement(getDriver(), title_mr);
	}

	public void enterPassword(String password1) {
		PageFunctions.sendKeys(password, password1);
	}

	public void enterday(String day) {
		day = day.substring(0, day.length()-2);
		PageFunctions.selectByVisibleText(selectDay, day);
	}

	public void enterMonth(String month) {
		PageFunctions.selectByVisibleText(selectMonths, month);
	}

	public void enterYears(String yrs) {
		yrs = yrs.substring(0, yrs.length()-2);
		PageFunctions.selectByVisibleText(selectYears, yrs);
	}

	public void checkNewsletter() {
		PageFunctions.clickOnElement(getDriver(), newsletterCheckbox);
	}

	public void checkSpecialOffers() {
		PageFunctions.clickOnElement(getDriver(), specialoffersCheckbox);

	}

	public void enterFirstname(String fname) {
		PageFunctions.sendKeys(firstname, fname);
	}

	public void enterLastname(String lname) {
		PageFunctions.sendKeys(lastname, lname);
	}

	public void enterAddress(String address) {
		PageFunctions.sendKeys(address1, address);
	}

	public void enterCompany(String cmpn) {
		PageFunctions.sendKeys(company, cmpn);
	}

	public void enterCountry(String cntry) {
		PageFunctions.selectByVisibleText(country, cntry);
	}

	public void enterState(String st) {
		PageFunctions.sendKeys(state, st);
	}

	public void enterCity(String cty) {
		PageFunctions.sendKeys(city, cty);
	}

	public void enterZipcode(String zc) {
		zc = zc.substring(0, zc.length()-2);
		PageFunctions.sendKeys(zipcode, zc);
	}

	public void enterMobno(String mn) {
		PageFunctions.sendKeys(mobno, mn);
	}

	public boolean ifTitlePresent() {
		try {
			return title.isDisplayed();
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public LoginPage clickOnCreateAccount() {
		PageFunctions.clickOnElement(getDriver(), createAccountBtn);
		return new LoginPage();
	}
	
	
	
	

}
