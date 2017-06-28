package com.wikia.webdriver.testcases.commentstests;

import java.util.List;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.oasis.components.notifications.Notification;
import com.wikia.webdriver.elements.oasis.components.notifications.NotificationType;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

@Test(groups = "comments-articleComments")
public class ArticleCommentsTests extends NewTestTemplate {

  @Test(groups = "ArticleComments_001")
  @Execute(asUser = User.COMMENTS_REGULAR_USER)
  public void UserCanEditHisOwnComment() {
    new ArticleContent(User.COMMENTS_REGULAR_USER).push(PageContent.ARTICLE_TEXT);

    ArticlePageObject article = new ArticlePageObject().open();
    String comment = PageContent.COMMENT_TEXT + article.getTimeStamp();
    MiniEditorComponentObject editor = article.triggerCommentArea();
    editor.switchAndWrite(comment);
    article.submitComment();
    article.verifyCommentText(comment);
    article.verifyCommentCreator(User.COMMENTS_REGULAR_USER.getUserName());
    article.triggerEditCommentArea();
    String commentEdited = PageContent.COMMENT_TEXT + article.getTimeStamp();
    editor.switchAndEditComment(commentEdited);
    article.submitEditComment();
    article.verifyCommentText(commentEdited);
  }

  @Test(groups = "ArticleComments_002")
  @Execute(asUser = User.COMMENTS_REGULAR_USER)
  public void UserCanReplyToAComment() {
    new ArticleContent(User.COMMENTS_REGULAR_USER).push(PageContent.ARTICLE_TEXT);

    ArticlePageObject article = new ArticlePageObject().open();
    String comment = PageContent.COMMENT_TEXT + DateTime.now().getMillis();
    MiniEditorComponentObject editor = article.triggerCommentArea();
    editor.switchAndWrite(comment);
    article.submitComment();
    article.verifyCommentText(comment);
    article.verifyCommentCreator(User.COMMENTS_REGULAR_USER.getUserName());
    article.triggerCommentReply();
    String commentReply = PageContent.REPLY_TEXT + article.getTimeStamp();
    editor.switchAndReplyComment(commentReply);
    article.submitReplyComment();
    article.verifyCommentReply(commentReply);
    article.verifyReplyCreator(User.COMMENTS_REGULAR_USER.getUserName());
  }

  @Test(groups = "ArticleComments_003")
  public void AnonCanReplyToAComment() {
    new ArticleContent(User.COMMENTS_REGULAR_USER).push(PageContent.ARTICLE_TEXT);

    ArticlePageObject article = new ArticlePageObject().open();
    String comment = PageContent.COMMENT_TEXT + article.getTimeStamp();
    MiniEditorComponentObject editor = article.triggerCommentArea();
    editor.switchAndWrite(comment);
    article.submitComment();
    article.verifyCommentText(comment);
    article.verifyCommentCreator(PageContent.WIKIA_CONTRIBUTOR);
    article.triggerCommentReply();
    String commentReply = PageContent.REPLY_TEXT + article.getTimeStamp();
    editor.switchAndReplyComment(commentReply);
    article.submitReplyComment();
    article.verifyCommentReply(commentReply);
    article.verifyReplyCreator(PageContent.WIKIA_CONTRIBUTOR);
  }

  @Test(groups = "ArticleComments_004")
  @Execute(asUser = User.COMMENTS_REGULAR_USER)
  public void AdminCanDeleteComments() {
    new ArticleContent(User.COMMENTS_REGULAR_USER).push(PageContent.ARTICLE_TEXT);

    ArticlePageObject article = new ArticlePageObject().open();
    String comment = PageContent.COMMENT_TEXT + article.getTimeStamp();
    MiniEditorComponentObject editor = article.triggerCommentArea();
    editor.switchAndWrite(comment);
    article.submitComment();
    article.verifyCommentText(comment);
    article.verifyCommentCreator(User.COMMENTS_REGULAR_USER.getUserName());
    article.loginAs(User.SUS_ADMIN);
    String commentText = article.getFirstCommentText();
    DeletePageObject delete = article.deleteFirstComment();
    delete.submitDeletion();

    List<Notification> confirmNotifications = article.getNotifications(NotificationType.CONFIRM);
    Assertion.assertEquals(confirmNotifications.size(), 1,
        DeletePageObject.AssertionMessages.INVALID_NUMBER_OF_CONFIRMING_NOTIFICATIONS);
    Assertion.assertTrue(confirmNotifications.stream().findFirst().get().isVisible());
    article.verifyCommentDeleted(commentText);
  }
}
