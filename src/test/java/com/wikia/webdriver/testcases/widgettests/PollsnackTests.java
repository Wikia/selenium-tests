package com.wikia.webdriver.testcases.widgettests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PollsnackWidgetPageObject;

/**
 * @ownership: Content X-Wing
 */
@Test(groups = {"PollsnackWidgetTests", "WidgetTests"})
public class PollsnackTests extends NewTestTemplate {

  @Test(groups = "PollsnackWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PollsnackWidgetTest_001_isLoaded() {
    PollsnackWidgetPageObject widget = new PollsnackWidgetPageObject(driver);

    widget.create().navigate(wikiURL);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "PollsnackWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PollsnackWidgetTest_002_areLoaded() {
    PollsnackWidgetPageObject widget = new PollsnackWidgetPageObject(driver);

    widget.createMultiple().navigate(wikiURL);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "PollsnackWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PollsnackWidgetTest_003_isErrorPresent() {
    PollsnackWidgetPageObject widget = new PollsnackWidgetPageObject(driver);

    widget.createIncorrect().navigate(wikiURL);
    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
