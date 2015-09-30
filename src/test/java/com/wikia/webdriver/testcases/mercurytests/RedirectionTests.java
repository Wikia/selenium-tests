package com.wikia.webdriver.testcases.mercurytests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.ArticlePageObject;

/**
 * @ownership Content X-Wing
 */
@Test(groups = {"RedirectionTests", "Mercury"})
public class RedirectionTests extends NewTestTemplate {

  private static final String ARTICLE_NAME = MercurySubpages.MAIN_PAGE;
  private static final String QUERY_STRING = "noads=1";
  private String url;
  private String expectedUrl;

  @BeforeMethod(alwaysRun = true)
  public void prepareURL() {
    String articleUrl = urlBuilder.getWebUrlForPath(Configuration.getWikiName(), ARTICLE_NAME);
    url = urlBuilder.appendQueryStringToURL(articleUrl, QUERY_STRING);

    String expectedArticleUrl = urlBuilder.getUrlForPath(Configuration.getWikiName(), ARTICLE_NAME);
    expectedUrl = urlBuilder.appendQueryStringToURL(expectedArticleUrl, QUERY_STRING);
  }

  @Test(groups = "RedirectionTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void RedirectionTest_001_RedirectFromWWW() {
    new ArticlePageObject(driver).openWikiPage(url);
    Assertion.assertUrlEqualToCurrentUrl(driver, expectedUrl);
  }
}
