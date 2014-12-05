package com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.editmode;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.slider.SliderBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.slideshow.SlideshowBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.WikiArticlePageObject;

public class WikiArticleEditMode extends WikiEditMode {

	@FindBy(css="a.RTEImageButton")
	private WebElement photoButton;
	@FindBy(css="a.RTEVideoButton")
	private WebElement videoButton;
	@FindBy(css="a.RTEGalleryButton")
	private WebElement galleryButton;
	@FindBy(css="a.RTESlideshowButton")
	private WebElement slideshowButton;
	@FindBy(css="a.RTESliderButton")
	private WebElement sliderButton;
	@FindBy(css="div.cke_skin_wikia.visible div.cke_contents iframe")
	private WebElement visualModeIFrame;
	@FindBy(css=".cke_source")
	private WebElement sourceModeTextArea;
	@FindBy(css="#wpTextbox1")
	private WebElement messageSourceModeTextArea;
	@FindBy(css="div.cke_wrapper.cke_ltr div.cke_contents iframe")
	private WebElement iFrame;
	@FindBy(css="div.neutral.modalToolbar a[id='publish']")
	private WebElement publishButtonPreview;
	@FindBy(css="span.cke_button_ModeSource a span.cke_label")
	private WebElement sourceModeButton;
	@FindBy(css="span.RTEMediaOverlayEdit")
	private WebElement modifyButton;
	@FindBy(css="span.cke_button_ModeWysiwyg a")
	private WebElement visualModeButton;
	@FindBy(css="body[id='bodyContent']")
	private WebElement bodyContent;
	@FindBy(css="input[value='Return to editing']")
	private WebElement returnToEditingButton;
	@FindBy (css = "#wpSave")
	private WebElement publishButton;
	@FindBy(css="input[id='ImageUploadLayoutLeft']")
	private WebElement imageLeftAlignmentOption;
	@FindBy(css="input[id='ImageUploadLayoutRight']")
	private WebElement imageRightAlignmentOption;
	@FindBy(css="button.close")
	private WebElement imageUploadCloseButton;
	@FindBy(css="input#ImageQuery")
	private WebElement findInputField;
	@FindBy(css="input[value='Find']")
	private WebElement findButton;
	@FindBy(css="input[value='Add photo']")
	private WebElement finalAddPhotoButton;
	@FindBy(css="div#ImageUploadHeadline")
	private WebElement ImageUploadHeadline;
	@FindBy(css="img[data-rte-meta*='QAWebdriverCaption1']")
	private WebElement captionInEditor;
	@FindBy(css = "span[id=cke_22_label]")
	private WebElement sourceButton;
	@FindBy(css = "a[data-map-title]")
	private WebElement embededMap;

	private By captionInPreview = By.cssSelector("section.modalWrapper.preview section.modalContent figcaption");
	private By addThisPhotoLink = By.cssSelector("tr.ImageUploadFindLinks td a");
	private String imageArticleIFrame = "img";
	private String publishButtonSelector = "div.neutral.modalToolbar a[id=\"publish\"]";

