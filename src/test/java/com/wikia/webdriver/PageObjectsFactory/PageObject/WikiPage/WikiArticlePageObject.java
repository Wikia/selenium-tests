package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slideshow.SlideshowBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.LightboxPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

public class WikiArticlePageObject extends WikiBasePageObject {
	
	protected String articlename;
	
	@FindBy(css="div.WikiaPageHeaderDiffHistory")
	private WebElement historyHeadLine;
	@FindBy(css="section.RelatedVideosModule")
	private WebElement rVModule;
	@FindBy(css="#VideoEmbedUrl")
	private WebElement videoRVmodalInput;
	@FindBy(css="div[class='editarea']")
	private WebElement editCommentTrigger;
	@FindBy(css="body[id='bodyContent']")
	private WebElement editCommentArea;
	@FindBy(css="div.cke_contents iframe")
	private WebElement iframe;
	@FindBy(css="input[id*='article-comm-submit']")
	private WebElement submitCommentButton;
	@FindBy(css="#WikiaArticleFooter")
	private WebElement commentHolder;
	@FindBy(css="a.article-comm-delete")
	private WebElement deleteCommentButton;
	@FindBy(css="span.edit-link a")
	private WebElement editCommentButton;
	@FindBy(css="input[id*='article-comm-reply']")
	private WebElement submitReplyButton;
	@FindBy(css="table.article-table")
	private WebElement tableOnWikiaArticle;
	@FindBy(css="#CategorySelectAdd")
	private WebElement categories_AddCategoryButton;
	@FindBy(css="#CategorySelectInput")
	private WebElement categories_CategoryInputField;
	@FindBy(css="#CategorySelectSave")
	private WebElement categories_saveButton;
	@FindBy(css="textarea#article-comm")
	private WebElement commentAreaDisabled;
	@FindBy(css=".article-comm-reply")
	private WebElement replyCommentButton;
	@FindBy(css="a[title='View photo details']")
	private WebElement videoDetailsButton;
	@FindBy(css="img.thumbimage")
	private WebElement thumbnailImage;
	@FindBy(css="#VideoEmbedUrlSubmit")
	private WebElement VideoModalAddButton;
	@FindBy(css="#WikiaImagePlaceholderInner0")
	private WebElement videoAddPlaceholder;
	@FindBy(css="#WikiaRail .addVideo")
    private WebElement addVideoWikiaRail;
	@FindBy(css="#SPOTLIGHT_FOOTER_1 a img")
	private WebElement spotlightImage1;
	@FindBy(css="#SPOTLIGHT_FOOTER_2 a img")
	private WebElement spotlightImage2;
	@FindBy(css="#SPOTLIGHT_FOOTER_3 a img")
	private WebElement spotlightImage3;
	@FindBy(css="#SPOTLIGHT_FOOTER")
	private WebElement spotlightFooter;
	@FindBy(css=".wikia-photogallery-add")
	private WebElement addPhotoToGalleryButton;
	@FindBy(css=".wikia-slideshow-addimage")
	private WebElement addPhotoToSlideShowButton;

	private By categories_listOfCategories = By.cssSelector(".WikiaArticleCategories li a");
	private By ImageOnWikiaArticle = By.cssSelector("div.WikiaArticle figure a img");
	private By VideoOnWikiaArticle = By.cssSelector("div.WikiaArticle img.sprite.play");
	private By AddVideoRVButton = By.cssSelector("a.addVideo");
	private By RVvideoLoading = By.cssSelector("section.loading");
	private By galleryOnPublish = By.cssSelector("div[class*='gallery']");
	private By slideShowOnPublish = By.cssSelector("div.wikia-slideshow");
	private By videoOnPublish = By.cssSelector("figure a.image.video");
	

	public WikiArticlePageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public String getPageName(){
		return this.articlename;
	}
	
