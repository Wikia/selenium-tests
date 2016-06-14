package com.wikia.webdriver.testcases.hubstests;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.dataprovider.HubsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HubBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject.HubName;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialManageWikiaHome;

public class HubsTests extends NewTestTemplate {

  @DataProvider
  private final Object[][] provideHubName() {
    return new Object[][] {{HubName.VIDEO_GAMES}, {HubName.ENTERTAINMENT}, {HubName.LIFESTYLE}};
  }

  @Test(enabled = false, groups = {"HubsTest_001", "Hubs", "Smoke4"},
      dataProviderClass = HubsDataProvider.class, dataProvider = "provideHubDBName")
  public void HubsTest_001_verifyMosaicSliderShowsImagesOnHover(String hubDBName) {
    HomePage home = new HomePage();
    HubBasePageObject hub = home.openHubByUrl(urlBuilder.getUrlForWiki(hubDBName));
    hub.verifyMosaicSliderImages();

    hub.mosaicSliderHoverOverImage(4);
    String currentLargeImageDescription = hub.mosaicSliderGetCurrentLargeImageDescription();

    hub.mosaicSliderHoverOverImage(3);
    hub.mosaicSliderVerifyLargeImageDescriptionDifferent(currentLargeImageDescription);
    currentLargeImageDescription = hub.mosaicSliderGetCurrentLargeImageDescription();

    hub.mosaicSliderHoverOverImage(2);
    hub.mosaicSliderVerifyLargeImageDescriptionDifferent(currentLargeImageDescription);
    currentLargeImageDescription = hub.mosaicSliderGetCurrentLargeImageDescription();

    hub.mosaicSliderHoverOverImage(1);
    hub.mosaicSliderVerifyLargeImageDescriptionDifferent(currentLargeImageDescription);
    currentLargeImageDescription = hub.mosaicSliderGetCurrentLargeImageDescription();

    hub.mosaicSliderHoverOverImage(0);
    hub.mosaicSliderVerifyLargeImageDescriptionDifferent(currentLargeImageDescription);
  }

  /**
   * verify that from community module has its elements
   */
  @Test(groups = {"HubsTest_002", "Hubs"}, dataProviderClass = HubsDataProvider.class,
      dataProvider = "provideHubDBName")
  public void HubsTest_002_verifyFromCommunityModuleHasItsElements(String hubDBName) {
    HomePage home = new HomePage();
    HubBasePageObject hub = home.openHubByUrl(urlBuilder.getUrlForWiki(hubDBName));
    hub.verifyFromModuleHasImages();
    hub.verifyFromModuleHasHeadline();
    hub.verifyFromModuleHasUserAndWikiField();
    hub.verifyFromModuleHasQuatation();
  }

  /**
   * click on 'Get Promoted' button verify if modal appears and if its fields/buttons are working
   * properly
   */
  @Test(groups = {"HubsTest_003", "Hubs"}, dataProviderClass = HubsDataProvider.class,
      dataProvider = "provideHubDBName")
  @Execute(asUser = User.USER_2)
  public void HubsTest_003_VerifyArticleSuggestionWorksProperly(String hubDBName) {
    HubBasePageObject hub =
        new HubBasePageObject(driver).openHubByUrl(urlBuilder.getUrlForWiki(hubDBName));
    hub.clickGetPromoted();
    hub.verifySuggestAVideoOrArticleModalAppeared();
    hub.verifySuggestAVideoOrArticleModalTopic();
    hub.verifySuggestVideoOrArticleButtonNotClickable();
    hub.suggestArticleTypeIntoWhatVideoField(hub.getTimeStamp());
    hub.suggestArticleTypeIntoWhyCoolField(hub.getTimeStamp());
    hub.verifySuggestVideoOrArticleButtonClickable();
    hub.closeSuggestAVideoOrArticleByXButton();
    hub.verifySuggestAVideoOrArticleModalDisappeared();
    hub.clickGetPromoted();
    hub.verifySuggestAVideoOrArticleModalAppeared();
    hub.verifySuggestAVideoOrArticleModalTopic();
    hub.verifySuggestVideoOrArticleButtonNotClickable();
    hub.closeSuggestAVideoOrArticleCancelButton();
    hub.verifySuggestAVideoOrArticleModalDisappeared();
  }

  /**
   * skipped due "promoted wikis" feature
   */
  @Test(enabled = false, groups = {"HubsTest_004", "Hubs"})
  @Execute(asUser = User.STAFF)
  public void HubsTests_004_VerifyCorporateSlotCollection() {
    SpecialManageWikiaHome manageWikia = new SpecialManageWikiaHome().open();
    Map<String, Integer> slotDesiredSetup = manageWikia.getSlotSetup();
    HomePage home = new HomePage().open("wikia");
    Map<String, Integer> slotCurrentSetup = home.getVisualizationWikisSetup();
    home.verifyVisualizationURLs(slotDesiredSetup, slotCurrentSetup);
  }

  @Test(groups = {"HubsTest_005", "Hubs", "new"}, enabled = false)
  @RelatedIssue(issueID = "MAIN-5636",
      comment = "Second issue: MAIN-5377. Product is not about to fix both of the issues. "
          + "Test disabled")
  /**
   * Verify that each language drop down goes to the correct page
   */
  public void HubsTest_005_VerifyLanguagesSelection() {
    HomePage home = new HomePage().open("wikia");
    home.verifyLanguageDropdownURLs();
  }

  /**
   * Verify that links in WikiaBar are working
   */
  @Test(enabled = false, dataProvider = "provideHubName", groups = {"HubsTest_006", "Hubs"})
  @RelatedIssue(issueID = "MAIN-6092", comment = "Product requested that this test is disabled. New"
      + "tests will be create wih MAIN-6101")
  public void HubsTest_006_VerifyLinkInWikiaBar(HubName hubName) {
    HomePage home = new HomePage();
    home.open("wikia");
    HubBasePageObject hub = new HubBasePageObject(driver);
    hub.clickWikiaBarLink(hubName);
    hub.verifyHubTitle(hubName);
  }
}
