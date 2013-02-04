package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.CommonUtils;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;


public class WikiArticleEditMode extends WikiBasePageObject {

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
	@FindBy(css="tr.ImageUploadFindLinks td a")
	private WebElement addThisPhotoLink;
	@FindBy(css="#wpPreview")
	private WebElement previewButton;
	@FindBy(css="div.neutral.modalToolbar a[id='publish']")
	private WebElement publishButtonPreview;
	@FindBy(css="span.cke_button_ModeSource a")
	private WebElement sourceModeButton;
	@FindBy(css="span.RTEMediaOverlayEdit")
	private WebElement modifyButton;
	@FindBy(css="span.RTEMediaOverlayDelete")
	private WebElement removeButton;
	@FindBy(css="div.RTEConfirmButtons a[id='RTEConfirmCancel'] span")
	private WebElement cancelImageRemovalButton;
	@FindBy(css="a[id='RTEConfirmOk']")
	private WebElement oKbutton;
	@FindBy(css="img.video")
	private WebElement videoInEditMode;
	@FindBy(css="div.ArticlePreview img.sprite.play")
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
	@FindBy(css="#csCategoryInput")
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
	
	
	private By captionInPreview = By.cssSelector("section.modalWrapper.preview section.modalContent figcaption");
	private By removePhotoDialog = By.cssSelector("section.modalWrapper.RTEModal");
	private By imageOnArticleEditMode = By.cssSelector("div.WikiaArticle figure a img");
	private By videoOnArticleEditMode = By.cssSelector("img.video");
	private By slideShowOnArticleEditMode = By.cssSelector("img.image-slideshow");
	private By sliderOnArticleEditMode = By.cssSelector("img.image-gallery-slider");
	private By contextMenuIframeList = By.cssSelector("iframe[aria-label='Context Menu Options']");
	private By contextMenuOptionsList = By.cssSelector("span.cke_menuitem a");
	private By categories_listOfCategoriyPrompts = By.cssSelector("#csSuggestContainer div.yui-ac-bd ul li");
	private String imageArticleIFrame = "img";
	private String galleryArticleIFrame = "img.image-gallery";
	private String sliderArticleIFrame = "img.image-gallery-slider";
	private String slideShowArticleIFrame = "img.image-slideshow";
	private String videoArticleIFrame = "img.video";
	private String previewButtonSelector = "#wpPreview";
	private String publishButtonSelector = "div.neutral.modalToolbar a[id=\"publish\"]";
	private String editButtonArticleItem = "span.RTEMediaOverlayEdit";
	private String deleteButtonArticleItem = "span.RTEMediaOverlayDelete";
	private String categories_listOfCategories = "#csItemsContainer li";

	public WikiArticleEditMode(WebDriver driver, String Domain,
			String wikiArticle) {
		super(driver, Domain);
		this.articlename = wikiArticle;
		PageFactory.initElements(driver, this);
	}


	/**
	 * @author Karol Kujawiak
	 * @param name
	 * @return WikiArticleByName
	 */
	public WikiArticleEditMode editArticleByName(String name)
	{
		getUrl(Global.DOMAIN + "wiki/"+name+"?action=edit");
		return new WikiArticleEditMode(driver, Domain, name);
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
//		clickAndWait(previewButton);
		PageObjectLogging.log("LeftClickOnPreviewButton", "Left Click on 'Preview' Button", true, driver);
		
		
	}

	/**
	 * Verify that the image appears in the preview
	 *  
	 * @author Michal Nowierski
	 */
	public void verifyTheImageOnThePreview() {
		waitForElementByElement(imageOnPreview);
		PageObjectLogging.log("VerifyTheImageOnThePreview", "Verify that the image appears in the preview", true, driver);
	
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
//		clickAndWait(publishButtonPreview);
		jQueryClick(publishButtonSelector);
		PageObjectLogging.log("LeftClickOnPublishButtonInPreviewMode", "Click on 'Publish' button in preview mode", true, driver);
	
		return new WikiArticlePageObject(driver, Domain, articlename);
	}

