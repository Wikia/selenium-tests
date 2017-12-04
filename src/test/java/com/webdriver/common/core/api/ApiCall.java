package com.webdriver.common.core.api;

import com.webdriver.common.core.helpers.User;
import com.webdriver.common.core.Helios;
import com.webdriver.common.logging.PageObjectLogging;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.WebDriverException;

import java.io.IOException;
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
      CloseableHttpClient httpClient = HttpClientBuilder.create().disableAutomaticRetries().build();
      HttpPost httpPost = new HttpPost(getURL());
      // set header
      if (getUser() != null) {
        httpPost.addHeader("X-Wikia-AccessToken", Helios.getAccessToken(getUser()));
      }
      // set query params
      if (getParams() != null) {
        httpPost.setEntity(new UrlEncodedFormEntity(getParams(), StandardCharsets.UTF_8));
      }

      CloseableHttpResponse resp = httpClient.execute(httpPost);

      PageObjectLogging.logInfo("CONTENT PUSH: ", "Content posted to: " + httpPost.toString());
      PageObjectLogging.logInfo("CONTENT PUSH: ", "Response: " + EntityUtils.toString(resp.getEntity(), "UTF-8"));
    } catch (ClientProtocolException e) {
      PageObjectLogging.log("EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(ERROR_MESSAGE);
    } catch (IOException e) {
      PageObjectLogging.log("IO EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(ERROR_MESSAGE);
    }
  }
}
