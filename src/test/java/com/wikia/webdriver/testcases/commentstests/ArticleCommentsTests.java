package com.wikia.webdriver.testcases.commentstests;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.ExecuteAs;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

/**
 * @author: Bogna 'bognix' Knychała
 */
@Test(groups = {"ArticleComments"})
public class ArticleCommentsTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @Test(groups = {"ArticleComments_001", "Smoke2"})
  @ExecuteAs(user = User.USER)
  public void ArticleComments_001_editComment() {
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
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

  @Test(groups = {"ArticleComments_002"})
  @ExecuteAs(user = User.USER)
  public void ArticleComments_002_replyComment() {
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
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

  @Test(groups = {"ArticleComments_003"})
  public void ArticleComments_003_anonReplyComment() {
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
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

  @Test(groups = {"ArticleComments_004"})
  @ExecuteAs(user = User.STAFF)
  public void ArticleComments_004_deleteComment() {
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
    String comment = PageContent.COMMENT_TEXT + article.getTimeStamp();
    MiniEditorComponentObject editor = article.triggerCommentArea();
    editor.switchAndWrite(comment);
    article.submitComment();
    article.verifyCommentText(comment);
    article.verifyCommentCreator(credentials.userNameStaff);
    String commentText = article.getFirstCommentText();
    DeletePageObject delete = article.deleteFirstComment();
    delete.submitDeletion();
    article.verifyNotificationMessage();
    article.verifyCommentDeleted(commentText);
  }
}