	/**
	 * Click on 'Source' button
	 *  
	 * @author Michal Nowierski
	 */
	public WikiArticleSourceEditMode clickOnSourceButton() {
		waitForElementByElement(sourceModeButton);
		waitForElementClickableByElement(sourceModeButton);
		clickAndWait(sourceModeButton);
//		jQueryClick("span.cke_button_ModeSource a");
		waitForElementByElement(sourceModeTextArea);
		PageObjectLogging.log("ClickOnSourceButton", "Click on 'Source' button", true, driver);
		return new WikiArticleSourceEditMode(driver, this.Domain);
	}
	
	/**
	 * Click on 'Source' button
	 *  
	 * @author Michal Nowierski
	 */
	public void clickOnVisualButton() {
		waitForElementByElement(visualModeButton);
		waitForElementClickableByElement(visualModeButton);
		clickAndWait(visualModeButton);
		waitForElementByElement(iFrame);
		PageObjectLogging.log("ClickOnVisualButton", "Click on 'Visual' button", true, driver);
		
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
		clickAndWait(visualModeButton);
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
//		clickOnSourceButton();
//		waitForElementByElement(sourceModeTextArea);
//		sourceModeTextArea.clear();
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
		clickAndWait(publishButtonPreview);
		PageObjectLogging.log("ClickOnPublishButtonPreview", "Click on 'Publish' button in preview", true, driver);
		
		return new WikiArticlePageObject(driver, Domain, articlename);
	}
	
	/**
	 * Hover your phisical mouse cursor over image. Identify image by its caption
	 *  
	 * @author Michal Nowierski
	 * @param caption Caption of the image 
	 * 	 */
	public void hoverCursorOverImage(String caption) {
		waitForElementByElement(iFrame);
		CommonFunctions.MoveCursorToIFrameElement(By.cssSelector("img[data-rte-meta*='"+caption+"']"), iFrame);
		PageObjectLogging.log("HoverCursorOverImage", "Hover your phisical mouse cursor over image.", true, driver);
	}
	
	public void typeInContent(String content)
	{
		waitForElementByElement(visualModeIFrame);
		driver.switchTo().frame(visualModeIFrame);
		waitForElementByElement(bodyContent);
		if (Global.BROWSER.equals("FF"))
		{
			((JavascriptExecutor) driver).executeScript("document.body.innerHTML='" + content + "'");
		}
		else
		{
			bodyContent.sendKeys(content);			
		}
		driver.switchTo().defaultContent();
		PageObjectLogging.log("typeInContent", "content type into article body", true, driver);
	}
	

	



	/**
	 * Click on 'modify button' of image with given caption
	 *  
	 * @author Michal Nowierski
	 * @param caption Caption of the image 
	 * 	 */
	public void clickModifyButtonOfImage(String caption) 
	{
		waitForElementByElement(iFrame);
		mouseOverInArticleIframe(imageArticleIFrame);
		waitForElementByElement(modifyButton);
		jQueryClick(editButtonArticleItem);
		PageObjectLogging.log("ClickModifyButtonOfImage", "Click on 'modify button' of image with caption: '"+caption+"'", true, driver);
	}
	
	public void clickModifyButtonGallery()
	{
		waitForElementByElement(iFrame);
		mouseOverInArticleIframe(galleryArticleIFrame);
		waitForElementByElement(modifyButton);
		jQueryClick(editButtonArticleItem);
		PageObjectLogging.log("clickModifyButtonGallery", "Click on 'modify button' on gallery", true, driver);
	}
	
	public void clickModifyButtonSlideshow() 
	{
		waitForElementByElement(iFrame);
		mouseOverInArticleIframe(slideShowArticleIFrame);
		waitForElementByElement(modifyButton);
		jQueryClick(editButtonArticleItem);
		PageObjectLogging.log("clickModifyButtonSlideshow", "Click on 'modify button' on slideshow", true, driver);
	}

	public void clickModifyButtonSlider() 
	{
		waitForElementByElement(iFrame);
		mouseOverInArticleIframe(sliderArticleIFrame);
		waitForElementByElement(modifyButton);
		jQueryClick(editButtonArticleItem);
		PageObjectLogging.log("clickModifyButtonSlideshow", "Click on 'modify button' on slideshow", true, driver);		
	}
	
