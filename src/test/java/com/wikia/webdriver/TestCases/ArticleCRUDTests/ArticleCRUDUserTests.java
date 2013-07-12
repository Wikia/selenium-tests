package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.DataProvider.ArticleDataProvider;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCreatePagePageObject;
import org.testng.annotations.Test;

/**
 *
 * @author: Bogna 'bognix' Knychała
 */
public class ArticleCRUDUserTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(
		groups={"ArticleCRUDUser", "ArticleCRUDUser_001", "Smoke"}
	)
	public void ArticleCRUDUser_specialPage() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.loginAndVerifyOnWiki(credentials.userName, credentials.password, wikiURL);
		SpecialCreatePagePageObject specialCreatePage = login.openSpecialCreatePage(wikiURL);
		String articleContent = PageContent.articleText;
		String articleTitle = PageContent.articleNamePrefix + specialCreatePage.getTimeStamp();
		VisualEditModePageObject visualEditMode = specialCreatePage.fillTitle(articleTitle);
		visualEditMode.addContent(articleContent);
		ArticlePageObject article  = visualEditMode.submit();
		article.compareContent(articleContent);
		article.compareTitle(articleTitle);
	}

	@Test(
		groups={"ArticleCRUDUser", "ArticleCRUDUser_002", "Smoke"}
	)
	public void ArticleCRUDUser_addByURL() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.loginAndVerifyOnWiki(credentials.userName, credentials.password, wikiURL);
		String articleContent = PageContent.articleText;
		String articleTitle = PageContent.articleNamePrefix + login.getTimeStamp();
		VisualEditModePageObject visualEditMode = login.goToArticleEditPage(wikiURL, articleTitle);
		visualEditMode.addContent(articleContent);
		ArticlePageObject article  = visualEditMode.submit();
		article.compareContent(articleContent);
		article.compareTitle(articleTitle);
	}

	@Test(
		groups = {"ArticleCRUDUser", "ArticleCRUDUser_003", "Smoke"}
	)
	public void ArticleCRUDUser_addDropdown() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.loginAndVerifyOnWiki(credentials.userName, credentials.password, wikiURL);
		String articleContent = PageContent.articleText;
		String articleTitle = PageContent.articleNamePrefix + login.getTimeStamp();
		ArticlePageObject article = login.openRandomArticleOnWiki(wikiURL);
		VisualEditModePageObject visualEditMode = article.createArticleUsingDropdown(articleTitle);
		visualEditMode.addContent(articleContent);
		visualEditMode.submit();
		article.compareContent(articleContent);
		article.compareTitle(articleTitle);
	}

	@Test(
		dataProviderClass = ArticleDataProvider.class,
		dataProvider = "articleTitles",
		groups = {"ArticleCRUDUser", "ArticleCRUDUser_004"}
	)
	public void ArticleCRUDUser_differentTitles(String articleTitle) {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.loginAndVerifyOnWiki(credentials.userName, credentials.password, wikiURL);
		String articleContent = PageContent.articleText;
		articleTitle = articleTitle + login.getTimeStamp();
		VisualEditModePageObject visualEditMode = login.goToArticleEditPage(wikiURL, articleTitle);
		visualEditMode.addContent(articleContent);
		ArticlePageObject article  = visualEditMode.submit();
		article.compareContent(articleContent);
		article.compareTitle(articleTitle);
	}

	@Test(
		groups = {"ArticleCRUDUser", "ArticleCRUDUser_005"}
	)
	public void ArticleCRUDUser_editByURL() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.loginAndVerifyOnWiki(credentials.userName, credentials.password, wikiURL);
		String articleContent = PageContent.articleText;
		ArticlePageObject article = login.openRandomArticleOnWiki(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.addContent(articleContent);
		visualEditMode.submit();
		article.compareContent(articleContent);
	}

	@Test(
		groups = {"ArticleCRUDUser", "ArticleCRUDUser_006"}
	)
	public void ArticleCRUDUser_editDropdown() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.loginAndVerifyOnWiki(credentials.userName, credentials.password, wikiURL);
		String articleContent = PageContent.articleText;
		ArticlePageObject article = login.openRandomArticleOnWiki(wikiURL);
		VisualEditModePageObject visualEditMode = article.editArticleUsingDropdown();
		visualEditMode.addContent(articleContent);
		visualEditMode.submit();
		article.compareContent(articleContent);
	}
}
