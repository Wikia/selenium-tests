package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.wikia.webdriver.elements.mercury.components.discussions.common.DeleteAllButton;
import com.wikia.webdriver.elements.mercury.components.discussions.common.ErrorMessages;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Post;
import com.wikia.webdriver.elements.mercury.components.discussions.common.SignInToFollowModalDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class UserPostsPage extends WikiBasePageObject implements AvailablePage {

  @Getter(lazy = true)
  private final Post post = new Post();

  @Getter(lazy = true)
  private final SignInToFollowModalDialog signInToFollowModalDialog = new SignInToFollowModalDialog();

  @Getter(lazy = true)
  private final ErrorMessages errorMessages = new ErrorMessages();

  @Getter(lazy = true)
  private final DeleteAllButton deleteAll = new DeleteAllButton();

  private static final Pattern PAGE_PATTERN = Pattern.compile("/d/u/\\d+$");

  private static final String PATH = "/d/u/%s";

  private static final String EXISTING_USER_ID = "1342502";

  private static final String NON_EXISTING_USER_ID = "4809883";

  public UserPostsPage open(String userId) {
    driver.get(urlBuilder.getUrlForWiki() + String.format(PATH, userId));
    return this;
  }

  public UserPostsPage open() {
    return open(NON_EXISTING_USER_ID);
  }

  public UserPostsPage openDefaultUserPage() {
    return open(EXISTING_USER_ID);
  }

  public static boolean is(String url) {
    return PAGE_PATTERN.matcher(url).find();
  }

  public static String extractUserIdFrom(String url) {
    return UserPostsPage.is(url) ? StringUtils.substringAfterLast(url, "/") : StringUtils.EMPTY;
  }
}
