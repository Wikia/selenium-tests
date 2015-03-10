package com.wikia.webdriver.testcases.interactivemapstests;

import com.wikia.webdriver.common.contentpatterns.InteractiveMapsContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.AddPinComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapsPageObject;

import org.testng.annotations.Test;

/**
 * @author: Rodrigo Molinero Gomez
 * @author: Lukasz Jedrzejczak
 * @author: Lukasz Nowak
 * @ownership: Mobile Web
 */

public class PinMapTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @Test(groups = {"PinMapTests_001", "PinMapTests", "InteractiveMaps"})
  public void PinMapTests_001_VerifyPinModalContent() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    InteractiveMapPageObject
        selectedMap =
        specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
    selectedMap.verifyMapOpened();
    AddPinComponentObject pinDialog = selectedMap.placePinInMap();
    pinDialog.verifyPinTitleFieldIsDisplayed();
    pinDialog.verifyAssociatedArticleFieldIsDisplayed();
    pinDialog.verifyAssociatedArticleImagePlaceholderIsDisplayed();
    pinDialog.verifyPinCategorySelectorIsDisplayed();
    pinDialog.verifyDescriptionFieldIsDisplayed();
    selectedMap = pinDialog.clickCancelButton();
  }

  @Test(groups = {"PinMapTests_002", "PinMapTests", "InteractiveMaps"})
  public void PinMapTests_002_VerifySuggestionsAndAssociatedImage_MAIN_3948() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    InteractiveMapPageObject
        selectedMap =
        specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
    selectedMap.verifyMapOpened();
    AddPinComponentObject pinDialog = selectedMap.placePinInMap();
    pinDialog.verifyPinTitleFieldIsDisplayed();
    pinDialog.typePinName(InteractiveMapsContent.PIN_NAME);
    String placeholderSrc = pinDialog.getAssociatedArticleImageSrc();
    pinDialog.typeAssociatedArticle(InteractiveMapsContent.ASSOCIATED_ARTICLE_NAME);
    pinDialog.clickSuggestion(0);
    pinDialog.verifyAssociatedImageIsVisible(placeholderSrc);
    pinDialog.selectPinType();
    selectedMap = pinDialog.clickSaveButton();
    selectedMap.verifyPinPopupImageIsVisible();
  }

  @Test(groups = {"PinMapTests_003", "PinMapTests", "InteractiveMaps"})
  public void PinMapTests_003_VerifyPinCreationErrors() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    InteractiveMapPageObject
        selectedMap =
        specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
    selectedMap.verifyMapOpened();
    AddPinComponentObject addPinModal = selectedMap.placePinInMap();
    addPinModal.clickSaveButton();
    addPinModal.verifyErrorExists();
    addPinModal.typePinName(InteractiveMapsContent.PIN_DESCRIPTION);
    addPinModal.clickSaveButton();
    addPinModal.verifyErrorExists();
    addPinModal.selectPinType();
    addPinModal.clickSaveButton();
    addPinModal.verifyErrorExists();
  }

  @Test(groups = {"PinMapTests_004", "PinMapTests", "InteractiveMaps"},
      dependsOnMethods = "PinMapTests_006_VerifyChangePinData")
  public void PinMapTests_004_VerifyPopUpAfterClickPin() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    InteractiveMapPageObject
        selectedMap =
        specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
    selectedMap.verifyMapOpened();
    selectedMap.clickOnPin(0);
    selectedMap.verifyPopUpVisible();
  }

  @Test(groups = {"PinMapTests_005", "PinMapTests", "InteractiveMaps"},
      dependsOnMethods = "PinMapTests_006_VerifyChangePinData")
  public void PinMapTests_005_VerifyDeletePin() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    InteractiveMapPageObject
        selectedMap =
        specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
    selectedMap.verifyMapOpened();
    selectedMap.clickOnFilterBoxTitle();
    selectedMap.clickOnPin(0);
    String pinName = selectedMap.getOpenPinName();
    selectedMap.verifyPopUpVisible();
    AddPinComponentObject editPinModal = selectedMap.clickOnEditPin();
    selectedMap = editPinModal.clickDeletePin();
    selectedMap.verifyPinNotExists(pinName);
  }

  @Test(groups = {"PinMapTests_006", "PinMapTests", "InteractiveMaps"})
  public void PinMapTests_006_VerifyChangePinData() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    InteractiveMapPageObject
        selectedMap =
        specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
    selectedMap.verifyMapOpened();
    selectedMap.clickOnFilterBoxTitle();
    AddPinComponentObject pinModal = selectedMap.placePinInMap();
    String pinTitle = base.getTimeStamp();
    String pinDescription = base.getTimeStamp() + base.getTimeStamp();
    pinModal.typePinName(pinTitle);
    pinModal.selectPinType();
    pinModal.typePinDescription(pinDescription);
    selectedMap = pinModal.clickSaveButton();
    pinModal = selectedMap.clickOnEditPin();
    pinModal.clearPinName();
    pinModal.typePinName(base.getTimeStamp() + base.getTimeStamp());
    pinModal.clearPinDescription();
    pinModal.typePinDescription(base.getTimeStamp());
    selectedMap = pinModal.clickSaveButton();
    selectedMap.verifyPinDataWasChanged(pinTitle, pinDescription);
  }

  @Test(groups = {"PinMapTests_007", "PinMapTests", "InteractiveMaps"})
  public void PinMapTests_007_VerifyValidExternalUrlCanBeAdded() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    InteractiveMapsPageObject specialMaps = base.openSpecialInteractiveMaps(wikiURL);
    InteractiveMapPageObject
        selectedMap =
        specialMaps.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
    selectedMap.verifyMapOpened();
    AddPinComponentObject addPinModal = selectedMap.placePinInMap();
    addPinModal.typePinName(InteractiveMapsContent.PIN_NAME);
    addPinModal.typeAssociatedArticle(InteractiveMapsContent.EXTERNAL_LINK);
    addPinModal.selectPinType();
    selectedMap = addPinModal.clickSaveButton();
    selectedMap.verifyPinTitleLink();
    selectedMap.clickOpenPinTitle();
    selectedMap.verifyUrlInNewWindow(InteractiveMapsContent.EXTERNAL_LINK);
  }

  @Test(groups = {"PinMapTests_008", "PinMapTests", "InteractiveMaps"})
  public void PinMapTests_008_VerifyErrorMessageWhenAssociatedArticleNotExist() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    InteractiveMapsPageObject specialMaps = base.openSpecialInteractiveMaps(wikiURL);
    InteractiveMapPageObject
        selectedMap =
        specialMaps.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
    selectedMap.verifyMapOpened();
    AddPinComponentObject addPinModal = selectedMap.placePinInMap();
    addPinModal.typePinName(InteractiveMapsContent.PIN_NAME);
    addPinModal.typeAssociatedArticle(InteractiveMapsContent.ARTICLE_WHICH_DOES_NOT_EXIST);
    addPinModal.selectPinType();
    addPinModal.clickSaveButton();
    addPinModal.verifyErrorContent(InteractiveMapsContent.ARTICLE_NOT_EXIST_ERROR.replace(
        "%articlename%",
        InteractiveMapsContent.ARTICLE_WHICH_DOES_NOT_EXIST
    ));
  }

  @Test(groups = {"PinMapTests_009", "PinMapTests", "InteractiveMaps"})
  public void PinMapTests_009_VerifyArticlePlaceholder() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    InteractiveMapsPageObject specialMaps = base.openSpecialInteractiveMaps(wikiURL);
    InteractiveMapPageObject
        selectedMap =
        specialMaps.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
    selectedMap.verifyMapOpened();
    AddPinComponentObject addPinModal = selectedMap.placePinInMap();
    addPinModal.verifyAssociatedArticlePlaceholder();
  }
}
