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
    Assertion.assertTrue(
        weiboWidget.isLoadedOnOasis(),
        MercuryMessages.INVISIBLE_MSG
    );
  }
}
