package com.wikia.webdriver.TestCases.VisualEditor;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Style;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 * @author Robert 'rochan' Chan
 * Verify bold formatting
 * Verify italic formatting
 * Verify strikethrough formatting
 * Verify underline formatting
 * Verify subscript formatting
 * Verify superscript formatting
 */
public class VisualEditorStylesTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();

	VisualEditorPageObject ve;

	private String text = PageContent.articleText;

	@BeforeClass(alwaysRun = true)
	public void setup() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ve = new VisualEditorPageObject(driver);
	}

	@Test(groups = {"VisualEditorStylesFullText", "VisualEditorStyles_001"},
			dataProviderClass = VisualEditorDataProvider.class,
			dataProvider = "getStyles")
	public void VisualEditorStyles_001_FullText(Style style) {
		PageObjectLogging.log("Style selection", style.toString() + " selected", true);
		ve.openNewArticleEditModeVisual(wikiURL);
		ve.typeTextArea(" ");
		ve.selectStyle(style);
		ve.typeTextArea(text);
		ve.verifyStyle(style, text);
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		ArticlePageObject article = saveDialog.savePage();
		article.verifyStyleFromVE(style, text);
	}

	@Test(groups = {"VisualEditorStylesSelectedText", "VisualEditorStyles_002"},
			dataProviderClass = VisualEditorDataProvider.class,
			dataProvider = "getStyles")
	public void VisualEditorStyles_002_SelectedText(Style style) {
		PageObjectLogging.log("Style selection", style.toString() + " selected", true);
		ve.openNewArticleEditModeVisual(wikiURL);
		ve.typeTextArea(text);

		String selectText = text.substring(12, 17);

		ve.selectText(selectText);
		ve.selectStyle(style);
		ve.verifyStyle(style, selectText);
		VisualEditorSaveChangesDialog save = ve.clickPublishButton();
		ArticlePageObject article = save.savePage();
		article.verifyStyleFromVE(style, selectText);
	}
}
