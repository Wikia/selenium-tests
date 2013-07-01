package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

public class ArticleCRUDTestsAnonymous extends TestTemplate{
			
	/*
	 * TestCase001
	 * Open random wiki page as anonymous user
	 * Click edit drop-down
	 * Verify available edit options for anonymous user (history item)
	 */
	@Test(groups={"ArticleCRUDAnon_001", "ArticleCRUDAnonymous"})
	public void ArticleCRUDAnon_001_VerifyEditDropDown()
	{
		CommonFunctions.logOut(driver);
		WikiArticlePageObject wiki = new WikiArticlePageObject(driver);
		wiki.openRandomArticleByUrl();
		wiki.clickEditDropDown();
		wiki.verifyEditDropDownAnonymous();
	}
	
	
	/*
	 * TestCase002
	 * Create article as admin user with following names:
	 * 	normal: QAarticle
	 * 	non-latin: 這是文章的名字在中國
	 * 	long: QAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleName
	 * 	with slash: QA/article
	 * 	with underscore: QA_article
	 * 	made from digits:123123123123
	 * Delete article
	 */
			
	@Test(dataProvider="getArticleName", groups={"ArticleCRUDAnon_002", "ArticleCRUDAnonymous"})
	public void ArticleCRUDAnon_002_CreateArticle(String articleName)
	{
		CommonFunctions.logOut(driver);
		WikiArticlePageObject wiki = new WikiArticlePageObject(driver);
		wiki.openWikiPage();
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.typeInContent(PageContent.articleText);
		edit.clickOnPublishButton();
		wiki.verifyPageTitle(wiki.getPageName());
		wiki.verifyArticleText(PageContent.articleText);
	}
	/*
	 * TestCase005
	 * Create article as admin user
	 * Edit article
	 * Delete article
	 */
	@Test(groups={"ArticleCRUDAnon_003", "ArticleCRUDAnonymous"})
	public void ArticleCRUDAnon_003_CreateEditArticle()
	{
		CommonFunctions.logOut(driver);
		WikiArticlePageObject wiki = new WikiArticlePageObject(driver);
		wiki.openWikiPage();
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.typeInContent(PageContent.articleText);
		edit.clickOnPublishButton();
		wiki.verifyPageTitle(wiki.getPageName());
		wiki.verifyArticleText(PageContent.articleText);
		wiki.clickEditButton();
		edit.deleteArticleContent();
		edit.typeInContent(PageContent.articleTextEdit);
		edit.clickOnPublishButton();
		wiki.verifyPageTitle(wiki.getPageName());
		wiki.verifyArticleText(PageContent.articleTextEdit);
	}
	
	/* 
	 * TestCase006
	 * Add article as admin
	 * Add comment
	 * Delete comment
	 * Delete article
	 */
	@Test(groups={"ArticleCRUDAnon_004", "ArticleCRUDAnonymous"})
	public void ArticleCRUDAnon_004_CreateArticleComment()
	{
		CommonFunctions.logOut(driver);
		WikiArticlePageObject wiki = new WikiArticlePageObject(driver);
		wiki.openWikiPage();
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.typeInContent(PageContent.articleText);
		edit.clickOnPublishButton();
		wiki.verifyPageTitle(wiki.getPageName());
		wiki.triggerCommentArea();
		wiki.writeOnCommentArea(PageContent.commentText);
		wiki.clickSubmitButton();
		wiki.verifyCommentText(PageContent.commentText, PageContent.wikiaContributor);
	}
	
	/* 
	 * TestCase006
	 * Add article as admin
	 * Add comment
	 * Delete comment
	 * Delete article
	 */
	@Test(groups={"ArticleCRUDAnon_005", "ArticleCRUDAnonymous"})
	public void ArticleCRUDAnon_005_CreateArticleCommentReply()
	{
		CommonFunctions.logOut(driver);
		WikiArticlePageObject wiki = new WikiArticlePageObject(driver);	
		wiki.openWikiPage();
		WikiArticleEditMode edit = wiki.createNewDefaultArticle();
		edit.deleteArticleContent();
		edit.typeInContent(PageContent.articleText);
		edit.clickOnPublishButton();
		wiki.verifyPageTitle(wiki.getPageName());
		wiki.triggerCommentArea();
		wiki.writeOnCommentArea(PageContent.commentText);
		wiki.clickSubmitButton();
		wiki.verifyCommentText(PageContent.commentText, PageContent.wikiaContributor);
		wiki.replyComment(PageContent.commentText, PageContent.replyText);
	}	
	
	/**
	 * creation reason: Jira QAART-64
	 * @author Michal 'justnpT' Nowierski
	 * 
	 */
	@Test(groups={"ArticleCRUDAnon_006", "ArticleCRUDAnonymous"})
	public void ArticleCRUDAnon_006_verifySpotlightsPresence()
	{
		CommonFunctions.logOut(driver);
		WikiArticlePageObject article = new WikiArticlePageObject(driver);	
		article.openRandomArticleByUrl();
		article.verifySpotlightsPresence();
	}
	
	@DataProvider
	private static final Object[][] getArticleName()
	{
		return new Object[][]
				{
					{"QAarticle"},
					{"QAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleName"},
					{"QA/article"},
					{"QA_article"},
					{"123123123123"}
				};
	}
}
