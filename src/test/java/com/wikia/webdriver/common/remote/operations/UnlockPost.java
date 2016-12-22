package com.wikia.webdriver.common.remote.operations;

import com.wikia.webdriver.common.core.helpers.User;

public class UnlockPost extends PostPadlock {

  public UnlockPost(User user) {
    super(new DeleteRemoteOperation(user));
  }
}
