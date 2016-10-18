package com.wikia.webdriver.elements.mercury.components.discussions.common;

public final class TextGenerator {

  private static final String TEXT = "Text";

  private static final String UNIQUE_TEXT_FORMAT = "Automated test, timestamp %d";

  private TextGenerator() {
    throw new AssertionError();
  }

  public static String defaultText() {
    return TEXT;
  }

  public static String createUniqueText() {
    return String.format(UNIQUE_TEXT_FORMAT, System.currentTimeMillis());
  }
}
