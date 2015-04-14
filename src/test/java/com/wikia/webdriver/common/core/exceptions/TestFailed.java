package com.wikia.webdriver.common.core.exceptions;

/**
 * Created by Ludwik on 2015-04-14.
 */
public class TestFailed extends Throwable {

  @Override
  public String getMessage() {
    return "Test failed due to errors during execution";
  }
}
