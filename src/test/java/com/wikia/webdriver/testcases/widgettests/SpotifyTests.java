package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SpotifyWidgetPageObject;

import org.testng.annotations.Test;

/**
 * @ownership: Content X-Wing
 */
@Test(groups = {"SpotifyWidgetTests", "WidgetTests"})
public class SpotifyTests extends NewTestTemplate {

  @Test(groups = "SpotifyWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void SpotifyWidgetTest_001_isLoaded() {
    SpotifyWidgetPageObject widget = new SpotifyWidgetPageObject(driver);

    widget.create().navigate(wikiURL);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "SpotifyWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void SpotifyWidgetTest_002_areLoaded() {
    SpotifyWidgetPageObject widget = new SpotifyWidgetPageObject(driver);

    widget.createMultiple().navigate(wikiURL);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "SpotifyWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void SpotifyWidgetTest_003_isErrorPresent() {
  SpotifyWidgetPageObject widget = new SpotifyWidgetPageObject(driver);

    widget.createIncorrect().navigate(wikiURL);
    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
