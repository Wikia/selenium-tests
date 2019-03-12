package com.wikia.webdriver.testcases.desktop.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

public class TestAdsOnSpecialOasis extends TemplateNoFirstLoad {

  private static final String TEST_LINE_ITEM_ID = "271491732";
  private static final String FILE_PAGE_TLB_MEGA_AD_UNIT = "wka1b.LB/top_leaderboard/desktop"
                                                           + "/oasis-file/_top1k_wiki-life";
  private static final String SPECIAL_PAGE_AD_UNIT = "wka1b.LB/top_leaderboard/unknown"
                                                     + "-specialpage/oasis-special/_top1k_wiki-life";
  private static final String
      FILE_PAGE_MR_MEGA_AD_UNIT
      = "wka1b.MR/top_boxad/desktop/oasis-file/_top1k_wiki-life";
  private static final String
      FILE_PAGE_BLB_MEGA_AD_UNIT
      = "wka1b.PF/bottom_leaderboard/desktop/oasis-file/_top1k_wiki-life";
  private static final Dimension RESOLUTION = new Dimension(1292, 1000);

  private void testSpecialPage(AdsBaseObject ads) {
    ads.setPageType(AdsBaseObject.PAGE_TYPE_SPECIAL);

    ads.verifyGptAdInSlot(AdsContent.TOP_LB, TEST_LINE_ITEM_ID);
    ads.verifyGptMEGAIframe(SPECIAL_PAGE_AD_UNIT, AdsContent.TOP_LB);
  }

  @Test(groups = "TestAdsOnSpecialPagesOasis")
  public void testAdsOnSpecialVideoPageOasis() {
    AdsBaseObject ads = new AdsBaseObject(driver,
                                          AdsDataProvider.PAGE_SPECIAL_VIDEOS.getUrl(),
                                          RESOLUTION
    );
    testSpecialPage(ads);
  }

  @Test(groups = "TestAdsOnSpecialPagesOasis")
  public void testAdsOnSpecialImagesPageOasis() {
    AdsBaseObject ads = new AdsBaseObject(driver,
                                          AdsDataProvider.PAGE_SPECIAL_IMAGES.getUrl(),
                                          RESOLUTION
    );
    testSpecialPage(ads);
  }

  @Test(groups = "TestAdsOnFilePagesOasis")
  public void testAdsOnFilePageOasis() {
    AdsBaseObject ads = new AdsBaseObject(driver,
                                          AdsDataProvider.PAGE_SPECIAL_FILE.getUrl(),
                                          RESOLUTION
    );
    ads.setPageType(AdsBaseObject.PAGE_TYPE_FILE);
    ads.verifyGptAdInSlot(AdsContent.TOP_LB, TEST_LINE_ITEM_ID);
    ads.verifyMEGAAdUnit(AdsContent.TOP_LB, FILE_PAGE_TLB_MEGA_AD_UNIT);

    ads.verifyGptAdInSlot(AdsContent.TOP_BOXAD, TEST_LINE_ITEM_ID);
    ads.verifyMEGAAdUnit(AdsContent.TOP_BOXAD, FILE_PAGE_MR_MEGA_AD_UNIT);

    ads.triggerAdSlot(AdsContent.BOTTOM_LB);
    ads.verifyGptAdInSlot(AdsContent.BOTTOM_LB, TEST_LINE_ITEM_ID);
    ads.verifyMEGAAdUnit(AdsContent.BOTTOM_LB, FILE_PAGE_BLB_MEGA_AD_UNIT);
  }
}
