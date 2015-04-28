package com.wikia.webdriver.testcases.visualeditor.text;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Formatting;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.common.templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Robert 'rochan' Chan
 * @ownership Contribution <p/> VE-1210 Verify VE is able to perform Copy and Paste text on the edit
 * area
 */

public class VECopyAndPasteTests extends NewTestTemplate {

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
    String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    String text = PageContent.ARTICLE_TEXT;
    ve.typeTextArea(text);
    ve.copyAndPaste();
    ve.verifyFormatting(Formatting.PARAGRAPH, text + text);
    VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
    ArticlePageObject article = saveDialog.savePage();
    article.verifyFormattingFromVE(Formatting.PARAGRAPH, text + text);
  }
}
