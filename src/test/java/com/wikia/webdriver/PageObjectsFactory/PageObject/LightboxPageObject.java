package com.wikia.webdriver.PageObjectsFactory.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;

public class LightboxPageObject extends BasePageObject{

	public LightboxPageObject(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="span[data-pin-title='Unpin top and bottom bars']")
	private WebElement unpinButton;
	@FindBy(css="span[data-pinned-title='Unpin top and bottom bars']")
	private WebElement pinButton;
	@FindBy(css="button[class='more-info-button secondary']")
	private WebElement moreInfoButton;
	@FindBy(css="button[class='share-button secondary']")
	private WebElement shareButton;
	@FindBy(css="button.close.wikia-chiclet-button")
	private WebElement closeLightboxButton;
	@FindBy(css="span.arrow.previous")
	private WebElement previousArrow;
	@FindBy(css="span[id='LightboxNext']")
	private WebElement nextArrow;
	@FindBy(css="div.media.video-media")
	private WebElement fullVideo;
	@FindBy(css="span.Wikia-video.playbutton.min")
	private WebElement videoThumbnailCarousel;
	@FindBy(css="span.carousel-arrow.next.button.secondary")
	private WebElement nextArrowCarousel;
	@FindBy(css="span.carousel-arrow.previous.button.secondary")
	private WebElement previousArrowCarousel;
	@FindBy(css="div.hero-inner")
	private WebElement moreInfoThumbnail;
	@FindBy(css="a[class='facebook']")
	private WebElement facebookShareLink;
	@FindBy(css="a.twitter")
	private WebElement twitterShareLink;
	@FindBy(css="a[class='stumbleupon']")
	private WebElement stumbleUponShareLink;
	@FindBy(css="a[class='reddit']")
	private WebElement redditShareLink;
	@FindBy(css="a[class='plusone']")
	private WebElement plusoneShareLink;
	@FindBy(css="button.more-info-close.secondary")
	private WebElement backButton;
	@FindBy(css="input[name='lightbox-email']")
	private WebElement emailInput;
	@FindBy(css="input[name='Send']")
	private WebElement sendButton;
	@FindBy(css="div.success-msg")
	private WebElement successMessage;
	@FindBy(css="div.error-msg")
	private WebElement errorMessage;
	@FindBy(css="input[name='lightbox-standard-link']")
	private WebElement standardLink;
	@FindBy(css="input[name='lightbox-embed-url']")
	private WebElement embedURL;
	@FindBy(css="input[name='lightbox-file-page-url']")
	private WebElement filePageURL;
	@FindBy(css="p#LightboxCarouselProgress")
	private WebElement progress;
	@FindBy(css="#LightboxModal")
	private WebElement lightBoxModal;

	public void clickMoreInfoButton() {
		waitForElementByElement(moreInfoButton);
		moreInfoButton.click();
		PageObjectLogging.log("clickMoreInfoButton", "Clicked More Info button", true, driver);
	}

	public void verifyMoreInfoThumbnail() {
		waitForElementByElement(moreInfoThumbnail);
		PageObjectLogging.log("verifyMoreInfoThumbnail", "Thumbnail in More Info modal is visible", true, driver);
	}

	public WikiArticlePageObject clickCloseButton() {
		jQueryClick("button.close.wikia-chiclet-button");
		PageObjectLogging.log("clickCloseButton", "Close button is clicked", true, driver);
		return new WikiArticlePageObject(driver);
	}

	public void clickShareButton() {
		waitForElementByElement(shareButton);
		shareButton.click();
		waitForElementByElement(moreInfoThumbnail);
		PageObjectLogging.log("clickShareButton", "Share button is clicked", true, driver);
	}

	public void verifyShareButtons() {
		waitForElementByElement(plusoneShareLink);
		waitForElementByElement(redditShareLink);
		waitForElementByElement(stumbleUponShareLink);
		waitForElementByElement(twitterShareLink);
		waitForElementByElement(facebookShareLink);
		PageObjectLogging.log("verifyShareButtons", "All share buttons are visible", true, driver);
	}

	public void clickMoreInfoBackButton() {
		waitForElementByElement(backButton);
		backButton.click();
		PageObjectLogging.log("clickMoreInfoBackButton", "Back button from more info page is clicked", true, driver);
	}

	public void clickFacebookShareButton() {
		facebookShareLink.click();
		PageObjectLogging.log("clickFacebookShareButton", "FB share button is clicked", true);
	}

	public void clickTwitterShareButton() {
		twitterShareLink.click();
		PageObjectLogging.log("clickTwitterShareButton", "Twitter share button is clicked", true);
	}

