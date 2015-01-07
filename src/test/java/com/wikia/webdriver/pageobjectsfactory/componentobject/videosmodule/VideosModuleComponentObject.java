package com.wikia.webdriver.pageobjectsfactory.componentobject.videosmodule;

import com.wikia.webdriver.common.core.Assertion;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import java.util.List;

/**
 * @author James Sutterfield
 */
public class VideosModuleComponentObject extends WikiBasePageObject {

	@FindBy(css="#videosModule")
	private WebElement videosModuleContainer;
	@FindBy(css="#videosModule img")
	private List<WebElement> videos;
	private static final int VIDEO_COUNT_MIN = 3;
	private static final int VIDEO_COUNT_MAX = 5;

	public VideosModuleComponentObject (WebDriver driver) {
		super(driver);
	}

	public void verifyVideosModuleShowing() {
		Assertion.assertTrue(checkIfElementOnPage(videosModuleContainer));
		PageObjectLogging.log("verifyVideosModuleShowing", "Videos Module showing", true);
	}

	public void verifyVideosModuleNotShowing() {
		Assertion.assertTrue(!checkIfElementOnPage(videosModuleContainer));
		PageObjectLogging.log("verifyVideosModuleNotShowing", "Videos Module not showing (test passed)", true);
	}

	public void verifyDisplayCount() {
		Assertion.assertTrue(videos.size() >= VIDEO_COUNT_MIN && videos.size() <= VIDEO_COUNT_MAX);
		PageObjectLogging.log("verifyDisplayCount", "Videos Module showing correct number of videos", true);
	}

	public void verifyNoDuplicates() {
		String videoTitle1, videoTitle2;
		for (int i = 0; i < videos.size() - 1; i++) {
			for (int j = i + 1; j < videos.size(); j++) {
				videoTitle1 = videos.get(i).getAttribute("data-video-key");
				videoTitle2 = videos.get(j).getAttribute("data-video-key");
				Assertion.assertNotEquals(videoTitle1, videoTitle2);
			}
		}
		PageObjectLogging.log("verifyNoDuplicates", "Videos Module not showing duplicates", true);
	}
}
