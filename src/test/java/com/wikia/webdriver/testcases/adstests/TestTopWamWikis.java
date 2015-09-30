package com.wikia.webdriver.testcases.adstests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class TestTopWamWikis extends TemplateNoFirstLoad {

  Credentials credentials = Configuration.getCredentials();
  Integer numberOfTop1kWikis = 998;
  String extensionURL = "var=1429&searchType=bool&val=2&likeValue=true";

  public TestTopWamWikis() {
    super();
    urlBuilder = new UrlBuilder(Configuration.getEnv());
  }

  @Test(groups = {"TopWamWikisWhereIsMyExtension"})
  public void TopWamWikisWhereIsMyExtension_GeoEdgeFree() {
    String whereIsExtensionUrl =
        urlBuilder
            .getUrlForPath(URLsContent.COMMUNITY_WIKI, URLsContent.SPECIAL_WHERE_IS_EXTENSION);
    whereIsExtensionUrl = urlBuilder.appendQueryStringToURL(whereIsExtensionUrl, extensionURL);
    WikiBasePageObject wikiPage = new WikiBasePageObject(driver);
    wikiPage.getUrl(whereIsExtensionUrl);
    wikiPage.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    wikiPage.verifyNumberOfTop1kWikis(numberOfTop1kWikis);
  }
}
