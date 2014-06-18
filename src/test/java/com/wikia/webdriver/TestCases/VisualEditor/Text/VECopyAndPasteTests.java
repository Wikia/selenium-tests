package com.wikia.webdriver.TestCases.VisualEditor.Text;

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

public class VECopyAndPasteTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;

	@BeforeMethod(alwaysRun = true)
	public void setupd() {
		base = new WikiBasePageObject(driver);
	}

	@Test(
		groups = {"VECopyAndPasteTests", "VECopyAndPasteTests_001"}
	)
	public void VECopyAndPasteTests_001_copyAndPaste() throws InterruptedException {
		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		ArticlePageObject article =
			base.openArticleByName(wikiURL, articleName);
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		String text = PageContent.articleText;
		ve.typeTextArea(text);
		ve.copyAndPaste();
		ve.verifyFormatting(Formatting.PARAGRAPH, text + text);
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		article = saveDialog.savePage();
		article.verifyFormattingFromVE(Formatting.PARAGRAPH, text + text);
	}
}
