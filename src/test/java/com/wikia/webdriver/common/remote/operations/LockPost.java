package com.wikia.webdriver.common.remote.operations;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.operations.http.PutRemoteOperation;

public class LockPost extends PostPadlock {

  public LockPost(User user) {
    super(new PutRemoteOperation(user));
  }
}
