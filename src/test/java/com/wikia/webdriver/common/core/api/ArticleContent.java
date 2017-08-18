package com.wikia.webdriver.common.core.api;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriverException;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Helios;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import java.net.URL;
import java.util.Iterator;

@lombok.RequiredArgsConstructor
public class ArticleContent extends ApiCall {
  private String baseURL = new UrlBuilder().getUrlForWiki(Configuration.getWikiName())
      + "/api.php";
  private ArrayList<BasicNameValuePair> params = new ArrayList<>();
  private User user = User.STAFF;
  private String editToken;
  private static String EDIT_TOKEN_ERROR_MESSAGE = "Problem with edit token API call";

  /**
   * Push content, overriding a default user
   * @param user
   */
  public ArticleContent(User user) {
    this.user = user;
  }

  @Override
  protected String getURL() {
    return URL_STRING;
  }

  @Override protected User getUser() {
    return user;
  }

  @Override
  protected ArrayList<BasicNameValuePair> getParams() {

    return params;
  }

  private static ResponseHandler<String> extractEditToken() {
    return res -> {
      HttpEntity entity = res.getEntity();
      PageObjectLogging.logInfo("EDIT TOKEN HEADERS: ", res.toString());
      String source = EntityUtils.toString(entity);
      PageObjectLogging.logInfo("EDIT TOKEN RESPONSE RAW: ", source);
      try {
        JSONObject pagesValue = new JSONObject(source).getJSONObject("query").getJSONObject("pages");
        Iterator pageIterator = pagesValue.keys();
        if (pageIterator.hasNext()) {
          String key = (String)pageIterator.next();
          JSONObject pageInfo = pagesValue.getJSONObject(key);
          return pageInfo.getString("edittoken");
        } else {
          throw new WebDriverException("Could not find page in edit token response");
        }
      } catch (JSONException e) {
        PageObjectLogging.log("JSON EXCEPTION", ExceptionUtils.getStackTrace(e), false);
        throw new WebDriverException(e);
      }
    };
  }

  private void getEditToken() {
    try {
      String apiURL = new URIBuilder(baseURL)
          .setParameter("action", "query")
          .setParameter("prop", "info")
          .setParameter("format", "json")
          .setParameter("intoken", "edit")
          .setParameter("titles", "Main Page")
          .build().toASCIIString();

      CloseableHttpClient httpClient = HttpClientBuilder.create().disableAutomaticRetries().build();
      HttpGet httpGet = new HttpGet(apiURL);
      // set header
      if (getUser() != null) {
        httpGet.addHeader("X-Wikia-AccessToken", Helios.getAccessToken(getUser()));
      }

      PageObjectLogging.logInfo("QUERY EDIT TOKEN: ", httpGet.toString());
      editToken = httpClient.execute(httpGet, extractEditToken());

      PageObjectLogging.logInfo("QUERY EDIT TOKEN: ", "Token fetched: " + editToken);
    } catch (ClientProtocolException e) {
      PageObjectLogging.log("EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(EDIT_TOKEN_ERROR_MESSAGE);
    } catch (IOException e) {
      PageObjectLogging.log("IO EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(EDIT_TOKEN_ERROR_MESSAGE);
    } catch (URISyntaxException e) {
      PageObjectLogging.log("URI_SYNTAX EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(EDIT_TOKEN_ERROR_MESSAGE);
    }
  }

  public void push(String text, String articleTitle) {
    getEditToken();
    try {
      URL_STRING = new URIBuilder(baseURL)
          .setParameter("text", text)
          .setParameter("summary", "SUMMARY_QM")
          .setParameter("title", articleTitle)
          .setParameter("action", "edit")
          .setParameter("format", "json")
          .setParameter("token", editToken)
          .build().toASCIIString();
    } catch (URISyntaxException e) {
      PageObjectLogging.log("URI_SYNTAX EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(EDIT_TOKEN_ERROR_MESSAGE);
    }
    call();
  }

  public void push(String content) {
    push(content, TestContext.getCurrentMethodName());
  }

  public void push() {
    push(PageContent.ARTICLE_TEXT, TestContext.getCurrentMethodName());
  }

  public ArticleContent clear(String articleTitle) {
    push("", articleTitle);

    return this;
  }

  public void clear() {
    push("", TestContext.getCurrentMethodName());
  }
}
