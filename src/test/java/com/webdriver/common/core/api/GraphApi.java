package com.webdriver.common.core.api;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.webdriver.common.logging.PageObjectLogging;
import com.webdriver.common.core.XMLReader;
import com.webdriver.common.core.helpers.FacebookUser;
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
import java.util.Collections;
import java.util.List;

public class GraphApi {

  private static final String ERROR_MESSAGE = "Problem with Graph API call used to create new facebook test user";
  private static final String URI_SYNTAX_EXCEPTION = "URI_SYNTAX EXCEPTION";
  private static final String WIKIA_PRODUCTION_APP_ACCESS_TOKEN = XMLReader
    .getValue("ci.user.facebook.prod.accessToken");
  private static final String WIKIA_PRODUCTION_APP_ID = XMLReader
    .getValue("ci.user.facebook.prod.appId");

  public FacebookUser createFacebookTestUser() {
    try {
      HttpResponse response = createTestUser(WIKIA_PRODUCTION_APP_ID);
      String entity = EntityUtils.toString(response.getEntity());
      DocumentContext json = JsonPath.parse(entity);
      return new FacebookUser(
        json.read("$.email"),
        json.read("$.password"),
        json.read("$.id"));
    } catch (IOException | URISyntaxException e) {
      PageObjectLogging.log(URI_SYNTAX_EXCEPTION, ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(ERROR_MESSAGE);
    }

  }

  public void deleteFacebookTestUser(String userId) {
    try {
      deleteTestUser(userId);
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

  private List<BasicNameValuePair> getParams() {
    return Collections.singletonList(
      new BasicNameValuePair("access_token", WIKIA_PRODUCTION_APP_ACCESS_TOKEN));
  }

  private HttpResponse createTestUser(String appId) throws IOException, URISyntaxException {
    URL url = new URL(getURLCreateUser(appId));
    CloseableHttpClient httpClient = HttpClientBuilder.create().disableAutomaticRetries().build();
    HttpPost httpPost = getHttpPost(url);
    httpPost.setEntity(new UrlEncodedFormEntity(getParams(), StandardCharsets.UTF_8));
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
      )).addParameter("access_token", WIKIA_PRODUCTION_APP_ACCESS_TOKEN).build());
  }

  private static HttpPost getHttpPost(URL url) throws URISyntaxException {
    return new HttpPost(new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(),
      url.getPath(), url.getQuery(), url.getRef()));
  }
}

