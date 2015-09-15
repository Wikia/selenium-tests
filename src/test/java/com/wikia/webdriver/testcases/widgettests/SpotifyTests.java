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
    SpotifyWidgetPageObject spotifyWidget = new SpotifyWidgetPageObject(driver);

    spotifyWidget.createAndNavigate(wikiURL);
    Assertion.assertTrue(spotifyWidget.isLoadedOnOasis(), MercuryMessages.INVISIBLE_MSG);
  }
}
