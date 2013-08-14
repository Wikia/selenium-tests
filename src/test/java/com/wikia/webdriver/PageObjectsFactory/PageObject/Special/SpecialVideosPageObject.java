package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Watch.WatchPageObject;

public class SpecialVideosPageObject extends SpecialPageObject{

	@FindBy(css = "a.addVideo")
	private WebElement addVideo;
	@FindBy(css = "div.WikiaGrid div:nth-child(1).grid-2")
	private WebElement newestVideo;
	@FindBys(@FindBy(css=".image.video > img"))
	private List<WebElement> videos;

	public SpecialVideosPageObject(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this);
	}

	public String getRandomVideo() {
		List<String> names = new ArrayList();
		for (WebElement elem:videos) {
			names.add(elem.getAttribute("data-video-name"));
		}
		Random r = new Random();
		int rnd = r.nextInt(names.size()-1);
		return names.get((rnd)+1);
	}

	public WatchPageObject unfollowVideo(String wikiURL, String videoName) {
		getUrl(
				wikiURL +
				URLsContent.wikiDir +
				URLsContent.fileNS +
				videoName+
				"?action=unwatch"
        );
        return new WatchPageObject(driver);
	}

	public VetAddVideoComponentObject clickAddAVideo() {
		waitForElementByElement(addVideo);
		waitForElementClickableByElement(addVideo);
		scrollAndClick(addVideo);
		PageObjectLogging.log("clickAddAVideo", "click on 'add a video' button", true, driver);
		return new VetAddVideoComponentObject(driver);
	}

	public void verifyVideoAdded(String videoDescription) {
		waitForElementByElement(newestVideo);
		List<WebElement> videoDescriptonElem = newestVideo.findElements(By.cssSelector("a.image.video span.info-overlay-title"));
		Assertion.assertEquals(videoDescription, videoDescriptonElem.get(0).getText());
		PageObjectLogging.log("verifyVideoAdded", "verify that video with following descriotion was added: "+videoDescription, true);
	}
}
