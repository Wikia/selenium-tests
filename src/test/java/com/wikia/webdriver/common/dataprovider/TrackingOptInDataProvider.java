package com.wikia.webdriver.common.dataprovider;

import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class TrackingOptInDataProvider {

  public static final String
      ADS_GOOGLE_ANALYTICS_REJECTED_PATTERN
      = "https?://.*google-analytics\\.com(/r)?/collect.*aip=1.*";
  private static final String
      ADS_KIKIMORA_OPT_IN_ACCEPTED_PATTERN
      = "https?://.*beacon\\.wikia-services\\.com/__track/special/adeng.*u=0.*";
  private static final String
      ADS_KIKIMORA_OPT_IN_REJECTED_PATTERN
      = "https?://.*beacon\\.wikia-services\\.com/__track/special/adeng.*u=-1.*";
  private static final String ADS_KRUX_PATTERN = "https?://.*cdn\\.krxd\\.net.*";
  private static final String
      ADS_NETZ_ATHLETEN_PATTERN
      = "http?://.*adadapter\\.netzathleten-media\\.de/.*/naMediaAd\\.js.*";
  private static final String ADS_MOAT_PATTERN = "https?://.*moatads\\.com.*";
  private static final String ADS_PUBMATIC_PATTERN = "http?://.*pubmatic\\.com/AdServer.*";
  private static final String ADS_A9_PATTERN = "http?://.*amazon-adsystem\\.com/.*/apstag\\.js.*";
  private static final String ADS_COMSCORE_PATTERN = "http?://.*scorecardresearch\\.com.*";
  private static final String ADS_QUANTCAST_PATTERN = "http?://.*quantserve\\.com/.*";
  private static final String ADS_QUANTCAST_SECURE_PATTERN = "https?://.*quantserve\\.com/.*";
  private static final String ADS_APP_NEXUS_PATTERN = "http?://.*ib\\.adnxs\\.com/.*";
  private static final String
      ADS_NORDICS_PATTERN
      = "https?://.*doubleclick\\.net/gampad/ads.*Nordics_RoN.*";
  private static final String ADS_OPOENX_PATTERN = "http?://.*wikia-d\\.openx\\.net/.*/arj.*";
  private static final String ADS_INDEX_EXCHANGE_PATTERN = "http?://.*casalemedia\\.com/cygnus.*";
  private static final String
      ADS_RUBBICON_FASTLANE_PATTERN
      = "http?://.*fastlane\\.rubiconproject\\.com/.*/fastlane\\.json.*";
  private static final String
      ADS_GOOGLE_ANALYTICS_PATTERN
      = "https?://.*google-analytics\\.com/collect.*";
  private static final String
      ADS_TLB_NPA_FIRST_PARAMETER_PATTERN
      = "https?://.*\\.doubleclick\\.net.*ads.*npa=1.*TOP_LEADERBOARD.*";
  private static final String
      ADS_TLB_NPA_SECOND_PARAMETER_PATTERN
      = "https?://.*\\.doubleclick\\.net.*ads.*TOP_LEADERBOARD.*cust_params=.*npa%3D1.*cookie.*";
  private static final String
      ADS_BLB_NPA_FIRST_PARAMETER_PATTERN
      = "https?://.*\\.doubleclick\\.net.*ads.*npa=1.*BOTTOM_LEADERBOARD.*";
  private static final String
      ADS_BLB_NPA_SECOND_PARAMETER_PATTERN
      = "https?://.*\\.doubleclick\\.net.*ads.*BOTTOM_LEADERBOARD.*cust_params=.*npa%3D1.*cookie.*";
  private static final String
      ADS_MOBILE_IN_CONTENT_NPA_FIRST_PARAMETER_PATTERN
      = "https?://.*\\.doubleclick\\.net.*ads.*npa=1.*MOBILE_IN_CONTENT.*";
  private static final String
      ADS_MOBILE_IN_CONTENT_NPA_SECOND_PARAMETER_PATTERN
      = "https?://.*\\.doubleclick\\.net.*ads.*MOBILE_IN_CONTENT.*cust_params=.*npa%3D1.*cookie.*";
  private static final String
      ADS_VAST_NPA_FIRST_PARAMETER_PATTERN
      = "https?://.*pubads\\.g\\.doubleclick\\.net/gampad/ads.*output=xml_vast.*cust_params=.*npa%3D1.*";
  private static final String
      ADS_VAST_NPA_SECOND_PARAMETER_PATTERN
      = "https?://.*pubads\\.g\\.doubleclick\\.net/gampad/ads.*output=xml_vast.*npa=1.*";
  private static final String PETAMETRICS_PATTERN = "http?://.*petametrics\\.com.*";

  private static final String[] ADS_KIKIMORA_INSTANT_GLOBALS = {
      "wgAdDriverKikimoraTrackingCountries", "wgAdDriverKikimoraViewabilityTrackingCountries",
      "wgAdDriverKikimoraPlayerTrackingCountries"};

  private static final String[] ADS_KRUX_INSTANT_GLOBALS = {"wgAdDriverKruxCountries"};

  private static final String[] ADS_NETZ_ATHLETEN_INSTANT_GLOBALS = {
      "wgAdDriverNetzAthletenCountries"};

  private static final String[] ADS_MOAT_INSTANT_GLOBALS = {"wgAdDriverVideoMoatTrackingCountries",
                                                            "wgAdDriverPorvataMoatTrackingCountries",
                                                            "wgAdDriverVideoMoatTrackingCountries"};

  private static final String[] ADS_PREBID_INSTANT_GLOBALS = {
      "wgAdDriverBottomLeaderBoardLazyPrebidCountries", "wgAdDriverOpenXPrebidBidderCountries",
      "wgAdDriverPrebidBidderCountries", "wgAdDriverRubiconDisplayPrebidCountries",
      "wgAdDriverRubiconPrebidCountries", "wgAdDriverAppNexusBidderCountries"};

  private static final String[] ADS_A9_INSTANT_GLOBALS = {"wgAdDriverA9BidderCountries",
                                                          "wgAdDriverA9VideoBidderCountries"};

  private static final String[] ADS_ARTICLE_PAGES = {"TrackingPixels/Article2",
                                                     "TrackingPixels/Article3",
                                                     "TrackingPixels/Article2",
                                                     "TrackingPixels/Article1", "Project43 Wikia"};

  private static final String
      GOOGLE_ANALYTICS_ANONYMIZED_USER
      = "https?://.*google-analytics\\.com/collect\\?.*aip=1.*";

  @DataProvider
  public static Object[][] GDPRcountries() {
    return new Object[][]{{"NA", "ai", true}, // Anguilla
                          {"NA", "aw", true}, // Aruba
                          {"EU", "ax", true}, // Åland Islands
                          {"EU", "at", true}, // Austria
                          {"EU", "be", true}, // Belgium
                          {"EU", "bg", true}, // Bulgaria
                          {"NA", "bm", true}, // Bermuda
                          {"NA", "vg", true}, // British Virgin Islands
                          {"NA", "bq", true}, // Bonaire
                          {"NA", "ky", true}, // Cayman Islands
                          {"EU", "hr", true}, // Croatia
                          {"NA", "cw", true}, // Curaçao
                          {"AS", "cy", true}, // Cyprus
                          {"EU", "cz", true}, // Czech Republic
                          {"EU", "dk", true}, // Denmark
                          {"EU", "ee", true}, // Estonia
                          {"SA", "fk", true}, // Falkland Islands
                          {"EU", "fo", true}, // Faroe Islands
                          {"EU", "fi", true}, // Finland
                          {"EU", "fr", true}, // France
                          {"SA", "gf", true}, // French Guiana
                          {"OC", "pf", true}, // French Polynesia
                          {"AN", "tf", true}, // French Southern and Antarctic Lands
                          {"EU", "de", true}, // Germany
                          {"EU", "gi", true}, // Gibraltar
                          {"EU", "gr", true}, // Greece
                          {"NA", "gl", true}, // Greenland
                          {"NA", "gp", true}, // Guadeloupe
                          {"EU", "hu", true}, // Hungary
                          {"EU", "is", true}, // Iceland
                          {"EU", "ie", true}, // Ireland
                          {"EU", "im", true}, // Isle of Man
                          {"EU", "it", true}, // Italy
                          {"AF", "re", true}, // La Réunion
                          {"EU", "lv", true}, // Latvia
                          {"EU", "li", true}, // Liechtenstein
                          {"EU", "lt", true}, // Lithuania
                          {"EU", "lu", true}, // Luxembourg
                          {"NA", "mq", true}, // Martinique
                          {"EU", "mt", true}, // Malta
                          {"AF", "yt", true}, // Mayotte
                          {"NA", "ms", true}, // Montserrat
                          {"EU", "nl", true}, // Netherlands
                          {"OC", "nc", true}, // New Caledonia
                          {"EU", "no", true}, // Norway
                          {"OC", "pn", true}, // Pitcairn Islands
                          {"EU", "pl", true}, // Poland
                          {"EU", "pt", true}, // Portugal
                          {"EU", "ro", true}, // Romania
                          {"NA", "bl", true}, // Saint Barthélemy
                          {"AF", "sh", true}, // Saint Helena
                          {"NA", "mf", true}, // Saint Martin
                          {"NA", "pm", true}, // Saint-Pierre-et-Miquelon
                          {"NA", "bq", true}, // Sint Eustatius
                          {"NA", "sx", true}, // Sint Maarten
                          {"EU", "sk", true}, // Slovakia
                          {"EU", "si", true}, // Slovenia
                          {"EU", "es", true}, // Spain
                          {"EU", "se", true}, // Sweden
                          {"EU", "ch", true}, // Switzerland
                          {"NA", "tc", true}, // Turks and Caicos Islands
                          {"EU", "gb", true},// United Kingdom of Great Britain and Northern Ireland
                          {"EU", "uk", true},// United Kingdom of Great Britain and Northern Ireland
                          {"OC", "wf", true}, // Wallis-et-Futuna

                          {"NA", "US", false}, // USA
                          {"AS", "JP", false}, // Japan
                          {"OC", "AU", false}, // Australia
                          {"AS", "CN", false}, // China
    };
  }

  @DataProvider
  public static Object[][] GDPRCountriesForTest() {
    return new Object[][]{{"EU", "de", true}, // Germany

                          {"NA", "US", false}, // USA
    };
  }

  @DataProvider
  public static Object[][] adsKikimoraAcceptedDataProvider() {
    return new Object[][]{
        {ADS_KIKIMORA_INSTANT_GLOBALS, Arrays.asList(ADS_KIKIMORA_OPT_IN_ACCEPTED_PATTERN)}};
  }

  @DataProvider
  public static Object[][] adsKikimoraRejectedDataProvider() {
    return new Object[][]{
        {ADS_KIKIMORA_INSTANT_GLOBALS, Arrays.asList(ADS_KIKIMORA_OPT_IN_REJECTED_PATTERN)}};
  }

  @DataProvider
  public static Object[][] adsKruxDataProvider() {
    return new Object[][]{{ADS_KRUX_INSTANT_GLOBALS, Arrays.asList(ADS_KRUX_PATTERN)}};
  }

  @DataProvider
  public static Object[][] adsNetzAthletenDataProvider() {
    return new Object[][]{
        {ADS_NETZ_ATHLETEN_INSTANT_GLOBALS, Arrays.asList(ADS_NETZ_ATHLETEN_PATTERN)}};
  }

  @DataProvider
  public static Object[][] adsMoatDataProvider() {
    return new Object[][]{{ADS_MOAT_INSTANT_GLOBALS, Arrays.asList(ADS_MOAT_PATTERN)}};
  }

  @DataProvider
  public static Object[][] adsPrebidDataProvider() {
    return new Object[][]{{ADS_PREBID_INSTANT_GLOBALS, Arrays.asList(
        ADS_PUBMATIC_PATTERN,
        ADS_APP_NEXUS_PATTERN,
        ADS_OPOENX_PATTERN,
        ADS_INDEX_EXCHANGE_PATTERN,
        ADS_RUBBICON_FASTLANE_PATTERN
    )}};
  }

  @DataProvider
  public static Object[][] adsNpaHiViDataProviderOasis() {
    return new Object[][]{{Arrays.asList(
        ADS_TLB_NPA_FIRST_PARAMETER_PATTERN,
        ADS_TLB_NPA_SECOND_PARAMETER_PATTERN,
        ADS_BLB_NPA_FIRST_PARAMETER_PATTERN,
        ADS_BLB_NPA_SECOND_PARAMETER_PATTERN,
        ADS_VAST_NPA_FIRST_PARAMETER_PATTERN,
        ADS_VAST_NPA_SECOND_PARAMETER_PATTERN
    )}};
  }

  @DataProvider
  public static Object[][] adsNpaSlotsDataProviderMobile() {
    return new Object[][]{{Arrays.asList(
        ADS_TLB_NPA_FIRST_PARAMETER_PATTERN,
        ADS_TLB_NPA_SECOND_PARAMETER_PATTERN,
        ADS_BLB_NPA_FIRST_PARAMETER_PATTERN,
        ADS_BLB_NPA_SECOND_PARAMETER_PATTERN,
        ADS_MOBILE_IN_CONTENT_NPA_FIRST_PARAMETER_PATTERN,
        ADS_MOBILE_IN_CONTENT_NPA_SECOND_PARAMETER_PATTERN
    )}};
  }

  @DataProvider
  public static Object[][] adsA9DataProvider() {
    return new Object[][]{{ADS_A9_INSTANT_GLOBALS, Arrays.asList(ADS_A9_PATTERN)}};
  }

  @DataProvider
  public static Object[][] adsNordicsDataProvider() {
    return new Object[][]{{Arrays.asList(ADS_NORDICS_PATTERN)}};
  }

  @DataProvider
  public static Object[][] adsPetametricsDataProvider() {
    return new Object[][]{{Arrays.asList(PETAMETRICS_PATTERN)}};
  }

  @DataProvider
  public static Object[][] adsQuantcastDataProvider() {
    return new Object[][]{{Arrays.asList(ADS_QUANTCAST_PATTERN)}};
  }

  @DataProvider
  public static Object[][] adsQuantcastSecureDataProvider() {
    return new Object[][]{{Arrays.asList(ADS_QUANTCAST_SECURE_PATTERN)}};
  }

  @DataProvider
  public static Object[][] adsComscoreDataProvider() {
    return new Object[][]{{Arrays.asList(ADS_COMSCORE_PATTERN)}};
  }

  @DataProvider
  public static Object[][] adsGoogleAnalyticsDataProvider() {
    return new Object[][]{{Arrays.asList(ADS_GOOGLE_ANALYTICS_REJECTED_PATTERN)}};
  }

  @DataProvider
  public static Object[][] googleAnalyticAnonymizedUser() {
    return new Object[][]{{Arrays.asList(GOOGLE_ANALYTICS_ANONYMIZED_USER)}};
  }

  @DataProvider
  public static Object[][] adsTrackingPixelsOnConsecutivePages() {
    return new Object[][]{
        {Arrays.asList(ADS_COMSCORE_PATTERN, ADS_QUANTCAST_PATTERN), ADS_ARTICLE_PAGES}};
  }

  @DataProvider
  public static Object[][] adsTrackingPixelsSent() {
    return new Object[][]{{Arrays.asList(
        ADS_COMSCORE_PATTERN,
        ADS_QUANTCAST_PATTERN,
        ADS_KRUX_PATTERN,
        ADS_GOOGLE_ANALYTICS_PATTERN
    )}};
  }

  @DataProvider
  public static Object[][] f2TrackingURLsForOptIn() {
    return new Object[][]{{ADS_GOOGLE_ANALYTICS_REJECTED_PATTERN, false},
                          {ADS_GOOGLE_ANALYTICS_PATTERN, true}, {ADS_QUANTCAST_PATTERN, true},
                          {ADS_KRUX_PATTERN, true}, {ADS_COMSCORE_PATTERN, true}};
  }

  @DataProvider
  public static Object[][] f2TrackingURLsForOptOut() {
    return new Object[][]{{ADS_GOOGLE_ANALYTICS_REJECTED_PATTERN, true},
                          {ADS_QUANTCAST_PATTERN, false}, {ADS_KRUX_PATTERN, false},
                          {ADS_COMSCORE_PATTERN, false}};
  }
}

