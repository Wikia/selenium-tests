package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SpotifykWidgetPageObject;

import org.testng.annotations.Test;

/**
 * @ownership: Content X-Wing
 */
public class PollsnackTests extends NewTestTemplate {

  @Test
  @Execute(onWikia = "mercuryautomationtesting")
  public void PollsnackWidgetTest_001_isLoaded() {
    SpotifykWidgetPageObject pollsnackWidget = new SpotifykWidgetPageObject(driver);

    pollsnackWidget.createAndNavigate(wikiURL);
    Assertion.assertTrue(pollsnackWidget.isLoadedOnOasis(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "PollsnackWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PollsnackWidgetTest_003_isErrorPresent() {
    SpotifykWidgetPageObject pollsnackWidget = new SpotifykWidgetPageObject(driver);

    pollsnackWidget.createIncorrectAndNavigate(wikiURL);
    Assertion.assertTrue(pollsnackWidget.isErrorPresentOnOasis(), MercuryMessages.INVISIBLE_MSG);
  }
}
