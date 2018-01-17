package com.wikia.webdriver.common.dataprovider.ads;

import com.wikia.webdriver.common.contentpatterns.AdsFandomContent;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;

import org.testng.annotations.DataProvider;

public class FandomAdsDataProvider {

  public static final String ARTICLE_VIDEO_PAGE_SLUG = "the-best-movies-of-2017-so-far";
  public static final String FEATURED_VIDEO_PAGE_SLUG = "orphan-black-clones-names";
  public static final String VUAP_PAGE_SLUG = "everything-we-know-about-the-han-solo-movie";

  public static final String AD_REDIRECT_URL = "http://fandom.wikia.com/";
  public static final String MOAT_VIDEO_TRACKING_URL = "https://z.moatads.com/wikiajwint101173217941/moatvideo.js";

  public static final String IGNORE_SAMPLING = "ignored_samplers=moat_video_tracking";
  public static final String INSTANT_GLOBAL_MIDROLL = "wgAdDriverVideoMidrollCountries";
  public static final String INSTANT_GLOBAL_MOAT_TRACKING = "wgAdDriverVideoMoatTrackingCountries";
  public static final String INSTANT_GLOBAL_POSTROLL = "wgAdDriverVideoPostrollCountries";

  private FandomAdsDataProvider() { }

  public static final String PAGE_NON_UAP_TOPIC = "movies";
  public static final String PAGE_NON_UAP_ARTICLE = "young-fans-guide-cinema-part-3";
  public static final String PAGE_HIVI_UAP_ARTICLE = "walking-dead-major-death-differs-from-comics";

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
            365404452,
            365416332
        }
    };
  }


  @DataProvider
  public static Object[][] fandomTopicPage() {
    return new Object[][]{
        {
            AdsFandomTestTemplate.PAGE_TYPE_TOPIC,
            "tv",
            365404452,
            365416332
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
