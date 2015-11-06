package com.wikia.webdriver.common.users;

import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.configuration.Configuration;

public class FacebookTestUser {
  private static final String envType = Configuration.getEnvType();

  public static TestUser getUser() {
    return new TestUser(XMLReader.getValue(String.format("ci.user.facebook.%s.username", envType)),
        XMLReader.getValue(String.format("ci.user.facebook.%s.password", envType)),
        XMLReader.getValue(String.format("ci.user.facebook.%s.email", envType)));
  }
}
