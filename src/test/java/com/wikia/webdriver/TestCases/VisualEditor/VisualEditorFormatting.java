package com.wikia.webdriver.TestCases.VisualEditor;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Formatting;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorSaveChangesDialog;

/**
 * @author Karol 'kkarolk' Kujawiak
 * https://wikia-inc.atlassian.net/browse/QAART-241
 * Verify paragraph formatting
 * Verify heading formatting
 * Verify sub-heading 1 formatting
 * Verify sub-heading 2 formatting
 * Verify sub-heading 3 formatting
 * Verify sub-heading 4 formatting
 * Verify preformatted formatting
 * Verify page title formatting
 */

public class VisualEditorFormatting extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();

	VisualEditorPageObject ve;

	private String text = PageContent.articleText;

	@BeforeClass(alwaysRun = true)
	public void setup() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ve = new VisualEditorPageObject(driver);
	}

	@Test(
			groups = {"VisualEditorFormatting", "VisualEditorFormatting_001"},
			dataProviderClass = VisualEditorDataProvider.class,
			dataProvider = "getFormatting"
	)
	public void VisualEditorFormatting_001(Formatting format) {
		ve.gotoArticleEditModeVisual(wikiURL, ve.getTimeStamp());
		ve.selectFormatting(format);
		ve.write(text);
		ve.verifyFormatting(format, text);
		VisualEditorSaveChangesDialog save = ve.savePage();
		ArticlePageObject article = save.savePage();
		article.refreshPage();
		article.verifyFormatting(format, text);
	}
}
