package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

public class WikiArticlePageObject extends WikiBasePageObject {

	protected String articlename;

	@FindBy(css = "div.WikiaPageHeaderDiffHistory")
	private WebElement historyHeadLine;
	@FindBy(css = "#mw-content-text img.thumbimage")
	private WebElement thumbnailImageArticle;
	@FindBy(css = "a[data-canonical='random']")
	private WebElement randomPageButton;
	@FindBy(css = ".sprite.search")
	private WebElement searchButton;

	private By ImageOnWikiaArticle = By.cssSelector("#WikiaArticle figure a img");
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
		PageObjectLogging.log("openRandomArticle", "random page button clicked", true, driver);
		return new WikiArticlePageObject(driver);
	}

	public void verifyArticleText(String content) {
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
		PageObjectLogging.log("edit", "Edit article", true);
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

	public WikiHistoryPageObject openHistoryPage() {
		getUrl(driver.getCurrentUrl() + "?action=history");
		waitForElementByElement(historyHeadLine);
		return new WikiHistoryPageObject(driver);
	}
}
