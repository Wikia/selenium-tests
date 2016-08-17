package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsTaboolaObject;

import org.testng.annotations.Test;

public class TestAdsTaboolaOasis extends TemplateNoFirstLoad {

  @RelatedIssue(issueID = "ADEN-3590")
  @Test(groups = "AdsTaboolaOasis")
  public void adsTaboolaOasis() {

    String testedPage = urlBuilder.getUrlForPath("adtest", "SyntheticTests/Taboola");
    testedPage = urlBuilder.appendQueryStringToURL(testedPage, AdsTaboolaObject.URL_PARAM_TRIGGER);

    AdsTaboolaObject adsTaboolaObject = new AdsTaboolaObject(driver);
    adsTaboolaObject.getUrl(testedPage);

    adsTaboolaObject.verifyTaboolaContainer(AdsTaboolaObject.BELOW_ARTICLE_CSS_SELECTOR);
    adsTaboolaObject.verifyTaboolaAdsPresent(AdsTaboolaObject.BELOW_ARTICLE_CSS_SELECTOR);

//    @TODO Assertions based on configuration: ADEN-2729
//    adsTaboolaObject.verifyTaboolaContainer(AdsTaboolaObject.RIGHT_RAIL_CSS_SELECTOR);
//    adsTaboolaObject.verifyTaboolaAdsPresent(AdsTaboolaObject.RIGHT_RAIL_CSS_SELECTOR);
  }

}
