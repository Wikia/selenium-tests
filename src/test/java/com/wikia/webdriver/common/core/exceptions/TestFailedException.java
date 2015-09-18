package com.wikia.webdriver.common.core.exceptions;

public class TestFailedException extends Exception {

  @Override
  public String getMessage() {
    return "Test failed due to errors during execution";
  }
}
