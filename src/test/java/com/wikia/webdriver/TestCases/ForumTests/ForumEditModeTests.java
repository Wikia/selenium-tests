package com.wikia.webdriver.TestCases.ForumTests;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumManageBoardsPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject.ForumPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;

public class ForumEditModeTests extends TestTemplate{
	
	/* 
	 * StoryQA0128 - Create test cases for forum
	 * https://wikia.fogbugz.com/default.asp?95449
	 */
	
	private String title, description, first, second;
	
	@Test(groups = {"Forum_001","Forum","ForumEditMode"})
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
	
	@Test(dataProvider="getForumName", groups={"Forum_002", "Forum","ForumEditMode"})
	public void forumEditModeTests_002_createNewBoard(String name){
		CommonFunctions.logOut(driver);
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.openForumMainPage();
		ForumManageBoardsPageObject manageForum = forumMainPage.clickManageBoardsButton();
		title = name+manageForum.getTimeStamp();
		description = PageContent.forumDescriptionPrefix+manageForum.getTimeStamp();
		manageForum.createNewBoard(title, description);
		manageForum.verifyBoardCreated(title, description);
		manageForum.verifyForumExists(title);
	}
	
	@Test(groups = {"Forum_003","Forum","ForumEditMode"})
	public void forumEditModeTests_003_deleteBoard(){
		CommonFunctions.logOut(driver);
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.openForumMainPage();
		ForumManageBoardsPageObject manageForum = forumMainPage.clickManageBoardsButton();
		first = manageForum.getFirstForumName();
		second = manageForum.getSecondForumName();
		manageForum.verifyForumExists(first);
		manageForum.deleteForum(first, second);
		manageForum.verifyForumNotExists(first);
	}
	
	@Test(groups = {"Forum_004","Forum","ForumEditMode"})
	public void forumEditModeTests_004_editBoard(){
		CommonFunctions.logOut(driver);
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.openForumMainPage();
		ForumManageBoardsPageObject manageForum = forumMainPage.clickManageBoardsButton();
		first = manageForum.getFirstForumName();
		title = PageContent.forumTitleEditPrefix+manageForum.getTimeStamp();
		description = PageContent.forumDescriptionEditPrefix+manageForum.getTimeStamp();
		manageForum.editForum(first, title, description);
		manageForum.verifyBoardCreated(title, description);
		manageForum.verifyForumExists(title);
	}
	
	@Test(groups = {"Forum_005","Forum","ForumEditMode"})
	public void forumEditModeTests_005_moveBoard(){
		CommonFunctions.logOut(driver);
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
		ForumPageObject forumMainPage = new ForumPageObject(driver);
		forumMainPage.openForumMainPage();
		ForumManageBoardsPageObject manageForum = forumMainPage.clickManageBoardsButton();
		first = manageForum.getFirstForumName();
		manageForum.clickMoveDown(first);
		second = manageForum.getSecondForumName();
		manageForum.clickMoveUp(second);
	}

    @Test(groups = {"Forum_006", "Forum", "ForumEditMode"})
    public void testTemplatesInBoardDescription() {
        ForumPageObject forumMainPage = new ForumPageObject(driver);

        // create a template
        String templateName = "Forum_test_template_" + forumMainPage.getTimeStamp();
        WikiArticleEditMode edit = forumMainPage.createNewTemplate( templateName, templateName );

        // go to Special:Forum and enter edit mode

        // add new board and use the template in description

        // verify the template's content IS visible on Special:Forum edit mode page

        // verify the template's content IS NOT visible on Special:Forum without edit mode

        // verify the templte's content IS visible on board page
    }
}
