package com.wikia.webdriver.pageobjectsfactory.componentobject.media;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
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
    wait.forElementVisible(videoEmbed);
    return videoEmbed.findElement(By.cssSelector("*[name=flashvars]"));
  }

  public WebElement getVideoPlayerIframe() {
    return videoEmbed.findElement(By.cssSelector("iframe"));
  }

  public void verifyVideoEmbedWidth() {
    Assertion.assertEquals(videoEmbed.findElement(By.tagName("div")).getCssValue("width"),
                           videoWidth + "px");
    LOG.success("verifyVideoEmbedWidth", "Width: " + videoWidth);
  }

  public void verifyVideoIframeWidth() {
    Assertion.assertEquals(videoEmbed.findElement(By.tagName("iframe")).getAttribute("width"),
                           videoWidth.toString());
    LOG.success("verifyVideoIframeWidth", "Width: " + videoWidth);
  }

  public void verifyVideoOoyalaAgeGate() {
    String ageGateClass = "ageGate";
    Assertion.assertTrue(videoEmbed.findElement(By.className(ageGateClass)) != null);
    LOG.success("verifyVideoOoyalaAgeGate", "Age gate module is enabled");
  }

  public void verifyVideoOoyalaEmbed() {
    WebElement container = videoEmbed.findElement(By.tagName("div"));
    String containerId = "ooyalaplayer-";
    Assertion.assertStringContains(container.getAttribute("id"), containerId);
    wait.forElementVisible(container.findElement(By.tagName("object")));
    LOG.success("verifyVideoOoyalaEmbed", "Ooyala video is embedded");
  }

  public void verifyVideoObjectVisible() {
    wait.forElementVisible(videoEmbed.findElement(By.tagName("object")));
    LOG.success("verifyVideoObjectVisible", "Video object is visible");
  }

  public void verifyVideoIframeVisible() {
    wait.forElementVisible(videoEmbed.findElement(By.tagName("iframe")));
    LOG.success("verifyVideoIframeVisible", "Video iframe is visible");
  }

  public void verifyVideoIgnEmbed() {
    String iframeSrc = "http://widgets.ign.com/video/embed/content.html?url=";
    Assertion.assertStringContains(getVideoPlayerIframe().getAttribute("src"), iframeSrc);
    LOG.success("verifyVideoIgnEmbed", "IGN video is embedded");
  }

  public void verifyVideoAnyclipEmbed() {
    WebElement container = videoEmbed.findElement(By.tagName("div"));
    String containerId = "ACPContainer0";
    Assertion.assertStringContains(container.getAttribute("id"), containerId);

    WebElement object = container.findElement(By.tagName("object"));
    wait.forElementVisible(object);
    Assertion.assertStringContains(getVideoPlayerObject().getAttribute("value"), object.getAttribute("id")
    );
    LOG.success("verifyVideoAnyclipEmbed", "Anyclip video is embedded");
  }

  public void verifyVideoAutoplay(String providerName, boolean status) {
    LOG.success("verifyVideoAutoplay", "Provider: " + providerName);

    String autoplayStr = "";
    String embedCode = "";
    switch (providerName) {
      case "screenplay":
        autoplayStr = "&autoplay=" + ((status) ? 1 : 0);
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

    Assertion.assertStringContains(embedCode, autoplayStr);
  }

}
