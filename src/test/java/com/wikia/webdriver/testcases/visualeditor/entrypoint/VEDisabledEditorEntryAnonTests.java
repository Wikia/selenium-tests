package com.wikia.webdriver.testcases.visualeditor.entrypoint;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VEDisabledEditorEntryAnonTests extends NewTestTemplate {

  WikiBasePageObject base;
  String wikiURL;

  @BeforeMethod(alwaysRun = true)
  public void setup_VEPreferred() {
    wikiURL = urlBuilder.getUrlForWiki(URLsContent.VE_DISABLED_WIKI);
    base = new WikiBasePageObject();
  }

  @Test(groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_001",
                  "createPageEntry"})
  public void VEDisabledEditorEntryAnonTestsTests_001_CreatePageEntry() {
    String articleName = base.getNameForArticle();
    ArticlePageObject article = new ArticlePageObject().open(articleName);
    VisualEditModePageObject ck = article.createArticleInCKUsingDropdown(articleName);
    Assertion.assertTrue(ck.isContentLoaded(), "Content is not loaded");
    ck.clickPublishButton();
  }

  @Test(groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_002",
                  "articleEditEntry"})
  public void VEDisabledEditorEntryAnonTestsTests_002_MainEditEntry() {
    ArticlePageObject article = new ArticlePageObject().open(base.getNameForArticle());
    VisualEditModePageObject ck = article.openCKModeWithMainEditButton();
    Assertion.assertTrue(ck.isContentLoaded(), "Content is not loaded");
    ck.clickPublishButton();
  }

  @Test(groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_003",
                  "redlinkEntry"})
  public void VEDisabledEditorEntryAnonTestsTests_003_RedlinkEntry() {
    ArticlePageObject article = new ArticlePageObject().open(URLsContent.TESTINGPAGE);
    VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_004",
                  "sectionEditEntry"})
  public void VEDisabledEditorEntryAnonTestsTests_004_SectionEditEntry() {
    ArticlePageObject article = new ArticlePageObject().open(URLsContent.TESTINGPAGE);
    VisualEditorPageObject ve = article.openVEModeWithSectionEditButton(0);
    ve.verifyEditorSurfacePresent();
    ve.verifyVEToolBarPresent();
  }

  @Test(groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_005",
                  "veactionURLEntry"})
  public void VEDisabledEditorEntryAnonTestsTests_005_URLEntry() {
    VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_006",
                  "listEntry"})
  public void VEDisabledEditorEntryAnonTestsTests_006_ListNamespace() {
    ArticlePageObject article = new ArticlePageObject().open(URLsContent.LIST_PAGE);
    VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_007",
                  "categoryEntry"})
  public void VEDisabledEditorEntryAnonTestsTests_007_CategoryNamespace() {
    ArticlePageObject article = new ArticlePageObject().open(URLsContent.CATEGORY_PAGE);
    VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_008",
                  "templateEntry"})
  public void VEDisabledEditorEntryAnonTestsTests_008_TemplateNamespace() {
    ArticlePageObject article = new ArticlePageObject().open(URLsContent.TEMPLATE_PAGE);
    SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
    src.verifySourceOnlyMode();
  }

  @Test(groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_009",
                  "actionURLEntry"})
  public void VEDisabledEditorEntryAnonTestsTests_009_actionEdit() {
    VisualEditModePageObject ck =
        base.navigateToArticleEditPage(wikiURL, base.getNameForArticle());
    Assertion.assertTrue(ck.isContentLoaded(), "Content is not loaded");
    ck.clickPublishButton();
  }
}
