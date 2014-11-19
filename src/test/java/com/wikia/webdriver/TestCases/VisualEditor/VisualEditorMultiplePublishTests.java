package com.wikia.webdriver.TestCases.VisualEditor;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Formatting;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'rochan' Chan
 *
 * VE-888 Verify VE is able to perform multiple publish on the same article in one logged in session
 */

public class VisualEditorMultiplePublishTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	VisualEditorPageObject ve;
	VisualEditorSaveChangesDialog save;
	ArticlePageObject article;
	WikiBasePageObject base;

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
	}

	@Test(groups = {"VisualEditorMultiplePublish", "VisualEditorMultiplePublish_001"})
	public void VisualEditorMultiplePublish_001() {
		String targetText = PageContent.ARTICLE_TEXT;
		String articleName = base.getNameForArticle();
		article = base.openArticleByName(wikiURL, articleName);
		ve = article.navigateToArticleEditModeVisual(wikiURL, articleName);
		article = ve.clickVEEditAndPublish(targetText);
		article.verifyFormattingFromVE(Formatting.PARAGRAPH, targetText);
		targetText = PageContent.ARTICLE_TEXT_EDIT + targetText;
		ve = article.navigateToArticleEditModeVisual(wikiURL, articleName);
		article = ve.clickVEEditAndPublish(PageContent.ARTICLE_TEXT_EDIT);
		article.verifyFormattingFromVE(Formatting.PARAGRAPH, targetText);
		targetText = PageContent.ARTICLE_TEXT_SECOND_EDIT + targetText;
		ve = article.navigateToArticleEditModeVisual(wikiURL, articleName);
		article = ve.clickVEEditAndPublish(PageContent.ARTICLE_TEXT_SECOND_EDIT);
		article.verifyFormattingFromVE(Formatting.PARAGRAPH, targetText);
	}
}
