package com.wikia.webdriver.elements.mercury.pages.discussions;


import com.wikia.webdriver.elements.mercury.components.discussions.common.Post;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.components.discussions.common.SignInToFollowModalDialog;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.*;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.DiscussionsHeader;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.FiltersPopOver;
import lombok.Getter;

public abstract class PageWithPosts extends BasePage {

  @Getter(lazy = true)
  private final SortingFilterDesktop sortingFilterDesktop = new SortingFilterDesktop();

  @Getter(lazy = true)
  private final DiscussionsHeader discussionsHeader = new DiscussionsHeader();

  @Getter(lazy = true)
  private final FiltersPopOver filtersPopOver = new FiltersPopOver();

  @Getter(lazy = true)
  private final Promoting promoting = new Promoting();

  @Getter(lazy = true)
  private final CommunityBadge communityBadge = new CommunityBadge();

  @Getter(lazy = true)
  private final HeroUnit heroUnit = new HeroUnit();

  @Getter(lazy = true)
  private final Moderation moderation = new Moderation();

  @Getter(lazy = true)
  private final Post post = new Post();

  public abstract SignInToFollowModalDialog getSignInDialog();

  public abstract PageWithPosts open();

  public PostEntity getPostById(String postId) {
    return getPost()
      .getPosts()
      .stream()
      .peek(postEntity -> scrollTo(postEntity.getWebElement()))
      .filter(postEntity -> postEntity.findId().equals(postId))
      .findFirst()
      .orElseThrow(
        () -> new RuntimeException(String.format("Post with id [%s] not found on page", postId)));
  }

}
