package com.wikia.webdriver.common.core.exceptions;

public class TestEnvInitFailedException extends RuntimeException {

  public TestEnvInitFailedException() {
    super("Failed to initialize test env");
  }

  public TestEnvInitFailedException(Throwable e) {
    super(e);
  }

  public TestEnvInitFailedException(String message) {
    super(message);
  }
}
