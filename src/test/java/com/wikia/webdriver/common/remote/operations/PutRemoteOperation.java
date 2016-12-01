package com.wikia.webdriver.common.remote.operations;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class PutRemoteOperation extends BaseRemoteOperation {

  PutRemoteOperation(User user) {
    super(user);
  }

  public String execute(final String url) {
    return execute(url, null);
  }

  public String execute(final String url, final JSONObject jsonObject) {
    String result = StringUtils.EMPTY;

    try {
      final HttpPut put = new HttpPut(url);
      if (null != jsonObject) {
        put.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
        put.setEntity(new StringEntity(jsonObject.toString()));
      }
      result = super.execute(put);
    } catch (UnsupportedEncodingException x) {
      PageObjectLogging.log("Error while creating post entity.", ExceptionUtils.getStackTrace(x), false);
    }

    return result;
  }
}
