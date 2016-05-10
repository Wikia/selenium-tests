package com.wikia.webdriver.elements.fandom;

import lombok.Getter;

import com.wikia.webdriver.elements.fandom.components.WPNotifications;

public abstract class FandomWPPage<T> extends FandomPage<T> {

  @Getter(lazy = true)
  private final WPNotifications notifications = new WPNotifications();
}
