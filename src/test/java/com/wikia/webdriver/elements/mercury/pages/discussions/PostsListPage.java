package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.google.common.base.Predicate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.*;
import com.wikia.webdriver.elements.mercury.components.discussions.common.category.CategoriesFieldset;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.PostEditorDesktop;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.PostEditorMobile;
import lombok.Getter;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

public class PostsListPage extends PageWithPosts {

  public static final String PATH = "/d/f";

  @Getter(lazy = true)
  private final PostEditorDesktop postsCreatorDesktop = new PostEditorDesktop();

  @Getter(lazy = true)
  private final PostEditorMobile postsCreatorMobile = new PostEditorMobile();

  @Getter(lazy = true)
  private final SignInToFollowModalDialog signInDialog = new SignInToFollowModalDialog();

  @Getter(lazy = true)
  private final ErrorMessages errorMessages = new ErrorMessages();

  @Getter(lazy = true)
  private final CategoriesFieldset categories = new CategoriesFieldset();

  @Override
  public PostsListPage open() {
    driver.get(urlBuilder.getUrlForWiki() + PATH);
    waitForPageReload();
    return this;
  }

  public void waitForPageReloadWith(final String categoryName) {
    waitForPageReload();

    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      new FluentWait<>(getPost())
          .withTimeout(DiscussionsConstants.TIMEOUT, TimeUnit.SECONDS)
          .until((Predicate<Post>) p -> p.getPosts().stream()
              .allMatch(postEntity -> postEntity.findCategory().endsWith(categoryName)));
    } finally {
      restoreDefaultImplicitWait();
    }
  }

}
