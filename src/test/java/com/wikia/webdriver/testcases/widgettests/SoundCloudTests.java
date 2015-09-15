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
public class SoundCloudTests extends NewTestTemplate {

  @Test
  @Execute(onWikia = "mercuryautomationtesting")
  public void SoundCloudWidgetTest_001_isLoaded() {
    SoundCloudWidgetPageObject soundCloudWidget = new SoundCloudWidgetPageObject(driver);

    soundCloudWidget.create().navigate(wikiURL);
    Assertion.assertTrue(soundCloudWidget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test
  @Execute(onWikia = "mercuryautomationtesting")
  public void SoundCloudWidgetTest_002_areLoaded() {
    SoundCloudWidgetPageObject soundCloudWidget = new SoundCloudWidgetPageObject(driver);

    soundCloudWidget.createMultiple().navigate(wikiURL);

    Assertion.assertTrue(
        soundCloudWidget.areLoaded(),
        MercuryMessages.INVISIBLE_MSG
    );
  }
}
