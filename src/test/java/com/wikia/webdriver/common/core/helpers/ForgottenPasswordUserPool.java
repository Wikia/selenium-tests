package com.wikia.webdriver.common.core.helpers;


import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;

public class ForgottenPasswordUserPool {

  private ForgottenPasswordUserPool() {
    //no-op
  }

  private static final Credentials CREDENTIALS = Configuration.getCredentials();

  public static UserWithEmail user1 = new UserWithEmail(User.FORGOTTEN_PASSWORD,
    CREDENTIALS.forgottenPasswordEmail1Address,
    CREDENTIALS.forgottenPasswordEmail1Password);

  public static UserWithEmail user2 = new UserWithEmail(User.FORGOTTEN_PASSWORD_SPACES,
    CREDENTIALS.forgottenPasswordEmail2Address,
    CREDENTIALS.forgottenPasswordEmail2Password);

}
