package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PortableInfoboxObject;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

/**
 * @ownshership: Content West-Wing
 */

@Test(groups = {"MercuryPortableInfoboxTests"})
public class PortableInfoboxTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_AUTOMATION_TESTING);
  }

  //TC01
  @Test(groups = {"MercuryPortableInfoboxTest_001", "MercuryPortableInfoboxTests"})
  public void MercuryPortableInfoboxTest_001_VerifyElementsVisible() {
    new ArticlePageObject(driver).openMercuryArticleByName(wikiURL, MercurySubpages.INFOBOX_1);

    new PortableInfoboxObject(driver)
        .isMainImageVisible()
        .verifyDataItemsVisibility()
        .isTitleVisible()
        .areLinksVisible();
  }


}