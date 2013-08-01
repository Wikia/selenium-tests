package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.CommonUtils;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slider.SliderBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slideshow.SlideshowBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.SourceEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;


public class WikiArticleEditMode extends WikiEditMode {

	//right rail toolbox - will be moved to edit mode
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

	@FindBy(css="div.reset[id='ImageUpload']")
	private WebElement imageUploadModal;
	@FindBy(css="div.cke_skin_wikia.visible div.cke_contents iframe")
	private WebElement visualModeIFrame;
	@FindBy(css=".cke_source")
	private WebElement sourceModeTextArea;
	@FindBy(css="#wpTextbox1")
	private WebElement messageSourceModeTextArea;
	@FindBy(css="div.cke_wrapper.cke_ltr div.cke_contents iframe")
	private WebElement iFrame;
	@FindBy(css="header.WikiaHeader")
	private WebElement header;
//	@FindBy(css="tr.ImageUploadFindLinks td a")
//	private WebElement addThisPhotoLink;
	@FindBy(css="#wpPreview")
	private WebElement previewButton;
	@FindBy(css="div.neutral.modalToolbar a[id='publish']")
	private WebElement publishButtonPreview;
	@FindBy(css="span.cke_button_ModeSource a span.cke_label")
	private WebElement sourceModeButton;
	@FindBy(css="span.RTEMediaOverlayEdit")
	private WebElement modifyButton;
	@FindBy(css="[type='video-placeholder'] span.RTEMediaOverlayEdit")
	private WebElement modifyButtonVideoPlaceholder;
	@FindBy(css="span.RTEMediaOverlayDelete")
	private WebElement removeButton;
	@FindBy(css="div.RTEConfirmButtons a[id='RTEConfirmCancel'] span")
	private WebElement cancelImageRemovalButton;
	@FindBy(css="a[id='RTEConfirmOk']")
	private WebElement oKbutton;
	@FindBy(css="img.video")
	private WebElement videoInEditMode;
	@FindBy(css="div.ArticlePreview div.Wikia-video-play-button")
	private WebElement videoOnPreview;
	@FindBy(css="span.cke_button_ModeWysiwyg a")
	private WebElement visualModeButton;
	@FindBy(css="section.modalWrapper.preview section.modalContent figure a img")
	private WebElement imageOnPreview;
	@FindBy(css="body[id='bodyContent']")
	private WebElement bodyContent;
	@FindBy(css="span.cke_button_table a")
	private WebElement tableButton;
	@FindBy(css="div.cke_dialog.modalWrapper")
	private WebElement tableModal;
	@FindBy(css="a.cke_dialog_ui_button.wikia-button")
	private WebElement tableModalOKbutton;
	@FindBy(css="table.article-table")
	private WebElement VisualModeTable;
	@FindBy(css="#CategorySelectInput")
	private WebElement categories_CategoryInputField;
	@FindBy(css="#csWikitext")
	private WebElement categories_CategorySourceInputField;
	@FindBy(xpath="//p[contains(text(), 'You do not have permission to edit this page, for the following reason:')]")
	private WebElement blockedUserMessage1;
	@FindBy(xpath="//b[contains(text(), 'Your user name or IP address has been blocked.')]")
	private WebElement blockedUserMessage2;
	@FindBy(css="#VideoEmbedUrlSubmit")
	private WebElement videoSubmitButton;
	@FindBy(css=".VideoEmbedNoBorder input[type='submit']")
	private WebElement addVideoButton;
	@FindBy(css="input[value='Return to editing']")
	private WebElement returnToEditingButton;
	@FindBy (css = "#wpSave")
	private WebElement publishButton;
	@FindBy(css="input[id='ImageUploadLayoutLeft']")
	private WebElement imageLeftAlignmentOption;
	@FindBy(css="input[id='ImageUploadLayoutRight']")
	private WebElement imageRightAlignmentOption;
	@FindBy(css="input[name='search'][placeholder='Search photos on this wiki']")
	private WebElement searchFieldImageInLightBox;
	@FindBy(css="img.sprite.search")
	private WebElement searchButtonImageInLightBox;
	@FindBy(css="button.close")
	private WebElement imageUploadCloseButton;
	@FindBy(css="div#ImageUploadBody")
	private WebElement imageUploadBodyModal;
	@FindBy(css="input#ImageQuery")
	private WebElement findInputField;
	@FindBy(css="input[value='Find']")
	private WebElement findButton;
	@FindBy(css="input[value='Add photo']")
	private WebElement finalAddPhotoButton;
	@FindBy(css="div#ImageUploadHeadline")
	private WebElement ImageUploadHeadline;
	@FindBy(css = "div.details input")
	private WebElement addPhotoButton;
	@FindBy(css = "section[id='WikiaPhotoGalleryEditor']")
	private WebElement objectModal;
	@FindBy(css = "a[id='WikiaPhotoGallerySearchResultsSelect']")
	private WebElement galleryDialogSelectButton;
	@FindBy(css = "a[id='WikiaPhotoGalleryEditorSave']")
	private WebElement galleryDialogFinishButton;
	@FindBy(css = "input[id='VideoEmbedUrl']")
	private WebElement videoModalInput;
	@FindBy(css = "a[id='VideoEmbedUrlSubmit']")
	private WebElement videoNextButton;
	@FindBy(css = "#VideoEmbedThumb")
	private WebElement videoDialog;
	@FindBy(css = "input[value='Return to editing']")
	private WebElement videoReturnToEditing;
	@FindBy(css="input[id='VideoEmbedCaption']")
	private WebElement videoCaptionTextArea;
	@FindBy(css = "div.input-group.VideoEmbedNoBorder input")
	private WebElement videoAddVideoButton;

