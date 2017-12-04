package com.webdriver.common.core.exceptions;

public class TestFailedException extends Exception {

  public TestFailedException(Throwable cause){
    super("Test failed due to errors during execution", cause);
  }

  @Override
  public String getMessage() {
    return "Test failed due to errors during execution";
  }
}
