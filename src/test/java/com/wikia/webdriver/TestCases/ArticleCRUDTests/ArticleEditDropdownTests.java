package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.testng.annotations.Test;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class ArticleEditDropdownTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(
		groups = {"ArticleEditDropdown_001", "ArticleEditDropdown"}
	)
	public void ArticleEditDropdown_001_admin() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.loginAndVerifyOnWiki(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		ArticlePageObject article = login.openRandomArticle(wikiURL);
		article.verifyDropdownForAdmin();
	}

	@Test(
		groups = {"ArticleEditDropdown_002", "ArticleEditDropdown"}
	)
	public void ArticleEditDropdown_002_user() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.loginAndVerifyOnWiki(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = login.openRandomArticle(wikiURL);
		article.verifyDropdownForUser();
	}
	@Test(
		groups = {"ArticleEditDropdown_003", "ArticleEditDropdown"}
	)
	public void ArticleEditDropdown_003_anon() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		article.verifyDropdownForAnon();
	}
}
