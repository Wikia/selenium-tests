package com.wikia.webdriver.common.core.helpers;


import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import lombok.Getter;

public class ForgottenPasswordUserPool {

  private static final Credentials CREDENTIALS = Configuration.getCredentials();

  @Getter
  private UserWithEmail user1 = new UserWithEmail(User.FORGOTTEN_PASSWORD,
    CREDENTIALS.forgottenPasswordEmail1Address,
    CREDENTIALS.forgottenPasswordEmail1Password);

  @Getter
  private UserWithEmail user2 = new UserWithEmail(User.FORGOTTEN_PASSWORD_SPACES,
    CREDENTIALS.forgottenPasswordEmail2Address,
    CREDENTIALS.forgottenPasswordEmail2Password);

}
