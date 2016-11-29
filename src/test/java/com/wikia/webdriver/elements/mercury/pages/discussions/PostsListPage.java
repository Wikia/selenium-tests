package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.wikia.webdriver.elements.mercury.components.discussions.common.ErrorMessages;
import com.wikia.webdriver.elements.mercury.components.discussions.common.IntroducingFollowingModal;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Post;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEditor;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.BackButtons;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.CommunityBadge;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.HeroUnit;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.Moderation;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.PostsCreatorDesktop;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.Promoting;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.SortingTool;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.DiscussionsHeader;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.FiltersPopOver;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.PostsCreatorMobile;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import lombok.Getter;


public class PostsListPage extends WikiBasePageObject implements PageWithPosts {

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
  private final IntroducingFollowingModal introducingFollowingModal = new IntroducingFollowingModal();

  @Getter(lazy = true)
  private final Moderation moderation = new Moderation();

  @Getter(lazy = true)
  private final PostsCreatorDesktop postsCreatorDesktop = new PostsCreatorDesktop();

  @Getter(lazy = true)
  private final PostsCreatorMobile postsCreatorMobile = new PostsCreatorMobile();

  @Getter(lazy = true)
  private final Promoting promoting = new Promoting();

  @Getter(lazy = true)
  private final SortingTool sortingTool = new SortingTool();

  @Getter(lazy = true)
  private final DiscussionsHeader discussionsHeader = new DiscussionsHeader();

  @Getter(lazy = true)
  private final FiltersPopOver filtersPopOver = new FiltersPopOver();

  @Getter(lazy = true)
  private final ErrorMessages errorMessages = new ErrorMessages();

  private static final String PATH = "/d/f/%s";
  private static final String DEFAULT_FORUM_ID = "1362702";


  public PostsListPage open(String wikiID) {
    driver.get(urlBuilder.getUrlForWiki() + String.format(PATH, wikiID));
    return this;
  }

  public PostsListPage open() {
    return open(DEFAULT_FORUM_ID);
  }
}