	public void clickModifyButtonVideo() {
		waitForElementByElement(iFrame);
		mouseOverInArticleIframe(videoArticleIFrame);
		waitForElementByElement(modifyButton);
		jQueryClick(editButtonArticleItem);
		PageObjectLogging.log("clickModifyButtonSlideshow", "Click on 'modify button' on video", true, driver);		
	}

	/**
	 * Click on 'remove button' of image with given caption
	 *  
	 * @author Michal Nowierski
	 * @param caption Caption of the image 
	 * 	 */
	public void clickRemoveButtonOfImage(String caption) {
		waitForElementByElement(iFrame);
		mouseOverInArticleIframe(imageArticleIFrame);
		waitForElementByElement(removeButton);
		jQueryClick(deleteButtonArticleItem);
		PageObjectLogging.log("ClickRemoveButtonOfImage", "Click on 'remove button' of image with caption: '"+caption+"'", true, driver);
	}
	
	public void clickRemoveButtonGallery() {
		waitForElementByElement(iFrame);
		mouseOverInArticleIframe(galleryArticleIFrame);
		waitForElementByElement(removeButton);
		jQueryClick(deleteButtonArticleItem);
		PageObjectLogging.log("ClickRemoveButtonOfImage", "Click on 'remove button' on gallery", true, driver);
	}
	
	public void clickRemoveButtonSlideshow() {
		waitForElementByElement(iFrame);
		mouseOverInArticleIframe(slideShowArticleIFrame);
		waitForElementByElement(removeButton);
		jQueryClick(deleteButtonArticleItem);
		PageObjectLogging.log("ClickRemoveButtonOfImage", "Click on 'remove button' on gallery", true, driver);
	}
	
	public void clickRemoveButtonSlider() {
		waitForElementByElement(iFrame);
		mouseOverInArticleIframe(sliderArticleIFrame);
		waitForElementByElement(removeButton);
		jQueryClick(deleteButtonArticleItem);
		PageObjectLogging.log("ClickRemoveButtonOfImage", "Click on 'remove button' on gallery", true, driver);
	}
	
	public void clickRemoveButtonVideo() {
		waitForElementByElement(iFrame);
		mouseOverInArticleIframe(videoArticleIFrame);
		waitForElementByElement(removeButton);
		jQueryClick(deleteButtonArticleItem);
		PageObjectLogging.log("ClickRemoveButtonOfImage", "Click on 'remove button' on gallery", true, driver);
	}

	/**
	 * Left Click on Cancel button
	 *  
	 * @author Michal Nowierski
	 * 	 */
	public void leftClickCancelButton() {
		waitForElementByElement(cancelImageRemovalButton);
		waitForElementClickableByElement(cancelImageRemovalButton);
		clickAndWait(cancelImageRemovalButton);
		PageObjectLogging.log("LeftClickCancelButton", "Left Click on Cancel button", true, driver);
				
	}

	/**
	 * Verify that 'Remove this photo?' modal has disappeared
	 *  
	 * @author Michal Nowierski
	 * 	 */
	public void verifyModalDisappeared() {
		waitForElementNotVisibleByBy(removePhotoDialog);
		PageObjectLogging.log("VerifyModalDisappeared", "Verify that 'Remove this photo?' modal has disappeared", true, driver);
	}

	/**
	 * Left Click on Ok button on remove photo dialog
	 *  
	 * @author Michal Nowierski
	 * 	 */
	public void leftClickOkButton() {
		waitForElementByElement(oKbutton);
		waitForElementClickableByElement(oKbutton);
		clickAndWait(oKbutton);
		PageObjectLogging.log("LeftClickOkButton", "Left Click on Ok button on remove photo dialog", true, driver);
		
		
	}

	/**
	 * Verify that the image does not appear on the Article Edit mode
	 *  
	 * @author Michal Nowierski
	 * 	 */
	public void verifyTheImageNotOnTheArticleEditMode() {
		waitForElementNotVisibleByBy(imageOnArticleEditMode);//this need to be rewritten
		PageObjectLogging.log("VerifyTheImageNotOnTheArticleEditMode", "Verify that the image does not appear on the Article edit mode", true, driver);
				
	}
	
