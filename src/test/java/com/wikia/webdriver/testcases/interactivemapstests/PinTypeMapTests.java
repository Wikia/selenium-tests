package com.wikia.webdriver.testcases.interactivemapstests;

import com.wikia.webdriver.common.contentpatterns.InteractiveMapsContent;
import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.CreatePinTypesComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapsPageObject;

import org.testng.annotations.Test;

public class PinTypeMapTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = {"PinTypeMapTests_001", "PinTypeMapTests", "InteractiveMaps"})
  @DontRun(env = {"dev", "sandbox", "preview"})
  @Execute(asUser = User.USER)
  @RelatedIssue(issueID = "QAART-690", comment = "functionality status is deprecated, " +
          "monitor the issue to find out resolution")
  public void PinTypeMapTests_001_VerifyImageValidationInPinTypeModal() {
    WikiBasePageObject base = new WikiBasePageObject();
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    InteractiveMapPageObject
        selectedMap =
        specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
    selectedMap.verifyMapOpened();
    selectedMap.clickEditPinTypesButton();
    CreatePinTypesComponentObject pinTypeModal = new CreatePinTypesComponentObject(driver);
    pinTypeModal.verifyPinTypesDialog();
    pinTypeModal.selectFileToUpload(PageContent.SMALLFILE, "Small image");
    pinTypeModal.verifyErrorExists();
    pinTypeModal.selectFileToUpload(PageContent.BROKENEXTENSIONFILE, "Image with wrong extension");
    pinTypeModal.verifyErrorExists();
  }

  @Test(groups = {"PinTypeMapTests_002", "PinTypeMapTests", "InteractiveMaps"})
  @DontRun(env = {"dev", "sandbox", "preview"})
  @Execute(asUser = User.USER)
  @RelatedIssue(issueID = "QAART-690", comment = "functionality status is deprecated, " +
          "monitor the issue to find out resolution")
  public void PinTypeMapTests_002_VerifyClickingAddAnotherPinType() {
    WikiBasePageObject base = new WikiBasePageObject();
    InteractiveMapsPageObject specialMap = base.openSpecialInteractiveMaps(wikiURL);
    InteractiveMapPageObject
        selectedMap =
        specialMap.clickMapWithIndex(InteractiveMapsContent.SELECTED_MAP_INDEX);
    selectedMap.verifyMapOpened();
    selectedMap.clickEditPinTypesButton();
    CreatePinTypesComponentObject pinTypesDialog = new CreatePinTypesComponentObject(driver);
    pinTypesDialog.verifyPinTypesDialog();
    pinTypesDialog.savePinTypesListState();
    pinTypesDialog.clickAddAnotherPinType();
    pinTypesDialog.verifyAddAnotherPinType();
  }
}
