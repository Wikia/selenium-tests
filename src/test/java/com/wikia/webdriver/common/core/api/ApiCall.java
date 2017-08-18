package com.wikia.webdriver.common.core.api;

import com.wikia.webdriver.common.core.Helios;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.WebDriverException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public abstract class ApiCall {

  private static String ERROR_MESSAGE = "Problem with API call";

  protected static String URL_STRING = null;

  protected ApiCall() {
  }

  abstract protected String getURL();

  /**
   * Return null if API call doesn't require to be logged in as specific user
   *
   * @return User to be logged in while executing API call
   */
  abstract protected User getUser();

  /**
   * Return null when no params should be added to API call
   *
   * @return params
   */
  abstract protected ArrayList<BasicNameValuePair> getParams();

  public void call() {
    try {
      URL url = new URL(getURL());
      CloseableHttpClient httpClient = HttpClientBuilder.create().disableAutomaticRetries().build();
      HttpPost httpPost = getHttpPost(url);
      // set header
      if (getUser() != null) {
        httpPost.addHeader("X-Wikia-AccessToken", Helios.getAccessToken(getUser()));
      }
      // set query params
      if (getParams() != null) {
        httpPost.setEntity(new UrlEncodedFormEntity(getParams(), StandardCharsets.UTF_8));
      }

      CloseableHttpResponse resp = httpClient.execute(httpPost);

      PageObjectLogging.logInfo("CONTENT PUSH: ", "Header: " + httpPost.getHeaders("X-Wikia-AccessToken")[0]);
      PageObjectLogging.logInfo("CONTENT PUSH: ", "Content posted to: " + httpPost.toString());
      PageObjectLogging.logInfo("CONTENT PUSH: ", "Response: " + EntityUtils.toString(resp.getEntity(), "UTF-8"));
    } catch (ClientProtocolException e) {
      PageObjectLogging.log("EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(ERROR_MESSAGE);
    } catch (IOException e) {
      PageObjectLogging.log("IO EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(ERROR_MESSAGE);
    } catch (URISyntaxException e) {
      PageObjectLogging.log("URI_SYNTAX EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(ERROR_MESSAGE);
    }
  }

  public static HttpPost getHttpPost(URL url) throws URISyntaxException {
    return new HttpPost(new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(),
        url.getPath(), url.getQuery(), url.getRef()));
  }

  public static HttpGet getHttpGet(URL url) throws URISyntaxException {
    return new HttpGet(new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(),
        url.getPath(), url.getQuery(), url.getRef()));
  }
}
