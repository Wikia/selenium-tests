package com.wikia.webdriver.testcases.visualeditor.text;

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
 * @ownership Contribution
 *
 * VE-1210 Verify VE is able to perform Copy and Paste text on the edit area
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
