package com.wikia.webdriver.common.dataprovider.ads;

import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;

import org.testng.annotations.DataProvider;

public class FandomAdsDataProvider {

  private static final String VUAP_PAGE_SLUG = "everything-we-know-about-the-han-solo-movie";
  public static final String AD_REDIRECT_URL = "http://fandom.wikia.com/";

  private FandomAdsDataProvider() { }

  @DataProvider
  public static Object[][] fandomAds() {
    return new Object[][]{
        {
            AdsFandomTestTemplate.PAGE_TYPE_ARTICLE,
            "young-fans-guide-cinema-part-3"
        },
        {
            AdsFandomTestTemplate.PAGE_TYPE_TOPIC,
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
  public static Object[][] fandomTopicPage() {
    return new Object[][]{
        {
            AdsFandomTestTemplate.PAGE_TYPE_TOPIC,
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
            VUAP_PAGE_SLUG,
            AdsFandomContent.TOP_LEADERBOARD,
        },
        {
            AdsFandomTestTemplate.PAGE_TYPE_ARTICLE,
            VUAP_PAGE_SLUG,
            AdsFandomContent.BOTTOM_LEADERBOARD,
        }
    };
  }
}
