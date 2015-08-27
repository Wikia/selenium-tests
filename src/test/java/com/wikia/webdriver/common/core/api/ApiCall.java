package com.wikia.webdriver.common.core.api;

import com.wikia.webdriver.common.core.Helios;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.openqa.selenium.WebDriverException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wikia on 2015-08-27.
 */
public abstract class ApiCall {

  private static String ERROR_MESSAGE = "Problem with API call";

  protected static String URL_STRING = null;
  protected static User user = null;
  protected static List<NameValuePair> nvps = null;

  protected ApiCall() {
    user = null;
    nvps = new ArrayList<>();
  }

  public static void call() {
    try {
      URL url = new URL(URL_STRING);
      CloseableHttpClient httpClient = HttpClients.createDefault();
      HttpPost httpPost = getHtppPost(url);
      //set header
      if (user != null) {
        httpPost.addHeader("Authorization", "Bearer " + Helios.getAccessToken(user));
      }
      //set query params
      if (nvps != null) {
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));
      }

      httpClient.execute(httpPost);

      PageObjectLogging
          .log("CONTENT PUSH", "Content posted to: " + URL_STRING,
               true);
    } catch (ClientProtocolException e) {
      PageObjectLogging.log("EXCEPTION", e.toString(), false);
      throw new WebDriverException(ERROR_MESSAGE);
    } catch (IOException e) {
      PageObjectLogging.log("IO EXCEPTION", e.toString(), false);
      throw new WebDriverException(ERROR_MESSAGE);
    } catch (URISyntaxException e) {
      PageObjectLogging.log("URI_SYNTAX EXCEPTION", e.toString(), false);
      throw new WebDriverException(ERROR_MESSAGE);
    }
  }

  public static HttpPost getHtppPost(URL url) throws URISyntaxException {
    return
        new HttpPost(new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(),
                             url.getPath(), url.getQuery(), url.getRef()));
  }
}

