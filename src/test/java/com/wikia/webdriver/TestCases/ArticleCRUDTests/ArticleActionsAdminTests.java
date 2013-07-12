package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticleActions.DeleteArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticleActions.RenameArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialRestorePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.testng.annotations.Test;

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
		DeleteArticlePageObject deletePage = article.deleteArticleUsingDropdown();
		deletePage.submitDeletion();
		SpecialRestorePageObject restore = base.undeleteArticle();
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
		RenameArticlePageObject renamePage = article.renameArticleUsingDropdown();
		renamePage.rename(articleNewName);
		article.verifyArticleTitle(articleNewName);
		article.verifyNotificationMessage();
	}
}
