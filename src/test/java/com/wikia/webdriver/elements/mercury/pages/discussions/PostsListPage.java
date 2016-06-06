package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.wikia.webdriver.elements.mercury.components.discussions.common.OptionsPostAndReply;
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


public class PostsListPage extends WikiBasePageObject {

  @Getter(lazy = true)
  private final Post post = new Post();

  @Getter(lazy = true)
  private final OptionsPostAndReply postOptions = new OptionsPostAndReply();

  @Getter(lazy = true)
  private final PostEditor postEditor = new PostEditor();

  @Getter(lazy = true)
  private final BackButtons backButtons = new BackButtons();

  @Getter(lazy = true)
  private final CommunityBadge communityBadge = new CommunityBadge();

  @Getter(lazy = true)
  private final HeroUnit heroUnit = new HeroUnit();

  @Getter(lazy = true)
  private final Moderation moderationTools = new Moderation();

  @Getter(lazy = true)
  private final PostsCreatorDesktop postCreatorDesktop = new PostsCreatorDesktop();

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

  private static final String PATH = "d/f/%s";
  private static final String DEFAULT_FORUM_ID = "1362702";

  public PostsListPage open(String wikiID) {
    driver.get(urlBuilder.getUrlForWiki().replace("/wiki", "") + String.format(PATH, wikiID));
    return this;
  }

  public PostsListPage open() {
    return open(DEFAULT_FORUM_ID);
  }
}
