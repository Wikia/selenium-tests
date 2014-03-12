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
 * VE-888 Verify VE is able to handle multiple edit on the same article in one session
 */

public class VisualEditorMultiplePublishTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"VisualEditorMultiplePublish", "VisualEditorMultiplePublish_001"})
	public void VisualEditorMultiplePublish_001() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		String targetText = PageContent.articleText;
		String articleName = PageContent.articleNamePrefix + ve.getTimeStamp();
		ve.gotoArticleEditModeVisual(wikiURL,articleName);
		ve.typeTextArea(PageContent.articleText);
		VisualEditorSaveChangesDialog save = ve.clickPublishButton();
		ArticlePageObject article = save.savePage();
		article.verifyFormattingFromVE(Formatting.PARAGRAPH, targetText);
		targetText = PageContent.articleTextEdit + targetText;
		ve.gotoArticleEditModeVisual(wikiURL,articleName);
		ve.typeTextArea(PageContent.articleTextEdit);
		save = ve.clickPublishButton();
		article = save.savePage();
		article.verifyFormattingFromVE(Formatting.PARAGRAPH, targetText);
		targetText = PageContent.articleTextSecondEdit + targetText;
		ve.gotoArticleEditModeVisual(wikiURL,articleName);
		ve.typeTextArea(PageContent.articleTextSecondEdit);
		save = ve.clickPublishButton();
		article = save.savePage();
		article.verifyFormattingFromVE(Formatting.PARAGRAPH, targetText);
	}
}
