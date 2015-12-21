package com.wikia.webdriver.testcases.oasismainpage;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.CuratedContentToolModal;
import com.wikia.webdriver.pageobjectsfactory.pageobject.oasis.MainPage;

import org.testng.annotations.Test;

public class CuratedContentModal extends NewTestTemplate {

  @Test(groups = {"CuratedContentModal_001", "CuratedContent"})
  @Execute(asUser = User.STAFF)
  public void CuratedContentModal_001_modalVisible() {
    MainPage main = new MainPage(driver).open();
    CuratedContentToolModal modal = main.clickCuratedContentToolButton();
    Assertion.assertTrue(modal.isModalVisible());
  }
}
