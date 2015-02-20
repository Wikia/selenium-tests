package com.wikia.webdriver.testcases.articlecrudtests;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.dataprovider.ArticleDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCreatePagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

/**
 * @author: Bogna 'bognix' Knychała
 */
@Test(groups = "ArticleCRUDAnon")
public class ArticleCRUDAnonTests extends NewTestTemplate {

  @Test(groups = {"ArticleCRUDAnon_001"})
  public void ArticleCRUDAnon_001_addBySpecialPage() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    String articleContent = PageContent.ARTICLE_TEXT;
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    SpecialCreatePagePageObject specialCreatePage = base.openSpecialCreatePage(wikiURL);
    VisualEditModePageObject visualEditMode = specialCreatePage.populateTitleField(articleTitle);
    visualEditMode.addContent(articleContent);
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyContent(articleContent);
    article.verifyArticleTitle(articleTitle);
  }

  @Test(groups = {"ArticleCRUDAnon_002"})
  public void ArticleCRUDAnon_002_addByURL() {
    String articleContent = PageContent.ARTICLE_TEXT;
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    VisualEditModePageObject visualEditMode =
        new ArticlePageObject(driver).navigateToArticleEditPageCK(wikiURL, articleTitle);
    visualEditMode.addContent(articleContent);
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyContent(articleContent);
    article.verifyArticleTitle(articleTitle);
  }

  @Test(groups = {"ArticleCRUDAnon_003"})
  public void ArticleCRUDAnon_003_addDropdown() {
    String articleContent = PageContent.ARTICLE_TEXT;
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
    VisualEditorPageObject ve = article.createArticleInVEUsingDropdown(articleTitle);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    article = ve.clickVEEditAndPublish(articleContent);
    article.verifyContent(articleContent);
    article.verifyArticleTitle(articleTitle);
  }

  @Test(dataProviderClass = ArticleDataProvider.class, dataProvider = "articleTitles",
      groups = {"ArticleCRUDAnon_004"})
  public void ArticleCRUDAnon_004_differentTitles(String articleTitle) {
    String articleContent = PageContent.ARTICLE_TEXT;
    String randomArticleTitle = articleTitle + DateTime.now().getMillis();
    VisualEditModePageObject visualEditMode =
        new ArticlePageObject(driver).navigateToArticleEditPageCK(wikiURL, randomArticleTitle);
    visualEditMode.addContent(articleContent);
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyContent(articleContent);
    article.verifyArticleTitle(randomArticleTitle);
  }

  @Test(groups = {"ArticleCRUDAnon_005"})
  public void ArticleCRUDAnon_005_editByURL() {
    String articleContent = PageContent.ARTICLE_TEXT;
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
    VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
    visualEditMode.addContent(articleContent);
    visualEditMode.submitArticle();
    article.verifyContent(articleContent);
  }

  @Test(groups = {"ArticleCRUDAnon_006"})
  public void ArticleCRUDAnon_006_editDropdown() {
    String articleContent = PageContent.ARTICLE_TEXT;
    ArticlePageObject article =
        new ArticlePageObject(driver).openArticleByName(wikiURL, PageContent.ARTICLE_NAME_PREFIX
            + DateTime.now().getMillis());
    VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    article = ve.clickVEEditAndPublish(articleContent);
    article.verifyContent(articleContent);
  }
}
