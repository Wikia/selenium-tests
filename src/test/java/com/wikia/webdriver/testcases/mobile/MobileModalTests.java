package com.wikia.webdriver.testcases.mobile;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileModalComponentObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author PMG <p/> Below test cases are executed against mobileregressiontesting wikiName with
 *         CHROMEMOBILE browser 1. Verify that user is able to go to next image in modal 2. Verify
 *         that user is able to go to previous image in modal 3. Verify that user is able to
 *         hide/show top bar in modal 4. Verify that user is able to go back to page from modal
 *         using back button 5. Verify that user is back on the same place of page when he go in and
 *         back from modal
 */

public class MobileModalTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @BeforeMethod(alwaysRun = true)
  public void logIn() {
    new MobileBasePageObject(driver).loginDropDown(credentials.userName, credentials.password);
  }

  @Test(groups = {"MobileModal_001", "MobileModal", "Mobile"})
  public void MobileModal_001_nextImage() {
    MobileArticlePageObject mobile = new MobileArticlePageObject(driver);
    mobile.openModals(wikiURL);
    MobileModalComponentObject modal = mobile.clickModal();
    String current = modal.getCurrentImageUrl();
    modal.goToNextImage();
    Assertion.assertNotEquals(current, modal.getCurrentImageUrl());
    modal.closeModal();
    modal.verifyModalClosed();
  }

  @Test(groups = {"MobileModal_002", "MobileModal", "Mobile"})
  public void MobileModal_002_previousImage() {
    MobileArticlePageObject mobile = new MobileArticlePageObject(driver);
    mobile.openModals(wikiURL);
    MobileModalComponentObject modal = mobile.clickModal();
    String current = modal.getCurrentImageUrl();
    modal.goToPreviousImage();
    Assertion.assertNotEquals(current, modal.getCurrentImageUrl());
    modal.closeModal();
    modal.verifyModalClosed();
  }

  @Test(groups = {"MobileModal_003", "MobileModal", "Mobile"})
  public void MobileModal_003_topBarVisibleOrNot() {
    MobileArticlePageObject mobile = new MobileArticlePageObject(driver);
    mobile.openModals(wikiURL);
    MobileModalComponentObject modal = mobile.clickModal();
    modal.verifyTopBarVisible();
    modal.hideTopBar();
    modal.verifyTopBarHidden();
    modal.showTopBar();
    modal.verifyTopBarVisible();
  }

  @Test(groups = {"MobileModal_004", "MobileModal", "Mobile"})
  public void MobileModal_004_backButton() {
    MobileArticlePageObject mobile = new MobileArticlePageObject(driver);
    mobile.openModals(wikiURL);
    MobileModalComponentObject modal = mobile.clickOpenedImage(5);
    modal.closeModalWithBackButton();
    modal.verifyModalClosed();
  }

  @Test(groups = {"MobileModal_005", "MobileModal", "Mobile"}, enabled = false)
  public void MobileModal_005_positionAfterCloseModal() {
    MobileArticlePageObject mobile = new MobileArticlePageObject(driver);
    mobile.openModals(wikiURL);
    mobile.scrollToImage(5);
    Long positionBeforeModal = mobile.getPosition();
    MobileModalComponentObject modal = mobile.clickOpenedImage(5);
    modal.closeModal();
    modal.verifyModalClosed();
    modal.verifyPositionTheSame(positionBeforeModal);
  }

}
