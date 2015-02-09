package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.urlbuilder.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

/**
 * @author Piotr PMG Gackowski
 * @ownership AdEngineering Below test case is prepared to check status of parameters allowing
 * targeting ads to Top 1000 WAM Wikis. TestTopWamWikisWikifactory_GeoEdgeFree -
 * go to Special:WhereIsMyExtension and check how many
 * wikis have set wgAdDriverWikiIsTop1000 variable. Need in settings: wikiName=community
 */

public class TestTopWamWikis extends NewTestTemplate {

  Credentials credentials = config.getCredentials();
  Integer numberOfTop1kWikis = 1000;
  String extensionURL = "var=1429&searchType=bool&val=2&likeValue=true";

  public TestTopWamWikis() {
    super();
    urlBuilder = new UrlBuilder(config.getEnv());
  }

  @Test(groups = {"TopWamWikisWhereIsMyExtension"})
  public void TopWamWikisWhereIsMyExtension_GeoEdgeFree() {
    String whereIsExtensionUrl = urlBuilder.getUrlForPath(URLsContent.COMMUNITY_WIKI,
                                                          URLsContent.SPECIAL_WHERE_IS_EXTENSION);
    whereIsExtensionUrl = urlBuilder.appendQueryStringToURL(whereIsExtensionUrl, extensionURL);
    WikiBasePageObject wikiPage = new WikiBasePageObject(driver);
    wikiPage.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    wikiPage.getUrl(whereIsExtensionUrl);
    wikiPage.verifyNumberOfTop1kWikis(numberOfTop1kWikis);
  }
}
