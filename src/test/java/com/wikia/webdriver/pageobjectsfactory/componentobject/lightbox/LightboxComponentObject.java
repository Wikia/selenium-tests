package com.wikia.webdriver.pageobjectsfactory.componentobject.lightbox;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.componentobject.media.VideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePagePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Karol 'kkarolk' Kujawiak
 * @author Saipetch Kongkatong
 */
public class LightboxComponentObject extends WikiBasePageObject {

  private static final Integer VIDEO_WIDTH_LIGHTBOX = 737;

  @FindBy(css = ".LightboxHeader button.close.wikia-chiclet-button")
  protected WebElement closeModalButton;
  @FindBy(css = ".thumb.thumbinner")
  private WebElement imageThumbnail;
  @FindBy(css = "#LightboxModal")
  private WebElement lightBoxModal;
  @FindBy(css = "span[data-pinned-title='Unpin top and bottom bars']")
  private WebElement pinButton;
  @FindBy(css = ".WikiaLightbox .share")
  private WebElement shareScreen;
  @FindBy(css = ".WikiaLightbox a.share-button.secondary")
  private WebElement shareButton;
  @FindBy(css = "div.hero-inner img")
  private WebElement moreInfoThumbnail;
  @FindBy(css = "a.facebook")
  private WebElement facebookShareLink;
  @FindBy(css = "a.twitter")
  private WebElement twitterShareLink;
  @FindBy(css = "a.stumbleupon")
  private WebElement stumbleUponShareLink;
  @FindBy(css = "a.reddit")
  private WebElement redditShareLink;
  @FindBy(css = "a.plusone")
  private WebElement plusoneShareLink;
  @FindBy(css = ".video-media")
  private WebElement videoContainer;
  @FindBy(css = ".LightboxHeader h1 a")
  private WebElement titleLink;
  @FindBy(css = ".more-info-button")
  private WebElement moreInfoLink;
  @FindBy(css = ".WikiaLightbox div:not(.video-media)")
  private WebElement imageContainer;
  @FindBy(css = "span.carousel-arrow.next")
  private WebElement carouselRight;
  @FindBy(css = "span.carousel-arrow.previous:not(.disabled)")
  private WebElement carouselLeft;
  @FindBy(css = "span.carousel-arrow.previous.disabled")
  private WebElement carouselLeftDisabled;
  @FindBy(css = "button.more-info-close")
  private WebElement closeShareScreenButton;

  public LightboxComponentObject(WebDriver driver) {
    super(driver);
  }

  public void verifyLightboxPopup() {
    wait.forElementVisible(lightBoxModal);
    LOG.log("verifyLightboxPopup", "Lightbox appeared", LOG.Type.SUCCESS);
  }

  public void verifyLightboxVideo() {
    wait.forElementVisible(videoContainer);
    LOG.log("verifyLightboxVideo", "Lightbox video appeared", LOG.Type.SUCCESS);
  }

  public void verifyLightboxImage() {
    wait.forElementVisible(imageContainer);
    LOG.log("verifyLightboxImage", "Lightbox image appeared", LOG.Type.SUCCESS);
  }

  public LightboxComponentObject openLightbox() {
    wait.forElementVisible(imageThumbnail);
    scrollAndClick(imageThumbnail);
    LOG.log("openLightbox", "opened ligthbox", LOG.Type.SUCCESS);
    return new LightboxComponentObject(driver);
  }

  public void makeHeaderVisible() {
    wait.forElementVisible(titleLink);
    jsActions.execute("$('.LightboxHeader').css('opacity', '1')");
  }

  public void clickCloseButton() {
    wait.forElementVisible(closeModalButton);
    scrollAndClick(closeModalButton);
    LOG.log("clickCloseButton ", "lightbox closed", LOG.Type.SUCCESS);
  }

  public void clickPinButton() {
    builder.moveToElement(lightBoxModal).
        click(pinButton).
        build().
        perform();
    LOG.log("clickPinButton", "pin button was clicked", LOG.Type.SUCCESS);
  }

