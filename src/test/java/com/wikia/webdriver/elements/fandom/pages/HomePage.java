package com.wikia.webdriver.elements.fandom.pages;

import com.wikia.webdriver.elements.fandom.components.FanFeed;
import com.wikia.webdriver.elements.fandom.components.LoadMore;
import lombok.Getter;

import com.wikia.webdriver.elements.fandom.FandomPage;
import com.wikia.webdriver.elements.fandom.components.HeroBlock;

public class HomePage extends FandomPage<HomePage> {

  @Getter(lazy = true)
  private final HeroBlock heroBlock = new HeroBlock();

  @Getter(lazy = true)
  private final FanFeed fanFeed = new FanFeed();

  @Getter(lazy = true)
  private final LoadMore loadMore = new LoadMore();

  @Override
  public HomePage open() {
    getUrl(SITE_URL);

    return this;
  }

  /**
   * Only use this for read-only b/c it touches production
   * Good for testing CORS
   * @return HomePage
   */
  public HomePage openWWW() {
    getUrl(WWW_SITE_URL);

    return this;
  }
}
