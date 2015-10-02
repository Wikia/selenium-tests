package com.wikia.webdriver.common.enums;

/**
 * @ownership Content X-Wing Wikia
 */
public class Mercury {

  /**
   * Warning - yellow
   */
  public static enum AlertType {
    Warning
  }

  public static enum AlertMessage {
    NOT_EXISTING_REDIRECT(
        "The link you followed is a redirect, but the page it directs to does not exist."),
    NOT_EXISTING_CATEGORY("Category not found"),
    NOT_EXISTING_SECTION("Section not found");

    private String message;

    AlertMessage(String message) {
      this.message = message;
    }

    public String getMessage() {
      return message;
    }
  }
}