	@FindBy(css="img.alignLeft")
	private WebElement leftAlignedVideoOnEditor;
	@FindBy(css="img.alignRight")
	private WebElement rightAlignedVideoOnEditor;
	@FindBy(css="img.alignCenter")
	private WebElement centerAlignedVideoOnEditor;
	@FindBy(css="figure.tleft")
	private WebElement leftAlignedVideoOnPreview;
	@FindBy(css="figure.tright")
	private WebElement rightAlignedVideoOnPreview;
	@FindBy(css="button.close.wikia-chiclet-button")
	private WebElement closePreviewModal;
	@FindBy(css="figure.tnone")
	private WebElement centerAlignedVideoOnPreview;
	@FindBy(css="img[width='250']")
	private WebElement videoWidthEditor;
	@FindBy(css="img[data-rte-meta*='QAWebdriverCaption1']")
	private WebElement captionInEditor;

	private By captionInPreview = By.cssSelector("section.modalWrapper.preview section.modalContent figcaption");
	private By videoOnArticleEditMode = By.cssSelector("img.video");
	private By categories_listOfCategoriyPrompts = By.cssSelector("li.ui-menu-item");
	private By addThisPhotoLink = By.cssSelector("tr.ImageUploadFindLinks td a");
	private String imageArticleIFrame = "img";
	private String videoArticleIFrame = "img.video";
	private String previewButtonSelector = "#wpPreview";
	private String publishButtonSelector = "div.neutral.modalToolbar a[id=\"publish\"]";
	private String editButtonArticleItem = "span.RTEMediaOverlayEdit";
	private String categories_listOfCategories = "li.category";


	public WikiArticleEditMode(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}


	/**
	 * @author Karol Kujawiak
	 * @param name
	 * @return WikiArticleByName
	 */
	public WikiArticleEditMode editArticleByName(String name)
	{
            String newUrl = URLsContent.addArticle.replace("%title%", name);
            getUrl(Global.DOMAIN + newUrl);
		return new WikiArticleEditMode(driver);
	}

	/**
	 * Verify that the photo appears in the visual mode
	 *
	 * @author Michal Nowierski
	 */
	public void verifyThatThePhotoAppears(String caption) {
		waitForElementByElement(visualModeIFrame);
		//The Editor is iframe so we have to switch to the iframe in order to investigate its content
		driver.switchTo().frame(visualModeIFrame);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[data-rte-meta*='"+caption+"']")));
		// Now switch back to the normal DOM
		driver.switchTo().defaultContent();
		PageObjectLogging.log("VerifyThatThePhotoAppears", "Verify that the photo appears in the visual mode", true, driver);
	}

