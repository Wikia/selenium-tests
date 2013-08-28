package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.LightboxPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

public class WikiArticlePageObject extends WikiBasePageObject {

	protected String articlename;

	@FindBy(css="div.WikiaPageHeaderDiffHistory")
	private WebElement historyHeadLine;
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
	@FindBy(css="input[id*='article-comm-reply']")
	private WebElement submitReplyButton;
	@FindBy(css="table.article-table")
	private WebElement tableOnWikiaArticle;
	@FindBy(css="textarea#article-comm")
	private WebElement commentAreaDisabled;
	@FindBy(css=".article-comm-reply")
	private WebElement replyCommentButton;
	@FindBy(css="a[title='View photo details']")
	private WebElement videoDetailsButton;
	@FindBy(css="#mw-content-text img.thumbimage")
	private WebElement thumbnailImageArticle;
	@FindBy(css="#mw-content-text")
	private WebElement articleContent;
	@FindBy(css="#VideoEmbedUrlSubmit")
	private WebElement VideoModalAddButton;
	@FindBy(css="#WikiaRail .addVideo")
    private WebElement addVideoWikiaRail;
	@FindBy(css=".wikia-photogallery-add")
	private WebElement addPhotoToGalleryButton;
	@FindBy(css=".wikia-slideshow-addimage")
	private WebElement addPhotoToSlideShowButton;
	@FindBy(css = "a[data-canonical='random']")
	private WebElement randomPageButton;
	@FindBy(css = ".sprite.search")
	private WebElement searchButton;
	@FindBy(css=".button.addVideo")
	protected WebElement rVAddVideo;

	private By ImageOnWikiaArticle = By.cssSelector("#WikiaArticle figure a img");
	private By VideoOnWikiaArticle = By.cssSelector("#WikiaArticle img.sprite.play");
	private By articleContentBy = By.cssSelector("#mw-content-text");
	protected By rvFirstVideo = By.cssSelector(
			".RVBody .item:nth-child(1) .lightbox[data-video-name]"
			);
	private String pageName;

	public WikiArticlePageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public WikiArticlePageObject(WebDriver driver, String pageName) {
		super(driver);
		this.pageName = pageName;
	}

	public String getPageName(){
		return this.pageName;
	}

	public WikiArticleEditMode createNewArticle(String pageName,
												int layoutNumber) {
		getUrl(Global.DOMAIN + "index.php?title=" + pageName
				+ "&action=edit&useFormat=" + layoutNumber);
		String pageNameEnc = pageName.replace("_", " ");
		waitForElementByElement(driver.findElement(By.cssSelector("a[title='"
				+ pageNameEnc + "']")));
		return new WikiArticleEditMode(driver);
	}

	public WikiArticleEditMode createNewArticle(String wikiURL,
			WikiArticlePageObject article) {
		String pageName = article.getPageName();
		getUrl(urlBuilder.appendQueryStringToURL(wikiURL + URLsContent.wikiDir
				+ pageName, URLsContent.actionEditParameter));
		String pageNameEnc = pageName.replace("_", " ");
		waitForElementByElement(driver.findElement(By.cssSelector("a[title='"
				+ pageNameEnc + "']")));

		return new WikiArticleEditMode(driver);
	}

	public WikiArticleEditMode createNewTemplate(String wikiURL, String templateName, String templateContent ) {
		WikiArticlePageObject templateArticle = new WikiArticlePageObject(driver, URLsContent.templateNs + ":" + templateName );
		WikiArticleEditMode edit = templateArticle.createNewArticle(wikiURL, templateArticle );
		edit.typeInTemplateContent( templateContent );
		edit.clickOnPublish();
		this.waitForElementByCss("#WikiaArticle");

		PageObjectLogging.log("createNewTemplate", "new template created: " + templateName, true);

		return edit;
	}