	public void triggerCommentArea()
	{
		waitForElementByElement(commentHolder);
		CommonFunctions.scrollToElement(commentHolder);
		waitForElementByElement(submitCommentButton);
		waitForElementByElement(commentAreaDisabled);
		int delay = 500;
		int sumDelay = 500;
		while (commentAreaDisabled.isDisplayed())
		{
			try {
				Thread.sleep(delay);
				jQueryFocus("textarea#article-comm");				
			}
			catch(WebDriverException e){
				PageObjectLogging.log("triggerCommentArea", "comment are visible after "+delay+" ms", true);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			sumDelay+=500;
			if (sumDelay > 3000)
			{
				break;
			}
		}

		waitForElementByElement(iframe);
		PageObjectLogging.log("triggerCommentArea", "comment area triggered", true, driver);
	}
	
	public void writeOnCommentArea(String comment)
	{
		driver.switchTo().frame(iframe);
		waitForElementByElement(editCommentArea);
		editCommentArea.clear();
		if (Global.BROWSER.equals("FF"))
		{
			((JavascriptExecutor) driver).executeScript("document.body.innerHTML='" + comment + "'");
		}
		else
		{			
			editCommentArea.sendKeys(comment);
		}
		driver.switchTo().defaultContent();
	}
	
	public void clickSubmitButton()
	{
		executeScript("document.querySelectorAll('#article-comm-submit')[0].click()");
		PageObjectLogging.log("clickSubmitButton", "submit article button clicked", true, driver);
	}
	
	public void clickSubmitButton(String userName)
	{		
		clickAndWait(driver.findElement(By.xpath("//a[contains(text(), '"+userName+"')]/../../..//input[@class='actionButton']")));//submit button taken by username which edited comment
		PageObjectLogging.log("clickSubmitButton", "submit article button clicked", true, driver);
	}
	
	public void verifyCommentText(String message, String userName)
	{
		waitForElementByXPath("//blockquote//p[contains(text(), '"+message+"')]");
		waitForElementByXPath("//div[@class='edited-by']//a[contains(text(), '"+userName+"')]");
		PageObjectLogging.log("verifyComment", "comment: "+message+" is visible", true, driver);
	}
	
	public void verifyCommentVideo(String videoName){
		waitForElementByCss(".speech-bubble-message img.Wikia-video-thumb[data-video-name*='"+videoName+"']");
		PageObjectLogging.log("verifyCommentVideo", "video is visible in comments section", true, driver);
	}
	
	private void clickReplyCommentButton(String comment)
	{
		CommonFunctions.scrollToElement(commentHolder);
		waitForElementByXPath("//p[contains(text(), '"+comment+"')]//..//..//button[contains(text(), 'Reply')]");
		jQueryClick(".article-comm-reply");
		waitForElementByElement(iframe);
		PageObjectLogging.log("clickReplyCommentButton", "reply comment button clicked", true);
	}
	
	
	private void writeReply(String reply)
	{
		waitForElementByElement(iframe);
		driver.switchTo().frame(iframe);
		editCommentArea.sendKeys(reply);
		driver.switchTo().defaultContent();
		waitForElementByElement(submitReplyButton);
		jQueryClick("input[id*=\"article-comm-reply\"]");
		waitForElementByXPath("//p[contains(text(), '"+reply+"')]");
		PageObjectLogging.log("writeReply", "reply comment written", true);
	}
	
	
	public void replyComment(String comment, String reply)
	{
		refreshPage();
		clickReplyCommentButton(comment);
		writeReply(reply);
		PageObjectLogging.log("reply comment", "reply comment written and checked", true, driver);
	}

	private void clickDeleteCommentButton()
	{
		executeScript("document.querySelectorAll('.article-comm-delete')[0].click()");
		PageObjectLogging.log("clickDeleteCommentButton", "delete comment button clicked", true, driver);
	}
	
	private void clickEditCommentButton()
	{
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		executeScript("document.querySelectorAll('.article-comm-edit')[0].click()");
		waitForElementByElement(iframe);
		PageObjectLogging.log("clickEditCommentButton", "edit comment button clicked", true, driver);
	}
	
	public void deleteComment(String comment)
	{
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,0)");
		clickDeleteCommentButton();
		clickCommentDeleteConfirmationButton();
		PageObjectLogging.log("deleteComment", "comment deleted", true, driver);
	}
	
	public void editComment(String comment)
	{
		refreshPage();
		CommonFunctions.scrollToElement(commentHolder);
		waitForElementByElement(replyCommentButton);
		clickEditCommentButton();
	}
	
	
	public void verifyPageTitle(String title)
	{
		title = title.replace("_", " ");
		waitForElementByXPath("//h1[contains(text(), '"+title+"')]");
		PageObjectLogging.log("verifyPageTitle", "page title is verified", true, driver);
	}
	
