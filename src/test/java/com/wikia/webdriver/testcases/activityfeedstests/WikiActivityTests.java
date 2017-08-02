package com.wikia.webdriver.testcases.activityfeedstests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
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

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = "WikiActivity_001")
  @Execute(asUser = User.STAFF)
  public void WikiActivityTests_001_newEditionIsRecordedOnActivityModule() {
    new ArticleContent().push(PageContent.LOREM_IPSUM_SHORT);
    ArticlePageObject article = new ArticlePageObject().open();
    String articleName = article.getArticleName();

    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.addContent(PageContent.ARTICLE_TEXT_EDIT);
    visualEditMode.submitArticle();

    Assertion.assertTrue(
            new SpecialWikiActivityPageObject(driver)
                    .open().doesLastNRecentActivitiesContain(5, articleName, User.STAFF.getUserName())
    );
  }

  @Test(groups = "WikiActivity_002")
  @Execute(asUser = User.STAFF)
  public void WikiActivityTests_002_newPageCreationIsRecordedOnActivityModule() {
    new ArticleContent().clear();
    new ArticleContent().push(PageContent.LOREM_IPSUM_SHORT);
    String articleName = new ArticlePageObject().open().getArticleName();

    Assertion.assertTrue(
            new SpecialWikiActivityPageObject(driver)
                    .open().doesLastNRecentActivitiesContain(5, articleName, User.STAFF.getUserName())
    );
  }

  @Test(groups = "WikiActivity_003")
  @Execute(asUser = User.USER)
  public void WikiActivityTests_003_newBlogCreationIsRecordedOnActivityModule() {
    String blogTitle = PageContent.BLOG_POST_NAME_PREFIX + DateTime.now().getMillis();
    String blogContent = PageContent.BLOG_CONTENT + DateTime.now().getMillis();
    UserProfilePage userProfile =
        new WikiBasePageObject().openProfilePage(credentials.userName, wikiURL);
    userProfile.clickOnBlogTab();
    SpecialCreatePage createBlogPage = userProfile.clickOnCreateBlogPost();
    VisualEditModePageObject visualEditMode = createBlogPage.populateTitleField(blogTitle);
    visualEditMode.addContent(blogContent);
    BlogPage blogPage = visualEditMode.submitBlog();

    Assertion.assertEquals(blogPage.getBlogTitle(), blogTitle);

    Assertion.assertTrue(
            new SpecialWikiActivityPageObject(driver)
                    .open().doesLastNRecentBlogActivitiesContain(5, blogContent, blogTitle, User.USER.getUserName())
    );
  }

  @Test(groups = "WikiActivity_004")
  @Execute(asUser = User.STAFF)
  public void WikiActivityTests_004_newCategorizationIsRecordedOnActivityModule() {
    new ArticleContent().push(PageContent.LOREM_IPSUM_SHORT);
    ArticlePageObject article = new ArticlePageObject().open();
    String articleName = article.getArticleName();
    String categoryName = PageContent.CATEGORY_NAME_PREFIX + article.getTimeStamp();

    article.addCategory(categoryName);
    article.submitCategory();
    Assertion.assertTrue(article.isCategoryPresent(categoryName));

    Assertion.assertTrue(
            new SpecialWikiActivityPageObject(driver)
                    .open().doesLastNRecentActivitiesContain(5, articleName, User.STAFF.getUserName())
    );
  }

  @Test(groups = "WikiActivity_005")
  @Execute(asUser = User.USER)
  public void WikiActivityTests_005_newEditionWoVisualChangeNotRecordedOnActivityModule() {
    new ArticleContent().push("content");
    ArticlePageObject article = new ArticlePageObject().open();
    String articleName = article.getArticleName();
    String articleContent = article.getContent();

    VisualEditModePageObject visualEditMode = article.navigateToArticleEditPage();
    visualEditMode.addContent(articleContent);
    visualEditMode.submitArticle();

    Assertion.assertFalse(
            new SpecialWikiActivityPageObject(driver)
                    .open().doesLastNRecentEditionsContain(2, articleName, User.USER.getUserName())
    );
  }

  @Test(groups = "WikiActivity_006")
  @Execute(asUser = User.USER)
  public void WikiActivityTests_006_clickingTitleRedirectsToArticle() {
    new ArticleContent().push("content");
    SpecialWikiActivityPageObject activityPage = new SpecialWikiActivityPageObject(driver);
    activityPage.open();
    List<Activity> activityList = activityPage.getActivities(10);

    String title = activityList.stream().findFirst().get().getTitle();
    ArticlePageObject article = activityList.stream().findFirst().get().clickOnTitle();
    String articleName = article.getArticleName();

    Assertion.assertEquals(articleName, title);
  }

  @Test(groups = "WikiActivity_007")
  @Execute(asUser = User.USER)
  public void WikiActivityTests_007_clickingUserRedirectsToUserPage() {
    new ArticleContent().push("content");
    new ArticleContent().push("content_after_edition");

    SpecialWikiActivityPageObject activityPage = new SpecialWikiActivityPageObject(driver);
    activityPage.open();
    List<Activity> activityList = activityPage.getActivities(10);

    Activity chosenActivity = activityList.stream().findFirst().get();
    String expectedUserName = chosenActivity.getUser();
    UserProfilePage userPage = chosenActivity.clickOnUserLink();
    String currentUserName = userPage.getUserName();

    Assertion.assertEquals(currentUserName, expectedUserName);
  }

  @Test(groups = "WikiActivity_008")
  @Execute(asUser = User.USER)
  public void WikiActivityTests_008_clickingIconNextToArticleRedirectsToDiff() {
    new ArticleContent().push("content");
    new ArticleContent().push("content_after_edition");

    SpecialWikiActivityPageObject activityPage = new SpecialWikiActivityPageObject(driver);
    activityPage.open();
    List<Activity> activityList = activityPage.getActivities(10);
    EditActivity editActivity = (EditActivity) activityList
        .stream()
        .filter(activity -> activity instanceof EditActivity)
        .findFirst().get();

    DiffPagePageObject diffPage = editActivity.showChanges();

    Assertion.assertTrue(diffPage.isDiffTableVisible());
  }
}
