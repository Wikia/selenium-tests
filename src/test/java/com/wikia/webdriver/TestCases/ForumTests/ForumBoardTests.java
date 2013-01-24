package com.wikia.webdriver.TestCases.ForumTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjects.PageObject.ForumPageObject.ForumBoardPageObject;
import com.wikia.webdriver.PageObjects.PageObject.ForumPageObject.ForumPageObject;
import com.wikia.webdriver.PageObjects.PageObject.ForumPageObject.ForumThreadPageObject;
import com.wikia.webdriver.PageObjects.PageObject.MiniEditor.MiniEditorComponentObject;

public class ForumBoardTests extends TestTemplate {

	private String title;
	private String message;
	
	@Test(groups= {"ForumBoardTests_001", "ForumBoardTests", "Forum"} )
	public void forumBoardTests_001_startDiscussionWithTitleAndMessage(){
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);	
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
	}
	
	@Test(groups= {"ForumBoardTests_002", "ForumBoardTests", "Forum"} )
	public void forumBoardTests_002_startDiscussionWithoutTitle(){
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);	
		ForumThreadPageObject forumThread = forumBoard.startDiscussionWithoutTitle(message);
		//"Message from" default title appears after posting message without title
		forumThread.verifyDiscussionTitleAndMessage("Message from", message);
	}
	
	@Test(groups= {"ForumBoardTests_003", "ForumBoardTests", "Forum"} )
	public void forumBoardTests_003_startDiscussionWithImage(){
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);	
		forumBoard.startDiscussionWithImgae(title);
		forumBoard.clickPostButton();
		forumBoard.verifyDiscussionWithImage();
	}
	
	@Test(groups= {"ForumBoardTests_004", "ForumBoardTests", "Forum"} )
	public void forumBoardTests_004_startDiscussionWithLink(){
		String Externallink = PageContent.externalLink;
		String Internallink = PageContent.internalLink;
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);	
		forumBoard.startDiscussionWithLink(Internallink, Externallink, title);
		forumBoard.clickPostButton();
		forumBoard.verifyStartedDiscussionWithLinks(Internallink, Externallink);
	}
	
	@Test(groups= {"ForumBoardTests_005, ForumBoardTests", "Forum"} )
	public void forumBoardTests_005_startDiscussionWithVideo(){
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);	
		forumBoard.startDiscussionWithVideo(VideoContent.youtubeVideoURL3, title);
		forumBoard.clickPostButton();
	}
	
	@Test(groups= {"ForumBoardTests_006, ForumBoardTests", "Forum"} )
	public void forumBoardTests_006_followDiscussion(){
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
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
	
//	@Test(groups= {"ForumBoardTests_007, ForumBoardTests", "Forum"} )
	public void forumBoardTests_007_highlightDiscussion(){
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);	
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, true);
		forumThread.verifyDiscussionTitleAndMessage(title, message);
	}
}
