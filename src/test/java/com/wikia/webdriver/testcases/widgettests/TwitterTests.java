package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.TwitterWidgetPageObject;

import org.testng.annotations.Test;

/**
 * @ownership: Content X-Wing
 */
public class TwitterTests extends NewTestTemplate {

  @Test
  @Execute(onWikia = "mercuryautomationtesting")
  public void TwitterWidgetTest_001_isLoaded() {
    TwitterWidgetPageObject twitterWidget = new TwitterWidgetPageObject(driver);

    twitterWidget.createAndNavigate(wikiURL);
    Assertion.assertTrue(twitterWidget.isLoadedOnOasis(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test
  @Execute(onWikia = "mercuryautomationtesting")
  public void TwitterWidgetTest_002_areLoaded() {
    TwitterWidgetPageObject twitterWidget = new TwitterWidgetPageObject(driver);

    twitterWidget.createMultiple().navigate(wikiURL);

    Assertion.assertTrue(
        twitterWidget.areLoadedOnOasis(),
        MercuryMessages.INVISIBLE_MSG
    );
  }

  @Test(groups = "TwitterWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void TwitterWidgetTest_003_isErrorPresent() {
    TwitterWidgetPageObject twitterWidget = new TwitterWidgetPageObject(driver);

    twitterWidget.createIncorrectAndNavigate(wikiURL);
    Assertion.assertTrue(twitterWidget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
