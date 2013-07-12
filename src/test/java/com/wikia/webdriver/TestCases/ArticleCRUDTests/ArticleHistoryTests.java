package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticleRevisionEditMode;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiHistoryPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

public class ArticleHistoryTests extends TestTemplate
{
	/**
	 * Verify if undo button works properly: create article with content, then change that content and at the end undo changes using history. Check if undo changes is succesful
	 */
	@Test(groups={"ArticleHistoryTests_001", "ArticleCRUDAdmin"})
	public void ArticleHistoryTests_001_RecoverPreviousVersion()
	{
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		WikiArticleEditMode edit = article.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.clickOnVisualButton();
		edit.typeInContent(PageContent.articleText);
		edit.clickOnPublishButton();
		article.verifyPageTitle(article.getPageName());
		article.verifyArticleText(PageContent.articleText);
		edit = article.edit();
		edit.deleteArticleContent();
		edit.clickOnVisualButton();
		edit.typeInContent(PageContent.articleTextEdit);
		article = edit.clickOnPublishButton();
		article.verifyPageTitle(article.getPageName());
		article.verifyArticleText(PageContent.articleTextEdit);
		WikiHistoryPageObject history = article.openHistoryPage();
		WikiArticleRevisionEditMode revision = history.clickUndoRevision(1);
		article = revision.clickOnPublishButton();
		article.verifyTitle(article.getPageName());
		article.verifyArticleText(PageContent.articleText);
		article.deleteArticle(article.getPageName());
		article.openArticle(article.getPageName());
		article.verifyDeletedArticlePage(article.getPageName());
		login.logOut(driver);
	}

	/**
	 * only STAFF and admins can use rollback button
	 * Rollback changes of user and verify: create article with content, then change that content and at the end undo changes using history. Check if undo changes is succesful
	 */
	@Test(groups={"ArticleHistoryTests_002", "ArticleCRUDAdmin"})
	public void ArticleHistoryTests_002_RollbackVersion()
	{
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		WikiArticleEditMode edit = article.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.clickOnVisualButton();
		edit.typeInContent(PageContent.articleText);
		edit.clickOnPublishButton();
		article.verifyPageTitle(article.getPageName());
		article.verifyArticleText(PageContent.articleText);
		login.logOut(driver);
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
		article.openArticle(article.getPageName());
		edit = article.edit();
		edit.deleteArticleContent();
		edit.clickOnVisualButton();
		edit.typeInContent(PageContent.articleTextEdit);
		article = edit.clickOnPublishButton();
		article.verifyPageTitle(article.getPageName());
		article.verifyArticleText(PageContent.articleTextEdit);
		login.logOut(driver);
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
		article.openArticle(article.getPageName());
		WikiHistoryPageObject history = article.openHistoryPage();
		history.rollbackPage();
		article = history.enterPageAfterRollback();
		article.verifyTitle(article.getPageName());
		article.verifyArticleText(PageContent.articleText);
		article.deleteArticle(article.getPageName());
		article.openArticle(article.getPageName());
		article.verifyDeletedArticlePage(article.getPageName());
		login.logOut(driver);
	}
}
