package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Lightbox.LightboxComponentObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Watch.WatchPageObject;

public class SpecialVideosPageObject extends SpecialPageObject {

	@FindBy(css = "a.addVideo")
	private WebElement addVideo;
	@FindBy(css = "div.WikiaGrid div:nth-child(1).grid-2")
	private WebElement newestVideo;
	@FindBys(@FindBy(css=".image.video > img"))
	private List<WebElement> videos;
	@FindBy(css = ".VideoGrid a.video img.play")
	private List<WebElement> galleryVideoBox;

	public SpecialVideosPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public String getRandomVideo() {
		List<String> names = new ArrayList();
		for (WebElement elem:videos) {
			names.add(elem.getAttribute("data-video-key"));
		}
		Random r = new Random();
		int rnd = r.nextInt(names.size()-1);
		return names.get((rnd)+1);
	}

	public WatchPageObject unfollowVideo(String wikiURL, String videoName) {
		getUrl(
				wikiURL +
				URLsContent.wikiDir +
				URLsContent.fileNameSpace +
				videoName +
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
		waitForTextToBePresentInElementByElement(newestVideo, videoDescription);
		PageObjectLogging.log("verifyVideoAdded", "verify that video with following descriotion was added: "+videoDescription, true);
	}

	public LightboxComponentObject openLightboxForGridVideo(int itemNumber) {
		scrollAndClick(galleryVideoBox.get(itemNumber));
		return new LightboxComponentObject(driver);
	}
}
