package com.wikia.webdriver.PageObjectsFactory.ComponentObject.RightRail;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Lightbox.LightboxComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class RelatedVideoComponentObject extends WikiBasePageObject {

	@FindBy(css=".RelatedVideos .video.lightbox")
	private List<WebElement> relatedVideoThumbnails;

	public RelatedVideoComponentObject(WebDriver driver) {
		super(driver);
	}

	public LightboxComponentObject openLightboxForVideo(int videoNumber) {
		scrollAndClick(relatedVideoThumbnails.get(videoNumber));
		return new LightboxComponentObject(driver);
	}
}
