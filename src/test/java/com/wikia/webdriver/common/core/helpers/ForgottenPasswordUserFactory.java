package com.wikia.webdriver.common.core.helpers;


import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import lombok.Builder;
import lombok.Getter;

public class ForgottenPasswordUserFactory {

  private static Credentials credentials = Configuration.getCredentials();

  @Builder
  @Getter
  public class ForgottenPasswordUser {
    private User user;
    private String email;
    private String emailPassword;
    private String username = user.getUserName();
    private String password = user.getPassword();
  }

  private static ForgottenPasswordUser user(User user, String email, String password) {
    return ForgottenPasswordUser.builder()
      .user(user)
      .email(email)
      .emailPassword(password)
      .build();
  }

  public static ForgottenPasswordUser user1() {
    return user(
      User.FORGOTTEN_PASSWORD,
      credentials.forgottenPasswordEmail1Address,
      credentials.forgottenPasswordEmail1Password);
  }

  public static ForgottenPasswordUser user2() {
    return user(
      User.FORGOTTEN_PASSWORD_SPACES,
      credentials.forgottenPasswordEmail2Address,
      credentials.forgottenPasswordEmail2Password);
  }
}
