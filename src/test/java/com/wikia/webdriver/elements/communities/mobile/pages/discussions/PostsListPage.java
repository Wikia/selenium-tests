package com.wikia.webdriver.elements.communities.mobile.pages.discussions;

import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.*;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.category.CategoriesFieldset;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.desktop.PostsCreatorDesktop;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.mobile.PostsCreatorMobile;

import com.google.common.base.Function;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

public class PostsListPage extends PageWithPosts {

  public static final String PATH = "/f";
  @Getter(lazy = true)
  private final PostEditor postEditor = new PostEditor();
  @Getter(lazy = true)
  private final PostsCreatorDesktop postsCreatorDesktop = new PostsCreatorDesktop();
  @Getter(lazy = true)
  private final PostsCreatorMobile postsCreatorMobile = new PostsCreatorMobile();
  @Getter(lazy = true)
  private final SignInToFollowModalDialog signInDialog = new SignInToFollowModalDialog();
  @Getter(lazy = true)
  private final ErrorMessages errorMessages = new ErrorMessages();
  @Getter(lazy = true)
  private final CategoriesFieldset categories = new CategoriesFieldset();
  @FindBy(css = ".guidelines-link")
  private WebElement guidelinesLink;

  @Override
  public PostsListPage open() {
    driver.get(getUrlWithCacheBuster(urlBuilder.getUrl() + PATH));
    return this;
  }

  public void waitForLoadingSpinnerWith(final String categoryName) {
    waitForLoadingSpinner();

    changeImplicitWait(IMPLICIT_SHORT, TimeUnit.SECONDS);
    try {
      new FluentWait<>(getPost()).withTimeout(DiscussionsConstants.TIMEOUT, TimeUnit.SECONDS)
          .until((Function<Post, Boolean>) p -> p.getPosts()
              .stream()
              .allMatch(postEntity -> postEntity.findCategory().endsWith(categoryName)));
    } finally {
      restoreDefaultImplicitWait();
    }
  }

  public GuidelinesPage openGuidelinesPage() {
    wait.forElementClickable(guidelinesLink);
    guidelinesLink.click();

    return new GuidelinesPage();
  }
}
