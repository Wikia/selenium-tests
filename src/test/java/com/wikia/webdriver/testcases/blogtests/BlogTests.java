package com.wikia.webdriver.testcases.blogtests;

import java.util.List;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.dataprovider.ArticleDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.oasis.components.notifications.Notification;
import com.wikia.webdriver.elements.oasis.components.notifications.NotificationType;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.RenamePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCreatePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialRestorePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog.BlogPage;

@Test(groups = "BlogTests")
public class BlogTests extends NewTestTemplate {

  private static final String USER_BLOG_PATH_FORMAT = "User_blog:%s/%s";

  @Execute(asUser = User.BLOGS)
  public void UserCanAddBlogFromProfilePage() {
    String blogTitle = PageContent.BLOG_POST_NAME_PREFIX + DateTime.now().getMillis();
    String blogContent = PageContent.BLOG_CONTENT + DateTime.now().getMillis();

    UserProfilePage userProfile = new UserProfilePage().open(User.BLOGS.getUserName());
    userProfile.clickOnBlogTab();
    SpecialCreatePage createBlogPage = userProfile.clickOnCreateBlogPost();
    VisualEditModePageObject visualEditMode = createBlogPage.populateTitleField(blogTitle);
    visualEditMode.addContent(blogContent);
    BlogPage blogPage = visualEditMode.submitBlog();
    blogPage.getBlogTitle();
    blogPage.verifyContent(blogContent);
  }

  @Test(dataProviderClass = ArticleDataProvider.class, dataProvider = "articleTitles")
  @Execute(asUser = User.BLOGS)
  public void UserCanCreateBlogsWithSpecialCharacters (String blogTitle) {
    String blogContent = PageContent.BLOG_CONTENT + DateTime.now().getMillis();
    String randomBlogTitle = blogTitle + DateTime.now().getMillis();
    SpecialCreatePage createBlogPage = new SpecialCreatePage().open();
    VisualEditModePageObject visualEditMode = createBlogPage.populateTitleField(randomBlogTitle);
    visualEditMode.addContent(blogContent);
    BlogPage blogPage = visualEditMode.submitBlog();
    blogPage.getBlogTitle();
    blogPage.verifyContent(blogContent);
  }

  @Execute(asUser = User.BLOGS)
  public void UserCanEditBlogPost() {
    String blogTitle = PageContent.BLOG_POST_NAME_PREFIX + DateTime.now().getMillis();
    String blogContent = PageContent.BLOG_CONTENT + DateTime.now().getMillis();
    new ArticleContent(User.BLOGS).push(blogContent,
        String.format(USER_BLOG_PATH_FORMAT, User.BLOGS.getUserName(), blogTitle));

    BlogPage blogPage = new BlogPage().open(User.BLOGS.getUserName(), blogTitle);
    VisualEditModePageObject visualEditMode = blogPage.openCKModeWithMainEditButton();
    visualEditMode.addContent(blogContent);
    visualEditMode.submitArticle();
    blogPage.getBlogTitle();
    blogPage.verifyContent(blogContent);
  }

  @Execute(asUser = User.STAFF_FORUM)
  @RelatedIssue(issueID = "SUS-2259", comment = "Feature is broken until issue is resolved")
  public void StaffCanDeleteAndUndeleteUsersBlogPost() {
    String blogTitle = PageContent.BLOG_POST_NAME_PREFIX + DateTime.now().getMillis();
    String blogContent = PageContent.BLOG_CONTENT + DateTime.now().getMillis();
    new ArticleContent(User.BLOGS).push(blogContent,
        String.format(USER_BLOG_PATH_FORMAT, User.BLOGS.getUserName(), blogTitle));

    BlogPage blogPage = new BlogPage().open(User.BLOGS.getUserName(), blogTitle);
    DeletePageObject deletePage = blogPage.deleteUsingDropdown();
    deletePage.submitDeletion();

    List<Notification> confirmNotifications = blogPage.getNotifications(NotificationType.CONFIRM);
    Assertion.assertEquals(confirmNotifications.size(), 1,
        DeletePageObject.AssertionMessages.INVALID_NUMBER_OF_CONFIRMING_NOTIFICATIONS);
    SpecialRestorePageObject restore =
        blogPage.getNotifications(NotificationType.CONFIRM).stream().findFirst().get().undelete();

    restore.giveReason(blogPage.getTimeStamp());
    restore.restorePage();

    confirmNotifications = blogPage.getNotifications(NotificationType.CONFIRM);
    Assertion.assertEquals(confirmNotifications.size(), 1,
        SpecialRestorePageObject.AssertionMessages.INVALID_NUMBER_OF_CONFIRMING_NOTIFICATIONS);
    Assertion.assertTrue(confirmNotifications.stream().findFirst().get().isVisible(),
        SpecialRestorePageObject.AssertionMessages.BANNER_NOTIFICATION_NOT_VISIBLE);

    blogPage.getBlogTitle();
  }

  @Execute(asUser = User.STAFF_FORUM)
  public void StaffCanMoveUserBlogPosts() {
    String blogTitleMove =
        "Renamed - " + PageContent.BLOG_POST_NAME_PREFIX + DateTime.now().getMillis();
    String blogTitle = PageContent.BLOG_POST_NAME_PREFIX + DateTime.now().getMillis();
    String blogContent = PageContent.BLOG_CONTENT + DateTime.now().getMillis();
    new ArticleContent(User.BLOGS).push(blogContent,
        String.format(USER_BLOG_PATH_FORMAT, User.BLOGS.getUserName(), blogTitle));

    BlogPage blogPage = new BlogPage().open(User.BLOGS.getUserName(), blogTitle);
    RenamePageObject renamePage = blogPage.renameUsingDropdown();
    renamePage.rename(User.STAFF_FORUM.getUserName() + "/" + blogTitleMove, true);
    blogPage.getBlogTitle();

    List<Notification> confirmNotifications = blogPage.getNotifications(NotificationType.CONFIRM);
    Assertion.assertEquals(confirmNotifications.size(), 1,
        RenamePageObject.AssertionMessages.INVALID_NUMBER_OF_CONFIRMING_NOTIFICATIONS);
    Assertion.assertTrue(confirmNotifications.stream().findFirst().get().isVisible(),
        RenamePageObject.AssertionMessages.BANNER_NOTIFICATION_NOT_VISIBLE);
  }
}
