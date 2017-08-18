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
  USER_6("ci.user.regular6.username", "ci.user.regular6.password", "ci.user.regular6.id"),
  USER_9("ci.user.regular9.username", "ci.user.regular9.password"),
  USER_11("ci.user.regular11.username", "ci.user.regular11.password"),
  USER_12("ci.user.regular12.username", "ci.user.regular12.password", "ci.user.regular12.user_id"),
  USER_GERMAN("ci.user.language5.username", "ci.user.language5.password"),
  USER_CNW("ci.user.cnw.username", "ci.user.cnw.password", "ci.user.cnw.user_id", "ci.user.cnw.access_token"),
  USER_ADMIN_FORUM("ci.user.forum.username", "ci.user.forum.password", "ci.user.forum.user_id", "ci.user.forum.access_token"),
  USER_MESSAGE_WALL("ci.user.message_wall.username", "ci.user.message_wall.password", "ci.user.message_wall.user_id", "ci.user.message_wall.access_token"),
  USER_VET_MODAL("ci.user.vet_modal.username", "ci.user.vet_modal.password", "ci.user.vet_modal.user_id", "ci.user.vet_modal.access_token"),
  IMAGE_REVIEWER("ci.user.reviewer.username", "ci.user.reviewer.password", "ci.user.reviewer.user_id"),
  STAFF("ci.user.wikiastaff.username", "ci.user.wikiastaff.password", "ci.user.wikiastaff.id", "ci.user.wikiastaff.access_token"),
  STAFF_FORUM("ci.user.wikiastaff.username", "ci.user.wikiastaff.password", "ci.user.wikiastaff.id", "ci.user.wikiastaff.access_token"),
  ANONYMOUS("anonymous", "anonymous"),
  DISCUSSIONS_ADMINISTRATOR("ci.user.discussions.administrator.username", "ci.user.discussions.administrator.password"),
  DISCUSSIONS_MODERATOR("ci.user.discussions.moderator.username", "ci.user.discussions.moderator.password"),
  VSTF("ci.user.vstf.username", "ci.user.vstf.password"),
  HELPER("ci.user.helper.username", "ci.user.helper.password"),
  REGULAR_USER_JAPAN("ci.user.language2.username", "ci.user.language2.password"),
  USER_GO_SEARCH_PREFERRED("ci.user.goSearchPreferredUser.username",
                           "ci.user.goSearchPreferredUser.password"),
  BLOCKED_USER("ci.user.tooManyLoginAttempts.username", "ci.user.tooManyLoginAttempts.password"),
  CONSTANTLY_BLOCKED_USER("ci.user.constantlyBlockedAccountUser.username", "ci.user.constantlyBlockedAccountUser.password"),
  GOOGLE_CONNECTED("ci.user.google_connected.username", "ci.user.google_connected.password"),
  CONTENT_REVIEWER("ci.user.wikiaContentReviewer.username", "ci.user.wikiaContentReviewer.password"),
  COMMENTS_REGULAR_USER("ci.user.comments.username", "ci.user.comments.password", "ci.user.comments.id", "ci.user.comments.access_token"),
  FORGOTTEN_PASSWORD("ci.user.forgottenPassword.username", "ci.user.forgottenPassword.password"),
  FORGOTTEN_PASSWORD_SPACES("ci.user.forgottenPasswordSpaces.username", "ci.user.forgottenPasswordSpaces.password"),
  MW119_ADMINISTRATOR("ci.user.mw_admin.username", "ci.user.mw_admin_password", "ci.user.mw_admin.user_id", "ci.user.mw_admin.access_token"),
  WIKIACTIVITY_USER("ci.user.wikiactivity.username", "ci.user.wikiactivity.password"),
  CURATED_CONTENT_USER("ci.user.curated_content.username", "ci.user.curated_content.password", "ci.user.curated_content.id", "ci.user.curated_content.access_token"),
  INFOBOX_BUILDER_ADMIN("ci.user.infobox_builder.username", "ci.user.infobox_builder.password", "ci.user.infobox_builder.id", "ci.user.infobox_builder.access_token"),
  CHAT_USER_TO_BE_BANNED("ci.user.chat.user_to_be_banned.username", "ci.user.chat.user_to_be_banned.password", "ci.user.chat.user_to_be_banned.user_id", "ci.user.chat.user_to_be_banned.access_token"),
  BLOGS("ci.user.blogs.username", "ci.user.blogs.password", "ci.user.blogs.user_id", "ci.chat.blogs.access_token"),
  THEME_DESIGNER("ci.user.theme_designer.username", "ci.user.theme_designer.password", "ci.user.theme_designer.user_id", "ci.chat.theme_designer.access_token"),
  FOLLOW_ARTICLE("ci.user.follow_article.username", "ci.user.follow_article.password", "ci.user.follow_article.user_id", "ci.chat.follow_article.access_token"),
  SUS_ADMIN("ci.user.sus_admin.username", "ci.user.sus_admin.password"),
  SUS_CHAT_USER("ci.user.sus_chat_user.username", "ci.user.sus_chat_user.password"),
  SUS_CHAT_USER2("ci.user.sus_chat_user2.username", "ci.user.sus_chat_user2.password"),
  SUS_CHAT_USER3("ci.user.sus_chat_user3.username", "ci.user.sus_chat_user3.password"),
  SUS_STAFF("ci.user.sus_staff.username", "ci.user.sus_staff.password"),
  SUS_STAFF2("ci.user.sus_staff2.username", "ci.user.sus_staff2.password"),
  SUS_CHAT_STAFF("ci.user.sus_staff_chat.username", "ci.user.sus_staff_chat.password"),
  SUS_CHAT_STAFF2("ci.user.sus_staff_chat2.username", "ci.user.sus_staff_chat2.password"),
  SUS_CHAT_BANNED_PERMANENTLY("ci.user.sus_chat_permanently_banned.username", "ci.user.sus_chat_permanently_banned.password"),
  SUS_REGULAR_USER("ci.user.sus_user.username", "ci.user.sus_user.password"),
  SUS_REGULAR_USER2("ci.user.sus_user2.username", "ci.user.sus_user2.password"),
  SUS_REGULAR_USER3("ci.user.sus_user3.username", "ci.user.sus_user3.password"),
  ;

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
