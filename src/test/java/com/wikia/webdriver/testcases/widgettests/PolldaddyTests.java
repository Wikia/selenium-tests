package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PolldaddyWidgetPageObject;

import org.testng.annotations.Test;

/**
 * @ownership: Content X-Wing
 */
@Test(groups = {"PolldaddyWidgetTests", "WidgetTests"})
public class PolldaddyTests extends NewTestTemplate {

  @Test(groups = "PolldaddyWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PolldaddyWidgetTest_001_isLoaded() {
    PolldaddyWidgetPageObject widget = new PolldaddyWidgetPageObject(driver);

    widget.createAndNavigate(wikiURL);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = {"PolldaddyWidgetTest_002"})
  @Execute(onWikia = "mercuryautomationtesting")
  public void PollsnackWidgetTest_002_areLoaded() {
    PolldaddyWidgetPageObject widget = new PolldaddyWidgetPageObject(driver);

    widget.createMultiple().navigate(wikiURL);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "PolldaddyWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PolldaddyKWidgetTest_002_isErrorPresent() {
    PolldaddyWidgetPageObject widget = new PolldaddyWidgetPageObject(driver);

    widget.createIncorrect().navigate(wikiURL);
    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
