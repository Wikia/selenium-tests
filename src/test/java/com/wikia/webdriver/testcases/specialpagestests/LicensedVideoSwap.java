package com.wikia.webdriver.testcases.specialpagestests;

import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.licensedvideoswap.LicensedVideoSwapHistoryPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.licensedvideoswap.LicensedVideoSwapPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by kenkouot on 3/20/14.
 * @ownership Content X-Wing
 */
public class LicensedVideoSwap extends NewTestTemplate {

  LicensedVideoSwapPageObject licensedVideoSwap;

  Credentials credentials = Configuration.getCredentials();

  @BeforeMethod(alwaysRun = true)
  public void lvsSetup() {
    WikiBasePageObject wiki = new WikiBasePageObject(driver);
    wiki.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    licensedVideoSwap = wiki.openLicensedVideoSwap(wikiURL);
  }

  @Test(groups = {"LicensedVideoSwap_001", "LicensedVideoSwap", "Media"})
  public void LicensedVideoSwap_001_navigateToHistory() {
    LicensedVideoSwapHistoryPageObject historyPage = licensedVideoSwap.navigateToHistoryPage();
    historyPage.verifyOnHistoryPage();
  }

  @Test(groups = {"LicensedVideoSwap_002", "LicensedVideoSwap", "Media"})
  public void LicensedVideoSwap_002_navigateToHistoryAndBackToLvs() {
    LicensedVideoSwapHistoryPageObject historyPage = licensedVideoSwap.navigateToHistoryPage();
    historyPage.verifyOnHistoryPage();
    historyPage.navigateToLvsPage();
    licensedVideoSwap.verifyOnLvsPage();
  }

  @RelatedIssue(issueID = "CONCF-438", comment = "Feature will be deprecated. No need to test manually")
  @Test(
      enabled = false, //CONCF-438
      groups = {"LicensedVideoSwap_003", "LicensedVideoSwap", "Media"})
  public void LicensedVideoSwap_003_swap() {
    LicensedVideoSwapHistoryPageObject historyPage = licensedVideoSwap.navigateToHistoryPage();
    historyPage.verifyOnHistoryPage();
    historyPage.clickUndoSwapLink();
    historyPage.verifyUndoSucceeded();
    historyPage.navigateToLvsPage();
    licensedVideoSwap.verifySwapVideo();
  }
}
