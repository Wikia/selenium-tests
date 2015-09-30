package com.wikia.webdriver.common.core.api;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.WebDriverException;

import com.wikia.webdriver.common.core.Helios;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.logging.LOG;

/**
 * Created by wikia on 2015-08-27.
 */
public abstract class ApiCall {

  protected static String URL_STRING = null;
  private static String ERROR_MESSAGE = "Problem with API call";

  protected ApiCall() {

  }

  public static HttpPost getHtppPost(URL url) throws URISyntaxException {
    return new HttpPost(new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(),
        url.getPath(), url.getQuery(), url.getRef()));
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
      CloseableHttpClient httpClient = HttpClients.createDefault();
      HttpPost httpPost = getHtppPost(url);
      // set header
      if (getUser() != null) {
        httpPost.addHeader("X-Wikia-AccessToken", Helios.getAccessToken(getUser()));
      }
      // set query params
      if (getParams() != null) {
        httpPost.setEntity(new UrlEncodedFormEntity(getParams(), StandardCharsets.UTF_8));
      }

      httpClient.execute(httpPost);

      LOG.log("CONTENT PUSH", "Content posted to: " + URL_STRING, LOG.Type.INFO);
    } catch (ClientProtocolException e) {
      LOG.error("EXCEPTION", ExceptionUtils.getStackTrace(e));
      throw new WebDriverException(ERROR_MESSAGE);
    } catch (IOException e) {
      LOG.error("IO EXCEPTION", ExceptionUtils.getStackTrace(e));
      throw new WebDriverException(ERROR_MESSAGE);
    } catch (URISyntaxException e) {
      LOG.error("URI_SYNTAX EXCEPTION", ExceptionUtils.getStackTrace(e));
      throw new WebDriverException(ERROR_MESSAGE);
    }
  }
}
