package com.wikia.webdriver.testcases.commentstests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.UserProfilePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog.BlogPageObject;

import org.testng.annotations.Test;

@Test(groups = "comments-blogComments")
public class BlogCommentsTests extends NewTestTemplate {

  private String userName = User.USER.getUserName();

  @Test(groups = "BlogComments_001")
  public void BlogComments_001_Anon_commentReply() {
    WikiBasePageObject base = new WikiBasePageObject();
    UserProfilePageObject userProfile = base.openProfilePage(userName, wikiURL);
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
    UserProfilePageObject userProfile = base.openProfilePage(userName, wikiURL);
    userProfile.clickOnBlogTab();
    BlogPageObject blogPage = userProfile.openFirstPost();
    MiniEditorComponentObject editor = blogPage.triggerCommentArea();
    String comment = PageContent.COMMENT_TEXT + blogPage.getTimeStamp();
    editor.switchAndWrite(comment);
    blogPage.submitComment();
    blogPage.verifyCommentText(comment);
    blogPage.verifyCommentCreator(userName);
    blogPage.triggerCommentReply();
    String commentReply = PageContent.COMMENT_TEXT + blogPage.getTimeStamp();
    editor.switchAndReplyComment(commentReply);
    blogPage.submitReplyComment();
    blogPage.verifyCommentReply(commentReply);
    blogPage.verifyReplyCreator(userName);
  }


  @Test(groups = "BlogComments_003")
  @Execute(asUser = User.USER)
  public void BlogComments_003_User_editComment() {
    WikiBasePageObject base = new WikiBasePageObject();
    UserProfilePageObject userProfile = base.openProfilePage(userName, wikiURL);
    userProfile.clickOnBlogTab();
    BlogPageObject blogPage = userProfile.openFirstPost();
    MiniEditorComponentObject editor = blogPage.triggerCommentArea();
    String comment = PageContent.COMMENT_TEXT + blogPage.getTimeStamp();
    editor.switchAndWrite(comment);
    blogPage.submitComment();
    blogPage.verifyCommentText(comment);
    blogPage.verifyCommentCreator(userName);
    blogPage.triggerEditCommentArea();
    String commentEdited = PageContent.COMMENT_TEXT + blogPage.getTimeStamp();
    editor.switchAndEditComment(commentEdited);
    blogPage.submitEditComment();
    blogPage.verifyCommentText(commentEdited);
  }

  @Test(groups = "BlogComments_004")
  @Execute(asUser = User.STAFF)
  public void BlogComments_004_Admin_deleteComment() {
    WikiBasePageObject base = new WikiBasePageObject();
    UserProfilePageObject userProfile = base.openProfilePage(userName, wikiURL);
    userProfile.clickOnBlogTab();
    BlogPageObject blogPage = userProfile.openFirstPost();
    MiniEditorComponentObject editor = blogPage.triggerCommentArea();
    String comment = PageContent.COMMENT_TEXT + blogPage.getTimeStamp();
    editor.switchAndWrite(comment);
    blogPage.submitComment();
    blogPage.verifyCommentText(comment);
    blogPage.verifyCommentCreator(User.STAFF.getUserName());
    String commentText = blogPage.getFirstCommentText();
    DeletePageObject delete = blogPage.deleteFirstComment();
    delete.submitDeletion();

    Assertion.assertTrue(blogPage.getBannerNotifications().isNotificationMessageVisible(),
                         "Banner notification message is not visible");
    blogPage.verifyCommentDeleted(commentText);
  }
}
