package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;
import utility.PageFunctions;

public class LoginPage extends Base {

	@FindBy(xpath = "//input[@name='name']")
	WebElement signupname;

	@FindBy(xpath = "//input[@data-qa='signup-email']")
	WebElement signupEmail;

	@FindBy(xpath = "//input[@data-qa='login-email']")
	WebElement loginEmail;

	@FindBy(xpath = "//input[@data-qa='login-password']")
	WebElement loginpassword;

	@FindBy(xpath = "//button[@data-qa='signup-button']")
	WebElement signupButton;

	@FindBy(xpath = "//button[@data-qa='login-button']")
	WebElement loginButton;

	@FindBy(xpath = "//form[@action='/login'] //p")
	WebElement errorMsg;
	
	@FindBy(css = "a[href*='delete']")
	WebElement dltAccnt;
	
	@FindBy(css = "a[data-qa*='continue']")
	WebElement continueBtn;
	
	@FindBy(css = "h2[data-qa='account-created']")
	WebElement accountCreationSuccessMsg;
	
	@FindBy(css = "h2[data-qa='account-deleted']")
	WebElement accountDeletionMsg;

	public LoginPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public void login(String email, String password) {
		PageFunctions.sendKeys(loginEmail, email);
		PageFunctions.sendKeys(loginpassword, password);
		PageFunctions.clickOnElement(getDriver(), loginButton);

	}

	public RegisterPage signup(String name1, String email) {
		PageFunctions.sendKeys(signupname, name1);
		PageFunctions.sendKeys(signupEmail, email);
		PageFunctions.clickOnElement(getDriver(), signupButton);
		return new RegisterPage();

	}

	public String getErrorMsgOnInvalidCredentials() {
		PageFunctions.explicitWait(getDriver(), errorMsg, 10);
		return PageFunctions.getText(errorMsg);
	}
	
	public void clickOnDeleteAccount() {
		PageFunctions.waitForElementToBeClickable(getDriver(), dltAccnt, 10);
		PageFunctions.clickOnElement(getDriver(), dltAccnt);
	}
	
	public LoginPage clickOnContinue() {
		PageFunctions.clickOnElement(getDriver(), continueBtn);
		return new LoginPage();
	}
	
	public String getSuccessMsg() {
		return PageFunctions.getText(accountCreationSuccessMsg);
		
	}
	
	public String getDeletionMsg() {
		return PageFunctions.getText(accountDeletionMsg);
		
	}

}
