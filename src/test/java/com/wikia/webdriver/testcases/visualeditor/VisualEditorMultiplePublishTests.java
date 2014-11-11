package com.wikia.webdriver.testcases.visualeditor;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Formatting;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

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
		String targetText = PageContent.articleText;
		String articleName = base.getNameForArticle();
		article = base.openArticleByName(wikiURL, articleName);
		ve = article.navigateToArticleEditModeVisual(wikiURL, articleName);
		article = ve.clickVEEditAndPublish(targetText);
		article.verifyFormattingFromVE(Formatting.PARAGRAPH, targetText);
		targetText = PageContent.articleTextEdit + targetText;
		ve = article.navigateToArticleEditModeVisual(wikiURL, articleName);
		article = ve.clickVEEditAndPublish(PageContent.articleTextEdit);
		article.verifyFormattingFromVE(Formatting.PARAGRAPH, targetText);
		targetText = PageContent.articleTextSecondEdit + targetText;
		ve = article.navigateToArticleEditModeVisual(wikiURL, articleName);
		article = ve.clickVEEditAndPublish(PageContent.articleTextSecondEdit);
		article.verifyFormattingFromVE(Formatting.PARAGRAPH, targetText);
	}
}
