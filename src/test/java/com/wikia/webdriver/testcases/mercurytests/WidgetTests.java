package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.LoginPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.TwitterWidgetPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ownership: Content X-Wing
 */
// Uncoment after finish all widget tags
//@Test(groups = {"MercuryWidgetTests", "Mercury"})
public class WidgetTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);

    // This login is temporary solution, use @Execute after QAART-669 is done
    new LoginPageObject(driver).get().logUserIn(Configuration.getCredentials().userNameStaff2,
                                                Configuration.getCredentials().passwordStaff2);
  }

  @Test(groups = "MercuryWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryWidgetTest_001_Twitter() {
    TwitterWidgetPageObject twitterWidgetPageObject = new TwitterWidgetPageObject(driver);

    twitterWidgetPageObject.createAndNavigate(wikiURL);
    Assertion.assertTrue(
        twitterWidgetPageObject.isLoadedOnMercury(),
        MercuryMessages.INVISIBLE_MSG
    );
  }
}
