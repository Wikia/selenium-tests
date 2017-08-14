package com.wikia.webdriver.common.core.helpers;


import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import lombok.Builder;
import lombok.Value;

public final class ForgottenPasswordUserFactory {

  private ForgottenPasswordUserFactory() {
    // no-op
  }

  @Builder
  @Value
  public static class ForgottenPasswordUser {
    private User user;
    private String email;
    private String emailPassword;

    public String getUsername() {
      return user.getUserName();
    }

    public String getPassword() {
      return user.getPassword();
    }
  }

  private static final Credentials CREDENTIALS = Configuration.getCredentials();

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
      CREDENTIALS.forgottenPasswordEmail1Address,
      CREDENTIALS.forgottenPasswordEmail1Password);
  }

  public static ForgottenPasswordUser user2() {
    return user(
      User.FORGOTTEN_PASSWORD_SPACES,
      CREDENTIALS.forgottenPasswordEmail2Address,
      CREDENTIALS.forgottenPasswordEmail2Password);
  }
}
