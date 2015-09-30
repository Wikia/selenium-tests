package com.wikia.webdriver.testcases.widgettests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SoundCloudWidgetPageObject;

/**
 * @ownership: Content X-Wing
 */
@Test(groups = {"SoundCloudWidgetTests", "WidgetTests"})
public class SoundCloudTests extends NewTestTemplate {

  @Test(groups = "SoundCloudWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void SoundCloudWidgetTest_001_isLoaded() {
    SoundCloudWidgetPageObject widget = new SoundCloudWidgetPageObject(driver);

    widget.create().navigate(wikiURL);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "SoundCloudWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void SoundCloudWidgetTest_002_areLoaded() {
    SoundCloudWidgetPageObject widget = new SoundCloudWidgetPageObject(driver);

    widget.createMultiple().navigate(wikiURL);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }
}
