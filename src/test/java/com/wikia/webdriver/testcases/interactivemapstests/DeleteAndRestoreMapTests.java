package com.wikia.webdriver.testcases.interactivemapstests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.InteractiveMapsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.DeleteAMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;

/**
 * @author: Lukasz Nowak and RodriGomez
 * @ownership: Mobile Web
 */

public class DeleteAndRestoreMapTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(enabled = false, // MAIN-4538
      groups = {"DeleteAndRestoreMapTests_001", "DeleteAndRestoreMapTests", "InteractiveMaps"})
  public void DeleteAndRestoreMapTests_001_DeleteAndRestoreMapAsAMapOwner() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userName, credentials.password, wikiURL);
    InteractiveMapPageObject selectedMap =
        base.openInteractiveMapById(wikiURL, InteractiveMapsContent.MAP_TO_DELETE_AND_RESTORE[0]);
    DeleteAMapComponentObject deleteMapModal = selectedMap.deleteMap();
    deleteMapModal.deleteMap();
    selectedMap =
        base.openInteractiveMapById(wikiURL, InteractiveMapsContent.MAP_TO_DELETE_AND_RESTORE[0]);
    selectedMap.verifyMapOpenedForDeleteMapTests();
    selectedMap.restoreMap();
    selectedMap.verifyMapOpenedForDeleteMapTests();
  }

  @Test(groups = {"DeleteAndRestoreMapTests_002", "DeleteAndRestoreMapTests", "InteractiveMaps"})
  public void DeleteAndRestoreMapTests_002_DeleteMapByNotOwner() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userName2, credentials.password2, wikiURL);
    InteractiveMapPageObject selectedMap =
        base.openInteractiveMapById(wikiURL, InteractiveMapsContent.MAP_TO_DELETE_AND_RESTORE[1]);
    DeleteAMapComponentObject deleteMapModal = selectedMap.deleteMap();
    deleteMapModal.clickDeleteMap();
    Assertion.assertEquals(deleteMapModal.getDeleteMapError(),
        InteractiveMapsContent.MAP_DELETE_ERROR);
  }

  @RelatedIssue(issueID = "QAART-557")
  @Test(groups = {"DeleteAndRestoreMapTests_003", "DeleteAndRestoreMapTests", "InteractiveMaps"},
      enabled = false)
  public void DeleteAndRestoreMapTests_003_StaffUserCanDeleteMap() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    InteractiveMapPageObject selectedMap =
        base.openInteractiveMapById(wikiURL, InteractiveMapsContent.MAP_TO_DELETE_AND_RESTORE[2]);
    selectedMap.verifyMapOpenedForDeleteMapTests();
    DeleteAMapComponentObject deleteMapModal = selectedMap.deleteMap();
    deleteMapModal.deleteMap();
    selectedMap =
        base.openInteractiveMapById(wikiURL, InteractiveMapsContent.MAP_TO_DELETE_AND_RESTORE[2]);
    selectedMap.verifyMapOpenedForDeleteMapTests();
    selectedMap.restoreMap();
    selectedMap.verifyMapOpenedForDeleteMapTests();
  }
}
