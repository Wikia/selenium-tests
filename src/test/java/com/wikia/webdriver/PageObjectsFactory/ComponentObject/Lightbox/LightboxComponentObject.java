package com.wikia.webdriver.PageObjectsFactory.ComponentObject.Lightbox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class LightboxComponentObject extends BasePageObject{

	public LightboxComponentObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(className="WikiaLightbox")
	private WebElement lightBoxHeader;
	@FindBy(css=".thumb.thumbinner a")
	private WebElement imageThumbnail;

	public void verifyLightboxPopup() {
		waitForElementByElement(lightBoxHeader);
		PageObjectLogging.log("verifyLightboxPopup",
			"verify lightbox appeared", true, driver);
	}

	public LightboxComponentObject openLightbox() {
		waitForElementByElement(imageThumbnail);
		clickAndWait(imageThumbnail);
		PageObjectLogging.log("openLightbox",
			"opened ligthbox", true, driver);
		return new LightboxComponentObject(driver);
	}

}