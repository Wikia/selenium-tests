package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlChecker;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.ArticlePageObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RedirectsTests extends NewTestTemplate {

  private final static String articleName = MercurySubpages.MAIN_PAGE;
  private final static String queryString = "noads=1";
  private String url;
  private String expectedUrl;

  @BeforeMethod(alwaysRun = true)
  public void prepareURL() {
    String articleUrl = urlBuilder.getUrlForPathWithWWW(Configuration.getWikiName(), articleName);
    url = urlBuilder.appendQueryStringToURL(articleUrl, queryString);

    String expectedArticleUrl = urlBuilder.getUrlForPath(Configuration.getWikiName(), articleName);
    expectedUrl = urlBuilder.appendQueryStringToURL(expectedArticleUrl, queryString);
  }

  @Test(groups = {"RedirectsTest_001", "Mercury"})
  @Execute(onWikia = "mercuryautomationtesting")
  public void RedirectsTest_001_RedirectFromWWW() {
    ArticlePageObject article = new ArticlePageObject(driver);
    article.openWikiPage(url);
    Assertion.assertTrue(
      UrlChecker.isUrlEqualToCurrentUrl(driver, expectedUrl),
      String.format("URL is different than expected. Expected: %s \n", expectedUrl)
    );
  }
}
