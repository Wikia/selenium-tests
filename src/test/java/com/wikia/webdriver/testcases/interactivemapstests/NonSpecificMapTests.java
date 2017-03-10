package com.wikia.webdriver.testcases.interactivemapstests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.InteractiveMapsContent;
import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.CreateACustomMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.CreateAMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.TemplateComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapsPageObject;

public class NonSpecificMapTests extends NewTestTemplate {

  @Test(groups = {"NonSpecificMapTests_001", "NonSpecificMapTests", "InteractiveMaps"})
  @DontRun(env = {"dev", "sandbox", "preview"})
  @Execute(asUser = User.USER)
  public void NonSpecificMapTests_001_ClickMapAndVerifyCorrectRedirect() {
    WikiBasePageObject base = new WikiBasePageObject();
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    String mapUrl = specialMap.getMapLink(InteractiveMapsContent.SELECTED_MAP_INDEX);
    String mapTitle = specialMap.getMapTitle(InteractiveMapsContent.SELECTED_MAP_INDEX);
    InteractiveMapPageObject selectedMap =
        specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
    selectedMap.verifyMapOpened();
    selectedMap.verifyURL(mapUrl);
    selectedMap.verifyCreatedMapTitle(mapTitle);
  }

  @Test(enabled = false,
      groups = {"NonSpecificMapTests_002", "NonSpecificMapTests", "InteractiveMaps"})
  public void NonSpecificMapTests_002_VerifyLoginModalWhenAnon() {
    WikiBasePageObject base = new WikiBasePageObject();
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    CreateAMapComponentObject map = specialMap.clickCreateAMap();
    map.verifyLoginModal();
  }

  @Test(enabled = false,
      groups = {"NonSpecificMapTests_003", "NonSpecificMapTests", "InteractiveMaps"})
  @Execute(asUser = User.USER)
  public void NonSpecificMapTests_003_VerifyTemplateSearch() {
    WikiBasePageObject base = new WikiBasePageObject();
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    CreateAMapComponentObject createMapDialog = specialMap.clickCreateAMap();
    CreateACustomMapComponentObject customMapDialog = createMapDialog.clickCustomMap();
    customMapDialog
        .typeSearchTile(InteractiveMapsContent.TEMPLATE_NAME_TO_SEARCH_SHOULD_NOT_BE_FOUND);
    customMapDialog.verifyErrorExists();
    customMapDialog.clearSearchTitle();
    customMapDialog.typeSearchTile(InteractiveMapsContent.TEMPLATE_NAME_TO_SEARCH_SHOULD_BE_FOUND);
    customMapDialog.verifyTemplateListElementVisible(0);
  }

  @DontRun(env = {"dev", "sandbox", "preview"})
  @Test(groups = {"NonSpecificMapTests_004", "NonSpecificMapTests", "InteractiveMaps"})
  public void NonSpecificMapTests_004_VerifyMapZoomOptions() {
    WikiBasePageObject base = new WikiBasePageObject();
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    InteractiveMapPageObject selectedMap =
        specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
    selectedMap.verifyMapOpened();
    selectedMap.clickZoomOutButton();
    selectedMap.clickZoomInButton();
  }

  @Test(groups = {"NonSpecificMapTests_005", "NonSpecificMapTests", "InteractiveMaps"})
  @DontRun(env = {"dev", "sandbox", "preview"})
  @Execute(asUser = User.USER)
  public void NonSpecificMapTests_005_VerifyMapListElements() {
    WikiBasePageObject base = new WikiBasePageObject();
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    specialMap.verifyAmountMapOnTheList();
    specialMap.verifyCreateMapButtonExists();
    specialMap.verifyCorrectPagination();
  }

  @Test(enabled = false,
      groups = {"NonSpecificMapTests_006", "NonSpecificMapTests", "InteractiveMaps"})
  @Execute(asUser = User.USER)
  public void NonSpecificMapTests_006_VerifyLearnMoreLink() {
    WikiBasePageObject base = new WikiBasePageObject();
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    CreateAMapComponentObject createMapModal = specialMap.clickCreateAMap();
    createMapModal.verifyLearnMoreLinkRedirect(InteractiveMapsContent.LEARN_MORE_LINK);
  }

