package com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Global;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.editmode.WikiArticleEditMode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WikiArticlePageObject extends WikiBasePageObject {

  protected String articlename;

  @FindBy(css = "div.WikiaPageHeaderDiffHistory")
  private WebElement historyHeadLine;
  @FindBy(css = "a[data-canonical='random']")
  private WebElement randomPageButton;
  @FindBy(css = ".sprite.search")
  private WebElement searchButton;

  private By imageOnWikiaArticle = By.cssSelector("#WikiaArticle figure a img");
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

  public String getPageName() {
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
    String articlePageName = article.getPageName();
    getUrl(urlBuilder.appendQueryStringToURL(wikiURL + URLsContent.WIKI_DIR
                                             + articlePageName, URLsContent.ACTION_EDIT));
    String pageNameEnc = articlePageName.replace("_", " ");
    waitForElementByElement(driver.findElement(By.cssSelector("a[title='"
                                                              + pageNameEnc + "']")));

    return new WikiArticleEditMode(driver);
  }

  public WikiArticleEditMode createNewTemplate(String wikiURL, String templateName,
                                               String templateContent) {
    WikiArticlePageObject
        templateArticle =
        new WikiArticlePageObject(driver, URLsContent.TEMPLATE_NAMESPACE + ":" + templateName);
    WikiArticleEditMode edit = templateArticle.createNewArticle(wikiURL, templateArticle);
    edit.typeInTemplateContent(templateContent);
    edit.clickOnPublish();
    this.waitForElementByCss("#WikiaArticle");

    PageObjectLogging.log("createNewTemplate", "new template created: " + templateName, true);

    return edit;
  }

  public WikiArticleEditMode createNewDefaultArticle() {
    this.pageName = PageContent.ARTICLE_NAME_PREFIX + getTimeStamp();
    return createNewArticle(this.pageName, 1);
  }

  public WikiArticlePageObject openRandomArticle() {
    scrollAndClick(randomPageButton);
    waitForElementByElement(searchButton);
    PageObjectLogging.log("openRandomArticle",
                          "random page button clicked", true, driver);
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
    waitForElementByBy(imageOnWikiaArticle);
    PageObjectLogging.log("VerifyTheImageOnThePage",
                          "Verify that the image appears on the page", true, driver);
  }

  public WikiHistoryPageObject openHistoryPage() {
    getUrl(driver.getCurrentUrl() + "?action=history");
    waitForElementByElement(historyHeadLine);
    return new WikiHistoryPageObject(driver);
  }
}