	public WikiArticleEditMode(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public SpecialVideosPageObject openSpecialVideoPage(String wikiURL){
		getUrl(wikiURL+URLsContent.SPECIAL_VIDEOS);
		return new SpecialVideosPageObject(driver);
	}

	public void verifyThatThePhotoAppears(String caption) {
		waitForElementByElement(visualModeIFrame);
		driver.switchTo().frame(visualModeIFrame);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[data-rte-meta*='"+caption+"']")));
		driver.switchTo().defaultContent();
		PageObjectLogging.log("VerifyThatThePhotoAppears", "Verify that the photo appears in the visual mode", true, driver);
	}


	public void verifyTheCaptionOnThePreview(String caption) {
		wait.until(ExpectedConditions.textToBePresentInElement(captionInPreview, caption));
		PageObjectLogging.log("VerifyTheCaptionOnThePreview", "Verify that the caption of image appears in the preview", true, driver);
	}

	public WikiArticlePageObject clickOnPublishButtonInPreviewMode() {
		waitForElementByElement(publishButtonPreview);
		waitForElementClickableByElement(publishButtonPreview);
		jQueryClick(publishButtonSelector);
		PageObjectLogging.log("LeftClickOnPublishButtonInPreviewMode", "Click on 'Publish' button in preview mode", true, driver);
		return new WikiArticlePageObject(driver);
	}

	public SourceEditModePageObject clickOnSourceButton() {
		waitForElementByElement(sourceModeButton);
		waitForElementClickableByElement(sourceModeButton);
		scrollAndClick(sourceModeButton);
		waitForElementByElement(sourceModeTextArea);
		PageObjectLogging.log("ClickOnSourceButton", "Click on 'Source' button", true, driver);
		return new SourceEditModePageObject(driver);
	}

	public void clickOnVisualButton() {
		waitForElementByElement(visualModeButton);
		waitForElementClickableByElement(visualModeButton);
		scrollAndClick(visualModeButton);
		waitForElementByElement(iFrame);
		PageObjectLogging.log("ClickOnVisualButton", "Click on 'Visual' button", true);
	}

	public void clickOnPublish() {
		waitForElementClickableByElement(publishButton);
		publishButton.click();
		PageObjectLogging.log("clickOnPublish", "publish button clicked", true, driver);
	}

	public void deleteArticleContent() {
		driver.switchTo().frame(iFrame);
		waitForElementByElement(bodyContent);
		bodyContent.clear();
		driver.switchTo().defaultContent();
		PageObjectLogging.log("deleteArticleContent", "Delete all source code on the article", true);
	}

	public void verifySourceEditorContentIsEmpty(){
		waitForElementVisibleByElement(sourceModeTextArea);
		Assertion.assertEquals(sourceModeTextArea.getText().isEmpty(), true);
		PageObjectLogging.log("verifySourceEditorContentIsEmpty", "Source editor content was cleaned", true);
	}

	public void clearSource(){
		waitForElementVisibleByElement(sourceModeTextArea);
		sourceModeTextArea.clear();
		PageObjectLogging.log("deleteArticleContent", "Delete all source code on the article", true);
	}

	public void writeSourceMode(String source) {
		sourceModeTextArea.sendKeys();
		PageObjectLogging.log("writeSourceMode", "Write in source mode: "+source, true);
	}


	public WikiArticlePageObject clickOnPublishButtonPreview() {
		waitForElementByElement(publishButtonPreview);
		waitForElementClickableByElement(publishButtonPreview);
		scrollAndClick(publishButtonPreview);
		PageObjectLogging.log("ClickOnPublishButtonPreview", "Click on 'Publish' button in preview", true, driver);
		return new WikiArticlePageObject(driver);
	}

	public void typeInContent(String content) {
		waitForElementByElement(iFrame);
		driver.switchTo().frame(iFrame);
		waitForElementByElement(bodyContent);
		bodyContent.sendKeys(content);
		PageObjectLogging.log("typeInContent", "content " + bodyContent.getText() + " - type into article body", true, driver);
		driver.switchTo().defaultContent();
	}

	public void clickSourceButton(){
		waitForElementByElement(sourceButton);
		sourceButton.click();
		driver.switchTo().defaultContent();
		PageObjectLogging.log("clickSourceButton", "Source button was clicked", true, driver);
	}

	public void clickReturnToEditingButton() {
		waitForElementByElement(returnToEditingButton);
		returnToEditingButton.click();
		PageObjectLogging.log("clickReturnToEditingButton", "Return to editing button is clicked", true, driver);
	}

	public void clickImageLeftAlignment() {
		waitForElementByElement(imageLeftAlignmentOption);
		imageLeftAlignmentOption.click();
		PageObjectLogging.log("clickImageLeftAlignment", "Left allignment option is selected", true, driver);
	}

	public void clickImageRightAlignment() {
		waitForElementByElement(imageRightAlignmentOption);
		imageRightAlignmentOption.click();
		PageObjectLogging.log("clickImageRightAlignment", "Right allignment option is selected", true, driver);
	}

	public void verifyCaptionInEditMode() {
		waitForElementByElement(iFrame);
		driver.switchTo().frame(iFrame);
		waitForElementByElement(captionInEditor);
		driver.switchTo().defaultContent();
		PageObjectLogging.log("verifyCaptionInEditMode", "Verified existence of caption in editor", true);
	}

	public void verifyWikiTextInSourceMode(String text) {
		String wikiText = sourceModeTextArea.getAttribute("value");
		Assertion.assertStringContains(text, wikiText);
	}

	public void clickOnModifyImageLink() {
		waitForElementByElement(modifyButton);
		modifyButton.click();
		PageObjectLogging.log("clickOnModifyImageLink", "Modify image link is clicked", true, driver);
	}

	public WikiArticleEditMode editArticleByName(String name, String wikiUrl) {
		String newUrl = URLsContent.ADD_ARTICLE.replace("%title%", name);
		getUrl(wikiUrl + newUrl);
		return new WikiArticleEditMode(driver);
	}

	public void verifyLeftAlignmentIsSelected() {
		mouseOverInArticleIframe(imageArticleIFrame);
		clickOnModifyImageLink();
		waitForElementByElement(imageLeftAlignmentOption);

		if (imageLeftAlignmentOption.isSelected()) {
			PageObjectLogging.log("verifyLeftAlignmentIsSelected", "Left allignment option is selected in modal", true);
		} else {
			PageObjectLogging.log("verifyLeftAlignmentIsSelected", "Left allignment option is NOT selected in modal", false);
		}

		waitForElementByElement(imageUploadCloseButton);
		imageUploadCloseButton.click();

	}

	public WikiArticlePageObject addImageForLightboxTesting () {
		waitForElementByElement(findInputField);
		findInputField.sendKeys("aa");
		waitForElementByElement(findButton);
		findButton.click();
		waitForElementByCss("#ImageUploadProgress2");
		waitForElementByCss("img[src*='AmericaAfrica']");
		waitForElementByElement(ImageUploadHeadline);
		WebElement addThisPhoto = waitForElementByBy(addThisPhotoLink);
		addThisPhoto.click();
		waitForElementByElement(finalAddPhotoButton);
		finalAddPhotoButton.click();
		clickOnPublishButton();
		return new WikiArticlePageObject(driver);
	}

	public PhotoAddComponentObject clickPhotoButton() {
		waitForElementByElement(photoButton);
		scrollAndClick(photoButton);
		PageObjectLogging.log("clickPhotoButton", "photo button clicked", true);
		return new PhotoAddComponentObject(driver);
	}

	public VetAddVideoComponentObject clickVideoButton() {
		waitForElementByElement(videoButton);
		scrollAndClick(videoButton);
		PageObjectLogging.log("clickVideoButton", "video button clicked", true);
		return new VetAddVideoComponentObject(driver);
	}

	public SliderBuilderComponentObject clickSliderButton() {
		waitForElementByElement(sliderButton);
		scrollAndClick(sliderButton);
		PageObjectLogging.log("clickSliderButton", "slider button clicked", true);
		return new SliderBuilderComponentObject(driver);
	}
	public SlideshowBuilderComponentObject clickSlideshowButton() {
		waitForElementByElement(slideshowButton);
		scrollAndClick(slideshowButton);
		PageObjectLogging.log("clickSlideshowButton", "slideshow button clicked", true);
		return new SlideshowBuilderComponentObject(driver);
	}
	public GalleryBuilderComponentObject clickGalleryButton() {
		waitForElementByElement(galleryButton);
		scrollAndClick(galleryButton);
		PageObjectLogging.log("clickGallery", "gallery button clicked", true);
		return new GalleryBuilderComponentObject(driver);
	}

	public String getMessageSourceText() {
		waitForElementByElement(messageSourceModeTextArea);
		PageObjectLogging.log("getMessageSourceText", "Get text of source mode text of message article page.", true);
		return messageSourceModeTextArea.getText();
	}

	public void deleteUnwantedVideoFromMessage(String unwantedVideoName) {
		ArrayList<String> videos = new ArrayList<String>();
		String sourceText = getMessageSourceText();
		int index = 0;
		while (true) {
			int previousStarIndex = sourceText.indexOf("*", index);
			int nextStarIndex = sourceText.indexOf("*", previousStarIndex+1);
			if (nextStarIndex<0) {
				break;
			}
			String video = sourceText.substring(previousStarIndex, nextStarIndex);
			if (!video.contains(unwantedVideoName)) {
				videos.add(video);
			}
			index = previousStarIndex+1;
		}
		waitForElementByElement(messageSourceModeTextArea);
		messageSourceModeTextArea.clear();
		messageSourceModeTextArea.sendKeys("WHITELIST");
		messageSourceModeTextArea.sendKeys(Keys.ENTER);
		messageSourceModeTextArea.sendKeys(Keys.ENTER);
		String builder = "";
		for (int i = 0; i<videos.size(); i++)
		{
			builder+=videos.get(i);
			builder+="\n";
		}
		CommonUtils.setClipboardContents(builder);
		messageSourceModeTextArea.sendKeys(Keys.chord(Keys.CONTROL, "v"));

		PageObjectLogging.log("deleteUnwantedVideoFromMessage",
				"Delete all source code on the article", true, driver);
	}

	public void typeContentInSourceMode(String content){
		waitForElementByElement(sourceModeTextArea);
		sourceModeTextArea.sendKeys(content);
		PageObjectLogging.log(
				"typeInContent",
				"content type into source mode textarea",
				true,
				driver
		);
	}

	public void typeInTemplateContent(String content) {
		driver.switchTo().defaultContent();
		waitForElementByElement(messageSourceModeTextArea);
		messageSourceModeTextArea.sendKeys(content);
		PageObjectLogging.log(
				"typeInContent",
				"content type into source mode textarea",
				true,
				driver
		);
	}

	public void verifyEmbededMap(String mapID){
		driver.switchTo().defaultContent();
		waitForElementByElement(embededMap);
		String embededMapID = embededMap.getAttribute("data-map-id");
		Assertion.assertEquals(mapID,embededMapID);
	}
}
