package com.wikia.webdriver.common.remote.operations.http;


import com.wikia.webdriver.common.core.Helios;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.remote.Discussions;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class NoAuthOperation extends PostRemoteOperation {
  public NoAuthOperation() {
    super(null);
  }

  private String makeRequest(final CloseableHttpClient client, final HttpRequestBase request)
    throws IOException {
    String result = StringUtils.EMPTY;
    try {
      try (CloseableHttpResponse response = client.execute(request)) {
        result = handleResponse(request, response);
      }
    } catch (UnsupportedEncodingException x) {
      PageObjectLogging
        .log("Error while creating post entity.", ExceptionUtils.getStackTrace(x), false);
    }
    return result;
  }
}
