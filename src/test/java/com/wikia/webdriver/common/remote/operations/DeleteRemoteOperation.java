package com.wikia.webdriver.common.remote.operations;

import com.wikia.webdriver.common.core.helpers.User;
import org.apache.http.client.methods.HttpDelete;


public class DeleteRemoteOperation extends BaseRemoteOperation implements RemoteOperation {

  DeleteRemoteOperation(User user) {
    super(user);
  }

  @Override
  public String execute(final String url) {
    return super.execute(new HttpDelete(url));
  }
}
