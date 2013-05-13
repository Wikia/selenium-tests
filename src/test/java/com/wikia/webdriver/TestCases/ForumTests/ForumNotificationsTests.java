package com.wikia.webdriver.TestCases.ForumTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumBoardPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumThreadPageObject;

public class ForumNotificationsTests extends TestTemplate{

	private String title;
	private String message;
	
	/**
	 * Test case created to check possible regression of DAR-112 defect
	 * 
	 * https://wikia-inc.atlassian.net/browse/DAR-112
	 */
	@Test(groups= {"ForumNotificationsTests_001", "ForumNotificationsTests", "Forum"} )
	public void forumNotificationsTests_001_notificationsGoToCorrectAnchoredPost(){
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		title = PageContent.forumTitlePrefix + forumMainPage.getTimeStamp();
		message = PageContent.forumMessage + forumMainPage.getTimeStamp();
		forumMainPage.openForumMainPage();
		ForumBoardPageObject forumBoard = forumMainPage.openForumBoard(1);	
		ForumThreadPageObject forumThread = forumBoard.startDiscussion(title, message, false);
		forumThread.verifyDiscussionTitleAndMessage(title, message);	
		CommonFunctions.logOut(driver);
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		forumThread.reply(message);
		forumThread.verifyReplyMessage(1, message);		
	}
	
}
