package com.wikia.webdriver.common.users;

import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.configuration.Configuration;

public class FacebookTestUser {
  private static final String ENV_TYPE = Configuration.getEnvType().getKey();

  private FacebookTestUser() {
  }

  public static TestUser getUser() {
    return new TestUser(
        XMLReader.getValue(String.format("ci.user.facebook.%s.username", ENV_TYPE)),
        XMLReader.getValue(String.format("ci.user.facebook.%s.password", ENV_TYPE)),
        XMLReader.getValue(String.format("ci.user.facebook.%s.email", ENV_TYPE)));
  }
}
