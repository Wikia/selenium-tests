package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PollsnackWidgetPageObject;

import org.testng.annotations.Test;

/**
 * @ownership: Content X-Wing
 */
public class PollsnackTests extends NewTestTemplate {

  @Test
  @Execute(onWikia = "mercuryautomationtesting")
  public void PollsnackWidgetTest_001_isLoaded() {
    PollsnackWidgetPageObject pollsnackWidget = new PollsnackWidgetPageObject(driver);

    pollsnackWidget.createAndNavigate(wikiURL);
    Assertion.assertTrue(pollsnackWidget.isLoadedOnOasis(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test
  @Execute(onWikia = "mercuryautomationtesting")
  public void PollsnackWidgetTest_002_areLoaded() {
    PollsnackWidgetPageObject pollsnackWidget = new PollsnackWidgetPageObject(driver);

    pollsnackWidget.create(2).navigate(wikiURL);

    Assertion.assertTrue(
        pollsnackWidget.areLoadedOnOasis(),
        MercuryMessages.INVISIBLE_MSG
    );
  }

  @Test(groups = "PollsnackWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PollsnackWidgetTest_003_isErrorPresent() {
    PollsnackWidgetPageObject pollsnackWidget = new PollsnackWidgetPageObject(driver);

    pollsnackWidget.createIncorrectAndNavigate(wikiURL);
    Assertion.assertTrue(pollsnackWidget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}

