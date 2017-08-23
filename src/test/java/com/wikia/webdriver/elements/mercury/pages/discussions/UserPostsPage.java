package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.mercury.components.discussions.common.DeleteAllButton;
import com.wikia.webdriver.elements.mercury.components.discussions.common.ErrorMessages;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Post;
import com.wikia.webdriver.elements.mercury.components.discussions.common.SignInToFollowModalDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.regex.Pattern;

public class UserPostsPage extends WikiBasePageObject implements AvailablePage {

  @Getter(lazy = true) private final Post post = new Post();

  @Getter(lazy = true) private final SignInToFollowModalDialog signInToFollowModalDialog = new SignInToFollowModalDialog();

  @Getter(lazy = true) private final ErrorMessages errorMessages = new ErrorMessages();

  @Getter(lazy = true) private final DeleteAllButton deleteAll = new DeleteAllButton();

  /**
   * moderation section visible to mod+ users in mobile view
   */
  @FindBy(className = "header-dropdown-button")
  private WebElement moderation;

  private static final Pattern PAGE_PATTERN = Pattern.compile("/d/u/\\d+$");

  private static final String PATH = "/d/u/%s";

  private static final String NON_EXISTING_USER_ID = "4809883";

  public UserPostsPage open(String userId) {
    driver.get(urlBuilder.getUrlForWiki() + String.format(PATH, userId));
    waitForPageReload();
    return this;
  }

  public UserPostsPage open() {
    return open(NON_EXISTING_USER_ID);
  }

  public static boolean is(String url) {
    return PAGE_PATTERN.matcher(url).find();
  }

  public UserPostsPage expandModeration() {
    try {
      moderation.click();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo("Moderation dropdown not found", e);
    }
    return this;
  }

}
