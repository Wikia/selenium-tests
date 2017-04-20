package com.wikia.webdriver.testcases.visualeditor;

import com.wikia.webdriver.common.contentpatterns.InteractiveMapsContent;
import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.CreateAMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.CreatePinTypesComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.CreateRealMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.DeleteAMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorAddMapDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapsPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

@Test(groups = {"VEAddMap"})
public class VEAddMapTests extends NewTestTemplate {

  String articleName, mapID;
  InteractiveMapPageObject createdMap;

  @RelatedIssue(issueID = "WW-108")
  @Test(enabled = false, groups = {"VEAddMapTests_001", "VEAddExistingMap"})
  @Execute(asUser = User.USER_12, onWikia = URLsContent.VE_ENABLED_WIKI)
  public void VEAddMapTests_001_AddExistingMap() {
    articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    VisualEditorPageObject ve =
        new VisualEditorPageObject().openVEOnArticle(wikiURL, articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMapDialog mapDialog =
        (VisualEditorAddMapDialog) ve.openDialogFromMenu(InsertDialog.MAP);
    VisualEditorPageObject veNew = mapDialog.addExistingMap(0);
    veNew.verifyMapPresent();
    VisualEditorSaveChangesDialog save = veNew.clickPublishButton();
    ArticlePageObject article = save.savePage();
    article.verifyVEPublishComplete();
  }

  @RelatedIssue(issueID = "WW-108")
  @Test(enabled = false, groups = {"VEAddMapTests_002", "VEEmptyMap"})
  @Execute(asUser = User.USER_12, onWikia = URLsContent.RTE_DISABLED_WIKI)
  public void VEAddMapTests_002_CheckEmptyMapWiki() {
    articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();

    VisualEditorPageObject ve =
        new VisualEditorPageObject().openVEOnArticle(wikiURL, articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMapDialog mapDialog =
        (VisualEditorAddMapDialog) ve.openDialogFromMenu(InsertDialog.MAP);
    mapDialog.checkIsEmptyState();
  }

  @RelatedIssue(issueID = "WW-108")
  @Test(enabled = false, groups = {"VEAddMapTests_003", "VEAddMapFromZeroState"})
  @Execute(asUser = User.USER_12, onWikia = URLsContent.VE_DISABLED_WIKI)
  public void VEAddMapTests_003_InsertMapFromZeroState() {
    articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();

    VisualEditorPageObject ve =
        new VisualEditorPageObject().openVEOnArticle(wikiURL, articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMapDialog mapDialog =
        (VisualEditorAddMapDialog) ve.openDialogFromMenu(InsertDialog.MAP);
    CreateAMapComponentObject map = mapDialog.clickCreateAMapButton();
    CreateRealMapComponentObject realMap = map.clickRealMap();
    realMap.verifyRealMapPreviewImage();
    realMap.typeMapName(InteractiveMapsContent.MAP_NAME);
    CreatePinTypesComponentObject pinDialog = realMap.clickNext();
    pinDialog.typePinTypeTitle(InteractiveMapsContent.PIN_TYPE_NAME,
        InteractiveMapsContent.PIN_TYPE_INDEX);
    createdMap = pinDialog.clickSave();
    createdMap.verifyMapOpened();
    mapID = createdMap.getEmbedMapID();
    createdMap.verifyControlButtonsAreVisible();

    ve.openInteractiveMapById(wikiURL, Integer.parseInt(mapID));
    DeleteAMapComponentObject deleteMapModal = createdMap.deleteMap();
    InteractiveMapsPageObject specialMaps = deleteMapModal.deleteMap();
    specialMaps.verifyEmptyState();
  }
}
