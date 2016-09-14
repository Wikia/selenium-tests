package com.wikia.webdriver.testcases.interactivemapstests;

import com.wikia.webdriver.common.contentpatterns.InteractiveMapsContent;
import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.AddPinComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.CreatePinTypesComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.EmbedMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.DabbletComPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapsPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.editmode.WikiArticleEditMode;

import org.testng.annotations.Test;

public class EmbedMapTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(enabled = false, groups = {"EmbedMapTests_001", "EmbedMapTests", "InteractiveMaps"})
  @Execute(asUser = User.USER)
  public void EmbedMapTests_001_EmbedMapInWikiaPage() {
    WikiBasePageObject base = new WikiBasePageObject();
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    InteractiveMapPageObject
        selectedMap =
        specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
    selectedMap.verifyMapOpened();
    String wikiEmbedCode = selectedMap.getEmbedMapWikiCode();
    String mapID = selectedMap.getEmbedMapID();
    WikiArticleEditMode editMode = selectedMap.openEmbedMapPageEdit(wikiURL);
    editMode.clickSourceButton();
    editMode.clearSource();
    editMode.verifySourceEditorContentIsEmpty();
    editMode.typeContentInSourceMode(wikiEmbedCode);
    editMode.clickOnPublish();
    editMode.verifyEmbededMap(mapID);
  }

  @Test(groups = {"EmbedMapTests_002", "EmbedMapTests", "InteractiveMaps"})
  @DontRun(env = {"dev", "sandbox", "preview"})
  public void EmbedMapTests_002_VerifyEmbedMapElements() {
    ArticlePageObject article = new ArticlePageObject();
    article.open(InteractiveMapsContent.EMBED_MAP_ARTICLE_NAME);
    EmbedMapComponentObject embedMapDialog = article.clickViewEmbedMap();
    embedMapDialog.verifyEmbedMapModalOpened();
    embedMapDialog.verifyMapTitlePresented();
    embedMapDialog.verifyCloseButtonPresented();
    embedMapDialog.verifyMapElementsPresented();
    embedMapDialog.verifyBrandFooterNotVisible();
  }

  @Test(enabled = false, groups = {"EmbedMapTests_003", "EmbedMapTests", "InteractiveMaps"})
  @Execute(asUser = User.USER)
  public void EmbedMapTests_003_VerifyEmbedMapCodeButton() {
    WikiBasePageObject base = new WikiBasePageObject();
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    InteractiveMapPageObject
        selectedMap =
        specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
    selectedMap.verifyMapOpened();
    selectedMap.clickEmbedMapCodeButton();
    selectedMap.verifyEmbedMapDialog();
    selectedMap.clickEmbedMapCodeButton(InteractiveMapPageObject.embedMapDialogButtons.SMALL);
    selectedMap.verifyEmbedMapCode(InteractiveMapPageObject.embedMapDialogButtons.SMALL);
    selectedMap.clickEmbedMapCodeButton(InteractiveMapPageObject.embedMapDialogButtons.MEDIUM);
    selectedMap.verifyEmbedMapCode(InteractiveMapPageObject.embedMapDialogButtons.MEDIUM);
    selectedMap.clickEmbedMapCodeButton(InteractiveMapPageObject.embedMapDialogButtons.LARGE);
    selectedMap.verifyEmbedMapCode(InteractiveMapPageObject.embedMapDialogButtons.LARGE);
  }

  @Test(enabled = false, groups = {"EmbedMapTests_004", "EmbedMapTests", "InteractiveMaps"})
  @Execute(asUser = User.USER)
  public void EmbedMapTests_004_VerifyEmbedMapOutsideWikia() {
    WikiBasePageObject base = new WikiBasePageObject();
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    InteractiveMapPageObject
        selectedMap =
        specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
    selectedMap.verifyMapOpened();
    selectedMap.clickEmbedMapCodeButton();
    selectedMap.verifyEmbedMapDialog();
    selectedMap.clickEmbedMapCodeButton(InteractiveMapPageObject.embedMapDialogButtons.SMALL);
    String mapCode = selectedMap.getEmbedMapCode();
    DabbletComPageObject outPage = new DabbletComPageObject(driver);
    outPage.openOutPage();
    outPage.typeHtmlCode(mapCode);
    outPage.verifyMapEmbed();
  }

  @Test(enabled = false, groups = {"EmbedMapTests_005", "EmbedMapTests", "InteractiveMaps"})
  @Execute(asUser = User.USER)
  public void EmbedMapTests_005_VerifyEmbedMapContributeModals() {
    WikiBasePageObject base = new WikiBasePageObject();
    ArticlePageObject article = new ArticlePageObject();
    article.open(InteractiveMapsContent.EMBED_MAP_ARTICLE_NAME);
    EmbedMapComponentObject embedMapDialog = article.clickViewEmbedMap();
    AddPinComponentObject addPinModal = embedMapDialog.placePinInMap();
    addPinModal.verifyPinTitleFieldIsDisplayed();
    addPinModal.verifyDescriptionFieldIsDisplayed();
    addPinModal.clickCancelButton();
    CreatePinTypesComponentObject pinTypesDialog = embedMapDialog.clickEditPinTypesButton();
    pinTypesDialog.verifyPinTypesDialog();
  }

  @Test(enabled = false, groups = {"EmbedMapTests_006", "EmbedMapTests", "InteractiveMaps"})
  @Execute(asUser = User.USER)
  public void EmbedMapTests_006_VerifyEmbeddedMapAddPinType() {
    WikiBasePageObject base = new WikiBasePageObject();
    ArticlePageObject article = new ArticlePageObject();
    article.open(InteractiveMapsContent.EMBED_MAP_ARTICLE_NAME);
    EmbedMapComponentObject embedMapDialog = article.clickViewEmbedMap();
    CreatePinTypesComponentObject pinTypesDialog = embedMapDialog.clickEditPinTypesButton();
    pinTypesDialog.verifyPinTypesDialog();
    pinTypesDialog.deletePinTypes();
    pinTypesDialog.typeManyPinTypeTitle(InteractiveMapsContent.PIN_TYPE_NAME, 4);
    pinTypesDialog.clickSave();
    embedMapDialog.verifyPinTypeExists(InteractiveMapsContent.PIN_TYPE_NAME);
  }
}
