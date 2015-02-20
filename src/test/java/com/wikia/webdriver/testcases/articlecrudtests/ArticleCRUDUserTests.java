package com.wikia.webdriver.testcases.articlecrudtests;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.ExecuteAs;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.dataprovider.ArticleDataProvider;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCreatePagePageObject;

/**
 * @author: Bogna 'bognix' Knychała
 */
@Test(groups = {"ArticleCRUDUser"})
public class ArticleCRUDUserTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @Test(groups = {"ArticleCRUDUser_001"})
  public void ArticleCRUDUser_001_specialPage() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    SpecialCreatePagePageObject specialCreatePage = base.openSpecialCreatePage(wikiURL);
    String articleContent = PageContent.ARTICLE_TEXT;
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + specialCreatePage.getTimeStamp();
    VisualEditModePageObject visualEditMode = specialCreatePage.populateTitleField(articleTitle);
    visualEditMode.addContent(articleContent);
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyContent(articleContent);
    article.verifyArticleTitle(articleTitle);
  }

  @Test(groups = {"ArticleCRUDUser_002"})
  public void ArticleCRUDUser_002_addByURL() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    String articleContent = PageContent.ARTICLE_TEXT;
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditModePageObject visualEditMode =
        base.navigateToArticleEditPageCK(wikiURL, articleTitle);
    visualEditMode.addContent(articleContent);
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyContent(articleContent);
    article.verifyArticleTitle(articleTitle);
  }

  @Test(groups = {"ArticleCRUDUser_003", "Smoke1"})
  @ExecuteAs(user = User.USER)
  public void ArticleCRUDUser_003_addDropdown_QAART_354() {
    String articleContent = PageContent.ARTICLE_TEXT;
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
    VisualEditModePageObject visualEditMode = article.createArticleInCKUsingDropdown(articleTitle);
    visualEditMode.addContent(articleContent);
    visualEditMode.submitArticle();
    article.verifyContent(articleContent);
    article.verifyArticleTitle(articleTitle);
  }

  @Test(dataProviderClass = ArticleDataProvider.class, dataProvider = "articleTitles",
      groups = {"ArticleCRUDUser_004"})
  @ExecuteAs(user = User.USER)
  public void ArticleCRUDUser_004_differentTitles(String articleTitle) {
    String articleContent = PageContent.ARTICLE_TEXT;
    String randomArticleTitle = articleTitle + DateTime.now().getMillis();
    VisualEditModePageObject visualEditMode =
        new WikiBasePageObject(driver).navigateToArticleEditPageCK(wikiURL, randomArticleTitle);
    visualEditMode.addContent(articleContent);
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyContent(articleContent);
    article.verifyArticleTitle(randomArticleTitle);
  }

  @Test(groups = {"ArticleCRUDUser_005"})
  @ExecuteAs(user = User.USER)
  public void ArticleCRUDUser_005_editByURL() {
    String articleContent = PageContent.ARTICLE_TEXT;
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
    VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
    visualEditMode.addContent(articleContent);
    visualEditMode.submitArticle();
    article.verifyContent(articleContent);
  }

  @Test(groups = {"ArticleCRUDUser_006"})
  @ExecuteAs(user = User.USER)
  public void ArticleCRUDUser_006_editDropdown() {
    String articleContent = PageContent.ARTICLE_TEXT;
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
    VisualEditModePageObject visualEditMode = article.editArticleInRTEUsingDropdown();
    visualEditMode.addContent(articleContent);
    visualEditMode.submitArticle();
    article.verifyContent(articleContent);
  }
}
