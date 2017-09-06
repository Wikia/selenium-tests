package com.wikia.webdriver.common.core.helpers;


import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import lombok.Value;

@Value
public class UserWithEmailPool {

  private static final Credentials CREDENTIALS = Configuration.getCredentials();

  private UserWithEmail user = new UserWithEmail(User.FORGOTTEN_PASSWORD,
    CREDENTIALS.forgottenPasswordEmail1Address,
    CREDENTIALS.forgottenPasswordEmail1Password);

  private UserWithEmail userWithSpaces = new UserWithEmail(User.FORGOTTEN_PASSWORD_SPACES,
    CREDENTIALS.forgottenPasswordEmail2Address,
    CREDENTIALS.forgottenPasswordEmail2Password);

  private UserWithEmail emailOnlyUser1 = new UserWithEmail(null,
    CREDENTIALS.emailQaart1,
    CREDENTIALS.emailPasswordQaart1);

  private UserWithEmail emailOnlyUser2 = new UserWithEmail(null,
    CREDENTIALS.emailQaart2,
    CREDENTIALS.emailPasswordQaart2);

}
