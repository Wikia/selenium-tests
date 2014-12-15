package com.wikia.webdriver.testcases.commentstests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.UserProfilePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog.BlogPageObject;

public class BlogCommentsTests extends NewTestTemplate {

	/**
	 * @author Karol 'kkarolk' Kujawiak
	 * <p/>
	 * Test cases:
	 * 1. (Anon) Add comment to the blog post, reply to the comment
	 * 2. (user) Add comment to the blog post, reply to the comment
	 * 3. (User) Edit existing comment,
	 * 4. (Staff) Delete existing comment,
	 */

	Credentials credentials = config.getCredentials();

	@Test(groups = {"BlogComments_001", "BlogCommentsTests"})
	public void BlogComments_001_Anon_commentReply() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
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

	@Test(groups = {"BlogComments_002", "BlogCommentsTests"})
	public void BlogComments_002_User_commentReply() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
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


	@Test(groups = {"BlogComments_003", "BlogCommentsTests"})
	public void BlogComments_003_User_editComment() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
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

	@Test(groups = {"BlogComments_004", "BlogCommentsTests"})
	public void BlogComments_004_Admin_deleteComment() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
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
		blogPage.verifyNotificationMessage();
		blogPage.verifyCommentDeleted(commentText);
	}
}
