package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCreatePagePageObject;


/**
 * @author: Michal 'justnpT' Nowierski
 */
public class ArticleTOCTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	/**
	 * 1. as anon create an article with TOC
	 * 2. verify TOC is present on the article
	 *
	 */
	@Test(
			groups={"ArticleTOCTests", "ArticleTOCTests_001"}
		)
		public void ArticleTOCTests_001_CreateArticleWithTOCasAnon() {
			WikiBasePageObject base = new WikiBasePageObject(driver);
			String articleTitle = PageContent.articleNamePrefix + base.getTimeStamp();
			SpecialCreatePagePageObject specialCreatePage = base.openSpecialCreatePage(wikiURL);
			VisualEditModePageObject visualEditMode = specialCreatePage.populateTitleField(articleTitle);
			visualEditMode.clearContent();
			visualEditMode.appendContent(PageContent.articleWithTOCline01);
			visualEditMode.appendContent(PageContent.articleWithTOCline02);
			visualEditMode.appendContent(PageContent.articleWithTOCline03);
			visualEditMode.appendContent(PageContent.articleWithTOCline04);
			visualEditMode.appendContent(PageContent.articleWithTOCline05);
			visualEditMode.appendContent(PageContent.articleWithTOCline06);
			visualEditMode.appendContent(PageContent.articleWithTOCline07);
			visualEditMode.appendContent(PageContent.articleWithTOCline08);
			ArticlePageObject article  = visualEditMode.submitArticle();
			article.verifyTOCpresent();
		}
}
