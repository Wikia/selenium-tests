package com.wikia.webdriver.testcases.commentstests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class ArticleCommentsTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @Test(groups = {"ArticleComments_001", "ArticleComments", "Smoke2"})
  public void ArticleComments_001_editComment() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    ArticlePageObject article = base.openRandomArticle(wikiURL);
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

  @Test(groups = {"ArticleComments_002", "ArticleComments"})
  public void ArticleComments_002_replyComment() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    ArticlePageObject article = base.openRandomArticle(wikiURL);
    String comment = PageContent.COMMENT_TEXT + article.getTimeStamp();
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

  @Test(groups = {"ArticleComments_003", "ArticleComments"})
  public void ArticleComments_003_anonReplyComment() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    ArticlePageObject article = base.openRandomArticle(wikiURL);
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

  @Test(groups = {"ArticleComments_004", "ArticleComments"})
  public void ArticleComments_004_deleteComment() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    ArticlePageObject article = base.openRandomArticle(wikiURL);
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
