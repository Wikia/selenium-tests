package com.wikia.webdriver.common.core;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriverException;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.properties.HeliosConfig;

public class Helios {

  private static final Map<String, String> tokenCache = new HashMap<String, String>();
  private static final String IOEXCEPTION_ERROR_MESSAGE = "PLEASE CHECK IF YOUR VPN IS ENABLED";
  private static final String IOEXCEPTION_COMMAND = "IO EXCEPTION";
  private static RequestConfig requestConfig =
      RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(3000).build();

  private Helios() {

    for (User user : User.values()) {
      if (StringUtils.isNotBlank(user.getAccessToken())) {
        tokenCache.put(user.getUserName(), user.getAccessToken());
      }
    }
  }

  public static String getAccessToken(User user) {
    return getAccessToken(user.getUserName(), user.getPassword());
  }

  public static void deleteAllTokens(User user) {
    String heliosGetTokenURL = HeliosConfig.getUrl(HeliosConfig.HeliosController.USERS);

    HttpDelete httpDelete =
        new HttpDelete(String.format("%s/%s/tokens", heliosGetTokenURL, user.getUserId()));
    httpDelete.setConfig(requestConfig);
    httpDelete.setHeader("THE-SCHWARTZ", Configuration.getCredentials().apiToken);

    CloseableHttpResponse response = null;
    try {
      response = getDefaultClient().execute(httpDelete);

      PageObjectLogging.log("DELETE HEADERS: ", response.toString(), true);

    } catch (ClientProtocolException e) {
      PageObjectLogging.log("CLIENT PROTOCOL EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(e);
    } catch (IOException e) {
      PageObjectLogging.log(IOEXCEPTION_COMMAND,
          IOEXCEPTION_ERROR_MESSAGE + ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(e);
    }
  }

  public static String getAccessToken(String userName, String password) {

    for (User user : User.values()) {
      if (userName.equals(user.getUserName()) && StringUtils.isNotBlank(user.getAccessToken())) {
        tokenCache.put(userName, user.getAccessToken());
      }
    }

    String heliosGetTokenURL = HeliosConfig.getUrl(HeliosConfig.HeliosController.TOKEN);

    CloseableHttpClient httpClient = getDefaultClient();

    if (StringUtils.isNotBlank(getTokenFromCache(userName))) {
      return tokenCache.get(userName);
    }

    HttpPost httpPost = new HttpPost(heliosGetTokenURL);
    httpPost.setConfig(requestConfig);
    List<NameValuePair> nvps = new ArrayList<>();

    nvps.add(new BasicNameValuePair("grant_type", HeliosConfig.GrantType.PASSWORD.getGrantType()));
    nvps.add(new BasicNameValuePair("username", userName));
    nvps.add(new BasicNameValuePair("password", password));

    CloseableHttpResponse response = null;
    String token = "";
    httpPost.setEntity(new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));
    try {
      try {
        response = httpClient.execute(httpPost);
      } catch (ConnectTimeoutException e) {
        PageObjectLogging.log("Timeout when connecting to helios", e, true);
        response = httpClient.execute(httpPost);
      }

      System.out.println(response);

      HttpEntity entity = response.getEntity();
      JSONObject responseValue = new JSONObject(EntityUtils.toString(entity));

      EntityUtils.consume(entity);

      PageObjectLogging.log("LOGIN HEADERS: ", response.toString(), true);
      PageObjectLogging.log("LOGIN RESPONSE: ", responseValue.toString(), true);

      token = responseValue.getString("access_token");
      tokenCache.put(userName, token);
    } catch (JSONException e) {
      PageObjectLogging.log("JSON EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(e);
    } catch (ClientProtocolException e) {
      PageObjectLogging.log("CLIENT PROTOCOL EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(e);
    } catch (IOException e) {
      PageObjectLogging.log(IOEXCEPTION_COMMAND,
          IOEXCEPTION_ERROR_MESSAGE + ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(e);
    }
    return token;
  }

  private static String getTokenFromCache(String userName) {
    CloseableHttpClient httpClient = getDefaultClient();
    try {
      if (tokenCache.containsKey(userName)) {

        String getTokenInfoURL = HeliosConfig.getUrl(HeliosConfig.HeliosController.INFO)
            + String.format("?code=%s&noblockcheck", tokenCache.get(userName));
        HttpGet getInfo = new HttpGet(getTokenInfoURL);
        getInfo.setConfig(requestConfig);

        if (httpClient.execute(getInfo).getStatusLine().getStatusCode() == 200) {
          return tokenCache.get(userName);
        }
      }
    } catch (IOException e) {
      PageObjectLogging.log(IOEXCEPTION_COMMAND,
          IOEXCEPTION_ERROR_MESSAGE + ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(e);
    }
    return "";
  }

  private static CloseableHttpClient getDefaultClient() {
    return HttpClientBuilder.create().disableCookieManagement().disableConnectionState()
        .disableAutomaticRetries().build();
  }
}
