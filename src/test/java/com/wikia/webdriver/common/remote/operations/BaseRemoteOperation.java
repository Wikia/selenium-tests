package com.wikia.webdriver.common.remote.operations;

import com.wikia.webdriver.common.core.Helios;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.remote.Discussions;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class BaseRemoteOperation {

  @Getter
  private final User user;

  String execute(final HttpEntityEnclosingRequestBase requestBase) {
    String result = StringUtils.EMPTY;

    try (CloseableHttpClient client = HttpClientBuilder.create()
        .disableContentCompression()
        .build()) {
      try {
        requestBase.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
        requestBase.setHeader(Discussions.ACCESS_TOKEN_HEADER, Helios.getAccessToken(user));

        try (CloseableHttpResponse response = client.execute(requestBase)) {
          result = EntityUtils.toString(response.getEntity());

          if (response.getStatusLine().getStatusCode() >= 400) {
            PageObjectLogging.log("Error while making request.", result, false);
          }
        }
      } catch (UnsupportedEncodingException x) {
        PageObjectLogging.log("Error while creating post entity.", ExceptionUtils.getStackTrace(x), false);
      }
    } catch (IOException x) {
      PageObjectLogging.log("Error while creating/closing http client.", ExceptionUtils.getStackTrace(x), false);
    }

    return result;
  }
}
