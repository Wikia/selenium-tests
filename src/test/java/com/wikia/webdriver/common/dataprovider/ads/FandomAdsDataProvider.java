package com.wikia.webdriver.common.dataprovider.ads;

import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;

import org.testng.annotations.DataProvider;

public class FandomAdsDataProvider {

  private FandomAdsDataProvider() {

  }

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
  public static Object[][] fandomUapPage() {
    return new Object[][]{
        {
            AdsFandomTestTemplate.PAGE_TYPE_ARTICLE,
            "box-office-audiences-download-angry-birds",
            291751932,
            291759372
        },
        {
            AdsFandomTestTemplate.PAGE_TYPE_HUB,
            "tv",
            291751932,
            291759372
        }
    };
  }

    @DataProvider
    public static Object[][] fandomVuapPage() {
        return new Object[][]{
                {
                        AdsFandomTestTemplate.PAGE_TYPE_ARTICLE,
                        "silicon-valley-perfect-mike-judge",
                        AdsFandomContent.GPT_TOP_LEADERBOARD,
                        String.format(
                                "google_ads_iframe_/5441/wka.fandom/_article/ARTICLE_%s_0",
                                AdsFandomContent.TOP_LEADERBOARD
                        )
                },
                {
                        AdsFandomTestTemplate.PAGE_TYPE_ARTICLE,
                        "silicon-valley-perfect-mike-judge",
                        AdsFandomContent.GPT_BOTTOM_LEADERBOARD,
                        String.format(
                                "google_ads_iframe_/5441/wka.fandom/_article/ARTICLE_%s_0",
                                AdsFandomContent.BOTTOM_LEADERBOARD
                        )
                }
        };
    }

    @DataProvider
    public static Object[][] fandomVuapPageMobile() {
        return new Object[][]{
                {
                        AdsFandomTestTemplate.PAGE_TYPE_ARTICLE,
                        "silicon-valley-perfect-mike-judge",
                        AdsFandomContent.GPT_TOP_LEADERBOARD,
                        String.format(
                                "google_ads_iframe_/5441/wka.fandom/_article/ARTICLE_%s_0",
                                AdsFandomContent.TOP_LEADERBOARD
                        )
                },
                {
                        AdsFandomTestTemplate.PAGE_TYPE_ARTICLE,
                        "silicon-valley-perfect-mike-judge",
                        AdsFandomContent.GPT_BOTTOM_LEADERBOARD_MOBILE,
                        String.format(
                                "google_ads_iframe_/5441/wka.fandom/_article/ARTICLE_%s_0",
                                AdsFandomContent.BOTTOM_LEADERBOARD
                        )
                }
        };
    }
}
