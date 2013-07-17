package com.wikia.webdriver.PageObjectsFactory.ComponentObject.Toolbars;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class ShareToolbarComponentObject extends BasePageObject {

	public ShareToolbarComponentObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css="a[data-id='shareButton']")
	private WebElement shareButton;
	@FindBy(css="iframe.twitter-share-button")
	private WebElement twitterIframe;
	@FindBy(css="a#b")
	private WebElement twitterButton;
	@FindBy(css="iframe.fb_ltr")
	private WebElement fBIframe;
	@FindBy(css="div.pluginConnectButton .pluginConnectButtonDisconnected button")
	private WebElement fBLikeButton;
	@FindBy(css="a.email-link")
	private WebElement emailButton;
	@FindBy(css="a[id='ok']")
	private WebElement emailModalSendButton;
	@FindBy(css="a[id='cancel']")
	private WebElement emailModalCancelButton;
	@FindBy(css="button.wikia-chiclet-button")
	private WebElement emailModalCloseButton;
	@FindBy(css="input#lightbox-share-email-text")
	private WebElement emailModalEmailInputField;

	public void clickShareButton() {
		waitForElementByElement(shareButton);
		shareButton.click();
		PageObjectLogging.log("clickShareButton", "Share button was clicked", true, driver);
	}

	public void verifyTwitterIframeVisibility() {
		waitForElementByElement(twitterIframe);
		PageObjectLogging.log("VerifyTwitterIframePresence", "Verify that the Twitter Iframe Is Present", true, driver);
	}

	public void verifyFBIframeVisibility() {
		waitForElementByElement(fBIframe);
		PageObjectLogging.log("VerifyFBIframeVisibility", "Verify that the FB Iframe Is Present", true, driver);
	}

	public void verifyEmailButtonVisibility() {
		waitForElementByElement(emailButton);
		PageObjectLogging.log("verifyEmailButtonVisibility", "Verify that the Email Button Is Present", true, driver);
	}

	public void navigteTweetButtonUrl() {
		waitForElementByElement(twitterIframe);
		driver.switchTo().frame(twitterIframe);
		String href = twitterButton.getAttribute("href");
		driver.switchTo().defaultContent();
		getUrl(href);
		PageObjectLogging.log("clickTweetButton", "Twitter button was clicked", true, driver);
	}

	public void verifyTwitterModalURL() {
		Assertion.assertStringContains(getCurrentUrl(), "twitter.com");
		PageObjectLogging.log("VerifyTwitterModalURL", "Verify that the Twitter Modal URL is correct", true, driver);
	}

	public void clickFBLikeButton() {
		waitForElementByElement(fBIframe);
		driver.switchTo().frame(fBIframe);
		fBLikeButton.click();
		driver.switchTo().defaultContent();
		PageObjectLogging.log("clickFBLikeButton", "FB Like button was clicked", true, driver);
	}

	public void verifyFBModalURL() {
		waitForWindow("", "");
		Object[] windows = driver.getWindowHandles().toArray();
		driver.switchTo().window(windows[1].toString());
		Assertion.assertStringContains(getCurrentUrl(), "facebook.com");
		driver.switchTo().window(windows[0].toString());
		PageObjectLogging.log("VerifyFBModalURL", "Verify that the FB Modal URL is correct", true, driver);
	}

	public void clickEmailButton() {
		waitForElementByElement(emailButton);
		emailButton.click();
		PageObjectLogging.log("clickEmailButton", "Email button was clicked", true, driver);
	}

	public void verifyEmailModalElements() {
		waitForElementByElement(emailModalSendButton);
		waitForElementByElement(emailModalCancelButton);
		waitForElementByElement(emailModalCloseButton);
		waitForElementByElement(emailModalEmailInputField);
		PageObjectLogging.log("VerifyEmailModalElements", "Verify that the Email Modal elements are present", true, driver);
	}
}
