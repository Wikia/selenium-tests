package com.wikia.webdriver.testcases.activityfeedstests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.Activity;
import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.EditActivity;
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

import java.util.List;


@Test(groups = "activityFeeds-wikiActivity")
public class WikiActivityTests extends NewTestTemplate {

  @Execute(asUser = User.STAFF)
  public void articleEditionIsRecordedInWikiActivity() {
    new ArticleContent().push(PageContent.LOREM_IPSUM_SHORT);
    ArticlePageObject article = new ArticlePageObject().open();
    String articleName = article.getArticleName();

    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.addContent(PageContent.ARTICLE_TEXT_EDIT);
    visualEditMode.submitArticle();

    Assertion.assertTrue(new SpecialWikiActivityPageObject()
      .open()
      .isArticleEditionActivityDisplayed(articleName, User.STAFF.getUserName()));
  }

  @Execute(asUser = User.STAFF)
  public void newPageCreationIsRecordedInWikiActivity() {
    new ArticleContent().clear();
    new ArticleContent().push(PageContent.LOREM_IPSUM_SHORT);
    String articleName = new ArticlePageObject().open().getArticleName();

    Assertion.assertTrue(new SpecialWikiActivityPageObject()
      .open()
      .isNewArticleActivityDisplayed(articleName, User.STAFF.getUserName()));
  }

  @Execute(asUser = User.USER)
  public void newBlogCreationIsRecordedInWikiActivity() {
    String blogTitle = PageContent.BLOG_POST_NAME_PREFIX + DateTime.now().getMillis();
    String blogContent = PageContent.BLOG_CONTENT + DateTime.now().getMillis();
    UserProfilePage userProfile =
        new WikiBasePageObject().openProfilePage(User.USER.getUserName(), wikiURL);
    userProfile.clickOnBlogTab();
    SpecialCreatePage createBlogPage = userProfile.clickOnCreateBlogPost();
    VisualEditModePageObject visualEditMode = createBlogPage.populateTitleField(blogTitle);
    visualEditMode.addContent(blogContent);
    BlogPage blogPage = visualEditMode.submitBlog();

    Assertion.assertEquals(blogPage.getBlogTitle(), blogTitle);

    Assertion.assertTrue(new SpecialWikiActivityPageObject()
      .open()
      .isNewBlogPostActivityDisplayed(blogContent, blogTitle, User.USER.getUserName()));
  }

  @Execute(asUser = User.STAFF)
  public void newCategorizationIsRecordedInWikiActivity() {
    new ArticleContent().push(PageContent.LOREM_IPSUM_SHORT);
    ArticlePageObject article = new ArticlePageObject().open();
    String articleName = article.getArticleName();
    String categoryName = PageContent.CATEGORY_NAME_PREFIX + article.getTimeStamp();

    article.addCategory(categoryName);
    article.submitCategory();
    Assertion.assertTrue(article.isCategoryPresent(categoryName));

    Assertion.assertTrue(new SpecialWikiActivityPageObject()
      .open()
      .isCategorizationActivityDisplayed(articleName, User.STAFF.getUserName()));
  }

  @Execute(asUser = User.USER)
  public void articleEditWithoutVisualChangeIsNotRecordedInWikiActivity() {
    new ArticleContent().push("content");
    ArticlePageObject article = new ArticlePageObject().open();
    String articleName = article.getArticleName();
    String articleContent = article.getContent();

    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.addContent(articleContent);
    visualEditMode.submitArticle();

    Assertion.assertFalse(new SpecialWikiActivityPageObject()
      .open()
      .isArticleEditionActivityDisplayed(articleName, User.USER.getUserName()));
  }

  @Execute(asUser = User.USER)
  public void clickingTitleRedirectsToArticle() {
    new ArticleContent().push("content");
    Activity articleActivity = new SpecialWikiActivityPageObject().open().getMostRecentArticleActivity();
    String title = articleActivity.getTitleLink().getText();
    articleActivity.getTitleLink().click();
    ArticlePageObject article = new ArticlePageObject();

    Assertion.assertEquals(article.getArticleTitle(), title);
  }

  @Execute(asUser = User.USER)
  public void clickingUsernameRedirectsToUserPage() {
    new ArticleContent().push("content");
    new ArticleContent().push("content_after_edition");

    Activity articleActivity = new SpecialWikiActivityPageObject().open().getMostRecentArticleActivity();
    String expectedUserName = articleActivity.getUserLink().getText();
    UserProfilePage userPage = articleActivity.clickOnUserLink();

    Assertion.assertEquals(userPage.getUserName(), expectedUserName);
  }

  @Execute(asUser = User.USER)
  public void clickingIconNextToArticleRedirectsToDiff() {
    new ArticleContent().push("content");
    new ArticleContent().push("content_after_edition");

    DiffPagePageObject diffPage = new SpecialWikiActivityPageObject()
      .open()
      .getMostRecentEditActivity()
      .clickOnDiffLink();

    Assertion.assertTrue(diffPage.isDiffTableVisible());
  }
}
