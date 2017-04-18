package com.wikia.webdriver.testcases.blogtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.dataprovider.ArticleDataProvider;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.oasis.components.notifications.Notification;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.RenamePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCreatePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialRestorePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog.BlogPageObject;

import org.testng.annotations.Test;

import java.util.List;

import static com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject.CONFIRM_NOTIFICATION;

public class BlogTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = {"BlogTests_001", "BlogTests", "Smoke1"})
  public void BlogTests_001_addFromProfile() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(credentials.userName4, credentials.password4, wikiURL);
    String blogTitle = PageContent.BLOG_POST_NAME_PREFIX + base.getTimeStamp();
    String blogContent = PageContent.BLOG_CONTENT + base.getTimeStamp();
    UserProfilePageObject userProfile = base.openProfilePage(credentials.userName4, wikiURL);
    userProfile.clickOnBlogTab();
    SpecialCreatePage createBlogPage = userProfile.clickOnCreateBlogPost();
    VisualEditModePageObject visualEditMode = createBlogPage.populateTitleField(blogTitle);
    visualEditMode.addContent(blogContent);
    BlogPageObject blogPage = visualEditMode.submitBlog();
    blogPage.verifyBlogTitle(blogTitle);
    blogPage.verifyContent(blogContent);
  }

  @Test(
      dataProviderClass = ArticleDataProvider.class,
      dataProvider = "articleTitles",
      groups = {"BlogTests_002", "BlogTests"})
  @Execute(asUser = User.USER)
  public void BlogTests_002_addByUrl(String blogTitle) {
    WikiBasePageObject base = new WikiBasePageObject();
    String blogContent = PageContent.BLOG_CONTENT + base.getTimeStamp();
    String randomBlogTitle = blogTitle + base.getTimeStamp();
    SpecialCreatePage createBlogPage = base.openSpecialCreateBlogPage(wikiURL);
    VisualEditModePageObject visualEditMode = createBlogPage.populateTitleField(randomBlogTitle);
    visualEditMode.addContent(blogContent);
    BlogPageObject blogPage = visualEditMode.submitBlog();
    blogPage.verifyBlogTitle(randomBlogTitle);
    blogPage.verifyContent(blogContent);
  }

  @Test(groups = {"BlogTests_003", "BlogTests"})
  @Execute(asUser = User.USER)
  public void BlogTests_003_editFromProfile() {
    WikiBasePageObject base = new WikiBasePageObject();
    String blogContent = PageContent.BLOG_CONTENT + base.getTimeStamp();
    UserProfilePageObject userProfile = base.openProfilePage(credentials.userName, wikiURL);
    userProfile.clickOnBlogTab();
    BlogPageObject blogPage = userProfile.openFirstPost();
    String blogTitle = blogPage.getBlogName();
    VisualEditModePageObject visualEditMode = blogPage.openCKModeWithMainEditButton();
    visualEditMode.addContent(blogContent);
    visualEditMode.submitArticle();
    blogPage.verifyBlogTitle(blogTitle);
    blogPage.verifyContent(blogContent);
  }

  @Test(groups = {"BlogTests_004", "BlogTests"})
  public void BlogTests_004_deleteUndelete() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    UserProfilePageObject userProfile = base.openProfilePage(credentials.userName4, wikiURL);
    userProfile.clickOnBlogTab();
    BlogPageObject blogPage = userProfile.openFirstPost();
    String blogTitle = blogPage.getBlogName();
    DeletePageObject deletePage = blogPage.deleteUsingDropdown();
    deletePage.submitDeletion();

    List<Notification> confirmNotifications = base.getNotifications(CONFIRM_NOTIFICATION);
    Assertion.assertEquals(confirmNotifications.size(),1,
            "Number of action confirming notifications is invalid");
    SpecialRestorePageObject restore = base.getNotifications(CONFIRM_NOTIFICATION)
            .stream().findFirst().get().clickUndeleteLinkInBannerNotification();

    restore.giveReason(blogPage.getTimeStamp());
    restore.restorePage();

    confirmNotifications = blogPage.getNotifications(CONFIRM_NOTIFICATION);
    Assertion.assertEquals(confirmNotifications.size(),1,
            "Number of banner notifications is invalid");
    Assertion.assertTrue(confirmNotifications.stream().findFirst().get().isVisible(),
                         "Banner notification message is not visible");

    blogPage.verifyBlogTitle(blogTitle);
  }

  @Test(groups = {"BlogTests_005", "BlogTests"})
  public void BlogTests_005_move() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    String blogTitleMove = PageContent.BLOG_POST_NAME_PREFIX + base.getTimeStamp();
    UserProfilePageObject userProfile = base.openProfilePage(credentials.userName, wikiURL);
    userProfile.clickOnBlogTab();
    BlogPageObject blogPage = userProfile.openFirstPost();
    RenamePageObject renamePage = blogPage.renameUsingDropdown();
    renamePage.rename(credentials.userNameStaff + "/" + blogTitleMove, true);
    blogPage.verifyBlogTitle(blogTitleMove);

    List<Notification> confirmNotifications = blogPage.getNotifications(CONFIRM_NOTIFICATION);
    Assertion.assertEquals(confirmNotifications.size(),1,
            "Number of action confirming notifications is invalid");
    Assertion.assertTrue(confirmNotifications.stream().findFirst().get().isVisible(),
                         "Banner notification message is not visible");
  }
}