	/**
	 * Left Click on 'Preview' Button
	 *
	 * @author Michal Nowierski
	 */
	public void clickOnPreviewButton() {

		waitForElementByElement(previewButton);
		waitForElementClickableByElement(previewButton);
		jQueryClick(previewButtonSelector);
		PageObjectLogging.log("LeftClickOnPreviewButton", "Left Click on 'Preview' Button", true, driver);


	}


	/**
	 * Verify that the caption of image appears in the preview
	 *
	 * @author Michal Nowierski
	 */
	public void verifyTheCaptionOnThePreview(String caption) {
		wait.until(ExpectedConditions.textToBePresentInElement(captionInPreview, caption));
		PageObjectLogging.log("VerifyTheCaptionOnThePreview", "Verify that the caption of image appears in the preview", true, driver);


	}

	/**
	 * Click on 'Publish' button
	 *
	 * @author Michal Nowierski
	 */
	public WikiArticlePageObject clickOnPublishButtonInPreviewMode() {
		waitForElementByElement(publishButtonPreview);
		waitForElementClickableByElement(publishButtonPreview);
		jQueryClick(publishButtonSelector);
		PageObjectLogging.log("LeftClickOnPublishButtonInPreviewMode", "Click on 'Publish' button in preview mode", true, driver);
		return new WikiArticlePageObject(driver);
	}

	/**
	 * Click on 'Source' button
	 *
	 * @author Michal Nowierski
	 */
	public SourceEditModePageObject clickOnSourceButton() {
		waitForElementByElement(sourceModeButton);
		waitForElementClickableByElement(sourceModeButton);
		scrollAndClick(sourceModeButton);
		waitForElementByElement(sourceModeTextArea);
		PageObjectLogging.log("ClickOnSourceButton", "Click on 'Source' button", true, driver);
		return new SourceEditModePageObject(driver);
	}

	/**
	 * Click on 'Source' button
	 *
	 * @author Michal Nowierski
	 */
	public void clickOnVisualButton() {
		waitForElementByElement(visualModeButton);
		waitForElementClickableByElement(visualModeButton);
		scrollAndClick(visualModeButton);
		waitForElementByElement(iFrame);
		PageObjectLogging.log("ClickOnVisualButton", "Click on 'Visual' button", true);

	}

        public void clickOnPublish() {
            waitForElementClickableByElement(publishButton);
            scrollAndClick(publishButton);
        }

	/**
	 * <p> Verify if js alert is or isn't there. You can expect alert with certain message, or not expect alert with certain message <br>
	 *
	 * @param alert message that we do or do not expect
	 * @param ifAlertExpected  if we expect JS alert - true. If we don't expect JS alert - false
	 * @author Michal Nowierski
	 */
	public void clickOnVisualButtonAndCheckJSalertThere(String alertMessage, Boolean ifAlertExpected) {
		waitForElementByElement(visualModeButton);
		waitForElementClickableByElement(visualModeButton);
		scrollAndClick(visualModeButton);
		//The potential alert must be served right after alert-sensitive action:
		//The alert check must come right after the action that might cause it. If it appear anywhere later, the potential alert will not be served.
		checkJSalertIsThere(alertMessage, ifAlertExpected);
		waitForElementByElement(iFrame);
		PageObjectLogging.log("clickOnVisualButtonAndCheckJSalertNotThere", "Click on 'Visual' button and check there is no JS alert", true, driver);
	}

	/**
	 * Delete all source code on the article
	 *
	 * @author Michal Nowierski
	 */
	public void deleteArticleContent() {
		driver.switchTo().frame(iFrame);
		bodyContent.clear();
		driver.switchTo().defaultContent();
		PageObjectLogging.log("deleteArticleContent", "Delete all source code on the article", true, driver);

	}

	public void clearSource(){
		waitForElementByElement(sourceModeTextArea);
		sourceModeTextArea.clear();
		PageObjectLogging.log("deleteArticleContent", "Delete all source code on the article", true, driver);
	}

