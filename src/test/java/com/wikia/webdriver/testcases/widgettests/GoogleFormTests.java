package com.wikia.webdriver.testcases.widgettests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.GoogleFormWidgetPageObject;

/**
 * @ownership: Content X-Wing
 */
@Test(groups = {"GoogleFormWidgetTests", "WidgetTests"})
public class GoogleFormTests extends NewTestTemplate {

  @Test(groups = "GoogleFormWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void GoogleFormWidgetTest_001_isLoaded() {
    GoogleFormWidgetPageObject widget = new GoogleFormWidgetPageObject(driver);

    widget.create().navigate(wikiURL);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "GoogleFormWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PollsnackWidgetTest_002_areLoaded() {
    GoogleFormWidgetPageObject widget = new GoogleFormWidgetPageObject(driver);

    widget.createMultiple().navigate(wikiURL);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "GoogleFormWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void GoogleFormWidgetTest_003_isErrorPresent() {
    GoogleFormWidgetPageObject widget = new GoogleFormWidgetPageObject(driver);

    widget.createIncorrect().navigate(wikiURL);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
