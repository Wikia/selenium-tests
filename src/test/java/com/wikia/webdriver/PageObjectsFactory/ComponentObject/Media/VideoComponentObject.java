package com.wikia.webdriver.PageObjectsFactory.ComponentObject.Media;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VideoComponentObject extends WikiBasePageObject{

	protected WebElement videoEmbed;

	public VideoComponentObject(WebDriver driver, WebElement fileEmbed) {
		super(driver);
		videoEmbed = fileEmbed;
	}

	public WebElement getVideoPlayerObject() {
		return videoEmbed.findElement(By.cssSelector("[name=flashvars]"));
	}

	public WebElement getVideoPlayerIframe() {
		return videoEmbed.findElement(By.cssSelector("iframe"));
	}

	public void verifyVideoEmbedWidth(Integer videoWidth) {
		Assertion.assertEquals(videoEmbed.findElement(By.tagName("div")).getCssValue("width"), videoWidth+"px");
		PageObjectLogging.log("verifyVideoEmbedWidth", "Width: "+videoWidth, true);
	}

	public void verifyVideoEmbedWidthIframe(Integer videoWidth) {
		Assertion.assertEquals(videoEmbed.findElement(By.tagName("iframe")).getAttribute("width"), videoWidth);
		PageObjectLogging.log("verifyVideoEmbedWidthIframe", "Width: "+videoWidth, true);
	}

	public void verifyVideoOoyalaAgeGate() {
		String ageGateClass = "ageGate";
		Assertion.assertTrue(videoEmbed.findElement(By.className(ageGateClass)) != null);
		PageObjectLogging.log("verifyVideoOoyalaAgeGate", "Age gate module is enabled", true);
	}

	public void verifyVideoOoyalaEmbed() {
		WebElement container = videoEmbed.findElement(By.tagName("div"));

		String containerId = "ooyalaplayer-";
		Assertion.assertStringContains(container.getAttribute("id"), containerId);

		WebElement object = container.findElement(By.tagName("object"));
		Assertion.assertTrue( object.isDisplayed() );

		PageObjectLogging.log("verifyVideoOoyalaEmbed", "Ooyala video is embedded", true);
	}

	public void verifyVideoAnyclipEmbed() {
		WebElement container = videoEmbed.findElement(By.tagName("div"));

		String containerId = "ACPContainer0";
		Assertion.assertStringContains(container.getAttribute("id"), containerId);

		WebElement object = container.findElement(By.tagName("object"));
		Assertion.assertTrue( object.isDisplayed() );
		Assertion.assertStringContains(getVideoPlayerObject().getAttribute("value"), object.getAttribute("id"));

		PageObjectLogging.log("verifyVideoAnyclipEmbed", "Anyclip video is embedded", true);
	}

	public void verifyVideoAutoplay(String providerName, boolean status) {
		PageObjectLogging.log("verifyVideoAutoplay", "Provider: "+providerName, true);

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

		Assertion.assertStringContains(embedCode, autoplayStr);
	}

}