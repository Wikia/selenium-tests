package com.wikia.webdriver.testcases.visualeditor;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Formatting;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Robert 'rochan' Chan
 *         <p/>
 *         VE-888 Verify VE is able to perform multiple publish on the same article in one logged in session
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
		ve = article.openVEOnArticle(wikiURL, articleName);
		article = ve.clickVEEditAndPublish(targetText);
		article.verifyFormattingFromVE(Formatting.PARAGRAPH, targetText);
		targetText = PageContent.ARTICLE_TEXT_EDIT + targetText;
		ve = article.openVEOnArticle(wikiURL, articleName);
		article = ve.clickVEEditAndPublish(PageContent.ARTICLE_TEXT_EDIT);
		article.verifyFormattingFromVE(Formatting.PARAGRAPH, targetText);
		targetText = PageContent.ARTICLE_TEXT_SECOND_EDIT + targetText;
		ve = article.openVEOnArticle(wikiURL, articleName);
		article = ve.clickVEEditAndPublish(PageContent.ARTICLE_TEXT_SECOND_EDIT);
		article.verifyFormattingFromVE(Formatting.PARAGRAPH, targetText);
	}
}
