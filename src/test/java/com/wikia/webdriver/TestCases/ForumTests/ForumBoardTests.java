package com.wikia.webdriver.TestCases.ForumTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumBoardPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumThreadPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;

public class ForumBoardTests extends TestTemplate {

	/*
	 * StoryQA0128 - Create test cases for forum
	 * https://wikia.fogbugz.com/default.asp?95449
	 */

	private String title;
	private String message;

	@Test(groups= {"ForumBoardTests_001", "ForumBoardTests", "Forum"} )
	public void forumBoardTests_001_startDiscussionWithTitleAndMessage(){
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
	}

	@Test(groups= {"ForumBoardTests_002", "ForumBoardTests", "Forum"} )
	public void forumBoardTests_002_startDiscussionWithoutTitle(){
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);
		ForumThreadPageObject forumThread = forumBoard.startDiscussionWithoutTitle(message);
		//"Message from" default title appears after posting message without title
		forumThread.verifyDiscussionTitleAndMessage("Message from", message);
	}

	@Test(groups= {"ForumBoardTests_003", "ForumBoardTests", "Forum"} )
	public void forumBoardTests_003_startDiscussionWithImage(){
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);
		forumBoard.startDiscussionWithImgae(title);
		forumBoard.clickPostButton();
		forumBoard.verifyDiscussionWithImage();
	}

	@Test(groups= {"ForumBoardTests_004", "ForumBoardTests", "Forum"} )
	public void forumBoardTests_004_startDiscussionWithLink(){
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		String Externallink = PageContent.externalLink;
		String Internallink = PageContent.internalLink;
		login.logOut(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);
		forumBoard.startDiscussionWithLink(Internallink, Externallink, title);
		forumBoard.clickPostButton();
		forumBoard.verifyStartedDiscussionWithLinks(Internallink, Externallink);
	}

	@Test(groups= {"ForumBoardTests_005, ForumBoardTests", "Forum"} )
	public void forumBoardTests_005_startDiscussionWithVideo(){
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);
		forumBoard.startDiscussionWithVideo(VideoContent.youtubeVideoURL3, title);
		forumBoard.clickPostButton();
	}

	@Test(groups= {"ForumBoardTests_006, ForumBoardTests", "Forum"} )
	public void forumBoardTests_006_followDiscussion(){
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);
		forumBoard.unfollowIfDiscussionIsFollowed(1);
		forumBoard.verifyTextOnFollowButton(1, "Follow");
		forumBoard.clickOnFollowButton(1);
		forumBoard.verifyTextOnFollowButton(1, "Following");
		forumBoard.clickOnFollowButton(1);
		forumBoard.verifyTextOnFollowButton(1, "Follow");
	}

	@Test(groups= {"ForumBoardTests_007, ForumBoardTests", "Forum"} )
	public void forumBoardTests_007_highlightDiscussion(){
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, true);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
 		forumThread.notifications_verifyLatestNotificationTitle(title);
// 		forumThread.notifications_markLatestNotificationsAsRead();
	}
}
