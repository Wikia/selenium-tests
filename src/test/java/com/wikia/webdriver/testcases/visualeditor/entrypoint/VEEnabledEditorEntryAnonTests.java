package com.wikia.webdriver.testcases.visualeditor.entrypoint;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VEEnabledEditorEntryAnonTests extends NewTestTemplate {

  WikiBasePageObject base;
  String wikiURL;

  @BeforeMethod(alwaysRun = true)
  public void setup_VEPreferred() {
    wikiURL = urlBuilder.getUrlForWiki(URLsContent.VE_ENABLED_WIKI);
    base = new WikiBasePageObject();
  }

  @Test(
      groups = {"VEEnabledEditorEntryAnonTests", "VEEnabledEditorEntryAnonTests_001",
                "createPageEntry"}
  )
  public void VEEnabledEditorEntryAnonTests_001_CreatePageEntry() {
    String articleName = base.getNameForArticle();
    ArticlePageObject article = new ArticlePageObject().open(articleName);
    VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(
      groups = {"VEEnabledEditorEntryAnonTests", "VEEnabledEditorEntryAnonTests_002",
                "articleEditEntry"}
  )
  public void VEEnabledEditorEntryAnonTests_002_MainEditEntry() {
    ArticlePageObject article =
        new ArticlePageObject().open(base.getNameForArticle());
    VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(
      groups = {"VEEnabledEditorEntryAnonTests", "VEEnabledEditorEntryAnonTests_003",
                "redlinkEntry"}
  )
  public void VEEnabledEditorEntryAnonTests_003_RedlinkEntry() {
    ArticlePageObject article =
        new ArticlePageObject().open(URLsContent.TESTINGPAGE);
    VisualEditorPageObject ve = article.openVEModeWithRedLinks(0);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(
      groups = {"VEEnabledEditorEntryAnonTests", "VEEnabledEditorEntryAnonTests_004",
                "sectionEditEntry"},
          enabled = false
  )
  @RelatedIssue(issueID = "XW-3680")
  public void VEEnabledEditorEntryAnonTests_004_SectionEditEntry() {
    ArticlePageObject article =
        new ArticlePageObject().open(URLsContent.TESTINGPAGE);
    VisualEditorPageObject ve = article.openVEModeWithSectionEditButton(0);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(
      groups = {"VEEnabledEditorEntryAnonTests", "VEEnabledEditorEntryAnonTests_005",
                "veactionURLEntry"}
  )
  public void VEEnabledEditorEntryAnonTests_005_URLEntry() {
    VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(
      groups = {"VEEnabledEditorEntryAnonTests", "VEEnabledEditorEntryAnonTests_006", "listEntry"}
  )
  public void VEEnabledEditorEntryAnonTests_006_ListNamespace() {
    ArticlePageObject article =
        new ArticlePageObject().open(URLsContent.LIST_PAGE);
    VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(
      groups = {"VEEnabledEditorEntryAnonTests", "VEEnabledEditorEntryAnonTests_007",
                "categoryEntry"}
  )
  public void VEEnabledEditorEntryAnonTests_007_CategoryNamespace() {
    ArticlePageObject article =
        new ArticlePageObject().open(URLsContent.CATEGORY_PAGE);
    VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(
      groups = {"VEEnabledEditorEntryAnonTests", "VEEnabledEditorEntryAnonTests_008",
                "templateEntry"},
          enabled = false
  )
  @RelatedIssue(issueID = "XW-3680")
  public void VEEnabledEditorEntryAnonTests_008_TemplateNamespace() {
    ArticlePageObject article =
        new ArticlePageObject().open(URLsContent.TEMPLATE_PAGE);
    SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
    src.verifySourceOnlyMode();
  }

  @Test(
      groups = {"VEEnabledEditorEntryAnonTests", "VEEnabledEditorEntryAnonTests_009",
                "actionURLEntry"}
  )
  public void VEEnabledEditorEntryAnonTests_009_actionEdit() {
    VisualEditModePageObject ck =
        base.navigateToArticleEditPage(wikiURL, base.getNameForArticle());
    
    Assertion.assertTrue(ck.isContentLoaded(), "Content is not loaded");
    ck.clickPublishButton();
  }
}
