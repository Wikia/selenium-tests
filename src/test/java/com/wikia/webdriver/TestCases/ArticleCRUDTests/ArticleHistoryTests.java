package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticleRevisionEditMode;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiHistoryPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

public class ArticleHistoryTests extends TestTemplate
{
	
	@Test(groups={"ArticleHistoryTests_001", "ArticleCRUDAdmin"})
	public void ArticleHistoryTests_001_RecoverPreviousVersion()
	{
		CommonFunctions.logOut(driver);
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.clickOnVisualButton();
		edit.typeInContent(PageContent.articleText);
		WikiArticlePageObject article = edit.clickOnPublishButton();
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
		CommonFunctions.logOut(driver);
	}
	
	@Test(groups={"ArticleHistoryTests_002", "ArticleCRUDAdmin"})
	public void ArticleHistoryTests_002_RollbackVersion()
	{
		CommonFunctions.logOut(driver);
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);		
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);	
		wiki.openWikiPage();
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.clickOnVisualButton();
		edit.typeInContent(PageContent.articleText);
		WikiArticlePageObject article = edit.clickOnPublishButton();
		article.verifyPageTitle(article.getPageName());
		article.verifyArticleText(PageContent.articleText);
		CommonFunctions.logOut(driver);
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
		article.openArticle(article.getPageName());
		edit = article.edit();
		edit.deleteArticleContent();
		edit.clickOnVisualButton();
		edit.typeInContent(PageContent.articleTextEdit);
		article = edit.clickOnPublishButton();
		article.verifyPageTitle(article.getPageName());
		article.verifyArticleText(PageContent.articleTextEdit);
		CommonFunctions.logOut(driver);
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
		CommonFunctions.logOut(driver);
	}
}
