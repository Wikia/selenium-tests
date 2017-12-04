package com.webdriver.common.remote.operations.http;

import com.webdriver.common.core.helpers.User;
import org.apache.http.client.methods.HttpGet;


public class GetRemoteOperation extends BaseRemoteOperation {

  public GetRemoteOperation(User user) {
    super(user);
  }

  public String execute(final String url) {
    return super.execute(new HttpGet(url));
  }
}
