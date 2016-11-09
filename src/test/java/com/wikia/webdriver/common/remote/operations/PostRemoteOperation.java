package com.wikia.webdriver.common.remote.operations;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class PostRemoteOperation extends BaseRemoteOperation {

  PostRemoteOperation(User user) {
    super(user);
  }

  public String execute(final String url, final JSONObject jsonObject) {
    String result = StringUtils.EMPTY;

    try {
      final HttpPost post = new HttpPost(url);
      post.setEntity(new StringEntity(jsonObject.toString()));
      result = super.execute(post);
    } catch (UnsupportedEncodingException x) {
      PageObjectLogging.log("Error while creating http post entity.", ExceptionUtils.getStackTrace(x), false);
    }

    return result;
  }
}
