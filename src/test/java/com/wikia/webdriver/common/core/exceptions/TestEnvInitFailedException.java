package com.wikia.webdriver.common.core.exceptions;

public class TestEnvInitFailedException extends RuntimeException {

  public TestEnvInitFailedException() {
    super("Failed to initialize test env");
  }

  public TestEnvInitFailedException() {
    super();
  }

  public TestEnvInitFailedException(String message) {
    super(message);
  }
}
