package com.wikia.webdriver.testcases.specialpagestests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.dataprovider.SpecialPagesDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;

public class SpecialPagesForAnons extends NewTestTemplate {

  @Test(dataProviderClass = SpecialPagesDataProvider.class,
      dataProvider = "getSpecialPagesForAnons", groups = {"SpecialPagesForAnons_001",
          "SpecialPages"})
  public void SpecialPagesForAnons_001_verifyHeaders(String specialPageName,
      String specialPageHeader) {
    String testedPage = urlBuilder.getUrlForPath(Configuration.getWikiName(), specialPageName);
    SpecialPageObject specialPage = new SpecialPageObject(driver);
    specialPage.getUrl(testedPage);
    specialPage.verifyPageHeader(specialPageHeader);
  }
}
