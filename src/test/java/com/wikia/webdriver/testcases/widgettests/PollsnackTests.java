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
@Test(groups = {"PollsnackWidgetTests", "WidgetTests"})
public class PollsnackTests extends NewTestTemplate {

  @Test(groups = "PollsnackWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PollsnackWidgetTest_001_isLoaded() {
    PollsnackWidgetPageObject pollsnackWidget = new PollsnackWidgetPageObject(driver);

    pollsnackWidget.createAndNavigate(wikiURL);
    Assertion.assertTrue(pollsnackWidget.isLoadedOnOasis(), MercuryMessages.INVISIBLE_MSG);
  }
}