	public void verifyTheGalleryNotOnTheArticleEditMode() {
		waitForElementNotVisibleByBy(imageOnArticleEditMode);//this need to be rewritten
		PageObjectLogging.log("VerifyTheImageNotOnTheArticleEditMode", "Verify that the image does not appear on the Article edit mode", true, driver);
	}
	
	public void verifyTheSlideshowNotOnTheArticleEditMode() {
		driver.switchTo().frame(iFrame);
		waitForElementNotVisibleByBy(slideShowOnArticleEditMode);
		driver.switchTo().defaultContent();
		PageObjectLogging.log("VerifyTheImageNotOnTheArticleEditMode", "Verify that the image does not appear on the Article edit mode", true, driver);		
	}
	
	public void verifyTheVideoNotOnTheArticleEditMode() {
		driver.switchTo().frame(iFrame);
		waitForElementNotVisibleByBy(videoOnArticleEditMode);
		driver.switchTo().defaultContent();
		PageObjectLogging.log("verifyTheVideoNotOnTheArticleEditMode", "Verify that the video does not appear on the Article edit mode", true, driver);	
	}
	
	public void verifyTheSliderNotOnTheArticleEditMode() {
		driver.switchTo().frame(iFrame);
		waitForElementNotVisibleByBy(sliderOnArticleEditMode);
		driver.switchTo().defaultContent();
		PageObjectLogging.log("verifyTheSliderNotOnTheArticleEditMode", "Verify that the slider does not appear on the Article edit mode", true, driver);
		
	}

	

	/**
	 * Verify Gallery object appears in edit mode
	 *  
	 * @author Michal Nowierski
	 * @param Object Object = {gallery, slideshow, gallery-slider}
	 * 	 */
	public void verifyObjectInEditMode(String Object) {
		//The Editor is iframe so we have to switch to the iframe in order to investigate its content
		waitForElementByElement(iFrame);
//		WebElement iFrame = driver.findElement(IframeVisualEditor);
		driver.switchTo().frame(iFrame);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.media-placeholder.image-"+Object)));
		// Now switch back to the normal DOM
		driver.switchTo().defaultContent();
		PageObjectLogging.log("VerifyObjectInEditMode", "Verify "+Object+" object appears in edit mode", true, driver);
		
	}

	/**
	 * Verify Gallery object appears in the preview
	 *  
	 * @author Michal Nowierski
	 * @param Object Object = {gallery, slideshow, slider}
	 * 	 */
	public void verifyTheObjectOnThePreview(String Object) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ArticlePreview div[id*='"+Object+"']")));
		PageObjectLogging.log("VerifyTheObjectOnThePreview", "Verify that the "+Object+" appears in the preview", true, driver);		
	}

	

	

	/**
	 * Verify that video appears in edit mode
	 *  
	 * @author Michal Nowierski
	 * 	 */
	public void verifyVideoInEditMode(String caption) {
		waitForElementByElement(iFrame);
//		WebElement iFrame = driver.findElement(IframeVisualEditor);
		driver.switchTo().frame(iFrame);
		waitForElementByElement(videoInEditMode);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(VideoInEditMode));		
		driver.switchTo().defaultContent();
		mouseOverInArticleIframe(videoArticleIFrame);
		waitForElementByXPath("//div[@class='RTEMediaCaption' and contains(text(), '"+caption+"')]");
		mouseReleaseInArticleIframe(videoArticleIFrame);
		PageObjectLogging.log("VerifyVideoInEditMode", "Verify that video appears in edit mode", true, driver);
		
	}

	/**
	 * Verify that the video appears in the preview
	 *  
	 * @author Michal Nowierski
	 * 	 */
	public void verifyTheVideoOnThePreview() {
	waitForElementByElement(videoOnPreview);
	PageObjectLogging.log("VerifyTheVideoOnThePreview", "Verify that the video appears in the preview", true, driver);
			
	}






	/**
	 * Get text of source mode text of message article page. Remmember that source mode must be turned on to invoke this method. Just invoke 'ClickOnSourceButton'
	 * Message article page is e.g http://mediawiki119.wikia.com/wiki/MediaWiki:RelatedVideosGlobalList
	 * 
	 * @author Michal Nowierski
	 */
	public String getMessageSourceText() {
		waitForElementByElement(messageSourceModeTextArea);
		PageObjectLogging.log("getMessageSourceText", "Get text of source mode text of message article page.", true, driver);		
		return messageSourceModeTextArea.getText();
	}
	
	/**
	 * Delete unwanted video by its name.
	 * Message article page is e.g http://mediawiki119.wikia.com/wiki/MediaWiki:RelatedVideosGlobalList
	 * This method destination is exactly related videos message article
	 *  
	 * @author Michal Nowierski
	 * @param unwantedVideoName e.g "What is love (?) - on piano (Haddway)"
	 */
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
		
