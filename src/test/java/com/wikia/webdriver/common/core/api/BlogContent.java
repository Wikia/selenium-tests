package com.wikia.webdriver.common.core.api;

import static org.testng.util.Strings.escapeHtml;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Helios;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.common.remote.operations.http.PostRemoteOperation;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.server.HttpStatusCodes;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class BlogContent extends ApiCall {

  private static String ERROR_MESSAGE = "Problem with API call";
  private String
      baseURL =
      UrlBuilder.createUrlBuilder().getUrlForWikiPage("Special:CreateBlogPage");
  private ArrayList<BasicNameValuePair> params = new ArrayList<>();
  private User user = User.USER;
  private String username;

  public BlogContent(User user) {
    this.user = user;
  }

  public BlogContent(String username) {
    this.username = username;
  }

  @Override
  protected String getURL() {
    return URL_STRING;
  }

  @Override
  protected User getUser() {
    return user;
  }

  @Override
  protected String getUserName() {
    return username;
  }

  @Override
  protected ArrayList<BasicNameValuePair> getParams() {
    return params;
  }

  public void push(String text, String articleTitle) {
    String editToken;
    DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("yyyyMMddHHmmss");
    String editTime = DateTime.now().toString(timeFormatter);
    if (username != null) {
      editToken = new EditToken(username).getEditToken();
    } else {
      editToken = new EditToken(user).getEditToken();
    }
    try {
      URL_STRING = new URIBuilder(baseURL)
          .setParameter("action", "submit")
          .setParameter("categories", "")
          .setParameter("RTEMode", "source")
          .setParameter("RTETemporarySaveType", "")
          .setParameter("RTETemporarySaveContent", "")
          .setParameter("wpSection", "")
          .setParameter("wpStarttime", editTime)
          .setParameter("wpEdittime", editTime)
          .setParameter("wpScrolltop", "0")
          .setParameter("wpLogin", "")
          .setParameter("pageId", "0")
          .setParameter("wpTitle", articleTitle)
          .setParameter("wpAutoSummary", "")
          .setParameter("summary", "SUMMARY_QM")
          .setParameter("oldid", "0")
          .setParameter("wpTextbox1", text)
          .setParameter("wpWatchthis", "off")
          .setParameter("wpEditToken", editToken)
          .setParameter("antispam", "")
          .setParameter("wpIsCommentingEnabled", "on")
          .setParameter("wpSummary", "QA")
          .setParameter("CategorySelectInput", "")
          .build()
          .toASCIIString();
    } catch (URISyntaxException e) {
      Log.log("URI_SYNTAX EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException("Failed to build edit API URL");
    }
    try {
      CloseableHttpClient httpClient = HttpClientBuilder.create()
          .disableAutomaticRetries()
          .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
          .build();
      HttpPost
          httpPost =
          new HttpPost(UrlBuilder.stripUrlFromEnvSpecificPartAndDowngradeForAPI(getURL()));
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

      // Retry if 200, since on that specific call, it should be 302 redirect to created page,
      // so 200 is probably an error
      if (resp.getStatusLine().getStatusCode() == HttpStatusCodes.OK) {
        httpPost.releaseConnection();
        Log.info("CONTENT PUSH: ", "Retry push content to: " + httpPost.toString());
        Log.info("CONTENT PUSH: ", "STATUS before retry: " + resp.getStatusLine());
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        resp = httpClient.execute(httpPost);
      }

      if (resp.getStatusLine().getStatusCode() == HttpStatusCodes.OK) {
        httpPost.releaseConnection();
        Log.info("CONTENT PUSH: ", "Retry again push content to: " + httpPost.toString());
        Log.info("CONTENT PUSH: ", "STATUS before retry: " + resp.getStatusLine());
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        resp = httpClient.execute(httpPost);
      }

      Log.info("CONTENT PUSH: ", "Content posted to: " + httpPost.toString());
      Log.info("CONTENT PUSH: ", "STATUS: " + resp.getStatusLine());
      Log.info("CONTENT PUSH: ", "Response: " + escapeHtml(
          EntityUtils.toString(resp.getEntity(), "UTF-8")));
    } catch (ClientProtocolException e) {
      Log.log("EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(ERROR_MESSAGE);
    } catch (IOException e) {
      Log.log("IO EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(ERROR_MESSAGE);
    }
  }

  public void push(String content) {
    push(content, TestContext.getCurrentMethodName());
  }

  public void push() {
    push(PageContent.ARTICLE_TEXT, TestContext.getCurrentMethodName());
  }
}