  @Test(enabled = false,
      groups = {"NonSpecificMapTests_007", "NonSpecificMapTests", "InteractiveMaps"})
  @Execute(asUser = User.USER)
  public void NonSpecificMapTests_007_VerifyCreateCustomMapErrors() {
    WikiBasePageObject base = new WikiBasePageObject();
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    CreateAMapComponentObject createMap = specialMap.clickCreateAMap();
    CreateACustomMapComponentObject customMap = createMap.clickCustomMap();
    TemplateComponentObject templateMap =
        customMap.selectTemplate(InteractiveMapsContent.SELECTED_TEMPLATE_INDEX);
    templateMap.clickNext();
    templateMap.verifyErrorExists();
  }

  @Test(groups = {"NonSpecificMapTests_008", "NonSpecificMapTests", "InteractiveMaps"})
  @DontRun(env = {"dev", "sandbox", "preview"})
  public void NonSpecificMapTests_008_VerifyMapIsDisplayedForAnons() {
    WikiBasePageObject base = new WikiBasePageObject();
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    InteractiveMapPageObject selectedMap =
        specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
    selectedMap.verifyMapOpened();
  }

  @Test(enabled = false,
      groups = {"NonSpecificMapTests_009", "NonSpecificMapTests", "InteractiveMaps"})
  @Execute(asUser = User.USER)
  public void NonSpecificMapTests_009_VerifyCreateMapButtonUnderContribution() {
    WikiBasePageObject base = new WikiBasePageObject();
    InteractiveMapsPageObject specialMaps = base.openSpecialInteractiveMaps(wikiURL);
    CreateAMapComponentObject createMap = specialMaps.clickCreateAMapUnderContributeButton();
    createMap.verifyRealMapAndCustomMapButtons();
  }

  @Test(groups = {"NonSpecificMapTests_010", "NonSpecificMapTests", "InteractiveMaps"})
  @DontRun(env = {"dev", "sandbox", "preview"})
  public void NonSpecificMapTests_010_VerifyFragmentContentTagVisibility() {
    WikiBasePageObject base = new WikiBasePageObject();
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    InteractiveMapPageObject selectedMap =
        specialMap.openEscapedFragmentMap(wikiURL, InteractiveMapsContent.ESCAPED_FRAGMENT_MAP_ID);
    selectedMap.verifyEscapedFragmentMetaTag();
  }

  @Test(groups = {"NonSpecificMapTests_011", "NonSpecificMapTests", "InteractiveMaps"})
  @DontRun(env = {"dev", "sandbox", "preview"})
  public void NonSpecificMapTests_011_VerifyEscapedFragmentPageContent() {
    WikiBasePageObject base = new WikiBasePageObject();
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    InteractiveMapPageObject selectedMap =
        specialMap.openEscapedFragmentMap(wikiURL, InteractiveMapsContent.ESCAPED_FRAGMENT_MAP_ID);
    selectedMap.verifyPoiCategoryTitle();
    selectedMap.verifyPoiPointTitle();
    selectedMap.verifyPoiPointDescription();
  }

  @RelatedIssue(issueID = "SUS-1775", comment = "Product is broken")
  @Test(groups = {"NonSpecificMapTests_012", "NonSpecificMapTests", "InteractiveMaps"})
  @DontRun(env = {"dev", "sandbox", "preview"})
  @NetworkTrafficDump
  public void NonSpecificMapTests_012_VerifyLinkedArticlePontoRequest() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.getUrl(InteractiveMapsContent.MOBILE_APPS_MAP);
    InteractiveMapPageObject map = new InteractiveMapPageObject();
    map.clickOnPin(0, true);
    networkTrafficInterceptor.startIntercepting(InteractiveMapsContent.MOBILE_APPS_MAP);
    map.clickOpenPinTitle(true);
    map.verifyPontoGetRequest(networkTrafficInterceptor);
  }
}
