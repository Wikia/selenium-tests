package com.wikia.webdriver.TestCases.SpecialPagesTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialContributionsPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Block.SpecialBlockListPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Block.SpecialBlockPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Block.SpecialUnblockPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Blog.SpecialCreateBlogPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

public class UserAndRights extends TestTemplate{
	
	@Test(groups = {"usersAndRights001", "UsersAndRights"})
	public void usersAndRights001_Block(){
		CommonFunctions.logOut(driver);
		SpecialBlockPageObject block = new SpecialBlockPageObject(driver);
		block.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		block = block.openSpecialBlockPage();
		block.deselectAllSelections();
		block.typeInUserName(Properties.userNameBlocked);
		block.selectExpiration("2 hours");
		block.clickBlockButton();
		CommonFunctions.logOut(driver);
	}
	
	@Test(groups = {"usersAndRights002", "UsersAndRights"}, dependsOnMethods={"usersAndRights001_Block"})
	public void usersAndRights002_VerifyBlockedUser(){
		CommonFunctions.logOut(driver);
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameBlocked, Properties.passwordBlocked);
		WikiArticleEditMode edit = article.createNewDefaultArticle();
		edit.verifyBlockedUserMessage();
	}
	
	@Test(groups = {"usersAndRights003", "UsersAndRights"}, dependsOnMethods={"usersAndRights001_Block"})
	public void usersAndRights003_BlockListBlocked(){
		CommonFunctions.logOut(driver);
		SpecialBlockListPageObject list = new SpecialBlockListPageObject(driver);
		list.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		list = list.openSpecialBlockListPage();
		list.searchForUser(Properties.userNameBlocked);
		list.verifyUserBlocked(Properties.userNameBlocked);
	}
	
//	public void 
	
	@Test(groups = {"usersAndRights004", "UsersAndRights"}, dependsOnMethods={"usersAndRights001_Block"})
	public void usersAndRights004_Unblock(){
		CommonFunctions.logOut(driver);
		SpecialUnblockPageObject unblock = new SpecialUnblockPageObject(driver);
		unblock.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		unblock = unblock.openSpecialUnblockPage();
		unblock.unblockUser(Properties.userNameBlocked);
		unblock.verifyUnblockMessage(Properties.userNameBlocked);
	}
	
	@Test(groups = {"usersAndRights005", "UsersAndRights"}, dependsOnMethods={"usersAndRights004_Unblock"})
	public void usersAndRights005_VerifyUnblockedUser(){
		CommonFunctions.logOut(driver);
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameBlocked, Properties.passwordBlocked);
		WikiArticleEditMode edit = article.createNewDefaultArticle();
		edit.clickOnPublishButton();
	}
	
	@Test(groups = {"usersAndRights006", "UsersAndRights"}, dependsOnMethods={"usersAndRights004_Unblock"})
	public void usersAndRights006_BlockListUnblocked(){
		CommonFunctions.logOut(driver);
		SpecialBlockListPageObject list = new SpecialBlockListPageObject(driver);
		list.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		list = list.openSpecialBlockListPage();
		list.searchForUser(Properties.userNameBlocked);
		list.verifyUserUnblocked();
	}

	@Test(groups = {"usersAndRights007", "UsersAndRights"})
	public void usersAndRights007_Contributions(){
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		String pageContent = wiki.getTimeStamp() + "Special:Contributions test article content";
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.typeInContent(pageContent);
		WikiArticlePageObject article = edit.clickOnPublishButton();
		SpecialContributionsPageObject contribution = new SpecialContributionsPageObject(driver);
		contribution = contribution.openContributionsPage();
		contribution.searchContributions(Properties.userNameStaff);
		contribution.verifyNewPageOnList(article.getPageName(), pageContent);
		CommonFunctions.logOut(driver);
	}
}
