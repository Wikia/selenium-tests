package com.wikia.webdriver.testcases.visualeditor;

import com.wikia.webdriver.common.contentpatterns.InteractiveMapsContent;
import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.CreateAMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.CreatePinTypesComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.CreateRealMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.DeleteAMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorAddMapDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapsPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Robert 'Rochan' Chan
 * @ownership Contribution VE-1337 - Adding existing map onto article VE-1337 - Checking empty state
 * dialog on wiki with no maps VE-1351 - Adding map in a empty state
 */

public class VEAddMapTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();
  WikiBasePageObject base;
  String articleName, mapID;
  InteractiveMapPageObject createdMap;

  @BeforeMethod(alwaysRun = true)
  public void setup_VEPreferred() {
    base = new WikiBasePageObject(driver);
    wikiURL = urlBuilder.getUrlForWiki(URLsContent.VE_ENABLED_WIKI);
    base.logInCookie(credentials.userName12, credentials.password12, wikiURL);
  }

  @Test(
      groups = {"VEAddMap", "VEAddMapTests_001", "VEAddExistingMap"}
  )
  public void VEAddMapTests_001_AddExistingMap() {
    articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMapDialog mapDialog =
        (VisualEditorAddMapDialog) ve.openDialogFromMenu(InsertDialog.MAP);
    VisualEditorPageObject veNew = mapDialog.addExistingMap(0);
    veNew.verifyMapPresent();
    VisualEditorSaveChangesDialog save = veNew.clickPublishButton();
    ArticlePageObject article = save.savePage();
    article.verifyVEPublishComplete();
    article.logOut(wikiURL);
  }

  @Test(
      groups = {"VEAddMap", "VEAddMapTests_002", "VEEmptyMap"}
  )
  public void VEAddMapTests_002_CheckEmptyMapWiki() {
    wikiURL = urlBuilder.getUrlForWiki(URLsContent.RTE_DISABLED_WIKI);
    articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorAddMapDialog mapDialog =
        (VisualEditorAddMapDialog) ve.openDialogFromMenu(InsertDialog.MAP);
    mapDialog.checkIsEmptyState();
    mapDialog.logOut(wikiURL);
  }

  @Test(
      groups = {"VEAddMap", "VEAddMapTests_003", "VEAddMapFromZeroState"}
  )
  public void VEAddMapTests_003_InsertMapFromZeroState() {
    int expectedMapNum = 1;
    wikiURL = urlBuilder.getUrlForWiki(URLsContent.VE_DISABLED_WIKI);
    articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();

    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
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
    delete_Map();
  }

  public void delete_Map() {
    createdMap = base.openInteractiveMapById(wikiURL, Integer.parseInt(mapID));
    DeleteAMapComponentObject deleteMapModal = createdMap.deleteMap();
    InteractiveMapsPageObject specialMaps = deleteMapModal.deleteMap();
    specialMaps.verifyEmptyState();
    specialMaps.logOut(wikiURL);
  }
}
