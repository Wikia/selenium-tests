package com.wikia.webdriver.PageObjects.PageObject.WikiPage;

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

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.CommonUtils;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;


public class WikiArticleEditMode extends WikiArticlePageObject {

	@FindBy(css="div.reset[id='ImageUpload']")
	private WebElement imageUploadModal;
	@FindBy(css="textarea[id='ImageUploadCaption']")
	private WebElement captionTextArea;
	@FindBy(css="div.cke_skin_wikia.visible div.cke_contents iframe")
	private WebElement visualModeIFrame;
	@FindBy(css=".cke_source")
	private WebElement sourceModeTextArea;
	@FindBy(css="textarea.yui-ac-input")
	private WebElement messageSourceModeTextArea;
	@FindBy(css="div.cke_wrapper.cke_ltr div.cke_contents iframe")
	private WebElement iFrame;
	@FindBy(css="header.WikiaHeader")
	private WebElement header;
	@FindBy(css="tr.ImageUploadFindLinks td a")
	private WebElement addThisPhotoLink;
	
//	@FindBy(css="div.module_content nav.buttons nav.wikia-menu-button a")
	@FindBy(css="#wpPreview")
	private WebElement previewButton;
	@FindBy(css="div.neutral.modalToolbar a[id='publish']")
	private WebElement publishButtonPreview;
	@FindBy(css="span.cke_button_ModeSource a")
	private WebElement sourceModeButton;
	@FindBy(css="input.control-button")
	private WebElement publishButtonGeneral;
	@FindBy(css="span.RTEMediaOverlayEdit")
	private WebElement modifyButton;
	@FindBy(css="span.RTEMediaOverlayDelete")
	private WebElement removeButton;
	@FindBy(css="div.RTEConfirmButtons a[id='RTEConfirmCancel'] span")
	private WebElement cancelImageRemovalButton;
	@FindBy(css="a[id='RTEConfirmOk']")
	private WebElement oKbutton;
	@FindBy(css="section[id='WikiaPhotoGalleryEditor']")
	private WebElement objectModal;
	@FindBy(css="a[id='WikiaPhotoGallerySearchResultsSelect']")
	private WebElement galleryDialogSelectButton;
	@FindBy(css="a[id='WikiaPhotoGalleryEditorSave']")
	private WebElement galleryDialogFinishButton;
	@FindBy(css="input[id='VideoEmbedCaption']")
	private WebElement videoCaptionTextArea;
	@FindBy(css="img.video")
	private WebElement videoInEditMode;
	@FindBy(css="div.ArticlePreview span.Wikia-video-play-button")
	private WebElement videoOnPreview;
	@FindBy(css="span.cke_button_ModeWysiwyg a")
	private WebElement visualModeButton;
	@FindBy(css="section.modalWrapper.preview section.modalContent figure a img")
	private WebElement imageOnPreview;
	@FindBy(css="body[id='bodyContent']")
	private WebElement bodyContent;
	@FindBy(css="input[name='search'][placeholder='Search photos on this wiki']")
	private WebElement searchFieldImageInLightBox;
	@FindBy(css="img.sprite.search")
	private WebElement searchButtonImageInLightBox;
	@FindBy(css="span.cke_button_table a")
	private WebElement tableButton;
	@FindBy(css="div.cke_dialog.modalWrapper")
	private WebElement tableModal;
	@FindBy(css="a.cke_dialog_ui_button.wikia-button")
	private WebElement tableModalOKbutton;
	@FindBy(css="table.article-table")
	private WebElement VisualModeTable;
	
	private By captionInPreview = By.cssSelector("section.modalWrapper.preview section.modalContent figcaption");
	private By removePhotoDialog = By.cssSelector("section.modalWrapper.RTEModal");
	private By imageOnArticleEditMode = By.cssSelector("div.WikiaArticle figure a img");
	private By videoOnArticleEditMode = By.cssSelector("img.video");
	private By slideShowOnArticleEditMode = By.cssSelector("img.image-slideshow");
	private By sliderOnArticleEditMode = By.cssSelector("img.image-gallery-slider");
	private By galleryDialogPhotosList = By.cssSelector("ul[class='WikiaPhotoGalleryResults'][type='results'] li input");
	private By galleryDialogPhotoOrientationsList = By.cssSelector("ul.clearfix[id='WikiaPhotoGalleryOrientation'] li");
	private By galleryDialogSlideshowOrientationsList = By.cssSelector("ul.clearfix[id='WikiaPhotoGallerySliderType'] li");
	
