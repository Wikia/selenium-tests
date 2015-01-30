package com.wikia.webdriver.pageobjectsfactory.componentobject.media;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VideoComponentObject extends WikiBasePageObject {

  protected WebElement videoEmbed;
  protected Integer videoWidth;

  public VideoComponentObject(WebDriver driver, WebElement fileEmbed) {
    super(driver);
    videoEmbed = fileEmbed;
  }

  public VideoComponentObject(WebDriver driver, WebElement fileEmbed, Integer width) {
    super(driver);
    videoEmbed = fileEmbed;
    videoWidth = width;
  }

  public WebElement getVideoPlayerObject() {
    return videoEmbed.findElement(By.cssSelector("[name=flashvars]"));
  }

  public WebElement getVideoPlayerIframe() {
    return videoEmbed.findElement(By.cssSelector("iframe"));
  }

  public void verifyVideoEmbedWidth() {
    Assertion.assertEquals(videoEmbed.findElement(By.tagName("div")).getCssValue("width"),
                           videoWidth + "px");
    PageObjectLogging.log("verifyVideoEmbedWidth", "Width: " + videoWidth, true);
  }

  public void verifyVideoIframeWidth() {
    Assertion.assertEquals(videoEmbed.findElement(By.tagName("iframe")).getAttribute("width"),
                           videoWidth.toString());
    PageObjectLogging.log("verifyVideoIframeWidth", "Width: " + videoWidth, true);
  }

  public void verifyVideoOoyalaAgeGate() {
    String ageGateClass = "ageGate";
    Assertion.assertTrue(videoEmbed.findElement(By.className(ageGateClass)) != null);
    PageObjectLogging.log("verifyVideoOoyalaAgeGate", "Age gate module is enabled", true);
  }

  public void verifyVideoOoyalaEmbed() {
    WebElement container = videoEmbed.findElement(By.tagName("div"));
    String containerId = "ooyalaplayer-";
    Assertion.assertStringContains(containerId, container.getAttribute("id"));
    waitForElementVisibleByElement(container.findElement(By.tagName("object")));
    PageObjectLogging.log("verifyVideoOoyalaEmbed", "Ooyala video is embedded", true);
  }

  public void verifyVideoObjectVisible() {
    waitForElementVisibleByElement(videoEmbed.findElement(By.tagName("object")));
    PageObjectLogging.log("verifyVideoObjectVisible", "Video object is visible", true);
  }

  public void verifyVideoIframeVisible() {
    waitForElementVisibleByElement(videoEmbed.findElement(By.tagName("iframe")));
    PageObjectLogging.log("verifyVideoIframeVisible", "Video iframe is visible", true);
  }

  public void verifyVideoIgnEmbed() {
    String iframeSrc = "http://widgets.ign.com/video/embed/content.html?url=";
    Assertion.assertStringContains(iframeSrc, getVideoPlayerIframe().getAttribute("src"));
    PageObjectLogging.log("verifyVideoIgnEmbed", "IGN video is embedded", true);
  }

  public void verifyVideoAnyclipEmbed() {
    WebElement container = videoEmbed.findElement(By.tagName("div"));
    String containerId = "ACPContainer0";
    Assertion.assertStringContains(containerId, container.getAttribute("id"));

    WebElement object = container.findElement(By.tagName("object"));
    waitForElementVisibleByElement(object);
    Assertion.assertStringContains(object.getAttribute("id"),
                                   getVideoPlayerObject().getAttribute("value"));
    PageObjectLogging.log("verifyVideoAnyclipEmbed", "Anyclip video is embedded", true);
  }

  public void verifyVideoAutoplay(String providerName, boolean status) {
    PageObjectLogging.log("verifyVideoAutoplay", "Provider: " + providerName, true);

    String autoplayStr = "";
    String embedCode = "";
    switch (providerName) {
      case "screenplay":
        autoplayStr = "autostart=" + status;
        embedCode = getVideoPlayerObject().getAttribute("value");
        break;
      case "ign":
        autoplayStr = "&autoplay=" + status;
        embedCode = getVideoPlayerIframe().getAttribute("src");
        break;
      case "anyclip":
        autoplayStr = "&autoPlay=" + status;
        embedCode = getVideoPlayerObject().getAttribute("value");
        break;
      case "youtube":
        autoplayStr = "&autoplay=" + ((status) ? 1 : 0);
        embedCode = getVideoPlayerIframe().getAttribute("src");
        break;
      case "vimeo":
        autoplayStr = "?autoplay=" + ((status) ? 1 : 0);
        embedCode = getVideoPlayerIframe().getAttribute("src");
        break;
      case "gamestar":
      case "hulu":
      case "dailymotion":
      case "myvideo":
      case "snappytv":
      case "ustream":
      case "fivemin":
      case "metacafe":
      case "movieclips":
      case "sevenload":
      case "gametrailers":
      case "viddler":
      case "bliptv":
      case "twitchtv":
      case "youku":
        break;
      // for ooyala videos
      default:
        autoplayStr = "&autoplay=" + ((status) ? 1 : 0);
        embedCode = getVideoPlayerObject().getAttribute("value");
        break;
    }

    Assertion.assertStringContains(autoplayStr, embedCode);
  }

}
