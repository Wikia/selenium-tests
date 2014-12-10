package com.wikia.webdriver.testcases.visualeditor;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

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
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.typeTextArea(URLsContent.EXTERNAL_URL);
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
