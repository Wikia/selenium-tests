package com.wikia.webdriver.common.core.helpers;

import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.configuration.Configuration;

import java.io.File;

public enum User {
  USER("ci.user.regular.username", "ci.user.regular.password", "ci.user.regular.user_id", "ci.user.regular.access_token"),
  USER_2("ci.user.regular2.username", "ci.user.regular2.password"),
  USER_3("ci.user.regular3.username", "ci.user.regular3.password"),
  USER_4("ci.user.regular4.username", "ci.user.regular4.password"),
  USER_5("ci.user.regular5.username", "ci.user.regular5.password"),
  USER_6("ci.user.regular6.username", "ci.user.regular6.password"),
  USER_9("ci.user.regular9.username", "ci.user.regular9.password"),
  USER_12("ci.user.regular12.username", "ci.user.regular12.password", "ci.user.regular12.user_id"),
  IMAGE_REVIEWER("ci.user.reviewer.username", "ci.user.reviewer.password", "ci.user.reviewer.user_id"),
  STAFF("ci.user.wikiastaff.username", "ci.user.wikiastaff.password", "", "ci.user.wikiastaff.access_token"),
  ANONYMOUS("anonymous", "anonymous"),
  DISCUSSIONS_MODERATOR("ci.user.discussions.moderator.username", "ci.user.discussions.moderator.password"),
  REGULAR_USER_JAPAN("ci.user.language2.username", "ci.user.language2.password"),
  USER_GO_SEARCH_PREFERRED("ci.user.goSearchPreferredUser.username",
                           "ci.user.goSearchPreferredUser.password"),
  BLOCKED_USER("ci.user.tooManyLoginAttempts.username", "ci.user.tooManyLoginAttempts.password"),
  CONSTANTLY_BLOCKED_USER("ci.user.constantlyBlockedAccountUser.username", "ci.user.constantlyBlockedAccountUser.password"),
  GOOGLE_CONNECTED("ci.user.google_connected.username", "ci.user.google_connected.password"),
  CONTENT_REVIEWER("ci.user.wikiaContentReviewer.username", "ci.user.wikiaContentReviewer.password");

  private final String userName;

  private final String password;
  private final String filePath = Configuration.getCredentialsFilePath();
  private String userId;
  private String accessToken;

  User(String userNameKey, String passwordKey) {
    this.userName = XMLReader.getValue(new File(filePath), userNameKey);
    this.password = XMLReader.getValue(new File(filePath), passwordKey);
    this.userId = "";
    this.accessToken = "";
  }

  User(String userNameKey, String passwordKey, String userId) {
    this.userName = XMLReader.getValue(new File(filePath), userNameKey);
    this.password = XMLReader.getValue(new File(filePath), passwordKey);
    this.userId = XMLReader.getValue(new File(filePath), userId);
    this.accessToken = "";
  }

  User(String userNameKey, String passwordKey, String userId, String accessToken) {
    this.userName = XMLReader.getValue(new File(filePath), userNameKey);
    this.password = XMLReader.getValue(new File(filePath), passwordKey);
    this.userId = XMLReader.getValue(new File(filePath), userId);
    this.accessToken = XMLReader.getValue(new File(filePath), accessToken);
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }

  public String getUserId() {
    return userId;
  }

  public String getAccessToken() {
    return accessToken;
  }
}
