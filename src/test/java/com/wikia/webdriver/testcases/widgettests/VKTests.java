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

  @Test
  @Execute(onWikia = "mercuryautomationtesting")
  public void VKWidgetTest_001_isLoaded() {
    VKWidgetPageObject vkWidget = new VKWidgetPageObject(driver);

    vkWidget.createAndNavigate(wikiURL);
    Assertion.assertTrue(vkWidget.isLoadedOnOasis(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test
  @Execute(onWikia = "mercuryautomationtesting")
  public void VKWidgetTest_002_areLoaded() {
    VKWidgetPageObject vkWidget = new VKWidgetPageObject(driver);

    vkWidget
        .create(2)
        .navigate(wikiURL);

    Assertion.assertTrue(
        vkWidget.areLoadedOnOasis(),
        MercuryMessages.INVISIBLE_MSG
    );
  }
}
