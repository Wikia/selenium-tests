package com.wikia.webdriver.common.remote.operations;

import com.wikia.webdriver.common.core.helpers.User;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONObject;


class PostRemoteOperation extends BaseRemoteOperation {

  PostRemoteOperation(User user) {
    super(user);
  }

  public String execute(final String url, final JSONObject jsonObject) {
    return super.execute(new HttpPost(url), jsonObject);
  }
}
