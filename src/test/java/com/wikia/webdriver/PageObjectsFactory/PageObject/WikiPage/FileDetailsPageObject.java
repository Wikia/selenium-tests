package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

public class FileDetailsPageObject extends WikiBasePageObject{

	public FileDetailsPageObject(WebDriver driver, String Domain) {
		super(driver, Domain);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div.fullImageLink")
	private WebElement videoEmbedded;
	@FindBy(css=".filehistory img.Wikia-video-thumb")
	private WebElement videoThumbnail;
	
	public void verifyEmbeddedVideoIsPresent() {		
		waitForElementByElement(videoEmbedded);
		PageObjectLogging.log("verifyEmbeddedVideoIsPresent", "Verified embedded video is visible", true);
		
	}
	
	public void verifythumbnailIsPresent() {	
		waitForElementByElement(videoThumbnail);
		PageObjectLogging.log("verifythumbnailIsPresent", "Verified thumbnail is visible", true);
		
	}
}
