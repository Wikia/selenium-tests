package com.wikia.webdriver.testcases.edithubtests;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.dataprovider.HubsDataProvider;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialEditHubPageObject;

import org.testng.annotations.Test;

/**
 * 1. Open edit hub dashboard and check if calendar exists
 *
 * @author Damian 'kvas' Jóźwiak
 * @ownership Content X-Wing
 */
public class EditHubTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = {"EditHub_001", "EditHub"},
      dataProviderClass = HubsDataProvider.class,
      dataProvider = "provideHubDBName")
  public void EditHub_001_dashboardSelectVertical(String hubDBName) {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    SpecialEditHubPageObject
        pageObject =
        base.openSpecialEditHub(urlBuilder.getUrlForWiki(hubDBName));
    pageObject.verifyCalendarAppears();
  }
}
