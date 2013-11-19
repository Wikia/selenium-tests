package com.wikia.webdriver.TestCases.SpecialPagesTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialContributionsPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Block.SpecialBlockListPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Block.SpecialBlockPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Block.SpecialUnblockPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

public class UserAndRights extends TestTemplate{

	@Test(groups = {"usersAndRights001", "UsersAndRights"})
	public void usersAndRights001_Block(){
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		SpecialBlockPageObject block = new SpecialBlockPageObject(driver);
		block.openWikiPage();
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		block = block.openSpecialBlockPage();
		block.deselectAllSelections();
		block.typeInUserName(Properties.userNameBlocked);
		block.selectExpiration("2 hours");
		block.clickBlockButton();
		login.logOut(driver);
	}

	@Test(groups = {"usersAndRights002", "UsersAndRights"}, dependsOnMethods={"usersAndRights001_Block"})
	public void usersAndRights002_VerifyBlockedUser(){
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		login.logInCookie(Properties.userNameBlocked, Properties.passwordBlocked);
		WikiArticleEditMode edit = article.createNewDefaultArticle();
		edit.verifyBlockedUserMessage();
	}

	@Test(groups = {"usersAndRights003", "UsersAndRights"}, dependsOnMethods={"usersAndRights001_Block"})
	public void usersAndRights003_BlockListBlocked(){
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		SpecialBlockListPageObject list = new SpecialBlockListPageObject(driver);
		list.openWikiPage();
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		list = list.openSpecialBlockListPage();
		list.searchForUser(Properties.userNameBlocked);
		list.verifyUserBlocked(Properties.userNameBlocked);
	}

	@Test(groups = {"usersAndRights004", "UsersAndRights"}, dependsOnMethods={"usersAndRights001_Block"})
	public void usersAndRights004_Unblock(){
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		SpecialUnblockPageObject unblock = new SpecialUnblockPageObject(driver);
		unblock.openWikiPage();
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		unblock = unblock.openSpecialUnblockPage();
		unblock.unblockUser(Properties.userNameBlocked);
		unblock.verifyUnblockMessage(Properties.userNameBlocked);
	}

	@Test(groups = {"usersAndRights005", "UsersAndRights"}, dependsOnMethods={"usersAndRights004_Unblock"})
	public void usersAndRights005_VerifyUnblockedUser(){
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		login.logInCookie(Properties.userNameBlocked, Properties.passwordBlocked);
		WikiArticleEditMode edit = article.createNewDefaultArticle();
		edit.clickOnPublishButton();
	}

	@Test(groups = {"usersAndRights006", "UsersAndRights"}, dependsOnMethods={"usersAndRights004_Unblock"})
	public void usersAndRights006_BlockListUnblocked(){
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		SpecialBlockListPageObject list = new SpecialBlockListPageObject(driver);
		list.openWikiPage();
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		list = list.openSpecialBlockListPage();
		list.searchForUser(Properties.userNameBlocked);
		list.verifyUserUnblocked();
	}

	@Test(groups = {"usersAndRights007", "UsersAndRights"})
	public void usersAndRights007_Contributions(){
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		String pageContent = article.getTimeStamp() + "Special:Contributions test article content";
		article.openWikiPage();
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = article.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.typeInContent(pageContent);
		edit.clickOnPublishButton();
		article.verifyArticleText(pageContent);
		SpecialContributionsPageObject contribution = new SpecialContributionsPageObject(driver);
		contribution = contribution.openContributionsPage();
		contribution.searchContributions(Properties.userNameStaff);
		contribution.verifyNewPageOnList(article.getPageName(), pageContent);
		login.logOut(driver);
	}
}