	private By contextMenuIframeList = By.cssSelector("iframe[aria-label='Context Menu Options']");
	private By contextMenuOptionsList = By.cssSelector("span.cke_menuitem a");

	private String imageArticleIFrame = "img";
	private String galleryArticleIFrame = "img.image-gallery";
	private String sliderArticleIFrame = "img.image-gallery-slider";
	private String slideShowArticleIFrame = "img.image-slideshow";
	private String videoArticleIFrame = "img.video";
	private String previewButtonSelector = "#wpPreview";
	private String publishButtonSelector = "div.neutral.modalToolbar a[id=\"publish\"]";
	
	private String editButtonArticleItem = "span.RTEMediaOverlayEdit";
	private String deleteButtonArticleItem = "span.RTEMediaOverlayDelete";
	
	
	public WikiArticleEditMode(WebDriver driver, String Domain,
			String articlename) {
		super(driver, Domain, articlename);
		PageFactory.initElements(driver, this);
	}

	/**
	 * Left Click on add Object button.
	 *  
	 * @author Michal Nowierski
	 * @param Object Object = {Image, Gallery, Slideshow, Slider, Video}
	 */
	public void clickOnAddObjectButton(String Object) {
		// TODO Auto-generated method stub
		String ObjectCss = "span.cke_button.RTE"+Object+"Button a";
		WebElement ObjectButton;
		waitForElementByCss(ObjectCss);
		waitForElementClickableByCss(ObjectCss);
		ObjectButton = driver.findElement(By.cssSelector(ObjectCss));
		clickAndWait(ObjectButton);
		PageObjectLogging.log("ClickOnAddObjectButton", "Edit Article: "+articlename+", on wiki: "+Domain+"", true, driver);
		
	}


	public WikiArticleEditMode editArticleByName(String name)
	{
		getUrl(Global.DOMAIN + "wiki/"+name+"?action=edit");
		return new WikiArticleEditMode(driver, Domain, name);
	}

	/**
	 * Type given caption for the photo
	 *  
	 * @author Michal Nowierski
	 */
	public void typePhotoCaption(String caption) {
		waitForElementByElement(captionTextArea);
		captionTextArea.clear();
		captionTextArea.sendKeys(caption);
		PageObjectLogging.log("TypeAcaption", "Type any caption for the photo", true, driver);
		}
	
