package com.wikia.webdriver.testcases.social;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.testng.annotations.Test;

public class MercuryAssetCheck extends NewTestTemplate {

  @Test(groups = "CheckAssetsOnJoin")
  @NetworkTrafficDump
  @Execute(onWikia = "glee")
  public void checkAssetsOnJoin() {
    WikiBasePageObject base = new WikiBasePageObject();
    networkTrafficInterceptor.startIntercepting("MercurryAssets");
    base.getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + "join");
    networkTrafficInterceptor.checkAssetsStatuses(".wikia.com");
  }
}
