package com.wikia.webdriver.common.core.api;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.logging.Log;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.WebDriverException;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ArticleContent extends ApiCall {

  private String baseURL;

  private ArrayList<BasicNameValuePair> params = new ArrayList<>();
  private User user = User.STAFF;
  private String username;

  public ArticleContent() {
    this.baseURL = UrlBuilder.stripUrlFromEnvSpecificPartAndDowngrade(
        UrlBuilder.createUrlBuilder().getUrl() + "/api.php");
  }

  /**
   * Push content, overriding a default user
   */
  public ArticleContent(User user) {
    this();
    this.user = user;
  }

  public ArticleContent(String username) {
    this();
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
    String editToken = "";
    if (username != null) {
      editToken = new EditToken(username).getEditToken();
    } else {
      editToken = new EditToken(user).getEditToken();
    }
    try {
      URL_STRING = new URIBuilder(baseURL).setParameter("text", text)
          .setParameter("summary", "SUMMARY_QM")
          .setParameter("title", articleTitle)
          .setParameter("action", "edit")
          .setParameter("format", "json")
          .setParameter("token", editToken)
          .build()
          .toASCIIString();
    } catch (URISyntaxException e) {
      Log.log("URI_SYNTAX EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException("Failed to build edit API URL");
    }
    call();
  }

  public void push(String content) {
    push(content, TestContext.getCurrentMethodName());
  }

  public String createUniqueArticle() {
    String uniqueText = String.format("%s%s", PageContent.ARTICLE_NAME_PREFIX, LocalDateTime.now());
    push(uniqueText, uniqueText);

    return uniqueText;
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
