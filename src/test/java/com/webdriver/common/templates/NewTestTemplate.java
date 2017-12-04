package com.webdriver.common.templates;

import com.webdriver.common.contentpatterns.URLsContent;
import com.webdriver.common.core.url.UrlBuilder;
import com.webdriver.common.templates.core.CoreTestTemplate;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class NewTestTemplate extends CoreTestTemplate {

  protected UrlBuilder urlBuilder;
  protected String wikiURL;
  protected String wikiCorporateURL;
  protected String wikiCorpSetupURL;

  @BeforeMethod(alwaysRun = true)
  public void start(Method method, Object[] data) {
  }

  protected void loadFirstPage() {
    driver.get(wikiURL + URLsContent.SPECIAL_VERSION);
  }

  protected void prepareURLs() {
    urlBuilder = new UrlBuilder();
    wikiURL = urlBuilder.getUrlForWiki();
    wikiCorporateURL = urlBuilder.getWikiGlobalURL();
    wikiCorpSetupURL = urlBuilder.getUrlForWiki("corp");
  }
}
