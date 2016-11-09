package com.wikia.webdriver.common.remote;

public class Discussions {

  private final static String SERVICES_URL = "https://services.wikia.com/";

  private final static String DISCUSSIONS_SERVICE = "discussion/";

  public final static String ACCESS_TOKEN_HEADER = "X-Wikia-AccessToken";

  public final static String DAUTO_WIKIA_SITE_ID = "1362702";

  public static String service(String url) {
    return SERVICES_URL + DISCUSSIONS_SERVICE + url;
  }
}
