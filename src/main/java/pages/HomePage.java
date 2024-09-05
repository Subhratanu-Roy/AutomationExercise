package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;
import utility.PageFunctions;

public class HomePage extends Base {

	public HomePage() {
		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(css = "h1")
	WebElement heading;

	@FindBy(css = ".col-sm-6 h2")
	WebElement heading2;

	@FindBy(css = "div.col-sm-6 p")
	WebElement para;
	
	@FindBy(linkText = "Signup / Login")
	WebElement signup_login;
	
	@FindBy(xpath = "//a[@href='/products']")
	WebElement products;
	
	@FindBy(xpath = "(//a[@href = '/view_cart'])[1]")
	private WebElement cart;

	public String getHeading() {
		return heading.getText();
	}

	public String getHeading2() {
		return heading2.getText();
	}

	public String getParagraph() {
		return para.getText();
	}
	
	public LoginPage clickOnLogin() {
		PageFunctions.explicitWait(getDriver(), signup_login, 10);
		PageFunctions.clickOnElement(getDriver(), signup_login);
		return new LoginPage();
		
	}
	
	public ProductPage clickOnProducts() {
		PageFunctions.clickOnElement(getDriver(),products);
		return new ProductPage();
	}
	
	public CartPage clickOnCart() {
		PageFunctions.clickOnElement(getDriver(), cart);
		return new CartPage();
	}
}
