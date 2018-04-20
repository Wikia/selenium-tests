package com.wikia.webdriver.testcases.specialpagestests;

import com.wikia.webdriver.common.dataprovider.SpecialPagesDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;
import org.testng.annotations.Test;

public class SpecialPagesForAnons extends NewTestTemplate {

  @Test(dataProviderClass = SpecialPagesDataProvider.class,
      dataProvider = "getSpecialPagesForAnons", groups = {"SpecialPagesForAnons_001",
          "SpecialPages"})
  public void SpecialPagesForAnons_001_verifyHeaders(String specialPageName,
      String specialPageHeader) {
    String testedPage = urlBuilder.getUrlForPath(specialPageName);
    SpecialPageObject specialPage = new SpecialPageObject();
    specialPage.getUrl(testedPage);
    specialPage.verifyPageHeader(specialPageHeader);
  }
}
