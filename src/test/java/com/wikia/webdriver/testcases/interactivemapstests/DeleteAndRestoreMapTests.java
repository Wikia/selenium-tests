package com.wikia.webdriver.testcases.interactivemapstests;

import com.wikia.webdriver.common.contentpatterns.InteractiveMapsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.DeleteAMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;

import org.testng.annotations.Test;

public class DeleteAndRestoreMapTests extends NewTestTemplate {

  @Test(groups = {"DeleteAndRestoreMapTests_002", "DeleteAndRestoreMapTests", "InteractiveMaps"})
  @Execute(asUser = User.USER_2)
  public void DeleteAndRestoreMapTests_002_DeleteMapByNotOwner() {
    WikiBasePageObject base = new WikiBasePageObject();
    InteractiveMapPageObject selectedMap =
        base.openInteractiveMapById(wikiURL, InteractiveMapsContent.MAP_TO_DELETE_AND_RESTORE[1]);
    DeleteAMapComponentObject deleteMapModal = selectedMap.deleteMap();
    deleteMapModal.clickDeleteMap();
    Assertion.assertEquals(deleteMapModal.getDeleteMapError(), InteractiveMapsContent.MAP_DELETE_ERROR);
  }
}
