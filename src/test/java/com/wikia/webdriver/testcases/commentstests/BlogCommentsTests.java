package com.wikia.webdriver.testcases.commentstests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.UserProfilePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog.BlogPageObject;

import org.testng.annotations.Test;

@Test(groups = "comments-blogComments")
public class BlogCommentsTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = "BlogComments_001")
  public void BlogComments_001_Anon_commentReply() {
    WikiBasePageObject base = new WikiBasePageObject();
    UserProfilePageObject userProfile = base.openProfilePage(credentials.userName, wikiURL);
    userProfile.clickOnBlogTab();
    BlogPageObject blogPage = userProfile.openFirstPost();
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
  @Execute(asUser = User.USER)
  public void BlogComments_002_User_commentReply() {
    WikiBasePageObject base = new WikiBasePageObject();
    UserProfilePageObject userProfile = base.openProfilePage(credentials.userName, wikiURL);
    userProfile.clickOnBlogTab();
    BlogPageObject blogPage = userProfile.openFirstPost();
    MiniEditorComponentObject editor = blogPage.triggerCommentArea();
    String comment = PageContent.COMMENT_TEXT + blogPage.getTimeStamp();
    editor.switchAndWrite(comment);
    blogPage.submitComment();
    blogPage.verifyCommentText(comment);
    blogPage.verifyCommentCreator(credentials.userName);
    blogPage.triggerCommentReply();
    String commentReply = PageContent.COMMENT_TEXT + blogPage.getTimeStamp();
    editor.switchAndReplyComment(commentReply);
    blogPage.submitReplyComment();
    blogPage.verifyCommentReply(commentReply);
    blogPage.verifyReplyCreator(credentials.userName);
  }


  @Test(groups = "BlogComments_003")
  @Execute(asUser = User.USER)
  public void BlogComments_003_User_editComment() {
    WikiBasePageObject base = new WikiBasePageObject();
    UserProfilePageObject userProfile = base.openProfilePage(credentials.userName, wikiURL);
    userProfile.clickOnBlogTab();
    BlogPageObject blogPage = userProfile.openFirstPost();
    MiniEditorComponentObject editor = blogPage.triggerCommentArea();
    String comment = PageContent.COMMENT_TEXT + blogPage.getTimeStamp();
    editor.switchAndWrite(comment);
    blogPage.submitComment();
    blogPage.verifyCommentText(comment);
    blogPage.verifyCommentCreator(credentials.userName);
    blogPage.triggerEditCommentArea();
    String commentEdited = PageContent.COMMENT_TEXT + blogPage.getTimeStamp();
    editor.switchAndEditComment(commentEdited);
    blogPage.submitEditComment();
    blogPage.verifyCommentText(commentEdited);
  }

  @Test(groups = "BlogComments_004")
  public void BlogComments_004_Admin_deleteComment() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    UserProfilePageObject userProfile = base.openProfilePage(credentials.userName, wikiURL);
    userProfile.clickOnBlogTab();
    BlogPageObject blogPage = userProfile.openFirstPost();
    MiniEditorComponentObject editor = blogPage.triggerCommentArea();
    String comment = PageContent.COMMENT_TEXT + blogPage.getTimeStamp();
    editor.switchAndWrite(comment);
    blogPage.submitComment();
    blogPage.verifyCommentText(comment);
    blogPage.verifyCommentCreator(credentials.userNameStaff);
    String commentText = blogPage.getFirstCommentText();
    DeletePageObject delete = blogPage.deleteFirstComment();
    delete.submitDeletion();
    blogPage.getBannerNotifications().verifyNotificationMessage();
    blogPage.verifyCommentDeleted(commentText);
  }
}
