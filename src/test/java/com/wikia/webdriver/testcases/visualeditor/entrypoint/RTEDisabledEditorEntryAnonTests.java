package com.wikia.webdriver.testcases.visualeditor.entrypoint;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RTEDisabledEditorEntryAnonTests extends NewTestTemplate {

  WikiBasePageObject base;
  String wikiURL;

  @BeforeMethod(alwaysRun = true)
  public void setup_VEPreferred() {
    wikiURL = urlBuilder.getUrlForWiki(URLsContent.RTE_DISABLED_WIKI);
    base = new WikiBasePageObject();
  }

  @Test(
      groups = {"RTEDisabledEditorEntryAnonTests", "RTEDisabledEditorEntryAnonTests_001",
                "createPageEntry"}
  )
  public void RTEDisabledEditorEntryAnonTests_001_CreatePageEntry() {
    String articleName = base.getNameForArticle();
    ArticlePageObject article = new ArticlePageObject().open(articleName);
    VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(
      groups = {"RTEDisabledEditorEntryAnonTests", "RTEDisabledEditorEntryAnonTests_002",
                "articleEditEntry"}
  )
  public void RTEDisabledEditorEntryAnonTests_002_MainEditEntry() {
    ArticlePageObject article =
        new ArticlePageObject().open(URLsContent.TESTINGPAGE);
    VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(
      groups = {"RTEDisabledEditorEntryAnonTests", "RTEDisabledEditorEntryAnonTests_003",
                "redlinkEntry"}
  )
  public void RTEDisabledEditorEntryAnonTests_003_RedlinkEntry() {
    ArticlePageObject article =
        new ArticlePageObject().open(URLsContent.TESTINGPAGE);
    VisualEditorPageObject ve = article.openVEModeWithRedLinks(0);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(
      groups = {"RTEDisabledEditorEntryAnonTests", "RTEDisabledEditorEntryAnonTests_004",
                "sectionEditEntry"},
      enabled = false
  )
  @RelatedIssue(issueID = "XW-3680")
  public void RTEDisabledEditorEntryAnonTests_004_SectionEditEntry() {
    ArticlePageObject article =
        new ArticlePageObject().open(URLsContent.TESTINGPAGE);
    VisualEditorPageObject ve = article.openVEModeWithSectionEditButton(0);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(
      groups = {"RTEDisabledEditorEntryAnonTests", "RTEDisabledEditorEntryAnonTests_005",
                "veactionURLEntry"}
  )
  public void RTEDisabledEditorEntryAnonTests_005_URLEntry() {
    VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(
      groups = {"RTEDisabledEditorEntryAnonTests", "RTEDisabledEditorEntryAnonTests_006",
                "listEntry"}
  )
  public void RTEDisabledEditorEntryAnonTests_006_ListNamespace() {
    ArticlePageObject article =
        new ArticlePageObject().open(URLsContent.LIST_PAGE);
    VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(
      groups = {"RTEDisabledEditorEntryAnonTests", "RTEDisabledEditorEntryAnonTests_007",
                "categoryEntry"}
  )
  public void RTEDisabledEditorEntryAnonTests_007_CategoryNamespace() {
    ArticlePageObject article =
        new ArticlePageObject().open(URLsContent.CATEGORY_PAGE);
    VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(
      groups = {"RTEDisabledEditorEntryAnonTests", "RTEDisabledEditorEntryAnonTests_008",
                "templateEntry"},
      enabled = false
  )
  @RelatedIssue(issueID = "XW-3681")
  public void RTEDisabledEditorEntryAnonTests_008_TemplateNamespace() {
    ArticlePageObject article =
        new ArticlePageObject().open(URLsContent.TEMPLATE_PAGE);
    SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
    src.verifySourceOnlyMode();
  }

  @Test(
      groups = {"RTEDisabledEditorEntryAnonTests", "RTEDisabledEditorEntryAnonTests_009",
                "actionURLEntry"}
  )
  public void RTEDisabledEditorEntryAnonTests_009_actionEdit() {
    SourceEditModePageObject src =
        base.navigateToArticleEditPageSrc(wikiURL, base.getNameForArticle());
    src.verifySourceOnlyMode();
  }
}
