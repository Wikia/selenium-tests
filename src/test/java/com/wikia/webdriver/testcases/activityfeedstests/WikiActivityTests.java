package com.wikia.webdriver.testcases.activityfeedstests;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.UserProfilePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCreatePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWikiActivityPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog.BlogPageObject;

/**
 * @author Michał 'justnpT' Nowierski
 */
public class WikiActivityTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  /**
   * https://wikia-inc.atlassian.net/browse/DAR-1617
   */
  @Test(groups = {"WikiActivity", "WikiActivity_001", "darwin"})
  @Execute(asUser = User.USER)
  public void WikiActivityTests_001_newEditionIsRecordedOnActivityModule() {
    String articleContent = PageContent.ARTICLE_TEXT + DateTime.now().getMillis();
    ArticlePageObject article =
        new ArticlePageObject(driver).open("NewEditionIsRecordedOnActivityModule");
    String articleName = article.getArticleName();
    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.addContent(articleContent);
    visualEditMode.submitArticle();
    article.verifyContent(articleContent);
    SpecialWikiActivityPageObject wikiActivity = article.openSpecialWikiActivity();
    wikiActivity.verifyRecentEdition(articleName, credentials.userName);
  }

  /**
   * https://wikia-inc.atlassian.net/browse/DAR-1617
   */
  @Test(groups = {"WikiActivity", "WikiActivity_002", "darwin"})
  @Execute(asUser = User.USER)
  public void WikiActivityTests_002_newPageCreationIsRecordedOnActivityModule() {
    SpecialCreatePage specialCreatePage = new SpecialCreatePage(driver).open();
    String articleContent = PageContent.ARTICLE_TEXT;
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    VisualEditModePageObject visualEditMode = specialCreatePage.populateTitleField(articleTitle);
    visualEditMode.addContent(articleContent);
    ArticlePageObject article = visualEditMode.submitArticle();
    String articleName = article.getArticleName();
    article.verifyContent(articleContent);
    article.verifyArticleTitle(articleTitle);
    SpecialWikiActivityPageObject wikiActivity = article.openSpecialWikiActivity();
    wikiActivity.verifyRecentNewPage(articleName, credentials.userName);
  }

  /**
   * https://wikia-inc.atlassian.net/browse/DAR-1617
   */
  @Test(groups = {"WikiActivity", "WikiActivity_003", "darwin"})
  @Execute(asUser = User.USER)
  public void WikiActivityTests_003_newBlogCreationIsRecordedOnActivityModule() {
    String blogTitle = PageContent.BLOG_POST_NAME_PREFIX + DateTime.now().getMillis();
    String blogContent = PageContent.BLOG_CONTENT + DateTime.now().getMillis();
    UserProfilePageObject userProfile =
        new WikiBasePageObject(driver).openProfilePage(credentials.userName, wikiURL);
    userProfile.clickOnBlogTab();
    SpecialCreatePage createBlogPage = userProfile.clickOnCreateBlogPost();
    VisualEditModePageObject visualEditMode = createBlogPage.populateTitleField(blogTitle);
    visualEditMode.addContent(blogContent);
    BlogPageObject blogPage = visualEditMode.submitBlog();
    blogPage.verifyBlogTitle(blogTitle);
    blogPage.verifyContent(blogContent);
    SpecialWikiActivityPageObject wikiActivity = blogPage.openSpecialWikiActivity();
    wikiActivity.verifyRecentNewBlogPage(blogContent, blogTitle, credentials.userName);
  }

  /**
   * https://wikia-inc.atlassian.net/browse/DAR-1617
   */
  @RelatedIssue(issueID = "QAART-673",
      comment = "Test will fail if article does not exist therefore test manually.")
  @Test(groups = {"WikiActivity", "WikiActivity_004", "darwin"})
  @Execute(asUser = User.USER)
  public void WikiActivityTests_004_newCategorizationIsRecordedOnActivityModule() {
    ArticlePageObject article =
        new ArticlePageObject(driver).open("NewCategorizationIsRecordedOnActivityModule");
    String articleName = article.getArticleName();
    String categoryName = PageContent.CATEGORY_NAME_PREFIX + article.getTimeStamp();
    article.addCategory(categoryName);
    article.submitCategory();
    article.verifyCategoryPresent(categoryName);
    SpecialWikiActivityPageObject wikiActivity = article.openSpecialWikiActivity();
    wikiActivity.verifyRecentNewCategorization(articleName, credentials.userName);
  }
}