	public void verifyArticleText(String content)
	{
		waitForElementByXPath("//div[@id='mw-content-text']//*[contains(text(), '"+content+"')]");
		PageObjectLogging.log("verifyArticleText", "article text is verified", true, driver);
	}

	/**
	 * Click Edit button on a wiki article
	 *  
	 * @author Michal Nowierski
	 */
	public WikiArticleEditMode edit() {
            waitForElementByElement(editButton);
            clickAndWait(editButton);
            PageObjectLogging.log(
                "Edit",
                "Edit Article: " + articlename + ", on wiki: " + Domain,
                true, driver
            );
            return new WikiArticleEditMode(driver);
	}

	/**
	 * Verify that the image appears on the page
	 *  
	 * @author Michal Nowierski
	 */
	public void verifyImageOnThePage() {
		waitForElementByBy(ImageOnWikiaArticle);
		PageObjectLogging.log("VerifyTheImageOnThePage", "Verify that the image appears on the page", true, driver);
	}
	
	/**
	 * Verify that the image does not appear on the page
	 *  
	 * @author Michal Nowierski
	 */
	public void verifyImageNotOnThePage() {
		waitForElementNotVisibleByBy(ImageOnWikiaArticle);
		PageObjectLogging.log("VerifyTheImageNotOnThePage", "Verify that the image does not appear on the page", true, driver);	
	}
	
	public void verifyGalleryNotOnThePage() {
		waitForElementNotVisibleByBy(galleryOnPublish);
		PageObjectLogging.log("verifyTheGalleryNotOnThePage", "Verify that the gallery does not appear on the page", true, driver);	
	}
	
	public void verifySlideshowNotOnThePage() 
	{
		waitForElementNotVisibleByBy(slideShowOnPublish);
		PageObjectLogging.log("verifyTheSlideshowNotOnThePage", "Verify that the slideshow does not appear on the page", true, driver);			
	}
	
	public void verifyVideoNotOnThePage() {
		waitForElementNotVisibleByBy(videoOnPublish);
		PageObjectLogging.log("verifyTheVideoNotOnThePage", "Verify that the video does not appear on the page", true, driver);
	}

	/**
	 * Verify that the Object appears on the page
	 *  
	 * @author Michal Nowierski
	 * @param Object Object = {gallery, slideshow}
	 * 	 */
	public void verifyObjectOnThePage(String Object) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.WikiaArticle div[id*='"+Object+"']")));
		PageObjectLogging.log("VerifyTheObjetOnThePage", "Verify that the "+Object+" appears on the page", true, driver);
		
	}
	
	/**
	 * Verify that the Video appears on the page
	 *  
	 * @author Michal Nowierski
	 * 	 */
	public void verifyVideoOnThePage() {
		waitForElementByBy(VideoOnWikiaArticle);
		PageObjectLogging.log("VerifyTheVideoOnThePage", "Verify that the Video appears on the page", true, driver);
	}
	
	/**
	 * Verify that the RV Module Is Present
	 *  
	 * @author Michal Nowierski
	 * 	 */
	public void verifyRVModulePresence() {
		waitForElementByElement(rVModule);
		PageObjectLogging.log("VerifyRVModulePresence", "Verify that the RV Module Is Present", true, driver);
		
	}

	/**
	 * Click On 'Add a video' button on RV module
	 *  
	 * @author Michal Nowierski
	 * 	 */
	public VetAddVideoComponentObject clickOnAddVideoRVModule() {
		waitForElementByBy(AddVideoRVButton);
		CommonFunctions.scrollToElement(driver.findElement(AddVideoRVButton));
		waitForElementClickableByBy(AddVideoRVButton);
		clickAndWait(driver.findElement(AddVideoRVButton));
		PageObjectLogging.log("ClickOnAddVideoRVModule", "Click On 'Add a video' button on RV module", true, driver);
		return new VetAddVideoComponentObject(driver);
	}

	/**
	 * Type given URL into RV modal
	 *  
	 * @author Michal Nowierski
	 * @param videoURL URL of the video to be added
	 * 	 */
	public void typeInVideoURL(String videoURL) {
		waitForElementByElement(videoRVmodalInput);		
		videoRVmodalInput.clear();
		videoRVmodalInput.sendKeys(videoURL);
		PageObjectLogging.log("TypeInVideoURL", "Type given URL into RV modal", true, driver);
	}

	/**
	 * Click on Add button on RV modal
	 *  
	 * @author Michal Nowierski
	 * 	 */
	public void clickOnRVModalAddButton() {
		waitForElementByElement(VideoModalAddButton);
		waitForElementClickableByElement(VideoModalAddButton);
		clickAndWait((VideoModalAddButton));
		PageObjectLogging.log("ClickOnRVModalAddButton", "Click on Add button on RV modal", true, driver);
		
	}

	/**
	 * Wait for processing the added video to finish
	 *  
	 * @author Michal Nowierski
	 * 	 */
	public void waitForProcessingToFinish() {
		waitForElementNotVisibleByBy(RVvideoLoading);
		PageObjectLogging.log("WaitForProcessingToFinish", "Wait for processing the added video to finish", true, driver);
		
	}

	/**
	 * Verify that video given by its name has been added to RV module
	 *  
	 * @author Michal Nowierski
	 * @param videoURL2name The name of the video, or any fragment of the video name
	 * 	 */
	public void verifyVideoAddedToRVModule(String videoURL2name) {
		waitForElementByCss(".RVBody img[data-video-name*=\""+videoURL2name+"\"]");
		PageObjectLogging.log("VerifyVideoAddedToRVModule", "Verify that video given by its name has been added to RV module", true, driver);
		
	}

	public void verifyGalleryPosion(String position) {
		waitForElementByCss("div.wikia-gallery-position-"+position);
		PageObjectLogging.log("verifyGalleryPosion", "Gallery position verified: "+position, true, driver);
	}

	public void verifySlideshowPosition(String position) {
		if (position.equals("left")||position.equals("right"))
		{
			waitForElementByCss("div.wikia-slideshow.float"+position);
			PageObjectLogging.log("verifySlideshowPosion", "Slideshow position verified: "+position, true, driver);
		}
		else if (position.equals("center"))
		{
			waitForElementByCss("div.wikia-slideshow.slideshow-center");			
			PageObjectLogging.log("verifySlideshowPosion", "Slideshow position verified: "+position, true, driver);
		}
		
	}
