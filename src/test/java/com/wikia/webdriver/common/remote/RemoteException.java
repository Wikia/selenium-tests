package com.wikia.webdriver.common.remote;

public class RemoteException extends RuntimeException {

  public RemoteException(String message) {
    super(message);
  }

  public RemoteException(String message, Throwable cause) {
    super(message, cause);
  }
}
