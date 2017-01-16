package com.wikia.webdriver.common.dataprovider.ads;

import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;

import org.testng.annotations.DataProvider;

public class FandomAdsDataProvider {

  private static final String VUAP_PAGE_SLUG = "silicon-valley-perfect-mike-judge";
  private static final String AD_UNIT_TEMPLATE = "google_ads_iframe_/5441/wka.fandom/_article/ARTICLE_%s_0";

  private FandomAdsDataProvider() { }

  @DataProvider
  public static Object[][] fandomAds() {
    return new Object[][]{
        {
            AdsFandomTestTemplate.PAGE_TYPE_ARTICLE,
            "young-fans-guide-cinema-part-3"
        },
        {
            AdsFandomTestTemplate.PAGE_TYPE_HUB,
            "games"
        }
    };
  }

  @DataProvider
  public static Object[][] fandomBtfBlockPage() {
    return new Object[][]{
        {
            "game-of-thrones-the-ghost-of-high-heart"
        }
    };
  }

  @DataProvider
  public static Object[][] fandomArticleUapPage() {
    return new Object[][]{
        {
            AdsFandomTestTemplate.PAGE_TYPE_ARTICLE,
            "box-office-audiences-download-angry-birds",
            291751932,
            291759372
        }
    };
  }


  @DataProvider
  public static Object[][] fandomHubUapPage() {
    return new Object[][]{
        {
            AdsFandomTestTemplate.PAGE_TYPE_HUB,
            "tv",
            291751932,
            291759372
        }
    };
  }

  @DataProvider
  public static Object[][] vuapPage() {
    return new Object[][]{
        {
            AdsFandomTestTemplate.PAGE_TYPE_ARTICLE,
            FandomAdsDataProvider.VUAP_PAGE_SLUG,
            AdsFandomContent.TOP_LEADERBOARD,
            String.format(AD_UNIT_TEMPLATE, AdsFandomContent.TOP_LEADERBOARD)
        },
        {
            AdsFandomTestTemplate.PAGE_TYPE_ARTICLE,
            VUAP_PAGE_SLUG,
            AdsFandomContent.BOTTOM_LEADERBOARD_DESKTOP,
            String.format(AD_UNIT_TEMPLATE, AdsFandomContent.BOTTOM_LEADERBOARD)
        }
    };
  }

  @DataProvider
  public static Object[][] vuapPageMobile() {
    return new Object[][]{
        {
            AdsFandomTestTemplate.PAGE_TYPE_ARTICLE,
            FandomAdsDataProvider.VUAP_PAGE_SLUG,
            AdsFandomContent.TOP_LEADERBOARD,
            String.format(AD_UNIT_TEMPLATE, AdsFandomContent.TOP_LEADERBOARD),
            "https://pubads.g.doubleclick.net/gampad/ads?output=vast&env=vp&gdfp_req=1&unviewed_position_start=1&iu=%2F5441%2Fwka.fandom%2F_fandom%2F%2Farticle%2Fgpt%2FTOP_LEADERBOARD"
        },
        {
            AdsFandomTestTemplate.PAGE_TYPE_ARTICLE,
            FandomAdsDataProvider.VUAP_PAGE_SLUG,
            AdsFandomContent.BOTTOM_LEADERBOARD_MOBILE,
            String.format(AD_UNIT_TEMPLATE, AdsFandomContent.BOTTOM_LEADERBOARD),
            "https://pubads.g.doubleclick.net/gampad/ads?output=vast&env=vp&gdfp_req=1&unviewed_position_start=1&iu=%2F5441%2Fwka.fandom%2F_fandom%2F%2Farticle%2Fgpt%2FBOTTOM_LEADERBOARD"
        }
    };
  }
}
