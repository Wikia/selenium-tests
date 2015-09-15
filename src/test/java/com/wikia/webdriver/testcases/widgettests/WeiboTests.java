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
@Test(groups = {"WeiboWidgetTests", "WidgetTests"})
public class WeiboTests extends NewTestTemplate {

  @Test(groups = "WeiboWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void WeiboWidgetTest_001_isLoaded() {
    WeiboWidgetPageObject weiboWidget = new WeiboWidgetPageObject(driver);

    weiboWidget.createAndNavigate(wikiURL);
    Assertion.assertTrue(weiboWidget.isLoadedOnOasis(), MercuryMessages.INVISIBLE_MSG);
  }
}
