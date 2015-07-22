package com.wikia.webdriver.testcases.specialpagestests;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Patrick on 17/07/2015. Create automated test for Under the hood preferences due to
 * missed P2 SERVICES-528
 */
public class UnderTheHoodPreferencesTests extends NewTestTemplate {

  @Test(groups = {"UnderTheHoodPreferencesTest", "UnderTheHoodPreference_001"})
  @Execute(asUser = User.USER_5, onWikia = URLsContent.VE_ENABLED_WIKI)
  public void UnderTheHoodPreference_001_Use_advanced_recent_changes() {

    PreferencesPageObject preferences = new WikiBasePageObject(driver)
        .openSpecialPreferencesPage(wikiURL);
    preferences
        .setAdvancedRecentChangesCheckboxValueToDefaultUnchecked()
        .setAdvancedRecentChangesCheckbox()
        .clickSaveButton();

    Assertion.assertTrue(preferences.getAdvancedRecentChangesCheckboxValue());
  }
}
