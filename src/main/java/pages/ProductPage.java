package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class ProductPage extends Base {

	public ProductPage() {
		PageFactory.initElements(getDriver(), this);
	}

	
	@FindBy(id="sale_image")
	WebElement image;
	
	public boolean isImageDIsplayed() {
		return image.isDisplayed();
	}
}
