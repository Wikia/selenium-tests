package com.wikia.webdriver.elements.communities.mobile.pages.discussions;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.DeleteAllButton;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.ErrorMessages;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.SignInToFollowModalDialog;

import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.regex.Pattern;

public class UserPostsPage extends PageWithPosts {

  private static final Pattern PAGE_PATTERN = Pattern.compile("/d/u/\\d+");
  private static final String PATH = "/d/u/%s";
  private static final String NON_EXISTING_USER_ID = "4809883";
  @Getter(lazy = true)
  private final SignInToFollowModalDialog signInDialog = new SignInToFollowModalDialog();
  @Getter(lazy = true)
  private final ErrorMessages errorMessages = new ErrorMessages();
  @Getter(lazy = true)
  private final DeleteAllButton deleteAll = new DeleteAllButton();
  /**
   * moderation section visible to mod+ users in mobile view
   */
  @FindBy(css = ".discussion-dropdown > .wds-dropdown")
  private WebElement moderation;

  public static boolean is(String url) {
    return PAGE_PATTERN.matcher(url).find();
  }

  public UserPostsPage open(String userId) {
    driver.get(getUrlWithCacheBuster(urlBuilder.getUrl() + String.format(PATH, userId)));
    waitForEmberLoad();
    return this;
  }

  @Override
  public UserPostsPage open() {
    return open(NON_EXISTING_USER_ID);
  }

  public UserPostsPage expandModeration() {
    try {
      moderation.click();
    } catch (NoSuchElementException e) {
      Log.info("Moderation dropdown not found", e);
    }
    return this;
  }
}
