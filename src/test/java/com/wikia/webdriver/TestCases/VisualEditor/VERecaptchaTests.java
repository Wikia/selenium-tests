package com.wikia.webdriver.TestCases.VisualEditor;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'Rochan' Chan
 * @ownership Contribution
 *
 * VE-1233 Testing Anon adding external link would prompt recaptcha
 *
 */

public class VERecaptchaTests extends NewTestTemplateBeforeClass {

	WikiBasePageObject base;

	@BeforeMethod(alwaysRun = true)
	public void setupd() {
		base = new WikiBasePageObject(driver);
	}

	@Test(
		groups = {"VERecaptchaTests", "VEAddRecaptcha_001", "VEAnon"}
	)
	public void VEAddRecaptchaTests_001_AddExternalURL() {
		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		ArticlePageObject article =
			base.openArticleByName(wikiURL, articleName);
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.typeTextArea(URLsContent.externalURL);
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		saveDialog = saveDialog.clickSaveWithRecaptcha();
		saveDialog.verifyRecaptchaImageSrc();
		String firstImgSrc = saveDialog.getRecaptchaImageSrc();
		saveDialog = saveDialog.clickSaveWithRecaptcha();
		saveDialog.verifyRecaptchaIsVisible();
		saveDialog.verifyRecaptchaImageSrc();
		saveDialog.verifyIsNewRecaptcha(firstImgSrc);
	}
}
