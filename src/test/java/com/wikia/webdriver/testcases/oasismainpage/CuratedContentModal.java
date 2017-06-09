package com.wikia.webdriver.testcases.oasismainpage;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.CuratedContentToolModal;
import com.wikia.webdriver.pageobjectsfactory.pageobject.oasis.MainPage;

import org.testng.annotations.Test;

public class CuratedContentModal extends NewTestTemplate {

  @Test(groups = {"CuratedContentModal_001", "CuratedContent"})
  @Execute(asUser = User.STAFF)
  public void CuratedContentModal_001_modalVisible() {
    MainPage main = new MainPage().open();
    CuratedContentToolModal modal = main.clickCuratedContentToolButton();
    Assertion.assertTrue(modal.isModalVisible());
  }

  @Execute(asUser = User.STAFF)
  @Test(groups = {"CuratedContentModal_002", "CuratedContent"})
  public void CuratedContentModal_002_MercuryNavBarShouldNotBeVisible() {
    MainPage main = new MainPage().open();
    CuratedContentToolModal modal = main.clickCuratedContentToolButton();

    Assertion.assertTrue(modal.isModalVisible());
    Assertion.assertFalse(new TopBar().isNavigationBarVisible());
  }
}
