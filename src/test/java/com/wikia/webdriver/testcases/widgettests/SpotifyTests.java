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
public class SpotifyTests extends NewTestTemplate {

  @Test(groups = "SpotifyWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void SpotifyWidgetTest_001_isLoaded() {
    SpotifyWidgetPageObject spotifyWidget = new SpotifyWidgetPageObject(driver);

    spotifyWidget.create().navigate(wikiURL);
    Assertion.assertTrue(spotifyWidget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "SpotifyWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void SpotifyWidgetTest_002_areLoaded() {
    SpotifyWidgetPageObject spotifyWidget = new SpotifyWidgetPageObject(driver);

    spotifyWidget.createMultiple().navigate(wikiURL);

    Assertion.assertTrue(
        spotifyWidget.areLoaded(),
        MercuryMessages.INVISIBLE_MSG
    );
  }

  @Test(groups = "SpotifyWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void SpotifyWidgetTest_003_isErrorPresent() {
  SpotifyWidgetPageObject spotifyWidget = new SpotifyWidgetPageObject(driver);

    spotifyWidget.createIncorrect().navigate(wikiURL);
    Assertion.assertTrue(spotifyWidget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
