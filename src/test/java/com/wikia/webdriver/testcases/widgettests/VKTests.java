package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.VKWidgetPageObject;

import org.testng.annotations.Test;

/**
 * @ownership: Content X-Wing
 */
public class VKTests extends NewTestTemplate {

  @Test(groups = "VKWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void VKWidgetTest_001_isLoaded() {
    VKWidgetPageObject vkWidget = new VKWidgetPageObject(driver);

    vkWidget.create().navigate(wikiURL);
    Assertion.assertTrue(vkWidget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "VKWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void VKWidgetTest_002_areLoaded() {
    VKWidgetPageObject vkWidget = new VKWidgetPageObject(driver);

    vkWidget.createMultiple().navigate(wikiURL);

    Assertion.assertTrue(
        vkWidget.areLoaded(),
        MercuryMessages.INVISIBLE_MSG
    );
  }

  @Test(groups = "VKWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void VKWidgetTest_003_isErrorPresent() {
    VKWidgetPageObject vkWidget = new VKWidgetPageObject(driver);

    vkWidget.createIncorrect().navigate(wikiURL);
    Assertion.assertTrue(vkWidget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
