package com.wikia.webdriver.elements.fandom.pages;

import lombok.Getter;

import com.wikia.webdriver.elements.fandom.FandomPage;
import com.wikia.webdriver.elements.fandom.components.HeroUnit;

public class MainPage extends FandomPage<MainPage> {

  @Getter(lazy = true)
  private final HeroUnit heroUnit = new HeroUnit();
  private String URL = "http://qa.fandom.wikia.com/";

  @Override
  public MainPage open() {
    getUrl(URL);

    return this;
  }

}
