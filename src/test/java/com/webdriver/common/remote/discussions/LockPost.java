package com.webdriver.common.remote.discussions;

import com.webdriver.common.core.helpers.User;
import com.webdriver.common.remote.operations.http.PutRemoteOperation;

public class LockPost extends PostPadlock {

  public LockPost(User user) {
    super(new PutRemoteOperation(user));
  }
}
