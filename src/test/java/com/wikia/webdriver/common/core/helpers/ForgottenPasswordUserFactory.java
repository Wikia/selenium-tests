package com.wikia.webdriver.common.core.helpers;


import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import lombok.Builder;
import lombok.Getter;

public final class ForgottenPasswordUserFactory {

  @Builder
  public static class ForgottenPasswordUser {
    private User user;

    @Getter
    private String email;

    @Getter
    private String emailPassword;

    public String getUsername() {
      return user.getUserName();
    }

    public String getPassword() {
      return user.getPassword();
    }
  }

  private static Credentials credentials = Configuration.getCredentials();

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
