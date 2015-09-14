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
  public void WidgetTest_001_Vk_isLoaded() {
    VKWidgetPageObject vkWidget = new VKWidgetPageObject(driver);

    vkWidget.createAndNavigate(wikiURL);
    Assertion.assertTrue(
        vkWidget.isLoadedOnOasis(),
        MercuryMessages.INVISIBLE_MSG
    );
  }
}
