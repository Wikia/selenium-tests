package com.wikia.webdriver.testcases.social;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

/**
 * Created by Ludwik on 2015-08-14.
 */
public class MercuryAssetCheck extends NewTestTemplate {

  @Test(groups = "CheckAssetsOnJoin")
  @NetworkTrafficDump
  @Execute(onWikia = "glee")
  public void CheckAssetsOnJoin() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    networkTrafficInterceptor.startIntercepting("MercurryAssets");
    base.getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + "/join");
    networkTrafficInterceptor.checkAssetsStatuses(".wikia.com");
  }
}
