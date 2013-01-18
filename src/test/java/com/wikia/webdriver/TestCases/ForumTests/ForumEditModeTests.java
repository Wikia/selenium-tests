package com.wikia.webdriver.TestCases.ForumTests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjects.PageObject.ForumPageObject.ForumManageBoardsPageObject;
import com.wikia.webdriver.PageObjects.PageObject.ForumPageObject.ForumPageObject;

public class ForumEditModeTests extends TestTemplate{
	
	private String title, description, first, second;
	
	@Test
	public void forumEditModeTests_001_faq(){
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.openRandomArticleByUrl();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		forumMainPage.openForumMainPage();
		forumMainPage.verifyFaqLightBox();
	}
	
	@DataProvider
	private static final Object[][] getForumName()
	{
		return new Object[][]
				{
					{PageContent.forumTitleNonlatinPrefix},
					{PageContent.forumTitlePrefix},
					{PageContent.forumTitle40CharPrefix},
					{PageContent.forumTitleSlashPrefix},
					{PageContent.forumTitleUnderScorePrefix}
				};
	}	
	
	@Test(dataProvider="getForumName", groups={""})
	public void forumEditModeTests_002_createNewBoard(String name){
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
//		forumMainPage.openRandomArticleByUrl();
		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		forumMainPage.openForumMainPage();
		ForumManageBoardsPageObject manageForum = forumMainPage.clickManageBoardsButton();
		title = name+manageForum.getTimeStamp();
		description = PageContent.forumDescriptionPrefix+manageForum.getTimeStamp();
		manageForum.createNewBoard(title, description);
		manageForum.verifyBoardCreated(title, description);
	}
	
	@Test
	public void forumEditModeTests_003_deleteBoard(){
		CommonFunctions.logOut(driver);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
//		forumMainPage.openRandomArticleByUrl();
		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff);
		forumMainPage.openForumMainPage();
		ForumManageBoardsPageObject manageForum = forumMainPage.clickManageBoardsButton();
		first = manageForum.getFirstForumName();
		second = manageForum.getSecondForumName();
		manageForum.verifyForumExists(first);
		manageForum.deleteForum(first, second);
		manageForum.verifyForumNotExists(first);
	}
}