	public void writeSourceMode(String source)
	{
		sourceModeTextArea.sendKeys();
		PageObjectLogging.log("writeSourceMode", "Write in source mode: "+source, true, driver);
	}


	public WikiArticlePageObject clickOnPublishButtonPreview() {
		waitForElementByElement(publishButtonPreview);
		waitForElementClickableByElement(publishButtonPreview);
		scrollAndClick(publishButtonPreview);
		PageObjectLogging.log("ClickOnPublishButtonPreview", "Click on 'Publish' button in preview", true, driver);

		return new WikiArticlePageObject(driver);
	}

	public void typeInContent(String content)
	{
		waitForElementByElement(visualModeIFrame);
		driver.switchTo().frame(visualModeIFrame);
		waitForElementByElement(bodyContent);
		bodyContent.sendKeys(content);
		driver.switchTo().defaultContent();
		PageObjectLogging.log("typeInContent", "content type into article body", true, driver);
	}

	public VetOptionsComponentObject clickModifyButtonVideo() {
		waitForElementByElement(iFrame);
		mouseOverInArticleIframe(videoArticleIFrame);
		waitForElementByElement(modifyButton);
		jQueryClick(editButtonArticleItem);
		PageObjectLogging.log("clickModifyButtonVideo", "Click on 'modify button' on video", true, driver);
		return new VetOptionsComponentObject(driver);
	}



	/**
	 * Verify that video appears in edit mode
	 *
	 * @author Michal Nowierski
	 *
	 */
	public void verifyVideoInEditMode(String caption) {
		waitForElementByElement(iFrame);
		driver.switchTo().frame(iFrame);
		waitForElementPresenceByBy(videoOnArticleEditMode);
		mouseOverByBy(videoOnArticleEditMode);
		driver.switchTo().defaultContent();
		waitForElementByXPath(
			"//div[@class='RTEMediaCaption' and contains(text(), '"+caption+"')]"
		);
		PageObjectLogging.log("VerifyVideoInEditMode", "Verify that video appears in edit mode", true);
	}

	public void verifyLeftVideoInEditMode() {
		waitForElementByElement(iFrame);
		driver.switchTo().frame(iFrame);
		waitForElementByElement(leftAlignedVideoOnEditor);
		driver.switchTo().defaultContent();
		PageObjectLogging.log("verifyLeftVideoInEditMode", "Verify that video appears in the left at the editor", true, driver);

	}

	public void verifyRightVideoInEditMode() {
		waitForElementByElement(iFrame);
		driver.switchTo().frame(iFrame);
		waitForElementByElement(rightAlignedVideoOnEditor);
		driver.switchTo().defaultContent();
		PageObjectLogging.log("verifyRighttVideoInEditMode", "Verify that video appears in the right at the editor", true, driver);

	}

	public void verifyCenterVideoInEditMode() {
		waitForElementByElement(iFrame);
		driver.switchTo().frame(iFrame);
		waitForElementByElement(centerAlignedVideoOnEditor);
		driver.switchTo().defaultContent();
		PageObjectLogging.log("verifyCenterVideoInEditMode", "Verify that video appears in the center at the editor", true, driver);
	}



	/**
	 * Verify that the video appears in the left hand side in preview
	 *
	 * @author Rodrigo Molinero
	 * 	 */

	public void verifyVideoOnTheLeftInPreview() {
		waitForElementByElement(leftAlignedVideoOnPreview);
		PageObjectLogging.log("verifyVideoOnTheLeftOnPreview", "Verify that the video appears on the left in preview", true, driver);
	}

	/**
	 * Verify that the video appears in the right hand side in preview
	 *
	 * @author Rodrigo Molinero
	 * 	 */

	public void verifyVideoOnTheRightInPreview() {
		waitForElementByElement(rightAlignedVideoOnPreview);
		PageObjectLogging.log("verifyRightVideoOnTheLeftOnPreview", "Verify that the video appears on the right in preview", true, driver);
	}

