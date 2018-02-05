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
    super();
    videoEmbed = fileEmbed;
  }

  public VideoComponentObject(WebDriver driver, WebElement fileEmbed, Integer width) {
    super();
    videoEmbed = fileEmbed;
    videoWidth = width;
  }

  public WebElement getVideoPlayerObject() {
    wait.forElementVisible(videoEmbed, 30);
    wait.forElementPresent(By.cssSelector("*[name=flashvars]"));
    return videoEmbed.findElement(By.cssSelector("*[name=flashvars]"));
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

  public void verifyFlashVideoObjectVisible() {
    wait.forElementVisible(videoEmbed.findElement(By.cssSelector("object")));
    PageObjectLogging.log("verifyFlashVideoObjectVisible", "Video object is visible", true);
  }
  
  public void verifyVideoIframeVisible() {
    wait.forElementVisible(videoEmbed.findElement(By.tagName("iframe")));
    PageObjectLogging.log("verifyVideoIframeVisible", "Video iframe is visible", true);
  }

  public void verifyVideoIgnEmbed() {
    String iframeSrc = "https://widgets.ign.com/video/embed/content.html?url=";
    Assertion.assertStringContains(getVideoPlayerIframe().getAttribute("src"), iframeSrc);
    PageObjectLogging.log("verifyVideoIgnEmbed", "IGN video is embedded", true);
  }

  public void verifyVideoAnyclipEmbed() {
    WebElement container = videoEmbed.findElement(By.tagName("div"));
    String containerId = "ACPContainer0";
    Assertion.assertStringContains(container.getAttribute("id"), containerId);

    WebElement object = container.findElement(By.tagName("object"));
    wait.forElementVisible(object);
    Assertion.assertStringContains(getVideoPlayerObject().getAttribute("value"), object.getAttribute("id")
    );
    PageObjectLogging.log("verifyVideoAnyclipEmbed", "Anyclip video is embedded", true);
  }

  public void verifyVideoAutoplay(String providerName, boolean status) {
    PageObjectLogging.log("verifyVideoPlay", "Provider: " + providerName, true);

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
