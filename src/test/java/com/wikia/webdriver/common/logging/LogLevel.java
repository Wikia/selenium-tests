package com.wikia.webdriver.common.logging;

public enum LogLevel implements LogData {
  ERROR("error"), OK("success"), WARNING("warning"), INFO("info"), DEBUG("lowLevelAction");

  private String cssClass;

  LogLevel(String cssClass) {
    this.cssClass = cssClass;
  }

  @Override
  public String cssClass() {
    return cssClass;
  }
}
