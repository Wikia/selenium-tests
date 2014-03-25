package com.wikia.webdriver.TestCases.SpecialPagesTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialContributionsPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Block.SpecialBlockListPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Block.SpecialBlockPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Block.SpecialUnblockPageObject;

public class UserAndRights extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"usersAndRights001", "UsersAndRights"})
	public void usersAndRights001_Block() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		SpecialBlockPageObject block  = base.openSpecialBlockPage(wikiURL);
		block.deselectAllSelections();
		block.typeInUserName(credentials.userNameBlocked);
		block.selectExpiration("2 hours");
		block.clickBlockButton();
	}

	@Test(groups = {"usersAndRights002", "UsersAndRights"}, dependsOnMethods={"usersAndRights001_Block"})
	public void usersAndRights002_VerifyBlockedUser() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameBlocked, credentials.passwordBlocked, wikiURL);
		VisualEditModePageObject edit = base.goToArticleDefaultContentEditPage(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		edit.verifyBlockedUserMessage();
	}

	@Test(groups = {"usersAndRights003", "UsersAndRights"}, dependsOnMethods={"usersAndRights001_Block"})
	public void usersAndRights003_BlockListBlocked() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		SpecialBlockListPageObject list = base.openSpecialBlockListPage(wikiURL);
		list.searchForUser(credentials.userNameBlocked);
		list.verifyUserBlocked(credentials.userNameBlocked);
	}

	@Test(groups = {"usersAndRights004", "UsersAndRights"}, dependsOnMethods={"usersAndRights001_Block"})
	public void usersAndRights004_Unblock() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		SpecialUnblockPageObject unblock = base.openSpecialUnblockPage(wikiURL);
		unblock.unblockUser(credentials.userNameBlocked);
		unblock.verifyUnblockMessage(credentials.userNameBlocked);
	}

	@Test(groups = {"usersAndRights005", "UsersAndRights"}, dependsOnMethods={"usersAndRights004_Unblock"})
	public void usersAndRights005_VerifyUnblockedUser() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameBlocked, credentials.passwordBlocked, wikiURL);
		String title = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditModePageObject edit = base.navigateToArticleEditPage(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		ArticlePageObject article = edit.submitArticle();
		article.verifyArticleTitle(title);
	}

	@Test(groups = {"usersAndRights006", "UsersAndRights"}, dependsOnMethods={"usersAndRights004_Unblock"})
	public void usersAndRights006_BlockListUnblocked() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameBlocked, credentials.passwordBlocked, wikiURL);
		SpecialBlockListPageObject list = base.openSpecialBlockListPage(wikiURL);
		list.searchForUser(credentials.userNameBlocked);
		list.verifyUserUnblocked();
	}

	@Test(groups = {"usersAndRights007", "UsersAndRights"})
	public void usersAndRights007_Contributions(){
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		String pageContent = base.getTimeStamp();
		String pageName = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditModePageObject edit = base.navigateToArticleEditPage(wikiURL, pageName);
		edit.clearContent();
		edit.addContent(pageContent);
		ArticlePageObject article = edit.submitArticle();
		article.verifyContent(pageContent);
		SpecialContributionsPageObject contribution = base.openContributionsPage(wikiURL);
		contribution.searchContributions(credentials.userNameStaff);
		contribution.verifyNewPageOnList(pageName, pageContent);
	}
}
