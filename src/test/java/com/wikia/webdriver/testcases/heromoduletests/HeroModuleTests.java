package com.wikia.webdriver.testcases.heromoduletests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.ModularMainPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.testng.annotations.BeforeMethod;
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
 */
public class HeroModuleTests extends NewTestTemplate{

  Credentials credentials = config.getCredentials();

  private final static String WIKI_NAME = "momrod2";

  @BeforeMethod(alwaysRun = true)
  public void hero_module_000_navigation() {
    urlBuilder.getUrlForWiki(URLsContent.HERO_MODULE_WIKI);
    PageObjectLogging.log("openMomPage", "MoM main wiki page opened", true);
    }

  @Test(groups = {"HeroModuleTests", "HeroModuleTests_001"})
  public void HeroModuleTests_001_VerifyImageVisibilityAfterRefresh() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    ModularMainPageObject mom = base.openModularMainPageWiki(wikiURL);//fix navigation to wiki, dont append
    mom.clickUpdateCoverImageLink();
    mom.selectFileToUpload(PageContent.FILE);
    mom.verifyDragToRepositionText();
    mom.clickPublishButton();
    mom.verifyMoMImage();//change this method to actually verify new image uploaded
    mom.refreshPage();
    mom.verifyMoMImage();
  }

  @Test(groups = {"HeroModuleTests", "HeroModuleTests_002"})
  public void HeroModuleTests_002_VerifyImmediateImageVisibility() {
      WikiBasePageObject base = new WikiBasePageObject(driver);
      base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
      ModularMainPageObject mom = base.openModularMainPageWiki(wikiURL);
      mom.clickUpdateCoverImageLink();
      mom.selectFileToUpload(PageContent.FILE);
      mom.verifyDragToRepositionText();
      mom.clickPublishButton();
      mom.verifyMoMImage();
  }

  @Test(groups = {"HeroModuleTests", "HeroModuleTests_003"})
  public void HeroModuleTests_003_VerifyImmediateChangesForDescription() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    ModularMainPageObject mom = base.openModularMainPageWiki(wikiURL);
    mom.clickEditDescriptionLink();
    String momDescription = mom.getTimeStamp();
    mom.typeMoMDescription();
    mom.clickDescriptionPublishButton();
    mom.verifyEditedAndPublishedDescriptions(momDescription);
  }

  @Test(groups = {"HeroModuleTests", "HeroModuleTests_004"})
  public void HeroModuleTests_004_OnlyAdminsAndStaffCanModify() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    ModularMainPageObject mom = base.openModularMainPageWiki(wikiURL);
    mom.verifyAdminStaffButtons();
    mom.logInCookie(credentials.userName, credentials.password, wikiURL);
    mom.verifyNoAdminStaffButtons();
    mom.logOut();
    mom.verifyNoAdminStaffButtons();
  }

  @Test(groups = {"HeroModuleTests", "HeroModuleTests_005"})
  public void HeroModuleTests_005_VerifyImageDiscardDisplaysPreviousState() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    ModularMainPageObject mom = base.openModularMainPageWiki(wikiURL);
    String imgSrc = mom.getMoMSrc();
    mom.clickUpdateCoverImageLink();
    mom.selectFileToUpload(PageContent.FILE);
    mom.verifyDragToRepositionText();
    mom.clickPublishButton();
    String newImgSrc = mom.getMoMSrc();
    mom.verifySrcTxtAreDifferent(imgSrc, newImgSrc);
  }
}

