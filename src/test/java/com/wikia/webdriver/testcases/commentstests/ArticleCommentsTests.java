package com.wikia.webdriver.testcases.commentstests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

@Test(groups = "comments-articleComments")
public class ArticleCommentsTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = "ArticleComments_001", invocationCount = 5)
  @Execute(asUser = User.USER)
  public void ArticleComments_001_editComment() {
    new ArticleContent().push(PageContent.ARTICLE_TEXT);

    ArticlePageObject article = new ArticlePageObject().open();
    String comment = PageContent.COMMENT_TEXT + article.getTimeStamp();
    MiniEditorComponentObject editor = article.triggerCommentArea();
    editor.switchAndWrite(comment);
    article.submitComment();
    article.verifyCommentText(comment);
    article.verifyCommentCreator(credentials.userName);
    article.triggerEditCommentArea();
    String commentEdited = PageContent.COMMENT_TEXT + article.getTimeStamp();
    editor.switchAndEditComment(commentEdited);
    article.submitEditComment();
    article.verifyCommentText(commentEdited);
  }

  @Test(groups = "ArticleComments_002")
  @Execute(asUser = User.USER)
  public void ArticleComments_002_replyComment() {
    new ArticleContent().push(PageContent.ARTICLE_TEXT);

    ArticlePageObject article = new ArticlePageObject().open();
    String comment = PageContent.COMMENT_TEXT +  DateTime.now().getMillis();
    MiniEditorComponentObject editor = article.triggerCommentArea();
    editor.switchAndWrite(comment);
    article.submitComment();
    article.verifyCommentText(comment);
    article.verifyCommentCreator(credentials.userName);
    article.triggerCommentReply();
    String commentReply = PageContent.REPLY_TEXT + article.getTimeStamp();
    editor.switchAndReplyComment(commentReply);
    article.submitReplyComment();
    article.verifyCommentReply(commentReply);
    article.verifyReplyCreator(credentials.userName);
  }

  @Test(groups = "ArticleComments_003")
  public void ArticleComments_003_anonReplyComment() {
    new ArticleContent().push(PageContent.ARTICLE_TEXT);

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
  @Execute(asUser = User.STAFF)
  public void ArticleComments_004_deleteComment() {
    new ArticleContent().push(PageContent.ARTICLE_TEXT);

    ArticlePageObject article = new ArticlePageObject().open();
    String comment = PageContent.COMMENT_TEXT + article.getTimeStamp();
    MiniEditorComponentObject editor = article.triggerCommentArea();
    editor.switchAndWrite(comment);
    article.submitComment();
    article.verifyCommentText(comment);
    article.verifyCommentCreator(credentials.userNameStaff);
    String commentText = article.getFirstCommentText();
    DeletePageObject delete = article.deleteFirstComment();
    delete.submitDeletion();
    Assertion.assertTrue(article.getBannerNotifications().isNotificationMessageVisible());
    article.verifyCommentDeleted(commentText);
  }
}
