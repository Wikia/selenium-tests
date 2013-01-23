package com.wikia.webdriver.TestCases.ForumTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjects.PageObject.ForumPageObject.ForumBoardPageObject;
import com.wikia.webdriver.PageObjects.PageObject.ForumPageObject.ForumPageObject;
import com.wikia.webdriver.PageObjects.PageObject.MiniEditor.MiniEditorComponentObject;

public class ForumBoardTests extends TestTemplate {

	String title;
	String message;
	
	@Test(groups= {"ForumBoardTests_001", "ForumBoardTests", "Forum"} )
	public void ForumBoardTests_001_startDiscussionWithTitleAndMessage(){
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);	
		forumBoard.startDiscussion(title, message);
		forumBoard.verifyDiscussionWithTitle(title, message);
	}
	
	@Test(groups= {"ForumBoardTests_002", "ForumBoardTests", "Forum"} )
	public void ForumBoardTests_002_startDiscussionWithoutTitle(){
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);	
		forumBoard.startDiscussionWithoutTitle(message);
		//Message from User text not visible after posting message without title
		forumBoard.verifyDiscussionWithTitle("Message from", message);
	}
	
	@Test(groups= {"ForumBoardTests_003", "ForumBoardTests", "Forum"} )
	public void ForumBoardTests_003_startDiscussionWithImage(){
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
	public void ForumBoardTests_004_startDiscussionWithLink(){
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
	public void ForumBoardTests_005_startDiscussionWithVideo(){
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.openForumMainPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff, driver);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);	
		forumBoard.startDiscussionWithVideo(VideoContent.youtubeVideoURL3, title);
		forumBoard.clickPostButton();
		
	}
}
