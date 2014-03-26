package com.wikia.webdriver.PageObjectsFactory.ComponentObject.Lightbox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.Common.Core.Assertion;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class LightboxComponentObject extends WikiBasePageObject {
	public LightboxComponentObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(className="WikiaLightbox")
	private WebElement lightBoxHeader;
	@FindBy(css=".thumb.thumbinner")
	private WebElement imageThumbnail;
	@FindBy(css="#LightboxModal")
	private WebElement lightBoxModal;
	@FindBy(css="span[data-pinned-title='Unpin top and bottom bars']")
	private WebElement pinButton;
	@FindBy(css="button.share-button.secondary")
	private WebElement shareButton;
	@FindBy(css="div.hero-inner")
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
	@FindBy(css="div.video-media")
	private WebElement mediaContainer;

	public void verifyLightboxPopup() {
		waitForElementByElement(lightBoxHeader);
		PageObjectLogging.log("verifyLightboxPopup", "verify lightbox appeared", true);
	}

	public void verifyLightboxVideo() {
		waitForElementByElement(mediaContainer);
		PageObjectLogging.log("verifyLightboxVideo", "verify lightbox video appeared", true);
	}

	public LightboxComponentObject openLightbox() {
		waitForElementByElement(imageThumbnail);
		scrollAndClick(imageThumbnail);
		PageObjectLogging.log("openLightbox", "opened ligthbox", true);
		return new LightboxComponentObject(driver);
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

}
