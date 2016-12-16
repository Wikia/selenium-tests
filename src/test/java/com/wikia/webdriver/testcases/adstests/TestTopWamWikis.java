package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.apache.commons.lang3.Range;
import org.testng.annotations.Test;

public class TestTopWamWikis extends TemplateNoFirstLoad {

  Credentials credentials = Configuration.getCredentials();
  Range<Integer> expectedTopWamWikiRange = Range.between(900, 1100);
  String extensionURL = "var=1429&searchType=bool&val=2&likeValue=true";

  @Test(groups = "TopWamWikisWhereIsMyExtension")
  public void TestTopWamWikisCountInRange() {
    String whereIsExtensionUrl = urlBuilder.getUrlForPath(URLsContent.COMMUNITY_WIKI,
                                                          URLsContent.SPECIAL_WHERE_IS_EXTENSION);
    whereIsExtensionUrl = urlBuilder.appendQueryStringToURL(whereIsExtensionUrl, extensionURL);
    WikiBasePageObject wikiPage = new WikiBasePageObject();
    wikiPage.getUrl(whereIsExtensionUrl);
    wikiPage.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    wikiPage.verifyNumberOfTop1kWikisInRange(expectedTopWamWikiRange);
  }
}
