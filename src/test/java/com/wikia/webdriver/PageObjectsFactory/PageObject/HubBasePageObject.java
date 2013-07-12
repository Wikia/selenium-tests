package com.wikia.webdriver.PageObjectsFactory.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class HubBasePageObject extends BasePageObject{

	@FindBy(css="div.button.scrollleft p")
	private WebElement RelatedVideosScrollLeft;
	@FindBy(css="div.button.scrollright p")
	private WebElement RelatedVideosScrollRight;
	@FindBy(css="form.WikiaSearch input[name='search']")
	private WebElement SearchField;
	@FindBy(css="form.WikiaSearch button.wikia-button")
	private WebElement SearchButton;
	@FindBy(css="form.WikiaSearch button.wikia-button")
	private WebElement NewsTabsNav;
	@FindBy(css="section.modalWrapper")
	private WebElement VideoPlayer;
	@FindBy(css="button.wikia-chiclet-button img")
	private WebElement modalWrapper_X_CloseButton;
	@FindBy(css="button.cancel")
	private WebElement modalWrapper_Cancel_CloseButton;
	@FindBy(css="button[id='suggestVideo']")
	private WebElement suggestVideoButton;
	@FindBy(css="button[id='suggestArticle']")
	private WebElement getPromotedButton;
	@FindBy(css="section.modalWrapper")
	private WebElement suggestVideoOrArticleModal;
	@FindBy(css="section.modalWrapper h1")
	private WebElement suggestVideoOrArticleModalTopic;
	@FindBy(css="div.videourl input")
	private WebElement suggestVideoWhatInput;
	@FindBy(css="div.articleurl input")
	private WebElement suggestArticleWhatInput;
	@FindBy(css="div.wikiname input")
	private WebElement suggestVideoWhichWikiInput;
	@FindBy(css="div.required textarea")
	private WebElement suggestArticleWhyCooliInput;
	@FindBy(css="button.submit")
	private WebElement submitButton;
	@FindBy(css="section.wikiahubs-pulse")
	private WebElement pulseModule;
	@FindBy(css="a[id='facebook']")
	private WebElement FacebookButton;
	@FindBy(css="a[id='twitter']")
	private WebElement TwitterButton;
	@FindBy(css="a[id='google']")
	private WebElement GoogleButton;
	@FindBy(css="div.top-wikis-content")
	private WebElement topWikisModule;

	By MosaicSliderLargeImageDescription = By.cssSelector("div.wikia-mosaic-slider-description span.image-description b");
	By FromCommunityImagesList = By.cssSelector("ul.wikiahubs-ftc-list div img");
	By FromCommunityHeadlinesList = By.cssSelector("ul.wikiahubs-ftc-list div.wikiahubs-ftc-title a");
	By FromCommunityWikinameAndUsernameFieldsList = By.cssSelector("ul.wikiahubs-ftc-list div.wikiahubs-ftc-subtitle a");
	By FromCommunityQuatationsList = By.cssSelector("ul.wikiahubs-ftc-list div.wikiahubs-ftc-creative");

	int RVmoduleCurrentVideosSet;

	public HubBasePageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		RVmoduleCurrentVideosSet = 1;
	}

	public void MosaicSliderVerifyHasImages() {
		List<WebElement> WikiaMosaicSliderPanoramaImages = driver.findElements(By.cssSelector("div.wikia-mosaic-slider-panorama"));
		List<WebElement> WikiaMosaicSliderThumbRegionImages = driver.findElements(By.cssSelector("ul.wikia-mosaic-thumb-region img"));
		waitForElementByElement(WikiaMosaicSliderPanoramaImages.get(0));
		for (int i = 0; i < 5; i++) {
			waitForElementByElement(WikiaMosaicSliderThumbRegionImages.get(i));
		}
		PageObjectLogging.log("MosaicSliderVerifyHasImages", "Verify that WikiaMosaicSlider has images", true);
	}

	/**
	 * Hover Over Image number 'n'on Mosaic Slider
	 *
	 * @param  n number of the image n={1,2,3,4,5}
	 * @author Michal Nowierski
	 */
	public void MosaicSliderHoverOverImage(int n) {
		if (n>5) {
			PageObjectLogging.log("MosaicSliderHoverOverImage", "MosaicSlider: The n parameter must be less than 5. It can not be: n = "+n, false, driver);
			return;
		}
		waitForElementByBy(By.cssSelector("ul.wikia-mosaic-thumb-region img"));
		mouseOver("ul.wikia-mosaic-thumb-region li:nth-child("+n+")");
		PageObjectLogging.log("MosaicSliderHoverOverImage", "MosaicSlider: Hover over image number "+n, true, driver);
	}

	/**
	 * Get title of current LargeImage on Mosaic Slider
	 *
	 * @author Michal Nowierski
	 */
	public String MosaicSliderGetCurrentLargeImageDescription() {
		WebElement MosaicSliderLargeImageDesc = driver.findElement(MosaicSliderLargeImageDescription);
		waitForElementByElement(MosaicSliderLargeImageDesc);
		String description = MosaicSliderLargeImageDesc.getText();
		PageObjectLogging.log("MosaicSliderGetCurrentLargeImageDescription", "description of current LargeImage on Mosaic Slider is: <b>"+ description +"</b>", true);
		return description;
	}

	/**
	 * Verify that Large Image has changed (by verifying description change), and get the current description
	 *
	 * @param  n number of the image n={1,2,3,4,5}
	 * @author Michal Nowierski
	 */
	public String MosaicSliderVerifyLargeImageChangeAndGetCurrentDescription(
			String PreviousLargeImageDescription) {
		String CurrentDescription = MosaicSliderGetCurrentLargeImageDescription();
		if (CurrentDescription.equals(PreviousLargeImageDescription)) {
			PageObjectLogging.log("MosaicSliderVerifyLargeImageChangeAndGetCurrentDescription", "Large Image hasn't changed", false);

		}
		PageObjectLogging.log("MosaicSliderVerifyLargeImageChangeAndGetCurrentDescription", "Verify that Large Image has changed", true);
		return CurrentDescription;
	}

	/**
	 * Click on getPromoted button
	 *
	 * @author Michal Nowierski
	 */
	public void ClickGetPromoted() {
		waitForElementByElement(getPromotedButton);
		scrollToElement(getPromotedButton);
		waitForElementClickableByElement(getPromotedButton);
		scrollAndClick(getPromotedButton);
		PageObjectLogging.log("ClickSuggestAnArticle", "Click on suggest an article button", true, driver);
	}

	/**
	 * Verify that suggest a video or article modal appeared
	 *
	 * @author Michal Nowierski
	 */
	public void VerifySuggestAVideoOrArticleModalAppeared() {
		waitForElementByElement(suggestVideoOrArticleModal);
		PageObjectLogging.log("VerifySuggestAVideoOrArticleModalAppeared", "Verify that suggest a video modal appeared", true);
	}

	/**
	 * Verify that suggest a video or an article modal has topic:
	 *
	 * @author Michal Nowierski
	 */
	public void VerifySuggestAVideoOrArticleModalTopic(String topic) {
		waitForElementByElement(suggestVideoOrArticleModalTopic);
		waitForTextToBePresentInElementByElement(suggestVideoOrArticleModalTopic, topic);
		PageObjectLogging.log("VerifySuggestAVideoOrArticleModalTopic", "Verify that suggest a video or an article modal has topic: "+topic, true);
	}

	/**
	 * Click on [x] to close suggest a video or article modal
	 *
	 * @author Michal Nowierski
	 */
	public void Click_X_toCloseSuggestAVideoOrArticle() {
		closeModalWrapper();
		PageObjectLogging.log("Click_X_toCloseSuggestAVideoOrArticle", "Click on [x] to close suggest a video or article modal", true, driver);
	}

	/**
	 * Click on Cancel to close suggest a video or article modal
	 *
	 * @author Michal Nowierski
	 */
	public void Click_Cancel_toCloseSuggestAVideoOrArticle() {
		waitForElementByElement(modalWrapper_Cancel_CloseButton);
		waitForElementClickableByElement(modalWrapper_Cancel_CloseButton);
		scrollAndClick(modalWrapper_Cancel_CloseButton);
		PageObjectLogging.log("Click_Cancel_toCloseSuggestAVideoOrArticle", "Click on Cancel to close suggest a video or article modal", true, driver);
	}

	/**
	 * Close modal wrapper. Modal wrapper can be e.g 'video player' or 'suggest a video modal'.
	 *
	 * @author Michal Nowierski
	 */
	private void closeModalWrapper() {
		waitForElementByElement(modalWrapper_X_CloseButton);
		waitForElementClickableByElement(modalWrapper_X_CloseButton);
		scrollAndClick(modalWrapper_X_CloseButton);
	}

	/**
	 * Verify that Suggest Video or Article submit button is disabled
	 *
	 * @author Michal Nowierski
	 */
	public void VerifySuggestVideoOrArticleButtonNotClickable() {
		waitForElementByElement(submitButton);
		waitForElementNotClickableByElement(submitButton);
		PageObjectLogging.log("VerifySuggestVideoOrArticleButtonNotClickable", "Verify that 'Suggest Video' or 'Article' submit button button is disabled", true);
	}

	/**
	 * Verify that Suggest Video or Article submit button is enabled
	 *
	 * @author Michal Nowierski
	 */
	public void VerifySuggestVideoOrArticleButtonClickable() {
		waitForElementByElement(submitButton);
		waitForElementClickableByElement(submitButton);
		PageObjectLogging.log("VerifySuggestVideoOrArticleButtonClickable", "Verify that Suggest Video or Article submit button is enabled", true);
	}

	/**
	 * Type text into 'What Video' field on 'Suggest Video Modal'
	 *
	 * @author Michal Nowierski
	 */
	public void SuggestArticleTypeIntoWhatVideoField(String text) {
		waitForElementByElement(suggestArticleWhatInput);
		suggestArticleWhatInput.sendKeys(text);
		PageObjectLogging.log("SuggestArticleTypeIntoWhatVideoField", "Type '"+text+"' into 'What Video' field on 'Suggest Article Modal'", true, driver);
	}

	/**
	 * Type text into 'Why cool' field on 'Suggest Video Modal'
	 *
	 * @author Michal Nowierski
	 */
	public void SuggestArticleTypeIntoWhyCoolField(String text) {
		waitForElementByElement(suggestArticleWhyCooliInput);
		suggestArticleWhyCooliInput.sendKeys(text);
		PageObjectLogging.log("SuggestArticleTypeIntoWhyCoolField", "Type '"+text+"' into 'Why cool' field on 'Suggest Video Modal'", true, driver);
	}

	/**
	 * Verify that from the community module has images
	 *
	 * @author Michal Nowierski
	 */
	public void verifyFromModuleHasImages() {
		List<WebElement> List = driver.findElements(FromCommunityImagesList);
		for (int i = 0; i < List.size(); i++) {
			PageObjectLogging.log("verifyFromModuleHasImages", "Checking image number "+(i+1), true);
			scrollToElement(List.get(i));
			waitForElementByElement(List.get(i));
		}
		PageObjectLogging.log("verifyFromModuleHasImages", "Verify that from the community module has images", true);
	}

	/**
	 * Verify that from the communitz module has headline
	 *
	 * @author Michal Nowierski
	 */
	public void verifyFromModuleHasHeadline() {
		List<WebElement> List = driver.findElements(FromCommunityHeadlinesList);
		for (int i = 0; i < List.size(); i++) {
			PageObjectLogging.log("verifyFromModuleHasHeadline", "Checking headline number "+(i+1), true);
			scrollToElement(List.get(i));
			waitForElementByElement(List.get(i));
		}
		PageObjectLogging.log("verifyFromModuleHasHeadline", "Verify that from the community module has headline", true);
	}

	/**
	 * Verify that from the community module has username field
	 *
	 * @author Michal Nowierski
	 */
	public void verifyFromModuleHasUserAndWikiField() {
		List<WebElement> List = driver.findElements(FromCommunityWikinameAndUsernameFieldsList);
		for (int i = 0; i < List.size(); i++) {
			PageObjectLogging.log("verifyFromModuleHasUserAndWikiField", "Checking field number "+(i+1), true);
			scrollToElement(List.get(i));
			waitForElementByElement(List.get(i));
		}
		PageObjectLogging.log("verifyFromModuleHasUserAndWikiField", "Verify that from the community module has username field", true);
	}

	/**
	 * Verify that from the communitz module has a quatation
	 *
	 * @author Michal Nowierski
	 */
	public void verifyFromModuleHasQuatation() {
		List<WebElement> List = driver.findElements(FromCommunityQuatationsList);
		for (int i = 0; i < List.size(); i++) {
			PageObjectLogging.log("verifyFromModuleHasQuatation", "Checking quotation number "+(i+1), true);
			scrollToElement(List.get(i));
			waitForElementByElement(List.get(i));
		}
		PageObjectLogging.log("verifyFromModuleHasQuatation", "Verify that from the community module has a quatation", true);
	}

	public void verifySuggestAVideoOrArticleModalDisappeared() {
		waitForElementNotVisibleByElement(suggestVideoOrArticleModal);
		PageObjectLogging.log("verifySuggestAVideoOrArticleModalDisappeared", "Verify that video 'suggest video or article' modal disppeared", true);
	}
}
































