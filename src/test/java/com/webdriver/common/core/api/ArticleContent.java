package com.webdriver.common.core.api;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.webdriver.common.contentpatterns.PageContent;
import com.webdriver.common.core.TestContext;
import com.webdriver.common.core.configuration.Configuration;
import com.webdriver.common.core.helpers.User;
import com.webdriver.common.core.url.UrlBuilder;
import com.webdriver.common.logging.PageObjectLogging;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.WebDriverException;

@lombok.RequiredArgsConstructor
public class ArticleContent extends ApiCall {
  private String baseURL = new UrlBuilder().getUrlForWiki(Configuration.getWikiName())
      + "/api.php";
  private ArrayList<BasicNameValuePair> params = new ArrayList<>();
  private User user = User.STAFF;

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

  public void push(String text, String articleTitle) {
    String editToken = new EditToken(user).getEditToken();
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
