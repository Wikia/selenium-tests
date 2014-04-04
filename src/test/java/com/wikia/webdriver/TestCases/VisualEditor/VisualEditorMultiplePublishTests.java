package com.wikia.webdriver.TestCases.VisualEditor;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Formatting;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorSaveChangesDialog;

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

	@Test(groups = {"VisualEditorMultiplePublish", "VisualEditorMultiplePublish_001"})
	public void VisualEditorMultiplePublish_001() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		String targetText = PageContent.articleText;
		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		article = base.openArticleByName(wikiURL, articleName);
		ve = article.clickVEEditButton();
		article = ve.editAndPublish(wikiURL, articleName, targetText);
		article.verifyFormattingFromVE(Formatting.PARAGRAPH, targetText);
		targetText = PageContent.articleTextEdit + targetText;
		ve = article.clickVEEditButton();
		article = ve.editAndPublish(wikiURL, articleName, PageContent.articleTextEdit);
		article.verifyFormattingFromVE(Formatting.PARAGRAPH, targetText);
		targetText = PageContent.articleTextSecondEdit + targetText;
		ve = article.clickVEEditButton();
		article = ve.editAndPublish(wikiURL, articleName, PageContent.articleTextSecondEdit);
		article.verifyFormattingFromVE(Formatting.PARAGRAPH, targetText);
	}
}
