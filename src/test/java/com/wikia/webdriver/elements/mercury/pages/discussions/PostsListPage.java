package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.wikia.webdriver.elements.mercury.components.discussions.common.DiscussionsConstants;
import com.wikia.webdriver.elements.mercury.components.discussions.common.ErrorMessages;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Post;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEditor;
import com.wikia.webdriver.elements.mercury.components.discussions.common.SignInToFollowModalDialog;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.BackButtons;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.CommunityBadge;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.HeroUnit;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.Moderation;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.PostsCreatorDesktop;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.Promoting;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.SortingFiltersOnDesktop;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.DiscussionsHeader;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.FiltersPopOver;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.PostsCreatorMobile;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import com.google.common.base.Predicate;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;


public class PostsListPage extends WikiBasePageObject implements AvailablePage {

  private static final String DEFAULT_FORUM_ID = "1362702";

  private static final String PATH = "/d/f/%s";

  @Getter(lazy = true)
  private final Post post = new Post();

  @Getter(lazy = true)
  private final PostEditor postEditor = new PostEditor();

  @Getter(lazy = true)
  private final BackButtons backButtons = new BackButtons();

  @Getter(lazy = true)
  private final CommunityBadge communityBadge = new CommunityBadge();

  @Getter(lazy = true)
  private final HeroUnit heroUnit = new HeroUnit();

  @Getter(lazy = true)
  private final Moderation moderation = new Moderation();

  @Getter(lazy = true)
  private final PostsCreatorDesktop postsCreatorDesktop = new PostsCreatorDesktop();

  @Getter(lazy = true)
  private final PostsCreatorMobile postsCreatorMobile = new PostsCreatorMobile();

  @Getter(lazy = true)
  private final Promoting promoting = new Promoting();

  @Getter(lazy = true)
  private final SortingFiltersOnDesktop sortingFiltersOnDesktop = new SortingFiltersOnDesktop();

  @Getter(lazy = true)
  private final DiscussionsHeader discussionsHeader = new DiscussionsHeader();

  @Getter(lazy = true)
  private final FiltersPopOver filtersPopOver = new FiltersPopOver();

  @Getter(lazy = true)
  private final SignInToFollowModalDialog signInToFollowModalDialog = new SignInToFollowModalDialog();

  @Getter(lazy = true)
  private final ErrorMessages errorMessages = new ErrorMessages();


  public PostsListPage open(String wikiID) {
    driver.get(urlBuilder.getUrlForWiki() + String.format(PATH, wikiID));
    return this;
  }

  public PostsListPage open() {
    return open(DEFAULT_FORUM_ID);
  }

  public PostsListPage waitForPageReload() {
    wait.forElementVisible(By.className("loading-overlay"));
    wait.forElementNotVisible(By.className("loading-overlay"));
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
