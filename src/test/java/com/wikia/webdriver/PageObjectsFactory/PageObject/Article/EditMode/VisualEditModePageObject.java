package com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.CommonUtils;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slider.SliderBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slideshow.SlideshowBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class VisualEditModePageObject extends EditMode {

	@FindBy(css="#bodyContent")
	private WebElement contentInput;
	@FindBy(css="div.cke_wrapper.cke_ltr div.cke_contents iframe")
	private WebElement iframe;
	@FindBy(css="img.image")
	private WebElement image;
	@FindBy(css="img.image-gallery")
	private WebElement gallery;
	@FindBy(css="img.image-slideshow")
	private WebElement slideshow;
	@FindBy(css="img.image-gallery-slider")
	private WebElement slider;
	@FindBy(css="img.video")
	private WebElement video;
	@FindBy(css="img.video-placeholder")
	private WebElement videoPlaceholder;
	@FindBy(css=".RTEMediaOverlayEdit")
	private WebElement modifyComponentButton;
	@FindBy(css="[style*=\"block\"] .RTEMediaOverlayDelete")
	private WebElement removeComponentButton;
	@FindBy(css="#RTEConfirmOk > span")
	private WebElement removeConfirmationButton;
	@FindBy(css="#wpTextbox1")
	private WebElement messageSourceModeTextArea;

	private By imageBy = By.cssSelector("img.image");
	private By galleryBy = By.cssSelector("img.image-gallery");
	private By slideshowBy = By.cssSelector("img.image-slideshow");
	private By sliderBy = By.cssSelector("img.image-gallery-slider");
	private By videoBy = By.cssSelector("img.video");

	public VisualEditModePageObject(WebDriver driver) {
		super(driver);
	}

	public void clearContent() {
		driver.switchTo().frame(iframe);
		contentInput.clear();
		driver.switchTo().defaultContent();
	}

	public void addContent(String content) {
		driver.switchTo().frame(iframe);
		contentInput.clear();
		contentInput.sendKeys(content);
		driver.switchTo().defaultContent();
	}

	private void verifyComponent(WebElement component){
		driver.switchTo().frame(iframe);
		waitForElementByElement(component);
		driver.switchTo().defaultContent();
	}

	public void verifyPhoto() {
		verifyComponent(image);
	}

	public void verifyGallery() {
		verifyComponent(gallery);
	}

	public void verifySlideshow() {
		verifyComponent(slideshow);
	}

	public void verifySlider() {
		verifyComponent(slider);
	}

	public void verifyVideo() {
		verifyComponent(video);
	}

	public enum Components {
		Photo, Gallery, Slideshow, Slider, Video, VideoPlaceholder
	}

	private void mouseOverComponent (Components component) {
		Actions actions = new Actions(driver);
		driver.switchTo().frame(iframe);
		switch (component) {
		case Gallery:
			actions.moveToElement(gallery).build().perform();
			break;
		case Slideshow:
			actions.moveToElement(slideshow).build().perform();
			break;
		case Slider:
			actions.moveToElement(slider).build().perform();
			break;
		case Video:
			actions.moveToElement(video).build().perform();
			break;
		case Photo:
			actions.moveToElement(image).build().perform();
			break;
		case VideoPlaceholder:
			actions.moveToElement(videoPlaceholder).build().perform();
			break;
		default:
			break;
		}
		driver.switchTo().defaultContent();
	}

	public Object modifyComponent(Components component) {
		mouseOverComponent(component);
		modifyComponentButton.click();
		PageObjectLogging.log("modifyGallery", "Click on 'modify button' on gallery", true, driver);
		switch (component) {
		case Gallery:
			return new GalleryBuilderComponentObject(driver);
		case Photo:
			return new PhotoAddComponentObject(driver);
		case Slider:
			return new SliderBuilderComponentObject(driver);
		case Slideshow:
			return new SlideshowBuilderComponentObject(driver);
		case Video:
			return new VetAddVideoComponentObject(driver);
		case VideoPlaceholder:
			return new VetAddVideoComponentObject(driver);
		default:
			return null;
		}
	}

	public void removeComponent(Components component) {
		mouseOverComponent(component);
		removeComponentButton.click();
		removeConfirmationButton.click();
		PageObjectLogging.log("removeGallery", "Click on 'remove button' on gallery", true);
	}

	public void verifyComponentRemoved(Components component) {
		driver.switchTo().frame(iframe);
		switch (component) {
		case Photo:
			waitForElementNotPresent(imageBy);
		case Gallery:
			waitForElementNotPresent(galleryBy);
		case Slideshow:
			waitForElementNotPresent(slideshowBy);
		case Slider:
			waitForElementNotPresent(sliderBy);
		case Video:
			waitForElementNotPresent(videoBy);
		default:
			break;
		}
		driver.switchTo().defaultContent();
		PageObjectLogging.log("verifyGalleryRemoved", "Click on 'remove button' on gallery", true);
	}

	/**
	 * Delete unwanted video by its name.
	 * Message article page is e.g http://mediawiki119.wikia.com/wiki/MediaWiki:RelatedVideosGlobalList
	 * This method destination is exactly related videos message article
	 *
	 * @author Michal Nowierski
	 * @param unwantedVideoName e.g "What is love (?) - on piano (Haddway)"
	 */
	public void deleteUnwantedVideoFromMessage(String unwantedVideoName) {
		ArrayList<String> videos = new ArrayList<String>();
		waitForElementByElement(messageSourceModeTextArea);
		String sourceText = messageSourceModeTextArea.getText();
		int index = 0;
		while (true) {
			int previousStarIndex = sourceText.indexOf("*", index);
			int nextStarIndex = sourceText.indexOf("*", previousStarIndex+1);
			if (nextStarIndex<0) {
				break;
			}
			String video = sourceText.substring(previousStarIndex, nextStarIndex);
			if (!video.contains(unwantedVideoName)) {
				videos.add(video);
			}
			index = previousStarIndex+1;
		}
		waitForElementByElement(messageSourceModeTextArea);
		messageSourceModeTextArea.clear();
		messageSourceModeTextArea.sendKeys("WHITELIST");
		messageSourceModeTextArea.sendKeys(Keys.ENTER);
		messageSourceModeTextArea.sendKeys(Keys.ENTER);
		String builder = "";
		for (int i = 0; i<videos.size(); i++) {
			builder+=videos.get(i);
			builder+="\n";
		}
		CommonUtils.setClipboardContents(builder);
		messageSourceModeTextArea.sendKeys(Keys.chord(Keys.CONTROL, "v"));
		PageObjectLogging.log("deleteUnwantedVideoFromMessage", "Delete all source code on the article", true, driver);
	}
}
