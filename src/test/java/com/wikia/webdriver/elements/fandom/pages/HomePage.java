package com.wikia.webdriver.elements.fandom.pages;

import lombok.Getter;

import com.wikia.webdriver.elements.fandom.components.FlyOut;
import com.wikia.webdriver.elements.fandom.FandomPage;
import com.wikia.webdriver.elements.fandom.components.HeroBlock;

public class HomePage extends FandomPage<HomePage> {

  @Getter(lazy = true)
  private final HeroBlock heroBlock = new HeroBlock();

  @Getter(lazy = true)
  private final FlyOut flyOut = new FlyOut();

  @Override
  public HomePage open() {
    getUrl(SITE_URL);

    return this;
  }
}
