package com.wikia.webdriver.testcases.social;

import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.testng.annotations.Test;

public class MercuryAssetCheck extends NewTestTemplate {

  @Test(groups = "CheckAssetsOnJoin")
  @NetworkTrafficDump
  public void checkAssetsOnJoin() {
    WikiBasePageObject base = new WikiBasePageObject();
    networkTrafficInterceptor.startIntercepting("MercurryAssets");
    base.getUrl(urlBuilder.getUrlForPath("join"));
    networkTrafficInterceptor.checkAssetsStatuses(".wikia.com");
  }
}