//		for (int i = 0; i < videos.size(); i++) {
//			messageSourceModeTextArea.sendKeys(videos.get(i));
//			messageSourceModeTextArea.sendKeys(Keys.ENTER);
//		}
		PageObjectLogging.log("deleteUnwantedVideoFromMessage", "Delete all source code on the article", true, driver);
	}

	
	/**
	 * 
	 * Left Click on add Table button.
	 * 
	 * 
	 * 
	 * 
	 @author Michal Nowierski
	 */

	public void clickOnAddTableButton() {

		waitForElementByElement(tableButton);
		waitForElementClickableByElement(tableButton);
		tableButton.click();
		PageObjectLogging.log("clickOnAddTableButton","Click on: table-button, on wiki: " + Domain + "",true, driver);
	}

	/**
	* wait for table modal
	*
	*
	@author Michal Nowierski
	*/
	public void verifyTableModal() {
	waitForElementByElement(tableModal);
	PageObjectLogging.log("waitForTableModal", "wait for table modal", true, driver);
	}

	/**
	* Click OK on table modal
	*
	*
	@author Michal Nowierski
	*/
	public void clickOKonTableModal() {
	waitForElementByElement(tableModalOKbutton);
	waitForElementClickableByElement(tableModalOKbutton);
	tableModalOKbutton.click();
	PageObjectLogging.log("clickOKonTableModal", "Click OK on table modal", true, driver);
	}

	/**
	* Verify that the table has appeared in the visual mode
	*
	*
	@author Michal Nowierski
	*/

	public void verifyTableAppears() {
		waitForElementByElement(visualModeIFrame);
		driver.switchTo().frame(visualModeIFrame);
		waitForElementByElement(VisualModeTable);
		driver.switchTo().defaultContent();
		PageObjectLogging.log("verifyTableAppeared",
				"Verify that the table has appeared in the visual mode", true,
				driver);
	}

	
	/**
	* Populate table cell
	*
	*
	@author Michal Nowierski
	*
	@param string value - text to be present in the cell
	@param j row index
	@param i column index
	*/

	public void tablePupulateCell(int i, int j, String value) {
	int cellPosition = (i*2)+(j-3);
	executeScript("$($($('iframe[title*=\"Rich\"]')[0].contentDocument.body).find('table.article-table tr *:not(br)')["+cellPosition+"]).text('"+value+"')");
// 	 *:not(br)  expression matches all possible elements, except <br> elements
	PageObjectLogging.log("tablePupulateCell", "send "+value+" to cell at row: "+i+", column: "+j+"", true, driver);

	}

	/**
	* Check if there are enough rows
	*
	*
	@author Michal Nowierski
	*
	@param i number of rows
	*/
	public void tableCheckTableRowsCount(int i) {
	waitForElementByElement(visualModeIFrame);
	driver.switchTo().frame(visualModeIFrame);
	waitForElementByElement(VisualModeTable);

	List<WebElement> RowsList;

	for (int j = 0; j < i; j++) {
	RowsList = VisualModeTable.findElements(By.cssSelector("tr"));
	waitForElementByElement(RowsList.get(j));
	}

	driver.switchTo().defaultContent();
	PageObjectLogging.log("tableCheckTableRowsCount", "check that there are "+i+" rows in the table", true, driver);
	}

	/**
	* Right click on a cell
	*
	*
	@author Michal Nowierski
	*
	@param i row index of the cell
	@param j column index of the cell
	*/
	public void tableRightClickOnCell(int i, int j) {

	waitForElementByElement(visualModeIFrame);
	driver.switchTo().frame(visualModeIFrame);
	waitForElementByElement(VisualModeTable);

	List<WebElement> RowsList = VisualModeTable.findElements(By.cssSelector("tr"));
	List<WebElement> CellsList = RowsList.get(i-1).findElements(By.cssSelector("*:not(br)"));

	Actions builderq = new Actions(driver);
	Action rClick = builderq.contextClick(CellsList.get(j-1)).build();
	rClick.perform();

	driver.switchTo().defaultContent();
	// somehow, the driver does not click on the wanted cell. It click Just on table
	PageObjectLogging.log("tableRightClickOnCell", "Right click on cell at row: "+i+", column: "+j+"", true, driver);

	}

	/**
	* Check that pointed table cell has wanted value in it
	*
	*
	@author Michal Nowierski
	*
	@param i row index of the cell
	@param j column index of the cell
	*/
	public void tableCheckCellContent(int i, int j, String value){

	waitForElementByElement(visualModeIFrame);
	driver.switchTo().frame(visualModeIFrame);
	waitForElementByElement(VisualModeTable);
	
	List<WebElement> RowsList = VisualModeTable.findElements(By.cssSelector("tr"));
	List<WebElement> CellsList = RowsList.get(i-1).findElements(By.cssSelector("*:not(br)"));

	waitForElementByElement(CellsList.get(j-1));
	waitForTextToBePresentInElementByElement(CellsList.get(j-1), value);

	driver.switchTo().defaultContent();
	PageObjectLogging.log("tableCheckCellContent", "Check that cell at row: "+i+", column: "+j+" has value: "+value+" in it", true, driver);

	}

	/**
	* Choose an option from table context menu.
	* Right click on table must be invoked before this method
	*
	*
	@author Michal Nowierski
	*
	@param i position of an option in the context menu. <br>
	1 = Paste, 2 = Cell, 3 = Row (...)
	@param j position of an option in the extended context menu <br>
	i = 2 , j = 1 indicates "insert cell before" option
	<br>
	<br> Manually right click on table context menu for better understanding
	*/
	public void tableChooseFromContextMenu(int i, int j) {

	List<WebElement> list = driver.findElements(contextMenuIframeList);
	driver.switchTo().frame(list.get(0));
	List<WebElement> list2 = driver.findElements(contextMenuOptionsList);

	WebElement click = list2.get(i-1);
	waitForElementByElement(click);
	click.click();
	driver.switchTo().defaultContent();

	if (i ==2 | i ==3 | i ==4) {
	list = driver.findElements(contextMenuIframeList);
	driver.switchTo().frame(list.get(1));
	list2 = driver.findElements(contextMenuOptionsList);
	click = list2.get(j-1);
	waitForElementByElement(click);
	click.click();
	driver.switchTo().defaultContent();

	}

	PageObjectLogging.log("tableChooseFromContextMenu", "Choose the indicated option from context menu", true, driver);
	}


	/**
	* type category name
	*
	@author Michal Nowierski
	*/
	public void categories_addCategoryEditMode(String categoryName) {
		waitForElementByElement(categories_CategoryInputField);
		waitForElementClickableByElement(categories_CategoryInputField);
		clickAndWait(categories_CategoryInputField);
		jQueryFocus("#csCategoryInput");
		categories_CategoryInputField.sendKeys(categoryName);
		try {Thread.sleep(500);	} catch (InterruptedException e) {e.printStackTrace();}
		executeScript("var e = jQuery.Event(\"keypress\"); e.keyCode=13; $('#csCategoryInput').trigger(e);");
//		categories_CategoryInputField.sendKeys(Keys.ENTER);
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
		clickAndWait(categories_CategorySourceInputField);
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
		clickAndWait(categories_CategoryInputField);
		try {Thread.sleep(500);	} catch (InterruptedException e) {e.printStackTrace();}
		categories_CategoryInputField.sendKeys(givenString);
		List<WebElement> PromptsList  = driver.findElements(categories_listOfCategoriyPrompts);
		WebElement category = PromptsList.get(3);
		try {Thread.sleep(500);	} catch (InterruptedException e) {e.printStackTrace();}
		waitForElementByElement(category);
		String categoryName = category.getText(); 
		waitForElementClickableByElement(category);
		clickAndWait(category);
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
		By categoryItemBy = By.cssSelector("span");
		// there might be more than one category on a random page. Thus - loop over all of them.
		for (WebElement webElement : lista) {
			waitForElementByElement(webElement);		
			if (webElement.getText().equalsIgnoreCase(categoryName)) {
//				if (webElement.findElement(categoryItemBy).getText().equalsIgnoreCase(categoryName)) {
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
		List<WebElement> l = driver.findElements(By.cssSelector("li.CSitem span"));
		int counter = 0;
		for (WebElement element:l)
		{
			if (element.getText().equals(categoryName))
			{
				counter = l.indexOf(element);
				counter+=1;
				jQueryClick("li.CSitem:nth-child("+counter+") .delete");
			}
		}
//		clickAndWait(categoryItem);
//		waitForElementByElement(categoryItemRemove);
//		clickAndWait(categoryItemRemove);
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
	
	
	public void verifyWikiTextInSourceMode(String text) {
		String wikiText = sourceModeTextArea.getAttribute("value");
		Assertion.assertStringContains(wikiText, text);
//		if (wikiText.contains(text))
//		{
//			PageObjectLogging.log("verifyWikiTextInSourceMode", "Correct wiki text is visible in source mode", true);
//		}
//		else
//		{
//			PageObjectLogging.log("verifyWikiTextInSourceMode", "Incorrect wiki text is visible in source mode", false);
//		}
		
		
//		waitForTextToBePresentInElementByElement(sourceModeText, text);
		
	}
	
	public void clickOnModifyImageLink() {
		waitForElementByElement(modifyButton);
		modifyButton.click();
		PageObjectLogging.log("clickOnModifyImageLink", "Modify image link is clicked", true, driver);
	}
	
	public void verifyLeftAlignmentIsSelected() {
		
//		waitForElementByElement(visualModeIFrame);
//		driver.switchTo().frame(visualModeIFrame);
		mouseOverInArticleIframe(imageArticleIFrame);
//		waitForElementByElement(modifyButton);
		clickOnModifyImageLink();
//		waitForElementByElement(visualModeIFrame);
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
//		driver.switchTo().defaultContent();
		
	}
	
public void verifyRightAlignmentIsSelected() {
		
//		waitForElementByElement(visualModeIFrame);
//		driver.switchTo().frame(visualModeIFrame);
		mouseOverInArticleIframe(imageArticleIFrame);
//		waitForElementByElement(modifyButton);
		clickOnModifyImageLink();
//		waitForElementByElement(visualModeIFrame);
		waitForElementByElement(imageRightAlignmentOption);
		
		if (imageRightAlignmentOption.isSelected())
		{
			PageObjectLogging.log("verifyRightAlignmentIsSelected", "Right allignment option is selected in modal", true);
			}
		else
			{
			PageObjectLogging.log("verifyRightAlignmentIsSelected", "Right allignment option is NOT selected in modal", false);
		}		
		
		waitForElementByElement(imageUploadCloseButton);
		imageUploadCloseButton.click();
//		driver.switchTo().defaultContent();
		
	}
	
	public WikiArticlePageObject addImageForLightboxTesting () {
		clickOnAddObjectButton("Image");
		waitForElementByElement(findInputField);
		findInputField.sendKeys("aa");
		waitForElementByElement(findButton);
		findButton.click();		
//		waitForElementByXPath("//div[@id='ImageUploadHeadline' and contains(text(), 'Photos on this wiki')]");
		waitForElementByCss("#ImageUploadProgress2");
		waitForElementByCss("img[src*='AmericaAfrica']");
//		CommonFunctions.assertString("Photos on this wiki (1 results)", ImageUploadHeadline.getText());
		waitForElementByElement(ImageUploadHeadline);
		addThisPhotoLink.click();
		waitForElementByElement(finalAddPhotoButton);
		finalAddPhotoButton.click();
		clickOnPublishButton();
		return new WikiArticlePageObject(driver, Domain, articlename);
		
		
		
}
	
	
}
