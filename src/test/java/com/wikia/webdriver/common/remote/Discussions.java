package com.wikia.webdriver.common.remote;

public class Discussions {

  private static final String SERVICES_URL = "https://services.wikia.com/";

  private static final String DISCUSSIONS_SERVICE = "discussion/";

  public static final String ACCESS_TOKEN_HEADER = "X-Wikia-AccessToken";

  public static final String DAUTO_WIKIA_SITE_ID = "1362702";

  public static String service(String url) {
    return SERVICES_URL + DISCUSSIONS_SERVICE + url;
  }
}
