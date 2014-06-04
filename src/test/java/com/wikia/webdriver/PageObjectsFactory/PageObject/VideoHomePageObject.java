package com.wikia.webdriver.PageObjectsFactory.PageObject;

import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;


/**
 * Created by liz_lux on 6/4/14.
 */


public class VideoHomePageObject extends WikiBasePageObject {

	@FindBy(css=".featured-video-slider .bx-controls")
	private WebElement featuredModuleControls;
	@FindBy(css=".latest-videos-wrapper .carousel-wrapper")
	private WebElement thirdLatestVideoRow;


	public VideoHomePageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void verifyFeaturedSliderInitialized() {
		waitForElementByElement(featuredModuleControls);
		PageObjectLogging.log("verifyFeaturedSliderInitialized", "Featured video slider has initialized", true);
	}

	public void verify3LatestVideosRows() {
		waitForElementByElement(thirdLatestVideoRow);
		PageObjectLogging.log("verify3LatestVideosRows", "At least three latest Videos modules have rendered", true);
	}
}
