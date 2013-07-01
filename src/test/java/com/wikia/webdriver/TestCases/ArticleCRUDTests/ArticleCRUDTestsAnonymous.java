package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.DataProvider.ArticleDataProvider;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import org.testng.annotations.Test;

public class ArticleCRUDTestsAnonymous extends TestTemplate{

	@Test(groups={"ArticleCRUDAnon_001", "ArticleCRUDAnonymous"})
	public void ArticleCRUDAnon_001_VerifyEditDropDown()
	{
		WikiBasePageObject wiki =  new WikiBasePageObject(driver);
		wiki.openWikiPage();
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		wiki.openRandomArticleByUrl();
		wiki.clickEditDropDown();
		wiki.verifyEditDropDownAnonymous();
	}

	@Test (
		dataProviderClass=ArticleDataProvider.class,
		dataProvider="getArticleName",
		groups={"ArticleCRUDAnon_002", "ArticleCRUDAnonymous"}
	)
	public void ArticleCRUDAnon_002_CreateArticle(String articleName)
	{
		WikiBasePageObject wiki =  new WikiBasePageObject(driver);
		wiki.openWikiPage();
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		articleName += article.getTimeStamp();
		WikiArticleEditMode edit = article.createNewArticle(articleName, 1);
		edit.deleteArticleContent();
		edit.typeInContent(PageContent.articleText);
		edit.clickOnPublishButton();
		article.verifyPageTitle(articleName);
		article.verifyArticleText(PageContent.articleText);
	}

	@Test(groups={"ArticleCRUDAnon_003", "ArticleCRUDAnonymous"})
	public void ArticleCRUDAnon_003_CreateEditArticle()
	{
		WikiBasePageObject wiki =  new WikiBasePageObject(driver);
		wiki.openWikiPage();
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		String articleTitle = article.getPageName();
		WikiArticleEditMode edit = wiki.clickEditButton();
		String articleNewContent = PageContent.articleTextEdit + wiki.getTimeStamp();
		edit.deleteArticleContent();
		edit.typeInContent(articleNewContent);
		edit.clickOnPublishButton();
		article.verifyPageTitle(articleTitle);
		article.verifyArticleText(articleNewContent);
	}

	@Test(groups={"ArticleCRUDAnon_004", "ArticleCRUDAnonymous"})
	public void ArticleCRUDAnon_004_CreateArticleComment()
	{
		WikiBasePageObject wiki =  new WikiBasePageObject(driver);
		wiki.openWikiPage();
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		article.openRandomArticleByUrl();
		article.triggerCommentArea();
		article.writeOnCommentArea(PageContent.commentText);
		article.clickSubmitButton();
		article.verifyCommentText(PageContent.commentText, PageContent.wikiaContributor);
	}

	@Test(groups={"ArticleCRUDAnon_005", "ArticleCRUDAnonymous"})
	public void ArticleCRUDAnon_005_CreateArticleCommentReply()
	{
		WikiBasePageObject wiki =  new WikiBasePageObject(driver);
		wiki.openWikiPage();
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		article.triggerCommentArea();
		article.writeOnCommentArea(PageContent.commentText);
		article.clickSubmitButton();
		article.verifyCommentText(PageContent.commentText, PageContent.wikiaContributor);
		article.replyComment(PageContent.commentText, PageContent.replyText);
	}

	@Test(groups={"ArticleCRUDAnon_006", "ArticleCRUDAnonymous"})
	public void ArticleCRUDAnon_006_verifySpotlightsPresence()
	{
		WikiBasePageObject wiki =  new WikiBasePageObject(driver);
		wiki.openWikiPage();
		WikiArticlePageObject article = wiki.openRandomArticleByUrl();
		article.verifySpotlightsPresence();
	}
}
