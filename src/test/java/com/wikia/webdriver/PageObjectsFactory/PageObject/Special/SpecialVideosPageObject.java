package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Lightbox.LightboxComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Watch.WatchPageObject;

/**
 * @author Garth Webb
 * @author Saipetch Kongkatong
 */
public class SpecialVideosPageObject extends SpecialPageObject {

	@FindBy(css = "a.addVideo")
	private WebElement addVideo;
	@FindBy(css = "div.WikiaGrid div:nth-child(1).grid-2")
	private WebElement newestVideo;
	@FindBy(css = "div.WikiaGrid div:nth-child(1).grid-2 .info-overlay-title")
	private WebElement newestVideoTitle;
	@FindBy(css = "div.WikiaGrid div:nth-child(1).grid-2 .remove")
	private WebElement newestVideoDeleteIcon;
	@FindBys(@FindBy(css=".image.video > img"))
	private List<WebElement> videos;
	@FindBy(css = ".VideoGrid a.video img.play")
	private List<WebElement> galleryVideoBox;
	@FindBy(css = "#WikiaConfirmOk")
	private WebElement deleteConfirmButton;

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

	public String getFileUrl(String wikiURL, int itemNumber) {
		String fileUrl = wikiURL + URLsContent.wikiDir + URLsContent.fileNameSpace + getVideoKey(itemNumber);
		PageObjectLogging.log("getFileUrl", "File url: " + fileUrl, true);
		return fileUrl;
	}

	public String getVideoKey(int itemNumber) {
		String videoKey = videos.get(itemNumber).getAttribute("data-video-key");
		PageObjectLogging.log("getVideoKey", "Video key: " + videoKey, true);
		return videoKey;
	}

	public String getNewestVideoTitle() {
		waitForElementByElement(newestVideo);
		return newestVideoTitle.getText();
	}

	public void deleteVideo(){
		executeScript("$('div.WikiaGrid div:nth-child(1).grid-2 .remove').show()");
		waitForElementByElement(newestVideo);
		newestVideoDeleteIcon.click();
		waitForElementByElement(deleteConfirmButton);
		deleteConfirmButton.click();
	}

	public void verifyDeleteViaGlobalNotifications() {
		addVideoViaAjax(VideoContent.youtubeVideoURL2);
		refreshPage();
		deleteVideo();
		String deletedVideo = "\"File:" + VideoContent.youtubeVideoURL2name + "\" has been deleted. (undelete)";
		Assertion.assertEquals(deletedVideo, getFlashMessageText());
		PageObjectLogging.log("verifyDeleteVideoGlobalNotifications", "verify video " + deletedVideo + " was deleted", true);
	}

	public void verifyDeleteViaVideoNotPresent() {
		addVideoViaAjax(VideoContent.youtubeVideoURL2);
		refreshPage();
		deleteVideo();
		verifyNotificationMessage();
		Assertion.assertNotEquals(VideoContent.youtubeVideoURL2name, getNewestVideoTitle());
		PageObjectLogging.log("verifyDeleteVideoNotPresent",
				"verify video " + VideoContent.youtubeVideoURL2name + " was deleted",
				true);
	}

}
