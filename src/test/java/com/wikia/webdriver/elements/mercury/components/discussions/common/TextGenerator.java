package com.wikia.webdriver.elements.mercury.components.discussions.common;

public final class TextGenerator {

  private static final String TEXT = "Text %d";

  private static final String UNIQUE_TEXT_FORMAT = "Automated test, timestamp %d";

  private static final String CATEGORY_NAME_FORMAT = "Category %d";

  private static final int MAX_CATEGORY_NAME_LENGTH = 20;

  private TextGenerator() {
    throw new AssertionError();
  }

  public static String defaultText() {
    return String.format(TEXT, System.currentTimeMillis());
  }

  public static String createUniqueText() {
    return String.format(UNIQUE_TEXT_FORMAT, System.currentTimeMillis());
  }

  public static String createUniqueCategoryName() {
    return String.format(CATEGORY_NAME_FORMAT, System.currentTimeMillis()).substring(0, MAX_CATEGORY_NAME_LENGTH);
  }
}
