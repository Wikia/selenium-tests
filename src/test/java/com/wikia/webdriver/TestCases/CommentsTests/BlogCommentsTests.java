package com.wikia.webdriver.TestCases.CommentsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor.MiniEditorComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticleActions.DeleteArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.UserProfilePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Blog.BlogPageObject;

public class BlogCommentsTests extends NewTestTemplate {

	/**
	 * @author Karol 'kkarolk' Kujawiak
	 *
	 * Test cases:
	 * 1. (Anon) Add comment to the blog post, reply to the comment
	 * 2. (user) Add comment to the blog post, reply to the comment
	 * 3. (User) Edit existing comment,
	 * 4. (Staff) Delete existing comment,
	 */

	Credentials credentials = config.getCredentials();

	@Test(groups = { "BlogComments_001", "BlogCommentsTests"})
	public void BlogComments_001_Anon_commentReply() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		UserProfilePageObject userProfile = base.navigateToProfilePage(credentials.userName, wikiURL);
		userProfile.clickOnBlogTab();
		BlogPageObject blogPage = userProfile.openFirstPost();
		MiniEditorComponentObject editor = blogPage.triggerCommentArea();
		String comment = PageContent.commentText + blogPage.getTimeStamp();
		editor.switchAndWrite(comment);
		blogPage.submitComment();
		blogPage.verifyArticleComment(comment);
		blogPage.verifyCommentCreator(PageContent.wikiaContributor);
		blogPage.triggerCommentReply();
		String commentReply = PageContent.commentText + blogPage.getTimeStamp();
		editor.switchAndReplyComment(commentReply);
		blogPage.submitReplyComment();
		blogPage.verifyCommentReply(commentReply);
		blogPage.verifyReplyCreator(PageContent.wikiaContributor);
	}

	@Test(groups = { "BlogComments_002", "BlogCommentsTests"})
	public void BlogComments_002_User_commentReply() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		UserProfilePageObject userProfile = base.navigateToProfilePage(credentials.userName, wikiURL);
		userProfile.clickOnBlogTab();
		BlogPageObject blogPage = userProfile.openFirstPost();
		MiniEditorComponentObject editor = blogPage.triggerCommentArea();
		String comment = PageContent.commentText + blogPage.getTimeStamp();
		editor.switchAndWrite(comment);
		blogPage.submitComment();
		blogPage.verifyArticleComment(comment);
		blogPage.verifyCommentCreator(credentials.userName);
		blogPage.triggerCommentReply();
		String commentReply = PageContent.commentText + blogPage.getTimeStamp();
		editor.switchAndReplyComment(commentReply);
		blogPage.submitReplyComment();
		blogPage.verifyCommentReply(commentReply);
		blogPage.verifyReplyCreator(credentials.userName);
	}


	@Test(groups = { "BlogComments_003", "BlogCommentsTests"})
	public void BlogComments_003_User_editComment() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		UserProfilePageObject userProfile = base.navigateToProfilePage(credentials.userName, wikiURL);
		userProfile.clickOnBlogTab();
		BlogPageObject blogPage = userProfile.openFirstPost();
		MiniEditorComponentObject editor = blogPage.triggerCommentArea();
		String comment = PageContent.commentText + blogPage.getTimeStamp();
		editor.switchAndWrite(comment);
		blogPage.submitComment();
		blogPage.verifyArticleComment(comment);
		blogPage.verifyCommentCreator(credentials.userName);
		blogPage.triggerEditCommentArea();
		String commentEdited = PageContent.commentText + blogPage.getTimeStamp();
		editor.switchAndEditComment(commentEdited);
		blogPage.submitEditComment();
		blogPage.verifyArticleComment(commentEdited);
	}

	@Test(groups = { "BlogComments_004", "BlogCommentsTests"})
	public void BlogComments_004_Admin_deleteComment() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		UserProfilePageObject userProfile = base.navigateToProfilePage(credentials.userName, wikiURL);
		userProfile.clickOnBlogTab();
		BlogPageObject blogPage = userProfile.openFirstPost();
		MiniEditorComponentObject editor = blogPage.triggerCommentArea();
		String comment = PageContent.commentText + blogPage.getTimeStamp();
		editor.switchAndWrite(comment);
		blogPage.submitComment();
		blogPage.verifyArticleComment(comment);
		blogPage.verifyCommentCreator(credentials.userName);
		String commentText = blogPage.getFirstCommentText();
		DeleteArticlePageObject delete = blogPage.deleteComment();
		delete.submitDeletion();
		blogPage.verifyNotificationMessage();
		blogPage.verifyCommentDeleted(commentText);
	}
}
