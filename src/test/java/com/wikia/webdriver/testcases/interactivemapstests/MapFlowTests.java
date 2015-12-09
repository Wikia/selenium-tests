package com.wikia.webdriver.testcases.interactivemapstests;

import com.wikia.webdriver.common.contentpatterns.InteractiveMapsContent;
import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.CreateACustomMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.CreateAMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.CreatePinTypesComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.CreateRealMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.TemplateComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapsPageObject;

import org.testng.annotations.Test;

public class MapFlowTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = {"MapFlowTests_001", "MapFlowTests", "InteractiveMaps"}, enabled = false)
  @Execute(asUser = User.USER)
  public void MapFlowTests_001_CreateCustomMapNewImageUpload() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    CreateAMapComponentObject map = specialMap.clickCreateAMap();
    CreateACustomMapComponentObject customMap = map.clickCustomMap();
    TemplateComponentObject template = customMap.selectFileToUpload(PageContent.FILE);
    template.verifyTemplateImagePreview();
    template.typeMapName(InteractiveMapsContent.MAP_NAME);
    InteractiveMapsContent.TEMPLATE_NAME = base.getTimeStamp();
    template.typeTemplateName(InteractiveMapsContent.TEMPLATE_NAME);
    CreatePinTypesComponentObject pinDialog = template.clickNext();
    pinDialog.typePinTypeTitle(InteractiveMapsContent.PIN_TYPE_NAME,
                               InteractiveMapsContent.PIN_TYPE_INDEX);
    InteractiveMapPageObject createdMap = pinDialog.clickSave();
    createdMap.verifyCreatedMapTitle(InteractiveMapsContent.MAP_NAME);
    createdMap.verifyMapOpened();
    createdMap.verifyCreatedPinTypesForNewMap();
    createdMap.verifyControlButtonsAreVisible();
  }

  @DontRun(env = {"dev", "sandbox", "preview"})
  @Test(groups = {"MapFlowTests_002", "MapFlowTests", "InteractiveMaps"})
  @Execute(asUser = User.USER)
  @RelatedIssue(issueID = "MAIN-5732", comment = "Not possible to test manually in preview environment")
  public void MapFlowTests_002_CreateCustomMapWithExistingTemplate() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    CreateAMapComponentObject map = specialMap.clickCreateAMap();
    CreateACustomMapComponentObject customMap = map.clickCustomMap();
    String
        selectedImageName =
        customMap.getSelectedTemplateImageName(InteractiveMapsContent.SELECTED_TEMPLATE_INDEX);
    TemplateComponentObject
        template =
        customMap.selectTemplate(InteractiveMapsContent.SELECTED_TEMPLATE_INDEX);
    template.verifyTemplateImage(selectedImageName);
    template.typeMapName(InteractiveMapsContent.MAP_NAME);
    CreatePinTypesComponentObject pinDialog = template.clickNext();
    pinDialog.typePinTypeTitle(InteractiveMapsContent.PIN_TYPE_NAME,
        InteractiveMapsContent.PIN_TYPE_INDEX);
    InteractiveMapPageObject createdMap = pinDialog.clickSave();
    createdMap.verifyCreatedMapTitle(InteractiveMapsContent.MAP_NAME);
    createdMap.verifyMapOpened();
    createdMap.verifyCreatedPinTypesForNewMap();
    createdMap.verifyControlButtonsAreVisible();
  }

  @DontRun(env = {"dev", "sandbox", "preview"})
  @Test(groups = {"MapFlowTests_003", "MapFlowTests", "InteractiveMaps"})
  @Execute(asUser = User.USER)
  @RelatedIssue(issueID = "MAIN-5732", comment = "Not possible to test manually in preview environment")
  public void MapFlowTests_003_CreateRealMap() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    CreateAMapComponentObject map = specialMap.clickCreateAMap();
    CreateRealMapComponentObject realMap = map.clickRealMap();
    realMap.verifyRealMapPreviewImage();
    realMap.typeMapName(InteractiveMapsContent.MAP_NAME);
    CreatePinTypesComponentObject pinDialog = realMap.clickNext();
    pinDialog.typePinTypeTitle(InteractiveMapsContent.PIN_TYPE_NAME,
                               InteractiveMapsContent.PIN_TYPE_INDEX);
    InteractiveMapPageObject createdMap = pinDialog.clickSave();
    createdMap.verifyMapOpened();
    createdMap.verifyControlButtonsAreVisible();
  }

  @Test(groups = {"MapFlowTests_004", "MapFlowTests", "InteractiveMaps"})
  @Execute(asUser = User.USER)
  public void MapFlowTests_004_VerifyBackButtonWorksCorrectly() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    CreateAMapComponentObject map = specialMap.clickCreateAMap();
    CreateACustomMapComponentObject customMap = map.clickCustomMap();
    TemplateComponentObject
        template =
        customMap.selectTemplate(InteractiveMapsContent.SELECTED_TEMPLATE_INDEX);
    template.verifyTemplateImagePreview();
    customMap = template.clickBack();
    customMap.verifyTemplateListElementVisible(InteractiveMapsContent.SELECTED_TEMPLATE_INDEX);
    map = customMap.clickBack();
    map.verifyRealMapAndCustomMapButtons();
    CreateRealMapComponentObject realMap = map.clickRealMap();
    realMap.verifyRealMapPreviewImage();
    map = realMap.clickBack();
    map.verifyRealMapAndCustomMapButtons();
  }

  @Test(groups = {"MapFlowTests_005", "MapFlowTests", "InteractiveMaps"})
  @Execute(asUser = User.USER)
  public void MapFlowTests_005_VerifyCloseButtonsInCreationMapFlow() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    CreateAMapComponentObject createMapModal = specialMap.clickCreateAMap();
    specialMap = createMapModal.clickCloseButton();
    specialMap.verifyCreateMapModalNotExists();
    createMapModal = specialMap.clickCreateAMap();
    CreateRealMapComponentObject realMapModal = createMapModal.clickRealMap();
    specialMap = realMapModal.clickClose();
    specialMap.verifyCreateMapModalNotExists();
    createMapModal = specialMap.clickCreateAMap();
    CreateACustomMapComponentObject customMapModal = createMapModal.clickCustomMap();
    customMapModal.clickCloseButton();
    specialMap.verifyCreateMapModalNotExists();
  }
}
