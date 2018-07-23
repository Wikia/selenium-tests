package com.wikia.webdriver.elements.fandom;

import com.wikia.webdriver.elements.fandom.components.WPNotifications;

import lombok.Getter;

public abstract class FandomWPPage<T> extends FandomPage<T> {

  @Getter(lazy = true)
  private final WPNotifications notifications = new WPNotifications();
}
