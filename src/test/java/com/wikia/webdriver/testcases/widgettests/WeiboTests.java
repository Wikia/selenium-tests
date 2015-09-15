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

  @Test
  @Execute(onWikia = "mercuryautomationtesting")
  public void WeiboWidgetTest_001_isLoaded() {
    WeiboWidgetPageObject weiboWidget = new WeiboWidgetPageObject(driver);

    weiboWidget.createAndNavigate(wikiURL);
    Assertion.assertTrue(weiboWidget.isLoadedOnOasis(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test
  @Execute(onWikia = "mercuryautomationtesting")
  public void WeiboWidgetTest_002_areLoaded() {
    WeiboWidgetPageObject weiboWidget = new WeiboWidgetPageObject(driver);

    weiboWidget.create(2).navigate(wikiURL);

    Assertion.assertTrue(
        weiboWidget.areLoadedOnOasis(),
        MercuryMessages.INVISIBLE_MSG
    );
  }

  @Test(groups = "WeiboWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void WeiboKWidgetTest_003_isErrorPresent() {
    WeiboWidgetPageObject weiboWidget = new WeiboWidgetPageObject(driver);

    weiboWidget.createIncorrectAndNavigate(wikiURL);
    Assertion.assertTrue(weiboWidget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
