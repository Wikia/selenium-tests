package com.wikia.webdriver.TestCases.VisualEditor;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Formatting;
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

public class VisualEditorFormattingTests extends NewTestTemplateBeforeClass {

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
		PageObjectLogging.log("Formatting selection", format.toString() + " selected", true);
		ve.navigateToArticleEditModeVisual(
			wikiURL,
			PageContent.articleNamePrefix + ve.getTimeStamp()
		);
		ve.selectFormatting(format);
		ve.typeTextArea(text);
		ve.verifyFormatting(format, text);
		VisualEditorSaveChangesDialog save = ve.clickPublishButton();
		ArticlePageObject article = save.savePage();
		article.verifyFormattingFromVE(format, text);
	}
}
