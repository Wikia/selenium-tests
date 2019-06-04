package com.wikia.webdriver.testcases.desktop.commentstests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.BlogContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.desktop.components.notifications.Notification;
import com.wikia.webdriver.elements.communities.desktop.components.notifications.NotificationType;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog.BlogPage;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.util.List;

@Test(groups = "comments-blogComments")
public class BlogCommentsTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = "BlogComments_001")
  public void AnonCanCommentAReply() {
    String blogTitle = PageContent.BLOG_POST_NAME_PREFIX + DateTime.now().getMillis();
    String blogContent = PageContent.BLOG_CONTENT + DateTime.now().getMillis();
    new BlogContent(credentials.userName).push(blogContent, blogTitle);

    BlogPage blogPage = new BlogPage().open(credentials.userName, blogTitle);
    MiniEditorComponentObject editor = blogPage.triggerCommentArea();
    String comment = PageContent.COMMENT_TEXT + blogPage.getTimeStamp();
    editor.switchAndWrite(comment);
    blogPage.submitComment();
    blogPage.verifyCommentText(comment);
    blogPage.verifyCommentCreator(PageContent.WIKIA_CONTRIBUTOR);
    blogPage.triggerCommentReply();
    String commentReply = PageContent.COMMENT_TEXT + blogPage.getTimeStamp();
    editor.switchAndReplyComment(commentReply);
    blogPage.submitReplyComment();
    blogPage.verifyCommentReply(commentReply);
    blogPage.verifyReplyCreator(PageContent.WIKIA_CONTRIBUTOR);
  }

  @Test(groups = "BlogComments_002")
  @Execute(asUser = User.SUS_REGULAR_USER)
  public void UserCanCommentAReply() {
    String blogTitle = PageContent.BLOG_POST_NAME_PREFIX + DateTime.now().getMillis();
    String blogContent = PageContent.BLOG_CONTENT + DateTime.now().getMillis();
    new BlogContent(credentials.userName).push(blogContent, blogTitle);

    BlogPage blogPage = new BlogPage().open(credentials.userName, blogTitle);
    MiniEditorComponentObject editor = blogPage.triggerCommentArea();
    String comment = PageContent.COMMENT_TEXT + blogPage.getTimeStamp();
    editor.switchAndWrite(comment);
    blogPage.submitComment();
    blogPage.verifyCommentText(comment);
    blogPage.verifyCommentCreator(User.SUS_REGULAR_USER.getUserName());
    blogPage.triggerCommentReply();
    String commentReply = PageContent.COMMENT_TEXT + blogPage.getTimeStamp();
    editor.switchAndReplyComment(commentReply);
    blogPage.submitReplyComment();
    blogPage.verifyCommentReply(commentReply);
    blogPage.verifyReplyCreator(User.SUS_REGULAR_USER.getUserName());
  }

  @Test(groups = "BlogComments_003")
  @Execute(asUser = User.SUS_REGULAR_USER)
  public void UserCanEditComment() {
    String blogTitle = PageContent.BLOG_POST_NAME_PREFIX + DateTime.now().getMillis();
    String blogContent = PageContent.BLOG_CONTENT + DateTime.now().getMillis();
    new BlogContent(credentials.userName).push(blogContent, blogTitle);

    BlogPage blogPage = new BlogPage().open(credentials.userName, blogTitle);
    MiniEditorComponentObject editor = blogPage.triggerCommentArea();
    String comment = PageContent.COMMENT_TEXT + blogPage.getTimeStamp();
    editor.switchAndWrite(comment);
    blogPage.submitComment();
    blogPage.verifyCommentText(comment);
    blogPage.verifyCommentCreator(User.SUS_REGULAR_USER.getUserName());
    blogPage.triggerEditCommentArea();
    String commentEdited = PageContent.COMMENT_TEXT + blogPage.getTimeStamp();
    editor.switchAndEditComment(commentEdited);
    blogPage.submitEditComment();
    blogPage.verifyCommentText(commentEdited);
  }

  @Test(groups = {"BlogComments_004", "k8s-notification-fail"})
  @Execute(asUser = User.STAFF)
  public void AdminCanDeleteAComment() {
    String blogTitle = PageContent.BLOG_POST_NAME_PREFIX + DateTime.now().getMillis();
    String blogContent = PageContent.BLOG_CONTENT + DateTime.now().getMillis();
    new BlogContent(credentials.userName).push(blogContent, blogTitle);

    BlogPage blogPage = new BlogPage().open(credentials.userName, blogTitle);
    MiniEditorComponentObject editor = blogPage.triggerCommentArea();
    String comment = PageContent.COMMENT_TEXT + blogPage.getTimeStamp();
    editor.switchAndWrite(comment);
    blogPage.submitComment();
    blogPage.verifyCommentText(comment);
    blogPage.verifyCommentCreator(credentials.userNameStaff);
    String commentText = blogPage.getFirstCommentText();
    DeletePageObject delete = blogPage.deleteFirstComment();
    delete.submitDeletion();

    List<Notification> confirmNotifications = blogPage.getNotifications(NotificationType.CONFIRM);
    Assertion.assertEquals(
        confirmNotifications.size(),
        1,
        DeletePageObject.AssertionMessages.INVALID_NUMBER_OF_CONFIRMING_NOTIFICATIONS
    );
    Assertion.assertTrue(
        confirmNotifications.stream().findFirst().get().isVisible(),
        DeletePageObject.AssertionMessages.BANNER_NOTIFICATION_NOT_VISIBLE
    );
    blogPage.verifyCommentDeleted(commentText);
  }
}