	public void verifyVideoOnTheCenterInPreview() {
		waitForElementByElement(centerAlignedVideoOnPreview);
		PageObjectLogging.log("verifyVideoOnTheCenterInPreview", "Verify that the video appears on the center in preview", true, driver);
	}


	public void clickClosePreviewModalButton() {
		waitForElementByElement(closePreviewModal);
		PageObjectLogging.log("clickClosePreviewModalButton", "Verify that the close button in the preview modal is clicked", true, driver);
	}


	/**
	* type category name
	*
	@author Michal Nowierski
	*/
	public void categories_addCategoryEditMode(String categoryName) {
		waitForElementByElement(categories_CategoryInputField);
		waitForElementClickableByElement(categories_CategoryInputField);
		scrollAndClick(categories_CategoryInputField);
		jQueryFocus("#CategorySelectInput");
		categories_CategoryInputField.sendKeys(categoryName);
		try {Thread.sleep(500);	} catch (InterruptedException e) {e.printStackTrace();}
		executeScript("var e = jQuery.Event(\"keydown\"); e.which=13; $('#CategorySelectInput').trigger(e);");
		PageObjectLogging.log("categories_typeCategoryNameEditMode", "category "+categoryName+" typed", true, driver);
	}

	/**
	* type category name in source mode
	*
	@author Michal Nowierski
	*/
	public void categories_addToCategorySourceEditMode(String textToBeAdded) {
		waitForElementByElement(categories_CategorySourceInputField);
		waitForElementClickableByElement(categories_CategorySourceInputField);
		scrollAndClick(categories_CategorySourceInputField);
		categories_CategorySourceInputField.sendKeys(textToBeAdded);
		try {Thread.sleep(500);	} catch (InterruptedException e) {e.printStackTrace();};
		PageObjectLogging.log("categories_addCategorySourceEditMode", "category "+textToBeAdded.replaceAll("<", "&lt").replaceAll(">", "&gt")+" typed in the source mode", true, driver);
	}

	/**
	 * check category name in source mode
	 *
	@author Michal Nowierski
	 */
	public void categories_verifyCategoryAddedSourceEditMode(String textToBeChecked) {
		waitForElementByElement(categories_CategorySourceInputField);
		String text = categories_CategorySourceInputField.getAttribute("value");
		if (text.contains(textToBeChecked)) {
			PageObjectLogging.log("categories_verifyCategoryAddedSourceEditMode", "category "+textToBeChecked.replaceAll("<", "&lt").replaceAll(">", "&gt")+" present in the source mode", true, driver);
		}
		else {
			PageObjectLogging.log("categories_verifyCategoryAddedSourceEditMode", "category "+textToBeChecked.replaceAll("<", "&lt").replaceAll(">", "&gt")+" NOT present in the source mode", false, driver);

		}
	}

	public String categories_addSuggestedCategoryEditMode(String givenString) {
		waitForElementByElement(categories_CategoryInputField);
		waitForElementClickableByElement(categories_CategoryInputField);
		scrollAndClick(categories_CategoryInputField);
		try {Thread.sleep(500);	} catch (InterruptedException e) {e.printStackTrace();}
		categories_CategoryInputField.sendKeys(givenString);
		List<WebElement> PromptsList  = driver.findElements(categories_listOfCategoriyPrompts);
		WebElement category = PromptsList.get(3);
		try {Thread.sleep(500);	} catch (InterruptedException e) {e.printStackTrace();}
		waitForElementByElement(category);
		String categoryName = category.getText();
		waitForElementClickableByElement(category);
		scrollAndClick(category);
		PageObjectLogging.log("categories_addSuggestedCategoryEditMode", "suggested category "+categoryName+" added", true, driver);
		return categoryName;
	}

	/**
	* categories_verifyCategoryAdded in edit mode
	*
	@author Michal Nowierski
	*/
	public void categories_verifyCategoryAddedEditMode(String categoryName) {
		List<WebElement> lista  = driver.findElements(By.cssSelector(categories_listOfCategories));
		Boolean result = false;
		// there might be more than one category on a random page. Thus - loop over all of them.
		for (WebElement webElement : lista) {
			waitForElementByElement(webElement);
			if (webElement.getText().equalsIgnoreCase(categoryName)) {
				result = true;
			}
		}
		if (result) {
			PageObjectLogging.log("categories_verifyCategoryAddedEditMode", "category "+categoryName+" succesfully added", true, driver);
		}
		else {
			PageObjectLogging.log("categories_verifyCategoryAddedEditMode", "category "+categoryName+" NOT added", false, driver);
		}

	}

