package com.webdriver.common.remote.discussions;

import com.webdriver.common.core.helpers.User;
import com.webdriver.common.remote.operations.http.DeleteRemoteOperation;

public class UnlockPost extends PostPadlock {

  public UnlockPost(User user) {
    super(new DeleteRemoteOperation(user));
  }
}
