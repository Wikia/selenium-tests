package com.wikia.webdriver.testcases.specialpagestests;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Patrick on 17/07/2015.
 * Create automated test for Under the hood preferences due to missed P2 SERVICES-528
 */
public class UnderTheHoodPreferencesTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();
  WikiBasePageObject base;

  @BeforeMethod(alwaysRun = true)
  public void setup() {
    wikiURL = urlBuilder.getUrlForWiki(URLsContent.VE_ENABLED_WIKI);
    base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName5, credentials.password5, wikiURL);
  }

  @Test(groups = {"UnderTheHoodPreferencesTest", "UnderTheHoodPreference_001"})
  public void UnderTheHoodPreference_001_Use_advanced_recent_changes() {

    PreferencesPageObject preferences = base.openSpecialPreferencesPage(wikiURL);
    preferences.setAdvancedRecentChangesCheckboxValueToDefaultUnchecked();
    preferences.setAdvancedRecentChangesCheckbox();
    preferences.clickSaveButton();
    preferences.getAdvancedRecentChangesCheckboxValue();
  }

}
