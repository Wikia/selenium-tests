package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.GoogleFormWidgetPageObject;

import org.testng.annotations.Test;

/**
 * @ownership: Content X-Wing
 */
// Uncomment after 22-sep
//@Test(groups = {"GoogleFormWidgetTests", "WidgetTests"})
public class GoogleFormTests extends NewTestTemplate {

  @Test(groups = "GoogleFormWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void GoogleFormWidgetTest_001_isLoaded() {
    GoogleFormWidgetPageObject googleFormWidget = new GoogleFormWidgetPageObject(driver);

    googleFormWidget.createAndNavigate(wikiURL);
    Assertion.assertTrue(googleFormWidget.isLoadedOnOasis(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "GoogleFormWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void GoogleFormWidgetTest_003_isErrorPresent() {
    GoogleFormWidgetPageObject googleFormWidget = new GoogleFormWidgetPageObject(driver);

    googleFormWidget.createIncorrectAndNavigate(wikiURL);
    Assertion.assertTrue(googleFormWidget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
