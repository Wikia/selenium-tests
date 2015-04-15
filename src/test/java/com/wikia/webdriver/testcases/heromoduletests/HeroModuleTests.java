package com.wikia.webdriver.testcases.heromoduletests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.ModularMainPageObject;

import org.testng.annotations.Test;

/**
 * Created by RodriGomez on 02/04/15.
 * Set of Test Cases found on https://wikia-inc.atlassian.net/browse/DAT-2563
 *
 * TC18: Add a new MoM image, refresh the page and verify it is still visible
 * TC04: upload a new image and verify it is displayed immediately
 * TC09: Make,publish and verify changes for description fields are visible immediately
 * TC15: check anons and regular users cant modify MoM
 * TC06: verify discarding an image will display previous state
 * TC05: verify dragging to reposition image is working
 * TC10: delete description field and then verify a promotional message and Publish button disability
 * TC11: delete text from desc field and click discard to view previous state
 * TC12: delete text from desc field and populate the field with a character to check Publish button is enabled
 */
public class HeroModuleTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  private final static String WIKI_NAME = "momrod2";

  @Test(groups = {"HeroModuleTests", "HeroModuleTests_001"})
  public void HeroModuleTests_001_VerifyImageVisibilityAfterRefresh() {
    ModularMainPageObject mom = new ModularMainPageObject(driver);
    mom.openWikiPage(urlBuilder.getUrlForWiki(WIKI_NAME));
    mom.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    String prevMoMSrc = mom.getMoMSrc();
    mom.selectFileToUpload(PageContent.FILE);
    mom.verifyDragToRepositionText();
    mom.clickPublishButton();
    String newMoMSrc = mom.getMoMSrc();
    mom.verifySrcTxtAreDifferent(prevMoMSrc, newMoMSrc);
    mom.refreshPage();
    mom.verifySrcTxtAreDifferent(prevMoMSrc, newMoMSrc);
  }

  @Test(groups = {"HeroModuleTests", "HeroModuleTests_002"})
  public void HeroModuleTests_002_VerifyImmediateImageVisibility() {
    ModularMainPageObject mom = new ModularMainPageObject(driver);
    mom.openWikiPage(urlBuilder.getUrlForWiki(WIKI_NAME));
    mom.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    mom.selectFileToUpload(PageContent.FILE);
    mom.verifyDragToRepositionText();
    mom.clickPublishButton();
    mom.verifyMoMImage();
  }

  @Test(groups = {"HeroModuleTests", "HeroModuleTests_003"})
  public void HeroModuleTests_003_VerifyImmediateChangesForDescription() {
    ModularMainPageObject mom = new ModularMainPageObject(driver);
    mom.openWikiPage(urlBuilder.getUrlForWiki(WIKI_NAME));
    mom.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    mom.clickEditDescriptionLink();
    String momDescription = mom.getTimeStamp();
    mom.typeMoMDescription(momDescription);
    mom.clickDescriptionPublishButton();
    mom.verifyEditedAndPublishedDescriptions(momDescription);
  }

  @Test(groups = {"HeroModuleTests", "HeroModuleTests_004"})
  public void HeroModuleTests_004_OnlyAdminsAndStaffCanModify() {
    ModularMainPageObject mom = new ModularMainPageObject(driver);
    mom.openWikiPage(urlBuilder.getUrlForWiki(WIKI_NAME));
    mom.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    mom.verifyAdminStaffButtons();
    mom.logOut();
    mom.logInCookie(credentials.userName, credentials.password, wikiURL);
    mom.verifyNoAdminStaffButtons();
    mom.logOut();
    mom.verifyNoAdminStaffButtons();
  }

  @Test(groups = {"HeroModuleTests", "HeroModuleTests_005"})
  public void HeroModuleTests_005_VerifyImageDiscardDisplaysPreviousState() {
    ModularMainPageObject mom = new ModularMainPageObject(driver);
    mom.openWikiPage(urlBuilder.getUrlForWiki(WIKI_NAME));
    mom.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    String imgSrc = mom.getMoMSrc();
    mom.selectFileToUpload(PageContent.FILE);
    mom.verifyDragToRepositionText();
    mom.clickPublishButton();
    String newImgSrc = mom.getMoMSrc();
    mom.verifySrcTxtAreDifferent(imgSrc, newImgSrc);
  }

  @Test(groups = {"HeroModuleTests", "HeroModuleTests_006"})
  public void HeroModuleTests_006_VerifyDraggingToRepositionFunctionality() {

  }

  @Test(groups = {"HeroModuleTests", "HeroModuleTests_007"})
  public void HeroModuleTests_007_DeleteDescriptionAndCheckPublishButton() {
    ModularMainPageObject mom = new ModularMainPageObject(driver);
    mom.openWikiPage(urlBuilder.getUrlForWiki(WIKI_NAME));
    mom.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    mom.clickEditDescriptionLink();
    mom.deleteDescriptionEditorContent();
    mom.verifyPublishButtonDisability();
    mom.verifyWikiaPromotionalMessage();
  }

  @Test(groups = {"HeroModuleTests", "HeroModuleTests_008"})
  public void HeroModuleTests_008_DescriptionFieldDiscardChanges() {
    ModularMainPageObject mom = new ModularMainPageObject(driver);
    mom.openWikiPage(urlBuilder.getUrlForWiki(WIKI_NAME));
    mom.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    String publishedText = mom.getDescriptionText();
    mom.clickEditDescriptionLink();
    String randomString = mom.getRandomDigits(9);
    mom.addRandomTextToDescriptionField(randomString);
    mom.clickDiscardButton();
    mom.verifyPublishedTextAndEditor(publishedText);
  }
}
