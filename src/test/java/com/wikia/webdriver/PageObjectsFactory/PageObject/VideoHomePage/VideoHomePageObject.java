package com.wikia.webdriver.PageObjectsFactory.PageObject.VideoHomePage;

import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Core.Assertion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


/**
 * Created by Liz Lee on 6/4/14.
 */


public class VideoHomePageObject extends WikiBasePageObject {

	@FindBy(css=".featured-video-slider .bx-controls")
	private WebElement featuredModuleControls;
	@FindBys(@FindBy(css="#featured-video-bxslider li"))
	private List<WebElement> featuredSlides;
	@FindBys(@FindBy(css=".latest-videos-wrapper .carousel-wrapper"))
	private List<WebElement> latestVideoRows;

	public VideoHomePageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void verifyFeaturedSliderInitialized() {
		waitForElementByElement(featuredModuleControls);
		PageObjectLogging.log("verifyFeaturedSliderInitialized", "Featured video slider has initialized", true);
	}

	public void verifyFeaturedSliderSlides(int count) {
		waitForElementByElement(featuredSlides.get(0));
		Assertion.assertTrue(featuredSlides.size() >= count);
		PageObjectLogging.log("verifyFeaturedSliderSlides", "At least " + count + "latest Videos modules have rendered", true);
	}

	public void verifyLatestVideosRows(int count) {
		waitForElementByElement(latestVideoRows.get(0));
		Assertion.assertTrue(latestVideoRows.size() >= count);
		PageObjectLogging.log("verifyLatestVideosRows", "At least " + count + "latest Videos modules have rendered", true);
	}
}
