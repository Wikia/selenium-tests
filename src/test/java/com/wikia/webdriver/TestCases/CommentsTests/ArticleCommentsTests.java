package com.wikia.webdriver.TestCases.CommentsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor.MiniEditorComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticleActions.DeleteArticlePageObject;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class ArticleCommentsTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"ArticleCommentsUser_001", "ArticleComments"})
	public void ArticleCommentsUser_001_editComment() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		String comment = PageContent.commentText + article.getTimeStamp();
		MiniEditorComponentObject editor = article.triggerCommentArea();
		editor.switchAndWrite(comment);
		article.submitComment();
		article.verifyCommentText(comment);
		article.verifyCommentCreator(credentials.userName);
		article.triggerEditCommentArea();
		String commentEdited = PageContent.commentText + article.getTimeStamp();
		editor.switchAndEditComment(commentEdited);
		article.submitEditComment();
		article.verifyCommentText(commentEdited);
	}

	@Test(groups = {"ArticleCommentsUser_002", "ArticleComments"})
	public void ArticleCommentsUser_002_replyComment() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		String comment = PageContent.commentText + article.getTimeStamp();
		MiniEditorComponentObject editor = article.triggerCommentArea();
		editor.switchAndWrite(comment);
		article.submitComment();
		article.verifyCommentText(comment);
		article.verifyCommentCreator(credentials.userName);
		article.triggerCommentReply();
		String commentReply = PageContent.replyText + article.getTimeStamp();
		editor.switchAndReplyComment(commentReply);
		article.submitReplyComment();
		article.verifyCommentReply(commentReply);
		article.verifyReplyCreator(credentials.userName);
	}

	@Test(groups = {"ArticleCommentsAnon_001", "ArticleComments"})
	public void ArticleCommentsAnon_003_replyComment() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		String comment = PageContent.commentText + article.getTimeStamp();
		MiniEditorComponentObject editor = article.triggerCommentArea();
		editor.switchAndWrite(comment);
		article.submitComment();
		article.verifyCommentText(comment);
		article.verifyCommentCreator(PageContent.wikiaContributor);
		article.triggerCommentReply();
		String commentReply = PageContent.replyText + article.getTimeStamp();
		editor.switchAndReplyComment(commentReply);
		article.submitReplyComment();
		article.verifyCommentReply(commentReply);
		article.verifyReplyCreator(PageContent.wikiaContributor);
	}

	@Test(groups = {"ArticleCommentsSTAFF_001", "ArticleComments"})
	public void ArticleCommentsSTAFF_004_deleteComment() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		String comment = PageContent.commentText + article.getTimeStamp();
		MiniEditorComponentObject editor = article.triggerCommentArea();
		editor.switchAndWrite(comment);
		article.submitComment();
		article.verifyCommentText(comment);
		article.verifyCommentCreator(credentials.userNameStaff);
		String commentText = article.getFirstCommentText();
		DeleteArticlePageObject delete = article.deleteComment();
		delete.submitDeletion();
		article.verifyNotificationMessage();
		article.verifyCommentDeleted(commentText);
	}
}