	public void clickStumbleUponShareButton() {
		stumbleUponShareLink.click();
		PageObjectLogging.log("clickStumbleUponShareButton", "Stumbleupon share button is clicked", true);
	}

	public void clickRedditShareButton() {
		redditShareLink.click();
		PageObjectLogging.log("clickRedditShareButton", "Reddit share button is clicked", true);
	}

	public void clickPlusOneShareButton() {
		plusoneShareLink.click();
		PageObjectLogging.log("clickPlusOneShareButton", "Plus one share button is clicked", true);
	}

	public void verifyFacebookWindow() {
		waitForWindow("", "");
		Object[] windows = driver.getWindowHandles().toArray();
		driver.switchTo().window(windows[1].toString());
		waitForStringInURL(URLsContent.facebookDomain);
		driver.close();
		driver.switchTo().window(windows[0].toString());
		PageObjectLogging.log("verifyFacebookWindow", "Verify that the FB window URL is correct", true, driver);
	}

	public void verifyTwitterWindow(){
		waitForWindow("", "");
		Object[] windows = driver.getWindowHandles().toArray();
		driver.switchTo().window(windows[1].toString());
		waitForStringInURL(URLsContent.twitterDomain);
		driver.close();
		driver.switchTo().window(windows[0].toString());
		PageObjectLogging.log("verifyTwitterWindow", "Verify that the Twitter window URL is correct", true, driver);
	}

	public void verifyStumbleUponWindow(){
		waitForWindow("", "");
		Object[] windows = driver.getWindowHandles().toArray();
		driver.switchTo().window(windows[1].toString());
		waitForStringInURL(URLsContent.stumpleUponDomain);
		driver.close();
		driver.switchTo().window(windows[0].toString());
		PageObjectLogging.log("verifyStumbleUponWindow", "Verify that the Stumbleupon window URL is correct", true, driver);
	}

	public void verifyRedditWindow() {
		waitForWindow("", "");
		Object[] windows = driver.getWindowHandles().toArray();
		driver.switchTo().window(windows[1].toString());
		waitForStringInURL(URLsContent.redditDomain);
		driver.close();
		driver.switchTo().window(windows[0].toString());
		PageObjectLogging.log("verifyRedditWindow", "Verify that the Reddit window URL is correct", true, driver);
	}

	public void verifyPlusOneWindow(){
		waitForWindow("", "");
		Object[] windows = driver.getWindowHandles().toArray();
		driver.switchTo().window(windows[1].toString());
		waitForStringInURL(URLsContent.googleDomain);
		driver.close();
		driver.switchTo().window(windows[0].toString());
		PageObjectLogging.log("verifyPlusOneWindow", "Verify that the Plus One window URL is correct", true, driver);
	}

	public void addCorrectEmail () {
		waitForElementByElement(emailInput);
		sendKeys(emailInput, "rodriuki@hotmail.com");
		sendButton.click();
		waitForElementByElement(successMessage);
		PageObjectLogging.log("addCorrectEmail", "Success message is displayed after providing correct mail address", true, driver);
	}

	public void addBlankEmail () {
		waitForElementByElement(emailInput);
		sendKeys(emailInput, "");
		sendButton.click();
		waitForElementByElement(errorMessage);
		PageObjectLogging.log("addBlankEmail", "Error message is displayed after providing incorrect mail address", true, driver);
	}

	public void verifyCorrectStandardLink() {
		waitForElementByElement(standardLink);
		Assertion.assertStringContains(standardLink.getText(), "file=Lion2.gif");
		PageObjectLogging.log("verifyCorrectStandardLink", "Standard link is correctly displayed", true, driver);
	}

	public void verifyCorrectFilePageURLLinkk() {
		waitForElementByElement(filePageURL);
		Assertion.assertStringContains(embedURL.getText(), "file=Lion2.gif");
		PageObjectLogging.log("verifyCorrectFilePageURLLinkk", "File Page URL link is correctly formatted", true, driver);
	}

	public void verifyLightboxCarouselProgress() {
		waitForElementByElement(progress);
		Assertion.assertEquals("1-1 of 1", progress.getText());
//		CommonFunctions.assertString("1-1 of 1", progress.getText());
		PageObjectLogging.log("verifyLightboxCarouselProgress", "Number of media elements in carousel progress is correct ", true, driver);
	}

	public void clickUnpinButton() {
		waitForElementByElement(unpinButton);
		unpinButton.click();
		PageObjectLogging.log("clickUnpinButton", "Unpin button was clicked", true, driver);
	}

	public void clickPinButton() {
		builder.moveToElement(lightBoxModal).
				click(pinButton).
				build().
				perform();
		PageObjectLogging.log("clickPinButton", "Pin button was clicked", true, driver);
	}
}
