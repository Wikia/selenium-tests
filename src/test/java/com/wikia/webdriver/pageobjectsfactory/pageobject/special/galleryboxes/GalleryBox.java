package com.wikia.webdriver.pageobjectsfactory.pageobject.special.galleryboxes;

import com.wikia.webdriver.pageobjectsfactory.componentobject.lightbox.LightboxComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class GalleryBox extends WikiBasePageObject {

	@FindBy(css = ".gallerybox a.image img:not([data-video-name=''])")
	private List<WebElement> galleryVideoBox;
	@FindBy(css = ".gallerybox a.image img:not([data-image-name=''])")
	private List<WebElement> galleryImageBox;

	public GalleryBox(WebDriver driver) {
		super(driver);
	}

	public LightboxComponentObject openLightboxForGridImage(int itemNumber) {
		scrollAndClick(galleryImageBox.get(itemNumber));
		return new LightboxComponentObject(driver);
	}

	public LightboxComponentObject openLightboxForGridVideo(int itemNumber) {
		scrollAndClick(galleryVideoBox.get(itemNumber));
		return new LightboxComponentObject(driver);
	}

}
