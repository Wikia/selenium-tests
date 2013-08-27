package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

public class FileDetailsPageObject extends WikiBasePageObject{

	public FileDetailsPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="div.fullImageLink")
	private WebElement videoEmbedded;
	@FindBy(css=".filehistory img.Wikia-video-thumb")
	private WebElement videoThumbnail;
	@FindBy(css="#WikiaPageHeader > h1")
	private WebElement header;

	public void verifyEmbeddedVideoIsPresent() {
		waitForElementByElement(videoEmbedded);
		PageObjectLogging.log("verifyEmbeddedVideoIsPresent", "Verified embedded video is visible", true);
	}


	public void verifyThumbnailIsPresent() {
		waitForElementByElement(videoThumbnail);
		PageObjectLogging.log("verifythumbnailIsPresent", "Verified thumbnail is visible", true);

	}

	public void verifyHeader(String fileName) {
		waitForElementByElement(header);
		Assertion.assertStringContains(header.getText(), fileName);
	}
}