	public void categories_removeCategoryEditMode(String categoryName) {
		List<WebElement> lista  = driver.findElements(By.cssSelector(categories_listOfCategories));
		WebElement categoryItem = null;
		WebElement categoryItemRemove = null;
		Boolean isTheCategoryOnList = false;
		By categoryItemBy = By.cssSelector("span");
		By categoryItemRemoveBy = By.cssSelector("img.delete");

		// We want item with given name. there might be more than one category on a random page. Thus - loop over all of them.
		for (WebElement webElement : lista) {
			waitForElementByElement(webElement);
			//if the element is the one we want
			if (webElement.findElement(categoryItemBy).getText().equalsIgnoreCase(categoryName)) {
				categoryItem = webElement.findElement(categoryItemBy);
				categoryItemRemove = webElement.findElement(categoryItemRemoveBy);
				isTheCategoryOnList = true;
			}
		}
		if (!isTheCategoryOnList) {
			PageObjectLogging.log("categories_removeCategoryEditMode", "category "+categoryName+" not found on list", false, driver);
			return;
		}
		List<WebElement> l = driver.findElements(By.cssSelector("li.category span"));
		int counter = 0;
		for (WebElement element:l)
		{
			if (element.getText().equals(categoryName))
			{
				counter = l.indexOf(element);
				counter+=1;
				jQueryClick("li.category:nth-child("+counter+") .delete");
			}
		}
		if (isTheCategoryOnList) {
			PageObjectLogging.log("categories_removeCategoryEditMode", "category "+categoryName+"  removed", true, driver);
			return;
		}

	}


	public void categories_verifyCategoryRemovedEditMode(String categoryName) {
		List<WebElement> lista  = driver.findElements(By.cssSelector(categories_listOfCategories+" span"));
		Boolean result = false;

		// there might be more than one category on a random page. Thus - loop over all of them.
		if (lista.size()>0) {

			for (WebElement webElement : lista) {
				waitForElementByElement(webElement);
				if (webElement.getText().equalsIgnoreCase(categoryName)) {
					result = true;
				}
			}
		}
		if (result) {
			PageObjectLogging.log("categories_verifyCategoryRemoved", "category "+categoryName+" not removed - found on the list", false, driver);
		}
		else {
			PageObjectLogging.log("categories_verifyCategoryRemoved", "category "+categoryName+" removed", true, driver);
		}

	}

	public void verifyBlockedUserMessage(){
		waitForElementByElement(blockedUserMessage1);
		waitForElementByElement(blockedUserMessage2);
		PageObjectLogging.log("verifyBlockedUserMessage", "blocked user message when attempting to create article verified", true, driver);
	}

	public void clickAddVideoButton() {
		waitForElementByElement(videoSubmitButton);
		videoSubmitButton.click();
		PageObjectLogging.log("clickAddVideoButton", "Add Video button is clicked", true, driver);
	}

	public void clickSubmitVideoButton()
	{
		waitForElementByElement(addVideoButton);
		addVideoButton.click();
		PageObjectLogging.log("clickSubmitVideoButton", "Submit Video button is clicked", true, driver);
	}

	public void verifySuccessAfterAddingVideo(){
		waitForElementByXPath("//h1[contains(text(), \"Success\")]");
		PageObjectLogging.log("verifySuccessAfterAddingVideo", "Verified Success after adding video modal is visible", true, driver);
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
		PageObjectLogging.log("verifyCaptionInEditMode", "Verified existence of caption in editor", true, driver);
	}



	public void verifyWikiTextInSourceMode(String text) {
		String wikiText = sourceModeTextArea.getAttribute("value");
		Assertion.assertStringContains(wikiText, text);
	}

