package com.wikia.webdriver.common.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriverException;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.properties.HeliosConfig;

public class Helios {

  private static final Map<String, String> tokenCache = new HashMap<>();
  private static final String IOEXCEPTION_ERROR_MESSAGE = "PLEASE CHECK IF YOUR VPN IS ENABLED";
  private static final String IOEXCEPTION_COMMAND = "IO EXCEPTION";
  private static final String X_WIKIA_INTERNAL_REQUEST = "X-Wikia-Internal-Request";
  private static final String THE_SCHWARTZ = "THE-SCHWARTZ";

  /**
   * Standard cookie spec is used instead of default one in order to suppress warnings about
   * SetCookie header values containing un-escaped commas (e.g.
   * "expires=Sat, 09 Sep 2017 15:33:53 GMT")
   */
  private static final RequestConfig REQUEST_CONFIG = RequestConfig.custom().setConnectTimeout(3000)
      .setSocketTimeout(3000).setCookieSpec(CookieSpecs.STANDARD).build();

  private Helios() {
    for (User user : User.values()) {
      if (StringUtils.isNotBlank(user.getAccessToken())) {
        tokenCache.put(user.getUserName(), user.getAccessToken());
      }
    }
  }

  public static String getAccessToken(User user) {
    return getAccessToken(user.getUserName());
  }

  public static void deleteAllTokens(User user) {
    String heliosGetTokenURL = HeliosConfig.getUrl(HeliosConfig.HeliosController.USERS);

    HttpDelete httpDelete =
        new HttpDelete(String.format("%s/%s/tokens", heliosGetTokenURL, user.getUserId()));
    httpDelete.setHeader(THE_SCHWARTZ, Configuration.getCredentials().apiToken);
    httpDelete.setHeader(X_WIKIA_INTERNAL_REQUEST, "0");

    try (CloseableHttpResponse response = getDefaultClient().execute(httpDelete)) {
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

  public static void updateTokenCache(){
    for (User user : User.values()) {
      if (StringUtils.isNotBlank(user.getAccessToken())) {
        tokenCache.put(user.getUserName(), user.getAccessToken());
      }
    }
  }

  public static String getAccessToken(String userName) {
    if (StringUtils.isNotBlank(getTokenFromCache(userName))) {
      return tokenCache.get(userName);
    }

    String heliosGetTokenURL = String.format("%s/%s/tokens",
        HeliosConfig.getUrl(HeliosConfig.HeliosController.USERS), getUserId(userName));

    HttpPost httpPost = new HttpPost(heliosGetTokenURL);
    httpPost.setHeader(X_WIKIA_INTERNAL_REQUEST, "0");

    try {
      String token = executeAndRetry(httpPost, extractAccessToken());
      tokenCache.put(userName, token);
      return token;
    } catch (ClientProtocolException e) {
      PageObjectLogging.log("CLIENT PROTOCOL EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(e);
    } catch (IOException e) {
      PageObjectLogging.log(IOEXCEPTION_COMMAND,
          IOEXCEPTION_ERROR_MESSAGE + ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(e);
    }
  }

  private static ResponseHandler<String> extractAccessToken() {
    return res -> {
      HttpEntity entity = res.getEntity();
      PageObjectLogging.logInfo("LOGIN HEADERS: ", res.toString());
      String source = EntityUtils.toString(entity);
      PageObjectLogging.logInfo("LOGIN RESPONSE RAW: ", source);
      try {
        JSONObject responseValue = new JSONObject(source);
        return responseValue.getString("access_token");
      } catch (JSONException e) {
        PageObjectLogging.log("JSON EXCEPTION", ExceptionUtils.getStackTrace(e), false);
        throw new WebDriverException(e);
      }
    };
  }

  private static ResponseHandler<String> extractUserId() {
    return res -> {
      HttpEntity entity = res.getEntity();
      PageObjectLogging.logInfo("USER_ID HEADERS: ", res.toString());
      String source = EntityUtils.toString(entity);
      PageObjectLogging.logInfo("USER_ID RESPONSE RAW: ", source);
      try {
        JSONObject responseValue = new JSONObject(source);
        return responseValue.getJSONObject("query").getJSONArray("users").getJSONObject(0)
            .getString("userid");
      } catch (JSONException e) {
        PageObjectLogging.log("JSON EXCEPTION", ExceptionUtils.getStackTrace(e), false);
        throw new WebDriverException(e);
      }
    };
  }

  private static String executeAndRetry(HttpPost httpPost, ResponseHandler<String> handler)
      throws IOException {
    try (CloseableHttpClient httpClient = getDefaultClient()) {
      try {
        return httpClient.execute(httpPost, handler);
      } catch (ConnectTimeoutException e) {
        PageObjectLogging.log("Timeout when connecting to helios", e, true);
        return httpClient.execute(httpPost, handler);
      }
    }
  }

  private static String getTokenFromCache(String userName) {
    try (CloseableHttpClient httpClient = getDefaultClient()) {
      if (tokenCache.containsKey(userName)) {

        String getTokenInfoURL = HeliosConfig.getUrl(HeliosConfig.HeliosController.INFO)
            + String.format("?code=%s&noblockcheck", tokenCache.get(userName));
        HttpGet getInfo = new HttpGet(getTokenInfoURL);
        getInfo.setHeader(X_WIKIA_INTERNAL_REQUEST, "0");

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

  private static String getUserId(String userName) {
    String getUserIDURL = "";
    try {
      getUserIDURL = String.format("%s/api.php?action=query&list=users&ususers=%s&format=json",
          new UrlBuilder().getUrlForWiki(), URLEncoder.encode(userName, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      PageObjectLogging.logError("UNSUPPORTED ENCODING EXCEPTION", e);
      throw new WebDriverException(e);
    }

    HttpGet httpGet = new HttpGet(getUserIDURL);

    try {
      return getDefaultClient().execute(httpGet, extractUserId());
    } catch (ClientProtocolException e) {
      PageObjectLogging.log("CLIENT PROTOCOL EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(e);
    } catch (IOException e) {
      PageObjectLogging.log(IOEXCEPTION_COMMAND,
          IOEXCEPTION_ERROR_MESSAGE + ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(e);
    }
  }

  private static CloseableHttpClient getDefaultClient() {
    return HttpClientBuilder.create().disableCookieManagement().disableConnectionState()
        .disableAutomaticRetries().setDefaultRequestConfig(REQUEST_CONFIG).build();
  }
}
