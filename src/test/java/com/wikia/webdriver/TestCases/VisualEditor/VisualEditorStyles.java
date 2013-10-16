package com.wikia.webdriver.TestCases.VisualEditor;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Style;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorSaveChangesDialog;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class VisualEditorStyles extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();

	VisualEditorPageObject ve;

	private String text = PageContent.articleText;

	@BeforeClass(alwaysRun = true)
	public void setup() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ve = new VisualEditorPageObject(driver);
	}

	@Test(groups = {"VisualEditorStyles", "VisualEditorStyles_001"},
			dataProviderClass = VisualEditorDataProvider.class,
			dataProvider = "getStyles")
	public void VisualEditorStyles_001_FullText(Style style) {
		ve.gotoArticleEditModeVisual(wikiURL, ve.getTimeStamp());
		ve.selectStyle(style);
		ve.write(text);
		ve.verifyStyle(style, text);
		VisualEditorSaveChangesDialog save = ve.savePage();
		ArticlePageObject article = save.savePage();
		article.verifyStyle(style, text);
	}

	@Test(groups = {"VisualEditorStyles", "VisualEditorStyles_002"},
			dataProviderClass = VisualEditorDataProvider.class,
			dataProvider = "getStyles")
	public void VisualEditorStyles_002_SelectedText(Style style) {
		ve.gotoArticleEditModeVisual(wikiURL, ve.getTimeStamp());
		ve.write(text);

		String selectText = text.substring(12, 17);

		ve.selectText(selectText);
		ve.selectStyle(style);
		ve.verifyStyle(style, selectText);
		VisualEditorSaveChangesDialog save = ve.savePage();
		ArticlePageObject article = save.savePage();
		article.verifyStyle(style, selectText);
	}
}