	public WikiArticleEditMode createNewDefaultArticle(){
		this.pageName = PageContent.articleNamePrefix+getTimeStamp();
		return createNewArticle(this.pageName, 1);
	}

	public WikiArticlePageObject openRandomArticle() {
		scrollAndClick(randomPageButton);
		waitForElementByElement(searchButton);
		PageObjectLogging.log("openRandomArticle",
				"random page button clicked", true, driver);
		return new WikiArticlePageObject(driver);
	}

	public void triggerCommentArea()
	{
		waitForElementByElement(commentHolder);
		scrollToElement(commentHolder);
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

	public void clickSubmitButton()
	{
		executeScript("document.querySelectorAll('#article-comm-submit')[0].click()");
		PageObjectLogging.log("clickSubmitButton", "submit article button clicked", true, driver);
	}

	public void verifyCommentVideo(String videoName){
		waitForElementByCss(".speech-bubble-message img.Wikia-video-thumb[data-video-name*='" + videoName + "']");
		PageObjectLogging.log("verifyCommentVideo", "video is visible in comments section", true, driver);
	}

	public void verifyPageTitle(String title)
	{
		title = title.replace("_", " ");
		waitForElementByXPath("//h1[contains(text(), '" + title + "')]");
		PageObjectLogging.log("verifyPageTitle", "page title is verified", true);
	}

	public void verifyArticleText(String content)
	{
		waitForElementByBy(articleContentBy);
		waitForTextToBePresentInElementByBy(articleContentBy, content);
		PageObjectLogging.log("verifyArticleText", "article text is verified", true);
	}

	/**
	 * Click Edit button on a wiki article
	 *
	 * @author Michal Nowierski
	 */
	public WikiArticleEditMode edit() {
            waitForElementByElement(editButton);
            scrollAndClick(editButton);
            PageObjectLogging.log(
                "edit",
                "Edit article",
                true
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
	 * Verify that the Video appears on the page
	 *
	 * @author Michal Nowierski
	 * 	 */
	public void verifyVideoOnThePage() {
		waitForElementByBy(VideoOnWikiaArticle);
		PageObjectLogging.log("VerifyTheVideoOnThePage", "Verify that the Video appears on the page", true, driver);
	}

	public void verifyGalleryPosion(String position) {
		waitForElementByCss("div.wikia-gallery-position-"+position);
		PageObjectLogging.log("verifyGalleryPosion", "Gallery position verified: "+position, true, driver);
	}

	public WikiHistoryPageObject openHistoryPage()
	{
		getUrl(driver.getCurrentUrl() + "?action=history");
		waitForElementByElement(historyHeadLine);
		return new WikiHistoryPageObject(driver);
	}

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
		unfollowArticleByApi(name);
		getUrl(Global.DOMAIN + "index.php?title=" + name + "&action=watch");
		driver.findElement(By.cssSelector("[value=OK]")).click();
		waitForElementByElement(followedButton);
		PageObjectLogging.log("followRandomArticle", "random article followed", true);
		return name;
	}

	public void unfollowArticleByApi(String name){
		getUrl(Global.DOMAIN + "index.php?title=" + name + "&action=unwatch");
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
		waitForElementByElement(thumbnailImageArticle);
		thumbnailImageArticle.click();
		PageObjectLogging.log("clickThumbnailImage", "Thumbnail image is clicked", true);
		return new LightboxPageObject(driver);
	}

	public void clickAddVideoFromRail() {
		waitForElementByElement(addVideoWikiaRail);
		scrollAndClick(addVideoWikiaRail);
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


	public VetAddVideoComponentObject clickOnAddVideoRVModule() {
		waitForElementByElement(rVAddVideo);
		scrollAndClick(rVAddVideo);
		return new VetAddVideoComponentObject(driver);
	}

	public void verifyVideoAddedToRVModule(String videoName) {
		waitForTextToBePresentInElementByBy(rvFirstVideo, videoName);
	}

}
