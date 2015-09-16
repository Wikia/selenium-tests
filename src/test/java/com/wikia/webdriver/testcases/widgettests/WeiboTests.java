package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WeiboWidgetPageObject;

import org.testng.annotations.Test;

/**
 * @ownership: Content X-Wing
 */
public class WeiboTests extends NewTestTemplate {

  @Test(groups = "WeiboWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void WeiboWidgetTest_001_isLoaded() {
    WeiboWidgetPageObject widget = new WeiboWidgetPageObject(driver);

    widget.create().navigate(wikiURL);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "WeiboWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void WeiboWidgetTest_002_areLoaded() {
    WeiboWidgetPageObject widget = new WeiboWidgetPageObject(driver);

    widget.createMultiple().navigate(wikiURL);

    Assertion.assertTrue(
        widget.areLoaded(),
        MercuryMessages.INVISIBLE_MSG
    );
  }

  @Test(groups = "WeiboWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void WeiboKWidgetTest_003_isErrorPresent() {
    WeiboWidgetPageObject widget = new WeiboWidgetPageObject(driver);

    widget.createIncorrect().navigate(wikiURL);
    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
