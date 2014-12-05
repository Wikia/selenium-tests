package com.wikia.webdriver.pageobjectsfactory.componentobject.lightbox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.media.VideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 * @author Saipetch Kongkatong
 */
public class LightboxComponentObject extends WikiBasePageObject {
	public LightboxComponentObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css=".thumb.thumbinner")
	private WebElement imageThumbnail;
	@FindBy(css="#LightboxModal")
	private WebElement lightBoxModal;
	@FindBy(css="span[data-pinned-title='Unpin top and bottom bars']")
	private WebElement pinButton;
	@FindBy(css=".WikiaLightbox .share")
	private WebElement shareScreen;
	@FindBy(css=".WikiaLightbox a.share-button.secondary")
	private WebElement shareButton;
	@FindBy(css="div.hero-inner img")
	private WebElement moreInfoThumbnail;
	@FindBy(css="a.facebook")
	private WebElement facebookShareLink;
	@FindBy(css="a.twitter")
	private WebElement twitterShareLink;
	@FindBy(css="a.stumbleupon")
	private WebElement stumbleUponShareLink;
	@FindBy(css="a.reddit")
	private WebElement redditShareLink;
	@FindBy(css="a.plusone")
	private WebElement plusoneShareLink;
	@FindBy(css=".video-media")
	private WebElement videoContainer;
	@FindBy(css=".LightboxHeader h1 a")
	private WebElement titleLink;
	@FindBy(css=".more-info-button")
	private WebElement moreInfoLink;
	@FindBy(css=".WikiaLightbox div:not(.video-media)")
	private WebElement imageContainer;
	@FindBy(css="span.carousel-arrow.next")
	private WebElement carouselRight;
	@FindBy(css="span.carousel-arrow.previous:not(.disabled)")
	private WebElement carouselLeft;
	@FindBy(css="span.carousel-arrow.previous.disabled")
	private WebElement carouselLeftDisabled;
	@FindBy(css="button.more-info-close")
	private WebElement closeShareScreenButton;
	@FindBy(css=".LightboxHeader button.close.wikia-chiclet-button")
	protected WebElement closeModalButton;

	private final Integer videoWidthLightbox = 737;

	public void verifyLightboxPopup() {
		waitForElementByElement(lightBoxModal);
		PageObjectLogging.log("verifyLightboxPopup", "Lightbox appeared", true);
	}

	public void verifyLightboxVideo() {
		waitForElementByElement(videoContainer);
		PageObjectLogging.log("verifyLightboxVideo", "Lightbox video appeared", true);
	}

	public void verifyLightboxImage() {
		waitForElementByElement(imageContainer);
		PageObjectLogging.log("verifyLightboxImage", ":Lightbox image appeared", true);
	}

	public LightboxComponentObject openLightbox() {
		waitForElementByElement(imageThumbnail);
		scrollAndClick(imageThumbnail);
		PageObjectLogging.log("openLightbox", "opened ligthbox", true);
		return new LightboxComponentObject(driver);
	}

	public void makeHeaderVisible() {
		waitForElementByElement(titleLink);
		executeScript("$('.LightboxHeader').css('opacity', '1')");
	}

	public void clickCloseButton() {
		waitForElementByElement(closeModalButton);
		scrollAndClick(closeModalButton);
		PageObjectLogging.log("clickCloseButton ", "lightbox closed", true);
	}

	public void clickPinButton() {
		builder.moveToElement(lightBoxModal).
				click(pinButton).
				build().
				perform();
		PageObjectLogging.log("clickPinButton", "pin button was clicked", true);
	}

	public void clickShareButton() {
		waitForElementByElement(shareButton);
		shareButton.click();
		waitForElementByElement(moreInfoThumbnail);
		PageObjectLogging.log("clickShareButton", "share button is clicked", true);
	}

	public void clickCloseShareScreenButton() {
		waitForElementByElement(closeShareScreenButton);
		closeShareScreenButton.click();
		PageObjectLogging.log("clickCloseShareScreenButton", "close share screen button was clicked", true);
	}

	public void verifyShareScreenClosed() {
		waitForElementNotVisibleByElement(shareScreen);
		PageObjectLogging.log("verifyShareScreenClosed", "share screen is closed", true);
	}

	public void verifyLightboxClosed() {
		waitForElementNotVisibleByElement(lightBoxModal);
		PageObjectLogging.log("verifyShareScreenClosed", "share lightbox is closed", true);
	}

	public void verifyShareButtons() {
		waitForElementByElement(plusoneShareLink);
		waitForElementByElement(redditShareLink);
		waitForElementByElement(stumbleUponShareLink);
		waitForElementByElement(twitterShareLink);
		waitForElementByElement(facebookShareLink);
		PageObjectLogging.log("verifyShareButtons", "all share buttons are visible", true);
	}

	public void clickFacebookShareButton() {
		facebookShareLink.click();
		PageObjectLogging.log("clickFacebookShareButton", "fb share button is clicked", true);
	}

	public void clickTwitterShareButton() {
		twitterShareLink.click();
		PageObjectLogging.log("clickTwitterShareButton", "twitter share button is clicked", true);
	}

	public void clickStumbleUponShareButton() {
		stumbleUponShareLink.click();
		PageObjectLogging.log("clickStumbleUponShareButton", "stumbleupon share button is clicked", true);
	}

	public void clickRedditShareButton() {
		redditShareLink.click();
		PageObjectLogging.log("clickRedditShareButton", "reddit share button is clicked", true);
	}

	public void clickPlusOneShareButton() {
		plusoneShareLink.click();
		PageObjectLogging.log("clickPlusOneShareButton", "plus one share button is clicked", true);
	}

	public void verifyTitleUrl(String expectedUrl) {
		String titleUrl = titleLink.getAttribute("href");
		Assertion.assertEquals(expectedUrl, titleUrl);
		PageObjectLogging.log("verifyTitleUrl", "Title URL is correct", true);
	}

	public FilePagePageObject clickTitle() {
		waitForElementByElement(titleLink);
		titleLink.click();
		PageObjectLogging.log("clickTitleUrl", "Title url is clicked", true);
		return new FilePagePageObject(driver);
	}

	public void verifyMoreInfoUrl(String expectedUrl) {
		String moreInfoUrl = moreInfoLink.getAttribute("href");
		Assertion.assertEquals(expectedUrl, moreInfoUrl);
		PageObjectLogging.log("verifyMoreInfoUrl", "More Info URL is correct", true);
	}

	public void clickCarouselRight() {
		waitForElementByElement(carouselRight);
		carouselRight.click();
		PageObjectLogging.log("clickCarouselRight", "carousel right button is clicked", true);
	}

	public void clickCarouselLeft() {
		waitForElementByElement(carouselLeft);
		carouselLeft.click();
		PageObjectLogging.log("clickCarouselLeft", "carousel left button is clicked", true);
	}

	public void verifyCarouselLeftDisabled() {
		waitForElementByElement(carouselLeftDisabled);
		PageObjectLogging.log("verifyCarouselLeftDisabled", "carousel left button is disabled", true);
	}

	public void verifyVideoAutoplay(String providerName) {
		VideoComponentObject video = new VideoComponentObject(driver, videoContainer);
		video.verifyVideoAutoplay(providerName, true);
	}

	public VideoComponentObject getVideoPlayer() {
		return new VideoComponentObject(driver, videoContainer, videoWidthLightbox);
	}

}
