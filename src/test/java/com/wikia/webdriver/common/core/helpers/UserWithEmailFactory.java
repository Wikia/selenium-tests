package com.wikia.webdriver.common.core.helpers;


import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;

public class UserWithEmailFactory {

  private UserWithEmailFactory() {
    // no-op
  }

  private static final Credentials CREDENTIALS = Configuration.getCredentials();

  public static UserWithEmail getUser() {
    return new UserWithEmail(User.FORGOTTEN_PASSWORD,
      CREDENTIALS.forgottenPasswordEmail1Address,
      CREDENTIALS.forgottenPasswordEmail1Password);
  }

  public static UserWithEmail getUserWithSpaces() {
    return new UserWithEmail(User.FORGOTTEN_PASSWORD_SPACES,
      CREDENTIALS.forgottenPasswordEmail2Address,
      CREDENTIALS.forgottenPasswordEmail2Password);
  }

  public static UserWithEmail getEmailOnlyUser1() {
    return new UserWithEmail(null,
      CREDENTIALS.emailQaart1,
      CREDENTIALS.emailPasswordQaart1);
  }

  public static UserWithEmail getEmailOnlyUserForConfirmation() {
    return new UserWithEmail(null,
        CREDENTIALS.confirmEmailAddress,
        CREDENTIALS.confirmEmailPassword);
  }

}
