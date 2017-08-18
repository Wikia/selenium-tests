package com.wikia.webdriver.testcases.activityfeedstests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.Activity;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.diffpage.DiffPagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCreatePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWikiActivityPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog.BlogPage;
import org.joda.time.DateTime;
import org.testng.annotations.Test;


@Test(groups = "activityFeeds-wikiActivity")
@Execute(onWikia = "wikiActivities", asUser = User.WIKIACTIVITY_USER)
public class WikiActivityTests extends NewTestTemplate {

  private User testUser = User.WIKIACTIVITY_USER;

  public void articleEditionIsRecordedInWikiActivity() {
    new ArticleContent(testUser).push(PageContent.LOREM_IPSUM_SHORT);

    ArticlePageObject article = new ArticlePageObject().open();
    String articleName = article.getArticleName();

    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.addContent(PageContent.ARTICLE_TEXT_EDIT);
    visualEditMode.submitArticle();

    Assertion.assertTrue(new SpecialWikiActivityPageObject()
      .open()
      .isArticleEditionActivityDisplayed(articleName, testUser.getUserName()),
      String.format("Activity for edited article with title %s was not found", articleName));
  }

  public void newPageCreationIsRecordedInWikiActivity() {
    String articleName = new ArticleContent(testUser).createUniqueArticle();

    Assertion.assertTrue(new SpecialWikiActivityPageObject()
      .open()
      .isNewArticleActivityDisplayed(articleName, testUser.getUserName()),
      String.format("Activity for new article with title %s was not found", articleName));
  }

  public void newBlogCreationIsRecordedInWikiActivity() {
    String blogTitle = PageContent.BLOG_POST_NAME_PREFIX + DateTime.now().getMillis();
    String blogContent = PageContent.BLOG_CONTENT + DateTime.now().getMillis();
    UserProfilePage userProfile =
        new WikiBasePageObject().openProfilePage(testUser.getUserName(), wikiURL);
    userProfile.clickOnBlogTab();
    SpecialCreatePage createBlogPage = userProfile.clickOnCreateBlogPost();
    VisualEditModePageObject visualEditMode = createBlogPage.populateTitleField(blogTitle);
    visualEditMode.addContent(blogContent);
    BlogPage blogPage = visualEditMode.submitBlog();

    Assertion.assertEquals(blogPage.getBlogTitle(), blogTitle);

    Assertion.assertTrue(new SpecialWikiActivityPageObject()
      .open()
      .isNewBlogPostActivityDisplayed(blogTitle, testUser.getUserName(), blogContent),
      String.format("Activity for new blog post with title %s by user %s was not found", blogTitle, testUser.getUserName()));
  }

  public void newCategorizationIsRecordedInWikiActivity() {
    new ArticleContent(testUser).push(PageContent.LOREM_IPSUM_SHORT);

    ArticlePageObject article = new ArticlePageObject().open();
    String articleName = article.getArticleName();
    String categoryName = String.format("%s %s", PageContent.CATEGORY_NAME_PREFIX, BasePageObject.getTimeStamp());

    article.addCategory(categoryName);
    article.submitCategory();
    Assertion.assertTrue(article.isCategoryPresent(categoryName));

    Assertion.assertTrue(new SpecialWikiActivityPageObject()
      .open()
      .isCategorizationActivityDisplayed(articleName, testUser.getUserName()),
      String.format("Activity for new category for article with title %s was not found", articleName));
  }

  public void articleEditWithoutVisualChangeIsNotRecordedInWikiActivity() {
    new ArticleContent(testUser).push("content");
    ArticlePageObject article = new ArticlePageObject().open();
    String articleName = article.getArticleName();
    String articleContent = article.getContent();

    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.addContent(articleContent);
    visualEditMode.submitArticle();

    Assertion.assertFalse(new SpecialWikiActivityPageObject()
      .open()
      .isArticleEditionActivityDisplayed(articleName, testUser.getUserName()),
      String.format("Activity edit with no visual change for article with title %s was found", articleName));
  }

  public void clickingTitleRedirectsToArticle() {
    new ArticleContent(testUser).push("content");
    Activity articleActivity = new SpecialWikiActivityPageObject().open().getMostRecentArticleActivity();
    String title = articleActivity.getTitleLink().getText();
    articleActivity.getTitleLink().click();
    ArticlePageObject article = new ArticlePageObject();

    Assertion.assertEquals(article.getArticleTitle(), title,
      String.format("Link in activities list for article with title %s "
        + "redirected to article with title %s", title, article.getArticleTitle()));
  }

  public void clickingUsernameRedirectsToUserPage() {
    ArticleContent content = new ArticleContent(testUser);
    content.push("content");
    content.push("content_after_edition");

    Activity articleActivity = new SpecialWikiActivityPageObject().open().getMostRecentArticleActivity();
    String expectedUserName = articleActivity.getUserLink().getText();
    UserProfilePage userPage = articleActivity.clickOnUserLink();

    Assertion.assertEquals(userPage.getUserName(), expectedUserName,
      String.format("Link in activities list for username %s "
        + "redirected to user profile for %s", expectedUserName, userPage.getUserName()));
  }

  public void clickingIconNextToArticleRedirectsToDiff() {
    ArticleContent content = new ArticleContent(testUser);
    content.push("content");
    content.push("content_after_edition");

    DiffPagePageObject diffPage = new SpecialWikiActivityPageObject()
      .open()
      .getMostRecentEditActivity()
      .clickOnDiffLink();

    Assertion.assertTrue(diffPage.isDiffTableVisible(),
      "Diff table was not found on page");
  }
}
