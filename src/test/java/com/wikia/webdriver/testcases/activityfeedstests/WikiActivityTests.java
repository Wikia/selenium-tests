package com.wikia.webdriver.testcases.activityfeedstests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.UserProfilePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCreatePagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWikiActivityPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog.BlogPageObject;

import org.testng.annotations.Test;

/**
 * @author Michał 'justnpT' Nowierski
 */
public class WikiActivityTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  /**
   * https://wikia-inc.atlassian.net/browse/DAR-1617
   */
  @Test(groups = {"WikiActivity", "WikiActivity_001", "darwin"})
  public void WikiActivityTests_001_newEditionIsRecordedOnAvtivityModule() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    String articleContent = PageContent.ARTICLE_TEXT + base.getTimeStamp(); //timeStamp required
    ArticlePageObject article = base.openRandomArticle(wikiURL);
    String articleName = article.getArticleName();
    VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
    visualEditMode.addContent(articleContent);
    visualEditMode.submitArticle();
    article.verifyContent(articleContent);
    SpecialWikiActivityPageObject wikiActivity = article.openSpecialWikiActivity(wikiURL);
    wikiActivity.verifyRecentEdition(articleName, credentials.userName);
  }

  /**
   * https://wikia-inc.atlassian.net/browse/DAR-1617
   */
  @Test(groups = {"WikiActivity", "WikiActivity_002", "darwin"})
  public void WikiActivityTests_002_newPageCretionIsRecordedOnAvtivityModule() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    SpecialCreatePagePageObject specialCreatePage = base.openSpecialCreatePage(wikiURL);
    String articleContent = PageContent.ARTICLE_TEXT;
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + specialCreatePage.getTimeStamp();
    VisualEditModePageObject visualEditMode = specialCreatePage.populateTitleField(articleTitle);
    visualEditMode.addContent(articleContent);
    ArticlePageObject article = visualEditMode.submitArticle();
    String articleName = article.getArticleName();
    article.verifyContent(articleContent);
    article.verifyArticleTitle(articleTitle);
    SpecialWikiActivityPageObject wikiActivity = article.openSpecialWikiActivity(wikiURL);
    wikiActivity.verifyRecentNewPage(articleName, credentials.userName);
  }

  /**
   * https://wikia-inc.atlassian.net/browse/DAR-1617
   */
  @Test(groups = {"WikiActivity", "WikiActivity_003", "darwin"})
  public void WikiActivityTests_003_newBlogCreationIsRecordedOnAvtivityModule() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    String blogTitle = PageContent.BLOG_POST_NAME_PREFIX + base.getTimeStamp();
    String blogContent = PageContent.BLOG_CONTENT + base.getTimeStamp();
    UserProfilePageObject userProfile = base.openProfilePage(credentials.userName, wikiURL);
    userProfile.clickOnBlogTab();
    SpecialCreatePagePageObject createBlogPage = userProfile.clickOnCreateBlogPost();
    VisualEditModePageObject visualEditMode = createBlogPage.populateTitleField(blogTitle);
    visualEditMode.addContent(blogContent);
    BlogPageObject blogPage = visualEditMode.submitBlog();
    blogPage.verifyBlogTitle(blogTitle);
    blogPage.verifyContent(blogContent);
    SpecialWikiActivityPageObject wikiActivity = blogPage.openSpecialWikiActivity(wikiURL);
    wikiActivity.verifyRecentNewBlogPage(blogContent, blogTitle, credentials.userName);
  }

  /**
   * https://wikia-inc.atlassian.net/browse/DAR-1617
   */
  @Test(groups = {"WikiActivity", "WikiActivity_004", "darwin"})
  public void WikiActivityTests_004_newCategorizationIsRecordedOnAvtivityModule() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    ArticlePageObject article = base.openRandomArticle(wikiURL);
    String articleName = article.getArticleName();
    String categoryName = PageContent.CATEGORY_NAME_PREFIX + article.getTimeStamp();
    article.addCategory(categoryName);
    article.submitCategory();
    article.verifyCategoryPresent(categoryName);
    SpecialWikiActivityPageObject wikiActivity = article.openSpecialWikiActivity(wikiURL);
    wikiActivity.verifyRecentNewCategorization(articleName, credentials.userName);
  }
}