  public void clickShareButton() {
    wait.forElementVisible(shareButton);
    shareButton.click();
    wait.forElementVisible(moreInfoThumbnail);
    LOG.log("clickShareButton", "share button is clicked", LOG.Type.SUCCESS);
  }

  public void clickCloseShareScreenButton() {
    wait.forElementVisible(closeShareScreenButton);
    closeShareScreenButton.click();
    LOG
        .logResult("clickCloseShareScreenButton", "close share screen button was clicked", true);
  }

  public void verifyShareScreenClosed() {
    waitForElementNotVisibleByElement(shareScreen);
    LOG.log("verifyShareScreenClosed", "share screen is closed", LOG.Type.SUCCESS);
  }

  public void verifyLightboxClosed() {
    waitForElementNotVisibleByElement(lightBoxModal);
    LOG.log("verifyShareScreenClosed", "share lightbox is closed", LOG.Type.SUCCESS);
  }

  public void verifyShareButtons() {
    wait.forElementVisible(plusoneShareLink);
    wait.forElementVisible(redditShareLink);
    wait.forElementVisible(stumbleUponShareLink);
    wait.forElementVisible(twitterShareLink);
    wait.forElementVisible(facebookShareLink);
    LOG.log("verifyShareButtons", "all share buttons are visible", LOG.Type.SUCCESS);
  }

  public void clickFacebookShareButton() {
    facebookShareLink.click();
    LOG.log("clickFacebookShareButton", "fb share button is clicked", LOG.Type.SUCCESS);
  }

  public void clickTwitterShareButton() {
    twitterShareLink.click();
    LOG.log("clickTwitterShareButton", "twitter share button is clicked", LOG.Type.SUCCESS);
  }

  public void clickStumbleUponShareButton() {
    stumbleUponShareLink.click();
    LOG
        .logResult("clickStumbleUponShareButton", "stumbleupon share button is clicked", true);
  }

  public void clickRedditShareButton() {
    redditShareLink.click();
    LOG.log("clickRedditShareButton", "reddit share button is clicked", LOG.Type.SUCCESS);
  }

  public void clickPlusOneShareButton() {
    plusoneShareLink.click();
    LOG.log("clickPlusOneShareButton", "plus one share button is clicked", LOG.Type.SUCCESS);
  }

  public void verifyTitleUrl(String expectedUrl) {
    String titleUrl = titleLink.getAttribute("href");
    Assertion.assertEquals(titleUrl, expectedUrl);
    LOG.log("verifyTitleUrl", "Title URL is correct", LOG.Type.SUCCESS);
  }

  public FilePagePageObject clickTitle() {
    wait.forElementVisible(titleLink);
    titleLink.click();
    LOG.log("clickTitleUrl", "Title url is clicked", LOG.Type.SUCCESS);
    return new FilePagePageObject(driver);
  }

  public void verifyMoreInfoUrl(String expectedUrl) {
    String moreInfoUrl = moreInfoLink.getAttribute("href");
    Assertion.assertEquals(moreInfoUrl, expectedUrl);
    LOG.log("verifyMoreInfoUrl", "More Info URL is correct", LOG.Type.SUCCESS);
  }

  public void clickCarouselRight() {
    wait.forElementVisible(carouselRight);
    carouselRight.click();
    LOG.log("clickCarouselRight", "carousel right button is clicked", LOG.Type.SUCCESS);
  }

  public void clickCarouselLeft() {
    wait.forElementVisible(carouselLeft);
    carouselLeft.click();
    LOG.log("clickCarouselLeft", "carousel left button is clicked", LOG.Type.SUCCESS);
  }

  public void verifyCarouselLeftDisabled() {
    wait.forElementVisible(carouselLeftDisabled);
    LOG.log("verifyCarouselLeftDisabled", "carousel left button is disabled", LOG.Type.SUCCESS);
  }

  public void verifyVideoAutoplay(String providerName) {
    VideoComponentObject video = new VideoComponentObject(driver, videoContainer);
    video.verifyVideoAutoplay(providerName, true);
  }

  public VideoComponentObject getVideoPlayer() {
    return new VideoComponentObject(driver, videoContainer, VIDEO_WIDTH_LIGHTBOX);
  }

}
