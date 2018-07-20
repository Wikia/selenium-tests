package com.wikia.webdriver.common.logging;

public enum LogType implements LogData {
  STEP("step"), STACKTRACE("stacktrace");

  private String cssClass;

  LogType(String cssClass) {
    this.cssClass = cssClass;
  }

  @Override
  public String cssClass() {
    return cssClass;
  }
}