	/**
	 * Type given caption for the video
	 *  
	 * @author Michal Nowierski
	 */
	public void typeVideoCaption(String caption) {
		waitForElementByElement(videoCaptionTextArea);
		videoCaptionTextArea.clear();
		videoCaptionTextArea.sendKeys(caption);
		PageObjectLogging.log("TypeAcaption", "Type any caption for the photo", true, driver);
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
	public void clickOnSourceButton() {
		waitForElementByElement(sourceModeButton);
		waitForElementClickableByElement(sourceModeButton);
		clickAndWait(sourceModeButton);
//		jQueryClick("span.cke_button_ModeSource a");
		waitForElementByElement(sourceModeTextArea);
		PageObjectLogging.log("ClickOnSourceButton", "Click on 'Source' button", true, driver);
		
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

	/**
	 * Click  on Publish button
	 *  
	 * @author Michal Nowierski
	 */
	public WikiArticlePageObject clickOnPublishButton() {
		waitForElementByElement(publishButtonGeneral);
		waitForElementClickableByElement(publishButtonGeneral);
		clickAndWait(publishButtonGeneral);
		waitForElementByElement(editButton);
		PageObjectLogging.log("ClickOnPublishButton", "Click on 'Publish' button", true, driver);
	
		return new WikiArticlePageObject(driver, Domain, articlename);
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
	 * Wait for Object and click on 'add this photo' under the first seen
	 *  
	 * @author Michal Nowierski
	 * @param Object Object = {Gallery, GallerySlideshow, GallerySlider}
	 * 	 */
	public void waitForObjectModalAndClickAddAphoto(String Object) {
		waitForElementClickableByBy(By.cssSelector("button[id='WikiaPhoto"+Object+"AddImage']"));
		clickAndWait(driver.findElement(
				By.cssSelector("button[id='WikiaPhoto"+Object+"AddImage']")));
		PageObjectLogging.log("WaitForObjectModalAndClickAddAphoto", "Wait for "
				+ Object
				+ " modal and click on 'add a photo'", true, driver);
		waitForElementByElement(objectModal);
	}

	/**
	 * Wait for Object and click on 'add this photo' under the first seen
	 *  
	 * @author Michal Nowierski
	 * @param n n = parameter determining how many inputs the method should check
	 * 	 */
	public void galleryCheckImageInputs(int n) {
		driver.findElement(galleryDialogPhotosList);
		List<WebElement> List = driver.findElements(galleryDialogPhotosList);
		for (int i = 0; i < n; i++) {
			clickAndWait(List.get(i));
		}
		PageObjectLogging.log("CheckGalleryImageInputs", "Check first "+n+" image inputs", true, driver);
	}
	
	public void searchImageInLightBox(String imageName)
	{
		waitForElementByElement(searchFieldImageInLightBox);
		searchFieldImageInLightBox.sendKeys(imageName);
		clickAndWait(searchButtonImageInLightBox);
		waitForElementByElement(searchButtonImageInLightBox);
	}
	/**
	 * Gallery dialog: Left click 'Select' button
	 *  
	 * @author Michal Nowierski
	 * 	 */
	public void galleryClickOnSelectButton() {
		waitForElementByElement(galleryDialogSelectButton);
		waitForElementClickableByElement(galleryDialogSelectButton);
		clickAndWait(galleryDialogSelectButton);
		PageObjectLogging.log("GalleryClickOnSelectButton", "Gallery dialog: Left click 'Select' button", true, driver);
		
	}

	
	/**
	 * Gallery dialog: Left click 'Finish' button 
	 *  
	 * @author Michal Nowierski
	 * 	 */
	public void galleryClickOnFinishButton() {
		waitForElementByElement(galleryDialogFinishButton);
		waitForElementClickableByElement(galleryDialogFinishButton);
		clickAndWait(galleryDialogFinishButton);
		PageObjectLogging.log("GalleryClickOnFinishButton", "Gallery dialog: Left click 'Finish' button ", true, driver);
		
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
	 * Set photo orientation option number n
	 *  
	 * @author Michal Nowierski
	 * @param n = {1,2,3,4} <p> 1 - Original.<p> 2 - Square.<p> 3 - Landscape.<p> 4 - Portrait
	 * 	 */
	public void gallerySetPhotoOrientation(int n) {
		List<WebElement> List = driver.findElements(galleryDialogPhotoOrientationsList);
		waitForElementByElement(List.get(n-1));
		clickAndWait(List.get(n-1));
		PageObjectLogging.log("GallerySetPhotoOrientation", "Set photo orientation option number "+n, true, driver);
				
	}

	/**
	 * Set Object position to the wanted one
	 *  
	 * @author Michal Nowierski
	 * @param Object {Gallery, Slideshow} 
	 * @param WantedPosition = {Left, Center, Right} !CASE SENSITIVITY!
	 * 	 * 	 */
	public void gallerySetPositionGallery(String WantedPosition) {
				
		Select select = new Select(driver.findElement(By.cssSelector("select[id='WikiaPhotoGalleryEditorGalleryPosition']")));
		select.selectByVisibleText(WantedPosition);
		// below code will make sure that proper position is selected
		String category_name = select.getAllSelectedOptions().get(0).getText();
		while (!category_name.equalsIgnoreCase(WantedPosition)) {
			select.selectByVisibleText(WantedPosition);
			category_name = select.getAllSelectedOptions().get(0).getText();
		
	}
		PageObjectLogging.log("GallerySetPosition", "Set gallery position to "+WantedPosition, true, driver);
		}
	
	public void gallerySetPositionSlideshow(String WantedPosition) {
		
		Select select = new Select(driver.findElement(By.cssSelector("select[id='WikiaPhotoGalleryEditorSlideshowAlign']")));
		select.selectByVisibleText(WantedPosition);
		// below code will make sure that proper position is selected
		String category_name = select.getAllSelectedOptions().get(0).getText();
		while (!category_name.equalsIgnoreCase(WantedPosition)) {
			select.selectByVisibleText(WantedPosition);
			category_name = select.getAllSelectedOptions().get(0).getText();
		
	}
		PageObjectLogging.log("GallerySetPosition", "Set slideshow position to "+WantedPosition, true, driver);
		}

	/**
	 * Set photo orientation option number n
	 *  
	 * @author Michal Nowierski
	 * @param n = {1, 2} <p> 1 - Horizontaal.<p> 2 - Vertical
	 * 	 */
	public void gallerySetSliderPosition(int n) {
		List<WebElement> List = driver.findElements(galleryDialogSlideshowOrientationsList);
		waitForElementByElement(List.get(n-1));
		clickAndWait(List.get(n-1));
		PageObjectLogging.log("GallerySetSliderPosition", "Set photo orientation option number "+n, true, driver);
		
		
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

	

	

	

	

	

	
	






}
