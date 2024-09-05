package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;
import utility.PageFunctions;

public class CartPage extends Base {

	public CartPage() {

		PageFactory.initElements(getDriver(), this);
	}

	@FindBy(xpath = "//b")
	private WebElement cartMsg;

	public String getEmptyCartMsg() {
		return PageFunctions.getText(cartMsg);
	}

}