/**
 * 
 * @param position available values (vertical, horizontal)
 */
	public void verifySliderThumbnailsPosition(String position) {
		waitForElementByCss(".wikiaPhotoGallery-slider-body div."+position);
		PageObjectLogging.log("verifySliderThumbnailsPosition", "Slider thumbnails position verified: "+position, true, driver);		
	}

	public WikiHistoryPageObject openHistoryPage()
	{
		getUrl(driver.getCurrentUrl() + "?action=history");
		waitForElementByElement(historyHeadLine);
		return new WikiHistoryPageObject(driver);
	}

	/**
	* Verify that the table appears on the page
	*
	*
	@author Michal Nowierski
	*/

	public void VerifyTheTableOnThePage() {
	waitForElementByElement(tableOnWikiaArticle);
	PageObjectLogging.log("VerifyTheTableOnThePage", "Verify that the table appears on the page", true, driver);
	}

	/**
	* Click on 'add Category' Button
	*
	@author Michal Nowierski
	*/
	public void categories_clickAddCategory() {
		waitForElementByElement(categories_AddCategoryButton);
		waitForElementClickableByElement(categories_AddCategoryButton);
		clickAndWait(categories_AddCategoryButton);
		PageObjectLogging.log("categories_clickAddCategory", "Click on 'add Category' Button", true, driver);
	}

	/**
	* type a category to field
	*
	@author Michal Nowierski
	*/
	public void categories_typeCategoryName(String categoryName) {
		waitForElementByElement(categories_CategoryInputField);
		categories_CategoryInputField.sendKeys(categoryName);
		executeScript("var e = jQuery.Event(\"keydown\"); e.which=13; $('#CategorySelectInput').trigger(e);");
//		categories_CategoryInputField.sendKeys(Keys.ENTER);
		PageObjectLogging.log("categories_clickAddCategory", "type "+categoryName+" to category input field", true, driver);
		
	}
	/**
	* click SaveButton
	*
	@author Michal Nowierski
	*/
	public void categories_clickOnSave() {
		waitForElementByElement(categories_saveButton);
		waitForElementClickableByElement(categories_saveButton);
		clickAndWait(categories_saveButton);
		PageObjectLogging.log("categories_clickOnSave", "Click on 'Save' Button", true, driver);
		
	}

	/**
	* click SaveButton
	*
	@author Michal Nowierski
	*/
	public void categories_verifyCategoryPresent(String categoryName) {
		List<WebElement> lista  = driver.findElements(categories_listOfCategories);
		Boolean result = false;
		// there might be more than one category on a random page. Thus - loop over all of them.
		for (WebElement webElement : lista) {
			waitForElementByElement(webElement);		
			if (webElement.getText().equalsIgnoreCase(categoryName)) {
				result = true;
			}
		}
		if (result) {
			PageObjectLogging.log("categories_verifyCategory", "category "+categoryName+" prsesnce succesfully verified", true, driver);			
		}
		else {
			PageObjectLogging.log("categories_verifyCategory", "category "+categoryName+" NOT present", false, driver);						
		}
		
	}

	public void categories_verifyCategoryRemoved(String categoryName) {
		List<WebElement> lista  = driver.findElements(categories_listOfCategories);
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
	/**
	* getArticleNameFromURL
	*
	@author Michal Nowierski
	*/
	public String getArticleNameFromURL() {
		//TODO: To Michal: use Regular Expression here, when its syntax is learned.
		String URL= driver.getCurrentUrl();
		int articlenameIndex = URL.indexOf("wiki/");
		String articleName = URL.substring(articlenameIndex+5);
		return articleName;
	}
	
	public String followRandomArticle(){
		openRandomArticle();
		String name = driver.findElement(By.cssSelector(".WikiaPageHeader h1")).getText();
		getUrl(Global.DOMAIN+"index.php?title="+name+"&action=watch");
		driver.findElement(By.cssSelector("[value=OK]")).click();
		waitForElementByElement(followedButton);
		PageObjectLogging.log("followRandomArticle", "random article followed", true);
		return name;
	}
	
	public void unfollowArticle(String name){
		getUrl(Global.DOMAIN+"index.php?title="+name+"&action=unwatch");
		driver.findElement(By.cssSelector("[value=OK]")).click();
		waitForElementByElement(unfollowedButton);
		PageObjectLogging.log("followRandomArticle", "random article followed", true);
	}
	
	public FileDetailsPageObject clickVideoDetailsButton() {
		waitForElementByElement(videoDetailsButton);
		videoDetailsButton.click();
		PageObjectLogging.log("clickVideoDetailsButton", "Video Details link is clicked", true);
		return new FileDetailsPageObject(driver);
	}

	public LightboxPageObject clickThumbnailImage() {
		waitForElementByElement(thumbnailImage);
		thumbnailImage.click();
		PageObjectLogging.log("clickThumbnailImage", "Thumbnail image is clicked", true, driver);
		return new LightboxPageObject(driver);
	}
	
	public VetAddVideoComponentObject clickAddVideoPlaceholder(){
		waitForElementByElement(videoAddPlaceholder);
		clickAndWait(videoAddPlaceholder);
		return new VetAddVideoComponentObject(driver);
	}

    public void clickAddVideoFromRail() {
        waitForElementByElement(addVideoWikiaRail);
        clickAndWait(addVideoWikiaRail);
        PageObjectLogging.log(
            "clickAndVideoOnWikiaRail",
            "Button add video on wikia rail is clicked",
            true, driver
        );
    }

    public void renameRandomArticle(String newName) {
        String oldName = getArticleNameFromURL();
        renameArticle(oldName, newName);
    }

	/**
	 *  @author Michal 'justnpT' Nowierski
	 */
	public void verifySpotlightsPresence() {
		CommonFunctions.scrollToElement(spotlightFooter);
		waitForElementByElement(spotlightImage1);
		waitForElementByElement(spotlightImage2);
		waitForElementByElement(spotlightImage3);		
		PageObjectLogging.log("verifySpotlightsPresence", "all 3 spotlights are present", true, driver);
	}
	
	public GalleryBuilderComponentObject clickAddPhotoToGallery(){
		clickAndWait(addPhotoToGalleryButton);
		return new GalleryBuilderComponentObject(driver);
	}
	public SlideshowBuilderComponentObject clickAddPhotoToSlideshow(){
		addPhotoToSlideShowButton.click();
		return new SlideshowBuilderComponentObject(driver);
	}
}