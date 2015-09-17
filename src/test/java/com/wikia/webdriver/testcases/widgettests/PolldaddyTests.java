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
    PolldaddyWidgetPageObject pollydaddyWidget = new PolldaddyWidgetPageObject(driver);

    pollydaddyWidget.createAndNavigate(wikiURL);
    Assertion.assertTrue(pollydaddyWidget.isLoadedOnOasis(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "PolldaddyWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PolldaddyKWidgetTest_002_isErrorPresent() {
    PolldaddyWidgetPageObject pollydaddyWidget = new PolldaddyWidgetPageObject(driver);

    pollydaddyWidget.createIncorrectAndNavigate(wikiURL);
    Assertion.assertTrue(pollydaddyWidget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
