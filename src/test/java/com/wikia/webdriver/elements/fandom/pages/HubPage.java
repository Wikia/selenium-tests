package com.wikia.webdriver.elements.fandom.pages;

import com.wikia.webdriver.elements.fandom.FandomPage;
import com.wikia.webdriver.elements.fandom.components.HeroUnit;
import lombok.Getter;

/**
 * Created by Ludwik on 2016-07-29.
 */
public class HubPage extends FandomPage<HubPage>{

  @Override
  public HubPage open() {
    getUrl(String.format("http://qa.fandom.wikia.com/%s", "games"));

    return this;
  }

    @Getter(lazy = true)
    private final HeroUnit heroUnit = new HeroUnit();
    private String URL = "http://qa.fandom.wikia.com/";

  public HubPage open(String hubName) {
    getUrl(String.format("http://qa.fandom.wikia.com/%s", hubName));

    return this;
  }
}
