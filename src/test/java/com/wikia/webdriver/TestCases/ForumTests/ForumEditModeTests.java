package com.wikia.webdriver.TestCases.ForumTests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumBoardPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumManageBoardsPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;

public class ForumEditModeTests extends NewTestTemplate{

	/*
	 * StoryQA0128 - Create test cases for forum
	 * https://wikia.fogbugz.com/default.asp?95449
	 */

	private String title, description, first, second;
	Credentials credentials = config.getCredentials();

	@Test(groups = {"ForumEditModeTests_001","Forum","ForumEditMode"})
	public void ForumEditModeTests_001_faq(){
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		forumMainPage.openForumMainPage(wikiURL);
		forumMainPage.verifyFaqLightBox();
	}

	@DataProvider
	private static final Object[][] getForumName()
	{
		return new Object[][]
				{
					{PageContent.FORUM_TITLE_NON_LATIN_PREFIX},
					{PageContent.FORUM_TITLE_PREFIX},
					{PageContent.FORUM_TITLE_40_CHAR_PREFIX},
					{PageContent.FORUM_TITLE_SLASH_PREFIX},
					{PageContent.FORUM_TITLE_UNDER_SCORE_PREFIX}
				};
	}

	@Test(dataProvider="getForumName", groups={"ForumEditModeTests_002", "Forum","ForumEditMode"})
	public void ForumEditModeTests_002_createNewBoard(String name){
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		forumMainPage.openForumMainPage(wikiURL);
		ForumManageBoardsPageObject manageForum = forumMainPage.clickManageBoardsButton();
		title = name+manageForum.getTimeStamp();
		description = PageContent.FORUM_DESCRIPTION_PREFIX+manageForum.getTimeStamp();
		manageForum.createNewBoard(title, description);
		manageForum.verifyBoardCreated(title, description);
		manageForum.verifyForumExists(title, wikiURL);
	}

	@Test(groups = {"ForumEditModeTests_003","Forum","ForumEditMode"})
	public void ForumEditModeTests_003_deleteBoard(){
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		forumMainPage.openForumMainPage(wikiURL);
		ForumManageBoardsPageObject manageForum = forumMainPage.clickManageBoardsButton();
		first = manageForum.getFirstForumName();
		second = manageForum.getSecondForumName();
		manageForum.verifyForumExists(first, wikiURL);
		manageForum.deleteForum(first, second);
		manageForum.verifyForumNotExists(first, wikiURL);
	}

	@Test(groups = {"ForumEditModeTests_004","Forum","ForumEditMode"})
	public void ForumEditModeTests_004_editBoard(){
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		forumMainPage.openForumMainPage(wikiURL);
		ForumManageBoardsPageObject manageForum = forumMainPage.clickManageBoardsButton();
		first = manageForum.getFirstForumName();
		title = PageContent.FORUM_TITLE_EDIT_PREFIX+manageForum.getTimeStamp();
		description = PageContent.FORUM_DESCRIPTION_EDIT_PREFIX+manageForum.getTimeStamp();
		manageForum.editForum(first, title, description);
		manageForum.verifyBoardCreated(title, description);
		manageForum.verifyForumExists(title, wikiURL);
	}

	@Test(groups = {"Forum_005","Forum","ForumEditMode"})
	public void forumEditModeTests_005_moveBoard(){
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		forumMainPage.openForumMainPage(wikiURL);
		ForumManageBoardsPageObject manageForum = forumMainPage.clickManageBoardsButton();
		first = manageForum.getFirstForumName();
		manageForum.clickMoveDown(first);
		second = manageForum.getSecondForumName();
		manageForum.clickMoveUp(second);
	}

	@Test(groups = {"Forum_006", "Forum", "ForumEditMode"})
	public void forumEditModeTests_006_templatesInBoardDescription() {		
		ForumPageObject forumMainPage = new ForumPageObject( driver );

		// create a template
		String templateNameAndContent = "Forum_test_template_" + forumMainPage.getTimeStamp();
		WikiArticlePageObject article = new WikiArticlePageObject( driver );
		forumMainPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		article.createNewTemplate(wikiURL, templateNameAndContent, templateNameAndContent );
		
		// open forum page and create new board
		forumMainPage.openForumMainPage(wikiURL);
		ForumManageBoardsPageObject forumManageBoardPage = forumMainPage.clickManageBoardsButton();

		// create new board and verify its creation
		String boardTitle = "QA with tpl (" + forumManageBoardPage.getTimeStamp() + ")";
		String boardDescWithoutTpl = "QA with tpl.";
		String boardDescWithTpl = boardDescWithoutTpl + " {{" + templateNameAndContent + "}}";
		String boardDescWithTplParsed = boardDescWithoutTpl + " " + templateNameAndContent;
		forumManageBoardPage.createNewBoard( boardTitle, boardDescWithTpl );
		forumManageBoardPage.verifyBoardCreated(boardTitle, boardDescWithoutTpl);

		// open the board and verify there is the template's content in description
		ForumBoardPageObject boardPage = forumMainPage.openForumBoard( );
		boardPage.verifyBoardDescription( boardDescWithTplParsed );
	}

}
