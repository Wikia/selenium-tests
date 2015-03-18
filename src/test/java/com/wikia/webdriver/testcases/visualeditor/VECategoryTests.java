package com.wikia.webdriver.testcases.visualeditor;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.CategoryResultType;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorOptionsDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorReviewChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Robert 'Rochan' Chan
 * @ownership Contribution <p/> VE-1407 Adding category to an article VE-1408 Deleting category from
 * an article VE-1411 New category suggestion when adding category to an article VE-1411 Matching
 * categories suggestion when adding category to an article
 */

public class VECategoryTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();
  WikiBasePageObject base;
  String articleName, testCategory, categorySearchStr;
  List<String> categoryWikiTexts;

  @BeforeMethod(alwaysRun = true)
  public void setup() {
    testCategory = "Ca";
    categorySearchStr = "abcd";
    categoryWikiTexts = new ArrayList<>();
    categoryWikiTexts.add("[[Category:" + testCategory + "]]");
    base = new WikiBasePageObject(driver);
  }

  //CA01
  @Test(
      groups = {"VECategoryTests", "VECategoryTests_001", "VEAddCategory", "VECategoryTests_002"}
  )
  public void VECategoryTests_001_AddNewCategory() {
    articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorOptionsDialog optionsDialog =
        (VisualEditorOptionsDialog) ve.openDialogFromMenu(InsertDialog.CATEGORIES);
    optionsDialog.addCategory(testCategory);
    ve = optionsDialog.clickApplyChangesButton();
    VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
    VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
    reviewDialog.verifyAddedDiffs(categoryWikiTexts);
    saveDialog = reviewDialog.clickReturnToSaveFormButton();
    ArticlePageObject article = saveDialog.savePage();
    article.verifyVEPublishComplete();
  }

  //CA02
  @Test(
      groups = {"VECategoryTests", "VECategoryTests_002", "VERemoveCategory"},
      dependsOnGroups = "VECategoryTests_001"
  )
  public void VECategoryTests_002_RemoveCategory() {
    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorOptionsDialog optionsDialog =
        (VisualEditorOptionsDialog) ve.openDialogFromMenu(InsertDialog.CATEGORIES);
    optionsDialog.removeCategory(testCategory);
    ve = optionsDialog.clickApplyChangesButton();
    VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
    VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
    reviewDialog.verifyDeletedDiffs(categoryWikiTexts);
    saveDialog = reviewDialog.clickReturnToSaveFormButton();
    ArticlePageObject article = saveDialog.savePage();
    article.verifyVEPublishComplete();
  }

  //CA03
  @Test(
      groups = {"VECategoryTests", "VECategoryTests_003", "VEAddCategory"}
  )
  public void VECategoryTests_003_NewCategorySuggestions() {
    String articleName2 = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName2);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorOptionsDialog optionsDialog =
        (VisualEditorOptionsDialog) ve.openDialogFromMenu(InsertDialog.CATEGORIES);
    optionsDialog.verifyLinkSuggestions(categorySearchStr, CategoryResultType.NEW);
  }

  //CA04
  @Test(
      groups = {"VECategoryTests", "VECategoryTests_004", "VEAddCategory"}
  )
  public void VECategoryTests_004_MatchingCategorySuggestions() {
    String articleName2 = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName2);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorOptionsDialog optionsDialog =
        (VisualEditorOptionsDialog) ve.openDialogFromMenu(InsertDialog.CATEGORIES);
    optionsDialog.verifyLinkSuggestions(categorySearchStr, CategoryResultType.MATCHING);
  }

  //CA05
  @Test(
      groups = {"VECategoryTests", "VECategoryTests_005", "VEAddCategory"}
  )
  public void VECategoryTests_005_AddNewCategoryWithSortKey() {
    String testCategory2 = "Newstuff";
    String sortKey = "testkey";
    List<String> categoryWithSortKeyWikiTexts = new ArrayList<>();
    categoryWithSortKeyWikiTexts.add("[[Category:" + testCategory2 + "|" + sortKey + "]]");

    String articleName2 = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName2);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorOptionsDialog optionsDialog =
        (VisualEditorOptionsDialog) ve.openDialogFromMenu(InsertDialog.CATEGORIES);
    optionsDialog.addCategory(testCategory2);
    optionsDialog.addSortKeyToCategory(testCategory2, sortKey);
    ve = optionsDialog.clickApplyChangesButton();
    VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
    VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
    reviewDialog.verifyAddedDiffs(categoryWithSortKeyWikiTexts);
    saveDialog = reviewDialog.clickReturnToSaveFormButton();
    ArticlePageObject article = saveDialog.savePage();
    article.verifyVEPublishComplete();
  }
}
