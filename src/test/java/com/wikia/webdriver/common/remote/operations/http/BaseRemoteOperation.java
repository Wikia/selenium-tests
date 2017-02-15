package com.wikia.webdriver.common.remote.operations.http;

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
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
class BaseRemoteOperation {

  @Getter
  private final User user;

  public String execute(final HttpRequestBase request) {
    String result = StringUtils.EMPTY;

    try (CloseableHttpClient client = HttpClientBuilder.create().disableContentCompression().build()) {
      result = makeRequest(client, request);
    } catch (IOException x) {
      PageObjectLogging.log("Error while creating/closing http client.", ExceptionUtils.getStackTrace(x), false);
    }

    return result;
  }

  String execute(final HttpEntityEnclosingRequestBase request, final JSONObject jsonObject) {
    String result = StringUtils.EMPTY;

    try {
      request.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
      request.setEntity(new StringEntity(jsonObject.toString(), ContentType.APPLICATION_JSON));
      result = execute(request);
    } catch (RemoteException ex) {
      PageObjectLogging.log("Error while creating http post entity.", ExceptionUtils.getStackTrace(ex), false);
    }

    return result;
  }

  private String makeRequest(final CloseableHttpClient client, final HttpRequestBase request)
      throws IOException {
    String result = StringUtils.EMPTY;
    if(user != null) {
      request.setHeader(Discussions.ACCESS_TOKEN_HEADER, Helios.getAccessToken(user));
    }
    try (CloseableHttpResponse response = client.execute(request)) {
      result = handleResponse(request, response);
    } catch (UnsupportedEncodingException x) {
      PageObjectLogging.log("Error while creating post entity.", ExceptionUtils.getStackTrace(x), false);
    }

    return result;
  }

  String handleResponse(final HttpRequestBase requestBase, final CloseableHttpResponse response)
      throws IOException {
    String result = StringUtils.EMPTY;
    final HttpEntity entity = response.getEntity();

    if (null != entity) {
      result = EntityUtils.toString(entity);

      if (response.getStatusLine().getStatusCode() >= 400) {
        throw new RemoteException("Error while invoking request. "
            + " Method: " + requestBase.getMethod()
            + " Url: " + requestBase.getURI().toString()
            + " Response: " + result);
      }
    }
    return result;
  }
}
