package com.wikia.webdriver.testcases.visualeditor;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VERecaptchaTests extends NewTestTemplate {

  WikiBasePageObject base;

  @BeforeMethod(alwaysRun = true)
  public void setupd() {
    base = new WikiBasePageObject();
  }

  // Verify that second click on "Save page" button changes recaptcha.
  @Test(groups = {"VERecaptchaTests", "VEAddRecaptcha_001", "VEAnon"})
  @RelatedIssue(issueID = "ZZZ-11954", comment = "test fails randomly")
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
