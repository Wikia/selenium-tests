package com.wikia.webdriver.TestCases.ForumTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumBoardPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumThreadPageObject;

public class ForumBoardTests extends NewTestTemplate {

	/*
	 * StoryQA0128 - Create test cases for forum
	 * https://wikia.fogbugz.com/default.asp?95449
	 */

	private String title;
	private String message;
	Credentials credentials = config.getCredentials();

	@Test(groups= {"ForumBoardTests_001", "ForumBoardTests", "Forum", "Smoke3"} )
	public void ForumBoardTests_001_startDiscussionWithTitleAndMessage(){
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		title = PageContent.FORUM_TITLE_PREFIX + forumMainPage.getTimeStamp();
		message = PageContent.FORUM_MESSAGE + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage(wikiURL);
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
	}

	@Test(groups= {"ForumBoardTests_002", "ForumBoardTests", "Forum"} )
	public void ForumBoardTests_002_startDiscussionWithoutTitle(){
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		message = PageContent.FORUM_MESSAGE + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage(wikiURL);
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
		ForumThreadPageObject forumThread = forumBoard.startDiscussionWithoutTitle(message);
		//"Message from" default title appears after posting message without title
		forumThread.verifyDiscussionTitleAndMessage("Message from", message);
	}

	@Test(groups= {"ForumBoardTests_003", "ForumBoardTests", "Forum"} )
	public void ForumBoardTests_003_startDiscussionWithImage(){
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		title = PageContent.FORUM_TITLE_PREFIX + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage(wikiURL);
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
		forumBoard.startDiscussionWithImgae(title);
		forumBoard.clickPostButton();
		forumBoard.verifyDiscussionWithImage();
	}

	@Test(groups= {"ForumBoardTests_004", "ForumBoardTests", "Forum"} )
	public void ForumBoardTests_004_startDiscussionWithLink(){
		String Externallink = PageContent.EXTERNAL_LINK;
		String Internallink = PageContent.REDIRECT_LINK;
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		title = PageContent.FORUM_TITLE_PREFIX + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage(wikiURL);
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
		forumBoard.startDiscussionWithLink(Internallink, Externallink, title);
		forumBoard.clickPostButton();
		forumBoard.verifyStartedDiscussionWithLinks(Internallink, Externallink);
	}

	@Test(groups= {"ForumBoardTests_005", "ForumBoardTests", "Forum"} )
	public void ForumBoardTests_005_startDiscussionWithVideo(){
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		title = PageContent.FORUM_TITLE_PREFIX + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage(wikiURL);
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
		forumBoard.startDiscussionWithVideo(VideoContent.youtubeVideoURL3, title);
		forumBoard.clickPostButton();
	}

	@Test(groups= {"ForumBoardTests_006", "ForumBoardTests", "Forum"} )
	public void ForumBoardTests_006_followDiscussion(){
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		title = PageContent.FORUM_TITLE_PREFIX + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage(wikiURL);
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
		forumBoard.unfollowIfDiscussionIsFollowed(1);
		forumBoard.verifyTextOnFollowButton(1, "Follow");
		forumBoard.clickOnFollowButton(1);
		forumBoard.verifyTextOnFollowButton(1, "Following");
		forumBoard.clickOnFollowButton(1);
		forumBoard.verifyTextOnFollowButton(1, "Follow");
	}

	@Test(groups= {"ForumBoardTests_007", "ForumBoardTests", "Forum"} )
	public void ForumBoardTests_007_highlightDiscussion_MAIN_2106(){
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		title = PageContent.FORUM_TITLE_PREFIX + forumMainPage.getTimeStamp();
		message = PageContent.FORUM_MESSAGE + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage(wikiURL);
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard();
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, true);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
 		forumThread.notifications_verifyLatestNotificationTitle(title);
	}
}
