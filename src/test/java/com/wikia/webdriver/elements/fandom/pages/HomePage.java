package com.wikia.webdriver.elements.fandom.pages;

import lombok.Getter;

import com.wikia.webdriver.elements.fandom.FandomPage;
import com.wikia.webdriver.elements.fandom.components.HeroBlock;

public class HomePage extends FandomPage<HomePage> {

  @Getter(lazy = true)
  private final HeroBlock heroBlock = new HeroBlock();

  @Override
  public HomePage open() {
    getUrl(SITE_URL);

    return this;
  }

  public void gaPageViewTracked() {

  }
}
