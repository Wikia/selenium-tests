package com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery.GalleryBuilderComponentObject;

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
	@FindBy(css=".RTEMediaOverlayEdit")
	private WebElement modifyComponentButton;
	@FindBy(css=".RTEMediaOverlayDelete")
	private WebElement removeComponentButton;
	@FindBy(css="#RTEConfirmOk > span")
	private WebElement removeConfirmationButton;

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
		Photo, Gallery, Slideshow, Slider, Video
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
		default:
			break;
		}
		driver.switchTo().defaultContent();
	}

	public GalleryBuilderComponentObject modifyComponent(Components component) {
		mouseOverComponent(component);
		modifyComponentButton.click();
		PageObjectLogging.log("modifyGallery", "Click on 'modify button' on gallery", true, driver);
		return new GalleryBuilderComponentObject(driver);
	}

	public GalleryBuilderComponentObject removeComponent(Components component) {
		mouseOverComponent(component);
		removeComponentButton.click();
		removeConfirmationButton.click();
		PageObjectLogging.log("removeGallery", "Click on 'remove button' on gallery", true);
		return new GalleryBuilderComponentObject(driver);
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
		}
		driver.switchTo().defaultContent();
		PageObjectLogging.log("verifyGalleryRemoved", "Click on 'remove button' on gallery", true);
	}
}
