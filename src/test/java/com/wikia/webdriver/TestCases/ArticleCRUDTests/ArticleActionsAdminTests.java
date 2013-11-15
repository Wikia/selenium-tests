package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Actions.DeletePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Actions.RenamePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialRestorePageObject;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class ArticleActionsAdminTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups={"ArticleActionsAdmin_001", "ArticleActionsAdmin"})
	public void ArticleActionsAdmin_001_deleteUndelete() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		String articleName = article.getArticleName();
		DeletePageObject deletePage = article.deleteUsingDropdown();
		deletePage.submitDeletion();
		SpecialRestorePageObject restore = base.undeleteByFlashMessage();
		restore.verifyArticleName(articleName);
		restore.giveReason(article.getTimeStamp());
		restore.restorePage();
		article.verifyNotificationMessage();
		article.verifyArticleTitle(articleName);
	}

	@Test(groups={"ArticleActionsAdmin_002", "ArticleActionsAdmin"})
	public void ArticleActionsAdmin_002_move() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		String articleNewName = PageContent.articleNamePrefix + article.getTimeStamp();
		RenamePageObject renamePage = article.renameUsingDropdown();
		renamePage.rename(articleNewName);
		article.verifyArticleTitle(articleNewName);
		article.verifyNotificationMessage();
	}
}
