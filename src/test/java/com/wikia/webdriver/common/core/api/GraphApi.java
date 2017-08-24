package com.wikia.webdriver.common.core.api;

import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
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
import java.util.HashMap;

public class GraphApi {

  private static final String ERROR_MESSAGE = "Problem with Graph API call used to create new facebook test user";
  private static final String URI_SYNTAX_EXCEPTION = "URI_SYNTAX EXCEPTION";
  private static final String wikiaProductionAppAccessToken = XMLReader
    .getValue("ci.user.facebook.prod.accessToken");
  private static final String wikiaProductionAppId = XMLReader
    .getValue("ci.user.facebook.prod.appId");

  public HashMap<String, String> createFacebookTestUser() {
    try {
      HttpResponse response = createTestUser(wikiaProductionAppId);
      String entity = EntityUtils.toString(response.getEntity());
      return new Gson().fromJson(entity, new TypeToken<HashMap<String, String>>(){}.getType());
    } catch (IOException | URISyntaxException e) {
      PageObjectLogging.log(URI_SYNTAX_EXCEPTION, ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(ERROR_MESSAGE);
    }

  }

  public HashMap<String, String> deleteFacebookTestUser(String userId) {
    try {
      HttpResponse response = deleteTestUser(userId);
      String entity = EntityUtils.toString(response.getEntity());
      return new Gson().fromJson(entity, new TypeToken<HashMap<String, String>>(){}.getType());
    } catch (IOException | URISyntaxException e) {
      PageObjectLogging.log(URI_SYNTAX_EXCEPTION, ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(ERROR_MESSAGE);
    }

  }

  private String getURLCreateUser(String appId) {
    return String.format("https://graph.facebook.com/v2.6/%s/accounts/test-users", appId);
  }

  private String getURLDeleteUser(String userId) {
    return String.format("https://graph.facebook.com/v2.7/%s", userId);
  }

  private ArrayList<BasicNameValuePair> getParams() {
    ArrayList<BasicNameValuePair> PARAMS = new ArrayList<>();
    PARAMS.add(new BasicNameValuePair("access_token", wikiaProductionAppAccessToken));
    return PARAMS;
  }

  private HttpResponse createTestUser(String appId) throws IOException, URISyntaxException {
    URL url = new URL(getURLCreateUser(appId));
    CloseableHttpClient httpClient = HttpClientBuilder.create().disableAutomaticRetries().build();
    HttpPost httpPost = getHttpPost(url);

    if (getParams() != null) {
      httpPost.setEntity(new UrlEncodedFormEntity(getParams(), StandardCharsets.UTF_8));
    }
    return httpClient.execute(httpPost);
  }

  private HttpResponse deleteTestUser(String userId) throws IOException, URISyntaxException {
    URL url = new URL(getURLDeleteUser(userId));
    CloseableHttpClient httpClient = HttpClientBuilder.create().disableAutomaticRetries().build();
    HttpDelete httpDelete = getHttpDelete(url);
    return httpClient.execute(httpDelete);
  }

  private static HttpDelete getHttpDelete(URL url) throws URISyntaxException {
    return new HttpDelete(
      new URIBuilder((
        new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(),
          url.getPath(), url.getQuery(), url.getRef())
      )).addParameter("access_token", wikiaProductionAppAccessToken).build());
  }

  private static HttpPost getHttpPost(URL url) throws URISyntaxException {
    return new HttpPost(new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(),
      url.getPath(), url.getQuery(), url.getRef()));
  }
}

