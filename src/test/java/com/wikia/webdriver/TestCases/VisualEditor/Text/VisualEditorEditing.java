package com.wikia.webdriver.TestCases.VisualEditor.Text;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Formatting;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Style;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'rochan' Chan
 * @ownership Contribution
 *
 */

public class VisualEditorEditing extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;

	private String text = PageContent.articleText;

	@BeforeClass(alwaysRun = true)
	public void setup() {
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
	}

	@Test(
			groups = {"VisualEditorEditing", "VisualEditorEditing_001"}
	)
	public void VisualEditorFormatting_001() {

		ArticlePageObject article =
			base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		for (Formatting format : Formatting.values()){
			PageObjectLogging.log("Formatting selection", format.toString() + " selected", true);
			ve.selectFormatting(format);
			ve.typeTextArea(text);
			ve.typeReturn();
		}

		for (Style style : Style.values()) {
			PageObjectLogging.log("Style selection", style.toString() + " selected", true);
			ve.selectStyle(style);
			ve.typeTextArea(text);
			ve.typeReturn();
		}
//		ve.verifyFormatting(format, text);
		VisualEditorSaveChangesDialog save = ve.clickPublishButton();
//		ArticlePageObject article = save.savePage();
//		article.verifyFormattingFromVE(format, text);
	}
}
