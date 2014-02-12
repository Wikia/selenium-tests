package com.wikia.webdriver.PageObjectsFactory.ComponentObject.RightRail;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Lightbox.LightboxComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class LatestPhotosComponentObject extends WikiBasePageObject {

	@FindBy(css=".carousel li.thumbs")
	private List<WebElement> latestPhotosList;

	public LatestPhotosComponentObject(WebDriver driver) {
		super(driver);
	}

	public LightboxComponentObject openLightboxForImage(int imageNumber) {
		scrollAndClick(latestPhotosList.get(imageNumber));
		PageObjectLogging.log("openLightboxForImage", "lightbox for image opened", true);
		return new LightboxComponentObject(driver);
	}
}
