package com.wikia.webdriver.testcases.articlecrudtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.dataprovider.ArticleDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCreatePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

@Test(groups = "ArticleCRUDAnon")
public class ArticleCRUDAnonTests extends NewTestTemplate {

  @Test(groups = {"ArticleCRUDAnon_001"})
  public void articleCRUDAnon_addBySpecialPage() {
    WikiBasePageObject base = new WikiBasePageObject();
    String articleContent = PageContent.ARTICLE_TEXT;
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    SpecialCreatePage specialCreatePage = new SpecialCreatePage().open();
    VisualEditModePageObject visualEditMode = specialCreatePage.populateTitleField(articleTitle);
    visualEditMode.addContent(articleContent);
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyContent(articleContent);
    article.verifyArticleTitle(articleTitle);
  }

  @Test(groups = {"ArticleCRUDAnon_002"})
  public void articleCRUDAnon_addByURL() {
    String articleContent = PageContent.ARTICLE_TEXT;
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    VisualEditModePageObject visualEditMode =
        new ArticlePageObject().navigateToArticleEditPage(wikiURL, articleTitle);
    visualEditMode.addContent(articleContent);
    ArticlePageObject article = visualEditMode.submitExpectingNotification().submitArticle();
    article.verifyContent(articleContent);
    article.verifyArticleTitle(articleTitle);
  }

  @Test(groups = {"ArticleCRUDAnon_003"})
  public void articleCRUDAnon_addDropdown() {
    String articleContent = PageContent.ARTICLE_TEXT;
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    ArticlePageObject article = new ArticlePageObject().open("AnonAddDropdown");
    VisualEditorPageObject ve = article.createArticleInVEUsingDropdown(articleTitle);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    article = ve.clickVEEditAndPublish(articleContent);
    article.verifyContent(articleContent);
    article.verifyArticleTitle(articleTitle);
  }

  @Test(dataProviderClass = ArticleDataProvider.class, dataProvider = "articleTitles",
      groups = {"ArticleCRUDAnon_004"})
  public void articleCRUDAnon_differentTitles(String articleTitle) {
    String articleContent = PageContent.ARTICLE_TEXT;
    String randomArticleTitle = articleTitle + DateTime.now().getMillis();
    VisualEditModePageObject visualEditMode =
        new ArticlePageObject().navigateToArticleEditPage(wikiURL, randomArticleTitle);
    visualEditMode.addContent(articleContent);
    ArticlePageObject article = visualEditMode.submitExpectingNotification().submitArticle();
    article.verifyContent(articleContent);
    article.verifyArticleTitle(randomArticleTitle);
  }

  @Test(groups = {"ArticleCRUDAnon_005"})
  public void articleCRUDAnon_editByURL() {
    String articleContent = PageContent.ARTICLE_TEXT;
    ArticlePageObject article = new ArticlePageObject().open("AnonEditByURL");
    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.addContent(articleContent);
    visualEditMode.submitExpectingNotification().submitArticle();
    article.verifyContent(articleContent);
  }

  @Test(groups = {"ArticleCRUDAnon_006"})
  public void articleCRUDAnon_editDropdown() {
    String articleContent = PageContent.ARTICLE_TEXT;
    ArticlePageObject article = new ArticlePageObject().open("AnonEditDropdown" + "?AbTest.ADD_NEW_PAGE=CONTROL1");
    VisualEditModePageObject visualEditMode = article.editArticleInCKUsingDropdown();
    visualEditMode.addContent(articleContent);
    visualEditMode.submitExpectingNotification().submitArticle();
    article.verifyContent(articleContent);
  }

  @Test(groups = {"ArticleCRUDAnon_007"})
  public void articleCRUDAnon_editArticleSecondTime() {
    String articleContent = PageContent.ARTICLE_TEXT;
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    VisualEditModePageObject visualEditMode =
        new ArticlePageObject().navigateToArticleEditPage(wikiURL, articleTitle);
    visualEditMode.addContent(articleContent);
    visualEditMode.submitExpectingNotification().submitArticle();
    String secondArticleTitle = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    visualEditMode.navigateToArticleEditPage(wikiURL, secondArticleTitle);
    visualEditMode.addContent(articleContent);
    visualEditMode.submitArticle();
  }
}
