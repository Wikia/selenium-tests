package com.wikia.webdriver.testcases.articlecrudtests;

import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.RenamePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialRestorePageObject;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class ArticleActionsAdminTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"ArticleActionsAdmin_001", "ArticleActionsAdmin"})
	@UseUnstablePageLoadStrategy
	public void ArticleActionsAdmin_001_deleteUndelete_CON_2014() {
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

	@Test(groups = {"ArticleActionsAdmin_002", "ArticleActionsAdmin"})
	@UseUnstablePageLoadStrategy
	public void ArticleActionsAdmin_002_move_CON_2014() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		String articleNewName = PageContent.ARTICLE_NAME_PREFIX + article.getTimeStamp();
		RenamePageObject renamePage = article.renameUsingDropdown();
		renamePage.rename(articleNewName);
		article.verifyNotificationMessage();
		article.verifyArticleTitle(articleNewName);
	}
}
