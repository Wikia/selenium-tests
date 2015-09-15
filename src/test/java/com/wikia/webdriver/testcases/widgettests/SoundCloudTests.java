package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SoundCloudWidgetPageObject;

import org.testng.annotations.Test;

/**
 * @ownership: Content X-Wing
 */
@Test(groups = {"SoundCloudWidgetTests", "WidgetTests"})
public class SoundCloudTests extends NewTestTemplate {

  @Test(groups = "SoundCloudWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void SoundCloudWidgetTest_001_isLoaded() {
    SoundCloudWidgetPageObject soundCloudWidget = new SoundCloudWidgetPageObject(driver);

    soundCloudWidget.createAndNavigate(wikiURL);
    Assertion.assertTrue(soundCloudWidget.isLoadedOnOasis(), MercuryMessages.INVISIBLE_MSG);
  }
}
