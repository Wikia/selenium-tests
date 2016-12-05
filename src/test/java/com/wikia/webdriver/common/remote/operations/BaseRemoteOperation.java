package com.wikia.webdriver.common.remote.operations;

import com.wikia.webdriver.common.core.Helios;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.remote.Discussions;
import com.wikia.webdriver.common.remote.RemoteException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class BaseRemoteOperation {

  @Getter
  private final User user;

  String execute(final HttpEntityEnclosingRequestBase request) {
    String result = StringUtils.EMPTY;

    try (CloseableHttpClient client = HttpClientBuilder.create().disableContentCompression().build()) {
      result = makeRequest(client, request);
    } catch (IOException x) {
      PageObjectLogging.log("Error while creating/closing http client.", ExceptionUtils.getStackTrace(x), false);
    }

    return result;
  }

  private String makeRequest(final CloseableHttpClient client, final HttpEntityEnclosingRequestBase request)
      throws IOException {
    String result = StringUtils.EMPTY;

    try {
      request.setHeader(Discussions.ACCESS_TOKEN_HEADER, Helios.getAccessToken(user));

      try (CloseableHttpResponse response = client.execute(request)) {
        result = handleResponse(request, response);
      }
    } catch (UnsupportedEncodingException x) {
      PageObjectLogging.log("Error while creating post entity.", ExceptionUtils.getStackTrace(x), false);
    }

    return result;
  }

  private String handleResponse(final HttpEntityEnclosingRequestBase requestBase, final CloseableHttpResponse response)
      throws IOException {
    final String result = EntityUtils.toString(response.getEntity());

    if (response.getStatusLine().getStatusCode() >= 400) {
      PageObjectLogging.log("Error while making request.", result, false);
      throw new RemoteException("Error while invoking request. "
          + " Method: " + requestBase.getMethod()
          + " Url: " + requestBase.getURI().toString());
    }

    return result;
  }
}
