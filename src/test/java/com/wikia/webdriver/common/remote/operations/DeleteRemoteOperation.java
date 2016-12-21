package com.wikia.webdriver.common.remote.operations;

import com.wikia.webdriver.common.core.helpers.User;
import org.apache.http.client.methods.HttpDelete;


public class DeleteRemoteOperation extends BaseRemoteOperation {

  DeleteRemoteOperation(User user) {
    super(user);
  }

  public String execute(final String url) {
    return super.execute(new HttpDelete(url));
  }
}
