package com.webdriver.common.driverprovider;

public enum UserAgentsRegistry {

  SONY_TVS("Mozilla/5.0(X11; sony_tvs; Linux x86_64; rv:24.0)Gecko/20100101 Firefox/24.0"),
  IPHONE(
      "Mozilla/6.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/8.0 Mobile/10A5376e Safari/8536.25");

  private String userAgent;

  UserAgentsRegistry(String userAgent) {
    this.userAgent = userAgent;
  }

  public String getUserAgent() {
    return userAgent;
  }
}