	public void clickOnModifyImageLink() {
		waitForElementByElement(modifyButton);
		modifyButton.click();
		PageObjectLogging.log("clickOnModifyImageLink", "Modify image link is clicked", true, driver);
	}

	public void verifyVideoWidthInEditMode() {
		waitForElementByElement(iFrame);
		driver.switchTo().frame(iFrame);
		waitForElementByElement(videoWidthEditor);
		driver.switchTo().defaultContent();
		PageObjectLogging.log("verifyVideoWidthInEditMode", "Video width in editor is exactly the same as value set in VET modal", true, driver);
	}

	public void verifyVideoWidthOnPreview(String width) {
		waitForElementByElement(videoOnPreview);
		Assertion.assertEquals(width, videoOnPreview.getCssValue("width"));
		PageObjectLogging.log("verifyVideoWidthOnPreview", "Video width in preview is exactly the same as value set in VET modal", true, driver);
	}



	public void verifyLeftAlignmentIsSelected() {
		mouseOverInArticleIframe(imageArticleIFrame);
		clickOnModifyImageLink();
		waitForElementByElement(imageLeftAlignmentOption);

		if (imageLeftAlignmentOption.isSelected())
		{
			PageObjectLogging.log("verifyLeftAlignmentIsSelected", "Left allignment option is selected in modal", true);
			}
		else
			{
			PageObjectLogging.log("verifyLeftAlignmentIsSelected", "Left allignment option is NOT selected in modal", false);
		}

		waitForElementByElement(imageUploadCloseButton);
		imageUploadCloseButton.click();

	}

	public void verifyRightAlignmentIsSelected() {
		mouseOverInArticleIframe(imageArticleIFrame);
		clickOnModifyImageLink();
		waitForElementByElement(imageRightAlignmentOption);

		if (imageRightAlignmentOption.isSelected()){
			PageObjectLogging.log("verifyRightAlignmentIsSelected", "Right allignment option is selected in modal", true);
		}
		else{
			PageObjectLogging.log("verifyRightAlignmentIsSelected", "Right allignment option is NOT selected in modal", false);
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



	public void verifyNoVideoCaptionInEditMode() {
		waitForElementNotVisibleByElement(captionInEditor);
		PageObjectLogging.log("verifyNoCaptionInEditMode", "Verify that the video does not have a caption in the editor", true);
	}

	public PhotoAddComponentObject clickPhotoButton(){
		waitForElementByElement(photoButton);
		scrollAndClick(photoButton);
		PageObjectLogging.log("clickPhotoButton", "photo button clicked", true);
		return new PhotoAddComponentObject(driver);
	}

	public VetAddVideoComponentObject clickVideoButton(){
		waitForElementByElement(videoButton);
		scrollAndClick(videoButton);
		PageObjectLogging.log("clickVideoButton", "video button clicked", true);
		return new VetAddVideoComponentObject(driver);
	}

	public SliderBuilderComponentObject clickSliderButton(){
		waitForElementByElement(sliderButton);
		scrollAndClick(sliderButton);
		PageObjectLogging.log("clickSliderButton", "slider button clicked", true);
		return new SliderBuilderComponentObject(driver);
	}
	public SlideshowBuilderComponentObject clickSlideshowButton(){
		waitForElementByElement(slideshowButton);
		scrollAndClick(slideshowButton);
		PageObjectLogging.log("clickSlideshowButton", "slideshow button clicked", true);
		return new SlideshowBuilderComponentObject(driver);
	}
	public GalleryBuilderComponentObject clickGalleryButton(){
		waitForElementByElement(galleryButton);
		scrollAndClick(galleryButton);
		PageObjectLogging.log("clickGallery", "gallery button clicked", true);
		return new GalleryBuilderComponentObject(driver);
	}

	public String getMessageSourceText() {
		waitForElementByElement(messageSourceModeTextArea);
		PageObjectLogging.log("getMessageSourceText", "Get text of source mode text of message article page.", true, driver);
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

		PageObjectLogging.log("deleteUnwantedVideoFromMessage", "Delete all source code on the article", true, driver);
	}

}
