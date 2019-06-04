package com.wikia.webdriver.common.core.api;

import static org.testng.util.Strings.escapeHtml;

import com.wikia.webdriver.common.core.Helios;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.common.remote.operations.http.PostRemoteOperation;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.WebDriverException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public abstract class ApiCall {

  protected static String URL_STRING = null;
  private static String ERROR_MESSAGE = "Problem with API call";

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

  /**
   * This enables passing username as string instead from ENUM.
   *
   * @return Username to log in.
   */
  abstract protected String getUserName();

  public void call() {
    try {
      CloseableHttpClient httpClient = HttpClientBuilder.create()
          .disableAutomaticRetries()
          .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
          .build();
      HttpPost httpPost = new HttpPost(UrlBuilder.stripUrlFromEnvSpecificPartAndDowngrade(getURL()));
      PostRemoteOperation.setBorderProxy(httpPost);
      // set header
      PostRemoteOperation.addXstagingHeaderIfNeeded(httpPost);

      if (getUserName() != null) {
        httpPost.addHeader("X-Wikia-AccessToken", Helios.getAccessToken(getUserName()));
      } else if (getUser() != null) {
        httpPost.addHeader("X-Wikia-AccessToken", Helios.getAccessToken(getUser().getUserName()));
      }
      // set query params
      if (getParams() != null) {
        httpPost.setEntity(new UrlEncodedFormEntity(getParams(), StandardCharsets.UTF_8));
      }

      CloseableHttpResponse resp = httpClient.execute(httpPost);

      Log.info("CONTENT PUSH: ", "Content posted to: " + httpPost.toString());
      Log.info("CONTENT PUSH: ", "Response: " + escapeHtml(EntityUtils.toString(resp.getEntity(), "UTF-8")));
    } catch (ClientProtocolException e) {
      Log.log("EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(ERROR_MESSAGE);
    } catch (IOException e) {
      Log.log("IO EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(ERROR_MESSAGE);
    }
  }
}
